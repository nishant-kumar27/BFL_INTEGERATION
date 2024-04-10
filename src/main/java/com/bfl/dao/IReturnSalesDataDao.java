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

@Repository
public interface IReturnSalesDataDao {

	public List<SalesHeaderDto> getReturnSalesData(String fromDate) throws IOException;

	public List<SalesDetailsDto> getReturnSalesDetailsData(String invoiceNo);

	List<PaymentsDto> getPaymentsData(String invoiceNo);

	ItemMasterDTO getItemMasterDate(String itemCode);

	public List<RTLogConfigDTO> getDataFromRtlogConfig();

	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode);




}
