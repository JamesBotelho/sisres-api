package br.com.jmsdevel.sisresapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jmsdevel.sisresapi.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

}
