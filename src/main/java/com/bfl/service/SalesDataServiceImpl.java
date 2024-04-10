package com.bfl.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.bfl.dao.ISalesDataDao;
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

@Service
public class SalesDataServiceImpl implements ISalesDataService{

	@Autowired
	ISalesDataDao salesDataDao;

	@Autowired
	ISalesDataService salesDataService;

	Logger logger = LoggerFactory.getLogger(SalesDataServiceImpl.class);

	@Override
	public List<SalesHeaderDto> getSalesData(String fromDate) throws IOException {
		try {
			List<SalesHeaderDto> salesData = salesDataDao.getSalesData(fromDate);
			if(salesData != null && salesData.size() > 0) {
				for(int i = 0; i < salesData.size(); i++) {
					String invoiceNo = salesData.get(i).getInvoiceNo();
					List<SalesDetailsDto> salesDetailsData = salesDataDao.getSalesDetailsData(invoiceNo);
					List<PaymentsDto> paymentDetailsData = salesDataDao.getPaymentsData(invoiceNo);
					List<CreditCardDTO> creditCardDetails = salesDataDao.getCreditCardData(invoiceNo);
					List<CreditNoteHistoryDTO> creditNoteHistory = salesDataDao.getCreditNoteHistoryForSales(invoiceNo);
					salesData.get(i).setSalesDetailsDtos(salesDetailsData);
					salesData.get(i).setPaymentsDto(paymentDetailsData);
					salesData.get(i).setCreditCardDTO(creditCardDetails);
					salesData.get(i).setCreditNoteHistoryDTOs(creditNoteHistory);
				}
			}
			return salesData;
		}catch (DataAccessException e) {
			logger.error("Error while fetching Sales Header Data : " + e);
			return null;
		}
	}
	
	@Override
	public StoreDTO getStoreDetailsFromStoreId(String storeId) {
		try {
			List<StoreDTO> storeDetails = salesDataDao.getStoreDetailsFromStoreId(storeId);
			if(null != storeDetails && storeDetails.size() > 0) {
				return storeDetails.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode, String row) {
		return salesDataDao.getVatItemDetails(invoiceNo, itemCode, row);
	}

	@Override
	public void sendDataInExportedTable(TransactionConfigDTO transactionConfigDTO) {
		salesDataDao.sendDataInExportedTable(transactionConfigDTO);		
	}

	@Override
	public List<RTLogConfigDTO> getDataFromRtlogConfig(String locCode, String tranType) {
		return salesDataDao.getDataFromRtlogConfig(locCode, tranType);
	}
	
	@Override
	public List<RTLogConfigDTO> getLatestDataFromRtlogConfig() {
		return salesDataDao.getLatestDataFromRtlogConfig();
	}

	
	@Override
	public ItemMasterDTO getItemMasterDate(String itemCode) {
		return salesDataDao.getItemMasterDate(itemCode);
	}




	
	public void setRTLogConfigDTO(List<RTLogConfigDTO> rtlogData) {
		try {
			salesDataDao.setRTLogConfigDTO(rtlogData);
		}  catch (Exception e) {
			logger.error("Could not persist RTLOG Registers values : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}
	
	public VATDTO getVatDetails(String vat_REGION, int vatPer) {
		try {
			List<VATDTO> vatDetails = salesDataDao.getVatDetails(vat_REGION, vatPer);
			if(null != vatDetails && !vatDetails.isEmpty()) {
				return vatDetails.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("Could not get vat details : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}
	
	public String getOriginaTransactionNo(String invoiceNo) {
		return salesDataDao.getOriginaTransactionNo(invoiceNo);
	}
	
	public String getLastTransactionNo(String registerNo) {
		try {
			String tranNo = salesDataDao.getLastTransactionNo(registerNo);
			if(null != tranNo && !tranNo.trim().isEmpty()) {
				int transactionNo = Integer.parseInt(tranNo);
				if(transactionNo >= 999999) {
					tranNo = String.valueOf(1);
				} else {
					tranNo = String.valueOf(transactionNo + 1);
				}
			} else {
				tranNo = "1";
			}
			return tranNo;
		} catch (Exception e) {
			logger.error("could not able to get transaction number : " + e.getMessage());
			return "1";
		}
	}
	
}
