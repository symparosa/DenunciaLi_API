package app.api.denuncia.Services.Implementation;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Dto.Auditoria;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Repositories.AuditoriaRepository;
import app.api.denuncia.Services.AuditoriaService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    private AuditoriaRepository auditoriaRepository;

    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public AuditoriaServiceImpl(AuditoriaRepository auditoriaRepository) {
        this.auditoriaRepository = auditoriaRepository;
    }

    @Override
    public Response filtroAuditoria(String accao, String tipo_objeto, LocalDate data_inicio,
            LocalDate data_fim) {

        gf.clearList(msg);

        try {
            if (auditoriaRepository.count() > 0) {

                String metodo = "listar";

                List<Object[]> results = auditoriaRepository.filtroAuditoria(accao, "dn_t_"+tipo_objeto, data_inicio,
                        data_fim);
                List<Auditoria> auditorias = new ArrayList<>();

                for (Object[] result : results) {
                    Auditoria auditoria = new Auditoria();
                    auditoria.setAccao((String) result[0]);
                    auditoria.setData_criacao(((Timestamp) result[1]).toLocalDateTime());
                    auditoria.setEstado((Integer) result[2]);
                    auditoria.setId_objeto((Integer) result[3]);
                    auditoria.setId_utilizador((Integer) result[4]);
                    auditoria.setTipo_objeto((String) result[5]);
                    auditoria.setValor_atual((String) result[6]);
                    auditoria.setValor_novo((String) result[7]);

                    auditorias.add(auditoria);
                }

                return gf.validateGetListMsg(metodo, auditorias);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }
}
