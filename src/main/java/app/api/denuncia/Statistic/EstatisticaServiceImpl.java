package app.api.denuncia.Statistic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Repositories.DenunciaRepository;
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
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class EstatisticaServiceImpl implements EstatisticaService {

    private DenunciaRepository denunciaRepository;
    private String metodo = "Listar";
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public EstatisticaServiceImpl(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA DE DENÚNCIA
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorAno(Collection<Integer> ano) {

        gf.clearList(msg);

        ArrayList<Integer> totalAnos = new ArrayList<>(ano);

        try {

            List<DenunciaPorAno> estatistica = denunciaRepository.getEstatisticaDenunciaPorAno(ano);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int anos : totalAnos) {
                    if (estatistica.stream().filter(item -> item.getAno() == anos).count() < 1) {
                        estatistica.add(addEstatisticaPorAno(anos, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_TipoQueixa(Integer ano, Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        try {

            List<DenunciaPorAno_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_TipoQueixa(ano, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposQueixa : totalTipoQueixa) {
                    if (estatistica.stream().filter(item -> item.getTipoQueixa() == tiposQueixa).count() < 1) {
                        estatistica
                                .add(addEstatisticaPorAno_TipoQueixa(ano, tiposQueixa, estatistica.get(0).getTotal()));
                    }
                }

                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Genero(Integer ano) {

        gf.clearList(msg);

        try {

            List<DenunciaPorAno_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Genero(ano);

            return gf.validateGetListMsg(metodo, estatistica);

        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E MÊS
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorAno_Mes(Integer ano, Collection<Integer> mes) {

        gf.clearList(msg);

        ArrayList<Integer> totalMeses = new ArrayList<>(mes);

        try {

            List<DenunciaPorAno_Mes> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Mes(ano, mes);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int meses : totalMeses) {
                    if (estatistica.stream().filter(item -> item.getMes() == meses).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Mes(ano, meses, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Mes_TipoCrime(Integer ano, Collection<Integer> mes,
            Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalMeses = new ArrayList<>(mes);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorAno_Mes_TipoCrime> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Mes_TipoCrime(ano, mes, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int meses : totalMeses) {
                        if (estatistica.stream()
                                .filter(item -> item.getMes() == meses && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorAno_Mes_TipoCrime e = estatistica.stream()
                                    .filter(item -> item.getMes() == meses).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Mes_TipoCrime(ano, meses, tiposCrime, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E TIPO DE CRIME
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorAno_TipoCrime(Integer ano, Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorAno_TipoCrime> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_TipoCrime(ano, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_TipoCrime(ano, tiposCrime, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_TipoCrime_Genero(Integer ano, Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorAno_TipoCrime_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_TipoCrime_Genero(ano, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_TipoCrime_Genero(ano, tiposCrime));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(Integer ano, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<DenunciaPorAno_TipoCrime_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(ano, tipoCrime, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {
                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorAno_TipoCrime_TipoQueixa e = estatistica.stream()
                                    .filter(item -> item.getTipoCrime() == tiposCrime).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(
                                    addEstatisticaPorAno_TipoCrime_TipoQueixa(ano, tiposCrime, tipoQueixas, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E FAIXA ETÁRIA
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorAno_FaixaEtaria(Integer ano) {

        gf.clearList(msg);

        List<String> totalFaixaEtaria = faixaEtariaList();

        try {

            List<DenunciaPorAno_FaixaEtaria> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_FaixaEtaria(ano);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (String faixaEtaria : totalFaixaEtaria) {
                    if (estatistica.stream().filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).count() < 1) {
                        estatistica
                                .add(addEstatisticaPorAno_FaixaEtaria(ano, faixaEtaria, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(Integer ano) {

        gf.clearList(msg);

        List<String> totalFaixaEtaria = faixaEtariaList();

        try {

            List<DenunciaPorAno_FaixaEtaria_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(ano);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (String faixaEtaria : totalFaixaEtaria) {
                    if (estatistica.stream().filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_FaixaEtaria_Genero(ano, faixaEtaria));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(Integer ano, Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        List<String> totalFaixaEtaria = faixaEtariaList();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorAno_FaixaEtaria_TipoCrime> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(ano, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (String faixaEtaria : totalFaixaEtaria) {
                        if (estatistica.stream().filter(
                                item -> item.getFaixa_etaria().equals(faixaEtaria) && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorAno_FaixaEtaria_TipoCrime e = estatistica.stream()
                                    .filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(
                                    addEstatisticaPorAno_FaixaEtaria_TipoCrime(ano, faixaEtaria, tiposCrime, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E ILHA
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorAno_Ilha(Integer ano, Collection<Integer> Ilhas) {

        gf.clearList(msg);

        ArrayList<Integer> totalIlhas = new ArrayList<>(Ilhas);

        try {

            List<DenunciaPorAno_Ilha> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha(ano, Ilhas);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int ilha : totalIlhas) {
                    if (estatistica.stream().filter(item -> item.getIdIlha() == ilha).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Ilha(ano, ilha, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Ilha_Genero(Integer ano, Collection<Integer> Ilhas) {

        gf.clearList(msg);

        ArrayList<Integer> totalIlhas = new ArrayList<>(Ilhas);

        try {

            List<DenunciaPorAno_Ilha_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_Genero(ano, Ilhas);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int ilha : totalIlhas) {
                    if (estatistica.stream().filter(item -> item.getIdIlha() == ilha).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Ilha_Genero(ano, ilha));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Ilha_TipoCrime(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalIlha = new ArrayList<>(Ilhas);
        try {

            List<DenunciaPorAno_Ilha_TipoCrime> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_TipoCrime(ano, Ilhas, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int ilha : totalIlha) {
                        if (estatistica.stream()
                                .filter(item -> item.getIdIlha() == ilha && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorAno_Ilha_TipoCrime e = estatistica.stream()
                                    .filter(item -> item.getIdIlha() == ilha).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Ilha_TipoCrime(ano, ilha, tiposCrime, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        ArrayList<Integer> totalIlha = new ArrayList<>(Ilhas);

        try {

            List<DenunciaPorAno_Ilha_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(ano, Ilhas, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposQueixa : totalTipoQueixa) {

                    for (int ilha : totalIlha) {
                        if (estatistica.stream()
                                .filter(item -> item.getIdIlha() == ilha && item.getTipoQueixa() == tiposQueixa)
                                .count() < 1) {
                            DenunciaPorAno_Ilha_TipoQueixa e = estatistica.stream()
                                    .filter(item -> item.getIdIlha() == ilha).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Ilha_TipoQueixa(ano, ilha, tiposQueixa, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorAno_Ilha_TipoCrime_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(ano, Ilha, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Ilha_TipoCrime_Genero(ano, Ilha, tiposCrime));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<DenunciaPorAno_Ilha_TipoCrime_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(ano, Ilha, tipoCrime, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {

                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorAno_Ilha_TipoCrime_TipoQueixa e = estatistica.stream()
                                    .filter(item -> item.getTipoCrime() == tiposCrime).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Ilha_TipoCrime_TipoQueixa(ano, Ilha, tiposCrime,
                                    tipoQueixas, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E CONCELHO
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorAno_Concelho(Integer ano, Collection<Integer> Concelhos) {

        gf.clearList(msg);

        ArrayList<Integer> totalConcelhos = new ArrayList<>(Concelhos);

        try {

            List<DenunciaPorAno_Concelho> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho(ano, Concelhos);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int Concelho : totalConcelhos) {
                    if (estatistica.stream().filter(item -> item.getConcelho() == Concelho).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Concelho(ano, Concelho, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Concelho_Genero(Integer ano, Collection<Integer> Concelhos) {

        gf.clearList(msg);

        ArrayList<Integer> totalConcelhos = new ArrayList<>(Concelhos);

        try {

            List<DenunciaPorAno_Concelho_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_Genero(ano, Concelhos);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int Concelho : totalConcelhos) {
                    if (estatistica.stream().filter(item -> item.getConcelho() == Concelho).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Concelho_Genero(ano, Concelho));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Concelho_TipoCrime(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalConcelho = new ArrayList<>(Concelhos);
        try {

            List<DenunciaPorAno_Concelho_TipoCrime> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_TipoCrime(ano, Concelhos, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int Concelho : totalConcelho) {
                        if (estatistica.stream()
                                .filter(item -> item.getConcelho() == Concelho && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorAno_Concelho_TipoCrime e = estatistica.stream()
                                    .filter(item -> item.getConcelho() == Concelho).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Concelho_TipoCrime(ano, Concelho, tiposCrime, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        ArrayList<Integer> totalConcelho = new ArrayList<>(Concelhos);

        try {

            List<DenunciaPorAno_Concelho_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(ano, Concelhos, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposQueixa : totalTipoQueixa) {

                    for (int Concelho : totalConcelho) {
                        if (estatistica.stream()
                                .filter(item -> item.getConcelho() == Concelho && item.getTipoQueixa() == tiposQueixa)
                                .count() < 1) {
                            DenunciaPorAno_Concelho_TipoQueixa e = estatistica.stream()
                                    .filter(item -> item.getConcelho() == Concelho).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica
                                    .add(addEstatisticaPorAno_Concelho_TipoQueixa(ano, Concelho, tiposQueixa, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorAno_Concelho_TipoCrime_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(ano, Concelho, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Concelho_TipoCrime_Genero(ano, Concelho, tiposCrime));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<DenunciaPorAno_Concelho_TipoCrime_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(ano, Concelho, tipoCrime, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {

                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorAno_Concelho_TipoCrime_TipoQueixa e = estatistica.stream()
                                    .filter(item -> item.getTipoCrime() == tiposCrime).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Concelho_TipoCrime_TipoQueixa(ano, Concelho,
                                    tiposCrime, tipoQueixas, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ILHA
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorIlha(Collection<Integer> Ilha) {

        gf.clearList(msg);

        ArrayList<Integer> totalIlha = new ArrayList<>(Ilha);

        try {

            List<DenunciaPorIlha> estatistica = denunciaRepository.getEstatisticaDenunciaPorIlha(Ilha);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int ilhas : totalIlha) {
                    if (estatistica.stream().filter(item -> item.getIlha() == ilhas).count() < 1) {
                        estatistica.add(addEstatisticaPorIlha(ilhas, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorIlha_Genero(Integer Ilha) {

        gf.clearList(msg);

        try {

            List<DenunciaPorIlha_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_Genero(Ilha);

            return gf.validateGetListMsg(metodo, estatistica);

        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorIlha_TipoCrime(Integer Ilha, Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorIlha_TipoCrime> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_TipoCrime(Ilha, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica
                                .add(addEstatisticaPorIlha_TipoCrime(Ilha, tiposCrime, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorIlha_TipoQueixa(Integer Ilha, Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        try {

            List<DenunciaPorIlha_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_TipoQueixa(Ilha, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposQueixa : totalTipoQueixa) {
                    if (estatistica.stream().filter(item -> item.getTipoQueixa() == tiposQueixa).count() < 1) {
                        estatistica.add(
                                addEstatisticaPorIlha_TipoQueixa(Ilha, tiposQueixa, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorIlha_TipoCrime_Genero(Integer Ilha, Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorIlha_TipoCrime_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_TipoCrime_Genero(Ilha, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorIlha_TipoCrime_Genero(Ilha, tiposCrime));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(Integer Ilha, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<DenunciaPorIlha_TipoCrime_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(Ilha, tipoCrime, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {
                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorIlha_TipoCrime_TipoQueixa e = estatistica.stream()
                                    .filter(item -> item.getTipoCrime() == tiposCrime).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(
                                    addEstatisticaPorIlha_TipoCrime_TipoQueixa(Ilha, tiposCrime, tipoQueixas, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR CONCELHO
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorConcelho(Collection<Integer> Concelho) {

        gf.clearList(msg);

        ArrayList<Integer> totalConcelho = new ArrayList<>(Concelho);

        try {

            List<DenunciaPorConcelho> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho(Concelho);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int Concelhos : totalConcelho) {
                    if (estatistica.stream().filter(item -> item.getConcelho() == Concelhos).count() < 1) {
                        estatistica.add(addEstatisticaPorConcelho(Concelhos, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorConcelho_Genero(Integer Concelho) {

        gf.clearList(msg);

        try {

            List<DenunciaPorConcelho_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_Genero(Concelho);

            return gf.validateGetListMsg(metodo, estatistica);

        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorConcelho_TipoCrime(Integer Concelho, Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorConcelho_TipoCrime> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_TipoCrime(Concelho, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorConcelho_TipoCrime(Concelho, tiposCrime,
                                estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorConcelho_TipoQueixa(Integer Concelho, Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        try {

            List<DenunciaPorConcelho_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_TipoQueixa(Concelho, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposQueixa : totalTipoQueixa) {
                    if (estatistica.stream().filter(item -> item.getTipoQueixa() == tiposQueixa).count() < 1) {
                        estatistica.add(addEstatisticaPorConcelho_TipoQueixa(Concelho, tiposQueixa,
                                estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(Integer Concelho,
            Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorConcelho_TipoCrime_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(Concelho, tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorConcelho_TipoCrime_Genero(Concelho, tiposCrime));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(Integer Concelho,
            Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<DenunciaPorConcelho_TipoCrime_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(Concelho, tipoCrime, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {
                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorConcelho_TipoCrime_TipoQueixa e = estatistica.stream()
                                    .filter(item -> item.getTipoCrime() == tiposCrime).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(
                                    addEstatisticaPorConcelho_TipoCrime_TipoQueixa(Concelho, tiposCrime, tipoQueixas,
                                            total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR GENERO
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorGenero() {

        gf.clearList(msg);

        try {

            List<DenunciaPorGenero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorGenero();

            return gf.validateGetListMsg(metodo, estatistica);

        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR TIPO DE CRIME
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorTipoCrime(Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorTipoCrime> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorTipoCrime(tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorTipoCrime(tiposCrime, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorTipoCrime_Genero(Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorTipoCrime_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorTipoCrime_Genero(tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorTipoCrime_Genero(tiposCrime));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorTipoCrime_TipoQueixa(Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<DenunciaPorTipoCrime_TipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorTipoCrime_TipoQueixa(tipoCrime, tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {
                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorTipoCrime_TipoQueixa e = estatistica.stream()
                                    .filter(item -> item.getTipoCrime() == tiposCrime).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(
                                    addEstatisticaPorTipoCrime_TipoQueixa(tiposCrime, tipoQueixas, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR TIPO DE QUEIXA
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorTipoQueixa(Collection<Integer> tipoQueixa) {

        gf.clearList(msg);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        try {

            List<DenunciaPorTipoQueixa> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorTipoQueixa(tipoQueixa);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposQueixa : totalTipoQueixa) {
                    if (estatistica.stream().filter(item -> item.getTipoQueixa() == tiposQueixa).count() < 1) {
                        estatistica.add(addEstatisticaPorTipoQueixa(tiposQueixa, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR FAIXA ETÁRIA
    // -------------------------------------------------------------------------

    @Override
    public Response getEstatisticaDenunciaPorFaixaEtaria() {

        gf.clearList(msg);

        List<String> totalFaixaEtaria = faixaEtariaList();

        try {

            List<DenunciaPorFaixaEtaria> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorFaixaEtaria();

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (String faixaEtaria : totalFaixaEtaria) {
                    if (estatistica.stream().filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).count() < 1) {
                        estatistica
                                .add(addEstatisticaPorFaixaEtaria(faixaEtaria, estatistica.get(0).getTotal()));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorFaixaEtaria_Genero() {

        gf.clearList(msg);

        List<String> totalFaixaEtaria = faixaEtariaList();

        try {

            List<DenunciaPorFaixaEtaria_Genero> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorFaixaEtaria_Genero();

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (String faixaEtaria : totalFaixaEtaria) {
                    if (estatistica.stream().filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).count() < 1) {
                        estatistica.add(addEstatisticaPorFaixaEtaria_Genero(faixaEtaria));
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(Collection<Integer> tipoCrime) {

        gf.clearList(msg);

        List<String> totalFaixaEtaria = faixaEtariaList();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<DenunciaPorFaixaEtaria_TipoCrime> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(tipoCrime);

            Response rps = gf.validateGetListMsg(metodo, estatistica);

            if (rps.getResponseCode() == 1) {

                for (int tiposCrime : totalTipoCrime) {

                    for (String faixaEtaria : totalFaixaEtaria) {
                        if (estatistica.stream().filter(
                                item -> item.getFaixa_etaria().equals(faixaEtaria) && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            DenunciaPorFaixaEtaria_TipoCrime e = estatistica.stream()
                                    .filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(
                                    addEstatisticaPorFaixaEtaria_TipoCrime(faixaEtaria, tiposCrime, total));
                        }
                    }
                }
                return rps;
            }
            return rps;
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    // -------------------------------------------------------------------------
    // MÉTODOS NECESSÁRIOS
    // -------------------------------------------------------------------------

    public List<String> faixaEtariaList() {

        List<String> faixaEtaria = new ArrayList<String>();
        faixaEtaria.add("até 15 anos");
        faixaEtaria.add("16 - 19 anos");
        faixaEtaria.add("20 - 39 anos");
        faixaEtaria.add("40 - 59 anos");
        faixaEtaria.add("60 - 64 anos");
        faixaEtaria.add("65 - 79 anos");
        faixaEtaria.add("80 anos ou mais");
        faixaEtaria.add("Anónimo");

        return faixaEtaria;
    }

    public DenunciaPorAno addEstatisticaPorAno(int ano, int total) {
        DenunciaPorAno e = new DenunciaPorAno() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };

        return e;
    }

    public DenunciaPorAno_Mes addEstatisticaPorAno_Mes(int ano, int mes, int total) {
        DenunciaPorAno_Mes e = new DenunciaPorAno_Mes() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getMes() {
                return mes;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_TipoCrime addEstatisticaPorAno_TipoCrime(int ano, int tipoCrime, int total) {
        DenunciaPorAno_TipoCrime e = new DenunciaPorAno_TipoCrime() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Mes_TipoCrime addEstatisticaPorAno_Mes_TipoCrime(int ano, int mes,
            int tipoCrime, int total) {
        DenunciaPorAno_Mes_TipoCrime e = new DenunciaPorAno_Mes_TipoCrime() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getMes() {
                return mes;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_FaixaEtaria addEstatisticaPorAno_FaixaEtaria(int ano, String faixaEtaria,
            int total) {
        DenunciaPorAno_FaixaEtaria e = new DenunciaPorAno_FaixaEtaria() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public String getFaixa_etaria() {
                return faixaEtaria;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_FaixaEtaria_Genero addEstatisticaPorAno_FaixaEtaria_Genero(int ano,
            String faixaEtaria) {
        DenunciaPorAno_FaixaEtaria_Genero e = new DenunciaPorAno_FaixaEtaria_Genero() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public String getFaixa_etaria() {
                return faixaEtaria;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_FaixaEtaria_TipoCrime addEstatisticaPorAno_FaixaEtaria_TipoCrime(int ano,
            String faixaEtaria, int tipoCrime, int total) {
        DenunciaPorAno_FaixaEtaria_TipoCrime e = new DenunciaPorAno_FaixaEtaria_TipoCrime() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public String getFaixa_etaria() {
                return faixaEtaria;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_TipoCrime_Genero addEstatisticaPorAno_TipoCrime_Genero(int ano, int tipoCrime) {
        DenunciaPorAno_TipoCrime_Genero e = new DenunciaPorAno_TipoCrime_Genero() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_TipoCrime_TipoQueixa addEstatisticaPorAno_TipoCrime_TipoQueixa(int ano,
            int tipoCrime, int tipoQueixa, int total) {
        DenunciaPorAno_TipoCrime_TipoQueixa e = new DenunciaPorAno_TipoCrime_TipoQueixa() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_TipoQueixa addEstatisticaPorAno_TipoQueixa(int ano, int tipoQueixa, int total) {
        DenunciaPorAno_TipoQueixa e = new DenunciaPorAno_TipoQueixa() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Ilha addEstatisticaPorAno_Ilha(int ano, int ilha, int total) {
        DenunciaPorAno_Ilha e = new DenunciaPorAno_Ilha() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getIdIlha() {
                return ilha;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Ilha_Genero addEstatisticaPorAno_Ilha_Genero(int ano, int ilha) {
        DenunciaPorAno_Ilha_Genero e = new DenunciaPorAno_Ilha_Genero() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getIdIlha() {
                return ilha;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Ilha_TipoCrime addEstatisticaPorAno_Ilha_TipoCrime(int ano, int ilha,
            int tipoCrime, int total) {

        DenunciaPorAno_Ilha_TipoCrime e = new DenunciaPorAno_Ilha_TipoCrime() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getIdIlha() {
                return ilha;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Ilha_TipoQueixa addEstatisticaPorAno_Ilha_TipoQueixa(int ano, int ilha,
            int tipoQueixa, int total) {
        DenunciaPorAno_Ilha_TipoQueixa e = new DenunciaPorAno_Ilha_TipoQueixa() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getIdIlha() {
                return ilha;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Ilha_TipoCrime_Genero addEstatisticaPorAno_Ilha_TipoCrime_Genero(int ano,
            int ilha, int tipoCrime) {
        DenunciaPorAno_Ilha_TipoCrime_Genero e = new DenunciaPorAno_Ilha_TipoCrime_Genero() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getIdIlha() {
                return ilha;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Ilha_TipoCrime_TipoQueixa addEstatisticaPorAno_Ilha_TipoCrime_TipoQueixa(
            int ano, int ilha, int tipoCrime, int tipoQueixa, int total) {

        DenunciaPorAno_Ilha_TipoCrime_TipoQueixa e = new DenunciaPorAno_Ilha_TipoCrime_TipoQueixa() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getIdIlha() {
                return ilha;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Concelho addEstatisticaPorAno_Concelho(int ano, int Concelho, int total) {
        DenunciaPorAno_Concelho e = new DenunciaPorAno_Concelho() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Concelho_Genero addEstatisticaPorAno_Concelho_Genero(int ano, int Concelho) {
        DenunciaPorAno_Concelho_Genero e = new DenunciaPorAno_Concelho_Genero() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Concelho_TipoCrime addEstatisticaPorAno_Concelho_TipoCrime(int ano,
            int Concelho,
            int tipoCrime, int total) {

        DenunciaPorAno_Concelho_TipoCrime e = new DenunciaPorAno_Concelho_TipoCrime() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Concelho_TipoQueixa addEstatisticaPorAno_Concelho_TipoQueixa(int ano,
            int Concelho, int tipoQueixa, int total) {
        DenunciaPorAno_Concelho_TipoQueixa e = new DenunciaPorAno_Concelho_TipoQueixa() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Concelho_TipoCrime_Genero addEstatisticaPorAno_Concelho_TipoCrime_Genero(
            int ano, int Concelho, int tipoCrime) {
        DenunciaPorAno_Concelho_TipoCrime_Genero e = new DenunciaPorAno_Concelho_TipoCrime_Genero() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorAno_Concelho_TipoCrime_TipoQueixa addEstatisticaPorAno_Concelho_TipoCrime_TipoQueixa(
            int ano, int Concelho, int tipoCrime, int tipoQueixa, int total) {

        DenunciaPorAno_Concelho_TipoCrime_TipoQueixa e = new DenunciaPorAno_Concelho_TipoCrime_TipoQueixa() {

            @Override
            public int getAno() {
                return ano;
            }

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorIlha addEstatisticaPorIlha(int Ilha, int total) {
        DenunciaPorIlha e = new DenunciaPorIlha() {

            @Override
            public int getIlha() {
                return Ilha;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };

        return e;
    }

    public DenunciaPorIlha_TipoCrime addEstatisticaPorIlha_TipoCrime(int Ilha, int tipoCrime, int total) {
        DenunciaPorIlha_TipoCrime e = new DenunciaPorIlha_TipoCrime() {

            @Override
            public int getIlha() {
                return Ilha;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorIlha_TipoQueixa addEstatisticaPorIlha_TipoQueixa(int Ilha, int tipoQueixa,
            int total) {
        DenunciaPorIlha_TipoQueixa e = new DenunciaPorIlha_TipoQueixa() {

            @Override
            public int getIlha() {
                return Ilha;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorIlha_TipoCrime_Genero addEstatisticaPorIlha_TipoCrime_Genero(int Ilha,
            int tipoCrime) {
        DenunciaPorIlha_TipoCrime_Genero e = new DenunciaPorIlha_TipoCrime_Genero() {

            @Override
            public int getIlha() {
                return Ilha;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorIlha_TipoCrime_TipoQueixa addEstatisticaPorIlha_TipoCrime_TipoQueixa(int Ilha,
            int tipoCrime, int tipoQueixa, int total) {
        DenunciaPorIlha_TipoCrime_TipoQueixa e = new DenunciaPorIlha_TipoCrime_TipoQueixa() {

            @Override
            public int getIlha() {
                return Ilha;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorConcelho addEstatisticaPorConcelho(int Concelho, int total) {
        DenunciaPorConcelho e = new DenunciaPorConcelho() {

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };

        return e;
    }

    public DenunciaPorConcelho_TipoCrime addEstatisticaPorConcelho_TipoCrime(int Concelho, int tipoCrime,
            int total) {
        DenunciaPorConcelho_TipoCrime e = new DenunciaPorConcelho_TipoCrime() {

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorConcelho_TipoQueixa addEstatisticaPorConcelho_TipoQueixa(int Concelho,
            int tipoQueixa, int total) {
        DenunciaPorConcelho_TipoQueixa e = new DenunciaPorConcelho_TipoQueixa() {

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorConcelho_TipoCrime_Genero addEstatisticaPorConcelho_TipoCrime_Genero(int Concelho,
            int tipoCrime) {
        DenunciaPorConcelho_TipoCrime_Genero e = new DenunciaPorConcelho_TipoCrime_Genero() {

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorConcelho_TipoCrime_TipoQueixa addEstatisticaPorConcelho_TipoCrime_TipoQueixa(
            int Concelho,
            int tipoCrime, int tipoQueixa, int total) {
        DenunciaPorConcelho_TipoCrime_TipoQueixa e = new DenunciaPorConcelho_TipoCrime_TipoQueixa() {

            @Override
            public int getConcelho() {
                return Concelho;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorTipoCrime addEstatisticaPorTipoCrime(int tipoCrime, int total) {
        DenunciaPorTipoCrime e = new DenunciaPorTipoCrime() {

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorTipoCrime_Genero addEstatisticaPorTipoCrime_Genero(int tipoCrime) {
        DenunciaPorTipoCrime_Genero e = new DenunciaPorTipoCrime_Genero() {

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorTipoCrime_TipoQueixa addEstatisticaPorTipoCrime_TipoQueixa(
            int tipoCrime, int tipoQueixa, int total) {
        DenunciaPorTipoCrime_TipoQueixa e = new DenunciaPorTipoCrime_TipoQueixa() {

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorTipoQueixa addEstatisticaPorTipoQueixa(int tipoQueixa, int total) {
        DenunciaPorTipoQueixa e = new DenunciaPorTipoQueixa() {

            @Override
            public int getTipoQueixa() {
                return tipoQueixa;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorFaixaEtaria addEstatisticaPorFaixaEtaria(String faixaEtaria, int total) {
        DenunciaPorFaixaEtaria e = new DenunciaPorFaixaEtaria() {

            @Override
            public String getFaixa_etaria() {
                return faixaEtaria;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorFaixaEtaria_Genero addEstatisticaPorFaixaEtaria_Genero(String faixaEtaria) {
        DenunciaPorFaixaEtaria_Genero e = new DenunciaPorFaixaEtaria_Genero() {

            @Override
            public String getFaixa_etaria() {
                return faixaEtaria;
            }

            @Override
            public int getTotal() {
                return 0;
            }

            @Override
            public int getQuantidadeFeminino() {
                return 0;
            }

            @Override
            public int getQuantidadeMasculino() {
                return 0;
            }

            @Override
            public int getQuantidadeAnonimo() {
                return 0;
            }

            @Override
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemAnonimo() {
                return 0.0000f;
            }
        };
        return e;
    }

    public DenunciaPorFaixaEtaria_TipoCrime addEstatisticaPorFaixaEtaria_TipoCrime(String faixaEtaria,
            int tipoCrime, int total) {
        DenunciaPorFaixaEtaria_TipoCrime e = new DenunciaPorFaixaEtaria_TipoCrime() {

            @Override
            public String getFaixa_etaria() {
                return faixaEtaria;
            }

            @Override
            public int getTipoCrime() {
                return tipoCrime;
            }

            @Override
            public int getQuantidade() {
                return 0;
            }

            @Override
            public int getTotal() {
                return total;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA DE UTILIZADOR
    // -------------------------------------------------------------------------
}
