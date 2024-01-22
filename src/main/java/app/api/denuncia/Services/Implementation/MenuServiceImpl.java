package app.api.denuncia.Services.Implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Menu;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.MenuModel;
import app.api.denuncia.Repositories.MenuRepository;
import app.api.denuncia.Services.MenuService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    private AuthenticationService auth;

    private String obj = "Menu";
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public MenuServiceImpl(MenuRepository menuRepository, AuthenticationService auth) {
        this.menuRepository = menuRepository;
        this.auth = auth;
    }

    public int IdUserLogado() {
        return auth.getUtiLogado().getId();
    }

    @Override
    public Response adicionar_atualizar(MenuModel menu) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";

            menu.setLast_user_change(IdUserLogado());
            
            if (menu.getId() != null) {

                return update(menu, metodo);

            } else {
                menu.setEstado(status.getAtivo());
                menu.setData_criacao(LocalDateTime.now());
               
                return insert(menu, metodo);
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

            if (menuRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = menuRepository.alterarEstado(estado, IdUserLogado(), id);
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

            if (menuRepository.count() > 0) {

                String metodo = "listar";

                List<Object[]> results = menuRepository.listarMenuEPerfilAssociado(gf.getStatusAtivoInativo());
                List<Menu> menus = new ArrayList<>();

                for (Object[] result : results) {
                    Menu menu = new Menu();
                    menu.setId_menu((Integer) result[0]);
                    menu.setCodigo((String) result[1]);
                    menu.setEstado_menu((Integer) result[2]);
                    menu.setId_menu_pai((Integer) result[3]);
                    menu.setMenu_icon((String) result[4]);
                    menu.setVisibilidade((Integer) result[5]);
                    menu.setTitulo((String) result[6]);
                    menu.setEstado_menu_perfil((Integer) result[7]);
                    menu.setPerfil((Integer) result[8]);

                    menus.add(menu);
                }

                return gf.validateGetListMsg(metodo, menus);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public Response insert(MenuModel menu, String metodo) {

        if (!menuRepository.existsByCodigo(menu.getCodigo())) {

            menu.setData_atualizacao(null);
            MenuModel men = menuRepository.save(menu);
            return gf.validateGetSaveMsgWithObj(metodo, men);

        } else {
            msg.add(message.getMessage03());
            return gf.getResponseError(msg);
        }

    }

    public Response update(MenuModel menu, String metodo) {

        if (menuRepository.existsById(menu.getId())) {

            if (!menuRepository.existsByCodigoAndIdNot(menu.getCodigo(), menu.getId())) {

                menu.setData_atualizacao(LocalDateTime.now());
                MenuModel men = menuRepository.save(menu);
                return gf.validateGetSaveMsgWithObj(metodo, men);

            } else {
                msg.add(message.getMessage03());
                return gf.getResponseError(msg);
            }
        } else {
            msg.add(message.getMessage06(obj));
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response get_by_id(int id) {

        gf.clearList(msg);

        try {

            if (menuRepository.count() > 0) {

                String metodo = "listar";

                MenuModel menu = menuRepository.findById(id).orElse(null);
                return gf.validateGetMsgWithObj(metodo, menu, obj);

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
