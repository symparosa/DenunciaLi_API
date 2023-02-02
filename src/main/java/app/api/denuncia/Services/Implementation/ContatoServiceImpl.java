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
import app.api.denuncia.Models.ContatoModel;
import app.api.denuncia.Services.ContatoService;
import app.api.denuncia.Repositories.ContatoRepository;
import app.api.denuncia.Repositories.DenuncianteRepository;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.EntidadeRepository;
import app.api.denuncia.Repositories.UtilizadorBackofficeRepository;

@Service
public class ContatoServiceImpl implements ContatoService {

    private ContatoRepository contatoRepository;
    private DominioRepository dominioRepository;
    private EntidadeRepository entidadeRepository;
    private DenuncianteRepository denuncianteRepository;
    private UtilizadorBackofficeRepository utilizadorBackofficeRepository;
    

    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();
    private Domain dom =  new Domain();

    public ContatoServiceImpl(ContatoRepository contatoRepository, DominioRepository dominioRepository,
            EntidadeRepository entidadeRepository, DenuncianteRepository denuncianteRepository) {
        this.contatoRepository = contatoRepository;
        this.dominioRepository = dominioRepository;
        this.entidadeRepository = entidadeRepository;
        this.denuncianteRepository = denuncianteRepository;
    }

    @Override
    public ResponseDto adicionar_atualizar(List<ContatoModel> contatoModels) {
        try {

            if (contatoModels.size() > 0) {

                if (!validateDuplicateData(contatoModels)) {

                    String metodo = "salvar";
                    int contUpdate = 0, contInsert = 0;
                    boolean update = false, insert = false;

                    for (ContatoModel contato : contatoModels) {

                        if (contato.getTipo_contato() != null
                                && dominioRepository.existsByIdAndDominio(contato.getTipo_contato().getId(), dom.getDomainTipoContato())
                                && contato.getIdObjeto() != null
                                && validateIdObjeto(contato.getIdObjeto(), contato.getTipoObjeto())) {

                            if (contato.getId() != null) { // update

                                if (contatoRepository.existsById(contato.getId())) {
                                    update = true;
                                    if (contatoRepository.existsByValorAndIdNot(contato.getValor(), contato.getId())) {
                                        contUpdate++;
                                    }
                                    contato.setData_atualizacao(new Date());
                                } else {
                                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                                }
                            } else if (contato.getId() == null) { // insert
                                insert = true;
                                if (contatoRepository.existsByValor(contato.getValor())) {
                                    contInsert++;
                                }
                                contato.setData_atualizacao(null);
                            }
                            contato.setEstado(status.getAtivo());
                            contato.setData_criacao(new Date());
                            contato.setLast_user_change(gf.getId_user_logado());
                        } else {
                            return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                        }
                    }

                    if ((contUpdate == 0 && insert == false) || (contInsert == 0 && update == false)) {

                        List<ContatoModel> cont = contatoRepository.saveAll(contatoModels);
                        return gf.validateGetSaveMsgWithList(metodo, cont);

                    } else {
                        return gf.getResponse(0, ResponseType.Erro, msg.getMessage03(), null);
                    }
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage08(), null);
                }
            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

    @Override
    public ResponseDto alterarEstado(int id, int estado) {
        try {
            if (contatoRepository.existsById(id)) {

                if (estado == status.getAtivo() || estado == status.getInativo() || estado == status.getEliminado()) {

                    String metodo = "salvar";

                    Integer result = contatoRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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
    public ResponseDto getInfoByIdObjeto(int id) {
        try {

            if (contatoRepository.count() > 0) {

                if (contatoRepository.existsByIdObjeto(id)) {

                    String metodo = "listar";

                    List<ContatoModel> lista = contatoRepository.findByIdObjetoAndEstadoIn(id, gf.getStatusAtivoInativo());
                    return gf.validateGetListMsg(metodo, lista);

                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                }
            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
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

        switch (tipo_objeto) {
            case "dn_t_entidade":
                if (entidadeRepository.existsById(id_objeto)) {
                    msg = true;
                }
                break;
            case "dn_t_denunciante":
                if (denuncianteRepository.existsById(id_objeto)) {
                    msg = true;
                }
                break;
            case "dn_t_utilizador_backoffice":
                if (utilizadorBackofficeRepository.existsById(id_objeto)) {
                    msg = true;
                }
                break;
            default:
                System.out.println("tipo_objeto não está mapeado");
        }
        return msg;
    }
}
