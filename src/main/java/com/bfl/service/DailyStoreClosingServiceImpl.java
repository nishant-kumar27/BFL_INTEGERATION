package com.bfl.service;

import java.util.List;

import com.bfl.dto.CloseShopDTO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfl.dao.IDailyStoreClosingDao;
import com.bfl.dto.DailyStoreClosingDTO;

@Service
public class DailyStoreClosingServiceImpl implements IDailyStoreClosingService {
	
	Logger logger = LoggerFactory.getLogger(DailyStoreClosingServiceImpl.class);
	
	@Autowired
	IDailyStoreClosingDao iDailyStoreClosingDao;
	
	@Override
	@Transactional
	public List<DailyStoreClosingDTO> getDailyStoreClosingDetails(String fromDate) {
		try {
			List<DailyStoreClosingDTO> dailyStoreClosingDetails = iDailyStoreClosingDao.getDailyStoreClosingDetails(fromDate);
			return dailyStoreClosingDetails;
		} catch (Exception e) {
			logger.error("Could not get Daily Store Closing Details : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}


	@Override
	public String getTotalTransactionsCount(String sourceOFDate) {
		return iDailyStoreClosingDao.getTotalTransactionsCount(sourceOFDate);
	}

	@Override
	public List<CloseShopDTO> getStoreClosingInfo(String fromDate) {
		return iDailyStoreClosingDao.getStoreClosingInfo(fromDate);
	}

}
