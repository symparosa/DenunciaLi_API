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
import app.api.denuncia.Models.BotaoModel;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Models.TransacaoModel;
import app.api.denuncia.Repositories.BotaoRepository;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.TransacaoRepository;
import app.api.denuncia.Services.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private TransacaoRepository transacaoRepository;
    private DominioRepository dominioRepository;
    private BotaoRepository botaoRepository;

    private Domain dom = new Domain();
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository, DominioRepository dominioRepository,
            BotaoRepository botaoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.dominioRepository = dominioRepository;
        this.botaoRepository = botaoRepository;
    }

    @Override
    public ResponseModel alterarPermissao(int id_botao, int id_perfil, int estado) {

        gf.clearList(msg);

        try {

            if (estado == status.getAtivo() || estado == status.getInativo()) {

                String metodo = "salvar", obj = "Tipo de utilizador";

                DominioModel perfil = dominioRepository.findByIdAndDominio(id_perfil, dom.getTipoUser());

                Optional<BotaoModel> botao = botaoRepository.findById(id_botao);

                if (perfil != null) {

                    obj = "Botão";

                    if (botao.isPresent()) {

                        if (transacaoRepository.existsByBotaoAndTipoUtilizador(botao.get(), perfil)) {

                            Integer result = transacaoRepository.alterarEstado(estado, gf.getId_user_logado(), id_botao,
                                    id_perfil);
                            return gf.validateGetUpdateMsg(metodo, result);

                        } else {

                            TransacaoModel transacaoModel = new TransacaoModel();
                            transacaoModel.setBotao(botao.get());
                            transacaoModel.setTipoUtilizador(perfil);
                            transacaoModel.setEstado(estado);
                            transacaoModel.setData_criacao(new Date());
                            transacaoModel.setLast_user_change(gf.getId_user_logado());

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
