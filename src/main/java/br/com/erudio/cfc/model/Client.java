package br.com.erudio.cfc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.erudio.cfc.model.enums.ClientStatus;
import br.com.erudio.cfc.model.enums.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
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
@Table(name = "client")
public class Client implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String userName;

	@Column(nullable = false)
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "client_permission", joinColumns = @JoinColumn(name = "id_client"), inverseJoinColumns = @JoinColumn(name = "id_permission"))
	private List<Permission> permissions;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private LocalDate birthDate;

	@Column(length = 11)
	private String telephone;

	@Column(nullable = false)
	private Gender gender;

	@Column(nullable = false)
	private ClientStatus status;

	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Address> addresses;

	public List<String> getRoles() {
		return permissions.stream().map(p -> p.getDescription()).toList();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissions;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
