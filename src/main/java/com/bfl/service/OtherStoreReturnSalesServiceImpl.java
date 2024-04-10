package com.bfl.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.bfl.dao.IOtherStoreReturnSalesDao;
import com.bfl.dao.ISalesDataDao;
import com.bfl.dto.InvoiceVatItemDTO;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.dto.RTLogConfigDTO;
import com.bfl.dto.SalesDetailsDto;
import com.bfl.dto.SalesHeaderDto;
import com.bfl.dto.StoreDTO;

@Service
public class OtherStoreReturnSalesServiceImpl implements IOtherStoreReturnSalesService {

	@Autowired
	IOtherStoreReturnSalesDao otherStoreReturnSalesDao;

	@Autowired
	ISalesDataService salesDataService;
	
	@Autowired
	ISalesDataDao salesDataDao;
	
	@Autowired
	IWebServiceHelper helper;

	Logger logger = LoggerFactory.getLogger(OtherStoreReturnSalesServiceImpl.class);

	@Override
	public List<SalesHeaderDto> getOtherStoreReturnSalesData(String fromDate) throws IOException {
		try {
			List<SalesHeaderDto> salesData = otherStoreReturnSalesDao.getOtherStoreReturnSalesData(fromDate);
			if(salesData != null && salesData.size() > 0) {
				for(int i = 0; i < salesData.size(); i++) {
					String invoiceNo = salesData.get(i).getSReturnNo();
					List<SalesDetailsDto> salesDetailsData = otherStoreReturnSalesDao.getOtherStoreReturnSalesDetailsData(invoiceNo);
//					salesData.get(i).setCreditNoteHistoryDTOs(salesDataDao.getCreditNoteHistory(invoiceNo));
					salesData.get(i).setSalesDetailsDtos(salesDetailsData);
					
//					List<PaymentsDto> paymentDetailsData = otherStoreReturnSalesDao.getPaymentsData(invoiceNo);
//					List<CreditCardDTO> creditCardDTOs = salesDataDao.getCreditCardData(salesData.get(i).getInvoiceDONo());
//					salesData.get(i).setCreditCardDTO(creditCardDTOs);
//					salesData.get(i).setPaymentsDto(paymentDetailsData);
				}
			}
			return salesData;
		} catch (DataAccessException e) {
			logger.error("Error while fetching Sales Other Sales Return data : " + e);
			return null;
		}
	}
	
	public StoreDTO getStoreDetials(String storeName) {
		try {
			List<StoreDTO> storeData = otherStoreReturnSalesDao.getStoreDetials(storeName);
			if(null != storeData && storeData.size() > 0) {
				return storeData.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error while fetching Sales Other Sales Return data : " + e);
			return null;
		}
	}
	
	@Override
	public StoreDTO getStoreFromStoreId(String storeId) throws Exception {
		try {
			List<StoreDTO> storeData = otherStoreReturnSalesDao.getStoreFromStoreId(storeId);
			if(null != storeData && storeData.size() > 0) {
				return storeData.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error while fetching Sales Other Sales Return data : " + e);
			return null;
		}
	}
	
	@Override
	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode) {
		return otherStoreReturnSalesDao.getVatItemDetails(invoiceNo, itemCode);
	}

	@Override
	public List<RTLogConfigDTO> getDataFromRtlogConfig(){
		return otherStoreReturnSalesDao.getDataFromRtlogConfig();

	}

	@Override
	public ItemMasterDTO getItemMasterDate(String itemCode) {
		return otherStoreReturnSalesDao.getItemMasterDate(itemCode);
	}


	/*
	public Item parseTransfersRequest(SalesHeaderDto salesData, String seqNo, String fromLoc, String toLoc) {
		
		com.bfl.gencode.store.header.request.Item item = new com.bfl.gencode.store.header.request.Item();
		
		item.setTransferNo(Long.parseLong(fromLoc + seqNo));
		
		item.setToLocation(Integer.parseInt(toLoc));
		
		item.setFromLocation(Integer.parseInt(fromLoc));
		
		item.setFromLocationType("S");
		
		item.setToLocationType("S");
		
		item.setTransferType("EG");
		
		item.setCreatedBy("Integration");
		
		item.setComments(salesData.getRemarks());
		
		List<Detail> detailsList = new ArrayList<Detail>();
		
		for(SalesDetailsDto salesDetails : salesData.getSalesDetailsDtos()) {
			Detail detail = new Detail();
			detail.setItem(salesDetails.getItemCode());
			detail.setTransferQuantity((int) Double.parseDouble(salesDetails.getQuantity()));
			detail.setInvStatus(1);
			detailsList.add(detail);
		}
		item.setDetails(detailsList);
		return item;
	}
	/*
	public GinRequest parseGinRequest(SalesHeaderDto salesData, String ginNo, String fromLoc, String toLoc) {
		
		GinRequest ginRequest = new GinRequest();
		
		ginRequest.setCollectionSize(1);
		
		List<com.bfl.gencode.merchhier.GIN.Request.Item> items = new ArrayList<com.bfl.gencode.merchhier.GIN.Request.Item>();
		
		com.bfl.gencode.merchhier.GIN.Request.Item item = new com.bfl.gencode.merchhier.GIN.Request.Item();
		
		item.setFromLocation(Long.parseLong(fromLoc));
		
		item.setToLocation(Long.parseLong(toLoc));
		
		item.setAsnNo(salesData.getSReturnNo());
		
		item.setBolNo(salesData.getSReturnNo());
		
		item.setShipDate(salesData.getSReturnDate().split(" ")[0]);
		
		item.setComments(salesData.getRemarks());
		
		List<ShipmentDetail> listOfShipmentDetails = new ArrayList<ShipmentDetail>();
		
		List<Carton> listOfCartons = new ArrayList<Carton>();
		
		List<Item__1> listOfItems = new ArrayList<Item__1>();
		
		Carton carton = new Carton();
		
		int qty = 0;
		
		carton.setCarton(salesData.getSReturnNo());
		
		ShipmentDetail shipmentDetail = new ShipmentDetail();
		
		shipmentDetail.setDistroNo(fromLoc + ginNo);
		
		shipmentDetail.setDistroDocumentType("T");
		
		for(SalesDetailsDto salesDetails : salesData.getSalesDetailsDtos()) {
			Item__1 itemDetails = new Item__1();
			itemDetails.setItem(salesDetails.getItemCode());
			itemDetails.setUnitQuantity((int) Double.parseDouble(salesDetails.getQuantity()));
			qty = qty + (int) Double.parseDouble(salesDetails.getQuantity());
			itemDetails.setFromDisposition("ATS");
			listOfItems.add(itemDetails);
		}
		carton.setItems(listOfItems);
		listOfCartons.add(carton);
		shipmentDetail.setCartons(listOfCartons);
		listOfShipmentDetails.add(shipmentDetail);
		
		item.setCartonQuantity(qty);
		item.setShipmentDetails(listOfShipmentDetails);
		items.add(item);
		ginRequest.setItems(items);
		return ginRequest;
	} */

	
}
