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
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_GeneroDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_IlhaDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_Ilha_GeneroDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_MesDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_Mes_TipoCrimeDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_TipoCrimeDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_TipoCrime_GeneroDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_TipoQueixaDto;
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
        List<EstatisticaDenunciaPorAnoDto> getEstatisticaDenunciaPorAno(@Param("ano") Collection<Integer> ano);

        @Query(value = "select Ano, Mes, Quantidade, Total, Percentagem from view_por_ano_mes"
        +" where Year(data_criacao) = :ano and month(data_criacao) in :mes and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Mes"
        +" order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_MesDto> getEstatisticaDenunciaPorAno_Mes(@Param("ano") Integer ano,@Param("mes") Collection<Integer> mes);

        @Query(value = "select Ano, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_tipoCrime"
        +" where Year(data_criacao) = :ano and TipoCrime in :tipoCrime and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by TipoCrime"
        +" order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_TipoCrimeDto> getEstatisticaDenunciaPorAno_TipoCrime( @Param("ano") Integer ano,@Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, Mes, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_mes_tipoCrime"
        +" where Year(data_criacao) = :ano and month(data_criacao) in :mes and TipoCrime in :tipoCrime and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Mes, TipoCrime"
        +" order by Mes;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Mes_TipoCrimeDto> getEstatisticaDenunciaPorAno_Mes_TipoCrime( @Param("ano") Integer ano,@Param("mes") Collection<Integer> mes,@Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, Faixa_etaria, Quantidade, Total, Percentagem from view_por_ano_faixaetaria"
        +" where Year(data_criacao) = :ano and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Faixa_etaria"
        +" order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_FaixaEtariaDto> getEstatisticaDenunciaPorAno_FaixaEtaria(@Param("ano") Integer ano);

        @Query(value = "select Ano, Faixa_etaria, QuantidadeFeminino,QuantidadeMasculino, Total, PercentagemFeminino,PercentagemMasculino "
        +" from view_por_ano_faixaetaria_genero"
        +" where Year(data_criacao) = :ano and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Faixa_etaria;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto> getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(@Param("ano") Integer ano);

        @Query(value = "select Ano, Faixa_etaria, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_faixaetaria_tipocrime"
        +" where Year(data_criacao) = :ano and TipoCrime in :tipoCrime and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Faixa_etaria, TipoCrime"
        +" order by Faixa_etaria;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto> getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(@Param("ano") Integer ano,@Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, TipoCrime, QuantidadeFeminino,QuantidadeMasculino, Total, PercentagemFeminino,PercentagemMasculino from view_por_ano_tipocrime_genero"
        +" where Year(data_criacao) = :ano and TipoCrime in :tipoCrime and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by TipoCrime;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_TipoCrime_GeneroDto> getEstatisticaDenunciaPorAno_TipoCrime_Genero(@Param("ano") Integer ano,@Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_tipoqueixa"
        +" where Year(data_criacao) = :ano and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by TipoQueixa"
        +" order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_TipoQueixaDto> getEstatisticaDenunciaPorAno_TipoQueixa(@Param("ano") Integer ano);

        @Query(value = "select Ano, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_tipocrime_tipoqueixa"
        +" where Year(data_criacao) = :ano and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by TipoQueixa, TipoCrime"
        +" order by TipoCrime;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto> getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(@Param("ano") Integer ano,@Param("tipoCrime") Collection<Integer> tipoCrime, @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ano, QuantidadeFeminino, QuantidadeMasculino, Total, PercentagemFeminino, PercentagemMasculino from view_por_ano_genero"
        +" where Year(data_criacao) = :ano and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by Ano;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_GeneroDto> getEstatisticaDenunciaPorAno_Genero(@Param("ano") Integer ano);

        @Query(value = "select Ano, IdIlha, Quantidade, Total, Percentagem from view_por_ano_ilha"
        +" where Year(data_criacao) = :ano and IdIlha in :Ilha and  estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by IdIlha"
        +" order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_IlhaDto> getEstatisticaDenunciaPorAno_Ilha(@Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha);

        @Query(value = "select Ano, IdIlha, QuantidadeFeminino, QuantidadeMasculino, Total, PercentagemFeminino, PercentagemMasculino from view_por_ano_ilha_genero"
        +" where Year(data_criacao) = :ano and IdIlha in :Ilha and estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_GeneroDto> getEstatisticaDenunciaPorAno_Ilha_Genero(@Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha);

        @Query(value = "select Ano, IdIlha, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_ilha_tipocrime"
        +" where Year(data_criacao) = :ano and IdIlha in :Ilha and TipoCrime in :tipoCrime  and  estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by IdIlha, TipoCrime"
        +" order by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto> getEstatisticaDenunciaPorAno_Ilha_TipoCrime(@Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, IdIlha, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_ilha_tipoqueixa"
        +" where Year(data_criacao) = :ano and IdIlha in :Ilha and TipoQueixa in :tipoQueixa  and  estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by IdIlha, TipoQueixa"
        +" order by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto> getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(@Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha, @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ano, IdIlha, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, Total, PercentagemFeminino, PercentagemMasculino from view_por_ano_ilha_tipocrime_genero"
        +" where Year(data_criacao) = :ano and IdIlha = :Ilha and TipoCrime in :tipoCrime  and  estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by IdIlha, TipoCrime"
        +" order by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto> getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(@Param("ano") Integer ano, @Param("Ilha") Integer Ilha, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, IdIlha, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_ilha_tipocrime_tipoqueixa"
        +" where Year(data_criacao) = :ano and IdIlha = :Ilha and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa and  estado = 1 and queixa_fk is not null and utilizador_fk is not null"
        +" group by IdIlha, TipoCrime, TipoQueixa"
        +" order by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto> getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(@Param("ano") Integer ano, @Param("Ilha") Integer Ilha, @Param("tipoCrime") Collection<Integer> tipoCrime, @Param("tipoQueixa") Collection<Integer> tipoQueixa);
}
