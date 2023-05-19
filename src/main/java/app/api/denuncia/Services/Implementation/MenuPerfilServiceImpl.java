package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.Domain;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.MenuModel;
import app.api.denuncia.Models.MenuPerfilModel;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.MenuPerfilRepository;
import app.api.denuncia.Repositories.MenuRepository;
import app.api.denuncia.Services.MenuPerfilService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class MenuPerfilServiceImpl implements MenuPerfilService {

    private MenuPerfilRepository menuPerfilRepository;
    private DominioRepository dominioRepository;
    private MenuRepository menuRepository;
    private AuthenticationService auth;

    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public MenuPerfilServiceImpl(MenuPerfilRepository menuPerfilRepository, DominioRepository dominioRepository,
            MenuRepository menuRepository, AuthenticationService auth) {
        this.menuPerfilRepository = menuPerfilRepository;
        this.dominioRepository = dominioRepository;
        this.menuRepository = menuRepository;
        this.auth = auth;
    }

    public int IdUserLogado() {
        return auth.getUtiLogado().getId();
    }

    @Override
    public Response alterarPermissao(int id_menu, int id_perfil, int estado) {

        gf.clearList(msg);

        try {

            if (estado == status.getAtivo() || estado == status.getInativo()) {

                String metodo = "salvar", obj = "tipo_utilizador";

                DominioModel perfil = dominioRepository.findByIdAndDominio(id_perfil,Domain.TIPO_UTILIZADOR.name());

                Optional<MenuModel> menu = menuRepository.findById(id_menu);

                if (perfil != null) {

                    obj = "menu";

                    if (menu.isPresent()) {

                        if (menuPerfilRepository.existsByMenuAndTipoUtilizador(menu.get(), perfil)) {

                            Integer result = menuPerfilRepository.alterarEstado(estado, IdUserLogado(), id_menu,
                                    id_perfil);
                            return gf.validateGetUpdateMsg(metodo, result);

                        } else {

                            MenuPerfilModel menuPerfilModel = new MenuPerfilModel();
                            menuPerfilModel.setMenu(menu.get());
                            menuPerfilModel.setTipoUtilizador(perfil);
                            menuPerfilModel.setEstado(estado);
                            menuPerfilModel.setData_criacao(new Date());
                            menuPerfilModel.setLast_user_change(IdUserLogado());

                            MenuPerfilModel mp = menuPerfilRepository.save(menuPerfilModel);
                            return gf.validateGetSaveMsgWithObj(metodo, mp);
                        }
                    } else {
                        msg.add(message.getMessage06(obj));
                        return gf.getResponseError(msg);
                    }
                } else {
                    msg.add(message.getMessage06(obj));
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage07());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }
}
