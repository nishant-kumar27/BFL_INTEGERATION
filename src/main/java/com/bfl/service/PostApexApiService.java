package com.bfl.service;

import java.util.List;
import java.util.Map;

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


public interface PostApexApiService {

	public List<MissingBarcodeDetailDTO> getMissingBarcodeDetailsRequest(String trnType);

	public void sendDataInExportedTable(ApexDataExportDTO dataToBeExported);

	public List<InvoiceReprintHistoryDTO> getInvoiceReprintHistoryRequest(String trnType);

	public List<ItemDeleteDTO> getItemDeleteRequest(String trnType);


	public List<BflEwalletLogDTO> getBflEwalletLogRequest(String trnType);

	public List<GiftCardEntryLogDTO> getGiftCardLogRequest(String trnType);

	public List<PriceDetailsAgeingDTO> getPriceDetailsAgeingRequest(String trnType);

	public List<PriceHeaderAgeingDTO> getPriceHeadAgeingRequest(String trnType);

	public List<MissingBarcodeHeaderDTO> getMissingBarcodeHeaderRequest(String trnType);
	
	public List<BflIntegrationDataDTO> getBflIntegrationDataRequest();


}
