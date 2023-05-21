package app.api.denuncia.Integration.Integrate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.Domain;
import app.api.denuncia.Enums.DomainValue;
import app.api.denuncia.Integration.Dto.Anexos;
import app.api.denuncia.Integration.Dto.Denuncia;
import app.api.denuncia.Integration.Dto.Denunciante;
import app.api.denuncia.Integration.Dto.LocalizacaoMapa;
import app.api.denuncia.Models.ArquivoModel;
import app.api.denuncia.Models.DenunciaModel;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.ReprocessamentoModel;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Services.ReprocessamentoService;
import app.api.denuncia.Utilities.LocalDateTimeTypeAdapter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class IntegrateServiceImpl implements IntegrateService {

    private ReprocessamentoService reprocessamentoService;
    private DominioService dominioService;

    public IntegrateServiceImpl(ReprocessamentoService reprocessamentoService, DominioService dominioService) {
        this.reprocessamentoService = reprocessamentoService;
        this.dominioService = dominioService;
    }

    @Override
    public void Integracao(DenunciaModel denunciaModel) {

        String requestBody = createRequestBody(denunciaModel);

        if (requestBody != null) {

            System.out.println(requestBody);

            WebClient webClient = WebClient.create();
            String apiUrl = "http://localhost:8080/api/denuncia/reprocessarDenuncia";

            Mono<Response> responseMono = webClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Response.class);

            responseMono.subscribe(responseBody -> {

                if (responseBody.getResponseCode() == 0) {

                    DominioModel dom = dominioService.findByDominioAndValor(Domain.TIPO_REPROCESSAMENTO.name(),
                            DomainValue.DENUNCIA.name());

                    ReprocessamentoModel repro = new ReprocessamentoModel();

                    repro.setData_criacao(LocalDateTime.now());
                    repro.setDenuncia(denunciaModel);
                    repro.setEstado(1);
                    repro.setLast_user_change(1);
                    repro.setTipo_reprocessamento(dom);

                    ReprocessamentoModel saveRepro = reprocessamentoService.saveReprocessamento(repro);

                    if (saveRepro == null) {
                        System.out.println("ERRO AO SALVAR REPROCESSAMENTO");
                    }
                } else {
                    System.out.println("INTEGRAÇÃO COM SUCESSO");
                }
            });
        }
    }

    public String createRequestBody(DenunciaModel denu) {

        Denunciante denunciante = new Denunciante();

        if (denu.getDenunciante() != null) {

            denunciante.setNome(denu.getDenunciante().getNome());
            denunciante.setReferencia_morada(denu.getDenunciante().getReferencia_morada());
            denunciante.setApelido(denu.getDenunciante().getApelido());
            denunciante.setCodigo_postal(denu.getDenunciante().getCodigo_postal());
            denunciante.setData_nascimento(denu.getDenunciante().getData_nascimento());
            denunciante.setDoc_identificacao(denu.getDenunciante().getDocIdentificacao());
            denunciante.setFoto(denu.getDenunciante().getFoto_perfil());
            denunciante.setGenero(denu.getDenunciante().getGenero());

            if (denu.getDenunciante().getLocalizacao() != null) {
                denunciante.setZona_lugar(denu.getDenunciante().getLocalizacao().getNome());
            } else {
                denunciante.setZona_lugar(null);
            }

            LocalizacaoMapa localizacaoMapa = new LocalizacaoMapa();

            String local_m = denu.getDenunciante().getLocalizacao_mapa();

            if (local_m != null) {
                String[] local_mapa = local_m.split(",");
                localizacaoMapa.setLat(local_mapa[0]);
                localizacaoMapa.setLng(local_mapa[1]);
                denunciante.setLocal_mapa(localizacaoMapa);
            } else {
                denunciante.setLocal_mapa(null);
            }
        }

        List<Anexos> anexos = new ArrayList<>();

        if (denu.getQueixa().getArquivos() != null && !denu.getQueixa().getArquivos().isEmpty()) {

            for (ArquivoModel arquivo : denu.getQueixa().getArquivos()) {

                Anexos anexo = new Anexos();

                anexo.setArquivo(arquivo.getArquivo());
                anexo.setTipo_arquivo(arquivo.getTipo_arquivo().getValor());

                anexos.add(anexo);
            }
        }

        Denuncia denuncia = new Denuncia();

        if (denu.getQueixa() != null) {

            denuncia.setData_denuncia(denu.getData_criacao());
            denuncia.setData_ocorrencia(denu.getQueixa().getData_ocorrencia());
            denuncia.setDescricao_denuncia(denu.getQueixa().getDescricao());
            denuncia.setReferencia_local(denu.getQueixa().getReferencia_morada());
            denuncia.setGrau_parentesco(denu.getQueixa().getGrau_parentesco().getValor());
            denuncia.setTipo_crime(denu.getQueixa().getTipo_crime().getValor());
            denuncia.setTipo_queixa(denu.getQueixa().getTipo_queixa().getValor());
            denuncia.setCodigo_postal(denu.getQueixa().getCodigo_postal());
            denuncia.setDenunciante(denunciante);
            denuncia.setAnexos(anexos);

            if (denu.getQueixa().getLocalizacao() != null) {
                denuncia.setZona_lugar(denu.getQueixa().getLocalizacao().getNome());
            } else {
                denuncia.setZona_lugar(null);
            }

            LocalizacaoMapa localizacaoMapa = new LocalizacaoMapa();

            String local_m = denu.getQueixa().getLocalizacao_mapa();

            if (local_m != null) {
                String[] local_mapa = local_m.split(",");
                localizacaoMapa.setLat(local_mapa[0]);
                localizacaoMapa.setLng(local_mapa[1]);
                denuncia.setLocal_mapa(localizacaoMapa);
            } else {
                denuncia.setLocal_mapa(null);
            }
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .serializeNulls().create();
        return gson.toJson(denuncia);
    }
}
