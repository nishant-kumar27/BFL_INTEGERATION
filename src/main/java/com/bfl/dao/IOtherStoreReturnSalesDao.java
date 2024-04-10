package com.bfl.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bfl.dto.InvoiceVatItemDTO;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.dto.PaymentsDto;
import com.bfl.dto.RTLogConfigDTO;
import com.bfl.dto.SalesDetailsDto;
import com.bfl.dto.SalesHeaderDto;
import com.bfl.dto.StoreDTO;

@Repository
public interface IOtherStoreReturnSalesDao {

	public List<SalesHeaderDto> getOtherStoreReturnSalesData(String fromDate) throws IOException;
	
	public List<StoreDTO> getStoreDetials(String storeName) throws Exception;
	
	public List<StoreDTO> getStoreFromStoreId(String storeId) throws Exception;

	public List<SalesDetailsDto> getOtherStoreReturnSalesDetailsData(String invoiceNo);

	List<PaymentsDto> getPaymentsData(String invoiceNo);

	ItemMasterDTO getItemMasterDate(String itemCode);

	public List<RTLogConfigDTO> getDataFromRtlogConfig();

	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode);


}
