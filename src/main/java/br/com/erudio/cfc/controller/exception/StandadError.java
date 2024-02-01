package br.com.erudio.cfc.controller.exception;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-01
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StandadError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String message;
	private String path;
	private Instant timeStamp;

}
