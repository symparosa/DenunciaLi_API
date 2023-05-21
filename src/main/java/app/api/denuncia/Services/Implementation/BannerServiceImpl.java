package app.api.denuncia.Services.Implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.BannerModel;
import app.api.denuncia.Repositories.BannerRepository;
import app.api.denuncia.Services.BannerService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class BannerServiceImpl implements BannerService {

    private BannerRepository bannerRepository;
    private AuthenticationService auth;

    private String obj = "Banner";
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public BannerServiceImpl(BannerRepository bannerRepository, AuthenticationService auth) {
        this.bannerRepository = bannerRepository;
        this.auth = auth;
    }

    // public int IdUserLogado(){
    // return auth.getUtiLogado().getId();
    // }

    public int IdUserLogado() {
        return 1;
    }

    @Override
    public Response adicionar_atualizar(BannerModel banner) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";

            banner.setEstado(status.getAtivo());
            banner.setData_criacao(LocalDateTime.now());
            banner.setLast_user_change(IdUserLogado());

            if (banner.getId() != null) {

                return update(banner, metodo);

            } else {

                return insert(banner, metodo);

            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public Response insert(BannerModel banner, String metodo) {

        banner.setData_atualizacao(null);
        BannerModel ba = bannerRepository.save(banner);
        return gf.validateGetSaveMsgWithObj(metodo, ba);

    }

    public Response update(BannerModel banner, String metodo) {

        gf.clearList(msg);

        if (bannerRepository.existsById(banner.getId())) {

            banner.setData_atualizacao(LocalDateTime.now());
            BannerModel ba = bannerRepository.save(banner);
            return gf.validateGetSaveMsgWithObj(metodo, ba);

        } else {
            msg.add(message.getMessage06(obj));
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            if (bannerRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = bannerRepository.alterarEstado(estado, IdUserLogado(), id);
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

            if (bannerRepository.count() > 0) {

                String metodo = "listar";

                List<BannerModel> lista = bannerRepository.findByEstadoIn(gf.getStatusAtivoInativo());
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

            if (bannerRepository.count() > 0) {

                String metodo = "listar";

                BannerModel Banner = bannerRepository.findById(id).orElse(null);
                return gf.validateGetMsgWithObj(metodo, Banner, obj);

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
