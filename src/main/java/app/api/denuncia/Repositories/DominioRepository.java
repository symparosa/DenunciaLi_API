// package app.api.denuncia.Repositories;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

// import app.api.denuncia.Models.DominioModel;

// @Repository
// @Transactional
// public interface DominioRepository extends JpaRepository<DominioModel, Integer> {

//     DominioModel findById(@Param("id") int id);

//     DominioModel findByNome(@Param("nome") String nome);

//     List<DominioModel> findAllByEstado(@Param("estado") int estado);

//     DominioModel findByIdAndEstado(@Param("id") int id, @Param("estado") int estado);

//     @Query(value = "select * from tipo_arquivo where id!=:id and nome=:nome", nativeQuery = true)
//     DominioModel validarTipoArquivo(@Param("id") int id, @Param("nome") String nome);

//     @Modifying
//     @Query(value = "INSERT INTO tipo_arquivo(nome, data_criacao, estado) VALUES (?,now(),1);", nativeQuery = true)
//     int save(@Param("nome") String nome);

//     @Modifying
//     @Query(value = "update tipo_arquivo set estado=:estado, data_atualizacao=now() where id=:id", nativeQuery = true)
//     int ativar_desativarTipoArquivo(@Param("estado") int fav, @Param("id") int id);

//     @Modifying
//     @Query(value = "UPDATE tipo_arquivo SET nome=:nome,data_atualizacao=now() where id=:id and estado = 1", nativeQuery = true)
//     int atualizarTipoArquivo(@Param("nome") String nome, @Param("id") int id);
// }