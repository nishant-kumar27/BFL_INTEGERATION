package com.bfl.service;

import java.util.List;

import com.bfl.dto.CloseShopDTO;
import com.bfl.dto.DailyStoreClosingDTO;

public interface IDailyStoreClosingService {
	
	public List<DailyStoreClosingDTO> getDailyStoreClosingDetails(String fromDate);

	public String getTotalTransactionsCount(String sourceOFDate);

	public List<CloseShopDTO> getStoreClosingInfo(String fromDate);


	
}
