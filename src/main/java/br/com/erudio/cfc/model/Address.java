package br.com.erudio.cfc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.erudio.cfc.model.enums.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Erik Vasconcelos
 * @since 2024-01-31
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "endereco")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private Integer number;
	
	@Column(nullable = false)
	private String neighborhood;
	
	@Column(nullable = false)
	private String city;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private State state;
	
	@Column(nullable = false, length = 8)
	private String postalCode;
	private String complement;
	
	@JsonIgnore
	@ManyToOne
	private Client client;

}
