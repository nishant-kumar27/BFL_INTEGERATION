package com.bfl.service;

import java.io.IOException;
import java.util.List;

import com.bfl.dto.InvoiceVatItemDTO;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.dto.RTLogConfigDTO;
import com.bfl.dto.SalesHeaderDto;
import com.bfl.dto.StoreDTO;
import com.bfl.dto.TransactionConfigDTO;
import com.bfl.dto.VATDTO;

public interface ISalesDataService {

	List<SalesHeaderDto> getSalesData(String fromDate) throws IOException;
	
	public StoreDTO getStoreDetailsFromStoreId(String storeId);

	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode, String row);
	
	public void sendDataInExportedTable(TransactionConfigDTO transactionConfigDTO);

	public List<RTLogConfigDTO> getDataFromRtlogConfig(String locCode, String tranType);
	
	public List<RTLogConfigDTO> getLatestDataFromRtlogConfig();

	public ItemMasterDTO getItemMasterDate(String itemCode);

	public void setRTLogConfigDTO(List<RTLogConfigDTO> rtlogData);

	public VATDTO getVatDetails(String vat_REGION, int vatPer);
	
	public String getLastTransactionNo(String registerNo);
	
	public String getOriginaTransactionNo(String invoiceNo);
	
}