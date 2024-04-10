package com.bfl.service;

import java.util.List;
import java.util.Map;

import com.bfl.dto.*;
import com.bfl.gencode.merchhier.PartialShipment.PartialShipmentResponse;
import com.bfl.gencode.merchhier.upcbarcodes.response.UpcBarcodeResponse;
import com.bfl.gencode.resa.MissingResaItems.MissingResaItemsResponse;

public interface IFoundationWMSDataService {

	public String getLastProcessingTimestamp(Long jobId);

	public void updateLastProcessingTimestamp(String timestamp, Long jobId, String jobName);
	
	public void insertLastProcessingTimestamp(String timestamp, Long jobId, String jobName);

	public void persistDepartments(List<DepartmentDto> departments);
	
	public void persistBrands(List<BrandMasterDTO> departments);

	
	public void persistCategories(List<CategoryDTO> categories);

	
	public void persistPurchaseOrdersData(List<PurchaseOrdersDTO> purchaseOrdersData);


	public void persistSlashingforAllCostCenters(Map<String, List<ClearancesDTO>> pricingData) throws Exception;


	public void persistMerchHierarchyData(List<DepartmentDto> deptDTO);


	public void persistItemAttributes(String contNo);
	public void persistPriceChangeDataForAllCostcenters(Map<String, List<ClearancesDTO>> pricingData) throws Exception;


	public ContainerDetailsDTO getLegacyContainerNo(String contNo);
	
	public Boolean getContainerUsaOrgfileDetails(String contNo, String tableName);
	
	public void persistUpcBarcodes(UpcBarcodeResponse response, List<UpcBarcodesDTO> upcBarcodeData, String legacyContNo, String contNo, List<ContainerDetailsDTO> listOfContData);
	

	public void persistPurchaseOrderData(List<ContainerDetailsDTO> listOfContData) throws Exception;
	

	public void persistShipmentUploadData(UpcBarcodeResponse response, String contno);
	

	public void sendDataInExportedTable(TransferConfigDTO transferConfigDTO);

	
	public List<ItemMasterDTO> getItemMasterData(String goLiveDate);

	public List<ItemMasterDTO> getMissingItemsData(String databaseName);
	
	public void truncateResaItems();


	public List<MfcsSubclass> getDeptClassSubclassData(String groupCode);
	
	public List<ItemMasterDTO> getItemData(String goLiveDate);
	
	public List<ItemMasterDTO> getItemLocRangingData(String goLiveDate);

	public String getBrandName(String itemCode);

	public String getSalesPrice(String itemCode);
	
	public String getAllocationSize(String itemCode);

	public void setExportedItems(ItemMasterConfigDTO itemMasterConfigDTO);

	public List<UdaDTO> getContNoRefNo(String sql);
	
	public void persistShipmentsData(PartialShipmentResponse response) throws Exception;

	public void setUsaContnoMapping(UpcBarcodeResponse response, String rmsContNo, String legacyContNo);

	public void persistMissingResaItems(MissingResaItemsResponse response);

	public void deleteCreatedItems(String itemCode);

	public List<BflIntegrationDataDTO> getBFLIntegrationData(String business_date);
	
	public void updateBFLIntegrationData(String business_date, String columnName, String columnValue);
	
	public void insertBFLIntegrationData(BflIntegrationDataDTO bflIntegrationDataDTO);

}
