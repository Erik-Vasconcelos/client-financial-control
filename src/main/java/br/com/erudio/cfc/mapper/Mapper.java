package br.com.erudio.cfc.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-01
 */

import org.modelmapper.ModelMapper;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-01
 */

public class Mapper {
	private static ModelMapper mapper = new ModelMapper();

	public static <O, D> D parseObject(O object, Class<D> destination) {
		return mapper.map(object, destination);
	}

	public static <O, D> List<D> parseListObject(List<O> objects, Class<D> destination) {
		List<D> list = new ArrayList<>();

		for (O o : objects) {
			list.add(mapper.map(o, destination));
		}

		return list;
	}

}
