package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Domain;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Models.EntidadeModel;
import app.api.denuncia.Models.EntidadeTipoCrimeModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Repositories.EntidadeRepository;
import app.api.denuncia.Repositories.EntidadeTipoCrimeRepository;
import app.api.denuncia.Services.EntidadeTipoCrimeService;

@Service
public class EntidadeTipoCrimeServiceImpl implements EntidadeTipoCrimeService {

    private EntidadeTipoCrimeRepository entidadeTipoCrimeRepository;
    private EntidadeServiceImpl entServiceImpl;
    private EntidadeRepository entRepository;
    private DominioServiceImpl domServiceImpl;

    private Domain dom = new Domain();
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public EntidadeTipoCrimeServiceImpl(EntidadeTipoCrimeRepository entidadeTipoCrimeRepository,
            EntidadeServiceImpl entServiceImpl, EntidadeRepository entRepository, DominioServiceImpl domServiceImpl) {
        this.entidadeTipoCrimeRepository = entidadeTipoCrimeRepository;
        this.entServiceImpl = entServiceImpl;
        this.entRepository = entRepository;
        this.domServiceImpl = domServiceImpl;
    }

    @Override
    public ResponseModel adicionar_atualizar(List<EntidadeTipoCrimeModel> entidadeTipoCrime) {

        gf.clearList(msg);

        try {

            if (entidadeTipoCrime.size() > 0) {

                if (!validateDuplicateData(entidadeTipoCrime)) {

                    String metodo = "salvar", obj = "Entidade";
                    int contUpdate = 0, contInsert = 0;
                    boolean update = false, insert = false;

                    for (EntidadeTipoCrimeModel ent : entidadeTipoCrime) {

                        if (entServiceImpl.existsEntidade(ent.getEntidade())) {

                            obj = "Tipo de crime";

                            if (domServiceImpl.existsTipo(ent.getTipoCrime(), dom.getTipoCrime())) {

                                if (ent.getId() != null) { // update

                                    obj = "Entidade tipo de crime";

                                    if (entidadeTipoCrimeRepository.existsById(ent.getId())) {
                                        update = true;
                                        if (entidadeTipoCrimeRepository.existsByEntidadeAndTipoCrimeAndIdNot(
                                                ent.getEntidade(),
                                                ent.getTipoCrime(),
                                                ent.getId())) {
                                            contUpdate++;
                                        }
                                        ent.setData_atualizacao(new Date());
                                    } else {
                                        msg.add(message.getMessage06(obj));
                                        return gf.getResponseError(msg);
                                    }
                                } else if (ent.getId() == null) { // insert
                                    insert = true;
                                    if (entidadeTipoCrimeRepository.existsByEntidadeAndTipoCrime(ent.getEntidade(),
                                            ent.getTipoCrime())) {
                                        contInsert++;
                                    }
                                    ent.setData_atualizacao(null);
                                }
                                ent.setEstado(status.getAtivo());
                                ent.setData_criacao(new Date());
                                ent.setLast_user_change(gf.getId_user_logado());
                            } else {
                                msg.add(message.getMessage06(obj));
                                return gf.getResponseError(msg);
                            }
                        } else {
                            msg.add(message.getMessage06(obj));
                            return gf.getResponseError(msg);
                        }
                    }

                    return saveAll(entidadeTipoCrime, contUpdate, contInsert, insert, update, metodo);

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

            String obj = "Entidade tipo de crime";

            if (entidadeTipoCrimeRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = entidadeTipoCrimeRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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
    public ResponseModel getInfoByEntidade(int id) {

        gf.clearList(msg);

        try {

            if (entidadeTipoCrimeRepository.count() > 0) {

                String obj = "Entidade";

                if (entRepository.existsById(id)) {

                    Optional<EntidadeModel> entidade = entRepository.findById(id);

                    obj = "Entidade tipo de crime";

                    if (entidadeTipoCrimeRepository.existsByEntidade(entidade.get())) {

                        String metodo = "listar";

                        List<EntidadeTipoCrimeModel> lista = entidadeTipoCrimeRepository
                                .findByEntidadeAndEstadoIn(entidade.get(), gf.getStatusAtivoInativo());
                        return gf.validateGetListMsg(metodo, lista);

                    } else {
                        msg.add(message.getMessage06(obj));
                        return gf.getResponseError(msg);
                    }
                } else {
                    msg.add(message.getMessage06(obj));
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

    public Boolean validateDuplicateData(List<EntidadeTipoCrimeModel> ent_tc) {

        boolean msg = false;

        for (EntidadeTipoCrimeModel ent : ent_tc) {
            if (ent_tc.stream().filter(
                    item -> item.getTipoCrime().getId().equals(ent.getTipoCrime().getId()))
                    .count() > 1) {
                msg = true;
            }
        }
        return msg;
    }

    public ResponseModel saveAll(List<EntidadeTipoCrimeModel> entidadeTipoCrime, int contUpdate, int contInsert,
            boolean insert, boolean update, String metodo) {

        if ((contUpdate == 0 && insert == false) || (contInsert == 0 && update == false)) {

            List<EntidadeTipoCrimeModel> ent = entidadeTipoCrimeRepository.saveAll(entidadeTipoCrime);
            return gf.validateGetSaveMsgWithList(metodo, ent);

        } else {
            msg.add(message.getMessage03());
            return gf.getResponseError(msg);
        }
    }
}
