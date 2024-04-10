package com.bfl.service;

import java.io.IOException;
import java.util.List;

import com.bfl.dto.InvoiceVatItemDTO;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.dto.RTLogConfigDTO;
import com.bfl.dto.SalesHeaderDto;

public interface IReturnSalesService {

	List<SalesHeaderDto> getReturnSalesData(String fromDate) throws IOException;

	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode);

	public List<RTLogConfigDTO> getDataFromRtlogConfig();

	public ItemMasterDTO getItemMasterDate(String itemCode);


}
