package com.bfl.service;

import java.util.List;

import com.bfl.dto.StoreConfigDTO;

public interface IFoundationDataService {

	public String getLastProcessingTimestamp(Long jobId);

	public void updateLastProcessingTimestamp(String timestamp, Long jobId, String jobName);
	

	public void insertLastProcessingTimestamp(String timestamp, Long jobId, String jobName);


	public void persistStoresData(List<StoreConfigDTO> storeDto);

	
	public StoreConfigDTO getStoreDTO(String storeId);


}
