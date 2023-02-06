package app.api.denuncia.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailDetailsModel {

    private String recipient;
    private String msgBody;
    private String subject;
}
