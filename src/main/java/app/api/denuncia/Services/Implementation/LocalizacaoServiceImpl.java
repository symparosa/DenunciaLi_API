package app.api.denuncia.Services.Implementation;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Dto.Localizacao;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Dto.Concelho;
import app.api.denuncia.Models.LocalizacaoModel;
import app.api.denuncia.Repositories.LocalizacaoRepository;
import app.api.denuncia.Services.LocalizacaoService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class LocalizacaoServiceImpl implements LocalizacaoService {

    private LocalizacaoRepository localRepository;

    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public LocalizacaoServiceImpl(LocalizacaoRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public Response listarLocalizacoes(String concelho) {

        gf.clearList(msg);

        try {

            if (localRepository.count() > 0) {

                String metodo = "listar";

                List<Object[]> results = localRepository.getLocalizacao(concelho);
                List<Localizacao> listas = new ArrayList<>();

                for (Object[] result : results) {
                    Localizacao local = new Localizacao();
                    local.setId_localidade((String) result[0]);
                    local.setNome_localidade((String) result[1]);
                    local.setNome_norm_localidade((String) result[2]);

                    listas.add(local);
                }

                return gf.validateGetListMsg(metodo, listas);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response getIlhas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response getConcelhos() {

        gf.clearList(msg);

        try {

            if (localRepository.count() > 0) {

                String metodo = "listar";

                List<Object[]> results = localRepository.getConcelhos();
                List<Concelho> listas = new ArrayList<>();

                for (Object[] result : results) {
                    Concelho local = new Concelho();
                    local.setId_concelho((String) result[0]);
                    local.setNome_concelho((String) result[1]);
                    local.setNome_norm_concelho((String) result[2]);

                    listas.add(local);
                }
                return gf.validateGetListMsg(metodo, listas);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public Boolean existsLocalizacao(LocalizacaoModel local) {

        if (local != null && localRepository.existsById(local.getId())) {
            return true;
        }
        return false;
    }
}
