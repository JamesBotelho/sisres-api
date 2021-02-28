package br.com.jmsdevel.sisresapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jmsdevel.sisresapi.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
