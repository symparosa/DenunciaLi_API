package app.api.denuncia.Integration.Integrate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Integration.Dto.Anexos;
import app.api.denuncia.Integration.Dto.Denuncia;
import app.api.denuncia.Integration.Dto.Denunciante;
import app.api.denuncia.Models.ArquivoModel;
import app.api.denuncia.Models.DenunciaModel;
import app.api.denuncia.Utilities.LocalDateTimeTypeAdapter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class IntegrateServiceImpl implements IntegrateService {

    @Override
    public void Integracao(DenunciaModel denunciaModel) {

        String requestBody = createRequestBody(denunciaModel);

        if (requestBody != null) {

            System.out.println(requestBody);

            WebClient webClient = WebClient.create();
            String apiUrl = "https://api.example.com/endpoint";

            Mono<Response> responseMono = webClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Response.class);

            responseMono.subscribe(responseBody -> {
                // Faz algo com a resposta
                System.out.println(responseBody);
            });
        }
    }

    public String createRequestBody(DenunciaModel denu) {

        Denunciante denunciante = new Denunciante();

        denunciante.setNome(denu.getDenunciante().getNome());
        denunciante.setZona_lugar(denu.getDenunciante().getLocalizacao().getNome());
        denunciante.setReferencia_morada(denu.getDenunciante().getReferencia_morada());
        denunciante.setLocal_mapa(denu.getDenunciante().getLocalizacao_mapa());
        denunciante.setApelido(denu.getDenunciante().getApelido());
        denunciante.setCodigo_postal(denu.getDenunciante().getCodigo_postal());
        denunciante.setData_nascimento(denu.getDenunciante().getData_nascimento());
        denunciante.setDoc_identificacao(denu.getDenunciante().getDocIdentificacao());
        denunciante.setFoto(denu.getDenunciante().getFoto_perfil());
        denunciante.setGenero(denu.getDenunciante().getGenero());

        List<Anexos> anexos = new ArrayList<>();

        if(denu.getQueixa().getArquivos() != null && !denu.getQueixa().getArquivos().isEmpty()){

            for (ArquivoModel arquivo : denu.getQueixa().getArquivos()) {

                Anexos anexo = new Anexos();
    
                anexo.setArquivo(arquivo.getArquivo());
                anexo.setTipo_arquivo(arquivo.getTipo_arquivo().getValor());
    
                anexos.add(anexo);
            }
        }

        Denuncia denuncia = new Denuncia();

        denuncia.setData_denuncia(denu.getData_criacao());
        denuncia.setData_ocorrencia(denu.getQueixa().getData_ocorrencia());
        denuncia.setDescricao_denuncia(denu.getQueixa().getDescricao());
        denuncia.setLocal_mapa(denu.getQueixa().getLocalizacao_mapa());
        denuncia.setReferencia_local(denu.getQueixa().getReferencia_morada());
        denuncia.setGrau_parentesco(denu.getQueixa().getGrau_parentesco().getValor());
        denuncia.setTipo_crime(denu.getQueixa().getTipo_crime().getValor());
        denuncia.setTipo_queixa(denu.getQueixa().getTipo_queixa().getValor());
        denuncia.setZona_lugar(denu.getQueixa().getLocalizacao().getNome());
        denuncia.setCodigo_postal(denu.getQueixa().getCodigo_postal());
        denuncia.setDenunciante(denunciante);
        denuncia.setAnexos(anexos);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();
        return gson.toJson(denuncia);
    }
}
