package com.bfl.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.bfl.dto.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfl.ConfigProperties;
import com.bfl.dao.IFoundationWMSDataDao;
import com.bfl.gencode.merchhier.GroupSummaryDetails.GroupSumPurOrderDetailReport;
import com.bfl.gencode.merchhier.PartialShipment.Link;
import com.bfl.gencode.merchhier.PartialShipment.PartialShipmentResponse;
import com.bfl.gencode.merchhier.upcbarcodes.response.UpcBarcodeResponse;
import com.bfl.gencode.resa.MissingResaItems.MissingResaItemsResponse;
import com.bfl.request.FoundadtionRequest;

@Service
public class FoundationWMSDataServiceImpl implements IFoundationWMSDataService {
	
	Logger logger = LoggerFactory.getLogger(FoundationWMSDataServiceImpl.class);
	
	@Autowired
	IFoundationWMSDataDao foundationDataDao;
	
	@Autowired
	IItemMasterServiceHelper itemMasterServiceHelper;
	
	@Autowired
	IWebServiceHelper helper;

	public void persistDepartments(List<DepartmentDto> departments) {
		try {
			for(DepartmentDto deptDTO : departments) {
				foundationDataDao.updateSubClass(deptDTO);
				List<DepartmentDto> checkItemGroup = foundationDataDao.checkItemGroup(deptDTO);
				if(null == checkItemGroup || checkItemGroup.size() == 0) {
					foundationDataDao.persistDepartments(deptDTO);
				}
			}
		} catch (Exception e) {
			logger.error("Could not persist groups : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}
	
	public void persistBrands(List<BrandMasterDTO> brandMasterDTO) {
		try {
			Date entryDate = new Date();
			entryDate.setTime(0);
			for(BrandMasterDTO brands : brandMasterDTO) {
				List<BrandMasterDTO> getBrands = foundationDataDao.getBrands(brands);
				if(null == getBrands || getBrands.size() == 0) {
					foundationDataDao.persistBrands(brands, entryDate);
				}
			}
		} catch (Exception e) {
			logger.error("Could not persist brands : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}


	public void persistCategories(List<CategoryDTO> categories) {
		try {
			for(CategoryDTO category : categories) {
				SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
				subCategoryDTO.setSubclassName(category.getSubclassName());
				subCategoryDTO.setRemarks("R");
				subCategoryDTO.setUserId("");
				List<SubCategoryDTO> subCategory = foundationDataDao.getSubCategory(subCategoryDTO);
				if(null == subCategory || subCategory.size() == 0) {
					if("INSERT".equalsIgnoreCase(category.getAction())) {
						foundationDataDao.persistSubCategory(subCategoryDTO);
					} else if("UPDATE".equalsIgnoreCase(category.getAction())) {
						foundationDataDao.persistSubCategory(subCategoryDTO);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Could not persist Categories : " +ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}



	public void persistPurchaseOrdersData(List<PurchaseOrdersDTO> purchaseOrdersData) {
		try {
			int sn = 0;
			int poModifiedData = 0;
			int poInsertedData = 0;
			for(PurchaseOrdersDTO purchaseOrders : purchaseOrdersData) {
				List<PurchaseOrdersDTO> purchaseOrder = foundationDataDao.getPurchaseOrderNo(purchaseOrders.getOrderNo(), purchaseOrders.getLegacyPONum());
				String suppName = getSuppName(purchaseOrders.getSuppCode());
				if(null != purchaseOrder && !purchaseOrder.isEmpty() && purchaseOrder.size() > 0) {
					List<PurchaseOrdersDTO> orderPurchased = foundationDataDao.getOrdersPurchasedDetails(purchaseOrder.get(0).getORAPONo(), purchaseOrder.get(0).getContNo());
					if(null == orderPurchased || orderPurchased.size() == 0) {
						String srNo = purchaseOrder.get(0).getSN();
						purchaseOrders.setSN(srNo);
						purchaseOrders.setSuppCode((null != suppName && !suppName.isEmpty()) ? suppName : purchaseOrders.getSuppCode());
						
						SimpleDateFormat output = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
						String currentDateTime = output.format(new Date());
						
						foundationDataDao.persistDelPOHeadersData(srNo, currentDateTime);
						
						foundationDataDao.persistDelPODetailsData(srNo, currentDateTime);
						
						foundationDataDao.updatePurchaseOrderHeaders(purchaseOrders);
						String bolNo = "";
						int counter = 0;
						String groupCode = "";
						String jobNo = "";
						for(PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO : purchaseOrders.getPurchaseOrdersDetailsDTOs()) {
							purchaseOrdersDetailsDTO.setSN(srNo);
							if(null != purchaseOrdersDetailsDTO.getBolNo() && !purchaseOrdersDetailsDTO.getBolNo().isEmpty()) {
								foundationDataDao.updateBolOrderDetails(purchaseOrdersDetailsDTO);
								if(counter == purchaseOrders.getPurchaseOrdersDetailsDTOs().size() - 1) {
									bolNo = bolNo + purchaseOrdersDetailsDTO.getBolNo();
								} else {
									bolNo = bolNo + purchaseOrdersDetailsDTO.getBolNo() + "','";
								}
								counter++;
							} else {
								foundationDataDao.updatePurchaseOrderDetails(purchaseOrdersDetailsDTO);
								if(counter == purchaseOrders.getPurchaseOrdersDetailsDTOs().size() - 1) {
									groupCode = groupCode + purchaseOrdersDetailsDTO.getGroupCode();
									jobNo = jobNo + purchaseOrdersDetailsDTO.getJobNo();
								} else {
									groupCode = groupCode + purchaseOrdersDetailsDTO.getGroupCode() + "','";
									jobNo = jobNo + purchaseOrdersDetailsDTO.getJobNo() + "','";
								}
								counter++;
							}
						}
						if(null != bolNo && !bolNo.isEmpty()) {
							foundationDataDao.updateBOLNoOrderDetails(srNo, bolNo);
						}
						
						if(null != groupCode && !groupCode.isEmpty()) {
							foundationDataDao.updatePurchaseDetailsOrder(srNo, groupCode);
						}
						
						if(null != jobNo && !jobNo.isEmpty()) {
							foundationDataDao.updateJobNoOrderDetails(srNo, jobNo);
						}
						
						poModifiedData++;
					}
				} else {
					List<PurchaseOrdersDTO> purchaseOrderHeadersSN = foundationDataDao.getPurchaseOrderHeadersSN();
					if(null != purchaseOrderHeadersSN && purchaseOrderHeadersSN.size() > 0) {
						sn = Integer.parseInt(purchaseOrderHeadersSN.get(0).getSN());
						sn = sn + 1;
					} else {
						sn = Integer.parseInt("1");
					}
					String bolNo = "";
					int counter = 0;
					String groupCode = "";
					String jobNo = "";
					purchaseOrders.setSN(String.valueOf(sn));
					purchaseOrders.setSuppCode((null != suppName && !suppName.isEmpty()) ? suppName : purchaseOrders.getSuppCode());
					foundationDataDao.updatePurchaseOrderHeaders(purchaseOrders);
					for(PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO : purchaseOrders.getPurchaseOrdersDetailsDTOs()) {
						purchaseOrdersDetailsDTO.setSN(String.valueOf(sn));
						if(null != purchaseOrdersDetailsDTO.getBolNo() && !purchaseOrdersDetailsDTO.getBolNo().isEmpty()) {
							foundationDataDao.updateBolOrderDetails(purchaseOrdersDetailsDTO);
							if(counter == purchaseOrders.getPurchaseOrdersDetailsDTOs().size() - 1) {
								bolNo = bolNo + purchaseOrdersDetailsDTO.getBolNo();
							} else {
								bolNo = bolNo + purchaseOrdersDetailsDTO.getBolNo() + "','";
							}
							counter++;
						} else {
							foundationDataDao.updatePurchaseOrderDetails(purchaseOrdersDetailsDTO);
							if(counter == purchaseOrders.getPurchaseOrdersDetailsDTOs().size() - 1) {
								groupCode = groupCode + purchaseOrdersDetailsDTO.getGroupCode();
								jobNo = jobNo + purchaseOrdersDetailsDTO.getJobNo();
							} else {
								groupCode = groupCode + purchaseOrdersDetailsDTO.getGroupCode() + "','";
								jobNo = jobNo + purchaseOrdersDetailsDTO.getJobNo() + "','";
							}
							counter++;
						}
					}
					if(null != bolNo && !bolNo.isEmpty()) {
						foundationDataDao.updateBOLNoOrderDetails(String.valueOf(sn), bolNo);
					}
					
					if(null != groupCode && !groupCode.isEmpty()) {
						foundationDataDao.updatePurchaseDetailsOrder(String.valueOf(sn), groupCode);
					}
					
					if(null != jobNo && !jobNo.isEmpty()) {
						foundationDataDao.updateJobNoOrderDetails(String.valueOf(sn), jobNo);
					}
					
					poInsertedData++;
				}
			}
			if(poModifiedData > 0) {
				setPOModifiedData(poModifiedData);
			}
			
			if(poInsertedData > 0) {
				setpoInsertedData(poInsertedData);
			}
		} catch (Exception e) {
			logger.error("Could not persist purchase orders : " +ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}
	
	private void setPOModifiedData(int count) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		String business_date = simpleDateFormat.format(new Date());
		List<BflIntegrationDataDTO> listBflIntegrationDataDTOs = getBFLIntegrationData(business_date);
		if(null != listBflIntegrationDataDTOs && listBflIntegrationDataDTOs.size() > 0) {
			count = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getOrderMod());
			updateBFLIntegrationData(business_date, "ORDER_MOD", String.valueOf(count));
		} else {
			BflIntegrationDataDTO bflIntegrationDataDTO = new BflIntegrationDataDTO();
			bflIntegrationDataDTO.setBusinessDate(business_date);
			bflIntegrationDataDTO.setBrands("0");
			bflIntegrationDataDTO.setCategory("0");
			bflIntegrationDataDTO.setContainerId("0");
			bflIntegrationDataDTO.setContainerMapping("0");
			bflIntegrationDataDTO.setDept("0");
			bflIntegrationDataDTO.setDivision("0");
			bflIntegrationDataDTO.setItemGroups("0");
			bflIntegrationDataDTO.setItemReclassify("0");
			bflIntegrationDataDTO.setItems("0");
			bflIntegrationDataDTO.setManifestUpload("0");
			bflIntegrationDataDTO.setOrderCancelled("0");
			bflIntegrationDataDTO.setOrderMod(String.valueOf(count));
			bflIntegrationDataDTO.setOrderNew("0");
			bflIntegrationDataDTO.setPriceChanges("0");
			bflIntegrationDataDTO.setPromotions("0");
			bflIntegrationDataDTO.setShipments("0");
			bflIntegrationDataDTO.setSlashing("0");
			bflIntegrationDataDTO.setSubCategory("0");
			bflIntegrationDataDTO.setSuppliers("0");
			insertBFLIntegrationData(bflIntegrationDataDTO);
		}
	}
	
	private void setpoInsertedData(int count) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		String business_date = simpleDateFormat.format(new Date());
		List<BflIntegrationDataDTO> listBflIntegrationDataDTOs = getBFLIntegrationData(business_date);
		if(null != listBflIntegrationDataDTOs && listBflIntegrationDataDTOs.size() > 0) {
			count = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getOrderNew());
			updateBFLIntegrationData(business_date, "ORDER_NEW", String.valueOf(count));
		} else {
			BflIntegrationDataDTO bflIntegrationDataDTO = new BflIntegrationDataDTO();
			bflIntegrationDataDTO.setBusinessDate(business_date);
			bflIntegrationDataDTO.setBrands("0");
			bflIntegrationDataDTO.setCategory("0");
			bflIntegrationDataDTO.setContainerId("0");
			bflIntegrationDataDTO.setContainerMapping("0");
			bflIntegrationDataDTO.setDept("0");
			bflIntegrationDataDTO.setDivision("0");
			bflIntegrationDataDTO.setItemGroups("0");
			bflIntegrationDataDTO.setItemReclassify("0");
			bflIntegrationDataDTO.setItems("0");
			bflIntegrationDataDTO.setManifestUpload("0");
			bflIntegrationDataDTO.setOrderCancelled("0");
			bflIntegrationDataDTO.setOrderMod("0");
			bflIntegrationDataDTO.setOrderNew(String.valueOf(count));
			bflIntegrationDataDTO.setPriceChanges("0");
			bflIntegrationDataDTO.setPromotions("0");
			bflIntegrationDataDTO.setShipments("0");
			bflIntegrationDataDTO.setSlashing("0");
			bflIntegrationDataDTO.setSubCategory("0");
			bflIntegrationDataDTO.setSuppliers("0");
			insertBFLIntegrationData(bflIntegrationDataDTO);
		}
	}
	
	private String getSuppName(String suppCode) {
//		String suppName = foundationDataDao.getSuppName(suppCode);
		logger.info("getting data for SuppCode : " + suppCode);
		if(suppCode.contains("_")) {
			String[] split = suppCode.split("_");
			suppCode = split[0];
		}
		List<PurchaseOrdersDTO> suppName = foundationDataDao.getSuppName(suppCode);
		if(null != suppName && suppName.size() > 0) {
			return suppName.get(0).getSuppCode();
		} else {
			return "";
		}
	}


	
	public void persistPriceChangeDataForAllCostcenters(Map<String, List<ClearancesDTO>> pricingData) throws Exception {
		try {
			if(null != pricingData && pricingData.size() > 0) {
				for(Entry<String, List<ClearancesDTO>> entry : pricingData.entrySet()) {
					List<ClearancesDTO> clearancesDto = entry.getValue();
					for(ClearancesDTO clearances : clearancesDto) {
						String country = clearances.getCountry();
						logger.info("country Name is :: " + country);
						List<StoreDTO> allCountryDatabases = foundationDataDao.getAllCountryDatabases(country);
						if(null != allCountryDatabases && allCountryDatabases.size() > 0) {
							List<ItemLocationDTO> checkGroup = foundationDataDao.checkItemGroup(clearances.getItemCode());
							if(null == checkGroup || checkGroup.size() == 0) {
								if("UAE".equals(clearances.getCountry())) {
									//this is for UAE WH
									processUAEWHPrice(clearances);
									// below are the changes for UAE stores...
									for(StoreDTO storeDTO : allCountryDatabases) {
										logger.info("Entered into Else condition for UAE Prices " + storeDTO.getDataname());
										clearances.setDataBaseName(storeDTO.getDataname());
										List<ClearancesDTO> salesPriceData = foundationDataDao.getAllCostCentersSalesPriceData(clearances);
										if(null != salesPriceData && salesPriceData.size() > 0) {
											for(ClearancesDTO salesPrice : salesPriceData) {
												salesPrice.setDataBaseName(clearances.getDataBaseName());
												salesPrice.setUserId("100");
												salesPrice.setSalesRate((null != salesPrice.getSalesRate() && !salesPrice.getSalesRate().isEmpty()) ? salesPrice.getSalesRate() : "0");
												salesPrice.setPrice((null != salesPrice.getPrice() && !salesPrice.getPrice().isEmpty()) ? salesPrice.getPrice() : "0");
												List<ClearancesDTO> oldSalesPrice = foundationDataDao.getOldSalesPriceDataforCostCenter(salesPrice);
												if(null == oldSalesPrice || oldSalesPrice.size() == 0) {
													clearances.setCostCode(salesPrice.getCostCode());
													foundationDataDao.persistOldSalesPriceForPriceChange(salesPrice);
												}
											}
											foundationDataDao.updateSalesPriceForAllCostCenters(clearances);
										} 
									}
								} else {
									logger.info("Entered into Else condition for Export Countries Prices");
									for(StoreDTO storeDTO : allCountryDatabases) {
										logger.info("DATANAME :: " + storeDTO.getDataname());
										String dataName = "";
										if("QATAR".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("QATAR")) {
											dataName = "BFLQATAR"; 
										} else if("KUWAIT".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("KUWAIT")) {
											dataName = "BFLKUWAIT"; 
										} else if("OMAN".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("OMAN")) {
											dataName = "BFLOMAN"; 
										} else if("BAHRAIN".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("BAHRAIN")) {
											dataName = "BFLBAHRAIN";
										} else if("KSA".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("KSA") || storeDTO.getDataname().toUpperCase().contains("SAUDI")) {
											dataName = "BFLKSA"; 
										} 
										clearances.setDataBaseName(dataName);
										List<ClearancesDTO> salesPriceData = foundationDataDao.getAllCostCentersSalesPriceData(clearances);
										if(null != salesPriceData && salesPriceData.size() > 0) {
											for(ClearancesDTO salesPrice : salesPriceData) {
												salesPrice.setDataBaseName(dataName);
												salesPrice.setUserId("100");
												salesPrice.setSalesRate((null != salesPrice.getSalesRate() && !salesPrice.getSalesRate().isEmpty()) ? salesPrice.getSalesRate() : "0");
												salesPrice.setPrice((null != salesPrice.getPrice() && !salesPrice.getPrice().isEmpty()) ? salesPrice.getPrice() : "0");
												List<ClearancesDTO> oldSalesPrice = foundationDataDao.getOldSalesPriceDataforCostCenter(salesPrice);
												if(null == oldSalesPrice || oldSalesPrice.size() == 0) {
													salesPrice.setCostCode(salesPrice.getCostCode());
													foundationDataDao.persistOldSalesPriceForPriceChange(salesPrice);
												}
											}

											foundationDataDao.updateSalesPriceForAllCostCenters(clearances);
											List<ClearancesDTO> rfSalesPrice = foundationDataDao.getRFSalesPrice(clearances);
											if(null != rfSalesPrice && rfSalesPrice.size() > 0) {
												for(ClearancesDTO rfPrice : rfSalesPrice) {
													ClearancesDTO clearancesDTO = rfPrice;
													clearancesDTO.setDataBaseName(dataName);
													clearancesDTO.setSalesPrice(clearancesDTO.getSalesPrice());
													List<ClearancesDTO> delRFSalesPrice = foundationDataDao.getDelRFSalesPrice(clearancesDTO);
													if(null == delRFSalesPrice || delRFSalesPrice.size() == 0) {
														String trndate = clearancesDTO.getTrndate();
														SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
														Date d = sdf.parse(trndate);
														SimpleDateFormat output = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
														String [] dateTime = output.format(d).split(" ");
														clearancesDTO.setTrndate(dateTime[0]);
														clearancesDTO.setTrnTime(dateTime[1]);
														clearancesDTO.setDataBaseName(dataName);
														foundationDataDao.persistdelRFSalesPrice(clearancesDTO);
													}
												}
											}
											foundationDataDao.updateRFSalesPrice(clearances);
										} else {
											List<ClearancesDTO> rfSalesPrice = foundationDataDao.getRFSalesPrice(clearances);
											if(null != rfSalesPrice && rfSalesPrice.size() > 0) {
												for(ClearancesDTO rfPrice : rfSalesPrice) {
													ClearancesDTO clearancesDTO = rfPrice;
													clearancesDTO.setDataBaseName(dataName);
													clearancesDTO.setSalesPrice(clearancesDTO.getSalesPrice());
													List<ClearancesDTO> delRFSalesPrice = foundationDataDao.getDelRFSalesPrice(clearancesDTO);
													if(null == delRFSalesPrice || delRFSalesPrice.size() == 0) {
														String trndate = clearancesDTO.getTrndate();
														SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
														Date d = sdf.parse(trndate);
														SimpleDateFormat output = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
														String [] dateTime = output.format(d).split(" ");
														clearancesDTO.setTrndate(dateTime[0]);
														clearancesDTO.setTrnTime(dateTime[1]);
														clearancesDTO.setDataBaseName(dataName);
														foundationDataDao.persistdelRFSalesPrice(clearancesDTO);
													}
												}
												foundationDataDao.updateRFSalesPrice(clearances);
											} else {
												foundationDataDao.persistRFSalesPrice(clearances);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Could not persist price changes data : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}
	
	public void persistSlashingforAllCostCenters(Map<String, List<ClearancesDTO>> pricingData) throws Exception {
		try {
			for(Entry<String, List<ClearancesDTO>> entry : pricingData.entrySet()) {
				List<ClearancesDTO> clearancesDto = entry.getValue();
				for(ClearancesDTO clearances : clearancesDto) {
					String country = clearances.getCountry();
					List<StoreDTO> allCountryDatabases = foundationDataDao.getAllCountryDatabases(country);
					if(null != allCountryDatabases && allCountryDatabases.size() > 0) {
						List<ItemLocationDTO> checkGroup = foundationDataDao.checkItemGroup(clearances.getItemCode());
						if(null == checkGroup || checkGroup.size() == 0) {
							if("UAE".equals(clearances.getCountry())) {
								//this is for UAE WH
								processUAEWHSlashing(clearances);
								// below are the changes for UAE stores...
								for(StoreDTO storeDTO : allCountryDatabases) {
									logger.info("Entered into Else condition for UAE Prices " + storeDTO.getDataname());
									clearances.setDataBaseName(storeDTO.getDataname());
									List<ClearancesDTO> salesPriceData = foundationDataDao.getAllCostCentersSalesPriceData(clearances);
									if(null != salesPriceData && salesPriceData.size() > 0) {
										for(ClearancesDTO salesPrice : salesPriceData) {
											salesPrice.setDataBaseName(clearances.getDataBaseName());
											salesPrice.setUserId("99");
											salesPrice.setSalesRate((null != salesPrice.getSalesRate() && !salesPrice.getSalesRate().isEmpty()) ? salesPrice.getSalesRate() : "0");
											salesPrice.setPrice((null != salesPrice.getPrice() && !salesPrice.getPrice().isEmpty()) ? salesPrice.getPrice() : "0");
											List<ClearancesDTO> oldSalesPrice = foundationDataDao.getOldSalesPriceDataforCostCenter(salesPrice);
											if(null == oldSalesPrice || oldSalesPrice.size() == 0) {
												clearances.setCostCode(salesPrice.getCostCode());
												foundationDataDao.persistOldSalesPriceForPriceChange(salesPrice);
											}
										}
										foundationDataDao.updateSalesPriceForAllCostCenters(clearances);
									} 
								}
							} else {
								logger.info("Entered into Else condition for Export Countries Prices");
								for(StoreDTO storeDTO : allCountryDatabases) {
									String dataName = "";
									if("QATAR".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("QATAR")) {
										dataName = "BFLQATAR"; 
									} else if("KUWAIT".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("KUWAIT")) {
										dataName = "BFLKUWAIT"; 
									} else if("OMAN".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("OMAN")) {
										dataName = "BFLOMAN"; 
									} else if("BAHRAIN".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("BAHRAIN")) {
										dataName = "BFLBAHRAIN";
									} else if("KSA".equalsIgnoreCase(storeDTO.getDataname()) || storeDTO.getDataname().toUpperCase().contains("KSA") || storeDTO.getDataname().toUpperCase().contains("SAUDI")) {
										dataName = "BFLKSA"; 
									} 
									clearances.setDataBaseName(dataName);
									List<ClearancesDTO> salesPriceData = foundationDataDao.getAllCostCentersSalesPriceData(clearances);
									if(null != salesPriceData && salesPriceData.size() > 0) {
										for(ClearancesDTO salesPrice : salesPriceData) {
											salesPrice.setDataBaseName(dataName);
											salesPrice.setUserId("99");
											List<ClearancesDTO> oldSalesPrice = foundationDataDao.getOldSalesPriceDataforCostCenter(salesPrice);
											if(null == oldSalesPrice || oldSalesPrice.size() == 0) {
												salesPrice.setCostCode(salesPrice.getCostCode());
												foundationDataDao.persistOldSalesPriceForPriceChange(salesPrice);
											}
										}
										foundationDataDao.updateSalesPriceForAllCostCenters(clearances);
										List<ClearancesDTO> rfSalesPrice = foundationDataDao.getRFSalesPrice(clearances);
										if(null != rfSalesPrice && rfSalesPrice.size() > 0) {
											for(ClearancesDTO rfPrice : rfSalesPrice) {
												ClearancesDTO clearancesDTO = rfPrice;
												clearancesDTO.setDataBaseName(dataName);
												clearancesDTO.setSalesPrice(clearancesDTO.getSalesPrice());
												List<ClearancesDTO> delRFSalesPrice = foundationDataDao.getDelRFSalesPrice(clearancesDTO);
												if(null == delRFSalesPrice || delRFSalesPrice.size() == 0) {
													String trndate = clearancesDTO.getTrndate();
													SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
													Date d = sdf.parse(trndate);
													SimpleDateFormat output = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
													String [] dateTime = output.format(d).split(" ");
													clearancesDTO.setTrndate(dateTime[0]);
													clearancesDTO.setTrnTime(dateTime[1]);
													clearancesDTO.setDataBaseName(dataName);
													foundationDataDao.persistdelRFSalesPrice(clearancesDTO);
												}
											}
										}
										foundationDataDao.updateRFSalesPrice(clearances);
									} else {
										List<ClearancesDTO> rfSalesPrice = foundationDataDao.getRFSalesPrice(clearances);
										if(null != rfSalesPrice && rfSalesPrice.size() > 0) {
											for(ClearancesDTO rfPrice : rfSalesPrice) {
												ClearancesDTO clearancesDTO = rfPrice;
												clearancesDTO.setDataBaseName(dataName);
												clearancesDTO.setSalesPrice(clearancesDTO.getSalesPrice());
												List<ClearancesDTO> delRFSalesPrice = foundationDataDao.getDelRFSalesPrice(clearancesDTO);
												if(null == delRFSalesPrice || delRFSalesPrice.size() == 0) {
													String trndate = clearancesDTO.getTrndate();
													SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
													Date d = sdf.parse(trndate);
													SimpleDateFormat output = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
													String [] dateTime = output.format(d).split(" ");
													clearancesDTO.setTrndate(dateTime[0]);
													clearancesDTO.setTrnTime(dateTime[1]);
													clearancesDTO.setDataBaseName(dataName);
													foundationDataDao.persistdelRFSalesPrice(clearancesDTO);
												}
											}
											foundationDataDao.updateRFSalesPrice(clearances);
										} else {
											foundationDataDao.persistRFSalesPrice(clearances);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Could not persist price changes data : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}
	
	private void processUAEWHSlashing(ClearancesDTO clearances) {
		List<ClearancesDTO> salesPriceData = foundationDataDao.getSalesPriceData(clearances);
		clearances.setCostCode("001");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String currentDateTime = output.format(new Date());
		if(null != salesPriceData && salesPriceData.size() > 0) {
//			Boolean status = false;
//			String oldPrice = "";
			for(ClearancesDTO salesPrice : salesPriceData) {
				Boolean tcmItemCheck = tcmPriceChangeData(salesPrice);
				salesPrice.setUserId("99");
				salesPrice.setSalesRate((null != salesPrice.getSalesRate() && !salesPrice.getSalesRate().isEmpty()) ? salesPrice.getSalesRate() : "0");
				if(tcmItemCheck) {
					List<PriceChangeDTO> tcmItemPrice = foundationDataDao.getTCMItemsSlashing(salesPrice.getItemCode());
					if(null != clearances.getSalesRate() && null != tcmItemPrice && tcmItemPrice.size() > 0 && Double.parseDouble(null != tcmItemPrice.get(0).getNewPrice() && !tcmItemPrice.get(0).getNewPrice().isEmpty() ? tcmItemPrice.get(0).getNewPrice() : "0") 
						!= (Double.parseDouble(clearances.getSalesRate()))) {
						PriceChangeDTO tcmChangeDTO = new PriceChangeDTO();
						tcmChangeDTO.setItemCode(clearances.getItemCode());
						tcmChangeDTO.setOldPrice(null != tcmItemPrice.get(0).getNewPrice() && !tcmItemPrice.get(0).getNewPrice().isEmpty() ? tcmItemPrice.get(0).getNewPrice() : "0");
						tcmChangeDTO.setNewPrice(clearances.getSalesRate());
						tcmChangeDTO.setTrnDate(clearances.getTrndate());
						tcmChangeDTO.setTime(clearances.getTrnTime());
						tcmChangeDTO.setUserid("99");
						tcmChangeDTO.setItemName(clearances.getItemDesc().replaceAll(",", ""));
						tcmChangeDTO.setRemarks("R" + "-" + currentDateTime);
						foundationDataDao.persistTCMItemsSlashing(tcmChangeDTO);
					} else if(null == tcmItemPrice || tcmItemPrice.size() == 0) {
						PriceChangeDTO tcmChangeDTO = new PriceChangeDTO();
						tcmChangeDTO.setItemCode(clearances.getItemCode());
						tcmChangeDTO.setOldPrice(clearances.getRetailRate());
						tcmChangeDTO.setNewPrice(null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty() ? clearances.getSalesRate() : "0");
						tcmChangeDTO.setTrnDate(clearances.getTrndate());
						tcmChangeDTO.setItemName(clearances.getItemDesc().replaceAll(",", ""));
						tcmChangeDTO.setTime(clearances.getTrnTime());
						tcmChangeDTO.setUserid("99");
						tcmChangeDTO.setRemarks("R" + "-" + currentDateTime);
						foundationDataDao.persistTCMItemsSlashing(tcmChangeDTO);
					}
				} else {
					List<ClearancesDTO> deadStock = foundationDataDao.getUSADeadStockData(clearances);
					if(null != deadStock && deadStock.size() > 0 && !"99".equals(deadStock.get(0).getUserId())
						&& null != clearances.getSalesRate() && Double.parseDouble(deadStock.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
						clearances.setOldPrice(String.valueOf((int) Double.parseDouble(deadStock.get(0).getNewPrice())));
						foundationDataDao.persistClearancesData(clearances);
					}else if(null != deadStock && deadStock.size() > 0 && !"99".equals(deadStock.get(0).getUserId())
						&& null != clearances.getSalesRate() && Double.parseDouble(deadStock.get(0).getNewPrice()) == (Double.parseDouble(clearances.getSalesRate()))) {
						clearances.setOldPrice(String.valueOf((int) Double.parseDouble(deadStock.get(0).getNewPrice())));
						foundationDataDao.persistClearancesData(clearances);
					} else if(null == deadStock || deadStock.size() == 0) {
						clearances.setOldPrice(String.valueOf((int) Double.parseDouble(null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty() ? clearances.getSalesRate() : "0")));
						foundationDataDao.persistClearancesData(clearances);
					} else if(null != deadStock && deadStock.size() > 0 && "99".equals(deadStock.get(0).getUserId())
						&& null != clearances.getSalesRate() && Double.parseDouble(deadStock.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
						clearances.setOldPrice(String.valueOf((int) Double.parseDouble(deadStock.get(0).getNewPrice())));
						foundationDataDao.persistClearancesData(clearances);
					} 
				}
				
				List<ClearancesDTO> usapriceChange = foundationDataDao.getTop1USAPriceData(salesPrice);
				if(null != usapriceChange && usapriceChange.size() > 0 && !"99".equals(usapriceChange.get(0).getUserId())
					&& null != clearances.getSalesRate() && Double.parseDouble(usapriceChange.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
					clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
					foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
				} else if(null != usapriceChange && usapriceChange.size() > 0 && !"99".equals(usapriceChange.get(0).getUserId())
					&& null != clearances.getSalesRate() && Double.parseDouble(usapriceChange.get(0).getNewPrice()) == (Double.parseDouble(clearances.getSalesRate()))) {
					clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
					foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
				} else if(null != usapriceChange && usapriceChange.size() > 0 && "99".equals(usapriceChange.get(0).getUserId())
					&& null != clearances.getSalesRate() && Double.parseDouble(usapriceChange.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
					clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
					foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
				} else if(null == usapriceChange || usapriceChange.size() == 0) {
					if(null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty()) {
						clearances.setOldPrice(clearances.getSalesRate());
					} else { 
						clearances.setOldPrice("0");
					}
					foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
				}
				
				clearances.setOldPrice(salesPriceData.get(0).getOldPrice());
				
				if(null != clearances.getSalesRate() && Double.parseDouble(salesPriceData.get(0).getSalesRate()) != Double.parseDouble(clearances.getSalesRate())) {
					foundationDataDao.updateSalesPriceForClearancesData(clearances);
				}
			}
		} else {
			foundationDataDao.persistSalesPriceForClearancesData(clearances);
			Boolean tcmItemCheck = tcmPriceChangeData(clearances);
			if(tcmItemCheck) {
				List<PriceChangeDTO> tcmItemPrice = foundationDataDao.getTCMItemsSlashing(clearances.getItemCode());
				if(null != tcmItemPrice && tcmItemPrice.size() > 0  && Double.parseDouble(null != tcmItemPrice.get(0).getNewPrice() && !tcmItemPrice.get(0).getNewPrice().isEmpty() ? tcmItemPrice.get(0).getNewPrice() : "0") 
						!= (Double.parseDouble(clearances.getRetailRate()))) {
					PriceChangeDTO tcmChangeDTO = new PriceChangeDTO();
					tcmChangeDTO.setItemCode(clearances.getItemCode());
					tcmChangeDTO.setOldPrice(null != tcmItemPrice.get(0).getNewPrice() && !tcmItemPrice.get(0).getNewPrice().isEmpty() ? tcmItemPrice.get(0).getNewPrice() : "0");
					tcmChangeDTO.setItemName(clearances.getItemDesc().replaceAll(",", ""));
					tcmChangeDTO.setNewPrice(clearances.getSalesRate());
					tcmChangeDTO.setTrnDate(clearances.getTrndate());
					tcmChangeDTO.setTime(clearances.getTrnTime());
					tcmChangeDTO.setUserid("99");
					tcmChangeDTO.setRemarks("R" + "-" + currentDateTime);
					foundationDataDao.persistTCMItemsSlashing(tcmChangeDTO);
				} else if(null == tcmItemPrice || tcmItemPrice.size() == 0) {
					PriceChangeDTO tcmChangeDTO = new PriceChangeDTO();
					tcmChangeDTO.setItemCode(clearances.getItemCode());
					tcmChangeDTO.setOldPrice(clearances.getRetailRate());
					tcmChangeDTO.setNewPrice(clearances.getSalesRate());
					tcmChangeDTO.setTrnDate(clearances.getTrndate());
					tcmChangeDTO.setItemName(clearances.getItemDesc().replaceAll(",", ""));
					tcmChangeDTO.setTime(clearances.getTrnTime());
					tcmChangeDTO.setUserid("99");
					tcmChangeDTO.setRemarks("R" + "-" + currentDateTime);
					foundationDataDao.persistTCMItemsSlashing(tcmChangeDTO);
				}
			} else {
				List<ClearancesDTO> deadStock = foundationDataDao.getUSADeadStockData(clearances);
				if(null != deadStock && deadStock.size() > 0 && !"99".equals(deadStock.get(0).getUserId())
					&& Double.parseDouble(deadStock.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
					clearances.setOldPrice(String.valueOf((int) Double.parseDouble(deadStock.get(0).getNewPrice())));
					foundationDataDao.persistClearancesData(clearances);
				} else if(null != deadStock && deadStock.size() > 0 && !"99".equals(deadStock.get(0).getUserId())
					&& Double.parseDouble(deadStock.get(0).getNewPrice()) == (Double.parseDouble(clearances.getSalesRate()))) {
					clearances.setOldPrice(String.valueOf((int) Double.parseDouble(deadStock.get(0).getNewPrice())));
					foundationDataDao.persistClearancesData(clearances);
				} else if(null != deadStock && deadStock.size() > 0 && "99".equals(deadStock.get(0).getUserId())
					&& Double.parseDouble(deadStock.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
					clearances.setOldPrice(String.valueOf((int) Double.parseDouble(deadStock.get(0).getNewPrice())));
					foundationDataDao.persistClearancesData(clearances);
				} else if(null == deadStock || deadStock.size() == 0) {
					clearances.setOldPrice(String.valueOf((int) Double.parseDouble(clearances.getSalesRate())));
					foundationDataDao.persistClearancesData(clearances);
				}
			}
			
			List<ClearancesDTO> usapriceChange = foundationDataDao.getTop1USAPriceData(clearances);
			if(null != usapriceChange && usapriceChange.size() > 0 && !"99".equals(usapriceChange.get(0).getUserId())
				&& Double.parseDouble(usapriceChange.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
				clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
				foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
			} else if(null != usapriceChange && usapriceChange.size() > 0 && !"99".equals(usapriceChange.get(0).getUserId())
				&& Double.parseDouble(usapriceChange.get(0).getNewPrice()) == (Double.parseDouble(clearances.getSalesRate()))) {
				clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
				foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
			} else if(null != usapriceChange && usapriceChange.size() > 0 && "99".equals(usapriceChange.get(0).getUserId())
				&& Double.parseDouble(usapriceChange.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
				clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
				foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
			} else if(null == usapriceChange || usapriceChange.size() == 0) {
				clearances.setOldPrice(clearances.getSalesRate());
				foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
			}
		}
	}
	
	private void processUAEWHPrice(ClearancesDTO clearances) {
		List<ClearancesDTO> salesPriceData = foundationDataDao.getSalesPriceData(clearances);
		clearances.setCostCode("001");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String currentDateTime = output.format(new Date());
		if(null != salesPriceData && salesPriceData.size() > 0) {
//			Boolean status = false;
//			String oldPrice = "";
			for(ClearancesDTO salesPrice : salesPriceData) {
				Boolean tcmItemCheck = tcmPriceChangeData(salesPrice);
				salesPrice.setUserId("100");
				salesPrice.setSalesRate((null != salesPrice.getSalesRate() && !salesPrice.getSalesRate().isEmpty()) ? salesPrice.getSalesRate() : "0");
				if(tcmItemCheck) {
					List<PriceChangeDTO> tcmItemPrice = foundationDataDao.getTCMItemsPrice(salesPrice.getItemCode());
					if(null != clearances.getSalesRate() && null != tcmItemPrice && tcmItemPrice.size() > 0 && Double.parseDouble(null != tcmItemPrice.get(0).getNewPrice() && !tcmItemPrice.get(0).getNewPrice().isEmpty() ? tcmItemPrice.get(0).getNewPrice() : "0") 
							!= (Double.parseDouble(clearances.getSalesRate()))) {
						PriceChangeDTO tcmChangeDTO = new PriceChangeDTO();
						tcmChangeDTO.setItemCode(clearances.getItemCode());
						tcmChangeDTO.setOldPrice(null != tcmItemPrice.get(0).getNewPrice() && !tcmItemPrice.get(0).getNewPrice().isEmpty() ? tcmItemPrice.get(0).getNewPrice() : "0");
						tcmChangeDTO.setNewPrice(clearances.getSalesRate());
						tcmChangeDTO.setTrnDate(clearances.getTrndate());
						tcmChangeDTO.setTime(clearances.getTrnTime());
						tcmChangeDTO.setUserid("100");
						tcmChangeDTO.setRemarks("R" + "-" + currentDateTime);
						foundationDataDao.persistTCMItemsPrice(tcmChangeDTO);
					} else if(null == tcmItemPrice || tcmItemPrice.size() == 0) {
						PriceChangeDTO tcmChangeDTO = new PriceChangeDTO();
						tcmChangeDTO.setItemCode(clearances.getItemCode());
						tcmChangeDTO.setOldPrice(clearances.getRetailRate());
						tcmChangeDTO.setNewPrice(clearances.getSalesRate());
						tcmChangeDTO.setTrnDate(clearances.getTrndate());
						tcmChangeDTO.setTime(clearances.getTrnTime());
						tcmChangeDTO.setUserid("100");
						tcmChangeDTO.setRemarks("R" + "-" + currentDateTime);
						foundationDataDao.persistTCMItemsPrice(tcmChangeDTO);
					}
				} else {
					List<ClearancesDTO> usapriceChange = foundationDataDao.getTop1USAPriceData(salesPrice);
					if(null != usapriceChange && usapriceChange.size() > 0 && !"100".equals(usapriceChange.get(0).getUserId())
						&& null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty() && Double.parseDouble(usapriceChange.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
						clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
						foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
					} else if(null != usapriceChange && usapriceChange.size() > 0 && !"100".equals(usapriceChange.get(0).getUserId())
						&& null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty() && Double.parseDouble(usapriceChange.get(0).getNewPrice()) == (Double.parseDouble(clearances.getSalesRate()))) {
						clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
						foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
					} else if(null != usapriceChange && usapriceChange.size() > 0 && "100".equals(usapriceChange.get(0).getUserId()) 
						&& null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty() && Double.parseDouble(usapriceChange.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
						clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
						foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
					} else if(null == usapriceChange || usapriceChange.size() == 0) {
						if(null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty()) {
							clearances.setOldPrice(clearances.getSalesRate());
						} else {
							clearances.setOldPrice("0");
						}
						foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
					}
				}
				clearances.setOldPrice(salesPriceData.get(0).getOldPrice());
				if(null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty() && Double.parseDouble(salesPriceData.get(0).getSalesRate()) != Double.parseDouble(clearances.getSalesRate())) {
					foundationDataDao.updateSalesPriceForClearancesData(clearances);
				}
//				if(Double.parseDouble(clearances.getSalesRate()) != Double.parseDouble(salesPrice.getSalesRate())) {
//					clearances.setOldPrice(salesPriceData.get(0).getOldPrice());
//					foundationDataDao.updateSalesPriceForClearancesData(clearances);
//				}
			}
		} else {
			foundationDataDao.persistSalesPriceForClearancesData(clearances);
			Boolean tcmItemCheck = tcmPriceChangeData(clearances);
			if(tcmItemCheck) {
				List<PriceChangeDTO> tcmItemPrice = foundationDataDao.getTCMItemsPrice(clearances.getItemCode());
				if(null != clearances.getSalesRate() && null != tcmItemPrice && tcmItemPrice.size() > 0 && Double.parseDouble(null != tcmItemPrice.get(0).getNewPrice() && !tcmItemPrice.get(0).getNewPrice().isEmpty() ? tcmItemPrice.get(0).getNewPrice() : "0") 
						!= (Double.parseDouble(clearances.getRetailRate()))) {
					PriceChangeDTO tcmChangeDTO = new PriceChangeDTO();
					tcmChangeDTO.setItemCode(clearances.getItemCode());
					tcmChangeDTO.setOldPrice(null != tcmItemPrice.get(0).getNewPrice() && !tcmItemPrice.get(0).getNewPrice().isEmpty() ? tcmItemPrice.get(0).getNewPrice() : "0");
					tcmChangeDTO.setNewPrice(clearances.getSalesRate());
					tcmChangeDTO.setTrnDate(clearances.getTrndate());
					tcmChangeDTO.setTime(clearances.getTrnTime());
					tcmChangeDTO.setUserid("100");
					tcmChangeDTO.setRemarks("R" + "-" + currentDateTime);
					foundationDataDao.persistTCMItemsPrice(tcmChangeDTO);
				} else if(null == tcmItemPrice || tcmItemPrice.size() == 0) {
					PriceChangeDTO tcmChangeDTO = new PriceChangeDTO();
					tcmChangeDTO.setItemCode(clearances.getItemCode());
					tcmChangeDTO.setOldPrice(clearances.getRetailRate());
					tcmChangeDTO.setNewPrice(clearances.getSalesRate());
					tcmChangeDTO.setTrnDate(clearances.getTrndate());
					tcmChangeDTO.setTime(clearances.getTrnTime());
					tcmChangeDTO.setUserid("100");
					tcmChangeDTO.setRemarks("R" + "-" + currentDateTime);
					foundationDataDao.persistTCMItemsPrice(tcmChangeDTO);
				}
			} else {
				List<ClearancesDTO> usapriceChange = foundationDataDao.getTop1USAPriceData(clearances);
				if(null != usapriceChange && usapriceChange.size() > 0 && !"100".equals(usapriceChange.get(0).getUserId())
					&& null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty() && Double.parseDouble(usapriceChange.get(0).getNewPrice()) != (Double.parseDouble(clearances.getRetailRate()))) {
					clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
					foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
				} else if(null != usapriceChange && usapriceChange.size() > 0 && !"100".equals(usapriceChange.get(0).getUserId())
					&& null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty() && Double.parseDouble(usapriceChange.get(0).getNewPrice()) == (Double.parseDouble(clearances.getSalesRate()))) {
					clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
					foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
				} else if(null != usapriceChange && usapriceChange.size() > 0 && "100".equals(usapriceChange.get(0).getUserId())
					&& null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty() && Double.parseDouble(usapriceChange.get(0).getNewPrice()) != (Double.parseDouble(clearances.getSalesRate()))) {
					clearances.setOldPrice(usapriceChange.get(0).getNewPrice());
					foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
				} else if(null == usapriceChange || usapriceChange.size() == 0) {
					if(null != clearances.getSalesRate() && !clearances.getSalesRate().isEmpty()) {
						clearances.setOldPrice(clearances.getSalesRate());
					} else {
						clearances.setOldPrice("0");
					}
					foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
				}
//				salesPriceData = foundationDataDao.getUSAPriceData(clearances);
//				if(null == salesPriceData || salesPriceData.size() == 0) {
//					clearances.setOldPrice(clearances.getSalesRate());
//					foundationDataDao.persistUSAPriceChangeForClearancesData(clearances);
//				}
			}
		}
	}
	
	private Boolean tcmPriceChangeData(ClearancesDTO clearances) {
		Boolean temItemStatus = false;
		try {
			String groupCode = ConfigProperties.getInstance().getConfigValue("TCM_GROUPS");
			String code = "";
			String [] splitGroupCode = groupCode.split(",");
			int count = 1;
			for(String str: splitGroupCode) {
				if(count == splitGroupCode.length) {
					code = code + "'" + str.trim() + "'";
				} else {
					code = code + "'" + str.trim() + "', ";
					count++;
				}
			}
			List<ItemMasterDTO> itemMasterDTO = foundationDataDao.checkItemForTCM(clearances.getItemCode(), code);
			if(null != itemMasterDTO && itemMasterDTO.size() > 0) {
				temItemStatus = true;
			} else {
				temItemStatus = false;
			}
		} catch(Exception e1) {
			logger.error("Error occured while checking for Tcm Items :: " + ExceptionUtils.getStackTrace(e1));
			temItemStatus = false;
		}
		return temItemStatus;
	}

	public void persistItemsPrice(Map<String, List<ClearancesDTO>> pricingData) throws Exception {
		try {
			if(null != pricingData && pricingData.size() > 0) {
				for(Entry<String, List<ClearancesDTO>> entry : pricingData.entrySet()) {
					List<ClearancesDTO> clearancesDto = entry.getValue();
					for(ClearancesDTO clearances : clearancesDto) {
						if("W".equals(clearances.getLocationType())) {
							if("INSERT".equalsIgnoreCase(clearances.getAction())) {
								List<ClearancesDTO> salesPriceData = foundationDataDao.getAllCostCentersSalesPriceData(clearances);
								if(null != salesPriceData && salesPriceData.size() > 0) {
									if("UAE".equals(clearances.getCountry())) {
										Boolean status = false;
										String oldPrice = "";
										for(ClearancesDTO salesPrice : salesPriceData) {
											salesPrice.setUserId("100");
											List<ClearancesDTO> oldSalesPrice = foundationDataDao.getUSAPriceData(salesPrice);
											for(ClearancesDTO clearancesDTO : oldSalesPrice) {
												if(Double.parseDouble(clearancesDTO.getNewPrice()) == (Double.parseDouble(clearances.getRetailRate()))) {
													oldPrice = clearancesDTO.getNewPrice();
													status = true;
													break;
												} else {
													oldPrice = clearancesDTO.getNewPrice();
													status = false;
												}
											}
										}
										if(!status) {
											if(oldPrice.isEmpty()) {
												oldPrice = clearances.getSalesRate();
											}
											clearances.setOldPrice(oldPrice);
											foundationDataDao.persistUSAPriceChange(clearances);
										}
										clearances.setOldPrice(salesPriceData.get(0).getSalesPrice());
										foundationDataDao.updateSalesPriceForAllCostCenters(clearances);
										break;
									} else {
										foundationDataDao.updateSalesPriceForAllCostCenters(clearances);
										List<ClearancesDTO> aedPriceOfItem = foundationDataDao.getAEDPriceOfItem(clearances.getItemCode());
										if(null != aedPriceOfItem && aedPriceOfItem.size() > 0) {
											clearances.setAedPrice(aedPriceOfItem.get(0).getNewPrice());
										} else {
											clearances.setAedPrice("0.0");
										}
										
										List<ClearancesDTO> rfSalesPrice = foundationDataDao.getRFSalesPrice(clearances);
										if(null != rfSalesPrice) {
											for(ClearancesDTO rfPrice : rfSalesPrice) {
												ClearancesDTO clearancesDTO = rfPrice;
												clearancesDTO.setSalesPrice(clearances.getSalesPrice());
												List<ClearancesDTO> delRFSalesPrice = foundationDataDao.getDelRFSalesPrice(clearancesDTO);
												if(null == delRFSalesPrice || delRFSalesPrice.size() > 0) {
													String trndate = clearancesDTO.getTrndate();
													SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
													Date d = sdf.parse(trndate);
													SimpleDateFormat output = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
													String [] dateTime = output.format(d).split(" ");
													clearancesDTO.setTrndate(dateTime[0]);
													clearancesDTO.setTrnTime(dateTime[1]);
													clearancesDTO.setDataBaseName(clearances.getDataBaseName());
													foundationDataDao.persistdelRFSalesPrice(clearancesDTO);
												}
											}
										}
										foundationDataDao.updateRFSalesPrice(clearances);
										break;
									}
								} else {
									for(ClearancesDTO slashing : clearancesDto) {
										foundationDataDao.persistSalesPriceForClearancesData(slashing);
										if("UAE".equals(slashing.getCountry())) {
											salesPriceData = foundationDataDao.getUSAPriceData(slashing);
											if(null == salesPriceData || salesPriceData.size() == 0) {
												foundationDataDao.persistUSAPriceChangeForClearancesData(slashing);
											} else {
												String oldPrice = "";
												Boolean status = false;
												for(ClearancesDTO clearancesDTO : salesPriceData) {
													if(Double.parseDouble(clearancesDTO.getNewPrice()) == (Double.parseDouble(slashing.getRetailRate()))) {
														oldPrice = clearancesDTO.getNewPrice();
														status = true;
														break;
													} else {
														oldPrice = clearancesDTO.getNewPrice();
														status = false;
													}
												}
												if(!status) {
													if(oldPrice.isEmpty()) {
														oldPrice = slashing.getSalesRate();
													}
													slashing.setOldPrice(oldPrice);
													foundationDataDao.persistUSAPriceChange(slashing);
												}
											}
										} else {
											List<ClearancesDTO> aedPriceOfItem = foundationDataDao.getAEDPriceOfItem(slashing.getItemCode());
											if(null != aedPriceOfItem && aedPriceOfItem.size() > 0) {
												slashing.setAedPrice(aedPriceOfItem.get(0).getNewPrice());
											} else {
												slashing.setAedPrice("0.0");
											}
											List<ClearancesDTO> rfSalesPrice = foundationDataDao.getRFSalesPrice(slashing);
											if(null != rfSalesPrice) {
												for(ClearancesDTO rfPrice : rfSalesPrice) {
													ClearancesDTO clearancesDTO = rfPrice;
													clearancesDTO.setSalesPrice(clearances.getSalesPrice());
													List<ClearancesDTO> delRFSalesPrice = foundationDataDao.getDelRFSalesPrice(clearancesDTO);
													if(null == delRFSalesPrice || delRFSalesPrice.size() > 0) {
														String trndate = clearancesDTO.getTrndate();
														SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
														Date d = sdf.parse(trndate);
														SimpleDateFormat output = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
														String [] dateTime = output.format(d).split(" ");
														clearancesDTO.setTrndate(dateTime[0]);
														clearancesDTO.setTrnTime(dateTime[1]);
														clearancesDTO.setDataBaseName(clearances.getDataBaseName());
														foundationDataDao.updatedelRFSalesPrice(clearancesDTO);
													}
												}
											}
											foundationDataDao.updateRFSalesPrice(slashing);
										}
									}
								}
							}
						} else if("S".equals(clearances.getLocationType())) {
							List<ItemLocationDTO> checkGroup = null;
							if("INSERT".equalsIgnoreCase(clearances.getAction())) {
								checkGroup = foundationDataDao.checkItemGroup(clearances.getItemCode());
								if(null == checkGroup || checkGroup.size() == 0) {
									List<ClearancesDTO> salesPriceData = foundationDataDao.getAllCostCentersSalesPriceData(clearances);
//									List<ClearancesDTO> salesPriceData = foundationDataDao.getSalesPriceData(clearances);
									if(null != salesPriceData && salesPriceData.size() > 0) {
//										Boolean status = false;
										for(ClearancesDTO salesPrice : salesPriceData) {
											salesPrice.setDataBaseName(clearances.getDataBaseName());
											salesPrice.setUserId("100");
											List<ClearancesDTO> oldSalesPrice = foundationDataDao.getOldSalesPriceDataforCostCenter(salesPrice);
											if(null == oldSalesPrice || oldSalesPrice.size() > 0) {
												foundationDataDao.persistOldSalesPriceForPriceChange(clearances);
											}
											foundationDataDao.updateSalesPriceForAllCostCenters(clearances);
										}
									} else {
										foundationDataDao.persistSalesPriceForClearancesData(clearances);
										salesPriceData = foundationDataDao.getOldSalesPriceDataforCostCenter(clearances);
										if(null == salesPriceData || salesPriceData.size() <= 0) {
											foundationDataDao.persistOldSalesPriceForPriceChange(clearances);
										}
									}
								} 
							}				
						}
						try {
							String groupCode = ConfigProperties.getInstance().getConfigValue("TCM_GROUPS");
							String code = "";
							String [] splitGroupCode = groupCode.split(",");
							int count = 1;
							for(String str: splitGroupCode) {
								if(count == splitGroupCode.length) {
									code = code + "'" + str.trim() + "'";
								} else {
									code = code + "'" + str.trim() + "', ";
									count++;
								}
							}
							List<ItemMasterDTO> itemMasterDTO = foundationDataDao.checkItemForTCM(clearances.getItemCode(), code);
							if(null != itemMasterDTO && itemMasterDTO.size() > 0) {
								PriceChangeDTO tcmChangeDTO = new PriceChangeDTO();
								tcmChangeDTO.setItemCode(clearances.getItemCode());
								tcmChangeDTO.setOldPrice(clearances.getOldPrice());
								tcmChangeDTO.setNewPrice(clearances.getNewPrice());
								tcmChangeDTO.setTrnDate(clearances.getTrndate());
								tcmChangeDTO.setTime(clearances.getTrnTime());
								tcmChangeDTO.setUserid("");
								tcmChangeDTO.setRemarks("R");
								foundationDataDao.updateTCMItemsPrice(tcmChangeDTO);
							}
						} catch(Exception e1) {
							logger.error("Error occured while getting group code in price change API :: " + ExceptionUtils.getStackTrace(e1));
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Could not persist price changes data : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}

	public void persistMerchHierarchyData(List<DepartmentDto> deptDTO) {
		try {
			for(DepartmentDto departmentDto : deptDTO) {
				List<DepartmentDto> checkMerchHier = foundationDataDao.checkMerchHierarchy(departmentDto);
				if(null == checkMerchHier || checkMerchHier.size() == 0) {
					foundationDataDao.persistMerchHierarchy(departmentDto);
				}
			}
//			foundationDataDao.persistMerchHierarchy(deptDTO);
		} catch (Exception e) {
			logger.error("Could not persist Merch Hierarchy data : " + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}
	

	public String getLastProcessingTimestamp(Long jobId) {
		try {
			return foundationDataDao.getLastProcessingTimestamp(jobId);
		}catch (DataAccessException e) {
			return null;
		}
	}


	public void updateLastProcessingTimestamp(String timestamp, Long jobId, String jobName) {
		foundationDataDao.updateLastProcessingTimestamp(timestamp, jobId, jobName);
	}


	public void insertLastProcessingTimestamp(String timestamp, Long jobId,  String jobName) {
		foundationDataDao.insertLastProcessingTimestamp(timestamp, jobId, jobName);
	}
	



	public ContainerDetailsDTO getLegacyContainerNo(String contNo) {
		try {
			List<ContainerDetailsDTO> legacyContainerNo = foundationDataDao.getLegacyContainerNo(contNo);
			if(null != legacyContainerNo && legacyContainerNo.size() > 0) {
				return legacyContainerNo.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("Could not get the legacy Container No : " + e);
			throw e;
		}
	}

	public Boolean getContainerUsaOrgfileDetails(String contNo, String tableName) {
		List<ContainerDetailsDTO> legacyContainerNo = foundationDataDao.getLegacyContainerNo(contNo);
		String legacyContNo = "";
		if(null != legacyContainerNo && legacyContainerNo.size() > 0) {
			legacyContNo = legacyContainerNo.get(0).getLegacy();
		}
		if("UsaOrgfile".equalsIgnoreCase(tableName)) {
			tableName = "Usa..UsaOrgfile";
		} else if("UsaOrgfile".equalsIgnoreCase(tableName)) {
			tableName = "Online..itemAttributes";
		}
		List<UpcBarcodesDTO> containerExistDetails = foundationDataDao.getContainerExistDetails(contNo, legacyContNo, tableName);
		if(null != containerExistDetails && containerExistDetails.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	public void persistUpcBarcodes(UpcBarcodeResponse response, List<UpcBarcodesDTO> upcBarcodeData, String legacyContNo, String contNo, List<ContainerDetailsDTO> listOfContData) {
		try {
			Date currDate = new Date();
			String currentDate = new SimpleDateFormat("ddMMYY").format(currDate);
			
			List<UsaContNoDTO> useContNoDTOs = foundationDataDao.getContNos(contNo, legacyContNo);
			
			if(null == useContNoDTOs || useContNoDTOs.size() == 0) {
				Date date = new Date();
		        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
		        String trnDate = dt1.format(date);
		        List<SqlParameterSource> contDetails = getContsDetails(response, trnDate, legacyContNo);				
				for(SqlParameterSource param : contDetails) {
					foundationDataDao.persistContNos(param);
				}
			}
			
			List<SqlParameterSource> paramList = setManifestData(response, legacyContNo, currentDate);
			
			String itemCode = paramList.get(0).getValue("Itemcode").toString();
			
			if(null != itemCode && !itemCode.trim().isEmpty()) {
				String trnDate = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(currDate);
				foundationDataDao.persistBulkUSAOrgFile(paramList);
				foundationDataDao.persistTempBarcodesData(paramList);
				foundationDataDao.persistTempItemAttributesData(paramList);
				String containerNo = (null != legacyContNo && !legacyContNo.isEmpty()) ? legacyContNo : contNo;
				
				List<UpcBarcodesDTO> upcData = foundationDataDao.getTempBarcodesData(containerNo);
				Map<String, UpcBarcodesDTO> map = new HashMap<String, UpcBarcodesDTO>();
				if(null != upcData && !upcData.isEmpty() && upcData.size() > 0) {
					for(UpcBarcodesDTO upcBarcode : upcData) {
						map.put(upcBarcode.getUPC(), upcBarcode);
					}
				}
				
				List<UpcBarcodesDTO> itemMasterData = foundationDataDao.getTempItemMasterData(containerNo);
				Map<String, UpcBarcodesDTO> itemMasterMap = new HashMap<String, UpcBarcodesDTO>();
				if(null != itemMasterData && !itemMasterData.isEmpty() && itemMasterData.size() > 0) {
					for(UpcBarcodesDTO upcBarcode : itemMasterData) {
						itemMasterMap.put(upcBarcode.getItemcode(), upcBarcode);
					}
				}
				
				if(null != itemMasterMap && itemMasterMap.size() > 0) {
					List<SqlParameterSource> updateUpcBarcodesData = new ArrayList<SqlParameterSource>();
					List<SqlParameterSource> itemMasterGroupCodeLog = new ArrayList<SqlParameterSource>();
					for(SqlParameterSource param : paramList) {
						String upc = param.getValue("UPC").toString();
						UpcBarcodesDTO upcBarcodesDTO = itemMasterMap.get(upc);
						if(null != upcBarcodesDTO) {
							String groupCode = param.getValue("GroupCode").toString();
							boolean flag = false;
							if(null != upcBarcodesDTO.getGroupCode() && !upcBarcodesDTO.getGroupCode().trim().isEmpty() && !upcBarcodesDTO.getGroupCode().trim().equals(groupCode)) {
								itemMasterGroupCodeLog.add(PersistItemMasterLog("GroupCode", "ItemMaster", upc, groupCode, upcBarcodesDTO.getGroupCode(), trnDate));
								flag = true;
							}
							if(flag) {
								updateUpcBarcodesData.add(param);
							}						
						}
					}
					if(null != updateUpcBarcodesData && updateUpcBarcodesData.size() > 0) {
						foundationDataDao.updateItemMasterData(currentDate);
					}
					
					if(null != itemMasterGroupCodeLog && itemMasterGroupCodeLog.size() > 0) {
						foundationDataDao.persistUpcBarcodesLogDTO(itemMasterGroupCodeLog);
					}
				}
				
				if(null != map && map.size() > 0) {
					List<SqlParameterSource> upcBarcodesLogDTOs = new ArrayList<SqlParameterSource>();
					List<SqlParameterSource> updateUpcBarcodesData = new ArrayList<SqlParameterSource>();
					List<SqlParameterSource> insertUpcBarcodesData = new ArrayList<SqlParameterSource>();
					
					for(SqlParameterSource param : paramList) {
						String upc = param.getValue("UPC").toString();
						UpcBarcodesDTO upcBarcodesDTO = map.get(upc);
						if(null != upcBarcodesDTO) {
							String groupCode = param.getValue("GroupCode").toString();
							String itemName = param.getValue("ItemName").toString();
							String color = param.getValue("Color").toString();
							String style = param.getValue("Style").toString();
							String vendor = param.getValue("Vendor").toString();
							boolean flag = false;
							
							if(null != upcBarcodesDTO.getGroupCode() && !upcBarcodesDTO.getGroupCode().trim().isEmpty() && !upcBarcodesDTO.getGroupCode().trim().equals(groupCode)) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("GroupCode", containerNo, upc, groupCode, upcBarcodesDTO.getGroupCode(), trnDate));
								flag = true;
							} else if(null == upcBarcodesDTO.getGroupCode() || upcBarcodesDTO.getGroupCode().trim().isEmpty()) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("GroupCode", containerNo, upc, groupCode, upcBarcodesDTO.getGroupCode(), trnDate));
								flag = true;
							}
							
							if(null != upcBarcodesDTO.getItemName() && !upcBarcodesDTO.getItemName().trim().isEmpty() && !upcBarcodesDTO.getItemName().trim().equals(itemName)) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("ItemName", containerNo, upc, itemName, upcBarcodesDTO.getItemName(), trnDate));
								flag = true;
							} else if(null == upcBarcodesDTO.getItemName() || upcBarcodesDTO.getItemName().trim().isEmpty()) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("ItemName", containerNo, upc, itemName, upcBarcodesDTO.getItemName(), trnDate));
								flag = true;
							}
							
							if(null != upcBarcodesDTO.getColor() && !upcBarcodesDTO.getColor().trim().isEmpty() && !upcBarcodesDTO.getColor().trim().equals(color) && !"NA".equals(color) && !"N/A".equals(color)) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("Color", containerNo, upc, color, upcBarcodesDTO.getColor(), trnDate));
								flag = true;
							} else if(null == upcBarcodesDTO.getColor() || upcBarcodesDTO.getColor().trim().isEmpty()) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("Color", containerNo, upc, color, upcBarcodesDTO.getColor(), trnDate));
								flag = true;
							}
							
							if(null != upcBarcodesDTO.getStyle() && !upcBarcodesDTO.getStyle().trim().isEmpty() && !upcBarcodesDTO.getStyle().trim().equals(style) && !"NA".equals(style) && !"N/A".equals(style)) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("Style", containerNo, upc, style, upcBarcodesDTO.getStyle(), trnDate));
								flag = true;
							} else if(null == upcBarcodesDTO.getStyle() || upcBarcodesDTO.getStyle().trim().isEmpty()) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("Style", containerNo, upc, style, upcBarcodesDTO.getStyle(), trnDate));
								flag = true;
							}
							
							if(null != upcBarcodesDTO.getVendor() && !upcBarcodesDTO.getVendor().trim().isEmpty() && !upcBarcodesDTO.getVendor().trim().equals(vendor) && !"NA".equals(vendor) && !"N/A".equals(vendor)) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("Vendor", containerNo, upc, vendor, upcBarcodesDTO.getVendor(), trnDate));
								flag = true;
							} else if(null == upcBarcodesDTO.getVendor() || upcBarcodesDTO.getVendor().trim().isEmpty()) {
								upcBarcodesLogDTOs.add(PersistUpcBarcodeLog("Vendor", containerNo, upc, vendor, upcBarcodesDTO.getVendor(), trnDate));
								flag = true;
							}
							
							if(flag) {
								updateUpcBarcodesData.add(param);
							}						
						} else {
							insertUpcBarcodesData.add(param);
						}
					}
					
					if(null != updateUpcBarcodesData && updateUpcBarcodesData.size() > 0) {
						foundationDataDao.updateUpcBarcodesColorData(currentDate);
						foundationDataDao.updateUpcBarcodesGroupCodeData(currentDate);
						foundationDataDao.updateUpcBarcodesStyleData(currentDate);
						foundationDataDao.updateUpcBarcodesBrandData(currentDate);
						foundationDataDao.updateUpcBarcodesItemNameData(currentDate);
//						foundationDataDao.updateUpcBarcodesData(updateUpcBarcodesData, currentDate);
					}
					
					if(null != insertUpcBarcodesData && insertUpcBarcodesData.size() > 0) {
						foundationDataDao.insertUpcBarcodesData(insertUpcBarcodesData);
					}
					
					if(null != upcBarcodesLogDTOs && upcBarcodesLogDTOs.size() > 0) {
						foundationDataDao.persistUpcBarcodesLogDTO(upcBarcodesLogDTOs);
					}
				} else {
					foundationDataDao.insertUpcBarcodesData(paramList);
				}
				foundationDataDao.truncateTempUpcBarcodesData();
			} else {
				foundationDataDao.persistTempItemAttributesData(paramList);
			}
		} catch (Exception e) {
			logger.error("Could not persist upcBarcode data: " + e);
		}
	}
	
	private void setShipmentData(int count) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		String business_date = simpleDateFormat.format(new Date());
		List<BflIntegrationDataDTO> listBflIntegrationDataDTOs = getBFLIntegrationData(business_date);
		if(null != listBflIntegrationDataDTOs && listBflIntegrationDataDTOs.size() > 0) {
			count = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getShipments());
			updateBFLIntegrationData(business_date, "SHIPMENTS", String.valueOf(count));
		} else {
			BflIntegrationDataDTO bflIntegrationDataDTO = new BflIntegrationDataDTO();
			bflIntegrationDataDTO.setBusinessDate(business_date);
			bflIntegrationDataDTO.setBrands("0");
			bflIntegrationDataDTO.setCategory("0");
			bflIntegrationDataDTO.setContainerId("0");
			bflIntegrationDataDTO.setContainerMapping("0");
			bflIntegrationDataDTO.setDept("0");
			bflIntegrationDataDTO.setDivision("0");
			bflIntegrationDataDTO.setItemGroups("0");
			bflIntegrationDataDTO.setItemReclassify("0");
			bflIntegrationDataDTO.setItems("0");
			bflIntegrationDataDTO.setManifestUpload("0");
			bflIntegrationDataDTO.setOrderCancelled("0");
			bflIntegrationDataDTO.setOrderMod("0");
			bflIntegrationDataDTO.setOrderNew("0");
			bflIntegrationDataDTO.setPriceChanges("0");
			bflIntegrationDataDTO.setPromotions("0");
			bflIntegrationDataDTO.setShipments(String.valueOf(count));
			bflIntegrationDataDTO.setSlashing("0");
			bflIntegrationDataDTO.setSubCategory("0");
			bflIntegrationDataDTO.setSuppliers("0");
			insertBFLIntegrationData(bflIntegrationDataDTO);
		}
	}

	public Map<Integer, List<PurchaseOrdersDTO>> parsePurchaseOrderData(PartialShipmentResponse partialShipmentResponse) throws Exception {
		Map<Integer, List<PurchaseOrdersDTO>> map = new HashMap<>();
		int Shipment = -1;
		List<PurchaseOrdersDTO> listOfPurchaseOrders = new ArrayList<PurchaseOrdersDTO>();
		for(com.bfl.gencode.merchhier.PartialShipment.Item item : partialShipmentResponse.getItems()) {
			if(Shipment == -1) {
				Shipment = item.getShipment();
				PurchaseOrdersDTO purchaseOrder = parsePurchaseOrderData(item);
				listOfPurchaseOrders.add(purchaseOrder);
			} else if(Shipment != -1 && Shipment == item.getShipment()) {
				Shipment = item.getShipment();
				PurchaseOrdersDTO purchaseOrder = parsePurchaseOrderData(item);
				listOfPurchaseOrders.add(purchaseOrder);
			} else if(Shipment != -1 && Shipment != item.getShipment()) {
				map.put(Shipment, listOfPurchaseOrders);
				listOfPurchaseOrders = new ArrayList<PurchaseOrdersDTO>();
				PurchaseOrdersDTO purchaseOrder = parsePurchaseOrderData(item);
				listOfPurchaseOrders.add(purchaseOrder);
				Shipment = item.getShipment();
			}
		}
		if(null != listOfPurchaseOrders && listOfPurchaseOrders.size() > 0) {
			map.put(Shipment, listOfPurchaseOrders);
		}
		return map;
	}
	
	private PurchaseOrdersDTO parsePurchaseOrderData(com.bfl.gencode.merchhier.PartialShipment.Item item) {
		PurchaseOrdersDTO purchaseOrdersDTO = new PurchaseOrdersDTO();
		purchaseOrdersDTO.setLegacyPONum(item.getPoNo());
		purchaseOrdersDTO.setOrderNo(String.valueOf(item.getOrderNo()));
		purchaseOrdersDTO.setGroupCode(item.getGroupCode());
		purchaseOrdersDTO.setQty(String.valueOf(item.getQty()));
		purchaseOrdersDTO.setBolNo(item.getBolNo());
		purchaseOrdersDTO.setVatAmt(String.valueOf(item.getVat()));
		purchaseOrdersDTO.setAmount(String.valueOf(item.getAmt()));
		purchaseOrdersDTO.setRefNo(item.getRefNo());
		return purchaseOrdersDTO;
	}
	
	public void persistPurchaseOrderData(List<ContainerDetailsDTO> listOfContData) throws Exception {
		int shipmentData = 0;
		for(ContainerDetailsDTO contData : listOfContData) {
			PartialShipmentResponse partialShipmentResponse = getPartialShipmentData(contData.getOrderNo());
			Map<Integer, List<PurchaseOrdersDTO>> parsePartialShipmentMap = parsePurchaseOrderData(partialShipmentResponse);
			int count = 0;
			int sn = 0;
			for(Map.Entry<Integer, List<PurchaseOrdersDTO>> entry : parsePartialShipmentMap.entrySet()) {
				Integer key = entry.getKey();
				if(key == 0 || count == parsePartialShipmentMap.size() - 1) {
					List<PurchaseOrdersDTO> existingPODATA = foundationDataDao.getOrderDetails(contData.getOrderNo(), entry.getValue().get(0).getLegacyPONum());
					if(null != existingPODATA && existingPODATA.size() > 0) {
						SimpleDateFormat output = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
						String currentDateTime = output.format(new Date());
						
						foundationDataDao.persistDelPOHeadersData(existingPODATA.get(0).getSN(), currentDateTime);
						foundationDataDao.persistDelPODetailsData(existingPODATA.get(0).getSN(), currentDateTime);
						double amount = 0;
						double vatAmt = 0;
						int counter = 0;
						String groupcode = "";
						String refNo = "";
						String jobNo = "";
						String bolNo = "";
						for(int i = 0; i < entry.getValue().size(); i++) {
							PurchaseOrdersDTO purchaseOrdersDTO = entry.getValue().get(i);
							purchaseOrdersDTO.setJobNo(entry.getValue().get(i).getGroupCode() + "-" + entry.getValue().get(i).getOrderNo());
							purchaseOrdersDTO.setBolNo(entry.getValue().get(i).getBolNo());
							purchaseOrdersDTO.setGroupCode(entry.getValue().get(i).getGroupCode());
							purchaseOrdersDTO.setCatCode(entry.getValue().get(i).getGroupCode());
							purchaseOrdersDTO.setQty(entry.getValue().get(i).getQty());
							purchaseOrdersDTO.setAmount(entry.getValue().get(i).getAmount());
							purchaseOrdersDTO.setPalletCnt("0");
							purchaseOrdersDTO.setLoadQty("0");
							purchaseOrdersDTO.setDiscount("0");
							purchaseOrdersDTO.setDiscountPerc("0");
							amount = amount + Double.parseDouble(entry.getValue().get(i).getAmount());
							vatAmt = vatAmt + Double.parseDouble(entry.getValue().get(i).getVatAmt());
							refNo = entry.getValue().get(i).getRefNo();
							if(null != entry.getValue().get(i).getBolNo() && !entry.getValue().get(i).getBolNo().isEmpty()) {
								foundationDataDao.updateUSAPurchaseOrderBOLDetails(existingPODATA.get(0).getSN(), purchaseOrdersDTO.getGroupCode(), purchaseOrdersDTO.getAmount(), purchaseOrdersDTO.getQty(), purchaseOrdersDTO.getBolNo());
								if(counter == entry.getValue().size() - 1) {
									bolNo = bolNo + entry.getValue().get(i).getBolNo();
								} else if(!bolNo.contains(entry.getValue().get(i).getBolNo())) {
									bolNo = bolNo + entry.getValue().get(i).getBolNo() + "','";
								}
								if(counter == entry.getValue().size() - 1) {
									groupcode = groupcode + entry.getValue().get(i).getGroupCode();
								} else if(!groupcode.contains(entry.getValue().get(i).getGroupCode())) {
									groupcode = groupcode + entry.getValue().get(i).getGroupCode() + "','";
								}
							} else {
								if(counter == entry.getValue().size() - 1) {
									groupcode = groupcode + entry.getValue().get(i).getGroupCode();
									jobNo = jobNo + entry.getValue().get(i).getJobNo();
								} else if(!groupcode.contains(entry.getValue().get(i).getGroupCode())) {
									groupcode = groupcode + entry.getValue().get(i).getGroupCode() + "','";
									jobNo = jobNo + entry.getValue().get(i).getJobNo() + "','";
								}
								foundationDataDao.updateUSAPurchaseOrderDetails(existingPODATA.get(0).getSN(), purchaseOrdersDTO.getGroupCode(), purchaseOrdersDTO.getAmount(), purchaseOrdersDTO.getQty(), purchaseOrdersDTO.getJobNo());
							}
							counter++;
						}
						if(null != groupcode && !groupcode.isEmpty() && bolNo.isEmpty()) {
							foundationDataDao.updatePurchaseDetailsOrder(existingPODATA.get(0).getSN(), groupcode);
						}
						if(null != bolNo && !bolNo.isEmpty()) {
							foundationDataDao.updateBOLNoOrderDetails(existingPODATA.get(0).getSN(), bolNo);
						}
						
						if(null != jobNo && !jobNo.isEmpty()) {
							foundationDataDao.updateJobNoOrderDetails(existingPODATA.get(0).getSN(), jobNo);
						}
						
						groupcode = (groupcode.length() < 99) ? groupcode : groupcode.substring(0, 99);
						if(null != refNo && !refNo.isEmpty()) {
							foundationDataDao.updateRefNoForPurchaseOrder(refNo, existingPODATA.get(0).getORAPONo(), existingPODATA.get(0).getSN(), amount, vatAmt, 
									groupcode.replaceAll("'", ""), key == 0 ? "" : String.valueOf(key), parsePartialShipmentMap.size() > 1 ? "SPLIT" : "");
						} else {
							foundationDataDao.updateRefNoForPurchaseOrder("", existingPODATA.get(0).getORAPONo(), existingPODATA.get(0).getSN(), amount, vatAmt, 
									groupcode.replaceAll("'", ""), key == 0 ? "" : "", parsePartialShipmentMap.size() > 1 ? "SPLIT" : "");
						}
						shipmentData++;
					}
				} else {
					List<PurchaseOrdersDTO> existingPODATA = foundationDataDao.getOrderDetails(contData.getOrderNo(), entry.getValue().get(0).getLegacyPONum());
					
					if(null != existingPODATA && existingPODATA.size() > 0) {
						Boolean checkShipmentStatus = foundationDataDao.getPurchaseOrderShipmentData(existingPODATA.get(0).getORAPONo(), String.valueOf(key));
						
						if(!checkShipmentStatus) {
							List<PurchaseOrdersDTO> purchaseOrderHeadersSN = foundationDataDao.getPurchaseOrderHeadersSN();
							if(null != purchaseOrderHeadersSN && purchaseOrderHeadersSN.size() > 0) {
								sn = Integer.parseInt(purchaseOrderHeadersSN.get(0).getSN());
								sn = sn + 1;
							} else {
								sn = Integer.parseInt("1");
							}
							String srNo = existingPODATA.get(0).getSN();
							String refNo = entry.getValue().get(0).getRefNo();
							String shipment = String.valueOf(key);
							foundationDataDao.persistPurchaseOrdersDATA(sn, srNo, refNo, shipment);
							double amount = 0;
							double vatAmt = 0;
							String groupcode = "";
							String jobNo = "";
							int counter = 0;
							String bolNo = "";
							List<PurchaseOrdersDTO> listOfPurchaseOrderDetails = new ArrayList<PurchaseOrdersDTO>();
							for(int i = 0; i < entry.getValue().size(); i++) {
								PurchaseOrdersDTO purchaseOrdersDTO = entry.getValue().get(i);
								if(null != entry.getValue().get(i).getBolNo() && !entry.getValue().get(i).getBolNo().isEmpty()) {
									purchaseOrdersDTO.setBolNo(entry.getValue().get(i).getBolNo());
								} else {
									purchaseOrdersDTO.setJobNo(entry.getValue().get(i).getGroupCode() + "-" + entry.getValue().get(i).getOrderNo());
								}
								purchaseOrdersDTO.setGroupCode(entry.getValue().get(i).getGroupCode());
								purchaseOrdersDTO.setCatCode(entry.getValue().get(i).getGroupCode());
								purchaseOrdersDTO.setQty(entry.getValue().get(i).getQty());
								purchaseOrdersDTO.setAmount(entry.getValue().get(i).getAmount());
								purchaseOrdersDTO.setPalletCnt("0");
								purchaseOrdersDTO.setLoadQty("0");
								purchaseOrdersDTO.setDiscount("0");
								purchaseOrdersDTO.setDiscountPerc("0");
								amount = amount + Double.parseDouble(entry.getValue().get(i).getAmount());
								vatAmt = vatAmt + Double.parseDouble(entry.getValue().get(i).getVatAmt());
								if(null != entry.getValue().get(i).getBolNo() && !entry.getValue().get(i).getBolNo().isEmpty()) {
									if(counter == entry.getValue().size() - 1) {
										bolNo = bolNo + entry.getValue().get(i).getBolNo();
									} else if(!bolNo.contains(entry.getValue().get(i).getBolNo())) {
										bolNo = bolNo + entry.getValue().get(i).getBolNo() + "','";
									}
									if(counter == entry.getValue().size() - 1) {
										groupcode = groupcode + entry.getValue().get(i).getGroupCode();
									} else if(!groupcode.contains(entry.getValue().get(i).getGroupCode())) {
										groupcode = groupcode + entry.getValue().get(i).getGroupCode() + "','";
									}
								} else {
									if(counter == entry.getValue().size() - 1) {
										groupcode = groupcode + entry.getValue().get(i).getGroupCode();
										jobNo = jobNo + entry.getValue().get(i).getJobNo();
									} else if(!groupcode.contains(entry.getValue().get(i).getGroupCode())) {
										groupcode = groupcode + entry.getValue().get(i).getGroupCode() + "','";
										jobNo = jobNo + entry.getValue().get(i).getJobNo() + "','";
									}
								}
								counter++;
								listOfPurchaseOrderDetails.add(purchaseOrdersDTO);
							}
//							if(null != groupcode && !groupcode.isEmpty()) {
//								foundationDataDao.updatePurchaseDetailsOrder(existingPODATA.get(0).getSN(), groupcode);
//							}
//							if(null != bolNo && !bolNo.isEmpty()) {
//								foundationDataDao.updateBOLNoOrderDetails(existingPODATA.get(0).getSN(), bolNo);
//							}
//							
//							if(null != jobNo && !jobNo.isEmpty()) {
//								foundationDataDao.updateJobNoOrderDetails(existingPODATA.get(0).getSN(), jobNo);
//							}
							
							foundationDataDao.persistPODetailsData(listOfPurchaseOrderDetails, sn);
							groupcode = (groupcode.length() < 99) ? groupcode : groupcode.substring(0, 99);
							foundationDataDao.updatePOHeaderData(sn, amount, vatAmt, groupcode.replaceAll("'", ""));
							shipmentData++;
						}
					}
				}
				count++;
			}
		}
		if(shipmentData > 0) {
			setShipmentData(shipmentData);
		}
	}

	@SuppressWarnings("unused")
	private void persistPOData(PurchaseOrdersDTO purchaseOrdersDTO, com.bfl.gencode.merchhier.PartialShipment.Item item) {
		
	}

	@SuppressWarnings("unused")
	private void persistNewPODATA(GroupSumPurOrderDetailReport purchaseOrdersData, PurchaseOrdersDTO existingPurchaseOrders) throws IOException {
		List<PurchaseOrdersDTO> purchaseOrdersDTO = parseResponse(purchaseOrdersData, existingPurchaseOrders);
		persistPurchaseOrdersData(purchaseOrdersDTO);
	}
	
	private List<PurchaseOrdersDTO> parseResponse(GroupSumPurOrderDetailReport response, PurchaseOrdersDTO existingPurchaseOrders) throws IOException {
		List<PurchaseOrdersDTO> purchaseOrdersDTOs = new ArrayList<>();
		String orderNo = "";
		String groupCode = "";
		double invoiceAmt = 0;
		int count = 0;
		PurchaseOrdersDTO purchaseOrdersDTO = null;
		List<PurchaseOrdersDetailsDTO> list = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String invoiceDate = simpleDateFormat.format(new Date());
		String country = "UAE";
		for(com.bfl.gencode.merchhier.GroupSummaryDetails.Item item : response.getItems()) {
			if(orderNo.isEmpty()) {
				country = ConfigProperties.getInstance().getConfigValue("");
				purchaseOrdersDTO = getPurchaseOrdersDTO(item, invoiceDate, country, existingPurchaseOrders);
				list = new ArrayList<PurchaseOrdersDetailsDTO>();
				PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO = getPurchaseOrdersDetailsDTO(item);
				invoiceAmt = invoiceAmt + Double.parseDouble(item.getValue());
				groupCode = groupCode + item.getGroupCode() + ",";
				list.add(purchaseOrdersDetailsDTO);
				orderNo = String.valueOf(item.getOrderNo());
				count++;
			} else if(!orderNo.isEmpty() && orderNo.equals(String.valueOf(item.getOrderNo()))) {
				PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO = getPurchaseOrdersDetailsDTO(item);
				invoiceAmt = invoiceAmt + Double.parseDouble(item.getValue());
				groupCode = groupCode + item.getGroupCode() + ",";
				list.add(purchaseOrdersDetailsDTO);
				orderNo = String.valueOf(item.getOrderNo());
				count++;
			} else if(!orderNo.isEmpty() && !orderNo.equals(String.valueOf(item.getOrderNo()))) {
				groupCode = groupCode.replaceAll(",$", "");
				purchaseOrdersDTO.setPurchaseOrdersDetailsDTOs(list);
				purchaseOrdersDTO.setGroupCode(groupCode);
				purchaseOrdersDTO.setItemGroups(groupCode);
				purchaseOrdersDTO.setInvAmount(String.valueOf(invoiceAmt));
				purchaseOrdersDTOs.add(purchaseOrdersDTO);
				list = new ArrayList<PurchaseOrdersDetailsDTO>();
				groupCode = item.getGroupCode() + ",";
				invoiceAmt = Double.parseDouble(item.getValue());
				country = ConfigProperties.getInstance().getConfigValue("");
				purchaseOrdersDTO = getPurchaseOrdersDTO(item, invoiceDate, country, existingPurchaseOrders);
				orderNo = String.valueOf(item.getOrderNo());
				PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO = getPurchaseOrdersDetailsDTO(item);
				list.add(purchaseOrdersDetailsDTO);
				count++;
			}
		}
		if(count == response.getItems().size() && list.size() > 0 && null != purchaseOrdersDTO) {
			groupCode = groupCode.replaceAll(",$", "");
			purchaseOrdersDTO.setPurchaseOrdersDetailsDTOs(list);
			purchaseOrdersDTO.setInvAmount(String.valueOf(invoiceAmt));
			purchaseOrdersDTO.setGroupCode(groupCode);
			purchaseOrdersDTO.setItemGroups(groupCode);
			purchaseOrdersDTOs.add(purchaseOrdersDTO);
		}
		return purchaseOrdersDTOs;
	}
	
	private PurchaseOrdersDTO getPurchaseOrdersDTO(com.bfl.gencode.merchhier.GroupSummaryDetails.Item item, String invoiceDate, String country, PurchaseOrdersDTO existingPurchaseOrders) {
		PurchaseOrdersDTO purchaseOrdersDTO = new PurchaseOrdersDTO(); 
		purchaseOrdersDTO.setActualTT("");
		purchaseOrdersDTO.setOrderNo(String.valueOf(item.getOrderNo()));
		purchaseOrdersDTO.setAirShipment("");
		purchaseOrdersDTO.setApproved_Date(existingPurchaseOrders.getApproved_Date());
		purchaseOrdersDTO.setAssignedTo("");
		purchaseOrdersDTO.setAtaDate(null);
		purchaseOrdersDTO.setBLNo("");
		purchaseOrdersDTO.setCargo_Size("");
		purchaseOrdersDTO.setCatCode("");
		purchaseOrdersDTO.setChqPath("");
		purchaseOrdersDTO.setChqPath2("");
		purchaseOrdersDTO.setChqPath3("");
		purchaseOrdersDTO.setClaim_Period("0");
		purchaseOrdersDTO.setCommAmount("0");
		purchaseOrdersDTO.setCommPerc("0");
		purchaseOrdersDTO.setContNo("");
		purchaseOrdersDTO.setCountry(country);
		purchaseOrdersDTO.setCurrency(existingPurchaseOrders.getCurrency());
		purchaseOrdersDTO.setCust_Decl("");
		purchaseOrdersDTO.setDiscount(item.getDiscount());
		purchaseOrdersDTO.setDiscountPerc("0");
		purchaseOrdersDTO.setDueDate(existingPurchaseOrders.getDueDate());
		purchaseOrdersDTO.setEmailDt(null);
		purchaseOrdersDTO.setEtaDate(null);
		purchaseOrdersDTO.setEtdDate(null);
		purchaseOrdersDTO.setExport("");
		purchaseOrdersDTO.setExportDesc("");
		purchaseOrdersDTO.setFF("");
		purchaseOrdersDTO.setFt20("");
		purchaseOrdersDTO.setFt40("");
		purchaseOrdersDTO.setInvoiceDate(invoiceDate);
		purchaseOrdersDTO.setInvoiceNo(existingPurchaseOrders.getInvoiceNo());
		purchaseOrdersDTO.setInvPath("");
		purchaseOrdersDTO.setJobNo(item.getBolNo());
		purchaseOrdersDTO.setLcl("");
		purchaseOrdersDTO.setLoadQty("0");
		purchaseOrdersDTO.setNonPayment("");
		purchaseOrdersDTO.setNoofCont("");
		purchaseOrdersDTO.setORAPONo(String.valueOf(item.getOrderNo()));
		purchaseOrdersDTO.setOrder_Changed("");
		purchaseOrdersDTO.setOtherDesc("");
		purchaseOrdersDTO.setOthersPath(existingPurchaseOrders.getOthersPath());
		purchaseOrdersDTO.setPaidAmount("0.0");
		purchaseOrdersDTO.setPaymentTerms(existingPurchaseOrders.getPaymentTerms());
		purchaseOrdersDTO.setPickupDate(null);
		purchaseOrdersDTO.setPOL("");
		purchaseOrdersDTO.setPOLineStatus("OPEN");
		purchaseOrdersDTO.setProdLeadTime("0");
		purchaseOrdersDTO.setPromisedTT("");
		purchaseOrdersDTO.setReceiptPath("");
		purchaseOrdersDTO.setReceiptPath2("");
		purchaseOrdersDTO.setReceiptPath3("");
		purchaseOrdersDTO.setRemarks(null != existingPurchaseOrders.getRemarks() && !existingPurchaseOrders.getRemarks().isEmpty() ? existingPurchaseOrders.getRemarks() : "");
		purchaseOrdersDTO.setSBLC("");
		purchaseOrdersDTO.setShoesCat("");
		purchaseOrdersDTO.setSN(null);
		purchaseOrdersDTO.setStackingFee("0");
		purchaseOrdersDTO.setSuppCode(existingPurchaseOrders.getSuppCode());
		purchaseOrdersDTO.setTentative_ShipmentDt(existingPurchaseOrders.getTentative_ShipmentDt());
		purchaseOrdersDTO.setUserid(existingPurchaseOrders.getOthersPath());
		purchaseOrdersDTO.setVatAmt(item.getVatValue());
		purchaseOrdersDTO.setVatJvNo("");
		purchaseOrdersDTO.setVolume("");
		purchaseOrdersDTO.setWinter("");
		return purchaseOrdersDTO;
	}

	private PurchaseOrdersDetailsDTO getPurchaseOrdersDetailsDTO(com.bfl.gencode.merchhier.GroupSummaryDetails.Item item) {
		PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO = new PurchaseOrdersDetailsDTO();
		purchaseOrdersDetailsDTO.setDiscount(item.getDiscount());
		purchaseOrdersDetailsDTO.setDiscountPerc("0");
		purchaseOrdersDetailsDTO.setAmount(item.getValue());
		purchaseOrdersDetailsDTO.setGroupCode(item.getGroupCode());
		purchaseOrdersDetailsDTO.setCatCode(item.getGroupCode());
		purchaseOrdersDetailsDTO.setJobNo(item.getGroupCode() + "-" + item.getOrderNo());
		purchaseOrdersDetailsDTO.setLoadQty("0");
		purchaseOrdersDetailsDTO.setQty(item.getQty());
		purchaseOrdersDetailsDTO.setPalletCnt("0");
		return purchaseOrdersDetailsDTO;
	}

	@SuppressWarnings("unused")
	private PurchaseOrdersDetailsDTO updateExistingRecords(PurchaseOrdersDTO purchaseOrdersDTO, double newPOQty, double amount) {
		PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO = new PurchaseOrdersDetailsDTO();
		purchaseOrdersDetailsDTO.setDiscount("0");
		purchaseOrdersDetailsDTO.setDiscountPerc("0");
		purchaseOrdersDetailsDTO.setAmount(String.valueOf(amount));
		purchaseOrdersDetailsDTO.setGroupCode(purchaseOrdersDTO.getGroupCode());
		purchaseOrdersDetailsDTO.setCatCode(purchaseOrdersDTO.getGroupCode());
		purchaseOrdersDetailsDTO.setJobNo(purchaseOrdersDTO.getGroupCode() + "-" + purchaseOrdersDTO.getOrderNo());
		purchaseOrdersDetailsDTO.setLoadQty("0");
		purchaseOrdersDetailsDTO.setQty(String.valueOf(newPOQty));
		purchaseOrdersDetailsDTO.setPalletCnt("0");
		return purchaseOrdersDetailsDTO;
	}

	@SuppressWarnings("unused")
	private GroupSumPurOrderDetailReport getPurchaseOrdersData(String orderNo) throws Exception {
		GroupSumPurOrderDetailReport response = null;
		do {
			if(response == null) {
				response = getPurchaseOrdersData(orderNo, null);
			} else {
				List<com.bfl.gencode.merchhier.GroupSummaryDetails.Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
				if(null != links && links.size() > 0)
					response = getPurchaseOrdersData(orderNo, links.get(0).getHref());
				else
					break;
			}
		} while(null != response && response.getHasMore());
		return response;
	}
	
	private PartialShipmentResponse getPartialShipmentData(String orderNo) throws Exception {
		PartialShipmentResponse response = null;
		do {
			if(response == null) {
				response = getPartialShipmentData(orderNo, null);
			} else {
				List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
				if(null != links && links.size() > 0)
					response = getPartialShipmentData(orderNo, links.get(0).getHref());
				else
					break;
			}
		} while(null != response && response.getHasMore());
		return response;
	}
	
	private GroupSumPurOrderDetailReport getPurchaseOrdersData(String orderNo, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setUrl(url);
		request.setLimit(1000);
		request.setToken(token);
		request.setOrderNo(orderNo);
		return helper.getGroupSumPurOrderDetailReport(request, orderNo);
	}
	
	private PartialShipmentResponse getPartialShipmentData(String orderNo, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setUrl(url);
		request.setLimit(1000);
		request.setToken(token);
		request.setOrderNo(orderNo);
		return helper.getPartialShipmentData(request, orderNo);
	}
	
	@Override
	public void persistItemAttributes(String contNo) {
		logger.info("Inserted into Item Attributes");
		List<ItemAttributesDTO> itemAttributesData = foundationDataDao.getItemAttributesData(contNo);
		if(null != itemAttributesData && itemAttributesData.size() > 0) {
			logger.info("Got data for Item Attributes for Container : " + contNo);
			List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
			LocalTime time = LocalTime.now();
			Date currDate = new Date();
			String currentDate = new SimpleDateFormat("dd/MM/YYYY").format(currDate);
			for(ItemAttributesDTO itemAttributesDTO : itemAttributesData) {
				SqlParameterSource param = new MapSqlParameterSource()
					.addValue("ContNo", itemAttributesDTO.getContNo())
					.addValue("TrnDate", currentDate)
					.addValue("UPC", itemAttributesDTO.getUPC())
					.addValue("ItemName", itemAttributesDTO.getItemName())
					.addValue("Itemcode", itemAttributesDTO.getUPC())
					.addValue("UserID", "")
					.addValue("Season", itemAttributesDTO.getSeason())
					.addValue("Vendor", itemAttributesDTO.getBrand())
					.addValue("GroupCode", itemAttributesDTO.getGroupCode())
					.addValue("Color", itemAttributesDTO.getColor())
					.addValue("Style", itemAttributesDTO.getStyle())
					.addValue("Gender", itemAttributesDTO.getGender())
					.addValue("subcategory", itemAttributesDTO.getSubcategory())
					.addValue("Size", itemAttributesDTO.getSize())
					.addValue("TrnTime", time)
					.addValue("UserName", "")
					.addValue("Upload", "Y")
					.addValue("StyleQty", itemAttributesDTO.getStyleQty())
					.addValue("Remarks", itemAttributesDTO.getRemarks())
					.addValue("Brand", itemAttributesDTO.getBrand())
					.addValue("GenBarcode", "");
				paramList.add(param);
			}
			foundationDataDao.persistManifestItemAttributes(paramList);
			foundationDataDao.truncateTempItemAttributesData();
		}
	}
	
	public SqlParameterSource PersistUpcBarcodeLog(String dataType, String containerNo, String upc, String newData, String oldData, String trnDate) {
		SqlParameterSource param = new MapSqlParameterSource().
			addValue("contno", containerNo).
			addValue("datatype", dataType).
			addValue("Itemcode", upc).
			addValue("oldData", ((null != oldData && !oldData.isEmpty() && oldData.length() > 100) ? oldData.substring(0, 99) : oldData)).
			addValue("newData", ((null != newData && !newData.isEmpty() && newData.length() > 100) ? newData.substring(0, 99) : newData)).
			addValue("trndate", trnDate).
			addValue("tableName", "upcBarcodes");
		return param;
	}
	
	public SqlParameterSource PersistItemMasterLog(String dataType, String containerNo, String upc, String newData, String oldData, String trnDate) {
		SqlParameterSource param = new MapSqlParameterSource().
			addValue("contno", containerNo).
			addValue("datatype", dataType).
			addValue("Itemcode", upc).
			addValue("oldData", ((null != oldData && !oldData.isEmpty() && oldData.length() > 100) ? oldData.substring(0, 99) : oldData)).
			addValue("newData", ((null != newData && !newData.isEmpty() && newData.length() > 100) ? newData.substring(0, 99) : newData)).
			addValue("trndate", trnDate).
			addValue("tableName", "ItemMaster");
		return param;
	}

	
	public void persistShipmentUploadData(UpcBarcodeResponse response, String contno) {
		try {
			List<SqlParameterSource> paramList = setManifestDataForGroupWise(response, contno);
			foundationDataDao.persistItemAttributesForGroupWise(paramList);
		} catch (Exception e) {
			logger.error("Could not persist group wise data: " + e);
			throw e;
		}
	}
	
	public void setUsaContnoMapping(UpcBarcodeResponse response, String rmsContNo, String legacyContNo) {
		List<UsaContNoDTO> useContNoDTOs = foundationDataDao.getContNos(rmsContNo, legacyContNo);
		
		if(null == useContNoDTOs || useContNoDTOs.size() == 0) {
			Date date = new Date();
	        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
	        String trnDate = dt1.format(date);
			
	        List<SqlParameterSource> contDetails = getContsDetails(response, trnDate, legacyContNo);
			
			for(SqlParameterSource param : contDetails) {
				foundationDataDao.persistContNos(param);
			}
		}
	}
	
	public void persistMissingResaItems(MissingResaItemsResponse response) {
		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
		for(com.bfl.gencode.resa.MissingResaItems.Item item : response.getItems()) {
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("itemCode", item.getItem());
			paramList.add(param);
		}
		if(null != paramList && paramList.size() > 0) {
			foundationDataDao.persistMissingResaItems(paramList);
		}
	}
	
	public void deleteCreatedItems(String itemCode) {
		foundationDataDao.deleteCreatedItems(itemCode);
	}
	
	public List<BflIntegrationDataDTO> getBFLIntegrationData(String business_date) {
		return foundationDataDao.getBFLIntegrationData(business_date);
	}
	
	public void insertBFLIntegrationData(BflIntegrationDataDTO bflIntegrationDataDTO) {
		foundationDataDao.insertBFLIntegrationData(bflIntegrationDataDTO);
	}
	
	public void updateBFLIntegrationData(String business_date, String columnName, String columnValue) {
		foundationDataDao.updateBFLIntegrationData(business_date, columnName, columnValue);
	}



	private List<SqlParameterSource> setManifestData(UpcBarcodeResponse response, String legacyContNo, String currentDate) {
		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
		LocalTime time = LocalTime.now();
		Date trnDate = new Date();
		for(com.bfl.gencode.merchhier.upcbarcodes.response.Item item : response.getItems()) {
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("ContNo", (null != legacyContNo && !legacyContNo.isEmpty()) ? legacyContNo : item.getContainerId())
				.addValue("BOLNO", (null != item.getBolNo() && !item.getBolNo().isEmpty()) ? item.getBolNo() : (null != item.getBoxno() && !item.getBoxno().isEmpty() && item.getBoxno().contains("_") ? item.getBoxno().split("_")[1] : item.getBoxno()))
				.addValue("TrnDate", trnDate)
				.addValue("UPC", (null != item.getUpc() && !item.getUpc().trim().isEmpty()) ? item.getUpc() : item.getItem())
				.addValue("DeptName", item.getDeptName())
				.addValue("ItemName", item.getItemname())
				.addValue("OrgRetRate", item.getRrp())
				.addValue("Itemcode", item.getItem())
				.addValue("UserID", "")
				.addValue("ItemType", (null != item.getSeason() ? String.valueOf(item.getSeason().charAt(0)) : "S"))
				.addValue("ShortSkirt", "")
				.addValue("ShortUpdated", "R-INS" + currentDate)
				.addValue("Size1", item.getSiz())
				.addValue("SizeUpdated", "")
				.addValue("WinterUpdated", "")
				.addValue("SizeCheck", "")
				.addValue("DHSCost", "0")
				.addValue("ClientCost", (null != item.getUnitCost() && !item.getUnitCost().isEmpty()) ? item.getUnitCost() : "0")
				.addValue("Vendor", item.getBrandName())
				.addValue("GroupCode", ((null != item.getGroupCode() && !item.getGroupCode().isEmpty()) ? item.getGroupCode().trim() : item.getGroupCode()))
				.addValue("Color", (null != item.getColor() && !item.getColor().isEmpty()) ? item.getColor() : "")
				.addValue("Style", (null != item.getStyle() && !item.getStyle().isEmpty()) ? item.getStyle() : "")
				.addValue("shortname", "")
				.addValue("TYPE1", "")
				.addValue("Gender", (null != item.getGender() && !item.getGender().isEmpty()) ? item.getGender() : "")
				.addValue("CountryOrigin", null != item.getOriginCountryId() && !item.getOriginCountryId().isEmpty() ? item.getOriginCountryId() : "")
				.addValue("Material", null != item.getProductMaterial() && !item.getProductMaterial().isEmpty() ? item.getProductMaterial() : "")
				.addValue("subcategory", (null != item.getSubName() && !item.getSubName().isEmpty() && item.getSubName().length() > 50) ? item.getSubName().substring(0, 49) : item.getSubName())
				.addValue("HSCode", item.getHscode())
				.addValue("RRPCurrency", item.getRrpCurrency())
				.addValue("Size", item.getSiz())
				.addValue("TrnTime", time)
				.addValue("UserName", "")
				.addValue("Upload", "Y")
				.addValue("StyleQty", item.getShippedqty())
				.addValue("Remarks", (null != item.getComments() && !item.getComments().isEmpty()) ? item.getComments() : "")
				.addValue("Brand", item.getBrandName())
				.addValue("Season", (null != item.getSeason() && !item.getSeason().isEmpty()) ? String.valueOf(item.getSeason().charAt(0)) : "S")
				.addValue("GenBarcode", (null != item.getGenbarcode() && !item.getGenbarcode().isEmpty()) ? item.getGenbarcode() : "")
				.addValue("orgqty", item.getShippedqty())
				.addValue("orgretail", item.getRrp())
				.addValue("totalretail", (null != item.getRrp() && !item.getRrp().isEmpty()) ? (Double.parseDouble(item.getShippedqty()) * Double.parseDouble(item.getRrp())) : "")
				.addValue("orgcost", item.getUnitCost())
				.addValue("totalorgcost", "0")
				.addValue("division", item.getDivision())
				.addValue("DhsSalesOrg", "0")
				.addValue("DHSSalesAdj", (null != item.getSellingprice() && !item.getSellingprice().isEmpty()) ? item.getSellingprice() : "0")
				.addValue("COSTCURR", item.getCostCurrency())
				.addValue("ItemIssued", "0")
				.addValue("Status","R")
				.addValue("InvoiceNo", item.getVendorOrderNo())
				.addValue("OrgRetExcel", item.getRrp())
				.addValue("SalesPriceCurrency", item.getSellingPriceCurrency())
				.addValue("SplitPO", "N")
				.addValue("orderNo", item.getOrderNo())
				.addValue("LoadManifestType","NORMAL-" + item.getContainerId());
			paramList.add(param);
		}
		return paramList;
	}


	@SuppressWarnings("deprecation")
	private List<SqlParameterSource> setManifestDataForGroupWise(UpcBarcodeResponse response, String contno) {
		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
		LocalTime time = LocalTime.now();
		Date trnDate = new Date();
		trnDate.setHours(0);
		trnDate.setMinutes(0);
		trnDate.setSeconds(0);
		SqlParameterSource param = null;
		for(com.bfl.gencode.merchhier.upcbarcodes.response.Item item : response.getItems()) {
			param = getManifestGroupData(item, trnDate, time, contno);
			paramList.add(param);
		}
		return paramList;
	}

	private SqlParameterSource getManifestGroupData(com.bfl.gencode.merchhier.upcbarcodes.response.Item item, Date trnDate, LocalTime time, String contno) {
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("ContNo", contno)
			.addValue("TrnDate", trnDate)
			.addValue("UPC", (null != item.getUpc() && !item.getUpc().trim().isEmpty()) ? item.getUpc() : "")
			.addValue("ItemName", (null != item.getItemname() && !item.getItemname().trim().isEmpty()) ? item.getItemname() : "")
			.addValue("UserID", "")
			.addValue("ItemType", (null != item.getSeason() ? String.valueOf(item.getSeason().charAt(0)) : "S"))
			.addValue("ShortSkirt", "")
			.addValue("ShortUpdated", "")
			.addValue("Size1", "")
			.addValue("SizeUpdated", "")
			.addValue("WinterUpdated", "")
			.addValue("SizeCheck", "")
			.addValue("DHSCost", "0")
			.addValue("Vendor", item.getBrandName())
			.addValue("GroupCode", ((null != item.getGroupCode() && !item.getGroupCode().isEmpty()) ? item.getGroupCode().trim() : item.getGroupCode()))
			.addValue("Color", (null != item.getColor() && !item.getColor().isEmpty()) ? item.getColor() : "")
			.addValue("Style", (null != item.getStyle() && !item.getStyle().isEmpty()) ? item.getStyle() : "")
			.addValue("shortname", "")
			.addValue("TYPE1", "")
			.addValue("Gender", (null != item.getGender() && !item.getGender().isEmpty()) ? item.getGender() : "")
			.addValue("Material", item.getProductMaterial())
			.addValue("subcategory", (null != item.getSubName() && !item.getSubName().isEmpty() && item.getSubName().length() > 50) ? item.getSubName().substring(0, 49) : item.getSubName())
			.addValue("HSCode", item.getHscode())
			.addValue("RRPCurrency", item.getRrpCurrency())
			.addValue("Size", item.getSiz())
			.addValue("TrnTime", time)
			.addValue("UserName", "")
			.addValue("Upload", "Y")
			.addValue("StyleQty", item.getShippedqty())
			.addValue("Remarks", (null != item.getComments() && !item.getComments().isEmpty()) ? item.getComments() : "")
			.addValue("Brand", item.getBrandName())
			.addValue("Season", (null != item.getSeason() && !item.getSeason().isEmpty()) ? String.valueOf(item.getSeason().charAt(0)) : "S")
			.addValue("GenBarcode", (null != item.getGenbarcode() && !item.getGenbarcode().isEmpty()) ? item.getGenbarcode() : "")
			.addValue("orgqty", item.getShippedqty())
			.addValue("orgretail", item.getRrp())
			.addValue("orgcost", item.getUnitCost())
			.addValue("totalorgcost", "0")
			.addValue("division", item.getDivision())
			.addValue("DhsSalesOrg", "0")
			.addValue("DHSSalesAdj", (null != item.getSellingprice() && !item.getSellingprice().isEmpty()) ? item.getSellingprice() : "0")
			.addValue("COSTCURR", item.getCostCurrency())
			.addValue("ItemIssued", "0")
			.addValue("Status","R")
			.addValue("InvoiceNo", item.getVendorOrderNo())
			.addValue("OrgRetExcel", item.getRrp())
			.addValue("SalesPriceCurrency", item.getSellingPriceCurrency())
			.addValue("SplitPO", "N")
			.addValue("styleDescription", item.getStyle_description())
			.addValue("LoadManifestType","NORMAL");
		return param;
	}

	private List<SqlParameterSource> getContsDetails(UpcBarcodeResponse response, String trnDate, String legacyContNo) {
		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
		String usaContNos = foundationDataDao.getUsaContNos();
        String [] dateTime = trnDate.split(" ");
		String contNo = "";
		String orderNo = "";
		int count = 1;
		if(null != usaContNos && !usaContNos.isEmpty()) {
			count = Integer.parseInt(usaContNos) + 1;
		}
		for(com.bfl.gencode.merchhier.upcbarcodes.response.Item item : response.getItems()) {
			if(contNo.isEmpty()) {
				SqlParameterSource param = new MapSqlParameterSource()
					.addValue("ContNo", null != legacyContNo && !legacyContNo.isEmpty() ? legacyContNo : item.getContainerId())
					.addValue("RefNo", item.getRefNo())
					.addValue("TrnDate", dateTime[0])
					.addValue("UserId", "")
					.addValue("SNo", count)
					.addValue("type", "")
					.addValue("time", dateTime[1])
					.addValue("reamrks", "R")
					.addValue("orderNo", item.getOrderNo());
				paramList.add(param);
				orderNo = orderNo + item.getOrderNo() + ",";
				contNo = item.getContainerId();
				count++;
			} else if(null != contNo && !contNo.isEmpty() && !contNo.equals(item.getContainerId())) {
				SqlParameterSource param = new MapSqlParameterSource()
					.addValue("ContNo", null != legacyContNo && !legacyContNo.isEmpty() ? legacyContNo : item.getContainerId())
					.addValue("RefNo", item.getRefNo())
					.addValue("TrnDate", dateTime[0])
					.addValue("UserId", "")
					.addValue("SNo", count)
					.addValue("type", "")
					.addValue("time", dateTime[1])
					.addValue("reamrks", "R")
					.addValue("orderNo", item.getOrderNo());
				paramList.add(param);
				contNo = item.getContainerId();
				orderNo = orderNo + item.getOrderNo() + ",";
				count++;
			}
		}
		return paramList;
	}




	@Transactional
	public void sendDataInExportedTable(TransferConfigDTO transferConfigDTO) {
		foundationDataDao.sendWMSDataInExportedTable(transferConfigDTO);
	}


	public List<ItemMasterDTO> getItemMasterData(String goLiveDate) {
		return foundationDataDao.getItemMasterData(goLiveDate);
	}

	
	public List<ItemMasterDTO> getMissingItemsData(String databaseName) {
		return foundationDataDao.getMissingItemsData(databaseName);
	}
	
	public void truncateResaItems() {
		foundationDataDao.truncateResaItems();
	}
	

	
	public List<MfcsSubclass> getDeptClassSubclassData(String groupCode) {
		return foundationDataDao.getDeptClassSubclassData(groupCode);
	}
	
	public List<ItemMasterDTO> getItemData(String goLiveDate) {
		return foundationDataDao.getItemData(goLiveDate);
	}
	
	public List<ItemMasterDTO> getItemLocRangingData(String goLiveDate) {
		return foundationDataDao.getItemLocRangingData(goLiveDate);
	}
	
	public String getBrandName(String itemCode) {
		String brandName = foundationDataDao.getBrandName(itemCode);
		if(null != brandName && !brandName.trim().isEmpty()) {
			return brandName;
		} else {
			return "NA";
		}
	}

	public String getSalesPrice(String itemCode) {
		return foundationDataDao.getSalesPrice(itemCode);
	}
	
	public String getAllocationSize(String itemCode) {
		return foundationDataDao.getAllocationSize(itemCode);
	}
	
	public void setExportedItems(ItemMasterConfigDTO itemMasterConfigDTO) {
		foundationDataDao.setExportedItems(itemMasterConfigDTO);
	}


	
	public List<UdaDTO> getContNoRefNo(String sql) {
		List<UdaDTO> projectCodeAndBatchNo = foundationDataDao.getContNoRefNo(sql);
		return projectCodeAndBatchNo;
	}
	
	public String formatDateTime(String entryDate) throws ParseException {
		Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entryDate);
		String trndate =  new SimpleDateFormat("dd/MM/yy").format(tranDate);
		return trndate;
	}
	
	public void persistShipmentsData(PartialShipmentResponse partialShipmentResponse) throws Exception {
			Map<Integer, List<PurchaseOrdersDTO>> parsePartialShipmentMap = parsePurchaseOrderData(partialShipmentResponse);
			int count = 0;
			int sn = 0;
			for(Map.Entry<Integer, List<PurchaseOrdersDTO>> entry : parsePartialShipmentMap.entrySet()) {
				Integer key = entry.getKey();
				if(key == 0 || count == parsePartialShipmentMap.size() - 1) {
					List<PurchaseOrdersDTO> existingPODATA = foundationDataDao.getShipmentDetails(entry.getValue().get(0).getOrderNo(), entry.getValue().get(0).getLegacyPONum(), String.valueOf(key));
					if(null != existingPODATA && existingPODATA.size() > 0) {
						double amount = 0;
						double vatAmt = 0;
						int counter = 0;
						String groupcode = "";
						String refNo = "";
						String jobNo = "";
						String bolNo = "";
						for(int i = 0; i < entry.getValue().size(); i++) {
							PurchaseOrdersDTO purchaseOrdersDTO = entry.getValue().get(i);
							purchaseOrdersDTO.setJobNo(entry.getValue().get(i).getGroupCode() + "-" + entry.getValue().get(i).getOrderNo());
							purchaseOrdersDTO.setBolNo(entry.getValue().get(i).getBolNo());
							purchaseOrdersDTO.setGroupCode(entry.getValue().get(i).getGroupCode());
							purchaseOrdersDTO.setCatCode(entry.getValue().get(i).getGroupCode());
							purchaseOrdersDTO.setQty(entry.getValue().get(i).getQty());
							purchaseOrdersDTO.setAmount(entry.getValue().get(i).getAmount());
							purchaseOrdersDTO.setPalletCnt("0");
							purchaseOrdersDTO.setLoadQty("0");
							purchaseOrdersDTO.setDiscount("0");
							purchaseOrdersDTO.setDiscountPerc("0");
							amount = amount + Double.parseDouble(entry.getValue().get(i).getAmount());
							vatAmt = vatAmt + Double.parseDouble(entry.getValue().get(i).getVatAmt());
							refNo = entry.getValue().get(i).getRefNo();
							if(null != entry.getValue().get(i).getBolNo() && !entry.getValue().get(i).getBolNo().isEmpty()) {
								foundationDataDao.updateUSAPurchaseOrderBOLDetails(existingPODATA.get(0).getSN(), purchaseOrdersDTO.getGroupCode(), purchaseOrdersDTO.getAmount(), purchaseOrdersDTO.getQty(), purchaseOrdersDTO.getBolNo());
								if(counter == entry.getValue().size() - 1) {
									bolNo = bolNo + entry.getValue().get(i).getBolNo();
								} else if(!bolNo.contains(entry.getValue().get(i).getBolNo())) {
									bolNo = bolNo + entry.getValue().get(i).getBolNo() + "','";
								}
								if(counter == entry.getValue().size() - 1) {
									groupcode = groupcode + entry.getValue().get(i).getGroupCode();
								} else if(!groupcode.contains(entry.getValue().get(i).getGroupCode())) {
									groupcode = groupcode + entry.getValue().get(i).getGroupCode() + "','";
								}
							} else {
								if(counter == entry.getValue().size() - 1) {
									groupcode = groupcode + entry.getValue().get(i).getGroupCode();
									jobNo = jobNo + entry.getValue().get(i).getJobNo();
								} else if(!groupcode.contains(entry.getValue().get(i).getGroupCode())) {
									groupcode = groupcode + entry.getValue().get(i).getGroupCode() + "','";
									jobNo = jobNo + entry.getValue().get(i).getJobNo() + "','";
								}
								foundationDataDao.updateUSAPurchaseOrderDetails(existingPODATA.get(0).getSN(), purchaseOrdersDTO.getGroupCode(), purchaseOrdersDTO.getAmount(), purchaseOrdersDTO.getQty(), purchaseOrdersDTO.getJobNo());
							}
							counter++;
						}
						if(null != groupcode && !groupcode.isEmpty() && bolNo.isEmpty()) {
							foundationDataDao.updatePurchaseDetailsOrder(existingPODATA.get(0).getSN(), groupcode);
						}
						if(null != bolNo && !bolNo.isEmpty()) {
							foundationDataDao.updateBOLNoOrderDetails(existingPODATA.get(0).getSN(), bolNo);
						}
						
						if(null != jobNo && !jobNo.isEmpty()) {
							foundationDataDao.updateJobNoOrderDetails(existingPODATA.get(0).getSN(), jobNo);
						}
						
						groupcode = (groupcode.length() < 99) ? groupcode : groupcode.substring(0, 99);
						if(null != refNo && !refNo.isEmpty()) {
							foundationDataDao.updateRefNoForPurchaseOrder(refNo, existingPODATA.get(0).getORAPONo(), existingPODATA.get(0).getSN(), amount, vatAmt, 
									groupcode.replaceAll("'", ""), key == 0 ? "" : String.valueOf(key), parsePartialShipmentMap.size() > 1 ? "SPLIT" : "");
						} else {
							foundationDataDao.updateRefNoForPurchaseOrder("", existingPODATA.get(0).getORAPONo(), existingPODATA.get(0).getSN(), amount, vatAmt, 
									groupcode.replaceAll("'", ""), key == 0 ? "" : "", parsePartialShipmentMap.size() > 1 ? "SPLIT" : "");
						}
					} else {
						existingPODATA = foundationDataDao.getShipmentDetails(entry.getValue().get(0).getOrderNo(), entry.getValue().get(0).getLegacyPONum(), "");
						if(null != existingPODATA && existingPODATA.size() > 0) {
							Boolean checkShipmentStatus = foundationDataDao.getPurchaseOrderShipmentData(existingPODATA.get(0).getORAPONo(), String.valueOf(key));
							if(!checkShipmentStatus) {
								List<PurchaseOrdersDTO> purchaseOrderHeadersSN = foundationDataDao.getPurchaseOrderHeadersSN();
								if(null != purchaseOrderHeadersSN && purchaseOrderHeadersSN.size() > 0) {
									sn = Integer.parseInt(purchaseOrderHeadersSN.get(0).getSN());
									sn = sn + 1;
								} else {
									sn = Integer.parseInt("1");
								}
								String srNo = existingPODATA.get(0).getSN();
								String refNo = entry.getValue().get(0).getRefNo();
								String shipment = String.valueOf(key);
								foundationDataDao.persistPurchaseOrdersDATA(sn, srNo, refNo, shipment);
								double amount = 0;
								double vatAmt = 0;
								String groupcode = "";
								String jobNo = "";
								int counter = 0;
								String bolNo = "";
								List<PurchaseOrdersDTO> listOfPurchaseOrderDetails = new ArrayList<PurchaseOrdersDTO>();
								for(int i = 0; i < entry.getValue().size(); i++) {
									PurchaseOrdersDTO purchaseOrdersDTO = entry.getValue().get(i);
									if(null != entry.getValue().get(i).getBolNo() && !entry.getValue().get(i).getBolNo().isEmpty()) {
										purchaseOrdersDTO.setBolNo(entry.getValue().get(i).getBolNo());
									} else {
										purchaseOrdersDTO.setJobNo(entry.getValue().get(i).getGroupCode() + "-" + entry.getValue().get(i).getOrderNo());
									}
									purchaseOrdersDTO.setGroupCode(entry.getValue().get(i).getGroupCode());
									purchaseOrdersDTO.setCatCode(entry.getValue().get(i).getGroupCode());
									purchaseOrdersDTO.setQty(entry.getValue().get(i).getQty());
									purchaseOrdersDTO.setAmount(entry.getValue().get(i).getAmount());
									purchaseOrdersDTO.setPalletCnt("0");
									purchaseOrdersDTO.setLoadQty("0");
									purchaseOrdersDTO.setDiscount("0");
									purchaseOrdersDTO.setDiscountPerc("0");
									amount = amount + Double.parseDouble(entry.getValue().get(i).getAmount());
									vatAmt = vatAmt + Double.parseDouble(entry.getValue().get(i).getVatAmt());
									if(null != entry.getValue().get(i).getBolNo() && !entry.getValue().get(i).getBolNo().isEmpty()) {
										if(counter == entry.getValue().size() - 1) {
											bolNo = bolNo + entry.getValue().get(i).getBolNo();
										} else if(!bolNo.contains(entry.getValue().get(i).getBolNo())) {
											bolNo = bolNo + entry.getValue().get(i).getBolNo() + "','";
										}
										if(counter == entry.getValue().size() - 1) {
											groupcode = groupcode + entry.getValue().get(i).getGroupCode();
										} else if(!groupcode.contains(entry.getValue().get(i).getGroupCode())) {
											groupcode = groupcode + entry.getValue().get(i).getGroupCode() + "','";
										}
									} else {
										if(counter == entry.getValue().size() - 1) {
											groupcode = groupcode + entry.getValue().get(i).getGroupCode();
											jobNo = jobNo + entry.getValue().get(i).getJobNo();
										} else if(!groupcode.contains(entry.getValue().get(i).getGroupCode())) {
											groupcode = groupcode + entry.getValue().get(i).getGroupCode() + "','";
											jobNo = jobNo + entry.getValue().get(i).getJobNo() + "','";
										}
									}
									counter++;
									listOfPurchaseOrderDetails.add(purchaseOrdersDTO);
								}

								
								foundationDataDao.persistPODetailsData(listOfPurchaseOrderDetails, sn);
								groupcode = (groupcode.length() < 99) ? groupcode : groupcode.substring(0, 99);
								foundationDataDao.updatePOHeaderData(sn, amount, vatAmt, groupcode.replaceAll("'", ""));
							}
						}
					}
				}
				count++;
			}

	}
	
}