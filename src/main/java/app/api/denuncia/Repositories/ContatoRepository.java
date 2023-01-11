package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.ContatoModel;

@Repository
@Transactional
public interface ContatoRepository extends JpaRepository<ContatoModel, Integer> {

    ContatoModel findById(@Param("id") int id);

    ContatoModel findByNome(@Param("nome") String nome);

    List<ContatoModel> findAllByEstado(@Param("estado") int estado);

    ContatoModel findByIdAndEstado(@Param("id") int id, @Param("estado") int estado);

    @Query(value = "select * from contato where id!=:id and nome=:nome", nativeQuery = true)
    ContatoModel validarContato(@Param("id") int id, @Param("nome") String nome);

    @Query(value = "select * from contato where telefone is not null and telefone=:telefone", nativeQuery = true)
    ContatoModel findByTelefone(@Param("telefone") String telefone);

    @Modifying
    @Query(value = "update contato set estado=:estado, data_atualizacao=now() where id=:id", nativeQuery = true)
    int ativar_desativarContato(@Param("estado") int estado, @Param("id") int id);

    @Modifying
    @Query(value = "INSERT INTO contato(telefone, data_criacao, estado, logotipo, nome) VALUES (?,now(),1,?,?)", nativeQuery = true)
    int save(@Param("telefone") String telefone, @Param("logotipo") String logotipo, @Param("nome") String nome);

    @Modifying
    @Query(value = "UPDATE contato SET telefone=:telefone,data_atualizacao=now(),nome=:nome, logotipo=:logotipo where id=:id and estado = 1", nativeQuery = true)
    int atualizarContatoInfo(@Param("telefone") String telefone, @Param("nome") String nome,
            @Param("logotipo") String logotipo, @Param("id") int id);
}
