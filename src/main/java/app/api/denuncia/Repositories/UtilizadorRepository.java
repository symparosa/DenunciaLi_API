package app.api.denuncia.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Dto.UtilizadorOutputDto;
import app.api.denuncia.Models.UtilizadorModel;

@Repository
@Transactional
public interface UtilizadorRepository extends JpaRepository<UtilizadorModel, Integer> {

        UtilizadorModel findById(@Param("id") int id);

        UtilizadorModel findByBi(@Param("bi") String bi);

        UtilizadorModel findByCni(@Param("cni") String cni);

        UtilizadorModel findByEmail(@Param("email") String email);

        UtilizadorModel findByUsername(@Param("username") String username);

        UtilizadorModel findByTelemovel(@Param("telemovel") String telemovel);

        UtilizadorModel findByIdAndEstado(@Param("id") int id, @Param("estado") int estado);

        @Query(value = "select estado from utilizador where id=:id", nativeQuery = true)
        int validarUtilizador(@Param("id") int id);

        @Query(value = "select * from utilizador where id!=:id and email=:email", nativeQuery = true)
        UtilizadorModel validarUtilizadorEmail(@Param("id") int id, @Param("email") String email);

        @Query(value = "select * from utilizador where id!=:id and telemovel=:telemovel", nativeQuery = true)
        UtilizadorModel validarUtilizadorTelemovel(@Param("id") int id, @Param("telemovel") String telemovel);

        @Query(value = "SELECT id, apelido, bi, cni, data_atualizacao, data_criacao, data_nascimento, email, estado, foto, genero,"
                        + "nome, telemovel, localizacao_fk,longitude,latitude,"
                        + "ilha,concelho,zona, lugar, username"
                        + " FROM view_utilizador_info "
                        + " WHERE estado=1 and estadoLugar = 1 and estadoZona = 1 and estadoConcelho = 1 and estadoIlha = 1 and id=:id", nativeQuery = true)
        UtilizadorOutputDto findByIdUser(@Param("id") int id);

        @Query(value = "SELECT id, apelido, bi, cni, data_atualizacao, data_criacao, data_nascimento, email, estado, foto, genero,"
                        + "nome, telemovel, localizacao_fk,longitude,latitude,"
                        + "ilha,concelho,zona, lugar, username"
                        + " FROM view_utilizador_info "
                        + " WHERE estado=:estado and estadoLugar = 1 and estadoZona = 1 and estadoConcelho = 1 and estadoIlha = 1", nativeQuery = true)
        List<UtilizadorOutputDto> listarUtilizadorsAtivos_Inativo(@Param("estado") int estado);

        @Modifying
        @Query(value = "update utilizador set estado=:estado, data_atualizacao=now() where id=:id", nativeQuery = true)
        int ativar_desativarUtilizador(@Param("estado") int fav, @Param("id") int id);

        @Modifying
        @Query(value = "INSERT INTO utilizador(apelido, bi, cni, data_criacao, data_nascimento, email, estado, foto, genero, nome, telemovel, localizacao_fk, username,tipo_utilizador_fk) VALUES (?,?,?,now(),?,?,1,?,?,?,?,?,?,?)", nativeQuery = true)
        int save(@Param("apelido") String apelido, @Param("bi") String bi, @Param("cni") String cni,
                        @Param("data_nascimento") Date data_nascimento, @Param("email") String email,
                        @Param("foto") String foto,
                        @Param("genero") String genero, @Param("nome") String nome,
                        @Param("telemovel") String telemovel,
                        @Param("localizacao_fk") int localizacao_fk, @Param("username") String username,
                        @Param("tipoUtilizador_fk") int tipoUtilizador_fk);

        @Modifying
        @Query(value = "UPDATE utilizador SET apelido=:apelido, data_atualizacao=now(),email=:email,nome=:nome,telemovel=:telemovel,localizacao_fk=:localizacao_fk,foto=:foto where id=:id and estado = 1", nativeQuery = true)
        int atualizarUtilizadorInfo(@Param("apelido") String apelido, @Param("email") String email,
                        @Param("nome") String nome, @Param("telemovel") String telemovel,
                        @Param("localizacao_fk") int localizacao_fk, @Param("foto") String foto, @Param("id") int id);
}
