package app.api.denuncia.Services.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Domain;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.UtilizadorBackofficeModel;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.EntidadeRepository;
import app.api.denuncia.Repositories.LocalizacaoRepository;
import app.api.denuncia.Repositories.UtilizadorBackofficeRepository;
import app.api.denuncia.Services.UtilizadorBackofficeService;

@Service
public class UtilizadorBackofficeServiceImpl implements UtilizadorBackofficeService {

    private UtilizadorBackofficeRepository utilizadorBackofficeRepository;
    private DominioRepository dominioRepository;
    private EntidadeRepository entidadeRepository;
    private LocalizacaoRepository localizacaoRepository;

    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();
    private Domain dom = new Domain();

    public UtilizadorBackofficeServiceImpl(UtilizadorBackofficeRepository utilizadorBackofficeRepository,
            DominioRepository dominioRepository, EntidadeRepository entidadeRepository,
            LocalizacaoRepository localizacaoRepository) {
        this.utilizadorBackofficeRepository = utilizadorBackofficeRepository;
        this.dominioRepository = dominioRepository;
        this.entidadeRepository = entidadeRepository;
        this.localizacaoRepository = localizacaoRepository;
    }

    @Override
    public ResponseDto adicionar_atualizar(UtilizadorBackofficeModel utilizador) {
        try {

            String metodo = "salvar";

            utilizador.setEstado(status.getAtivo());
            utilizador.setData_criacao(new Date());
            utilizador.setLast_user_change(gf.getId_user_logado());

            if (utilizador.getLocalizacao() != null
                    && localizacaoRepository.existsById(utilizador.getLocalizacao().getId())
                    && utilizador.getTipo_utilizador() != null
                    && dominioRepository.existsByIdAndDominio(utilizador.getTipo_utilizador().getId(), dom.getDomainTipoUtilizador())
                    && utilizador.getEntidade() != null
                    && entidadeRepository.existsById(utilizador.getEntidade().getId())) {

                if (utilizador.getId() != null) { // update

                    if (utilizadorBackofficeRepository.existsById(utilizador.getId())) {

                        if (!utilizadorBackofficeRepository.existsByUsernameAndIdNot(utilizador.getUsername(), utilizador.getId())) {
                            utilizador.setData_atualizacao(new Date());
                            UtilizadorBackofficeModel user = utilizadorBackofficeRepository.save(utilizador);
                            return gf.validateGetSaveMsgWithObj(metodo, user);
                        } else {
                            return gf.getResponse(0, ResponseType.Erro, msg.getMessage03(), null);
                        }
                    } else {
                        return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                    }
                } else { // insert

                    if (!utilizadorBackofficeRepository.existsByUsername(utilizador.getUsername())) {
                        utilizador.setData_atualizacao(null);
                        UtilizadorBackofficeModel user = utilizadorBackofficeRepository.save(utilizador);
                        return gf.validateGetSaveMsgWithObj(metodo, user);
                    } else {
                        return gf.getResponse(0, ResponseType.Erro, msg.getMessage03(), null);
                    }
                }
            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

    @Override
    public ResponseDto alterarEstado(int id, int estado) {
        try {

            if (utilizadorBackofficeRepository.existsById(id)) {

                if (estado == status.getAtivo() || estado == status.getInativo() || estado == status.getEliminado()) {

                    String metodo = "salvar";

                    Integer result = utilizadorBackofficeRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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

            if (utilizadorBackofficeRepository.count() > 0) {

                String metodo = "listar";

                List<UtilizadorBackofficeModel> lista = utilizadorBackofficeRepository.findByEstadoIn(gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, lista);

            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

}
