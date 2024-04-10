package com.bfl.dao;

import java.util.Date;
import java.util.List;

import com.bfl.dto.*;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.bfl.gencode.merchhier.upcbarcodes.response.Item;

@Repository
public interface IFoundationWMSDataDao {

	public String getLastProcessingTimestamp(Long jobId);
	
	public void updateLastProcessingTimestamp(String timestamp, Long jobId, String jobName);
	
	public void insertLastProcessingTimestamp(String timestamp, Long jobId, String jobName);
	

	public void persistDepartments(DepartmentDto departments);
	
	public void persistSubClass(DepartmentDto departments);
	
	public void updateSubClass(DepartmentDto departmentDto);

	
	public void persistMerchHierarchy(DepartmentDto deptDTO);



	public List<MfcsSubclass> getDeptClassSubclassData(String groupCode);
	
	public void persistPurchaseOrdersHeadersData(PurchaseOrdersDTO purchaseOrdersData);
	

	public List<SubCategoryDTO> getSubCategory(SubCategoryDTO subCategory);
	
	public void persistSubCategory(SubCategoryDTO subCategory);
	
	public void persistPurchaseOrdersDetailsData(PurchaseOrdersDetailsDTO purchaseOrdersData);
	
	public void persistBOLOrdersDetailsData(PurchaseOrdersDetailsDTO purchaseOrdersData);
	
	public void persistPODetailsData(List<PurchaseOrdersDTO> purchaseOrdersDTO, int sn);


	public List<StoreDTO> getAllCountryDatabases(String countryName);
	
	public void persistClearancesData(ClearancesDTO clearancesDTO);
	

	public List<ClearancesDTO> getOldSalesPriceDataforCostCenter(ClearancesDTO itemLocDTO);
	
	public List<ClearancesDTO> getUSADeadStockData(ClearancesDTO clearancesDTO);
	
	public void persistUSAPriceChangeForClearancesData(ClearancesDTO clearances);
	
	public void persistUSAPriceChange(ClearancesDTO clearances);
	
	public void persistRFSalesPrice(ClearancesDTO clearances);
	

	public void updateRFSalesPrice(ClearancesDTO clearancesDTO);
	
	public List<ClearancesDTO> getRFSalesPrice(ClearancesDTO clearances);

	public List<ClearancesDTO> getSalesPriceData(ClearancesDTO clearancesDTO);
	
	public List<ClearancesDTO> getAllCostCentersSalesPriceData(ClearancesDTO clearancesDTO);

	public List<ClearancesDTO> getUSAPriceData(ClearancesDTO clearancesDTO);
	
	public List<ClearancesDTO> getTop1USAPriceData(ClearancesDTO clearancesDTO);
	
	public List<ClearancesDTO> getAEDPriceOfItem(String itemCode);
	

	public void persistOldSalesPriceForPriceChange(ClearancesDTO itemLocDTO);


	public void updateSalesPriceForClearancesData(ClearancesDTO clearancesDTO);
	
	public void updateSalesPriceForAllCostCenters(ClearancesDTO clearancesDTO);
	

	public List<ItemMasterDTO> checkItemForTCM(String itemCode, String groupCode);
	
	public void updatePurchaseOrderHeaders(PurchaseOrdersDTO purchaseOrders);
	
	public List<PurchaseOrdersDTO> getPurchaseOrderHeadersSN();
	

	public List<PurchaseOrdersDTO> getPurchaseOrderNo(String orderNo, String legacyPoNum);
	
	public List<PurchaseOrdersDTO> getOrdersPurchasedDetails(String orderNo, String contNo);
	
	public void updatePurchaseOrderDetails(PurchaseOrdersDetailsDTO purchaseOrders);
	
	public void updateBolOrderDetails(PurchaseOrdersDetailsDTO purchaseOrders);
	
	public void updateUSAPurchaseOrderDetails(String SN, String groupCode, String amount, String qty, String jobNo);
	
	public void updateUSAPurchaseOrderBOLDetails(String SN, String groupCode, String amount, String qty, String bolNo);
	
	public void persistDelPOHeadersData(String oldSN, String currentDate);
	
	public void persistDelPODetailsData(String oldSN, String currentDate);
	

	public void persistSalesPriceForClearancesData(ClearancesDTO clearancesDTO);

	
	public void persistItemAttributesForGroupWise(List<SqlParameterSource> param);

	
	public void persistBulkUSAOrgFile(List<SqlParameterSource> param);


	public void sendWMSDataInExportedTable(TransferConfigDTO transferConfigDTO);


	public List<ItemLocationDTO> checkItemGroup(String itemCode);
	
	public List<DepartmentDto> checkItemGroup(DepartmentDto departmentDto);
	
	public List<DepartmentDto> checkMerchHierarchy(DepartmentDto departmentDto);
	
	public List<ItemMasterDTO> getItemMasterData(String goLiveDate);

	public List<ItemMasterDTO> getMissingItemsData(String databaseName);
	
	public void truncateResaItems();
	

	public List<ItemMasterDTO> getItems(String itemCode);
	
	public List<ItemMasterDTO> getItemData(String goLiveDate);
	

	public List<ItemMasterDTO> getItemLocRangingData(String goLiveDate);

	
	public String getBrandName(String itemCode);
	
	public String getSalesPrice(String itemCode);

	public List<UdaDTO> getContNoRefNo(String sql);

	public void setExportedItems(ItemMasterConfigDTO itemMasterConfigDTO);
	

	public String getAllocationSize(String itemCode);

	public void persistBrands(BrandMasterDTO brand, Date entryDate);
	
	public List<BrandMasterDTO> getBrands(BrandMasterDTO brands);

	public void updateTCMItemsPrice(PriceChangeDTO tcmChangeDTO);

	public void persistContNos(SqlParameterSource paramList);
	

	public List<UsaContNoDTO> getContNos(String rmsContNo, String legacyContNo);
	

	public String getUsaContNos();
	
	public String getContReceipt();


	public void updateRefNoForPurchaseOrder(String refNo, String poNum, String sn, double amount, double vatAmt, String groupcode, String shipment, String POL);

	public List<ContainerDetailsDTO> getLegacyContainerNo(String contNo);
	
	public List<UpcBarcodesDTO> getContainerExistDetails(String contNo, String legacyContNo, String tableName);

	public void persistManifestItemAttributes(List<SqlParameterSource> param);

	public void persistdelRFSalesPrice(ClearancesDTO rfSalesPrice);
	
	public void updatedelRFSalesPrice(ClearancesDTO rfSalesPrice);
	
	public List<ClearancesDTO> getDelRFSalesPrice(ClearancesDTO rfSalesPrice);


	public List<PurchaseOrdersDTO> getSuppName(String suppCode);

	List<PriceChangeDTO> getTCMItemsPrice(String itemCode);
	
	public List<PriceChangeDTO> getTCMItemsSlashing(String itemCode);
	
	public void persistTCMItemsPrice(PriceChangeDTO tcmChangeDTO);
	
	public void persistTCMItemsSlashing(PriceChangeDTO tcmChangeDTO);


	public void updateUpcBarcodesColorData(String currentDate);
	
	public void updateUpcBarcodesGroupCodeData(String currentDate);
	
	public void updateUpcBarcodesStyleData(String currentDate);
	
	public void updateUpcBarcodesBrandData(String currentDate);
	
	public void updateUpcBarcodesItemNameData(String currentDate);
	
	public void updateItemMasterData(String currentDate);
	
	public void insertUpcBarcodesData(List<SqlParameterSource> param);
	
	public void persistTempBarcodesData(List<SqlParameterSource> param);
	
	public void persistTempItemAttributesData(List<SqlParameterSource> param);

	public List<UpcBarcodesDTO> getTempBarcodesData(String contNo);
	
	public List<UpcBarcodesDTO> getTempItemMasterData(String contNo);
	

	public void persistUpcBarcodesLogDTO(List<SqlParameterSource> param);

	public void truncateTempUpcBarcodesData();
	
	public void truncateTempItemAttributesData();


	public List<ItemAttributesDTO> getItemAttributesData(String contNo);

	public List<PurchaseOrdersDTO> getOrderDetails(String orderNo, String legacyPoNum);
	
	public List<PurchaseOrdersDTO> getShipmentDetails(String orderNo, String legacyPoNum, String shipment);


	public void persistPurchaseOrdersDATA(int newSN, String oldSN, String refNo, String shipment);


	public Boolean getPurchaseOrderShipmentData(String orapoNo, String shipment);

	public void updatePOHeaderData(int sn, double amount, double vatAmt, String groupcode);

	public void updatePurchaseDetailsOrder(String sn, String groupcode);
	
	public void updateBOLNoOrderDetails(String sn, String groupcode);
	
	public void updateJobNoOrderDetails(String sn, String jobNo);

	public void persistMissingResaItems(List<SqlParameterSource> paramList);

	public void deleteCreatedItems(String itemCode);

	public List<BflIntegrationDataDTO> getBFLIntegrationData(String business_date);
	
	public void insertBFLIntegrationData(BflIntegrationDataDTO bflIntegrationDataDTO);
	
	public void updateBFLIntegrationData(String business_date, String columnName, String columnValue);

}
