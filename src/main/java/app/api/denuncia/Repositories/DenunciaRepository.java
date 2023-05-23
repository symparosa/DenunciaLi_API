package app.api.denuncia.Repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.DenunciaModel;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Concelho;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Concelho_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Concelho_TipoCrime;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Concelho_TipoCrime_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Concelho_TipoCrime_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Concelho_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_FaixaEtaria;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_FaixaEtaria_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_FaixaEtaria_TipoCrime;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Ilha;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Ilha_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Ilha_TipoCrime;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Ilha_TipoCrime_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Ilha_TipoCrime_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Ilha_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Mes;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_Mes_TipoCrime;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_TipoCrime;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_TipoCrime_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_TipoCrime_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorAno_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorConcelho;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorConcelho_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorConcelho_TipoCrime;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorConcelho_TipoCrime_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorConcelho_TipoCrime_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorConcelho_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorFaixaEtaria;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorFaixaEtaria_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorFaixaEtaria_TipoCrime;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorGenero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorIlha;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorIlha_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorIlha_TipoCrime;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorIlha_TipoCrime_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorIlha_TipoCrime_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorIlha_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorTipoCrime;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorTipoCrime_Genero;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorTipoCrime_TipoQueixa;
import app.api.denuncia.Statistic.Denuncia.DenunciaPorTipoQueixa;

@Repository
@Transactional
public interface DenunciaRepository extends JpaRepository<DenunciaModel, Integer> {
        @Query(value = "SELECT data_criacao"
                        + " ,estado"
                        + " ,queixa_codigo_postal AS codigo_postal"
                        + " ,data_ocorrencia"
                        + " ,descricao"
                        + " ,queixa_localizacao_mapa AS localizacao_mapa"
                        + " ,queixa_referencia_morada AS referencia_morada"
                        + " ,local_queixa_nome AS localizacao_nome"
                        + " ,local_queixa_nome_norm AS localizacao_nome_norm"
                        + " ,dominio_grau_parentesco_valor AS grau_parentesco"
                        + " ,dominio_tipo_crime_valor AS tipo_crime"
                        + " ,dominio_tipo_queixa_valor AS tipo_queixa"
                        + " FROM dbo.view_denuncia_info"
                        + " where denunciante_id=:id", nativeQuery = true)
        List<Object[]> listarDenunciasByUserId(@Param("id") int id);

        @Modifying
        @Query(value = "UPDATE dn_t_arquivo SET  data_criacao=GETDATE(), data_atualizacao=GETDATE(), last_user_change=:user, queixa_fk=:queixa_fk WHERE id=:id", nativeQuery = true)
        int atualizarArquivo(@Param("user") int user, @Param("queixa_fk") int queixa_fk, @Param("id") int id);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA DE DENÚNCIA
        // -------------------------------------------------------------------------

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, Quantidade, Total, Percentagem from view_por_ano"
                        + " where Ano in :ano"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorAno> getEstatisticaDenunciaPorAno(@Param("ano") Collection<Integer> ano);

        @Query(value = "select Ano, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_tipoqueixa"
                        + " where Ano = :ano and TipoQueixa in :tipoQueixa"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorAno_TipoQueixa> getEstatisticaDenunciaPorAno_TipoQueixa(@Param("ano") Integer ano,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ano, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_genero"
                        + " where Ano = :ano", nativeQuery = true)
        List<DenunciaPorAno_Genero> getEstatisticaDenunciaPorAno_Genero(@Param("ano") Integer ano);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E MÊS
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, Mes, Quantidade, Total, Percentagem from view_por_ano_mes"
                        + " where Ano = :ano and Mes in :mes"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorAno_Mes> getEstatisticaDenunciaPorAno_Mes(@Param("ano") Integer ano,
                        @Param("mes") Collection<Integer> mes);

        @Query(value = "select Ano, Mes, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_mes_tipoCrime"
                        + " where Ano = :ano and Mes in :mes and TipoCrime in :tipoCrime"
                        + " order by Mes;", nativeQuery = true)
        List<DenunciaPorAno_Mes_TipoCrime> getEstatisticaDenunciaPorAno_Mes_TipoCrime(
                        @Param("ano") Integer ano, @Param("mes") Collection<Integer> mes,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E TIPO DE CRIME
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_tipoCrime"
                        + " where Ano = :ano and TipoCrime in :tipoCrime "
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorAno_TipoCrime> getEstatisticaDenunciaPorAno_TipoCrime(@Param("ano") Integer ano,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_tipocrime_genero"
                        + "  where Ano = :ano and TipoCrime in :tipoCrime ", nativeQuery = true)
        List<DenunciaPorAno_TipoCrime_Genero> getEstatisticaDenunciaPorAno_TipoCrime_Genero(
                        @Param("ano") Integer ano, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_tipocrime_tipoqueixa"
                        + " where Ano = :ano and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " order by TipoCrime;", nativeQuery = true)
        List<DenunciaPorAno_TipoCrime_TipoQueixa> getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(
                        @Param("ano") Integer ano, @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E FAIXA ETÁRIA
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, Faixa_etaria, Quantidade, Total, Percentagem from view_por_ano_faixaetaria"
                        + " where Ano = :ano "
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorAno_FaixaEtaria> getEstatisticaDenunciaPorAno_FaixaEtaria(
                        @Param("ano") Integer ano);

        @Query(value = "select Ano, Faixa_etaria, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_faixaetaria_genero"
                        + " where Ano = :ano ", nativeQuery = true)
        List<DenunciaPorAno_FaixaEtaria_Genero> getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(
                        @Param("ano") Integer ano);

        @Query(value = "select Ano, Faixa_etaria, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_faixaetaria_tipocrime"
                        + " where Ano = :ano and TipoCrime in :tipoCrime "
                        + " order by Faixa_etaria;", nativeQuery = true)
        List<DenunciaPorAno_FaixaEtaria_TipoCrime> getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(
                        @Param("ano") Integer ano, @Param("tipoCrime") Collection<Integer> tipoCrime);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E ILHA
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, IdIlha, Quantidade, Total, Percentagem from view_por_ano_ilha"
                        + " where Ano = :ano and IdIlha in :Ilha "
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorAno_Ilha> getEstatisticaDenunciaPorAno_Ilha(@Param("ano") Integer ano,
                        @Param("Ilha") Collection<Integer> Ilha);

        @Query(value = "select Ano, IdIlha, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_ilha_genero"
                        + " where Ano = :ano and IdIlha in :Ilha ", nativeQuery = true)
        List<DenunciaPorAno_Ilha_Genero> getEstatisticaDenunciaPorAno_Ilha_Genero(
                        @Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha);

        @Query(value = "select Ano, IdIlha, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_ilha_tipocrime"
                        + " where Ano = :ano and IdIlha in :Ilha and TipoCrime in :tipoCrime  "
                        + " order by IdIlha;", nativeQuery = true)
        List<DenunciaPorAno_Ilha_TipoCrime> getEstatisticaDenunciaPorAno_Ilha_TipoCrime(
                        @Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, IdIlha, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_ilha_tipoqueixa"
                        + " where Ano = :ano and IdIlha in :Ilha and TipoQueixa in :tipoQueixa  "
                        + " order by IdIlha;", nativeQuery = true)
        List<DenunciaPorAno_Ilha_TipoQueixa> getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(
                        @Param("ano") Integer ano, @Param("Ilha") Collection<Integer> Ilha,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ano, IdIlha, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_ilha_tipocrime_genero"
                        + " where Ano = :ano and IdIlha =:Ilha and TipoCrime in :tipoCrime  "
                        + " order by IdIlha;", nativeQuery = true)
        List<DenunciaPorAno_Ilha_TipoCrime_Genero> getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(
                        @Param("ano") Integer ano, @Param("Ilha") Integer Ilha,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, IdIlha, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_ilha_tipocrime_tipoqueixa"
                        + " where Ano = :ano and IdIlha = :Ilha and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " order by IdIlha;", nativeQuery = true)
        List<DenunciaPorAno_Ilha_TipoCrime_TipoQueixa> getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(
                        @Param("ano") Integer ano, @Param("Ilha") Integer Ilha,
                        @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E CONCELHO
        // -------------------------------------------------------------------------

        @Query(value = "select Ano, Concelho, Quantidade, Total, Percentagem from view_por_ano_concelho"
                        + " where Ano = :ano and Concelho in :Concelho "
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorAno_Concelho> getEstatisticaDenunciaPorAno_Concelho(@Param("ano") Integer ano,
                        @Param("Concelho") Collection<Integer> Concelho);

        @Query(value = "select Ano, Concelho, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_concelho_genero"
                        + " where Ano = :ano and Concelho in :Concelho ", nativeQuery = true)
        List<DenunciaPorAno_Concelho_Genero> getEstatisticaDenunciaPorAno_Concelho_Genero(
                        @Param("ano") Integer ano, @Param("Concelho") Collection<Integer> Concelho);

        @Query(value = "select Ano, Concelho, TipoCrime, Quantidade, Total, Percentagem from view_por_ano_concelho_tipocrime"
                        + " where Ano = :ano and Concelho in :Concelho and TipoCrime in :tipoCrime  "
                        + " order by Concelho;", nativeQuery = true)
        List<DenunciaPorAno_Concelho_TipoCrime> getEstatisticaDenunciaPorAno_Concelho_TipoCrime(
                        @Param("ano") Integer ano, @Param("Concelho") Collection<Integer> Concelho,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, Concelho, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_concelho_tipoqueixa"
                        + " where Ano = :ano and Concelho in :Concelho and TipoQueixa in :tipoQueixa  "
                        + " order by Concelho;", nativeQuery = true)
        List<DenunciaPorAno_Concelho_TipoQueixa> getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(
                        @Param("ano") Integer ano, @Param("Concelho") Collection<Integer> Concelho,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ano, Concelho, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ano_concelho_tipocrime_genero"
                        + " where Ano = :ano and Concelho = :Concelho and TipoCrime in :tipoCrime  "
                        + " order by Concelho;", nativeQuery = true)
        List<DenunciaPorAno_Concelho_TipoCrime_Genero> getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(
                        @Param("ano") Integer ano, @Param("Concelho") Integer Concelho,
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ano, Concelho, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ano_concelho_tipocrime_tipoqueixa"
                        + " where Ano = :ano and Concelho = :Concelho and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " order by Concelho;", nativeQuery = true)
        List<DenunciaPorAno_Concelho_TipoCrime_TipoQueixa> getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(
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
        List<DenunciaPorIlha> getEstatisticaDenunciaPorIlha(@Param("Ilha") Collection<Integer> Ilha);

        @Query(value = "select Ilha, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ilha_genero"
                        + " where Ilha =:Ilha "
                        + " group by Ilha;", nativeQuery = true)
        List<DenunciaPorIlha_Genero> getEstatisticaDenunciaPorIlha_Genero(@Param("Ilha") Integer Ilha);

        @Query(value = "select Ilha, TipoCrime, Quantidade, Total, Percentagem from view_por_ilha_tipocrime"
                        + " where Ilha = :Ilha and TipoCrime in :tipoCrime "
                        + " group by TipoCrime"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorIlha_TipoCrime> getEstatisticaDenunciaPorIlha_TipoCrime(
                        @Param("Ilha") Integer Ilha, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ilha, TipoQueixa, Quantidade, Total, Percentagem from view_por_ilha_tipoqueixa"
                        + " where Ilha = :Ilha and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorIlha_TipoQueixa> getEstatisticaDenunciaPorIlha_TipoQueixa(
                        @Param("Ilha") Integer Ilha, @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Ilha, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_ilha_tipocrime_genero"
                        + " where  Ilha = :Ilha and TipoCrime in :tipoCrime  "
                        + " group by TipoCrime;", nativeQuery = true)
        List<DenunciaPorIlha_TipoCrime_Genero> getEstatisticaDenunciaPorIlha_TipoCrime_Genero(
                        @Param("Ilha") Integer Ilha, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Ilha, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_ilha_tipocrime_tipoqueixa"
                        + " where Ilha = :Ilha and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa, TipoCrime"
                        + " order by TipoCrime;", nativeQuery = true)
        List<DenunciaPorIlha_TipoCrime_TipoQueixa> getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(
                        @Param("Ilha") Integer Ilha, @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR CONCELHO
        // -------------------------------------------------------------------------

        @Query(value = "select Concelho, Quantidade, Total, Percentagem from view_por_concelho"
                        + " where Concelho in :Concelho "
                        + " group by Concelho"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorConcelho> getEstatisticaDenunciaPorConcelho(
                        @Param("Concelho") Collection<Integer> Concelho);

        @Query(value = "select Concelho, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_concelho_genero"
                        + " where Concelho = :Concelho  "
                        + " group by Concelho;", nativeQuery = true)
        List<DenunciaPorConcelho_Genero> getEstatisticaDenunciaPorConcelho_Genero(
                        @Param("Concelho") Integer Concelho);

        @Query(value = "select Concelho, TipoCrime, Quantidade, Total, Percentagem from view_por_concelho_tipocrime"
                        + " where Concelho = :Concelho and TipoCrime in :tipoCrime "
                        + " group by TipoCrime"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorConcelho_TipoCrime> getEstatisticaDenunciaPorConcelho_TipoCrime(
                        @Param("Concelho") Integer Concelho, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Concelho, TipoQueixa, Quantidade, Total, Percentagem from view_por_concelho_tipoqueixa"
                        + " where Concelho = :Concelho and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorConcelho_TipoQueixa> getEstatisticaDenunciaPorConcelho_TipoQueixa(
                        @Param("Concelho") Integer Concelho, @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        @Query(value = "select Concelho, TipoCrime, QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_concelho_tipocrime_genero"
                        + " where  Concelho = :Concelho and TipoCrime in :tipoCrime  "
                        + " group by Concelho, TipoCrime"
                        + " order by Concelho;", nativeQuery = true)
        List<DenunciaPorConcelho_TipoCrime_Genero> getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(
                        @Param("Concelho") Integer Concelho, @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select Concelho, TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_concelho_tipocrime_tipoqueixa"
                        + " where Concelho = :Concelho and TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa, TipoCrime"
                        + " order by TipoCrime;", nativeQuery = true)
        List<DenunciaPorConcelho_TipoCrime_TipoQueixa> getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(
                        @Param("Concelho") Integer Concelho, @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR GENERO
        // -------------------------------------------------------------------------

        @Query(value = "select QuantidadeFeminino, QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_genero"
                        + " group by QuantidadeFeminino, QuantidadeMasculino;", nativeQuery = true)
        List<DenunciaPorGenero> getEstatisticaDenunciaPorGenero();

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR TIPO DE CRIME
        // -------------------------------------------------------------------------

        @Query(value = "select TipoCrime, Quantidade, Total, Percentagem from view_por_tipocrime"
                        + " where TipoCrime in :tipoCrime "
                        + " group by TipoCrime"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorTipoCrime> getEstatisticaDenunciaPorTipoCrime(
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select TipoCrime, QuantidadeFeminino,QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_tipocrime_genero"
                        + " where TipoCrime in :tipoCrime "
                        + " group by TipoCrime;", nativeQuery = true)
        List<DenunciaPorTipoCrime_Genero> getEstatisticaDenunciaPorTipoCrime_Genero(
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

        @Query(value = "select TipoCrime, TipoQueixa, Quantidade, Total, Percentagem from view_por_tipocrime_tipoqueixa"
                        + " where TipoCrime in :tipoCrime and TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa, TipoCrime"
                        + " order by TipoCrime;", nativeQuery = true)
        List<DenunciaPorTipoCrime_TipoQueixa> getEstatisticaDenunciaPorTipoCrime_TipoQueixa(
                        @Param("tipoCrime") Collection<Integer> tipoCrime,
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR TIPO DE QUEIXA
        // -------------------------------------------------------------------------

        @Query(value = "select TipoQueixa, Quantidade, Total, Percentagem from view_por_tipoqueixa"
                        + " where TipoQueixa in :tipoQueixa "
                        + " group by TipoQueixa"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorTipoQueixa> getEstatisticaDenunciaPorTipoQueixa(
                        @Param("tipoQueixa") Collection<Integer> tipoQueixa);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR FAIXA ETÁRIA
        // -------------------------------------------------------------------------

        @Query(value = "select Faixa_etaria, Quantidade, Total, Percentagem from view_por_faixaetaria"
                        + " group by Faixa_etaria"
                        + " order by Quantidade desc;", nativeQuery = true)
        List<DenunciaPorFaixaEtaria> getEstatisticaDenunciaPorFaixaEtaria();

        @Query(value = "select Faixa_etaria, QuantidadeFeminino,QuantidadeMasculino, QuantidadeAnonimo, Total, PercentagemFeminino, PercentagemMasculino, PercentagemAnonimo from view_por_faixaetaria_genero"
                        + " group by Faixa_etaria;", nativeQuery = true)
        List<DenunciaPorFaixaEtaria_Genero> getEstatisticaDenunciaPorFaixaEtaria_Genero();

        @Query(value = "select Faixa_etaria, TipoCrime, Quantidade, Total, Percentagem from view_por_faixaetaria_tipocrime"
                        + " where TipoCrime in :tipoCrime "
                        + " group by Faixa_etaria, TipoCrime"
                        + " order by Faixa_etaria;", nativeQuery = true)
        List<DenunciaPorFaixaEtaria_TipoCrime> getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(
                        @Param("tipoCrime") Collection<Integer> tipoCrime);

}
