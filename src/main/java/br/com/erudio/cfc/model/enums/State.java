package br.com.erudio.cfc.model.enums;

import lombok.AllArgsConstructor;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-01
 */

@AllArgsConstructor
public enum State {
	
	AM(1, "AM", "Amazonas"),
    PA(2, "PA", "Pará"),
    MT(3, "MT", "Mato Grosso"),
    MG(4, "MG", "Minas Gerais"),
    BA(5, "BA", "Bahia"),
    MS(6, "MS", "Mato Grosso do Sul"),
    GO(7, "GO", "Goiás"),
    MA(8, "MA", "Maranhão"),
    RS(9, "RS", "Rio Grande do Sul"),
    TO(10, "TO", "Tocantins"),
    PI(11, "PI", "Piauí"),
    SP(12, "SP", "São Paulo"),
    CE(13, "CE", "Ceará"),
    PR(14, "PR", "Paraná"),
    PB(15, "PB", "Paraíba"),
    PE(16, "PE", "Pernambuco"),
    SC(17, "SC", "Santa Catarina"),
    SE(18, "SE", "Sergipe"),
    AL(19, "AL", "Alagoas"),
    RN(20, "RN", "Rio Grande do Norte"),
    RO(21, "RO", "Rondônia"),
    RR(22, "RR", "Roraima"),
    AP(23, "AP", "Amapá"),
    ES(24, "ES", "Espírito Santo"),
    RJ(25, "RJ", "Rio de Janeiro"),
    AC(26, "AC", "Acre"),
    DF(27, "DF", "Distrito Federal");

	private Integer code;
	private String acronym;
	private String description;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static State toEnum(Integer code) {
		if (code == null) {
			throw new IllegalArgumentException("Not possible code convert to State why code is null");
		}

		for (State state : State.values()) {
			if (code.equals(state.getCode())) {
				return state;
			}
		}

		throw new IllegalArgumentException("The code is invalid: " + code);
	}
	
	public static State toEnumByAcronym(String acronym) {
		if (acronym == null) {
			throw new IllegalArgumentException("Not possible code convert to State why acronym is null");
		}

		for (State state : State.values()) {
			if (acronym.equalsIgnoreCase(state.getAcronym())) {
				return state;
			}
		}

		throw new IllegalArgumentException("The acronym is invalid: " + acronym);
	}

}
