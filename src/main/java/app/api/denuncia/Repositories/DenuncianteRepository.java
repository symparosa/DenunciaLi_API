package app.api.denuncia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.DenuncianteModel;

@Repository
@Transactional
public interface DenuncianteRepository extends JpaRepository<DenuncianteModel, Integer> {

    Boolean existsByIdAndEstado(int id, int estado);
}
