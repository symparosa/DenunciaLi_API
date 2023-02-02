package app.api.denuncia.Services.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Services.DominioService;

@Service
public class DominioServiceImpl implements DominioService {

    private DominioRepository dominioRepository;
    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();

    public DominioServiceImpl(DominioRepository dominioRepository) {
        this.dominioRepository = dominioRepository;
    }

    @Override
    public ResponseDto adicionar_atualizar(List<DominioModel> dominio) {
        try {

            if (dominio.size() > 0) {

                if (!validateDuplicateData(dominio)) {

                    String metodo = "salvar";
                    int contUpdate = 0, contInsert = 0;
                    boolean update = false, insert = false;

                    for (DominioModel dom : dominio) {

                        if (dom.getId() != null) { // update

                            if (dominioRepository.existsById(dom.getId())) {
                                update = true;
                                if (dominioRepository.existsByDominioAndValorAndIdNot(dom.getDominio(), dom.getValor(),
                                        dom.getId())) {
                                    contUpdate++;
                                }
                                dom.setData_atualizacao(new Date());
                            } else {
                                return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                            }
                        } else if (dom.getId() == null) { // insert
                            insert = true;
                            if (dominioRepository.existsByDominioAndValor(dom.getDominio(), dom.getValor())) {
                                contInsert++;
                            }
                            dom.setData_atualizacao(null);
                        }
                        dom.setEstado(status.getAtivo());
                        dom.setData_criacao(new Date());
                        dom.setLast_user_change(gf.getId_user_logado());
                    }

                    if ((contUpdate == 0 && insert == false) || (contInsert == 0 && update == false)) {

                        List<DominioModel> dom = dominioRepository.saveAll(dominio);
                        return gf.validateGetSaveMsgWithList(metodo, dom);

                    } else {
                        return gf.getResponse(0, ResponseType.Erro, msg.getMessage03(), null);
                    }
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage08(), null);
                }
            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

    @Override
    public ResponseDto alterarEstado(int id, int estado) {
        try {

            if (dominioRepository.existsById(id)) {

                if (estado == status.getAtivo() || estado == status.getInativo() || estado == status.getEliminado()) {

                    String metodo = "salvar";

                    Integer result = dominioRepository.alterarEstado(estado,gf.getId_user_logado(), id);
                    return gf.validateGetUpdateMsg(metodo, result);
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage07(), null);
                }
            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

    @Override
    public ResponseDto listar() {
        try {

            if (dominioRepository.count() > 0) {

                String metodo = "listar";

                List<DominioModel> listaDom = dominioRepository.findByEstadoIn(gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, listaDom);

            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

    @Override
    public ResponseDto getDominio(String dominio) {
        try {

            if (dominioRepository.existsByDominio(dominio)) {

                String metodo = "listar";

                List<DominioModel> listaDom = dominioRepository.findByDominioAndEstadoIn(dominio, gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, listaDom);

            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
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
}
