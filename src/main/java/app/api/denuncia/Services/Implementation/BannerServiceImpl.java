package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Models.BannerModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Repositories.BannerRepository;
import app.api.denuncia.Services.BannerService;

@Service
public class BannerServiceImpl implements BannerService {

    private BannerRepository bannerRepository;
    
    private String obj = "Banner";
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public ResponseModel adicionar_atualizar(BannerModel banner) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";

            banner.setEstado(status.getAtivo());
            banner.setData_criacao(new Date());
            banner.setLast_user_change(gf.getUser().getUserLogado().getId());

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

    @Override
    public ResponseModel alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            if (bannerRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = bannerRepository.alterarEstado(estado, gf.getUser().getUserLogado().getId(), id);
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
    public ResponseModel listar() {

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

    public ResponseModel insert(BannerModel banner, String metodo) {

        banner.setData_atualizacao(null);
        BannerModel ba = bannerRepository.save(banner);
        return gf.validateGetSaveMsgWithObj(metodo, ba);

    }

    public ResponseModel update(BannerModel banner, String metodo) {

        gf.clearList(msg);

        if (bannerRepository.existsById(banner.getId())) {

            banner.setData_atualizacao(new Date());
            BannerModel ba = bannerRepository.save(banner);
            return gf.validateGetSaveMsgWithObj(metodo, ba);

        } else {
            msg.add(message.getMessage06(obj));
            return gf.getResponseError(msg);
        }
    }
}
