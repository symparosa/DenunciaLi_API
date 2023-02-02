package app.api.denuncia.Services.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.MenuDto;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.MenuModel;
import app.api.denuncia.Repositories.MenuRepository;
import app.api.denuncia.Services.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    private Status status = new Status();
    private Message msg = new Message();
    private GlobalFunctions gf = new GlobalFunctions();

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public ResponseDto adicionar_atualizar(MenuModel menu) {
        try {

            String metodo = "salvar";

            menu.setEstado(status.getAtivo());
            menu.setData_criacao(new Date());
            menu.setLast_user_change(gf.getId_user_logado());

            if (menu.getId() != null) { // update

                if (menuRepository.existsById(menu.getId())) {

                    if (!menuRepository.existsByCodigoAndIdNot(menu.getCodigo(), menu.getId())) {
                        menu.setData_atualizacao(new Date());
                        MenuModel men = menuRepository.save(menu);
                        return gf.validateGetSaveMsgWithObj(metodo, men);
                    } else {
                        return gf.getResponse(0, ResponseType.Erro, msg.getMessage03(), null);
                    }
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage06(), null);
                }
            } else { // insert

                if (!menuRepository.existsByCodigo(menu.getCodigo())) {
                    menu.setData_atualizacao(null);
                    MenuModel men = menuRepository.save(menu);
                    return gf.validateGetSaveMsgWithObj(metodo, men);
                } else {
                    return gf.getResponse(0, ResponseType.Erro, msg.getMessage03(), null);
                }
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }

    @Override
    public ResponseDto alterarEstado(int id, int estado) {
        try {

            if (menuRepository.existsById(id)) {

                if (estado == status.getAtivo() || estado == status.getInativo() || estado == status.getEliminado()) {

                    String metodo = "salvar";

                    Integer result = menuRepository.alterarEstado(estado,gf.getId_user_logado(), id);
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

            if (menuRepository.count() > 0) {

                String metodo = "listar";

                List<MenuDto> lista = menuRepository.listarMenuEPerfilAssociado(gf.getTodosStatus(), gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, lista);

            } else {
                return gf.getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
            }
        } catch (Exception e) {
            return gf.getResponse(0, ResponseType.Erro, msg.getMessage04(), null);
        }
    }
}
