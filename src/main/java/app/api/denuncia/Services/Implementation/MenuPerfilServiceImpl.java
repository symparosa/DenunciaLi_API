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
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.MenuModel;
import app.api.denuncia.Models.MenuPerfilModel;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.MenuPerfilRepository;
import app.api.denuncia.Repositories.MenuRepository;
import app.api.denuncia.Services.MenuPerfilService;

@Service
public class MenuPerfilServiceImpl implements MenuPerfilService {

    private MenuPerfilRepository menuPerfilRepository;
    private DominioRepository dominioRepository;
    private MenuRepository menuRepository;

    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();
    private Domain dom =  new Domain();

    public MenuPerfilServiceImpl(MenuPerfilRepository menuPerfilRepository, DominioRepository dominioRepository,
            MenuRepository menuRepository) {
        this.menuPerfilRepository = menuPerfilRepository;
        this.dominioRepository = dominioRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public ResponseDto alterarPermissao(int id_menu, int id_perfil, int estado) {
        try {

            if (estado == status.getAtivo() || estado == status.getInativo()) {

                String metodo = "salvar";

                DominioModel perfil = dominioRepository.findByIdAndDominio(id_perfil, dom.getDomainTipoUtilizador());

                Optional<MenuModel> menu = menuRepository.findById(id_menu);

                if (perfil != null && menu.isPresent()) {

                    if (menuPerfilRepository.existsByMenuAndTipoUtilizador(menu.get(), perfil)) {

                        Integer result = menuPerfilRepository.alterarEstado(estado, gf.getId_user_logado(), id_menu,
                                id_perfil);
                        return gf.validateGetUpdateMsg(metodo, result);

                    } else {

                        MenuPerfilModel menuPerfilModel = new MenuPerfilModel();
                        menuPerfilModel.setMenu(menu.get());
                        menuPerfilModel.setTipoUtilizador(perfil);
                        menuPerfilModel.setEstado(estado);
                        menuPerfilModel.setData_criacao(new Date());
                        menuPerfilModel.setLast_user_change(gf.getId_user_logado());

                        MenuPerfilModel mp = menuPerfilRepository.save(menuPerfilModel);
                        return gf.validateGetSaveMsgWithObj(metodo, mp);
                    }
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                }
            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage07(), null);
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }
}
