package app.api.denuncia.Services.Implementation;

import org.springframework.stereotype.Service;

import app.api.denuncia.Models.ReprocessamentoModel;
import app.api.denuncia.Repositories.ReprocessamentoRepository;
import app.api.denuncia.Services.ReprocessamentoService;

@Service
public class ReprocessamentoServiceImpl implements ReprocessamentoService {

    private ReprocessamentoRepository reprocessamentoRepository;

    public ReprocessamentoServiceImpl(ReprocessamentoRepository reprocessamentoRepository) {
        this.reprocessamentoRepository = reprocessamentoRepository;
    }

    @Override
    public ReprocessamentoModel saveReprocessamento(ReprocessamentoModel reprocessamento) {
        return reprocessamentoRepository.save(reprocessamento);
    }
}
