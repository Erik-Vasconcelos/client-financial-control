package br.com.erudio.cfc.model.enums;

import lombok.AllArgsConstructor;

/**
 * @author Erik Vasconcelos
 * @since 2024-01-31
 */

@AllArgsConstructor
public enum ClientStatus {

	INACTIVE(0, "Inativo"), NEW(1, "Novo"), REGULAR(2, "Regular"), FREQUENT(3, "Frequente"), PREMIUM(4, "Premium");

	private Integer code;
	private String description;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static ClientStatus toEnum(Integer code) {
		if (code == null) {
			throw new IllegalArgumentException("Not possible code convert to client status why code is null");
		}

		for (ClientStatus status : ClientStatus.values()) {
			if (code.equals(status.getCode())) {
				return status;
			}
		}

		throw new IllegalArgumentException("The code is invalid: " + code);
	}

}
