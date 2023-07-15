package app.api.denuncia.Services.Implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class DominioServiceImpl implements DominioService {

    private DominioRepository domRepository;
    private AuthenticationService auth;

    private String obj = "Dominio";
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public DominioServiceImpl(DominioRepository domRepository, AuthenticationService auth) {
        this.domRepository = domRepository;
        this.auth = auth;
    }

    public int IdUserLogado() {
        return auth.getUtiLogado().getId();
    }

    @Override
    public Response adicionar_atualizar(List<DominioModel> dominio) {

        gf.clearList(msg);

        try {

            if (dominio.size() > 0) {

                if (!validateDuplicateData(dominio)) {

                    String metodo = "salvar";
                    int contUpdate = 0, contInsert = 0;
                    boolean update = false, insert = false;

                    for (DominioModel dom : dominio) {

                        if (dom.getId() != null) { // update

                            if (domRepository.existsById(dom.getId())) {
                                update = true;
                                if (domRepository.existsByDominioAndValorAndIdNot(dom.getDominio(), dom.getValor(),
                                        dom.getId())) {
                                    contUpdate++;
                                }
                                dom.setData_atualizacao(LocalDateTime.now());
                            } else {
                                msg.add(message.getMessage06(obj));
                                return gf.getResponseError(msg);
                            }
                        } else if (dom.getId() == null) { // insert
                            insert = true;
                            if (domRepository.existsByDominioAndValor(dom.getDominio(), dom.getValor())) {
                                contInsert++;
                            }
                            dom.setData_atualizacao(null);
                        }
                        dom.setEstado(status.getAtivo());
                        dom.setData_criacao(LocalDateTime.now());
                        dom.setLast_user_change(IdUserLogado());
                    }

                    return saveAll(dominio, contUpdate, contInsert, insert, update, metodo);

                } else {
                    msg.add(message.getMessage08());
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            if (domRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = domRepository.alterarEstado(estado, IdUserLogado(), id);
                    return gf.validateGetUpdateMsg(metodo, result);

                } else {
                    msg.add(message.getMessage07());
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage06(obj));
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response listar() {

        gf.clearList(msg);

        try {

            if (domRepository.count() > 0) {

                String metodo = "listar";

                List<DominioModel> listaDom = domRepository.findByEstadoIn(gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, listaDom);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getDominio(String dominio) {

        gf.clearList(msg);

        try {

            if (domRepository.existsByDominio(dominio)) {

                String metodo = "listar";

                List<DominioModel> listaDom = domRepository.findByDominioAndEstado(dominio,
                        status.getAtivo());
                return gf.validateGetListMsg(metodo, listaDom);

            } else {
                msg.add(message.getMessage06(obj));
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public Boolean validateDuplicateData(List<DominioModel> dominio) {

        boolean msg = false;

        for (DominioModel dom : dominio) {
            if (dominio.stream().filter(
                    item -> item.getDominio().equals(dom.getDominio()) && item.getValor().equals(dom.getValor()))
                    .count() > 1) {
                msg = true;
            }
        }
        return msg;
    }

    public Response saveAll(List<DominioModel> dominio, int contUpdate, int contInsert, boolean insert,
            boolean update, String metodo) {

        if ((contUpdate == 0 && insert == false) || (contInsert == 0 && update == false)) {

            List<DominioModel> dom = domRepository.saveAll(dominio);
            return gf.validateGetSaveMsgWithList(metodo, dom);

        } else {
            msg.add(message.getMessage03());
            return gf.getResponseError(msg);
        }
    }

    public Boolean existsTipo(DominioModel tipo, String dom) {

        if (tipo != null && domRepository.existsByIdAndDominio(tipo.getId(), dom)) {
            return true;
        }
        return false;
    }

    @Override
    public Response get_by_id(int id) {

        gf.clearList(msg);

        try {

            if (domRepository.count() > 0) {

                String metodo = "listar";

                DominioModel dom = domRepository.findById(id).orElse(null);
                return gf.validateGetMsgWithObj(metodo, dom, obj);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public DominioModel findByDominioAndValor(String dom, String tipo) {
        return domRepository.findByDominioAndValor(dom, tipo);
    }

    @Override
    public DominioModel findbyid(Integer id) {
        return domRepository.findById(id).orElse(null);
    }
}
