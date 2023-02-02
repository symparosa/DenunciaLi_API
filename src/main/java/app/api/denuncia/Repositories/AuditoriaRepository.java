package app.api.denuncia.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.AuditoriaModel;

@Repository
@Transactional
public interface AuditoriaRepository extends JpaRepository<AuditoriaModel, Integer>{
    
}
