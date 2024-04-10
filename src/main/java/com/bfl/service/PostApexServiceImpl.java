package com.bfl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfl.dao.IFoundationDataDao;
import com.bfl.dao.IFoundationWMSDataDao;
import com.bfl.dto.ApexDataExportDTO;
import com.bfl.dto.BflEwalletLogDTO;
import com.bfl.dto.BflIntegrationDataDTO;
import com.bfl.dto.GiftCardEntryLogDTO;
import com.bfl.dto.InvoiceReprintHistoryDTO;
import com.bfl.dto.ItemDeleteDTO;
import com.bfl.dto.MissingBarcodeDetailDTO;
import com.bfl.dto.MissingBarcodeHeaderDTO;
import com.bfl.dto.PriceDetailsAgeingDTO;
import com.bfl.dto.PriceHeaderAgeingDTO;


@Service
public class PostApexServiceImpl implements PostApexApiService {
	
	Logger logger = LoggerFactory.getLogger(PostApexServiceImpl.class);

	
	@Autowired
	IFoundationDataDao foundationDataDao;
	
	@Autowired
	IFoundationWMSDataDao foundationWMSDataDao;
	
	@Autowired
	FoundationWMSDataServiceImpl foundationWMSDataServiceImpl;
	
	@Override
	public List<MissingBarcodeDetailDTO> getMissingBarcodeDetailsRequest(String trnType) {
		return foundationDataDao.getMissingBarcodeDetailsRequest(trnType);
	}
	
	@Override
//	@Transactional
	public void sendDataInExportedTable(ApexDataExportDTO dataToBeExported) {
		foundationDataDao.sendDataInExportedTable(dataToBeExported);
	}
	
	@Override
	public List<InvoiceReprintHistoryDTO> getInvoiceReprintHistoryRequest(String trnType) {
		return foundationDataDao.getInvoiceReprintHistoryRequest(trnType);
	}
	
	@Override
	public List<ItemDeleteDTO> getItemDeleteRequest(String trnType) {
		return foundationDataDao.getItemDeleteRequest(trnType);
	}
	

	
	@Override
	public List<BflEwalletLogDTO> getBflEwalletLogRequest(String trnType) {
		return foundationDataDao.getBflEwalletLogRequest(trnType);
	}
	
	@Override
	public List<GiftCardEntryLogDTO> getGiftCardLogRequest(String trnType) {
		return foundationDataDao.getGiftCardLogRequest(trnType);
	}
	
	@Override
	public List<PriceDetailsAgeingDTO> getPriceDetailsAgeingRequest(String trnType) {
		return foundationDataDao.getPriceDetailsAgeingRequest(trnType);
	}
	
	@Override
	public List<PriceHeaderAgeingDTO> getPriceHeadAgeingRequest(String trnType) {
		return foundationDataDao.getPriceHeadAgeingRequest(trnType);
	}
	
	@Override
	public List<MissingBarcodeHeaderDTO> getMissingBarcodeHeaderRequest(String trnType) {
		return foundationDataDao.getMissingBarcodeHeaderRequest(trnType);
	}
	
	@Override
	public List<BflIntegrationDataDTO> getBflIntegrationDataRequest() {
		return foundationDataDao.getBflIntegrationDataRequest();
	}


	
}