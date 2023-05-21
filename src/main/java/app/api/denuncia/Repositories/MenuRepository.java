package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.MenuModel;

@Repository
@Transactional
public interface MenuRepository extends JpaRepository<MenuModel, Integer> {

    Boolean existsByCodigo(String codigo);

    Boolean existsByCodigoAndIdNot(String codigo, Integer id);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_menu SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado, @Param("user") int user, @Param("id") int id);

    @Query(value = "select distinct m.id as id_menu, m.codigo, m.estado as estado_menu, m.id_menu_pai, m.menu_icon,"
            + "m.visibilidade, m.titulo, mp.estado as estado_menu_perfil, mp.tipo_utilizador_fk as perfil"
            + " from dbo.dn_t_menu m"
            + " left join dbo.dn_t_menu_perfil mp on m.id = mp.menu_fk"
            + " where ( mp.estado in :estadoAtivoInativo or mp.estado is null ) and m.estado in :estadoAtivoInativo and m.visibilidade in :estadoAtivoInativo", nativeQuery = true)
    List<Object[]> listarMenuEPerfilAssociado(@Param("estadoAtivoInativo") List<Integer> estadoAtivoInativo);
}
