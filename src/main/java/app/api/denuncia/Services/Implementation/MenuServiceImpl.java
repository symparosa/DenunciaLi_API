package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.MenuDto;
import app.api.denuncia.Models.MenuModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Repositories.MenuRepository;
import app.api.denuncia.Services.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    private AuthenticationService auth;

    private String obj = "menu";
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public MenuServiceImpl(MenuRepository menuRepository, AuthenticationService auth) {
        this.menuRepository = menuRepository;
        this.auth = auth;
    }

    @Override
    public ResponseModel adicionar_atualizar(MenuModel menu) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";

            menu.setEstado(status.getAtivo());
            menu.setData_criacao(new Date());
            menu.setLast_user_change(auth.getUserLogado().getId());

            if (menu.getId() != null) {

                return update(menu, metodo);

            } else {

                return insert(menu, metodo);
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

            if (menuRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = menuRepository.alterarEstado(estado, auth.getUserLogado().getId(), id);
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

            if (menuRepository.count() > 0) {

                String metodo = "listar";

                List<MenuDto> lista = menuRepository.listarMenuEPerfilAssociado(gf.getStatusAtivoInativo());
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

    public ResponseModel insert(MenuModel menu, String metodo) {

        if (!menuRepository.existsByCodigo(menu.getCodigo())) {

            menu.setData_atualizacao(null);
            MenuModel men = menuRepository.save(menu);
            return gf.validateGetSaveMsgWithObj(metodo, men);

        } else {
            msg.add(message.getMessage03());
            return gf.getResponseError(msg);
        }

    }

    public ResponseModel update(MenuModel menu, String metodo) {

        if (menuRepository.existsById(menu.getId())) {

            if (!menuRepository.existsByCodigoAndIdNot(menu.getCodigo(), menu.getId())) {

                menu.setData_atualizacao(new Date());
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
}
