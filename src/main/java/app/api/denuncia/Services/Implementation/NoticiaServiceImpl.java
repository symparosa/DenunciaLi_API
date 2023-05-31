package app.api.denuncia.Services.Implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.NoticiaModel;
import app.api.denuncia.Repositories.NoticiaRepository;
import app.api.denuncia.Services.NoticiaService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class NoticiaServiceImpl implements NoticiaService {

    private NoticiaRepository noticiaRepository;
    private AuthenticationService auth;

    private String obj = "Noticia";
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public NoticiaServiceImpl(NoticiaRepository noticiaRepository, AuthenticationService auth) {
        this.noticiaRepository = noticiaRepository;
        this.auth = auth;
    }

    // public int IdUserLogado(){
    // return auth.getUtiLogado().getId();
    // }

    public int IdUserLogado() {
        return 1;
    }

    @Override
    public Response listar() {

        gf.clearList(msg);

        try {

            if (noticiaRepository.count() > 0) {

                String metodo = "listar";

                List<NoticiaModel> lista = noticiaRepository.findByEstadoIn(gf.getStatusAtivoInativo());
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

    @Override
    public Response get_by_id(int id) {

        gf.clearList(msg);

        try {

            if (noticiaRepository.count() > 0) {

                String metodo = "listar";

                NoticiaModel Noticia = noticiaRepository.findById(id).orElse(null);
                return gf.validateGetMsgWithObj(metodo, Noticia, obj);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
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

            if (noticiaRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = noticiaRepository.alterarEstado(estado, IdUserLogado(), id);
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
    public Response adicionar_atualizar(NoticiaModel noticia) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";

            noticia.setEstado(status.getAtivo());
            noticia.setData_criacao(LocalDateTime.now());
            noticia.setLast_user_change(IdUserLogado());

            if (noticia.getId() != null) {

                return update(noticia, metodo);

            } else {

                return insert(noticia, metodo);

            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public Response insert(NoticiaModel noticia, String metodo) {

        if (!noticiaRepository.existsByTitulo(noticia.getTitulo())) {

            noticia.setData_atualizacao(null);
            NoticiaModel no = noticiaRepository.save(noticia);
            return gf.validateGetSaveMsgWithObj(metodo, no);

        } else {
            msg.add(message.getMessage03());
            return gf.getResponseError(msg);
        }
    }

    public Response update(NoticiaModel noticia, String metodo) {

        if (noticiaRepository.existsById(noticia.getId())) {

            if (!noticiaRepository.existsByTituloAndIdNot(noticia.getTitulo(), noticia.getId())) {

                noticia.setData_atualizacao(LocalDateTime.now());
                NoticiaModel no = noticiaRepository.save(noticia);
                return gf.validateGetSaveMsgWithObj(metodo, no);

            } else {
                msg.add(message.getMessage03());
                return gf.getResponseError(msg);
            }
        } else {
            msg.add(message.getMessage06(obj));
            return gf.getResponseError(msg);
        }
    }
}
