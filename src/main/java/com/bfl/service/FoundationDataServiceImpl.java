package com.bfl.service;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.bfl.dao.IFoundationDataDao;
import com.bfl.dto.StoreConfigDTO;

@Service
public class FoundationDataServiceImpl implements IFoundationDataService {

	Logger logger = LoggerFactory.getLogger(FoundationDataServiceImpl.class);

	@Autowired
	IFoundationDataDao foundationDataDao;
	
	@Autowired
	IItemMasterServiceHelper itemMasterServiceHelper;


	@Override
	public void persistStoresData(List<StoreConfigDTO> storeDto) {
		try {
			for(StoreConfigDTO stores : storeDto) {
				if("INSERT".equalsIgnoreCase(stores.getAction())) {
					foundationDataDao.updateStoresData(stores);
				} else if("UPDATE".equalsIgnoreCase(stores.getAction())) {
					foundationDataDao.updateStoresData(stores);
				}
			}
		} catch (Exception e) {
			logger.error("Could not persist Stores data in RMS..STORE_CONFIG_TABLE : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}
	



	@Override
	public String getLastProcessingTimestamp(Long jobId) {
		try {
			return foundationDataDao.getLastProcessingTimestamp(jobId);
		} catch (DataAccessException e) {
			return null;
		}
	}


	@Override
	public void updateLastProcessingTimestamp(String timestamp, Long jobId, String jobName) {
		foundationDataDao.updateLastProcessingTimestamp(timestamp, jobId, jobName);
	}


	@Override
	public void insertLastProcessingTimestamp(String timestamp, Long jobId,  String jobName) {
		foundationDataDao.insertLastProcessingTimestamp(timestamp, jobId, jobName);
	}


	
	@Override
	public StoreConfigDTO getStoreDTO(String storeId) {
		List<StoreConfigDTO> storeDto = foundationDataDao.getStoreDTO(storeId);
		if(null != storeDto && storeDto.size() > 0) {
			return storeDto.get(0);
		} else {
			return null;
		}
	}

	
}
