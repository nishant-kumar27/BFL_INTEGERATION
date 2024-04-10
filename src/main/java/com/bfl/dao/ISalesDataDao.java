package com.bfl.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bfl.dto.CreditCardDTO;
import com.bfl.dto.CreditNoteHistoryDTO;
import com.bfl.dto.InvoiceVatItemDTO;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.dto.PaymentsDto;
import com.bfl.dto.RTLogConfigDTO;
import com.bfl.dto.SalesDetailsDto;
import com.bfl.dto.SalesHeaderDto;
import com.bfl.dto.StoreDTO;
import com.bfl.dto.TransactionConfigDTO;
import com.bfl.dto.VATDTO;

@Repository
public interface ISalesDataDao {
	
	public List<SalesHeaderDto> getSalesData(String fromDate) throws IOException;
	
	public List<StoreDTO> getStoreDetailsFromStoreId(String storeId);

	public List<SalesDetailsDto> getSalesDetailsData(String invoiceNo);

	List<PaymentsDto> getPaymentsData(String invoiceNo);
	
	public List<CreditNoteHistoryDTO> getCreditNoteHistory(String invoiceNo);
	
	public List<CreditNoteHistoryDTO> getCreditNoteHistoryForSales(String invoiceNo);

	ItemMasterDTO getItemMasterDate(String itemCode);

	public void sendDataInExportedTable(TransactionConfigDTO transactionConfigDTO);

	public List<RTLogConfigDTO> getDataFromRtlogConfig(String locCode, String tranType);
	
	public List<RTLogConfigDTO> getLatestDataFromRtlogConfig();
	
	public List<SalesHeaderDto> getStationId(String cashierId, String invoiceDate);

	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode, String row);

	public String getNextPayRollTransSequence();

	
	public List<VATDTO> getVatDetails(String vat_REGION, int vatPer);
	
	public String getLastTransactionNo(String registerNo);
	
	public String getOriginaTransactionNo(String invoiceNo);

	public List<CreditCardDTO> getCreditCardData(String invoiceNo);
	
	public void setRTLogConfigDTO(List<RTLogConfigDTO> rtlogData);

}
