package app.api.denuncia.Services.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.BotaoModel;
import app.api.denuncia.Repositories.BotaoRepository;
import app.api.denuncia.Services.BotaoService;

@Service
public class BotaoServiceImpl implements BotaoService {
    
    private BotaoRepository botaoRepository;
    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();

    public BotaoServiceImpl(BotaoRepository botaoRepository) {
        this.botaoRepository = botaoRepository;
    }

    @Override
    public ResponseDto adicionar_atualizar(BotaoModel botao) {
        try {

            String metodo = "salvar";

            botao.setEstado(status.getAtivo());
            botao.setData_criacao(new Date());
            botao.setLast_user_change(gf.getId_user_logado());

            if (botao.getId() != null) { // update

                if (botaoRepository.existsById(botao.getId())) {

                    if (!botaoRepository.existsByCodigoAndIdNot(botao.getCodigo(), botao.getId())) {
                        botao.setData_atualizacao(new Date());
                        BotaoModel bo = botaoRepository.save(botao);
                        return gf.validateGetSaveMsgWithObj(metodo, bo);
                    } else {
                        return gf.getResponse(0, ResponseType.Erro, msg.getMessage03(), null);
                    }
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                }
            } else { // insert

                if (!botaoRepository.existsByCodigo(botao.getCodigo())) {
                    botao.setData_atualizacao(null);
                    BotaoModel bo = botaoRepository.save(botao);
                    return gf.validateGetSaveMsgWithObj(metodo, bo);
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage03(), null);
                }
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

    @Override
    public ResponseDto alterarEstado(int id, int estado) {
        try {

            if (botaoRepository.existsById(id)) {

                if (estado == status.getAtivo() || estado == status.getInativo() || estado == status.getEliminado()) {

                    String metodo = "salvar";

                    Integer result = botaoRepository.alterarEstado(estado,gf.getId_user_logado(), id);
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
    public ResponseDto listar() {
        try {

            if (botaoRepository.count() > 0) {

                String metodo = "listar";

                List<BotaoModel> lista = botaoRepository.findByEstadoIn(gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, lista);

            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }
}
