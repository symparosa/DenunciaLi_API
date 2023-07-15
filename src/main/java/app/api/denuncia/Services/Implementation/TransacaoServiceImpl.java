package app.api.denuncia.Services.Implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.Domain;
import app.api.denuncia.Models.BotaoModel;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.TransacaoModel;
import app.api.denuncia.Repositories.BotaoRepository;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.TransacaoRepository;
import app.api.denuncia.Services.TransacaoService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private TransacaoRepository transacaoRepository;
    private DominioRepository dominioRepository;
    private BotaoRepository botaoRepository;
    private AuthenticationService auth;

    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository, DominioRepository dominioRepository,
            BotaoRepository botaoRepository, AuthenticationService auth) {
        this.transacaoRepository = transacaoRepository;
        this.dominioRepository = dominioRepository;
        this.botaoRepository = botaoRepository;
        this.auth = auth;
    }

    public int IdUserLogado() {
        return auth.getUtiLogado().getId();
    }

    @Override
    public Response alterarPermissao(int id_botao, int id_perfil, int estado) {

        gf.clearList(msg);

        try {

            if (estado == status.getAtivo() || estado == status.getInativo()) {

                String metodo = "salvar", obj = "Tipo de utilizador";

                DominioModel perfil = dominioRepository.findByIdAndDominio(id_perfil, Domain.TIPO_UTILIZADOR.name());

                Optional<BotaoModel> botao = botaoRepository.findById(id_botao);

                if (perfil != null) {

                    obj = "Botão";

                    if (botao.isPresent()) {

                        if (transacaoRepository.existsByBotaoAndTipoUtilizador(botao.get(), perfil)) {

                            Integer result = transacaoRepository.alterarEstado(estado, IdUserLogado(), id_botao,
                                    id_perfil);
                            return gf.validateGetUpdateMsg(metodo, result);

                        } else {

                            TransacaoModel transacaoModel = new TransacaoModel();
                            transacaoModel.setBotao(botao.get());
                            transacaoModel.setTipoUtilizador(perfil);
                            transacaoModel.setEstado(estado);
                            transacaoModel.setData_criacao(LocalDateTime.now());
                            transacaoModel.setLast_user_change(IdUserLogado());

                            TransacaoModel t = transacaoRepository.save(transacaoModel);
                            return gf.validateGetSaveMsgWithObj(metodo, t);
                        }
                    } else {
                        msg.add(message.getMessage06(obj));
                        return gf.getResponseError(msg);
                    }
                } else {
                    msg.add(message.getMessage06(obj));
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage07());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }
}
