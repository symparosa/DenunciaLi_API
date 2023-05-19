package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.Domain;
import app.api.denuncia.Enums.DomainValue;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.EntidadeModel;
import app.api.denuncia.Repositories.EntidadeRepository;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Services.EntidadeService;
import app.api.denuncia.Services.LocalizacaoService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class EntidadeServiceImpl implements EntidadeService {

    private EntidadeRepository entRepository;
    private LocalizacaoService localService;
    private DominioService domService;
    private AuthenticationService auth;

    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();
    

    public EntidadeServiceImpl(EntidadeRepository entRepository, LocalizacaoService localService,
            DominioService domService, AuthenticationService auth) {
        this.entRepository = entRepository;
        this.localService = localService;
        this.domService = domService;
        this.auth = auth;
    }

    public int IdUserLogado(){
        return auth.getUtiLogado().getId();
    }
    
    @Override
    public Response adicionar_atualizar(EntidadeModel entidade) {

        gf.clearList(msg);

        try {

            String metodo = "salvar", obj = "Localização";

            entidade.setEstado(status.getAtivo());
            entidade.setData_criacao(new Date());
            entidade.setLast_user_change(IdUserLogado());

            if (localService.existsLocalizacao(entidade.getLocalizacao())) {

                obj = "Tipo de entidade";

                if (domService.existsTipo(entidade.getTipoEntidade(), Domain.TIPO_ENTIDADE.name())) {

                    if (entidade.getId() != null) { // update

                        obj = "Entidade";

                        return update(entidade, metodo, obj);

                    } else {

                        return insert(entidade, metodo);

                    }
                } else {
                    msg.add(message.getMessage06(obj));
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
    public Response alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            String obj = "Entidade";

            if (entRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = entRepository.alterarEstado(estado, IdUserLogado(), id);
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

            if (entRepository.count() > 0) {

                String metodo = "listar";

                List<EntidadeModel> lista = entRepository.findByEstadoIn(gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, lista);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public Response insert(EntidadeModel entidade, String metodo) {

        if (!entRepository.existsBySigla(entidade.getSigla())) {

            entidade.setData_atualizacao(null);
            EntidadeModel e = entRepository.save(entidade);
            return gf.validateGetSaveMsgWithObj(metodo, e);

        } else {
            msg.add(message.getMessage03());
            return gf.getResponseError(msg);
        }
    }

    public Response update(EntidadeModel entidade, String metodo, String obj) {

        if (entRepository.existsById(entidade.getId())) {

            if (!entRepository.existsBySiglaAndIdNot(entidade.getSigla(), entidade.getId())) {

                entidade.setData_atualizacao(new Date());
                EntidadeModel e = entRepository.save(entidade);
                return gf.validateGetSaveMsgWithObj(metodo, e);

            } else {
                msg.add(message.getMessage03());
                return gf.getResponseError(msg);
            }
        } else {
            msg.add(message.getMessage06(obj));
            return gf.getResponseError(msg);
        }
    }

    public Boolean existsEntidade(EntidadeModel ent) {

        if (ent != null && entRepository.existsByIdAndEstado(ent.getId(), status.getAtivo())) {
            return true;
        }
        return false;
    }

    @Override
    public Response get_by_id(int id) {

        gf.clearList(msg);

        try {

            if (entRepository.count() > 0) {

                String metodo = "listar", obj = "Entidade";
                ;

                EntidadeModel ent = entRepository.findById(id).orElse(null);
                return gf.validateGetMsgWithObj(metodo, ent, obj);

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
    public Response getEntidadeByTipo(String tipo) {
        
        gf.clearList(msg);

        try {

            if (tipo.equals(DomainValue.INSTITUICAO_APOIO.name()) || tipo.equals(DomainValue.CONTATO_IMPORTANTE.name())) {

                DominioModel dominioModel = domService.findByDominioAndValor(Domain.TIPO_ENTIDADE.name(), tipo);

                if (dominioModel != null) {

                    String metodo = "listar";

                    List<EntidadeModel> lista = entRepository.findByTipoEntidadeAndEstado(dominioModel,status.getAtivo());
                    return gf.validateGetListMsg(metodo, lista);

                } else {
                    msg.add(message.getMessage06(tipo));
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage12());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Boolean existsByIdAndEstado(int id, int estado) {
        return entRepository.existsByIdAndEstado(id,estado);
    }
}
