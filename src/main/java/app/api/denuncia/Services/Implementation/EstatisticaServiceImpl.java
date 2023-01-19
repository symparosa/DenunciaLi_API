package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
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
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Repositories.DenunciaRepository;
import app.api.denuncia.Services.EstatisticaService;

@Service
public class EstatisticaServiceImpl implements EstatisticaService {

    private DenunciaRepository denunciaRepository;

    public EstatisticaServiceImpl(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO
    // -------------------------------------------------------------------------

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno(Collection<Integer> ano) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalAnos = new ArrayList<>(ano);

        try {

            List<EstatisticaDenunciaPorAnoDto> estatistica = denunciaRepository.getEstatisticaDenunciaPorAno(ano);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int anos : totalAnos) {
                    if (estatistica.stream().filter(item -> item.getAno() == anos).count() < 1) {
                        estatistica.add(addEstatisticaPorAno(anos, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_TipoQueixa(Integer ano, Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        try {

            List<EstatisticaDenunciaPorAno_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_TipoQueixa(ano, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposQueixa : totalTipoQueixa) {
                    if (estatistica.stream().filter(item -> item.getTipoQueixa() == tiposQueixa).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_TipoQueixa(ano, tiposQueixa));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Genero(Integer ano) {

        ResponseDto response = new ResponseDto();

        try {

            List<EstatisticaDenunciaPorAno_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Genero(ano);

            if (estatistica != null && !estatistica.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E MÊS
    // -------------------------------------------------------------------------

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Mes(Integer ano, Collection<Integer> mes) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalMeses = new ArrayList<>(mes);

        try {

            List<EstatisticaDenunciaPorAno_MesDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Mes(ano, mes);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int meses : totalMeses) {
                    if (estatistica.stream().filter(item -> item.getMes() == meses).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Mes(ano, meses, estatistica.get(0).getTotal()));
                    }
                }
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Mes_TipoCrime(Integer ano, Collection<Integer> mes,
            Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalMeses = new ArrayList<>(mes);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorAno_Mes_TipoCrimeDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Mes_TipoCrime(ano, mes, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int meses : totalMeses) {
                        if (estatistica.stream()
                                .filter(item -> item.getMes() == meses && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorAno_Mes_TipoCrimeDto e = estatistica.stream()
                                    .filter(item -> item.getMes() == meses).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Mes_TipoCrime(ano, meses, tiposCrime, total));
                        }
                    }
                }
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E TIPO DE CRIME
    // -------------------------------------------------------------------------

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_TipoCrime(Integer ano, Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorAno_TipoCrimeDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_TipoCrime(ano, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_TipoCrime(ano, tiposCrime, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_TipoCrime_Genero(Integer ano, Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorAno_TipoCrime_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_TipoCrime_Genero(ano, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_TipoCrime_Genero(ano, tiposCrime));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(Integer ano, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(ano, tipoCrime, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {
                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto e = estatistica.stream()
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

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E FAIXA ETÁRIA
    // -------------------------------------------------------------------------

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria(Integer ano) {

        ResponseDto response = new ResponseDto();

        List<String> totalFaixaEtaria = faixaEtariaList();

        try {

            List<EstatisticaDenunciaPorAno_FaixaEtariaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_FaixaEtaria(ano);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (String faixaEtaria : totalFaixaEtaria) {
                    if (estatistica.stream().filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).count() < 1) {
                        estatistica
                                .add(addEstatisticaPorAno_FaixaEtaria(ano, faixaEtaria, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(Integer ano) {

        ResponseDto response = new ResponseDto();

        List<String> totalFaixaEtaria = faixaEtariaList();

        try {

            List<EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(ano);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (String faixaEtaria : totalFaixaEtaria) {
                    if (estatistica.stream().filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_FaixaEtaria_Genero(ano, faixaEtaria));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(Integer ano, Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        List<String> totalFaixaEtaria = faixaEtariaList();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(ano, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (String faixaEtaria : totalFaixaEtaria) {
                        if (estatistica.stream().filter(
                                item -> item.getFaixa_etaria().equals(faixaEtaria) && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto e = estatistica.stream()
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

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E ILHA
    // -------------------------------------------------------------------------

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Ilha(Integer ano, Collection<Integer> Ilhas) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalIlhas = new ArrayList<>(Ilhas);

        try {

            List<EstatisticaDenunciaPorAno_IlhaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha(ano, Ilhas);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int ilha : totalIlhas) {
                    if (estatistica.stream().filter(item -> item.getIdIlha() == ilha).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Ilha(ano, ilha, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Ilha_Genero(Integer ano, Collection<Integer> Ilhas) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalIlhas = new ArrayList<>(Ilhas);

        try {

            List<EstatisticaDenunciaPorAno_Ilha_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_Genero(ano, Ilhas);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int ilha : totalIlhas) {
                    if (estatistica.stream().filter(item -> item.getIdIlha() == ilha).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Ilha_Genero(ano, ilha));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalIlha = new ArrayList<>(Ilhas);
        try {

            List<EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_TipoCrime(ano, Ilhas, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int ilha : totalIlha) {
                        if (estatistica.stream()
                                .filter(item -> item.getIdIlha() == ilha && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto e = estatistica.stream()
                                    .filter(item -> item.getIdIlha() == ilha).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Ilha_TipoCrime(ano, ilha, tiposCrime, total));
                        }
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        ArrayList<Integer> totalIlha = new ArrayList<>(Ilhas);

        try {

            List<EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(ano, Ilhas, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposQueixa : totalTipoQueixa) {

                    for (int ilha : totalIlha) {
                        if (estatistica.stream()
                                .filter(item -> item.getIdIlha() == ilha && item.getTipoQueixa() == tiposQueixa)
                                .count() < 1) {
                            EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto e = estatistica.stream()
                                    .filter(item -> item.getIdIlha() == ilha).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Ilha_TipoQueixa(ano, ilha, tiposQueixa, total));
                        }
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(ano, Ilha, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Ilha_TipoCrime_Genero(ano, Ilha, tiposCrime));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(ano, Ilha, tipoCrime, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {

                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto e = estatistica.stream()
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

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E CONCELHO
    // -------------------------------------------------------------------------

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Concelho(Integer ano, Collection<Integer> Concelhos) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalConcelhos = new ArrayList<>(Concelhos);

        try {

            List<EstatisticaDenunciaPorAno_ConcelhoDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho(ano, Concelhos);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int Concelho : totalConcelhos) {
                    if (estatistica.stream().filter(item -> item.getConcelho() == Concelho).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Concelho(ano, Concelho, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Concelho_Genero(Integer ano, Collection<Integer> Concelhos) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalConcelhos = new ArrayList<>(Concelhos);

        try {

            List<EstatisticaDenunciaPorAno_Concelho_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_Genero(ano, Concelhos);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int Concelho : totalConcelhos) {
                    if (estatistica.stream().filter(item -> item.getConcelho() == Concelho).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Concelho_Genero(ano, Concelho));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoCrime(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalConcelho = new ArrayList<>(Concelhos);
        try {

            List<EstatisticaDenunciaPorAno_Concelho_TipoCrimeDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_TipoCrime(ano, Concelhos, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int Concelho : totalConcelho) {
                        if (estatistica.stream()
                                .filter(item -> item.getConcelho() == Concelho && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorAno_Concelho_TipoCrimeDto e = estatistica.stream()
                                    .filter(item -> item.getConcelho() == Concelho).findAny().orElse(null);
                            int total = 0;
                            if (e != null) {
                                total = e.getTotal();
                            }
                            estatistica.add(addEstatisticaPorAno_Concelho_TipoCrime(ano, Concelho, tiposCrime, total));
                        }
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        ArrayList<Integer> totalConcelho = new ArrayList<>(Concelhos);

        try {

            List<EstatisticaDenunciaPorAno_Concelho_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(ano, Concelhos, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposQueixa : totalTipoQueixa) {

                    for (int Concelho : totalConcelho) {
                        if (estatistica.stream()
                                .filter(item -> item.getConcelho() == Concelho && item.getTipoQueixa() == tiposQueixa)
                                .count() < 1) {
                            EstatisticaDenunciaPorAno_Concelho_TipoQueixaDto e = estatistica.stream()
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

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorAno_Concelho_TipoCrime_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(ano, Concelho, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorAno_Concelho_TipoCrime_Genero(ano, Concelho, tiposCrime));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<EstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(ano, Concelho, tipoCrime, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {

                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixaDto e = estatistica.stream()
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

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ILHA
    // -------------------------------------------------------------------------

    @Override
    public ResponseDto getEstatisticaDenunciaPorIlha(Collection<Integer> Ilha) {
        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalIlha = new ArrayList<>(Ilha);

        try {

            List<EstatisticaDenunciaPorIlhaDto> estatistica = denunciaRepository.getEstatisticaDenunciaPorIlha(Ilha);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int ilhas : totalIlha) {
                    if (estatistica.stream().filter(item -> item.getIlha() == ilhas).count() < 1) {
                        estatistica.add(addEstatisticaPorIlha(ilhas, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorIlha_Genero(Integer Ilha) {

        ResponseDto response = new ResponseDto();

        try {

            List<EstatisticaDenunciaPorIlha_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_Genero(Ilha);

            if (estatistica != null && !estatistica.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorIlha_TipoCrime(Integer Ilha, Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorIlha_TipoCrimeDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_TipoCrime(Ilha, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica
                                .add(addEstatisticaPorIlha_TipoCrime(Ilha, tiposCrime, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorIlha_TipoQueixa(Integer Ilha, Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        try {

            List<EstatisticaDenunciaPorIlha_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_TipoQueixa(Ilha, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposQueixa : totalTipoQueixa) {
                    if (estatistica.stream().filter(item -> item.getTipoQueixa() == tiposQueixa).count() < 1) {
                        estatistica.add(addEstatisticaPorIlha_TipoQueixa(Ilha, tiposQueixa));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorIlha_TipoCrime_Genero(Integer Ilha, Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorIlha_TipoCrime_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_TipoCrime_Genero(Ilha, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorIlha_TipoCrime_Genero(Ilha, tiposCrime));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(Integer Ilha, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<EstatisticaDenunciaPorIlha_TipoCrime_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(Ilha, tipoCrime, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {
                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorIlha_TipoCrime_TipoQueixaDto e = estatistica.stream()
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

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorConcelho(Collection<Integer> Concelho) {
        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalConcelho = new ArrayList<>(Concelho);

        try {

            List<EstatisticaDenunciaPorConcelhoDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho(Concelho);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int Concelhos : totalConcelho) {
                    if (estatistica.stream().filter(item -> item.getConcelho() == Concelhos).count() < 1) {
                        estatistica.add(addEstatisticaPorConcelho(Concelhos, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorConcelho_Genero(Integer Concelho) {

        ResponseDto response = new ResponseDto();

        try {

            List<EstatisticaDenunciaPorConcelho_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_Genero(Concelho);

            if (estatistica != null && !estatistica.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorConcelho_TipoCrime(Integer Concelho, Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorConcelho_TipoCrimeDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_TipoCrime(Concelho, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorConcelho_TipoCrime(Concelho, tiposCrime,
                                estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorConcelho_TipoQueixa(Integer Concelho, Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        try {

            List<EstatisticaDenunciaPorConcelho_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_TipoQueixa(Concelho, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposQueixa : totalTipoQueixa) {
                    if (estatistica.stream().filter(item -> item.getTipoQueixa() == tiposQueixa).count() < 1) {
                        estatistica.add(addEstatisticaPorConcelho_TipoQueixa(Concelho, tiposQueixa));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(Integer Concelho,
            Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorConcelho_TipoCrime_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(Concelho, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorConcelho_TipoCrime_Genero(Concelho, tiposCrime));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(Integer Concelho,
            Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<EstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(Concelho, tipoCrime, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {
                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixaDto e = estatistica.stream()
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

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorGenero() {
        ResponseDto response = new ResponseDto();

        try {

            List<EstatisticaDenunciaPorGeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorGenero();

            if (estatistica != null && !estatistica.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorTipoCrime(Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorTipoCrimeDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorTipoCrime(tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorTipoCrime(tiposCrime, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorTipoCrime_Genero(Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorTipoCrime_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorTipoCrime_Genero(tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {
                    if (estatistica.stream().filter(item -> item.getTipoCrime() == tiposCrime).count() < 1) {
                        estatistica.add(addEstatisticaPorTipoCrime_Genero(tiposCrime));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorTipoCrime_TipoQueixa(Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);
        try {

            List<EstatisticaDenunciaPorTipoCrime_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorTipoCrime_TipoQueixa(tipoCrime, tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (int tipoQueixas : totalTipoQueixa) {
                        if (estatistica.stream().filter(
                                item -> item.getTipoQueixa() == tipoQueixas && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorTipoCrime_TipoQueixaDto e = estatistica.stream()
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

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorTipoQueixa(Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        try {

            List<EstatisticaDenunciaPorTipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorTipoQueixa(tipoQueixa);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposQueixa : totalTipoQueixa) {
                    if (estatistica.stream().filter(item -> item.getTipoQueixa() == tiposQueixa).count() < 1) {
                        estatistica.add(addEstatisticaPorTipoQueixa(tiposQueixa));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorFaixaEtaria() {

        ResponseDto response = new ResponseDto();

        List<String> totalFaixaEtaria = faixaEtariaList();

        try {

            List<EstatisticaDenunciaPorFaixaEtariaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorFaixaEtaria();

            if (estatistica != null && !estatistica.isEmpty()) {

                for (String faixaEtaria : totalFaixaEtaria) {
                    if (estatistica.stream().filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).count() < 1) {
                        estatistica
                                .add(addEstatisticaPorFaixaEtaria(faixaEtaria, estatistica.get(0).getTotal()));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorFaixaEtaria_Genero() {

        ResponseDto response = new ResponseDto();

        List<String> totalFaixaEtaria = faixaEtariaList();

        try {

            List<EstatisticaDenunciaPorFaixaEtaria_GeneroDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorFaixaEtaria_Genero();

            if (estatistica != null && !estatistica.isEmpty()) {

                for (String faixaEtaria : totalFaixaEtaria) {
                    if (estatistica.stream().filter(item -> item.getFaixa_etaria().equals(faixaEtaria)).count() < 1) {
                        estatistica.add(addEstatisticaPorFaixaEtaria_Genero(faixaEtaria));
                    }
                }

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        List<String> totalFaixaEtaria = faixaEtariaList();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        try {

            List<EstatisticaDenunciaPorFaixaEtaria_TipoCrimeDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int tiposCrime : totalTipoCrime) {

                    for (String faixaEtaria : totalFaixaEtaria) {
                        if (estatistica.stream().filter(
                                item -> item.getFaixa_etaria().equals(faixaEtaria) && item.getTipoCrime() == tiposCrime)
                                .count() < 1) {
                            EstatisticaDenunciaPorFaixaEtaria_TipoCrimeDto e = estatistica.stream()
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

                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(estatistica);
                response.setMessage(" Listar estatística sucesso.");
                return response;
            } else if (estatistica == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha estatística.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de estatística está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    public List<String> faixaEtariaList() {

        List<String> faixaEtaria = new ArrayList<String>();
        faixaEtaria.add("até 15 anos");
        faixaEtaria.add("16 - 19 anos");
        faixaEtaria.add("20 - 39 anos");
        faixaEtaria.add("40 - 59 anos");
        faixaEtaria.add("60 - 64 anos");
        faixaEtaria.add("65 - 79 anos");
        faixaEtaria.add("80 anos ou mais");

        return faixaEtaria;
    }

    public EstatisticaDenunciaPorAnoDto addEstatisticaPorAno(int ano, int total) {
        EstatisticaDenunciaPorAnoDto e = new EstatisticaDenunciaPorAnoDto() {

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

    public EstatisticaDenunciaPorAno_MesDto addEstatisticaPorAno_Mes(int ano, int mes, int total) {
        EstatisticaDenunciaPorAno_MesDto e = new EstatisticaDenunciaPorAno_MesDto() {

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

    public EstatisticaDenunciaPorAno_TipoCrimeDto addEstatisticaPorAno_TipoCrime(int ano, int tipoCrime, int total) {
        EstatisticaDenunciaPorAno_TipoCrimeDto e = new EstatisticaDenunciaPorAno_TipoCrimeDto() {

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

    public EstatisticaDenunciaPorAno_Mes_TipoCrimeDto addEstatisticaPorAno_Mes_TipoCrime(int ano, int mes,
            int tipoCrime, int total) {
        EstatisticaDenunciaPorAno_Mes_TipoCrimeDto e = new EstatisticaDenunciaPorAno_Mes_TipoCrimeDto() {

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

    public EstatisticaDenunciaPorAno_FaixaEtariaDto addEstatisticaPorAno_FaixaEtaria(int ano, String faixaEtaria,
            int total) {
        EstatisticaDenunciaPorAno_FaixaEtariaDto e = new EstatisticaDenunciaPorAno_FaixaEtariaDto() {

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

    public EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto addEstatisticaPorAno_FaixaEtaria_Genero(int ano,
            String faixaEtaria) {
        EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto e = new EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto() {

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

    public EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto addEstatisticaPorAno_FaixaEtaria_TipoCrime(int ano,
            String faixaEtaria, int tipoCrime, int total) {
        EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto e = new EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto() {

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

    public EstatisticaDenunciaPorAno_TipoCrime_GeneroDto addEstatisticaPorAno_TipoCrime_Genero(int ano, int tipoCrime) {
        EstatisticaDenunciaPorAno_TipoCrime_GeneroDto e = new EstatisticaDenunciaPorAno_TipoCrime_GeneroDto() {

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

    public EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto addEstatisticaPorAno_TipoCrime_TipoQueixa(int ano,
            int tipoCrime, int tipoQueixa, int total) {
        EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto e = new EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto() {

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

    public EstatisticaDenunciaPorAno_TipoQueixaDto addEstatisticaPorAno_TipoQueixa(int ano, int tipoQueixa) {
        EstatisticaDenunciaPorAno_TipoQueixaDto e = new EstatisticaDenunciaPorAno_TipoQueixaDto() {

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
                return 0;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public EstatisticaDenunciaPorAno_IlhaDto addEstatisticaPorAno_Ilha(int ano, int ilha, int total) {
        EstatisticaDenunciaPorAno_IlhaDto e = new EstatisticaDenunciaPorAno_IlhaDto() {

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

    public EstatisticaDenunciaPorAno_Ilha_GeneroDto addEstatisticaPorAno_Ilha_Genero(int ano, int ilha) {
        EstatisticaDenunciaPorAno_Ilha_GeneroDto e = new EstatisticaDenunciaPorAno_Ilha_GeneroDto() {

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

    public EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto addEstatisticaPorAno_Ilha_TipoCrime(int ano, int ilha,
            int tipoCrime, int total) {

        EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto e = new EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto() {

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

    public EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto addEstatisticaPorAno_Ilha_TipoQueixa(int ano, int ilha,
            int tipoQueixa, int total) {
        EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto e = new EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto() {

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

    public EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto addEstatisticaPorAno_Ilha_TipoCrime_Genero(int ano,
            int ilha, int tipoCrime) {
        EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto e = new EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto() {

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

    public EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto addEstatisticaPorAno_Ilha_TipoCrime_TipoQueixa(
            int ano, int ilha, int tipoCrime, int tipoQueixa, int total) {

        EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto e = new EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto() {

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

    public EstatisticaDenunciaPorAno_ConcelhoDto addEstatisticaPorAno_Concelho(int ano, int Concelho, int total) {
        EstatisticaDenunciaPorAno_ConcelhoDto e = new EstatisticaDenunciaPorAno_ConcelhoDto() {

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

    public EstatisticaDenunciaPorAno_Concelho_GeneroDto addEstatisticaPorAno_Concelho_Genero(int ano, int Concelho) {
        EstatisticaDenunciaPorAno_Concelho_GeneroDto e = new EstatisticaDenunciaPorAno_Concelho_GeneroDto() {

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

    public EstatisticaDenunciaPorAno_Concelho_TipoCrimeDto addEstatisticaPorAno_Concelho_TipoCrime(int ano,
            int Concelho,
            int tipoCrime, int total) {

        EstatisticaDenunciaPorAno_Concelho_TipoCrimeDto e = new EstatisticaDenunciaPorAno_Concelho_TipoCrimeDto() {

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

    public EstatisticaDenunciaPorAno_Concelho_TipoQueixaDto addEstatisticaPorAno_Concelho_TipoQueixa(int ano,
            int Concelho, int tipoQueixa, int total) {
        EstatisticaDenunciaPorAno_Concelho_TipoQueixaDto e = new EstatisticaDenunciaPorAno_Concelho_TipoQueixaDto() {

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

    public EstatisticaDenunciaPorAno_Concelho_TipoCrime_GeneroDto addEstatisticaPorAno_Concelho_TipoCrime_Genero(
            int ano, int Concelho, int tipoCrime) {
        EstatisticaDenunciaPorAno_Concelho_TipoCrime_GeneroDto e = new EstatisticaDenunciaPorAno_Concelho_TipoCrime_GeneroDto() {

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
        };
        return e;
    }

    public EstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixaDto addEstatisticaPorAno_Concelho_TipoCrime_TipoQueixa(
            int ano, int Concelho, int tipoCrime, int tipoQueixa, int total) {

        EstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixaDto e = new EstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixaDto() {

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

    public EstatisticaDenunciaPorIlhaDto addEstatisticaPorIlha(int Ilha, int total) {
        EstatisticaDenunciaPorIlhaDto e = new EstatisticaDenunciaPorIlhaDto() {

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

    public EstatisticaDenunciaPorIlha_TipoCrimeDto addEstatisticaPorIlha_TipoCrime(int Ilha, int tipoCrime, int total) {
        EstatisticaDenunciaPorIlha_TipoCrimeDto e = new EstatisticaDenunciaPorIlha_TipoCrimeDto() {

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

    public EstatisticaDenunciaPorIlha_TipoQueixaDto addEstatisticaPorIlha_TipoQueixa(int Ilha, int tipoQueixa) {
        EstatisticaDenunciaPorIlha_TipoQueixaDto e = new EstatisticaDenunciaPorIlha_TipoQueixaDto() {

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
                return 0;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public EstatisticaDenunciaPorIlha_TipoCrime_GeneroDto addEstatisticaPorIlha_TipoCrime_Genero(int Ilha,
            int tipoCrime) {
        EstatisticaDenunciaPorIlha_TipoCrime_GeneroDto e = new EstatisticaDenunciaPorIlha_TipoCrime_GeneroDto() {

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
        };
        return e;
    }

    public EstatisticaDenunciaPorIlha_TipoCrime_TipoQueixaDto addEstatisticaPorIlha_TipoCrime_TipoQueixa(int Ilha,
            int tipoCrime, int tipoQueixa, int total) {
        EstatisticaDenunciaPorIlha_TipoCrime_TipoQueixaDto e = new EstatisticaDenunciaPorIlha_TipoCrime_TipoQueixaDto() {

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

    public EstatisticaDenunciaPorConcelhoDto addEstatisticaPorConcelho(int Concelho, int total) {
        EstatisticaDenunciaPorConcelhoDto e = new EstatisticaDenunciaPorConcelhoDto() {

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

    public EstatisticaDenunciaPorConcelho_TipoCrimeDto addEstatisticaPorConcelho_TipoCrime(int Concelho, int tipoCrime,
            int total) {
        EstatisticaDenunciaPorConcelho_TipoCrimeDto e = new EstatisticaDenunciaPorConcelho_TipoCrimeDto() {

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

    public EstatisticaDenunciaPorConcelho_TipoQueixaDto addEstatisticaPorConcelho_TipoQueixa(int Concelho,
            int tipoQueixa) {
        EstatisticaDenunciaPorConcelho_TipoQueixaDto e = new EstatisticaDenunciaPorConcelho_TipoQueixaDto() {

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
                return 0;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public EstatisticaDenunciaPorConcelho_TipoCrime_GeneroDto addEstatisticaPorConcelho_TipoCrime_Genero(int Concelho,
            int tipoCrime) {
        EstatisticaDenunciaPorConcelho_TipoCrime_GeneroDto e = new EstatisticaDenunciaPorConcelho_TipoCrime_GeneroDto() {

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
        };
        return e;
    }

    public EstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixaDto addEstatisticaPorConcelho_TipoCrime_TipoQueixa(
            int Concelho,
            int tipoCrime, int tipoQueixa, int total) {
        EstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixaDto e = new EstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixaDto() {

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

    public EstatisticaDenunciaPorTipoCrimeDto addEstatisticaPorTipoCrime(int tipoCrime, int total) {
        EstatisticaDenunciaPorTipoCrimeDto e = new EstatisticaDenunciaPorTipoCrimeDto() {

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

    public EstatisticaDenunciaPorTipoCrime_GeneroDto addEstatisticaPorTipoCrime_Genero(int tipoCrime) {
        EstatisticaDenunciaPorTipoCrime_GeneroDto e = new EstatisticaDenunciaPorTipoCrime_GeneroDto() {

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
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }
        };
        return e;
    }

    public EstatisticaDenunciaPorTipoCrime_TipoQueixaDto addEstatisticaPorTipoCrime_TipoQueixa(
            int tipoCrime, int tipoQueixa, int total) {
        EstatisticaDenunciaPorTipoCrime_TipoQueixaDto e = new EstatisticaDenunciaPorTipoCrime_TipoQueixaDto() {

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

    public EstatisticaDenunciaPorTipoQueixaDto addEstatisticaPorTipoQueixa(int tipoQueixa) {
        EstatisticaDenunciaPorTipoQueixaDto e = new EstatisticaDenunciaPorTipoQueixaDto() {

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
                return 0;
            }

            @Override
            public Float getPercentagem() {
                return 0.0000f;
            }
        };
        return e;
    }

    public EstatisticaDenunciaPorFaixaEtariaDto addEstatisticaPorFaixaEtaria(String faixaEtaria, int total) {
        EstatisticaDenunciaPorFaixaEtariaDto e = new EstatisticaDenunciaPorFaixaEtariaDto() {

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

    public EstatisticaDenunciaPorFaixaEtaria_GeneroDto addEstatisticaPorFaixaEtaria_Genero(String faixaEtaria) {
        EstatisticaDenunciaPorFaixaEtaria_GeneroDto e = new EstatisticaDenunciaPorFaixaEtaria_GeneroDto() {

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
            public Float getPercentagemFeminino() {
                return 0.0000f;
            }

            @Override
            public Float getPercentagemMasculino() {
                return 0.0000f;
            }
        };
        return e;
    }

    public EstatisticaDenunciaPorFaixaEtaria_TipoCrimeDto addEstatisticaPorFaixaEtaria_TipoCrime(String faixaEtaria,
            int tipoCrime, int total) {
        EstatisticaDenunciaPorFaixaEtaria_TipoCrimeDto e = new EstatisticaDenunciaPorFaixaEtaria_TipoCrimeDto() {

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

    public DenunciaRepository getDenunciaRepository() {
        return denunciaRepository;
    }

    public void setDenunciaRepository(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }
}
