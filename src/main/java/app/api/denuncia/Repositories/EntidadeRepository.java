// package app.api.denuncia.Repositories;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

// import app.api.denuncia.Dto.Instituicao_ApoioOutputDto;
// import app.api.denuncia.Models.EntidadeModel;

// @Repository
// @Transactional
// public interface Instituicao_ApoioRepository extends JpaRepository<EntidadeModel, Integer> {

//         EntidadeModel findById(@Param("id") int id);

//         EntidadeModel findByNome(@Param("nome") String nome);

//         EntidadeModel findByEmail(@Param("email") String email);

//         EntidadeModel findByTelefone(@Param("telefone") String telefone);

//         EntidadeModel findByIdAndEstado(@Param("id") int id, @Param("estado") int estado);

//         @Query(value = "select * from instituicao_apoio where id!=:id and email=:email", nativeQuery = true)
//         EntidadeModel validarInstituicaoApoioEmail(@Param("id") int id, @Param("email") String email);

//         @Query(value = "select * from instituicao_apoio where id!=:id and telefone=:telefone", nativeQuery = true)
//         EntidadeModel validarInstituicaoApoioTelefone(@Param("id") int id, @Param("telefone") String telefone);

//         @Query(value = "SELECT id,data_atualizacao,data_criacao,email,estado,logotipo,nome,porta,"
//                         + "rua,telefone,tipo_crime_fk,crime,localizacao_fk,"
//                         + "longitude,latitude, ilha,concelho,zona,lugar"
//                         + " FROM view_instituicaoApoio_info"
//                         + " WHERE id=:id", nativeQuery = true)
//         Instituicao_ApoioOutputDto findByIdInst(@Param("id") int id);

//         @Query(value = "SELECT id,data_atualizacao,data_criacao,email,estado,logotipo,nome,porta,"
//                         + "rua,telefone,tipo_crime_fk,crime,localizacao_fk,"
//                         + "longitude,latitude, ilha,concelho,zona,lugar"
//                         + " FROM view_instituicaoApoio_info"
//                         + " WHERE estadoInstituicao_apoio =:estado", nativeQuery = true)
//         List<Instituicao_ApoioOutputDto> listarInstituicaoDeApoioAtivos_Inativo(@Param("estado") int estado);

//         @Query(value = "SELECT id,data_atualizacao,data_criacao,email,estado,logotipo,nome,porta,"
//                         + "rua,telefone,tipo_crime_fk,crime,localizacao_fk,"
//                         + "longitude,latitude, ilha,concelho,zona,lugar"
//                         + " FROM view_instituicaoApoio_info"
//                         + " WHERE tipoCrimeId=:id", nativeQuery = true)
//         List<Instituicao_ApoioOutputDto> getInstituicaoApoioByCrime(@Param("id") int id);

//         @Modifying
//         @Query(value = "update instituicao_apoio set estado=:estado, data_atualizacao=now() where id=:id", nativeQuery = true)
//         int ativar_desativarInstituicaoApoio(@Param("estado") int fav, @Param("id") int id);

//         @Modifying
//         @Query(value = "INSERT INTO instituicao_apoio(data_criacao, email,localizacao_fk, estado, logotipo, nome, telefone, tipo_crime_fk,porta,rua) VALUES (now(),?,?,1,?,?,?,?,?,?)", nativeQuery = true)
//         int save(@Param("email") String email, @Param("endereco") int endereco, @Param("logotipo") String logotipo,
//                         @Param("nome") String nome, @Param("telefone") String telefone,
//                         @Param("tipo_crime_fk") int tipo_crime_fk,
//                         @Param("porta") String porta, @Param("rua") String rua);

//         @Modifying
//         @Query(value = "UPDATE instituicao_apoio SET data_atualizacao=now(),email=:email,localizacao_fk=:endereco,logotipo=:logotipo,nome=:nome,telefone=:telefone,porta=:porta,rua=:rua where id=:id and estado = 1", nativeQuery = true)
//         int atualizarInstituicaoApoio(@Param("email") String email, @Param("endereco") int endereco,
//                         @Param("logotipo") String logotipo, @Param("nome") String nome,
//                         @Param("telefone") String telefone,
//                         @Param("porta") String porta, @Param("rua") String rua, @Param("id") int id);
// }
