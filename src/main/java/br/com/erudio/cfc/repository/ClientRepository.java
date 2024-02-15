package br.com.erudio.cfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.cfc.model.Client;

/**
 * @author Erik Vasconcelos
 * @since 2024-01-31
 * 
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	@Query("SELECT c FROM Client c WHERE c.userName = :userName")
	public Client findByUsername(@Param("userName") String userName);
	
}
