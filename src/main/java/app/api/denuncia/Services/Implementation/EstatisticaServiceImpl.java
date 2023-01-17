package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
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
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Repositories.DenunciaRepository;
import app.api.denuncia.Services.EstatisticaService;

@Service
public class EstatisticaServiceImpl implements EstatisticaService {

    private DenunciaRepository denunciaRepository;

    public EstatisticaServiceImpl(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

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
    public ResponseDto getEstatisticaDenunciaPorAno_TipoQueixa(Integer ano, Collection<Integer> tipoQueixa) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoQueixa = new ArrayList<>(tipoQueixa);

        try {

            List<EstatisticaDenunciaPorAno_TipoQueixaDto> estatistica = denunciaRepository
                    .getEstatisticaDenunciaPorAno_TipoQueixa(ano);

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
                            estatistica.add(addEstatisticaPorAno_Ilha_TipoQueixa(ano,ilha,tiposQueixa,total));
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
                                    estatistica.add(addEstatisticaPorAno_Ilha_TipoCrime_TipoQueixa(ano, Ilha, tiposCrime, tipoQueixas, total));
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

    public EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto addEstatisticaPorAno_Ilha_TipoQueixa(int ano, int ilha,int tipoQueixa, int total){
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

    public EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto addEstatisticaPorAno_Ilha_TipoCrime_Genero(int ano, int ilha, int tipoCrime){
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

    public EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto addEstatisticaPorAno_Ilha_TipoCrime_TipoQueixa(int ano, int ilha, int tipoCrime, int tipoQueixa, int total){

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

    public DenunciaRepository getDenunciaRepository() {
        return denunciaRepository;
    }

    public void setDenunciaRepository(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }
}
