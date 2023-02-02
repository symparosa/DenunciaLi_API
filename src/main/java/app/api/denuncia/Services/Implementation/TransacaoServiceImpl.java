package app.api.denuncia.Services.Implementation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Domain;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.BotaoModel;
import app.api.denuncia.Models.DominioModel;
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

    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();
    private Domain dom =  new Domain();

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository, DominioRepository dominioRepository,
            BotaoRepository botaoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.dominioRepository = dominioRepository;
        this.botaoRepository = botaoRepository;
    }

    @Override
    public ResponseDto alterarPermissao(int id_botao, int id_perfil, int estado) {
        try {

            if (estado == status.getAtivo() || estado == status.getInativo()) {

                String metodo = "salvar";

                DominioModel perfil = dominioRepository.findByIdAndDominio(id_perfil, dom.getDomainTipoUtilizador());

                Optional<BotaoModel> botao = botaoRepository.findById(id_botao);

                if (perfil != null && botao.isPresent()) {

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
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                }
            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage07(), null);
            }
        } catch (Exception e) {
            System.out.println("--> "+e.getMessage());
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }
}
