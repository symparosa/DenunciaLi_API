package app.api.denuncia.Tasks;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.api.denuncia.Constants.Status;
import app.api.denuncia.Services.DenuncianteService;
import app.api.denuncia.Services.UtilizadorService;

@Component
public class ValidarConfirmarConta {

    private Status status = new Status();
    private DenuncianteService denuncianteService;
    private UtilizadorService utilizadorService;

    public ValidarConfirmarConta(DenuncianteService denuncianteService, UtilizadorService utilizadorService) {
        this.denuncianteService = denuncianteService;
        this.utilizadorService = utilizadorService;
    }

    @Scheduled(cron = "0 0 23 L * ?") // Executar todos os fins de mês às 23:00
    public void taskValidarConfirmarConta() {

        System.out.println("Executando taskValidarConfirmarConta...");

        // denunciantes
        List<Integer> idDenun = denuncianteService.getIdUserContaNaoConfirmada();

        if (idDenun != null && !idDenun.isEmpty()) {

            for (int i = 0; i < idDenun.size(); i++) {
                denuncianteService.alterarEstado(status.getEliminado(), 1, idDenun.get(i));
            }
        }

        // utilizador
        List<Integer> idUsers = utilizadorService.getIdUserContaNaoConfirmada();

        if (idUsers != null && !idUsers.isEmpty()) {

            for (int i = 0; i < idUsers.size(); i++) {
                utilizadorService.eliminarUser(status.getEliminado(), 1, idUsers.get(i));
            }
        }
    }
}
