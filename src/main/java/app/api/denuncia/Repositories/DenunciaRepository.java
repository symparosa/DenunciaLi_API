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
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAnoDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_ConcelhoDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Concelho_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Concelho_TipoCrimeDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Concelho_TipoCrime_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Concelho_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_FaixaEtariaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_IlhaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Ilha_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_MesDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_Mes_TipoCrimeDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_TipoCrimeDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_TipoCrime_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorAno_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorConcelhoDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorConcelho_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorConcelho_TipoCrimeDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorConcelho_TipoCrime_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorConcelho_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorFaixaEtariaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorFaixaEtaria_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorFaixaEtaria_TipoCrimeDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorGeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorIlhaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorIlha_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorIlha_TipoCrimeDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorIlha_TipoCrime_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorIlha_TipoCrime_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorIlha_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorTipoCrimeDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorTipoCrime_GeneroDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorTipoCrime_TipoQueixaDto;
import app.api.denuncia.Dto.Estatistica.EstatisticaDenunciaPorTipoQueixaDto;
import app.api.denuncia.Models.DenunciaModel;

@Repository
@Transactional
public interface DenunciaRepository extends JpaRepository<DenunciaModel, Integer> {

        @Query(value = "SELECT id,data_criacao,data_ocorrencia,descricao,grau_parentesco,tipo_queixa_fk,tipoQueixa,tipo_crime_fk,tipoCrime,"
                        + "descricao_endereco,porta,rua,localizacao_fk,ilha,concelho,zona,lugar,utilizador_fk,username, localizacao_gps_map"
                        + " FROM view_denuncia_info"
                        + " WHERE id=:id", nativeQuery = true)
        DenunciaOutputDto findById(@Param("id") int id);

        @Query(value = "SELECT id,data_criacao,data_ocorrencia,descricao,grau_parentesco,tipo_queixa_fk,tipoQueixa,tipo_crime_fk,tipoCrime,"
                        + "descricao_endereco,porta,rua,localizacao_fk,ilha,concelho,zona,lugar,utilizador_fk,username, localizacao_gps_map"
                        + " FROM view_denuncia_info"
                        + " WHERE utilizadorId=:id", nativeQuery = true)
        List<DenunciaOutputDto> listarDenunciasByUserId(@Param("id") int id);

        @Modifying
        @Query(value = "UPDATE arquivo SET data_atualizacao=now(), queixa_fk=:queixa_fk WHERE id=:id", nativeQuery = true)
        int atualizarArquivo(@Param("queixa_fk") int queixa_fk, @Param("id") int id);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, Quantidade, Total, Percentagem from view_por_ano"
                        + " where Ano in :ano"
                        + " group by Ano"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAnoDto> getEstatisticaDenunciaPorAno(@Param("ano") Collection<Integer> ano);

        @Query(value = "select Ano, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_tipoqueixa"
                        + " where Ano = :ano and TipoQueixa in :tipoQueixa"
                        + " group by TipoQueixa"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_TipoQueixaDto> getEstatisticaDenunciaPorAno_TipoQueixa(@Param("ano") Integer ano,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ano, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_genero"
                        + " where Ano = :ano"
                        + " group by Ano;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_GeneroDto> getEstatisticaDenunciaPorAno_Genero(@Param("ano") Integer ano);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E MÊS
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, Mes, Quantidade, Total, Percentagem from view_por_ano_mes"
                        + " where Ano = :ano and Mes in :mes"
                        + " group by Mes"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_MesDto> getEstatisticaDenunciaPorAno_Mes(@Param("ano") Integer ano,
                        @Param("mes") Collection<Integer> mes);

        @Query(value = "select Ano, Mes, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_mes_tipoCrime"
                        + " where Ano = :ano and Mes in :mes and TipoCrime in :tipoCrime"
                        + " group by Mes, TipoCrime"
                        + " order by Mes;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Mes_TipoCrimeDto> getEstatisticaDenunciaPorAno_Mes_TipoCrime(
                        @Param("ano") Integer ano, @Param("mes") Collection<Integer> mes,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E TIPO DE CRIME
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_tipoCrime"
                        + " where Ano = :ano and TipoCrime in :tipoCrime "
                        + " group by TipoCrime"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_TipoCrimeDto> getEstatisticaDenunciaPorAno_TipoCrime(@Param("ano") Integer ano,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_tipocrime_genero"
                        + "  where Ano = :ano and TipoCrime in :tipoCrime "
                        + " group by TipoCrime;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_TipoCrime_GeneroDto> getEstatisticaDenunciaPorAno_TipoCrime_Genero(
                        @Param("ano") Integer ano, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_tipocrime_tipoqueixa"
                        + " where Ano = :ano and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa, TipoCrime"
                        + " order by TipoCrime;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto> getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(
                        @Param("ano") Integer ano, @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E FAIXA ETÁRIA
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, Faixa_etaria, Quantidade, Total, Percentagem from view_por_ano_faixaetaria"
                        + " where Ano = :ano "
                        + " group by Faixa_etaria"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_FaixaEtariaDto> getEstatisticaDenunciaPorAno_FaixaEtaria(
                        @Param("ano") Integer ano);

        @Query(value = "select Ano, Faixa_etaria, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_faixaetaria_genero"
                        + " where Ano = :ano "
                        + " group by Faixa_etaria;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto> getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(
                        @Param("ano") Integer ano);

        @Query(value = "select Ano, Faixa_etaria, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_faixaetaria_tipocrime"
                        + " where Ano = :ano and TipoCrime in :tipoCrime "
                        + " group by Faixa_etaria, TipoCrime"
                        + " order by Faixa_etaria;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto> getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(
                        @Param("ano") Integer ano, @Param("tipoCrime") Collection<Integer> tipoCrime);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E ILHA
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, IdIlha, Quantidade, Total, Percentagem from view_por_ano_ilha"
                        + " where Ano = :ano and IdIlha in :Ilha "
                        + " group by IdIlha"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_IlhaDto> getEstatisticaDenunciaPorAno_Ilha(@Param("ano") Integer ano,
                        @Param("Ilha") Collection<Integer> Ilha);

        @Query(value = "select Ano, IdIlha, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_ilha_genero"
                        + " where Ano = :ano and IdIlha in :Ilha "
                        + " group by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_GeneroDto> getEstatisticaDenunciaPorAno_Ilha_Genero(
                        @Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha);

        @Query(value = "select Ano, IdIlha, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_ilha_tipocrime"
                        + " where Ano = :ano and IdIlha in :Ilha and TipoCrime in :tipoCrime  "
                        + " group by IdIlha, TipoCrime"
                        + " order by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto> getEstatisticaDenunciaPorAno_Ilha_TipoCrime(
                        @Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, IdIlha, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_ilha_tipoqueixa"
                        + " where Ano = :ano and IdIlha in :Ilha and TipoQueixa in :tipoQueixa  "
                        + " group by IdIlha, TipoQueixa"
                        + " order by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto> getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(
                        @Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ano, IdIlha, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_ilha_tipocrime_genero"
                        + " where Ano = :ano and IdIlha =:Ilha and TipoCrime in :tipoCrime  "
                        + " group by IdIlha, TipoCrime"
                        + " order by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto> getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(
                        @Param("ano") Integer ano, @Param("Ilha") Integer Ilha,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, IdIlha, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_ilha_tipocrime_tipoqueixa"
                        + " where Ano = :ano and IdIlha = :Ilha and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " group by IdIlha, TipoCrime, TipoQueixa"
                        + " order by IdIlha;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto> getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(
                        @Param("ano") Integer ano, @Param("Ilha") Integer Ilha,
                        @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E CONCELHO
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, Concelho, Quantidade, Total, Percentagem from view_por_ano_concelho"
                        + " where Ano = :ano and Concelho in :Concelho "
                        + " group by Concelho"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_ConcelhoDto> getEstatisticaDenunciaPorAno_Concelho(@Param("ano") Integer ano,
                        @Param("Concelho") Collection<Integer> Concelho);

        @Query(value = "select Ano, Concelho, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_concelho_genero"
                        + " where Ano = :ano and Concelho in :Concelho "
                        + " group by Concelho;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Concelho_GeneroDto> getEstatisticaDenunciaPorAno_Concelho_Genero(
                        @Param("ano") Integer ano, @Param("Concelho") Collection<Integer> Concelho);

        @Query(value = "select Ano, Concelho, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_concelho_tipocrime"
                        + " where Ano = :ano and Concelho in :Concelho and TipoCrime in :tipoCrime  "
                        + " group by Concelho, TipoCrime"
                        + " order by Concelho;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Concelho_TipoCrimeDto> getEstatisticaDenunciaPorAno_Concelho_TipoCrime(
                        @Param("ano") Integer ano, @Param("Concelho") Collection<Integer> Concelho,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, Concelho, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_concelho_tipoqueixa"
                        + " where Ano = :ano and Concelho in :Concelho and TipoQueixa in :tipoQueixa  "
                        + " group by Concelho, TipoQueixa"
                        + " order by Concelho;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Concelho_TipoQueixaDto> getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(
                        @Param("ano") Integer ano, @Param("Concelho") Collection<Integer> Concelho,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ano, Concelho, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_concelho_tipocrime_genero"
                        + " where Ano = :ano and Concelho = :Concelho and TipoCrime in :tipoCrime  "
                        + " group by Concelho, TipoCrime"
                        + " order by Concelho;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Concelho_TipoCrime_GeneroDto> getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(
                        @Param("ano") Integer ano, @Param("Concelho") Integer Concelho,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, Concelho, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_concelho_tipocrime_tipoqueixa"
                        + " where Ano = :ano and Concelho = :Concelho and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " group by Concelho, TipoCrime, TipoQueixa"
                        + " order by Concelho;", nativeQuery = true)
        List<EstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixaDto> getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(
                        @Param("ano") Integer ano, @Param("Concelho") Integer Concelho,
                        @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ILHA
        // -------------------------------------------------------------------------

        @Query(value = "select Ilha, Quantidade, Total, Percentagem from view_por_ilha"
                        + " where Ilha in :Ilha "
                        + " group by Ilha"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorIlhaDto> getEstatisticaDenunciaPorIlha(@Param("Ilha") Collection<Integer> Ilha);

        @Query(value = "select Ilha, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ilha_genero"
                        + " where Ilha =:Ilha "
                        + " group by Ilha;", nativeQuery = true)
        List<EstatisticaDenunciaPorIlha_GeneroDto> getEstatisticaDenunciaPorIlha_Genero(@Param("Ilha") Integer Ilha);

        @Query(value = "select Ilha, TipoCrime, Quantidade, Total, Percentagem from view_por_ilha_tipocrime"
                        + " where Ilha = :Ilha and TipoCrime in :tipoCrime "
                        + " group by TipoCrime"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorIlha_TipoCrimeDto> getEstatisticaDenunciaPorIlha_TipoCrime(
                        @Param("Ilha") Integer Ilha, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ilha, TipoQueixa, Quantidade, Total, Percentagem from view_por_ilha_tipoqueixa"
                        + " where Ilha = :Ilha and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorIlha_TipoQueixaDto> getEstatisticaDenunciaPorIlha_TipoQueixa(
                        @Param("Ilha") Integer Ilha, @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ilha, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ilha_tipocrime_genero"
                        + " where  Ilha = :Ilha and TipoCrime in :tipoCrime  "
                        + " group by TipoCrime;", nativeQuery = true)
        List<EstatisticaDenunciaPorIlha_TipoCrime_GeneroDto> getEstatisticaDenunciaPorIlha_TipoCrime_Genero(
                        @Param("Ilha") Integer Ilha, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ilha, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ilha_tipocrime_tipoqueixa"
                        + " where Ilha = :Ilha and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa, TipoCrime"
                        + " order by TipoCrime;", nativeQuery = true)
        List<EstatisticaDenunciaPorIlha_TipoCrime_TipoQueixaDto> getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(
                        @Param("Ilha") Integer Ilha, @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR CONCELHO
        // -------------------------------------------------------------------------

        @Query(value = "select Concelho, Quantidade, Total, Percentagem from view_por_concelho"
                        + " where Concelho in :Concelho "
                        + " group by Concelho"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorConcelhoDto> getEstatisticaDenunciaPorConcelho(
                        @Param("Concelho") Collection<Integer> Concelho);

        @Query(value = "select Concelho, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_concelho_genero"
                        + " where Concelho = :Concelho  "
                        + " group by Concelho;", nativeQuery = true)
        List<EstatisticaDenunciaPorConcelho_GeneroDto> getEstatisticaDenunciaPorConcelho_Genero(
                        @Param("Concelho") Integer Concelho);

        @Query(value = "select Concelho, TipoCrime, Quantidade, Total, Percentagem from view_por_concelho_tipocrime"
                        + " where Concelho = :Concelho and TipoCrime in :tipoCrime "
                        + " group by TipoCrime"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorConcelho_TipoCrimeDto> getEstatisticaDenunciaPorConcelho_TipoCrime(
                        @Param("Concelho") Integer Concelho, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Concelho, TipoQueixa, Quantidade, Total, Percentagem from view_por_concelho_tipoqueixa"
                        + " where Concelho = :Concelho and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorConcelho_TipoQueixaDto> getEstatisticaDenunciaPorConcelho_TipoQueixa(
                        @Param("Concelho") Integer Concelho, @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Concelho, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_concelho_tipocrime_genero"
                        + " where  Concelho = :Concelho and TipoCrime in :tipoCrime  "
                        + " group by Concelho, TipoCrime"
                        + " order by Concelho;", nativeQuery = true)
        List<EstatisticaDenunciaPorConcelho_TipoCrime_GeneroDto> getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(
                        @Param("Concelho") Integer Concelho, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Concelho, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_concelho_tipocrime_tipoqueixa"
                        + " where Concelho = :Concelho and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa, TipoCrime"
                        + " order by TipoCrime;", nativeQuery = true)
        List<EstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixaDto> getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(
                        @Param("Concelho") Integer Concelho, @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR GENERO
        // -------------------------------------------------------------------------

        @Query(value = "select QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_genero"
                        + " group by QuantidadeFeminino, QuantidadeMasculino;", nativeQuery = true)
        List<EstatisticaDenunciaPorGeneroDto> getEstatisticaDenunciaPorGenero();

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR TIPO DE CRIME
        // -------------------------------------------------------------------------

        @Query(value = "select TipoCrime, Quantidade, Total, Percentagem from view_por_tipocrime"
                        + " where TipoCrime in :tipoCrime "
                        + " group by TipoCrime"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorTipoCrimeDto> getEstatisticaDenunciaPorTipoCrime(
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select TipoCrime, QuantidadeFeminino,QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_tipocrime_genero"
                        + " where TipoCrime in :tipoCrime "
                        + " group by TipoCrime;", nativeQuery = true)
        List<EstatisticaDenunciaPorTipoCrime_GeneroDto> getEstatisticaDenunciaPorTipoCrime_Genero(
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_tipocrime_tipoqueixa"
                        + " where TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa, TipoCrime"
                        + " order by TipoCrime;", nativeQuery = true)
        List<EstatisticaDenunciaPorTipoCrime_TipoQueixaDto> getEstatisticaDenunciaPorTipoCrime_TipoQueixa(
                        @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR TIPO DE QUEIXA
        // -------------------------------------------------------------------------

        @Query(value = "select TipoQueixa, Quantidade, Total, Percentagem from view_por_tipoqueixa"
                        + " where TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorTipoQueixaDto> getEstatisticaDenunciaPorTipoQueixa(
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR FAIXA ETÁRIA
        // -------------------------------------------------------------------------

        @Query(value = "select Faixa_etaria, Quantidade, Total, Percentagem from view_por_faixaetaria"
                        + " group by Faixa_etaria"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<EstatisticaDenunciaPorFaixaEtariaDto> getEstatisticaDenunciaPorFaixaEtaria();

        @Query(value = "select Faixa_etaria, QuantidadeFeminino,QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_faixaetaria_genero"
                        + " group by Faixa_etaria;", nativeQuery = true)
        List<EstatisticaDenunciaPorFaixaEtaria_GeneroDto> getEstatisticaDenunciaPorFaixaEtaria_Genero();

        @Query(value = "select Faixa_etaria, TipoCrime, Quantidade, Total, Percentagem from view_por_faixaetaria_tipocrime"
                        + " where TipoCrime in :tipoCrime "
                        + " group by Faixa_etaria, TipoCrime"
                        + " order by Faixa_etaria;", nativeQuery = true)
        List<EstatisticaDenunciaPorFaixaEtaria_TipoCrimeDto> getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

}
