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
import app.api.denuncia.Models.ContatoModel;
import app.api.denuncia.Services.ContatoService;
import app.api.denuncia.Services.DenuncianteService;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Services.EntidadeService;
import app.api.denuncia.Services.UtilizadorService;
import app.api.denuncia.Utilities.GlobalFunctions;
import app.api.denuncia.Repositories.ContatoRepository;

@Service
public class ContatoServiceImpl implements ContatoService {

    private ContatoRepository contatoRepository;
    private DominioService domService;
    private EntidadeService entidadeService;
    private DenuncianteService denuncianteService;
    private UtilizadorService utilizadorService;
    private AuthenticationService auth;

    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public ContatoServiceImpl(ContatoRepository contatoRepository, DominioService domService,
            EntidadeService entidadeService, DenuncianteService denuncianteService, UtilizadorService utilizadorService,
            AuthenticationService auth) {
        this.contatoRepository = contatoRepository;
        this.domService = domService;
        this.entidadeService = entidadeService;
        this.denuncianteService = denuncianteService;
        this.utilizadorService = utilizadorService;
        this.auth = auth;
    }

    public int IdUserLogado() {
        return auth.getUtiLogado().getId();
    }

    @Override
    public Response adicionar_atualizar(List<ContatoModel> contatoModels) {

        gf.clearList(msg);

        try {
            if (contatoModels.size() > 0) {

                if (!validateDuplicateData(contatoModels)) {

                    String metodo = "salvar", obj = "Tipo de contato";

                    for (ContatoModel contato : contatoModels) {

                        if (domService.existsTipo(contato.getTipoContato(), Domain.TIPO_CONTATO.name())) {

                            obj = "Id Objeto";

                            if (contato.getIdObjeto() != null
                                    && validateIdObjeto(contato.getIdObjeto(), contato.getTipoObjeto())) {

                                if (!contato.getValor().equals("") && !contato.getValor().equals(null)) {

                                    if (contato.getId() != null) { // update

                                        obj = "Contato";

                                        if (contatoRepository.existsById(contato.getId())) {
                                            contato.setData_atualizacao(new Date());
                                        } else {
                                            msg.add(message.getMessage06(obj));
                                            return gf.getResponseError(msg);
                                        }
                                    } else if (contato.getId() == null) { // insert
                                        contato.setData_atualizacao(null);
                                    }
                                    contato.setEstado(status.getAtivo());
                                    contato.setData_criacao(new Date());
                                    contato.setLast_user_change(IdUserLogado());
                                    contato.setTipoObjeto("dn_t_" + contato.getTipoObjeto());
                                } else {
                                    msg.add(message.getMessage09("valor"));
                                    return gf.getResponseError(msg);
                                }
                            } else {
                                msg.add(message.getMessage06(obj));
                                return gf.getResponseError(msg);
                            }
                        } else {
                            msg.add(message.getMessage06(obj));
                            return gf.getResponseError(msg);
                        }
                    }

                    List<ContatoModel> cont = contatoRepository.saveAll(contatoModels);
                    return gf.validateGetSaveMsgWithList(metodo, cont);

                } else {
                    msg.add(message.getMessage08());
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (

        Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            String obj = "Contato";

            if (contatoRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = contatoRepository.alterarEstado(estado, IdUserLogado(), id);
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
    public Response getInfoByIdObjeto(int id_obj, String tipo_obj) {

        gf.clearList(msg);

        try {

            String obj = "Contato";
            String tipo_objeto = "dn_t_" + tipo_obj;

            if (contatoRepository.count() > 0) {

                if (contatoRepository.existsByIdObjetoAndTipoObjeto(id_obj, tipo_objeto)) {

                    String metodo = "listar";

                    List<ContatoModel> lista = contatoRepository.findByIdObjetoAndTipoObjetoAndEstadoIn(id_obj,
                            tipo_objeto,
                            gf.getStatusAtivoInativo());
                    return gf.validateGetListMsg(metodo, lista);

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

    public Boolean validateDuplicateData(List<ContatoModel> contato) {

        boolean msg = false;

        for (ContatoModel cont : contato) {
            if (contato.stream().filter(
                    item -> item.getValor().equals(cont.getValor()))
                    .count() > 1) {
                msg = true;
            }
        }
        return msg;
    }

    public Boolean validateIdObjeto(int id_objeto, String tipo_objeto) {

        boolean msg = false;
        String tipo_obj = "dn_t_" + tipo_objeto;

        switch (tipo_obj) {
            case "dn_t_entidade":
                if (entidadeService.existsByIdAndEstado(id_objeto, status.getAtivo())) {
                    msg = true;
                }
                break;
            case "dn_t_denunciante":
                if (denuncianteService.existsByIdAndEstado(id_objeto, status.getAtivo())) {
                    msg = true;
                }
                break;
            case "dn_t_utilizador_backoffice":
                if (utilizadorService.existsByIdAndEstado(id_objeto, status.getAtivo())) {
                    msg = true;
                }
                break;
            default:
                System.out.println("tipo_objeto não está mapeado");
        }
        return msg;
    }
}
