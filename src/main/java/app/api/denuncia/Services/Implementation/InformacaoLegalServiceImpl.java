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
import app.api.denuncia.Models.InformacaoLegalModel;
import app.api.denuncia.Repositories.InformacaoLegalRepository;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Services.InformacaoLegalService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class InformacaoLegalServiceImpl implements InformacaoLegalService {

    private InformacaoLegalRepository infoRepository;
    private DominioService domService;
    private AuthenticationService auth;

    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public InformacaoLegalServiceImpl(InformacaoLegalRepository infoRepository, DominioService domService,
            AuthenticationService auth) {
        this.infoRepository = infoRepository;
        this.domService = domService;
        this.auth = auth;
    }

    public int IdUserLogado() {
        return auth.getUtiLogado().getId();
    }

    @Override
    public Response adicionar_atualizar(InformacaoLegalModel infoLegal) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";

            infoLegal.setEstado(status.getAtivo());
            infoLegal.setData_criacao(new Date());
            infoLegal.setLast_user_change(IdUserLogado());

            String obj = "Tipo de informação legal";

            if (validateTipo(infoLegal.getTipoInformacaoLegal())) {

                if (infoLegal.getId() != null) {

                    return update(infoLegal, metodo);

                } else {

                    return insert(infoLegal, metodo);

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

            String obj = "Informação Legal";

            if (infoRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = infoRepository.alterarEstado(estado, IdUserLogado(), id);
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

            if (infoRepository.count() > 0) {

                String metodo = "listar";

                List<InformacaoLegalModel> lista = infoRepository.findByEstadoIn(gf.getStatusAtivoInativo());
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

    public Response insert(InformacaoLegalModel info, String metodo) {

        info.setData_atualizacao(null);
        InformacaoLegalModel inf = infoRepository.save(info);
        return gf.validateGetSaveMsgWithObj(metodo, inf);

    }

    public Response update(InformacaoLegalModel info, String metodo) {

        String obj = "Informação Legal";

        if (infoRepository.existsById(info.getId())) {

            info.setData_atualizacao(new Date());
            InformacaoLegalModel inf = infoRepository.save(info);
            return gf.validateGetSaveMsgWithObj(metodo, inf);

        } else {
            msg.add(message.getMessage06(obj));
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getInfoByTipo(String tipo) {

        gf.clearList(msg);

        try {

            if (tipo.equals(DomainValue.FAQ.name()) || tipo.equals(DomainValue.POLITICA.name())
                    || tipo.equals(DomainValue.TERMO.name())) {

                DominioModel dominioModel = domService.findByDominioAndValor(Domain.TIPO_INFO_LEGAL.name(), tipo);

                if (dominioModel != null) {

                    String metodo = "listar";

                    List<InformacaoLegalModel> lista = infoRepository.findByTipoInformacaoLegalAndEstado(dominioModel,
                            status.getAtivo());
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

    public Boolean validateTipo(DominioModel tipoInfo) {

        boolean msg = false;

        if (domService.existsTipo(tipoInfo, Domain.TIPO_INFO_LEGAL.name())) {
            msg = true;
        }
        return msg;
    }

    @Override
    public Response get_by_id(int id) {

        gf.clearList(msg);

        try {

            if (infoRepository.count() > 0) {

                String metodo = "listar", obj = "Informação Legal";

                InformacaoLegalModel info = infoRepository.findById(id).orElse(null);
                return gf.validateGetMsgWithObj(metodo, info, obj);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }
}
