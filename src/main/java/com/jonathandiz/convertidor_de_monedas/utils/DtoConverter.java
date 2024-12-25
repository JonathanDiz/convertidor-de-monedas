package com.jonathandiz.convertidor_de_monedas.utils;

import java.util.Optional;

import com.jonathandiz.convertidor_de_monedas.dto.ExchangeRateHistoryDto;
import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;

public class DtoConverter {
	
	public static ExchangeRateHistoryDto toDTO(ExchangeRateHistoryModel history) {
		ExchangeRateHistoryDto dto = new ExchangeRateHistoryDto();
		dto.setId(history.getId());
		dto.setFromCurrency(history.getSourceCurrency());
		dto.setFromCurrency(history.getTargetCurrency());
		dto.setRate(history.getRate());
		return dto;
	}

	public static Object toDTO(Optional<ExchangeRateHistoryModel> exchangeRateHistoryById) {
		// TODO Auto-generated method stub
		return null;
	} 
}
