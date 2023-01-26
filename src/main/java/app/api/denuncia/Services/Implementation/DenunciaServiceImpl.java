// package app.api.denuncia.Services.Implementation;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import app.api.denuncia.Constants.ResponseType;
// import app.api.denuncia.Dto.DenunciaOutputDto;
// import app.api.denuncia.Dto.Response.ResponseDto;
// import app.api.denuncia.Models.ArquivoModel;
// import app.api.denuncia.Models.DenunciaModel;
// import app.api.denuncia.Repositories.DenunciaRepository;
// import app.api.denuncia.Repositories.UtilizadorRepository;
// import app.api.denuncia.Services.DenunciaService;

// @Service
// public class DenunciaServiceImpl implements DenunciaService {

//     private DenunciaRepository denunciaRepository;
//     private UtilizadorRepository denuncianteRepository;

//     public DenunciaServiceImpl(DenunciaRepository denunciaRepository, UtilizadorRepository denuncianteRepository) {
//         this.denunciaRepository = denunciaRepository;
//         this.denuncianteRepository = denuncianteRepository;
//     }

//     @Override
//     public ResponseDto adicionarDenuncia(DenunciaModel denuncia) {

//         ResponseDto response = new ResponseDto();

//         try {
            
//             DenunciaModel denunciaSave = denunciaRepository.save(denuncia);

//             if (denunciaSave != null) {

//                 List<ArquivoModel> arquivo = denunciaSave.getQueixa().getArquivos();

//                 if (arquivo != null && !arquivo.isEmpty()) {

//                     for (int i = 0; i < arquivo.size(); i++) {
//                         denunciaRepository.atualizarArquivo(denunciaSave.getQueixa().getId(), arquivo.get(i).getId());
//                     }
//                 }
//                 response.setResponseCode(1);
//                 response.setResponseType(ResponseType.Sucesso);
//                 response.setObject(denuncia);
//                 response.setMessage(" Denúncia salvo com sucesso.");
//                 return response;
//             } else {
//                 response.setResponseCode(0);
//                 response.setResponseType(ResponseType.Erro);
//                 response.setObject(null);
//                 response.setMessage(" Falha ao salvar a denúncia.");
//                 return response;
//             }
//         } catch (Exception e) {
//             response.setResponseCode(0);
//             response.setResponseType(ResponseType.Erro);
//             response.setObject(null);
//             response.setMessage(" Falha no sistema.");
//             return response;
//         }
//     }

//     @Override
//     public ResponseDto listarDenunciasByUserId(int id) {

//         ResponseDto response = new ResponseDto();

//         try {

//             if (denuncianteRepository.findById(id) != null) {

//                 List<DenunciaOutputDto> listadenuncia = denunciaRepository.listarDenunciasByUserId(id);

//                 if (listadenuncia != null && !listadenuncia.isEmpty()) {
//                     response.setResponseCode(1);
//                     response.setResponseType(ResponseType.Sucesso);
//                     response.setObject(listadenuncia);
//                     response.setMessage(" Listar denúncias de utilizador com sucesso.");
//                     return response;
//                 } else if (listadenuncia == null) {
//                     response.setResponseCode(0);
//                     response.setResponseType(ResponseType.Erro);
//                     response.setObject(null);
//                     response.setMessage(" Falha ao listar denúncias de utilizador.");
//                     return response;
//                 } else {
//                     response.setResponseCode(0);
//                     response.setResponseType(ResponseType.Erro);
//                     response.setObject(null);
//                     response.setMessage(" A lista de denúncias de utilizador está vazia.");
//                     return response;
//                 }
//             } else {
//                 response.setResponseCode(0);
//                 response.setResponseType(ResponseType.Erro);
//                 response.setObject(null);
//                 response.setMessage(" utilizador não existe.");
//                 return response;
//             }
//         } catch (Exception e) {
//             response.setResponseCode(0);
//             response.setResponseType(ResponseType.Erro);
//             response.setObject(null);
//             response.setMessage(" Falha no sistema.");
//             return response;
//         }
//     }

//     @Override
//     public ResponseDto getDenunciaById(int id) {
//         ResponseDto response = new ResponseDto();

//         try {
//             DenunciaOutputDto denuncia = denunciaRepository.findById(id);

//             if (denuncia != null) {
//                 response.setResponseCode(1);
//                 response.setResponseType(ResponseType.Sucesso);
//                 response.setObject(denuncia);
//                 response.setMessage(" Denúncia encontrada com sucesso.");
//                 return response;
//             } else {
//                 response.setResponseCode(0);
//                 response.setResponseType(ResponseType.Erro);
//                 response.setObject(null);
//                 response.setMessage(" Denúncia não existe.");
//                 return response;
//             }
//         } catch (Exception e) {
//             response.setResponseCode(0);
//             response.setResponseType(ResponseType.Erro);
//             response.setObject(null);
//             response.setMessage(" Falha no sistema.");
//             return response;
//         }
//     }

//     public DenunciaRepository getDenunciaRepository() {
//         return denunciaRepository;
//     }

//     public void setDenunciaRepository(DenunciaRepository denunciaRepository) {
//         this.denunciaRepository = denunciaRepository;
//     }
// }
