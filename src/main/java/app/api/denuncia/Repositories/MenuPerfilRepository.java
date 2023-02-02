package app.api.denuncia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.MenuModel;
import app.api.denuncia.Models.MenuPerfilModel;

@Repository
@Transactional
public interface MenuPerfilRepository extends JpaRepository<MenuPerfilModel, Integer>{
    
    Boolean existsByMenuAndTipoUtilizador(MenuModel menu, DominioModel perfil);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_menu_perfil SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE menu_fk =:id_m and tipo_utilizador_fk=:id_p", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado,@Param("user") int user, @Param("id_m") int id_m, @Param("id_p") int id_p);
}
