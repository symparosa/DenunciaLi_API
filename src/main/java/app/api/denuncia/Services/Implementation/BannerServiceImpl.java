package app.api.denuncia.Services.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.BannerModel;
import app.api.denuncia.Repositories.BannerRepository;
import app.api.denuncia.Services.BannerService;

@Service
public class BannerServiceImpl implements BannerService {

    private BannerRepository bannerRepository;
    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();

    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public ResponseDto adicionar_atualizar(BannerModel banner) {
        try {

            String metodo = "salvar";

            banner.setEstado(status.getAtivo());
            banner.setData_criacao(new Date());
            banner.setLast_user_change(gf.getId_user_logado());

            if (banner.getId() != null) { // update
                if (bannerRepository.existsById(banner.getId())) {
                    banner.setData_atualizacao(new Date());
                    BannerModel ba = bannerRepository.save(banner);
                    return gf.validateGetSaveMsgWithObj(metodo, ba);
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                }
            } else { // insert
                banner.setData_atualizacao(null);
                BannerModel ba = bannerRepository.save(banner);
                return gf.validateGetSaveMsgWithObj(metodo, ba);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

    @Override
    public ResponseDto alterarEstado(int id, int estado) {
        try {

            if (bannerRepository.existsById(id)) {

                if (estado == status.getAtivo() || estado == status.getInativo() || estado == status.getEliminado()) {

                    String metodo = "salvar";

                    Integer result = bannerRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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

            if (bannerRepository.count() > 0) {

                String metodo = "listar";

                List<BannerModel> lista = bannerRepository.findByEstadoIn(gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, lista);

            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

}
