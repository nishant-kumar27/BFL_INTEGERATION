package com.bfl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
import com.bfl.dto.StoreConfigDTO;

@Repository
public interface IFoundationDataDao {

	public String getLastProcessingTimestamp(Long jobId);


	public void updateLastProcessingTimestamp(String timestamp, Long jobId, String jobName);
	

	public void insertLastProcessingTimestamp(String timestamp, Long jobId, String jobName);
	


	public void persistStoresData(StoreConfigDTO storeDto);
	
	public void updateStoresData(StoreConfigDTO storeDto);


	public List<MissingBarcodeDetailDTO> getMissingBarcodeDetailsRequest(String trnType);

	void sendDataInExportedTable(ApexDataExportDTO dataToBeExported);

	public List<InvoiceReprintHistoryDTO> getInvoiceReprintHistoryRequest(String trnType);

	public List<ItemDeleteDTO> getItemDeleteRequest(String trnType);


	public List<BflEwalletLogDTO> getBflEwalletLogRequest(String trnType);

	public List<GiftCardEntryLogDTO> getGiftCardLogRequest(String trnType);

	public List<PriceDetailsAgeingDTO> getPriceDetailsAgeingRequest(String trnType);


	public List<PriceHeaderAgeingDTO> getPriceHeadAgeingRequest(String trnType);

	public List<MissingBarcodeHeaderDTO> getMissingBarcodeHeaderRequest(String trnType);
	
	public List<BflIntegrationDataDTO> getBflIntegrationDataRequest();


	public List<StoreConfigDTO> getStoreDTO(String storeId);



}
