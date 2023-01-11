package app.api.denuncia.Repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Dto.DenunciaOutputDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAnoDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_FaixaEtariaDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_MesDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_Mes_TipoCrimeDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_TipoCrimeDto;
import app.api.denuncia.Models.DenunciaModel;

@Repository
@Transactional
public interface DenunciaRepository extends JpaRepository<DenunciaModel, Integer> {

        @Query(value = "SELECT id,data_criacao,data_ocorrencia,descricao,grau_parentesco,tipo_queixa_fk,tipoQueixa,tipo_crime_fk,tipoCrime,"
        + "descricao_endereco,porta,rua,localizacao_fk,ilha,concelho,zona,lugar,utilizador_fk,username"
        + " FROM view_denuncia_info"
        + " WHERE estadoTipo_crime = 1 AND estadoLugar = 1 AND estadoQueixa = 1 AND estadoZona = 1"
        + " AND estadoConcelho = 1 AND estadoIlha = 1 AND estadoDenuncia = 1"
        + " AND estadoUtilizador = 1 AND estadoTipo_queixa = 1 AND id=:id", nativeQuery = true)
        DenunciaOutputDto findById(@Param("id") int id);

        @Query(value = "SELECT id,data_criacao,data_ocorrencia,descricao,grau_parentesco,tipo_queixa_fk,tipoQueixa,tipo_crime_fk,tipoCrime,"
        + "descricao_endereco,porta,rua,localizacao_fk,ilha,concelho,zona,lugar,utilizador_fk,username"
        + " FROM view_denuncia_info"
        + " WHERE estadoTipo_crime = 1 AND estadoLugar = 1 AND estadoQueixa = 1 AND estadoZona = 1"
        + " AND estadoConcelho = 1 AND estadoIlha = 1 AND estadoDenuncia = 1"
        + " AND estadoUtilizador = 1 AND estadoTipo_queixa = 1 AND utilizadorId=:id", nativeQuery = true)
        List<DenunciaOutputDto> listarDenunciasByUserId(@Param("id") int id);

        @Modifying
        @Query(value = "UPDATE arquivo SET data_atualizacao=now(), queixa_fk=:queixa_fk WHERE id=:id", nativeQuery = true)
        int atualizarArquivo(@Param("queixa_fk") int queixa_fk, @Param("id") int id);

        //Estatística

        @Query(value = "select Ano, Quantidade, Total, Percentagem from view_por_ano"
        +" where Year(data_criacao) in :ano and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Ano"
        +" order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAnoDto> getEstatisticaDenunciaPorAno(@Param("ano") Collection<String> ano);

        @Query(value = "select Ano, Mes, Quantidade, Total, Percentagem from view_por_ano_mes"
        +" where Year(data_criacao) = :ano and month(data_criacao) in :mes and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Mes"
        +" order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_MesDto> getEstatisticaDenunciaPorAno_Mes(@Param("ano") String ano,@Param("mes") Collection<Integer> mes);

        @Query(value = "select Ano, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_tipoCrime"
        +" where Year(data_criacao) = :ano and TipoCrime in :tipoCrime and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by TipoCrime"
        +" order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_TipoCrimeDto> getEstatisticaDenunciaPorAno_TipoCrime( @Param("ano") String ano,@Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, Mes, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_mes_tipoCrime"
        +" where Year(data_criacao) = :ano and month(data_criacao) in :mes and TipoCrime in :tipoCrime and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Mes, TipoCrime"
        +" order by Mes;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Mes_TipoCrimeDto> getEstatisticaDenunciaPorAno_Mes_TipoCrime( @Param("ano") String ano,@Param("mes") Collection<Integer> mes,@Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, Faixa_etaria, Quantidade, Total, Percentagem from view_por_ano_faixaetaria"
        +" where Year(data_criacao) = :ano and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Faixa_etaria"
        +" order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_FaixaEtariaDto> getEstatisticaDenunciaPorAno_FaixaEtaria(@Param("ano") String ano);

        @Query(value = "select Ano, Faixa_etaria, QuantidadeFeminino,QuantidadeMasculino, Total, PercentagemFeminino,PercentagemMasculino "
        +" from view_por_ano_faixaetaria_genero"
        +" where Year(data_criacao) = :ano and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Faixa_etaria;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto> getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(@Param("ano") String ano);

        @Query(value = "select Ano, Faixa_etaria, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_faixaetaria_tipocrime"
        +" where Year(data_criacao) = :ano and TipoCrime in :tipoCrime and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Faixa_etaria, TipoCrime"
        +" order by Faixa_etaria;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto> getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(@Param("ano") String ano,@Param("tipoCrime") Collection<Integer> tipoCrime);
}
