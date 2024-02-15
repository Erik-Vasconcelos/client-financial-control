package br.com.erudio.cfc.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-13
 * 
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

	@Override
	public String getAuthority() {
		return description;
	}

}
