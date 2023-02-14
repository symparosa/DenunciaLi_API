package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Domain;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.InformacaoLegalModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.InformacaoLegalRepository;
import app.api.denuncia.Services.InformacaoLegalService;

@Service
public class InformacaoLegalServiceImpl implements InformacaoLegalService {

    private InformacaoLegalRepository infoRepository;
    private DominioServiceImpl domServiceImpl;
    private DominioRepository domRepository;

    private Domain dom = new Domain();
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public InformacaoLegalServiceImpl(InformacaoLegalRepository infoRepository, DominioServiceImpl domServiceImpl,
            DominioRepository domRepository) {
        this.infoRepository = infoRepository;
        this.domServiceImpl = domServiceImpl;
        this.domRepository = domRepository;
    }

    @Override
    public ResponseModel adicionar_atualizar(InformacaoLegalModel infoLegal) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";

            infoLegal.setEstado(status.getAtivo());
            infoLegal.setData_criacao(new Date());
            infoLegal.setLast_user_change(gf.getId_user_logado());

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
    public ResponseModel alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            String obj = "Informação Legal";

            if (infoRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = infoRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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

    public ResponseModel insert(InformacaoLegalModel info, String metodo) {

        info.setData_atualizacao(null);
        InformacaoLegalModel inf = infoRepository.save(info);
        return gf.validateGetSaveMsgWithObj(metodo, inf);

    }

    public ResponseModel update(InformacaoLegalModel info, String metodo) {

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
    public ResponseModel getInfoByTipo(String tipo) {

        gf.clearList(msg);

        try {

            if (tipo.equals(dom.getTipoFAQ()) || tipo.equals(dom.getTipoPolitica())
                    || tipo.equals(dom.getTipoTermo())) {

                DominioModel dominioModel = domRepository.findByDominio(tipo);

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

        if (domServiceImpl.existsTipo(tipoInfo, dom.getTipoFAQ())
                || domServiceImpl.existsTipo(tipoInfo, dom.getTipoPolitica())
                || domServiceImpl.existsTipo(tipoInfo, dom.getTipoTermo())) {
            msg = true;
        }

        return msg;
    }
}
