package com.bfl.dao;

import java.util.List;

import com.bfl.dto.CloseShopDTO;
import com.bfl.dto.DailyStoreClosingDTO;

public interface IDailyStoreClosingDao {
	
	public List<DailyStoreClosingDTO> getDailyStoreClosingDetails(String fromDate);

	public List<CloseShopDTO> getStoreClosingInfo(String fromDate);

	public String getTotalTransactionsCount(String sourceOFDate);
	
}
