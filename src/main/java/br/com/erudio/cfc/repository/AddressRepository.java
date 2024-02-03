package br.com.erudio.cfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.cfc.model.Address;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-02
 * 
 */

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
