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
import app.api.denuncia.Models.EntidadeModel;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.EntidadeRepository;
import app.api.denuncia.Repositories.LocalizacaoRepository;
import app.api.denuncia.Services.EntidadeService;

@Service
public class EntidadeServiceImpl implements EntidadeService {

    private EntidadeRepository entidadeRepository;
    private LocalizacaoRepository localizacaoRepository;
    private DominioRepository dominioRepository;

    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();
    private Domain dom = new Domain();
    
    public EntidadeServiceImpl(EntidadeRepository entidadeRepository, LocalizacaoRepository localizacaoRepository,
            DominioRepository dominioRepository) {
        this.entidadeRepository = entidadeRepository;
        this.localizacaoRepository = localizacaoRepository;
        this.dominioRepository = dominioRepository;
    }

    @Override
    public ResponseDto adicionar_atualizar(EntidadeModel entidade) {
        try {

            String metodo = "salvar";

            entidade.setEstado(status.getAtivo());
            entidade.setData_criacao(new Date());
            entidade.setLast_user_change(gf.getId_user_logado());

            if (entidade.getLocalizacao() != null
                    && localizacaoRepository.existsById(entidade.getLocalizacao().getId())
                    && entidade.getTipo_entidade() != null
                            && dominioRepository.existsByIdAndDominio(entidade.getTipo_entidade().getId(), dom.getDomainTipoEntidade())) {

                if (entidade.getId() != null) { // update

                    if (entidadeRepository.existsById(entidade.getId())) {

                        if (!entidadeRepository.existsByNomeAndIdNot(entidade.getNome(), entidade.getId())) {
                            entidade.setData_atualizacao(new Date());
                            EntidadeModel e = entidadeRepository.save(entidade);
                            return gf.validateGetSaveMsgWithObj(metodo, e);
                        } else {
                            return gf.getResponse(0, ResponseType.Erro, msg.getMessage03(), null);
                        }
                    } else {
                        return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                    }
                } else { // insert

                    if (!entidadeRepository.existsByNome(entidade.getNome())) {
                        entidade.setData_atualizacao(null);
                        EntidadeModel e = entidadeRepository.save(entidade);
                        return gf.validateGetSaveMsgWithObj(metodo, e);
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

            if (entidadeRepository.existsById(id)) {

                if (estado == status.getAtivo() || estado == status.getInativo() || estado == status.getEliminado()) {

                    String metodo = "salvar";

                    Integer result = entidadeRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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

            if (entidadeRepository.count() > 0) {

                String metodo = "listar";

                List<EntidadeModel> lista = entidadeRepository.findByEstadoIn(gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, lista);

            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }
}
