package app.api.denuncia.Services.Implementation;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Dto.LocalizacaoDto;
import app.api.denuncia.Dto.ConcelhoDto;
import app.api.denuncia.Models.LocalizacaoModel;
import app.api.denuncia.Models.ResponseModel;
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
    public ResponseModel listarLocalizacoes(String concelho) {
        
        gf.clearList(msg);

        try {

            if (localRepository.count() > 0) {

                String metodo = "listar";

                List<LocalizacaoDto> lista = localRepository.getLocalizacao(concelho);
                return gf.validateGetListMsg(metodo, lista);

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
    public ResponseModel getIlhas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseModel getConcelhos() {

        gf.clearList(msg);

        try {

            if (localRepository.count() > 0) {

                String metodo = "listar";

                List<ConcelhoDto> lista = localRepository.getConcelhos();
                return gf.validateGetListMsg(metodo, lista);

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
