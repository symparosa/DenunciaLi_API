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
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_MesDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_Mes_TipoCrimeDto;
import app.api.denuncia.Dto.EstatisticaDenunciaPorAno_TipoCrimeDto;
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
    public ResponseDto getEstatisticaDenunciaPorAno(Collection<String> ano) {

        ResponseDto response = new ResponseDto();

        ArrayList<String> totalAnos = new ArrayList<>(ano);

        List<String> anosFinais = new ArrayList<String>();

        try {

            List<EstatisticaDenunciaPorAnoDto> estatistica = denunciaRepository.getEstatisticaDenunciaPorAno(ano);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int i = 0; i < estatistica.size(); i++) {

                    anosFinais.add(estatistica.get(i).getAno());
                }

                final int total = estatistica.get(0).getTotal();

                for (int y = 0; y < totalAnos.size(); y++) {

                    if (!anosFinais.contains(totalAnos.get(y))) {

                        String anoNulo = totalAnos.get(y);

                        EstatisticaDenunciaPorAnoDto e = new EstatisticaDenunciaPorAnoDto() {

                        @Override
                        public String getAno() {
                        return anoNulo;
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
                        }};
                        estatistica.add(e);
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
    public ResponseDto getEstatisticaDenunciaPorAno_Mes(String ano, Collection<Integer> mes) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalMeses = new ArrayList<>(mes);

        List<Integer> mesesFinais = new ArrayList<Integer>();

        try {

            List<EstatisticaDenunciaPorAno_MesDto> estatistica = denunciaRepository.getEstatisticaDenunciaPorAno_Mes(ano, mes);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int i = 0; i < estatistica.size(); i++) {

                    mesesFinais.add(estatistica.get(i).getMes());
                }

                final int total = estatistica.get(0).getTotal();
                final String anoFixo = estatistica.get(0).getAno();

                for (int y = 0; y < totalMeses.size(); y++) {

                    if (!mesesFinais.contains(totalMeses.get(y))) {

                        Integer mesesNulo = totalMeses.get(y);
                        
                        EstatisticaDenunciaPorAno_MesDto e = new EstatisticaDenunciaPorAno_MesDto() {

                            @Override
                            public String getAno() {
                                return anoFixo;
                            }

                            @Override
                            public int getMes() {
                                return mesesNulo;
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
                        estatistica.add(e);
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
    public ResponseDto getEstatisticaDenunciaPorAno_TipoCrime(String ano, Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        List<Integer> tipoCrimeFinais = new ArrayList<Integer>();

        try {

            List<EstatisticaDenunciaPorAno_TipoCrimeDto> estatistica = denunciaRepository.getEstatisticaDenunciaPorAno_TipoCrime(ano, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int i = 0; i < estatistica.size(); i++) {

                    tipoCrimeFinais.add(estatistica.get(i).getTipoCrime());
                }

                final int total = estatistica.get(0).getTotal();
                final String anoFixo = estatistica.get(0).getAno();

                for (int y = 0; y < totalTipoCrime.size(); y++) {

                    if (!tipoCrimeFinais.contains(totalTipoCrime.get(y))) {

                        Integer tipoCrimeNulo = totalTipoCrime.get(y);
                        
                        EstatisticaDenunciaPorAno_TipoCrimeDto e = new EstatisticaDenunciaPorAno_TipoCrimeDto() {

                            @Override
                            public String getAno() {
                                return anoFixo;
                            }

                            @Override
                            public int getTipoCrime() {
                                return tipoCrimeNulo;
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
                        estatistica.add(e);
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
    public ResponseDto getEstatisticaDenunciaPorAno_Mes_TipoCrime(String ano, Collection<Integer> mes,Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        ArrayList<Integer> totalMeses = new ArrayList<>(mes);

        ArrayList<Integer> totalTipoCrime = new ArrayList<>(tipoCrime);

        List<Integer> mesesFinais = new ArrayList<Integer>();

        List<Integer> tipoCrimeFinais = new ArrayList<Integer>();

        try {

            List<EstatisticaDenunciaPorAno_Mes_TipoCrimeDto> estatistica = denunciaRepository.getEstatisticaDenunciaPorAno_Mes_TipoCrime(ano, mes, tipoCrime);

            if (estatistica != null && !estatistica.isEmpty()) {

                
                for (int i = 0; i < estatistica.size(); i++) {

                    mesesFinais.add(estatistica.get(i).getMes());
                    tipoCrimeFinais.add(estatistica.get(i).getTipoCrime());
                }

                final int total = estatistica.get(0).getTotal();
                final String anoFixo = estatistica.get(0).getAno();

                for (int y = 0; y < totalMeses.size(); y++) {

                    if (!mesesFinais.contains(totalMeses.get(y))) {

                        Integer mesesNulo = totalMeses.get(y);
                        
                        EstatisticaDenunciaPorAno_Mes_TipoCrimeDto e = new EstatisticaDenunciaPorAno_Mes_TipoCrimeDto() {

                            @Override
                            public String getAno() {
                                return anoFixo;
                            }

                            @Override
                            public int getMes() {
                                return mesesNulo;
                            }

                            @Override
                            public int getTipoCrime() {
                                return 0;
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
                        estatistica.add(e);
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
    public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria(String ano) {

        ResponseDto response = new ResponseDto();

        List<String> totalFaixaEtaria = faixaEtariaList();

        List<String> faixaEtariaFinais = new ArrayList<String>();

        try {

            List<EstatisticaDenunciaPorAno_FaixaEtariaDto> estatistica = denunciaRepository.getEstatisticaDenunciaPorAno_FaixaEtaria(ano);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int i = 0; i < estatistica.size(); i++) {

                    faixaEtariaFinais.add(estatistica.get(i).getFaixa_etaria());
                }

                final int total = estatistica.get(0).getTotal();
                final String anoFixo = estatistica.get(0).getAno();

                for (int y = 0; y < totalFaixaEtaria.size(); y++) {

                    if (!faixaEtariaFinais.contains(totalFaixaEtaria.get(y))) {

                        String faixaEtariaNulo = totalFaixaEtaria.get(y);
                        
                        EstatisticaDenunciaPorAno_FaixaEtariaDto e = new EstatisticaDenunciaPorAno_FaixaEtariaDto() {

                            @Override
                            public String getAno() {
                                return anoFixo;
                            }

                            @Override
                            public String getFaixa_etaria() {
                                return faixaEtariaNulo;
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
                        estatistica.add(e);
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
    public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(String ano) {

        ResponseDto response = new ResponseDto();

        List<String> totalFaixaEtaria = faixaEtariaList();

        List<String> faixaEtariaFinais = new ArrayList<String>();

        try {

            List<EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto> estatistica = denunciaRepository.getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(ano);

            if (estatistica != null && !estatistica.isEmpty()) {

                for (int i = 0; i < estatistica.size(); i++) {

                    faixaEtariaFinais.add(estatistica.get(i).getFaixa_etaria());
                }

                final String anoFixo = estatistica.get(0).getAno();

                for (int y = 0; y < totalFaixaEtaria.size(); y++) {

                    if (!faixaEtariaFinais.contains(totalFaixaEtaria.get(y))) {

                        String faixaEtariaNulo = totalFaixaEtaria.get(y);
                        
                        EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto e = new EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto() {

                            @Override
                            public String getAno() {
                                return anoFixo;
                            }

                            @Override
                            public String getFaixa_etaria() {
                                return faixaEtariaNulo;
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
                        estatistica.add(e);
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
    public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(String ano,Collection<Integer> tipoCrime) {

        ResponseDto response = new ResponseDto();

        List<String> faixaEtaria = faixaEtariaList();

        List<String> faixa = new ArrayList<String>();

        try {

            List<EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto> estatistica = denunciaRepository.getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(ano,tipoCrime);

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

    public DenunciaRepository getDenunciaRepository() {
        return denunciaRepository;
    }

    public void setDenunciaRepository(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }
}
