package app.api.denuncia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.api.denuncia.Models.ReprocessamentoModel;

public interface ReprocessamentoRepository extends JpaRepository<ReprocessamentoModel, Integer> {

}
