package br.com.erudio.cfc.model.enums;

import lombok.AllArgsConstructor;

/**
 * @author Erik Vasconcelos
 * @since 2024-01-31
 */

@AllArgsConstructor
public enum Gender {

	MALE(1, "Masculino"), FEMALE(2, "Feminino");

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

	public static Gender toEnum(Integer code) {
		if (code == null) {
			throw new IllegalArgumentException("Not possible code convert to Gender why code is null");
		}

		for (Gender gender : Gender.values()) {
			if (code.equals(gender.getCode())) {
				return gender;
			}
		}

		throw new IllegalArgumentException("The code is invalid: " + code);
	}

}
