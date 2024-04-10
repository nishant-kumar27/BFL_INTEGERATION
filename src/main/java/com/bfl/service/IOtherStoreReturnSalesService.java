package com.bfl.service;

import java.io.IOException;
import java.util.List;

import com.bfl.dto.InvoiceVatItemDTO;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.dto.RTLogConfigDTO;
import com.bfl.dto.SalesHeaderDto;
import com.bfl.dto.StoreDTO;

public interface IOtherStoreReturnSalesService {

	public List<SalesHeaderDto> getOtherStoreReturnSalesData(String fromDate) throws IOException;
	
	public StoreDTO getStoreDetials(String storeName);
	
	public StoreDTO getStoreFromStoreId(String storeId) throws Exception;

	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode);

	public List<RTLogConfigDTO> getDataFromRtlogConfig();

	public ItemMasterDTO getItemMasterDate(String itemCode);


}
