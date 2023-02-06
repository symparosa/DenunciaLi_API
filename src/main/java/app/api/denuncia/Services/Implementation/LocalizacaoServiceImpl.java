package app.api.denuncia.Services.Implementation;

import org.springframework.stereotype.Service;

import app.api.denuncia.Models.LocalizacaoModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Repositories.LocalizacaoRepository;
import app.api.denuncia.Services.LocalizacaoService;

@Service
public class LocalizacaoServiceImpl implements LocalizacaoService {

    private LocalizacaoRepository localRepository;

    public LocalizacaoServiceImpl(LocalizacaoRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public ResponseModel listarLocalizacoes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseModel getIlhas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseModel getConcelhos() {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean existsLocalizacao(LocalizacaoModel local) {

        if (local != null && localRepository.existsById(local.getId())) {
            return true;
        }
        return false;
    }
}
