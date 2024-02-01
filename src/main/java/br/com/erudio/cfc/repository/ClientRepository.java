package br.com.erudio.cfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.cfc.model.Client;

/**
 * @author Erik Vasconcelos
 * @since 31/01/2024
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
