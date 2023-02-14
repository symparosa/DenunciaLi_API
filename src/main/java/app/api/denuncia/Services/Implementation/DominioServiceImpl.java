package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Services.DominioService;

@Service
public class DominioServiceImpl implements DominioService {

    private DominioRepository domRepository;

    private String obj = "Dominio";
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public DominioServiceImpl(DominioRepository domRepository) {
        this.domRepository = domRepository;
    }

    @Override
    public ResponseModel adicionar_atualizar(List<DominioModel> dominio) {

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
                                dom.setData_atualizacao(new Date());
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
                        dom.setData_criacao(new Date());
                        dom.setLast_user_change(gf.getId_user_logado());
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
    public ResponseModel alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            if (domRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = domRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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
    public ResponseModel listar() {

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
    public ResponseModel getDominio(String dominio) {

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

    public ResponseModel saveAll(List<DominioModel> dominio, int contUpdate, int contInsert, boolean insert,
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
}
