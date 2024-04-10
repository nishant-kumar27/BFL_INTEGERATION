package com.bfl.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.bfl.dao.IReturnSalesDataDao;
import com.bfl.dao.ISalesDataDao;
import com.bfl.dto.InvoiceVatItemDTO;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.dto.RTLogConfigDTO;
import com.bfl.dto.SalesDetailsDto;
import com.bfl.dto.SalesHeaderDto;

@Service
public class ReturnSalesServiceImpl implements IReturnSalesService {

	@Autowired
	IReturnSalesDataDao returnSalesDataDao;

	@Autowired
	ISalesDataService salesDataService;
	
	@Autowired
	ISalesDataDao salesDataDao;

	Logger logger = LoggerFactory.getLogger(ReturnSalesServiceImpl.class);

	@Override
	public List<SalesHeaderDto> getReturnSalesData(String fromDate) throws IOException {
		try {
			List<SalesHeaderDto> salesData = returnSalesDataDao.getReturnSalesData(fromDate);
			if(salesData != null && salesData.size() > 0) {
				for(int i = 0; i < salesData.size(); i++) {
					String invoiceNo = salesData.get(i).getSReturnNo();
					List<SalesDetailsDto> salesDetailsData = returnSalesDataDao.getReturnSalesDetailsData(invoiceNo);
//					salesData.get(i).setCreditNoteHistoryDTOs(salesDataDao.getCreditNoteHistory(invoiceNo));
					salesData.get(i).setSalesDetailsDtos(salesDetailsData);
				}
			}
			return salesData;
		} catch (DataAccessException e) {
			logger.error("Error while fetching Sales Returns Data : " + e);
			return null;
		}
	}

	@Override
	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode) {
		return returnSalesDataDao.getVatItemDetails(invoiceNo, itemCode);
	}

	@Override
	public List<RTLogConfigDTO> getDataFromRtlogConfig(){
		return returnSalesDataDao.getDataFromRtlogConfig();

	}

	@Override
	public ItemMasterDTO getItemMasterDate(String itemCode) {
		return returnSalesDataDao.getItemMasterDate(itemCode);
	}



}