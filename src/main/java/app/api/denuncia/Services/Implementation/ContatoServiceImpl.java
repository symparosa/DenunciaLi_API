package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Domain;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Models.ContatoModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.ContatoService;
import app.api.denuncia.Repositories.ContatoRepository;
import app.api.denuncia.Repositories.DenuncianteRepository;
import app.api.denuncia.Repositories.EntidadeRepository;
import app.api.denuncia.Repositories.UtilizadorRepository;

@Service
public class ContatoServiceImpl implements ContatoService {

    private ContatoRepository contatoRepository;
    private DominioServiceImpl domServiceImpl;
    private EntidadeRepository entidadeRepository;
    private DenuncianteRepository denuncianteRepository;
    private UtilizadorRepository utilizadorBackofficeRepository;

    private Domain dom = new Domain();
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public ContatoServiceImpl(ContatoRepository contatoRepository, DominioServiceImpl domServiceImpl,
            EntidadeRepository entidadeRepository, DenuncianteRepository denuncianteRepository,
            UtilizadorRepository utilizadorBackofficeRepository) {
        this.contatoRepository = contatoRepository;
        this.domServiceImpl = domServiceImpl;
        this.entidadeRepository = entidadeRepository;
        this.denuncianteRepository = denuncianteRepository;
        this.utilizadorBackofficeRepository = utilizadorBackofficeRepository;
    }

    @Override
    public ResponseModel adicionar_atualizar(List<ContatoModel> contatoModels) {

        gf.clearList(msg);

        try {
            if (contatoModels.size() > 0) {

                if (!validateDuplicateData(contatoModels)) {

                    String metodo = "salvar", obj = "Tipo de contato";

                    for (ContatoModel contato : contatoModels) {

                        if (domServiceImpl.existsTipo(contato.getTipoContato(), dom.getTipoContato())) {

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
                                    contato.setLast_user_change(gf.getId_user_logado());
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
    public ResponseModel alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            String obj = "Contato";

            if (contatoRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = contatoRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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
    public ResponseModel getInfoByIdObjeto(int id_obj, String tipo_obj) {

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
                if (entidadeRepository.existsByIdAndEstado(id_objeto, status.getAtivo())) {
                    msg = true;
                }
                break;
            case "dn_t_denunciante":
                if (denuncianteRepository.existsByIdAndEstado(id_objeto, status.getAtivo())) {
                    msg = true;
                }
                break;
            case "dn_t_utilizador_backoffice":
                if (utilizadorBackofficeRepository.existsByIdAndEstado(id_objeto, status.getAtivo())) {
                    msg = true;
                }
                break;
            default:
                System.out.println("tipo_objeto não está mapeado");
        }
        return msg;
    }
}
