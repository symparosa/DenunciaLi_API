package app.api.denuncia.Tasks;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.api.denuncia.Integration.Integrate.IntegrateService;
import app.api.denuncia.Services.ReprocessamentoService;

@Component
public class Reprocessamento {

    private IntegrateService integrateService;
    private ReprocessamentoService reprocessamentoService;

    public Reprocessamento(IntegrateService integrateService, ReprocessamentoService reprocessamentoService) {
        this.integrateService = integrateService;
        this.reprocessamentoService = reprocessamentoService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // todos os dias as 00:00
    public void taskReprocessamento() {

        System.out.println("Executando taskReprocessamento...");

        // email

        List<Integer> IdEmails = reprocessamentoService.getIdEmailNaoRepro();

        if (IdEmails != null && !IdEmails.isEmpty()) {

            System.out.println("email");

            for (int i = 0; i < IdEmails.size(); i++) {
                reprocessamentoService.reprocessamentoManualEmail(IdEmails.get(i));
            }
        }

        // denúncia

        List<Integer> IdDenus = reprocessamentoService.getIdDenunciaNaoRepro();

        if (IdDenus != null && !IdDenus.isEmpty()) {

            System.out.println("denúncia");

            for (int i = 0; i < IdDenus.size(); i++) {
                integrateService.reprocessamentoManualDenuncia(IdDenus.get(i));
            }
        }
    }
}
