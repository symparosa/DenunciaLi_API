package app.api.denuncia.Services.Implementation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Domain;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.EntidadeModel;
import app.api.denuncia.Models.EntidadeTipoCrimeModel;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.EntidadeRepository;
import app.api.denuncia.Repositories.EntidadeTipoCrimeRepository;
import app.api.denuncia.Services.EntidadeTipoCrimeService;

@Service
public class EntidadeTipoCrimeServiceImpl implements EntidadeTipoCrimeService {

    private EntidadeTipoCrimeRepository entidadeTipoCrimeRepository;
    private EntidadeRepository entidadeRepository;
    private DominioRepository dominioRepository;

    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();
    private Domain dom =  new Domain();

    public EntidadeTipoCrimeServiceImpl(EntidadeTipoCrimeRepository entidadeTipoCrimeRepository,
            EntidadeRepository entidadeRepository, DominioRepository dominioRepository) {
        this.entidadeTipoCrimeRepository = entidadeTipoCrimeRepository;
        this.entidadeRepository = entidadeRepository;
        this.dominioRepository = dominioRepository;
    }

    @Override
    public ResponseDto adicionar_atualizar(List<EntidadeTipoCrimeModel> entidadeTipoCrime) {
        try {

            if (entidadeTipoCrime.size() > 0) {

                if (!validateDuplicateData(entidadeTipoCrime)) {

                    String metodo = "salvar";
                    int contUpdate = 0, contInsert = 0;
                    boolean update = false, insert = false;

                    for (EntidadeTipoCrimeModel ent : entidadeTipoCrime) {

                        if (ent.getEntidade() != null
                                && entidadeRepository.existsById(ent.getEntidade().getId())
                                && ent.getTipoCrime() != null
                                && dominioRepository.existsByIdAndDominio(ent.getTipoCrime().getId(), dom.getDomainTipoCrime())) {

                            if (ent.getId() != null) { // update

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
                                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
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
                            return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                        }
                    }

                    if ((contUpdate == 0 && insert == false) || (contInsert == 0 && update == false)) {

                        List<EntidadeTipoCrimeModel> ent = entidadeTipoCrimeRepository.saveAll(entidadeTipoCrime);
                        return gf.validateGetSaveMsgWithList(metodo, ent);

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
            if (entidadeTipoCrimeRepository.existsById(id)) {

                if (estado == status.getAtivo() || estado == status.getInativo() || estado == status.getEliminado()) {

                    String metodo = "salvar";

                    Integer result = entidadeTipoCrimeRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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
    public ResponseDto getInfoByEntidade(int id) {
        try {

            if (entidadeTipoCrimeRepository.count() > 0) {

                if (entidadeRepository.existsById(id)) {

                    Optional<EntidadeModel> entidade = entidadeRepository.findById(id);

                    if (entidadeTipoCrimeRepository.existsByEntidade(entidade.get())) {

                        String metodo = "listar";

                        List<EntidadeTipoCrimeModel> lista = entidadeTipoCrimeRepository
                                .findByEntidadeAndEstadoIn(entidade.get(), gf.getStatusAtivoInativo());
                        return gf.validateGetListMsg(metodo, lista);

                    } else {
                        return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                    }
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                }
            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

    public Boolean validateDuplicateData(List<EntidadeTipoCrimeModel> ent_tc) {

        boolean msg = false;

        for (EntidadeTipoCrimeModel ent : ent_tc) {
            if (ent_tc.stream().filter(
                    item -> item.getEntidade().equals(ent.getEntidade())
                            && item.getTipoCrime().equals(ent.getTipoCrime()))
                    .count() > 1) {
                msg = true;
            }
        }
        return msg;
    }
}
