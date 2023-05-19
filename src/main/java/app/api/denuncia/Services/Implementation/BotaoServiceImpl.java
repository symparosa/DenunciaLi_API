package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.BotaoDto;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.BotaoModel;
import app.api.denuncia.Repositories.BotaoRepository;
import app.api.denuncia.Services.BotaoService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class BotaoServiceImpl implements BotaoService {

    private BotaoRepository botaoRepository;
    private AuthenticationService auth;

    private String obj = "Botão";
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public BotaoServiceImpl(BotaoRepository botaoRepository, AuthenticationService auth) {
        this.botaoRepository = botaoRepository;
        this.auth = auth;
    }

    public int IdUserLogado() {
        return auth.getUtiLogado().getId();
    }

    @Override
    public Response adicionar_atualizar(BotaoModel botao) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";

            botao.setEstado(status.getAtivo());
            botao.setData_criacao(new Date());
            botao.setLast_user_change(IdUserLogado());

            if (botao.getId() != null) {

                return update(botao, metodo);

            } else {

                return insert(botao, metodo);

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

            if (botaoRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = botaoRepository.alterarEstado(estado, IdUserLogado(), id);
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
            if (botaoRepository.count() > 0) {

                String metodo = "listar";

                List<BotaoDto> lista = botaoRepository.listarBotaoEPerfilAssociado(gf.getStatusAtivoInativo());
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

    public Response insert(BotaoModel botao, String metodo) {

        if (!botaoRepository.existsByCodigo(botao.getCodigo())) {

            botao.setData_atualizacao(null);
            BotaoModel bo = botaoRepository.save(botao);
            return gf.validateGetSaveMsgWithObj(metodo, bo);

        } else {
            msg.add(message.getMessage03());
            return gf.getResponseError(msg);
        }
    }

    public Response update(BotaoModel botao, String metodo) {

        if (botaoRepository.existsById(botao.getId())) {

            if (!botaoRepository.existsByCodigoAndIdNot(botao.getCodigo(), botao.getId())) {

                botao.setData_atualizacao(new Date());
                BotaoModel bo = botaoRepository.save(botao);
                return gf.validateGetSaveMsgWithObj(metodo, bo);

            } else {
                msg.add(message.getMessage03());
                return gf.getResponseError(msg);
            }
        } else {
            msg.add(message.getMessage06(obj));
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response get_by_id(int id) {

        gf.clearList(msg);

        try {

            if (botaoRepository.count() > 0) {

                String metodo = "listar";

                BotaoModel bo = botaoRepository.findById(id).orElse(null);
                return gf.validateGetMsgWithObj(metodo, bo, obj);

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
