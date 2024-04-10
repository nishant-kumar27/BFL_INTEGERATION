package com.bfl.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bfl.dto.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.bfl.ConfigProperties;
import com.bfl.gencode.merchhier.upcbarcodes.response.Item;

@Repository
public class FoundationWMSDataDaoImpl implements IFoundationWMSDataDao {

	private static final Logger logger = LoggerFactory.getLogger(FoundationWMSDataDaoImpl.class); 

	@Autowired
	private NamedParameterJdbcTemplate appJdbcTemplate;


	@Override
	public void persistDepartments(DepartmentDto departmentDto) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "Insert into " + WHDataBase + "ItemGroup (GroupCode,Description,ShortName) values(:GroupCode, :Description, ' ')";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("GroupCode", departmentDto.getGroupCode())
			.addValue("Description", departmentDto.getGroupName())
			.addValue("ShortName", departmentDto.getShortName());

		appJdbcTemplate.update(sql, param);
		logger.info(departmentDto.getGroupCode() + " inserted successfully in ItemGroup table.");
	}
	
	@Override
	public void persistSubClass(DepartmentDto departmentDto) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "Insert into " + WHDataBase + "MFCS_SUBCLASS (Dept, Class, SubClass, SubClassId, GroupCode, supplier, suppCode, country) values(:Dept, :Class, :SubClass, :SubClassId, :GroupCode, :supplier, :supplierCode, :countryId)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("Dept", departmentDto.getDeptId())
			.addValue("Class", departmentDto.getClassId())
			.addValue("SubClass", departmentDto.getSubclassId())
			.addValue("SubClassId", departmentDto.getSubClassUniqueId())
			.addValue("GroupCode", departmentDto.getGroupCode())
			.addValue("supplier", departmentDto.getSupplier())
			.addValue("supplierCode", departmentDto.getSuppCode())
			.addValue("countryId", departmentDto.getCountry());;
		appJdbcTemplate.update(sql, param);
		logger.info(departmentDto.getGroupCode() + " inserted successfully in MFCS_SUBCLASS table.");
	}

	
	@Override
	public void updateSubClass(DepartmentDto departmentDto) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "update " + WHDataBase + "MFCS_SUBCLASS set Dept=:Dept, Class=:Class, SubClass=:SubClass, SubClassId=:SubClassId, "
					+ " supplier=:supplier, suppCode=:supplierCode, country=:countryId where GroupCode=:groupCode";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("Dept", departmentDto.getDeptId())
			.addValue("Class", departmentDto.getClassId())
			.addValue("SubClass", departmentDto.getSubclassId())
			.addValue("SubClassId", departmentDto.getSubClassUniqueId())
			.addValue("groupCode", departmentDto.getGroupCode())
			.addValue("supplier", departmentDto.getSupplier())
			.addValue("supplierCode", departmentDto.getSuppCode())
			.addValue("countryId", departmentDto.getCountry());
		int update = appJdbcTemplate.update(sql, param);
		if(update == 0) {
			persistSubClass(departmentDto);
		}
		logger.info(departmentDto.getGroupCode() + " updated successfully in MFCS_SUBCLASS table.");
	}

	@Override
	public void persistMerchHierarchy(DepartmentDto departmentDTO) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching count from Application properties");
		}
		String sql = "Insert into " + WHDataBase + "usapriority(groupCode, Grpname, GenCat, Department, Gender, Brand, DivisionY, Consignment, MultiBrand, Supplier_Code, WasPerc, LastUpdateDt) values(:groupCode, :Grpname, :GenCat, :Department, :Gender, :Brand, :DivisionY, :Consignment, :MultiBrand, :Supplier_Code, :WasPerc, :LastUpdateDt)";
//		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
//		for(DepartmentDto departmentDTO : deptDTO) {
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("groupCode", departmentDTO.getGroupCode()) 
			.addValue("Grpname", departmentDTO.getGroupName())
			.addValue("GenCat", departmentDTO.getSubCategory()) 
			.addValue("Department", departmentDTO.getDeptName())
			.addValue("LastUpdateDt" , departmentDTO.getUpdateDateTime())
			.addValue("Gender", departmentDTO.getGender())
			.addValue("Brand", departmentDTO.getBrand())
			.addValue("DivisionY", departmentDTO.getDivision())
			.addValue("Consignment", departmentDTO.getConsignment())
			.addValue("WasPerc", departmentDTO.getWasPerc())
			.addValue("Supplier_Code", departmentDTO.getSupplier())
			.addValue("MultiBrand", departmentDTO.getMultiBrand());
//			paramList.add(param);
//		}
		logger.info("Inserting Merch Hierarchy in usapriority table.");
		appJdbcTemplate.update(sql, param);
//		int [] records = appJdbcTemplate.batchUpdate(sql, paramList.toArray(new SqlParameterSource[0]));
//		logger.info("Total Merch Hierarchy records insterted: " + records.length);
	}


	@Override
	public List<MfcsSubclass> getDeptClassSubclassData(String groupCode) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "select Dept, groupCode, subclass, class _class, supplier, suppCode, country suppCountry from " + TRAN_FLAG_DB + "MFCS_SUBCLASS WHERE groupCode = '" + groupCode + "'";
		List<MfcsSubclass> subclassData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(MfcsSubclass.class));
		return subclassData;
	}
	

	@Override
	public List<ItemAttributesDTO> getItemAttributesData(String contNo) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
//		String sql = "Select SubCat, trndate from USA..Subcategory with (nolock) where UPPER(SubCat)=UPPER('" + subCategory.getSubclassName() + "')";
		String sql = "SELECT CONTNO, UPC, ItemName, GroupCode, Style, Color, Size, TrnDate, 'Y' Upload, 'R' Remarks, Brand, Gender, Season, subcategory, SUM(StyleQty) StyleQty FROM RMS..Temp_ItemAttibutes "
				+ "with (nolock) WHERE CONTNO in ('" + contNo + "') group by CONTNO, UPC, ItemName, GroupCode, Style, Color, Size, TrnDate, brand, Gender, Season, subcategory";
		List<ItemAttributesDTO> itemAttributes = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemAttributesDTO.class));
		return itemAttributes;
	}
	
	@Override
	public List<PurchaseOrdersDTO> getOrderDetails(String orderNo, String legacyPoNum) {
		String sql = "SELECT UOH.SN, UOH.Approved_Date Approved_Date, UOH.Currency, UOH.DueDate, UOH.InvoiceNo, UOH.OthersPath, UOH.PaymentTerms, UOH.Remarks, "
				+ " UOH.SuppCode, UOH.Tentative_ShipmentDt, UOH.ORAPONO ORAPONo, UOD.GroupCode GroupCode, UOD.Qty Qty, UOD.Amount Amount FROM HODATA..USAORDERHEADER UOH with (nolock) "
				+ "JOIN HODATA..USAORderDetail UOD ON (UOD.SN = UOH.SN) WHERE (ORAPONO = '" + orderNo + "' or ORAPONO = '" + legacyPoNum + "') and SBLC = '' order by SN";
		List<PurchaseOrdersDTO> purchaseOrderDTO = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PurchaseOrdersDTO.class));
		return purchaseOrderDTO;
	}
	
	@Override
	public List<PurchaseOrdersDTO> getShipmentDetails(String orderNo, String legacyPoNum, String shipment) {
		String sql = "SELECT UOH.SN, UOH.Approved_Date Approved_Date, UOH.Currency, UOH.DueDate, UOH.InvoiceNo, UOH.OthersPath, UOH.PaymentTerms, UOH.Remarks, "
				+ " UOH.SuppCode, UOH.Tentative_ShipmentDt, UOH.ORAPONO ORAPONo, UOD.GroupCode GroupCode, UOD.Qty Qty, UOD.Amount Amount FROM HODATA..USAORDERHEADER UOH with (nolock) "
				+ "JOIN HODATA..USAORderDetail UOD ON (UOD.SN = UOH.SN) WHERE (ORAPONO = '" + orderNo + "' or ORAPONO = '" + legacyPoNum + "')";
		if(null != shipment && !shipment.isEmpty()) {
			sql = sql + " and SBLC = '" + shipment + "' order by SN";
		} else {
			sql = sql + " order by SN";
		}
		List<PurchaseOrdersDTO> purchaseOrderDTO = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PurchaseOrdersDTO.class));
		return purchaseOrderDTO;
	}


	public void persistPurchaseOrdersDATA(int newSN, String oldSN, String refNo, String shipment) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		logger.info("Inserting purchase Order data in persistPurchaseOrdersHeadersData table.");
		String sql = "Insert into HODATA..Usaorderheader(SN, EntryDate,  InvoiceDate, InvoiceNo,  Remarks,  Userid,  ContNo,  StackingFee,  InvAmount,  CommAmount, CommPerc,  PaidAmount,  DueDate,  Winter,  OtherDesc,  Export,  ExportDesc,  SuppCode,  Currency,  NonPayment,  ShoesCat,  ItemGroups,  SBLC,  Country,  EtaDate,  EtdDate, AtaDate,  BLNo,  PickupDate,  InvPath,  ChqPath,  ChqPath2,  ChqPath3,  ReceiptPath,  ReceiptPath2,  ReceiptPath3,  OthersPath,  VatAmt,  VatJvNo,  EmailDt,  ORAPONo,  noofCont,  Approved_Date,  paymentTerms,  Tentative_ShipmentDt,  claim_Period,  ProdLeadTime,  POLineStatus,  Order_Changed,  PromisedTT,  ActualTT,  Discount,  POL,  FF,  Cargo_Size,  volume,  Cust_Decl,  ft40,  ft20,  lcl,  AssignedTo,  AirShipment) " +
					" (SELECT :newSN, EntryDate,  InvoiceDate, InvoiceNo,  Remarks,  Userid, :refNo,  StackingFee,  InvAmount,  CommAmount, CommPerc,  PaidAmount,  DueDate,  Winter,  OtherDesc,  Export,  ExportDesc,  SuppCode,  Currency,  NonPayment,  ShoesCat,  ItemGroups, :shipment,  Country,  EtaDate,  EtdDate, AtaDate,  BLNo,  PickupDate,  InvPath,  ChqPath,  ChqPath2,  ChqPath3,  ReceiptPath,  ReceiptPath2,  ReceiptPath3,  OthersPath,  VatAmt,  VatJvNo,  EmailDt,  ORAPONo,  noofCont,  Approved_Date,  paymentTerms,  Tentative_ShipmentDt,  claim_Period,  ProdLeadTime,  POLineStatus,  Order_Changed,  PromisedTT,  ActualTT,  Discount,  'SPLIT',  FF,  Cargo_Size,  volume,  Cust_Decl,  ft40,  ft20,  lcl,  AssignedTo,  AirShipment FROM HODATA..USAORderHeader WHERE SN=:oldSN)";
		SqlParameterSource param = new MapSqlParameterSource().
			addValue("oldSN", oldSN).
			addValue("newSN", newSN).
			addValue("refNo", refNo).
			addValue("shipment", shipment);
		int records = appJdbcTemplate.update(sql, param);
		logger.info("purchase Order data records insterted: " + records);
	}
	
	public void updatePOHeaderData(int sn, double amount, double vatAmt, String groupcode) {
		logger.info("updating purchase Order data in persistPurchaseOrdersHeadersData table.");
		String sql = "update HODATA..Usaorderheader set InvAmount=:amount, VatAmt=:vat, ItemGroups=:groupCode WHERE SN=:SN ";
		SqlParameterSource param = new MapSqlParameterSource().
			addValue("amount", amount).
			addValue("vat", vatAmt).
			addValue("SN", sn).
			addValue("groupCode", groupcode);
		int records = appJdbcTemplate.update(sql, param);
		logger.info("purchase Order data records updated: " + records);
	}
	
	public void updatePurchaseDetailsOrder(String sn, String groupcode) {
		logger.info("updating purchase Order details data in ");
		String sql = "update HODATA..USAORderDetail set qty=0, amount=0 WHERE SN=:SN AND groupCode not in ('" + groupcode + "')";
		SqlParameterSource param = new MapSqlParameterSource().
			addValue("SN", sn);
		int records = appJdbcTemplate.update(sql, param);
		logger.info("purchase Order data records updated: " + records);
	}
	
	public void updateBOLNoOrderDetails(String sn, String jobNo) {
		logger.info("updating purchase Order details data in ");
		String sql = "update HODATA..USAORderDetail set qty=0, amount=0 WHERE SN=:SN AND jobNo not in ('" + jobNo + "')";
		SqlParameterSource param = new MapSqlParameterSource().
			addValue("SN", sn);
		int records = appJdbcTemplate.update(sql, param);
		logger.info("purchase Order data records updated: " + records);
	}
	
	public void updateJobNoOrderDetails(String sn, String jobNo) {
		logger.info("updating purchase Order details data for job no");
		String sql = "update HODATA..USAORderDetail set qty=0, amount=0 WHERE SN=:SN AND jobNo not in ('" + jobNo + "')";
		SqlParameterSource param = new MapSqlParameterSource().
			addValue("SN", sn);
		int records = appJdbcTemplate.update(sql, param);
		logger.info("purchase Order data records updated: " + records);
	}
	
	public void persistMissingResaItems(List<SqlParameterSource> paramList) {
		String sql =  "Insert into RMS..missing_resa_items(itemcode) values (:itemCode)";
		logger.info("Inserting data in missingResaItems table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, paramList.toArray(new SqlParameterSource[0]));
		logger.info("Total missingResaItems records inserted: " + records.length);
	}
	
	public void deleteCreatedItems(String itemCode) {
		String sql =  "DELETE FROM RMS..missing_resa_items WHERE itemcode=:itemCode";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("itemCode", itemCode);
		appJdbcTemplate.update(sql, param);
	}
	
	public List<BflIntegrationDataDTO> getBFLIntegrationData(String business_date) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "SELECT CONTAINER_MAPPING containerMapping, BUSINESS_DATE businessDate, ITEM_GROUPS itemGroups, ITEMS items, ITEM_RECLASSIFY itemReclassify, "
					+ " ORDER_NEW orderNew, ORDER_MOD orderMod, ORDER_CANCELLED orderCancelled, SHIPMENTS shipments, "
					+ " CONTAINER_ID containerId, MANIFEST_UPLOAD manifestUpload, PRICE_CHANGES priceChanges, PROMOTIONS promotions, CLEARANCES slashing, BRANDS brands, "
					+ " SUPPLIERS suppliers, DIVISION division, DEPT dept, CATEGORY category, SUBCATEGORY subCategory From " + TRAN_FLAG_DB + "BFL_INTEGRATION_DATA "
					+ " WHERE business_date = '" + business_date + "'";
		List<BflIntegrationDataDTO> bflIntegrationDataDTOs = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BflIntegrationDataDTO.class));
		return bflIntegrationDataDTOs;
	}
	
	public void updateBFLIntegrationData(String business_date, String columnName, String columnValue) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
//		String sql =  "Insert into " + WHDataBase + "MFCS_SUBCLASS (Dept, Class, SubClass, SubClassId, GroupCode) values(:Dept, :Class, :SubClass, :SubClassId, :GroupCode)";
		String sql = "update " + WHDataBase + "BFL_INTEGRATION_DATA set " + columnName + "= '" + columnValue + "' "
					+ " WHERE business_date = '" + business_date + "'";
		SqlParameterSource param = new MapSqlParameterSource();
		appJdbcTemplate.update(sql, param);
		logger.info("updated bfl Integration data table for column " + columnName);
	}
	
	public void insertBFLIntegrationData(BflIntegrationDataDTO bflIntegrationDataDTO) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "Insert into " + WHDataBase + "BFL_INTEGRATION_DATA (CONTAINER_MAPPING, BUSINESS_DATE, ITEM_GROUPS, ITEMS, ITEM_RECLASSIFY, ORDER_NEW, ORDER_MOD, ORDER_CANCELLED, SHIPMENTS, CONTAINER_ID, MANIFEST_UPLOAD, PRICE_CHANGES, PROMOTIONS, CLEARANCES, BRANDS, SUPPLIERS, DIVISION, DEPT, CATEGORY, SUBCATEGORY) "
					+ " values(:CONTAINER_MAPPING, :BUSINESS_DATE, :ITEM_GROUPS, :ITEMS, :ITEM_RECLASSIFY, :ORDER_NEW, :ORDER_MOD, :ORDER_CANCELLED, :SHIPMENTS, :CONTAINER_ID, :MANIFEST_UPLOAD, :PRICE_CHANGES, :PROMOTIONS, :CLEARANCES, :BRANDS, :SUPPLIERS, :DIVISION, :DEPT, :CATEGORY, :SUBCATEGORY)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("CONTAINER_MAPPING", bflIntegrationDataDTO.getContainerMapping())
			.addValue("BUSINESS_DATE", bflIntegrationDataDTO.getBusinessDate())
			.addValue("ITEM_GROUPS", bflIntegrationDataDTO.getItemGroups())
			.addValue("ITEMS", bflIntegrationDataDTO.getItems())
			.addValue("ITEM_RECLASSIFY", bflIntegrationDataDTO.getItemReclassify())
			.addValue("ORDER_NEW", bflIntegrationDataDTO.getOrderNew())
			.addValue("ORDER_MOD", bflIntegrationDataDTO.getOrderMod())
			.addValue("ORDER_CANCELLED", bflIntegrationDataDTO.getOrderCancelled())
			.addValue("SHIPMENTS", bflIntegrationDataDTO.getShipments())
			.addValue("CONTAINER_ID", bflIntegrationDataDTO.getContainerId())
			.addValue("MANIFEST_UPLOAD", bflIntegrationDataDTO.getManifestUpload())
			.addValue("PRICE_CHANGES", bflIntegrationDataDTO.getPriceChanges())
			.addValue("PROMOTIONS", "0")
			.addValue("CLEARANCES", bflIntegrationDataDTO.getSlashing())
			.addValue("BRANDS", bflIntegrationDataDTO.getBrands())
			.addValue("SUPPLIERS", bflIntegrationDataDTO.getSuppliers())
			.addValue("DIVISION", bflIntegrationDataDTO.getDivision())
			.addValue("DEPT", bflIntegrationDataDTO.getDept())
			.addValue("CATEGORY", bflIntegrationDataDTO.getCategory())
			.addValue("SUBCATEGORY", bflIntegrationDataDTO.getSubCategory());
		appJdbcTemplate.update(sql, param);
		logger.info("Inserted into bfl Integration data table..");
	}
	

	@Override
	public Boolean getPurchaseOrderShipmentData(String oraPoNo, String shipment) {
		Boolean response = false;
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		
		String sql = "SELECT UOH.SN, UOH.Approved_Date Approved_Date, UOH.Currency, UOH.DueDate, UOH.InvoiceNo, UOH.OthersPath, UOH.PaymentTerms, UOH.Remarks, "
				+ " UOH.SuppCode, UOH.Tentative_ShipmentDt, UOH.ORAPONO ORAPONo FROM HODATA..USAORDERHEADER UOH with (nolock) "
				+ " WHERE ORAPONO = '" + oraPoNo + "' and SBLC = '" + shipment + "'";
		List<PurchaseOrdersDTO> purchaseOrderDTO = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PurchaseOrdersDTO.class)); 
		
		if(null != purchaseOrderDTO && purchaseOrderDTO.size() > 0) {
			response = true;
		} else {
			response = false;
		}
		return response;
	}
	
	@Override
	public List<SubCategoryDTO> getSubCategory(SubCategoryDTO subCategory) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "Select SubCat, trndate from USA..Subcategory with (nolock) where UPPER(SubCat)=UPPER('" + subCategory.getSubclassName() + "')";
		List<SubCategoryDTO> subclassData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubCategoryDTO.class));
		return subclassData;
	}

	@Override
	public void persistSubCategory(SubCategoryDTO subCategory) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "INSERT INTO " + WHDataBase + "Subcategory(SubCat, trndate, remarks) values (:SubCatName, :trndate, :remarks)";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("SubCatName", subCategory.getSubclassName())
			.addValue("remarks", subCategory.getRemarks())
			.addValue("trndate", subCategory.getTrnDate())
			.addValue("userId", subCategory.getUserId());
		appJdbcTemplate.update(sql, parameters);
	}
	
	@Override
	public void persistPurchaseOrdersHeadersData(PurchaseOrdersDTO purchaseOrdersData) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "Insert into " + WHDataBase + "Usaorderheader(SN, EntryDate,  InvoiceDate, InvoiceNo,  Remarks,  Userid,  ContNo,  StackingFee,  InvAmount,  CommAmount, CommPerc,  PaidAmount,  DueDate,  Winter,  OtherDesc,  Export,  ExportDesc,  SuppCode,  Currency,  NonPayment,  ShoesCat,  ItemGroups,  SBLC,  Country,  EtaDate,  EtdDate, AtaDate,  BLNo,  PickupDate,  InvPath,  ChqPath,  ChqPath2,  ChqPath3,  ReceiptPath,  ReceiptPath2,  ReceiptPath3,  OthersPath,  VatAmt,  VatJvNo,  EmailDt,  ORAPONo,  noofCont,  Approved_Date,  paymentTerms,  Tentative_ShipmentDt,  claim_Period,  ProdLeadTime,  POLineStatus,  Order_Changed,  PromisedTT,  ActualTT,  Discount,  POL,  FF,  Cargo_Size,  volume,  Cust_Decl,  ft40,  ft20,  lcl,  AssignedTo,  AirShipment) values(:SN, :EntryDate,  :InvoiceDate, :InvoiceNo,  :Remarks,  :Userid,  :ContNo,  :StackingFee,  :InvAmount,  :CommAmount, :CommPerc,  :PaidAmount,  :DueDate,  :Winter,  :OtherDesc,  :Export,  :ExportDesc,  :SuppCode,  :Currency,  :NonPayment,  :ShoesCat,  :ItemGroups,  :SBLC,  :Country,  :EtaDate,  :EtdDate, :AtaDate,  :BLNo,  :PickupDate,  :InvPath,  :ChqPath,  :ChqPath2,  :ChqPath3,  :ReceiptPath,  :ReceiptPath2,  :ReceiptPath3,  :OthersPath,  :VatAmt,  :VatJvNo,  :EmailDt,  :ORAPONo,  :noofCont,  :Approved_Date,  :paymentTerms,  :Tentative_ShipmentDt,  :claim_Period,  :ProdLeadTime,  :POLineStatus,  :Order_Changed,  :PromisedTT,  :ActualTT,  :Discount,  :POL,  :FF,  :Cargo_Size,  :volume,  :Cust_Decl,  :ft40,  :ft20,  :lcl,  :AssignedTo,  :AirShipment)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", purchaseOrdersData.getSN())
			.addValue("EntryDate", purchaseOrdersData.getEntryDate())
			.addValue("InvoiceDate", purchaseOrdersData.getInvoiceDate())
			.addValue("InvoiceNo", purchaseOrdersData.getInvoiceNo())
			.addValue("Remarks", purchaseOrdersData.getRemarks())
//			.addValue("Userid", purchaseOrdersData.getUserid())
			.addValue("Userid", "")
			.addValue("ContNo", purchaseOrdersData.getContNo())
			.addValue("StackingFee", purchaseOrdersData.getStackingFee())
			.addValue("InvAmount", purchaseOrdersData.getInvAmount())
			.addValue("CommAmount", purchaseOrdersData.getCommAmount())
			.addValue("CommPerc", purchaseOrdersData.getCommPerc())
			.addValue("PaidAmount", purchaseOrdersData.getPaidAmount())
			.addValue("DueDate", purchaseOrdersData.getDueDate())
			.addValue("Winter", purchaseOrdersData.getWinter())
			.addValue("OtherDesc", purchaseOrdersData.getOtherDesc())
			.addValue("Export", purchaseOrdersData.getExport())
			.addValue("ExportDesc", purchaseOrdersData.getExportDesc())
			.addValue("SuppCode", purchaseOrdersData.getSuppCode())
			.addValue("Currency", purchaseOrdersData.getCurrency())
			.addValue("NonPayment", purchaseOrdersData.getNonPayment())
			.addValue("ShoesCat", purchaseOrdersData.getShoesCat())
			.addValue("ItemGroups", (purchaseOrdersData.getItemGroups().length() < 99) ? purchaseOrdersData.getItemGroups() : purchaseOrdersData.getItemGroups().substring(0, 99))
			.addValue("SBLC", purchaseOrdersData.getSBLC())
			.addValue("Country", purchaseOrdersData.getCountry())
			.addValue("EtaDate", purchaseOrdersData.getEtaDate())
			.addValue("EtdDate", purchaseOrdersData.getEtdDate())
			.addValue("AtaDate", purchaseOrdersData.getAtaDate())
			.addValue("BLNo", purchaseOrdersData.getBLNo())
			.addValue("PickupDate", purchaseOrdersData.getPickupDate())
			.addValue("InvPath", purchaseOrdersData.getInvPath())
			.addValue("ChqPath", purchaseOrdersData.getChqPath())
			.addValue("ChqPath2", purchaseOrdersData.getChqPath2())
			.addValue("ChqPath3", purchaseOrdersData.getChqPath3())
			.addValue("ReceiptPath", purchaseOrdersData.getReceiptPath())
			.addValue("ReceiptPath2", purchaseOrdersData.getReceiptPath2())
			.addValue("ReceiptPath3", purchaseOrdersData.getReceiptPath3())
			.addValue("OthersPath", purchaseOrdersData.getOthersPath())
			.addValue("VatAmt", purchaseOrdersData.getVatAmt())
			.addValue("VatJvNo", purchaseOrdersData.getVatJvNo())
			.addValue("EmailDt", purchaseOrdersData.getEmailDt())
			.addValue("ORAPONo", purchaseOrdersData.getORAPONo())
			.addValue("noofCont", purchaseOrdersData.getNoofCont())
			.addValue("Approved_Date", purchaseOrdersData.getApproved_Date())
			.addValue("paymentTerms", purchaseOrdersData.getPaymentTerms())
			.addValue("Tentative_ShipmentDt", purchaseOrdersData.getTentative_ShipmentDt()) 
			.addValue("claim_Period", purchaseOrdersData.getClaim_Period())
			.addValue("ProdLeadTime", purchaseOrdersData.getProdLeadTime())
			.addValue("POLineStatus", purchaseOrdersData.getPOLineStatus())
			.addValue("Order_Changed", purchaseOrdersData.getOrder_Changed())
			.addValue("PromisedTT", purchaseOrdersData.getPromisedTT())
			.addValue("ActualTT", purchaseOrdersData.getActualTT())
			.addValue("Discount", purchaseOrdersData.getDiscount())
			.addValue("POL", purchaseOrdersData.getPOL())
			.addValue("FF", purchaseOrdersData.getFF())
			.addValue("Cargo_Size", purchaseOrdersData.getCargo_Size())
			.addValue("volume", purchaseOrdersData.getVolume())
			.addValue("Cust_Decl", purchaseOrdersData.getCust_Decl())
			.addValue("ft40", purchaseOrdersData.getFt40())
			.addValue("ft20", purchaseOrdersData.getFt20())
			.addValue("lcl", purchaseOrdersData.getLcl())
			.addValue("AssignedTo", purchaseOrdersData.getAssignedTo())
			.addValue("AirShipment", purchaseOrdersData.getAirShipment());
		logger.info("Inserting purchase Order data in persistPurchaseOrdersHeadersData table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("purchase Order data records insterted: " + records);
	}
	
	@Override
	public void persistPurchaseOrdersDetailsData(PurchaseOrdersDetailsDTO purchaseOrdersData) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "Insert into " + WHDataBase + "USAORderDetail(SN, JobNo, GroupCode, CatCode, Qty, Amount, DiscountPerc, PalletCnt, LoadQty, Discount) values(:SN, :JobNo, :GroupCode, :CatCode, :Qty, :Amount, :DiscountPerc, :PalletCnt, :LoadQty, :Discount)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", purchaseOrdersData.getSN())
			.addValue("JobNo", purchaseOrdersData.getJobNo())
			.addValue("GroupCode", purchaseOrdersData.getGroupCode())
			.addValue("CatCode", purchaseOrdersData.getCatCode())
			.addValue("Qty", purchaseOrdersData.getQty())
			.addValue("Amount", purchaseOrdersData.getAmount())
			.addValue("DiscountPerc", purchaseOrdersData.getDiscountPerc())
			.addValue("PalletCnt", purchaseOrdersData.getPalletCnt())
			.addValue("LoadQty", purchaseOrdersData.getLoadQty())
			.addValue("Discount", purchaseOrdersData.getDiscount());
		logger.info("Inserting Purchase Order Details Data in USAORderDetail table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Purchase Order Details data records insterted: " + records);
	}
	
	@Override
	public void persistBOLOrdersDetailsData(PurchaseOrdersDetailsDTO purchaseOrdersData) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "Insert into " + WHDataBase + "USAORderDetail(SN, JobNo, GroupCode, CatCode, Qty, Amount, DiscountPerc, PalletCnt, LoadQty, Discount) values(:SN, :JobNo, :GroupCode, :CatCode, :Qty, :Amount, :DiscountPerc, :PalletCnt, :LoadQty, :Discount)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", purchaseOrdersData.getSN())
			.addValue("JobNo", purchaseOrdersData.getBolNo())
			.addValue("GroupCode", purchaseOrdersData.getGroupCode())
			.addValue("CatCode", purchaseOrdersData.getCatCode())
			.addValue("Qty", purchaseOrdersData.getQty())
			.addValue("Amount", purchaseOrdersData.getAmount())
			.addValue("DiscountPerc", purchaseOrdersData.getDiscountPerc())
			.addValue("PalletCnt", purchaseOrdersData.getPalletCnt())
			.addValue("LoadQty", purchaseOrdersData.getLoadQty())
			.addValue("Discount", purchaseOrdersData.getDiscount());
		logger.info("Inserting Purchase Order Details Data in USAORderDetail table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Purchase Order Details data records insterted: " + records);
	}
	
	@Override
	public void persistPODetailsData(List<PurchaseOrdersDTO> listOfpurchaseOrdersData, int sn) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "Insert into " + WHDataBase + "USAORderDetail(SN, JobNo, GroupCode, CatCode, Qty, Amount, DiscountPerc, PalletCnt, LoadQty, Discount) values(:SN, :JobNo, :GroupCode, :CatCode, :Qty, :Amount, :DiscountPerc, :PalletCnt, :LoadQty, :Discount)";
		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
		for(PurchaseOrdersDTO purchaseOrdersData : listOfpurchaseOrdersData) {
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("SN", sn)
				.addValue("JobNo", (null != purchaseOrdersData.getJobNo() && !purchaseOrdersData.getJobNo().isEmpty() ? purchaseOrdersData.getJobNo() : purchaseOrdersData.getBolNo()))
				.addValue("GroupCode", purchaseOrdersData.getGroupCode())
				.addValue("CatCode", purchaseOrdersData.getCatCode())
				.addValue("Qty", purchaseOrdersData.getQty())
				.addValue("Amount", purchaseOrdersData.getAmount())
				.addValue("DiscountPerc", purchaseOrdersData.getDiscountPerc())
				.addValue("PalletCnt", purchaseOrdersData.getPalletCnt())
				.addValue("LoadQty", purchaseOrdersData.getLoadQty())
				.addValue("Discount", purchaseOrdersData.getDiscount());
			paramList.add(param);
		}
		logger.info("Inserting Purchase Order Details Data in USAORderDetail table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, paramList.toArray(new SqlParameterSource[0]));
		logger.info("Purchase Order Details data records insterted: " + records.length);
	}


	@Override
	public List<StoreDTO> getAllCountryDatabases(String countryName) {
		String sql = "SELECT distinct dataname dataname, country FROM BFLDATA..DataSettings WHERE COUNTRY = '" + countryName  
				+ "' and Dataname not in ('BURDBDAT', 'BEAUTY', 'FABSMAIN') and CostCodeTo <> '' and loccodeto <> '' and fccode <> '' order by Dataname";
		List<StoreDTO> storeDto = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StoreDTO.class));
		return storeDto;
	}
	
	@Override
	public void persistClearancesData(ClearancesDTO clearancesDTO) {
		String dbName = "";
		try {
			dbName =  ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != dbName && !dbName.isEmpty()) {
				dbName = dbName + "..";
			}
		} catch (Exception e) {
			logger.error("Error while getting warehouse data base name for Item Loc(intial price) :- " + ExceptionUtils.getStackTrace(e));
		}
		String sql =  "Insert into " + dbName + "USADeadStock(Itemcode, NewPrice, TrnDate, ItemName, OldPrice, userId) values(:item, :newPrice, :trnDate, :itemDescription, :oldPrice, :userId)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", clearancesDTO.getItemCode())
			.addValue("newPrice", clearancesDTO.getPrice())
			.addValue("trnDate", clearancesDTO.getTrndate())
			.addValue("oldPrice", clearancesDTO.getOldPrice())
			.addValue("itemDescription", clearancesDTO.getItemDesc())
			.addValue("userId", "99");
		logger.info("Inserting clearances data in a table.");
		appJdbcTemplate.update(sql, param);
		logger.info("Inserted clearances data...");
	}

	@Override
	public void persistUSAPriceChangeForClearancesData(ClearancesDTO clearances) {
		String DbName = "";
		try {
			DbName =  ConfigProperties.getInstance().getConfigValue("WHDataBase") + "..";
		} catch (Exception e) {
			logger.error("Error while getting warehouse data base name for Item Loc(intial price) :- " + ExceptionUtils.getStackTrace(e));
		}
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String currentDateTime = output.format(new Date());
		String sql = "Insert into " + DbName + "USAPriceChange(Itemcode, OldPrice, NewPrice, Trndate, Time1, remarks, userId) "
				+ " values(:item, :salesRate, :retailRate, :trnDate, :trnTime, :remarks, :userId)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", clearances.getItemCode())
			.addValue("remarks", "R" + "-" + currentDateTime)
//			.addValue("remarks", "R")
			.addValue("salesRate", Math.round(Double.parseDouble(clearances.getOldPrice())))
			.addValue("retailRate", Math.round(Double.parseDouble(clearances.getPrice())))
			.addValue("trnDate", clearances.getTrndate())
			.addValue("trnTime", clearances.getTrnTime())
			.addValue("userId", clearances.getUserId());
		logger.info("Inserting sales price data in USAPriceChange table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Total sales prices data records insterted: " + records);
	}
	
	@Override
	public void persistUSAPriceChange(ClearancesDTO clearances) {
		String DbName = "";
		try {
			DbName =  ConfigProperties.getInstance().getConfigValue("WHDataBase") + "..";
		} catch (Exception e) {
			logger.error("Error while getting warehouse data base name for Item Loc(intial price) :- " + ExceptionUtils.getStackTrace(e));
		}
		String sql =  "Insert into " + DbName + "USAPriceChange(Itemcode, OldPrice, NewPrice, Trndate, Time1, remarks, userId) values(:item, :salesRate, :retailRate, :trnDate, :trnTime, :remarks, :userId)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", clearances.getItemCode())
			.addValue("remarks", "R")
			.addValue("salesRate", Math.round(Double.parseDouble(clearances.getOldPrice())))
			.addValue("retailRate", Math.round(Double.parseDouble(clearances.getPrice())))
			.addValue("trnDate", clearances.getTrndate())
			.addValue("trnTime", clearances.getTrnTime())
			.addValue("userId", clearances.getUserId());
		logger.info("Inserting sales price data in USAPriceChange table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Total sales prices data records insterted: " + records);
	}
	
	@Override
	public void persistRFSalesPrice(ClearancesDTO clearances) {
		String DbName = clearances.getDataBaseName();
		try {
			DbName =  DbName + "..";
		} catch (Exception e) {
			logger.error("Error while getting warehouse data base name for Item Loc(intial price) :- " + ExceptionUtils.getStackTrace(e));
		}
		String sql =  "Insert into " + DbName + "RFSalesPrice(Itemcode, Price, AedPrice, Trndate, Time1, remarks, userId, upc) values(:item, :retailRate, :salesRate, :trnDate, :trnTime, :remarks, :userId, :item)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", clearances.getItemCode())
			.addValue("remarks", "R")
			.addValue("salesRate", clearances.getAedPrice())
			.addValue("retailRate", Double.parseDouble(clearances.getPrice()))
			.addValue("trnDate", clearances.getTrndate())
			.addValue("trnTime", clearances.getTrnTime())
			.addValue("userId", clearances.getUserId());
		logger.info("Inserting RF sales price data in RFSalesPrice table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Total RF sales prices data records insterted: " + records);
	}

	
	@Override
	public void updateRFSalesPrice(ClearancesDTO clearances) {
		String DbName = clearances.getDataBaseName();
		try {
			DbName =  DbName + "..";
		} catch (Exception e) {
			logger.error("Error while getting warehouse data base name for Item Loc(intial price) :- " + ExceptionUtils.getStackTrace(e));
		}
		String sql = "update " + DbName + "RFSalesPrice set AEDPrice=:salesRate, Price=:retailRate, Trndate=:trnDate, Time1=:trnTime, remarks=:remarks, "
				+ " userId=:userId where Itemcode =:item";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", clearances.getItemCode())
			.addValue("remarks", "R")
			.addValue("salesRate", clearances.getAedPrice())
			.addValue("retailRate", clearances.getPrice())
			.addValue("trnDate", clearances.getTrndate())
			.addValue("trnTime", clearances.getTrnTime())
			.addValue("userId", clearances.getUserId());
		logger.info("updating sales price data in RFSALES PRICE table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Total sales prices data records updated : " + records);
		if(records == 0) {
			persistRFSalesPrice(clearances);
		}
	}
	
	@Override
	public List<ClearancesDTO> getRFSalesPrice(ClearancesDTO clearances) {
		logger.info("Extracting records from RF Sales Price table.");
		String dbName = clearances.getDataBaseName();
		try {
			if(null != dbName && !dbName.isEmpty()) {
				dbName = dbName + "..";
			}
		} catch (Exception e) {
			logger.error("Error while getting db name from properties file " + ExceptionUtils.getStackTrace(e));
		}
		String sql = "SELECT UPC, Itemcode, Price, AEDPrice, Trndate, Time1, UserID, Remarks from "+ dbName + "RFSALESPRICE with (nolock) where ItemCode = '" 
					+ clearances.getItemCode() + "' order by trnDate";
		logger.info("Reading data from RF sales price table");
		List<ClearancesDTO> salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClearancesDTO.class));
		return salesPriceData;
	}

	@Override
	public List<ClearancesDTO> getSalesPriceData(ClearancesDTO clearancesDTO) {
		logger.info("Extracting records from Sales Price table.");
		String WHDataBase = clearancesDTO.getDataBaseName();
		if(null != WHDataBase && !WHDataBase.isEmpty()) {
			WHDataBase = WHDataBase + "..";
		}

		String sql = "select CostCode, ItemCode, SalesRate, RetailRate, TrMode, trndate from " + WHDataBase + "SalesPrice with (nolock) where ItemCode = '" + clearancesDTO.getItemCode() 
					+ "' and CostCode = '" + clearancesDTO.getCostCode() + "' Order by trndate desc";
		logger.info("Reading data for Sales Price table");
		List<ClearancesDTO> salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClearancesDTO.class));
		return salesPriceData;
	}
	
	@Override
	public List<ClearancesDTO> getAllCostCentersSalesPriceData(ClearancesDTO clearancesDTO) {
		logger.info("Extracting records from Sales Price table.");
		String WHDataBase = clearancesDTO.getDataBaseName();
		if(null != WHDataBase && !WHDataBase.isEmpty()) {
			WHDataBase = WHDataBase + "..";
		}

		String sql = "select CostCode, ItemCode, SalesRate, RetailRate, TrMode, trndate, SalesRate price from " + WHDataBase + "SalesPrice with (nolock) where ItemCode = '" + clearancesDTO.getItemCode() 
					+ "' Order by trndate";
		logger.info("Reading data for Sales Price table");
		List<ClearancesDTO> salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClearancesDTO.class));
		return salesPriceData;
	}

	
	@Override
	public List<ClearancesDTO> getOldSalesPriceDataforCostCenter(ClearancesDTO itemLocDTO) {
		String WHDataBase = itemLocDTO.getDataBaseName();
		if(null != WHDataBase && !WHDataBase.isEmpty()) {
			WHDataBase = WHDataBase + "..";
		}
		String sql = "SELECT CostCode costCode , ItemCode itemCode, SalesRate salesRate, RetailRate retailRate from " 
					+ WHDataBase + "OldSalesPrice with (nolock) where ItemCode = '" + itemLocDTO.getItemCode() + "' and costCode = '" + itemLocDTO.getCostCode() + "'"
					+ " and salesRate = '" + itemLocDTO.getPrice() + "'";
		logger.info("Reading data for old Sales Price table");
		List<ClearancesDTO> salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClearancesDTO.class));
		return salesPriceData;
	}
	
	@Override
	public List<ClearancesDTO> getUSADeadStockData(ClearancesDTO clearancesDTO) {
		logger.info("Extracting records from USA DEAD STOCK table.");
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "select Itemcode, NewPrice, TrnDate, userId, ItemName, OldPrice from " + WHDataBase + "USADEADSTOCK with (nolock) where ItemCode = '" + clearancesDTO.getItemCode() + "' order by TrnDate desc, NewPrice";
		logger.info("Reading data for Sales Price table");
		List<ClearancesDTO> salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClearancesDTO.class));
		return salesPriceData;
	}

	
	@Override
	public List<ClearancesDTO> getUSAPriceData(ClearancesDTO clearancesDTO) {
		logger.info("Extracting records from Old Sales Price table.");
		String dbName = "WHDataBase";
		try {
			dbName = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != dbName && !dbName.isEmpty()) {
				dbName = dbName + "..";
			}
		} catch (Exception e) {
			logger.error("Error while getting db name from properties file " + ExceptionUtils.getStackTrace(e));
		}
		String sql = "select Itemcode itemCode, OldPrice oldPrice, NewPrice newPrice, Trndate trnDate, Time1 trnTime, userId from "+ dbName + "USAPriceChange with (nolock) where ItemCode = '" 
					+ clearancesDTO.getItemCode() + "' and userId = '" + clearancesDTO.getUserId() + "' order by trnDate";
		logger.info("Reading data from usa price change table");
		List<ClearancesDTO> salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClearancesDTO.class));
		return salesPriceData;
	}
	
	@Override
	public List<ClearancesDTO> getTop1USAPriceData(ClearancesDTO clearancesDTO) {
		String dbName = "WHDataBase";
		try {
			dbName = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != dbName && !dbName.isEmpty()) {
				dbName = dbName + "..";
			}
		} catch (Exception e) {
			logger.error("Error while getting db name from properties file " + ExceptionUtils.getStackTrace(e));
		}
		String sql = "select top 1 Itemcode itemCode, OldPrice oldPrice, NewPrice newPrice, Trndate trnDate, Time1 trnTime, userId from "+ dbName + "USAPriceChange with (nolock) where ItemCode = '" 
					+ clearancesDTO.getItemCode() + "' order by trnDate DESC, time1 desc";
		logger.info("Reading data from usa price change table for Item :: " + clearancesDTO.getItemCode());
		List<ClearancesDTO> salesPriceData = null;
		try {
			salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClearancesDTO.class));
		} catch (Exception e) {
			salesPriceData = new ArrayList<ClearancesDTO>();
		}
		return salesPriceData;
	}
	
	@Override
	public List<ClearancesDTO> getAEDPriceOfItem(String itemCode) {
		logger.info("Extracting records from Old Sales Price table.");
		String dbName = "WHDataBase";
		try {
			dbName = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != dbName && !dbName.isEmpty()) {
				dbName = dbName + "..";
			}
		} catch (Exception e) {
			logger.error("Error while getting db name from properties file " + ExceptionUtils.getStackTrace(e));
		}
		String sql = "select Itemcode itemCode, OldPrice oldPrice, NewPrice newPrice, Trndate trnDate, Time1 trnTime, userId from "+ dbName + "USAPriceChange with (nolock) where ItemCode = '" 
					+ itemCode + "' order by trnDate desc" ;
		logger.info("Reading data from usa price change table");
		List<ClearancesDTO> salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClearancesDTO.class));
		return salesPriceData;
	}

	@Override
	public void persistOldSalesPriceForPriceChange(ClearancesDTO itemLocDTO) {
		String DbName = itemLocDTO.getDataBaseName();
		if(null != DbName && !DbName.isEmpty()) {
			DbName = DbName + "..";
		}
		String sql =  "Insert into " + DbName + "OldSalesPrice(CostCode, ItemCode, SalesRate, RetailRate) values(:CostCode, :ItemCode, :SalesRate, :RetailRate)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("ItemCode", itemLocDTO.getItemCode())
			.addValue("CostCode", itemLocDTO.getCostCode())
			.addValue("SalesRate", itemLocDTO.getSalesRate())
			.addValue("RetailRate", itemLocDTO.getRetailRate());
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Total old sales prices data records insterted for Item Loc is : " + records);
	}


	@Override
	public void updateSalesPriceForClearancesData(ClearancesDTO clearancesDTO) {

		String WHDataBase = clearancesDTO.getDataBaseName();
		if(null != WHDataBase && !WHDataBase.isEmpty()) {
			WHDataBase = WHDataBase + "..";
		}
		String sql = "update " + WHDataBase + "SalesPrice set SalesRate=:salesRate, TrMode=:trnMode, trndate=:WefDate where itemcode=:item and CostCode=:costCode";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", clearancesDTO.getItemCode())
			.addValue("costCode", clearancesDTO.getCostCode())
			.addValue("salesRate", clearancesDTO.getSalesRate())
			.addValue("retailRate", clearancesDTO.getRetailRate())
			.addValue("trnMode", clearancesDTO.getTrnMode())
			.addValue("WefDate", clearancesDTO.getTrndate());
		logger.info("updating SalesPrice data in SalesPrice table.");
		appJdbcTemplate.update(sql, param);
		logger.info("updated SalesPrice data...");
	}
	
	@Override
	public void updateSalesPriceForAllCostCenters(ClearancesDTO clearancesDTO) {

		String WHDataBase = clearancesDTO.getDataBaseName();
		if(null != WHDataBase && !WHDataBase.isEmpty()) {
			WHDataBase = WHDataBase + "..";
		}
		String sql =  "update " + WHDataBase + "SalesPrice set SalesRate=:salesRate, TrMode=:trnMode, trndate=:WefDate where itemcode=:item";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", clearancesDTO.getItemCode())
			.addValue("costCode", clearancesDTO.getCostCode())
			.addValue("salesRate", clearancesDTO.getSalesRate())
			.addValue("retailRate", clearancesDTO.getRetailRate())
			.addValue("trnMode", clearancesDTO.getTrnMode())
			.addValue("WefDate", clearancesDTO.getTrndate());
		logger.info("updating SalesPrice data in SalesPrice table.");
		appJdbcTemplate.update(sql, param);
		logger.info("updated SalesPrice data...");
	}

	
	@Override
	public List<ItemMasterDTO> checkItemForTCM(String itemCode, String groupCode) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "select itemCode, description, groupCode, openingDate from " + WHDataBase +"Itemmaster with (nolock) WHERE itemCode = '" + itemCode + "' and groupCode in (" + groupCode + ")";
		List<ItemMasterDTO> usaItemAll = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemMasterDTO.class));
		return usaItemAll;
	}

	
	public List<PurchaseOrdersDTO> getPurchaseOrderHeadersSN() {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "SELECT SN FROM " + WHDataBase + "USAORderHeader with (nolock) order by SN desc";
		List<PurchaseOrdersDTO> purchaseOrdersDTO = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PurchaseOrdersDTO.class));
		return purchaseOrdersDTO;
	}

	
	@Override
	public List<PurchaseOrdersDTO> getPurchaseOrderNo(String orderNo, String legacyPoNum) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "SELECT * FROM " + WHDataBase + "USAORderHeader with (nolock) WHERE (ORAPONo = '" + orderNo + "' OR ORAPONo = '" + legacyPoNum + "') order by SN";
		List<PurchaseOrdersDTO> purchaseOrdersDTO = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PurchaseOrdersDTO.class));
		return purchaseOrdersDTO;
	}
	
	public List<PurchaseOrdersDTO> getOrdersPurchasedDetails(String orderNo, String contNo) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "SELECT ORAPONO, UOH.CONTNO, SN FROM HODATA..USAOrderHeader UOH JOIN BFLDATA..USAContNos UCN ON (UCN.ContNo = UOH.ContNo) JOIN USA..USAPurchase USP ON (USP.Contno = UCN.RefNo) "
					+ "WHERE UOH.CONTNO = '" + contNo + "'";
		List<PurchaseOrdersDTO> purchaseOrdersDTO = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PurchaseOrdersDTO.class));
		return purchaseOrdersDTO;
	}
	
	public void updatePurchaseOrderHeaders(PurchaseOrdersDTO purchaseOrders) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		
		String sql = "update " + WHDataBase + "USAORderHeader set EntryDate=:EntryDate, InvoiceDate=:InvoiceDate, InvoiceNo=:InvoiceNo, ORAPONo=:ORAPONo, InvAmount=:InvAmount, Currency=:Currency, NonPayment=:NonPayment, Country=:Country, DueDate=:DueDate, claim_Period=:claim_Period, ProdLeadTime=:ProdLeadTime, VatAmt=:VatAmt, Approved_Date=:Approved_Date, SuppCode=:SuppCode, paymentTerms=:paymentTerms, Remarks=:Remarks, Userid=:Userid, POLineStatus=:POLineStatus, Discount=:Discount, Tentative_ShipmentDt=:Tentative_ShipmentDt, Order_Changed=:Order_Changed, ItemGroups=:ItemGroups "
				+ " WHERE SN=:SN";
		
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", purchaseOrders.getSN())
			.addValue("EntryDate", purchaseOrders.getEntryDate())
			.addValue("InvoiceDate", purchaseOrders.getInvoiceDate())
			.addValue("InvoiceNo", purchaseOrders.getInvoiceNo())
			.addValue("ORAPONo", purchaseOrders.getORAPONo())
			.addValue("InvAmount", purchaseOrders.getInvAmount())
			.addValue("Currency", purchaseOrders.getCurrency())
			.addValue("NonPayment", purchaseOrders.getNonPayment())
			.addValue("Country", purchaseOrders.getCountry())
			.addValue("DueDate", purchaseOrders.getDueDate())
			.addValue("claim_Period", purchaseOrders.getClaim_Period())
			.addValue("ProdLeadTime", purchaseOrders.getProdLeadTime())
			.addValue("VatAmt", purchaseOrders.getVatAmt())
			.addValue("Approved_Date", purchaseOrders.getApproved_Date())
			.addValue("SuppCode", purchaseOrders.getSuppCode())
			.addValue("paymentTerms", purchaseOrders.getPaymentTerms())
			.addValue("Remarks", purchaseOrders.getRemarks())
			.addValue("Userid", "")
//			.addValue("Userid", purchaseOrders.getUserid())
			.addValue("POLineStatus", purchaseOrders.getPOLineStatus())
			.addValue("GroupCode", purchaseOrders.getGroupCode())
			.addValue("Discount", purchaseOrders.getDiscount())
			.addValue("Tentative_ShipmentDt", purchaseOrders.getTentative_ShipmentDt())
			.addValue("Order_Changed", purchaseOrders.getOrder_Changed())
			.addValue("LegacyPONo", purchaseOrders.getLegacyPONum())
			.addValue("ItemGroups", (purchaseOrders.getItemGroups().length() < 99) ? purchaseOrders.getItemGroups() : purchaseOrders.getItemGroups().substring(0, 99));
		int update = appJdbcTemplate.update(sql, param);
		if(update == 0) {
			persistPurchaseOrdersHeadersData(purchaseOrders);
		}
	}
	
	public void updatePurchaseOrderDetails(PurchaseOrdersDetailsDTO purchaseOrders) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "update " + WHDataBase + "USAORderDetail set DiscountPerc=:DiscountPerc, PalletCnt=:PalletCnt, amount=:amount, LoadQty=:LoadQty, Discount=:Discount, Qty=:Qty, GroupCode=:GroupCode, catCode=:catCode "
				+ " WHERE SN=:SN and GroupCode=:GroupCode and jobNo=:jobNo";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", purchaseOrders.getSN())
			.addValue("DiscountPerc", purchaseOrders.getDiscountPerc())
			.addValue("PalletCnt", purchaseOrders.getPalletCnt())
			.addValue("Qty", purchaseOrders.getQty())
			.addValue("LoadQty", purchaseOrders.getLoadQty())
			.addValue("amount", purchaseOrders.getAmount())
			.addValue("GroupCode", purchaseOrders.getGroupCode())
			.addValue("jobNo", purchaseOrders.getJobNo())
			.addValue("catCode", purchaseOrders.getCatCode())
			.addValue("Discount", purchaseOrders.getDiscount());
		int update = appJdbcTemplate.update(sql, param);
		if(update == 0) {
			persistPurchaseOrdersDetailsData(purchaseOrders);
		}
	}
	
	public void updateBolOrderDetails(PurchaseOrdersDetailsDTO purchaseOrders) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "update " + WHDataBase + "USAORderDetail set DiscountPerc=:DiscountPerc, PalletCnt=:PalletCnt, LoadQty=:LoadQty, Discount=:Discount, Qty=:Qty, amount=:amount WHERE SN=:SN and jobNo=:BolNo and GroupCode=:GroupCode";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", purchaseOrders.getSN())
			.addValue("DiscountPerc", purchaseOrders.getDiscountPerc())
			.addValue("amount", purchaseOrders.getAmount())
			.addValue("BolNo", purchaseOrders.getBolNo())
			.addValue("PalletCnt", purchaseOrders.getPalletCnt())
			.addValue("Qty", purchaseOrders.getQty())
			.addValue("LoadQty", purchaseOrders.getLoadQty())
			.addValue("GroupCode", purchaseOrders.getGroupCode())
			.addValue("catCode", purchaseOrders.getCatCode())
			.addValue("Discount", purchaseOrders.getDiscount());
		int update = appJdbcTemplate.update(sql, param);
		if(update == 0) {
			persistBOLOrdersDetailsData(purchaseOrders);
		}
	}
	
	public void updateUSAPurchaseOrderDetails(String SN, String groupCode, String amount, String qty, String jobNo) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "update " + WHDataBase + "USAORderDetail set Qty=:qty, amount=:amount WHERE SN=:SN and GroupCode=:GroupCode and jobno=:jobNo";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", SN)
			.addValue("GroupCode", groupCode)
			.addValue("amount", amount)
			.addValue("qty", qty)
			.addValue("jobNo", jobNo);
		int update = appJdbcTemplate.update(sql, param);
		if(update == 0) {
			persistOrderDetails(SN, groupCode, amount, qty, jobNo);
		}
	}
	
	public void updateUSAPurchaseOrderBOLDetails(String SN, String groupCode, String amount, String qty, String bolNo) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "update " + WHDataBase + "USAORderDetail set Qty=:qty, amount=:amount WHERE SN=:SN and GroupCode=:GroupCode and jobNo=:bolNo";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", SN)
			.addValue("GroupCode", groupCode)
			.addValue("amount", amount)
			.addValue("bolNo", bolNo)
			.addValue("qty", qty);
		int update = appJdbcTemplate.update(sql, param);
		if(update == 0) {
			persistOrderDetails(SN, groupCode, amount, qty, bolNo);
		}
	}
	
	public void persistOrderDetails(String SN, String groupCode, String amount, String qty, String jobNo) {
		String sql =  "Insert into HODATA..USAORderDetail(SN, JobNo, GroupCode, CatCode, Qty, Amount, DiscountPerc, PalletCnt, LoadQty, Discount) values(:SN, :JobNo, :GroupCode, :CatCode, :Qty, :Amount, :DiscountPerc, :PalletCnt, :LoadQty, :Discount)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", SN)
			.addValue("JobNo", jobNo)
			.addValue("GroupCode", groupCode)
			.addValue("CatCode", groupCode)
			.addValue("Qty", qty)
			.addValue("Amount", amount)
			.addValue("DiscountPerc", "0")
			.addValue("PalletCnt", "0")
			.addValue("LoadQty", "0")
			.addValue("Discount", "0");
		logger.info("Inserting Purchase Order Details Data in USAORderDetail table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Purchase Order Details data records insterted: " + records);
	}
	
	public void persistDelPOHeadersData(String oldSN, String currentDate) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "INSERT INTO " + WHDataBase + "DelUsaorderHeader(SN, EntryDate, InvoiceDate, InvoiceNo, Remarks, Userid, ContNo, StackingFee, InvAmount, CommAmount, CommPerc, "
				+ " PaidAmount, DueDate, Winter, OtherDesc, Export, ExportDesc, SuppCode, Currency, NonPayment, ShoesCat, ItemGroups, SBLC, Country, EtaDate, EtdDate, AtaDate, BlNo, "
				+ " PickupDate, InvPath, ChqPath, ChqPath2, ChqPath3, ReceiptPath, ReceiptPath2, ReceiptPath3, OthersPath, VatAmt, VatJvNo, EmailDt, DelRemarks, "
				+ " ORAPONo, noofCont, Approved_Date, paymentTerms, Tentative_ShipmentDt, claim_Period, ProdLeadTime, POLineStatus, Order_Changed, PromisedTT, "
				+ " ActualTT, Discount, POL, FF, Cargo_Size, volume, Cust_Decl, ft40, ft20, lcl, AssignedTo, AirShipment) (SELECT :SN, EntryDate, InvoiceDate, "
				+ " InvoiceNo, Remarks, Userid, ContNo, StackingFee, InvAmount, CommAmount, CommPerc, PaidAmount, DueDate, Winter, OtherDesc, Export, ExportDesc, "
				+ " SuppCode, Currency, NonPayment, ShoesCat, ItemGroups, SBLC, Country, EtaDate, EtdDate, AtaDate, BLNo, PickupDate, InvPath, ChqPath, ChqPath2, ChqPath3, "
				+ " ReceiptPath, ReceiptPath2, ReceiptPath3, OthersPath, VatAmt, VatJvNo, EmailDt, 'R-" + currentDate + "'" + ",  ORAPONo, noofCont, Approved_Date, paymentTerms, "
				+ " Tentative_ShipmentDt, claim_Period, ProdLeadTime, POLineStatus, Order_Changed, PromisedTT, ActualTT, Discount, POL, FF, Cargo_Size, volume, Cust_Decl, "
				+ " ft40, ft20, lcl, AssignedTo, AirShipment FROM " + WHDataBase + " UsaorderHeader WHERE SN=:SN)";
		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", oldSN);
		paramList.add(param);
		int [] records = appJdbcTemplate.batchUpdate(sql, paramList.toArray(new SqlParameterSource[0]));
		logger.info("Inserted data into .." + records.length);
	}
	
	public void persistDelPODetailsData(String oldSN, String currentDate) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "INSERT INTO " + WHDataBase + "DelUsaorderDetail(SN, JobNo, GroupCode, CatCode, Qty, Amount, DiscountPerc, DelRemarks, PalletCnt, LoadQty, Discount) "
					+ " (SELECT :SN, JobNo, GroupCode, CatCode, Qty, Amount, DiscountPerc, 'R-" + currentDate + "'" + ", PalletCnt, LoadQty, Discount FROM " + WHDataBase + "USAORDERDETAIL WHERE SN=:SN)";
		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("SN", oldSN);
		paramList.add(param);
		int [] records = appJdbcTemplate.batchUpdate(sql, paramList.toArray(new SqlParameterSource[0]));
		logger.info("Inserted data into .." + records.length);
	}

	@Override
	public void persistSalesPriceForClearancesData(ClearancesDTO clearancesDTO) {
		String WHDataBase = clearancesDTO.getDataBaseName();
		if(null != WHDataBase && !WHDataBase.isEmpty()) {
			WHDataBase = WHDataBase + "..";
		}
		String sql =  "Insert into " + WHDataBase + "SalesPrice(ItemCode, CostCode, SalesRate, retailRate, TrMode, trndate) values(:item, :costCode, :salesRate, :retailRate, :trnMode, :WefDate)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", clearancesDTO.getItemCode())
			.addValue("costCode", clearancesDTO.getCostCode())
			.addValue("retailRate", clearancesDTO.getRetailRate())
			.addValue("salesRate", clearancesDTO.getSalesRate())
			.addValue("retailRate", clearancesDTO.getRetailRate())
			.addValue("trnMode", clearancesDTO.getTrnMode())
			.addValue("WefDate", clearancesDTO.getTrndate());
		logger.info("Inserting SalesPrice data for clearances in SalesPrice table.");
		appJdbcTemplate.update(sql, param);
		logger.info("Inserted SalesPrice data...");
	}


	@Override
	public String getLastProcessingTimestamp(Long jobId) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "select LAST_SUCCESS_PROCESS_TS from " + TRAN_FLAG_DB + "BFL_RIT_PROCESS_TS where JOB_ID='"+jobId+"'";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String lastTimestamp = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		return lastTimestamp;
	}

	@Override
	public void updateLastProcessingTimestamp(String timestamp, Long jobId, String jobName) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql =  "update " + TRAN_FLAG_DB + "BFL_RIT_PROCESS_TS set LAST_SUCCESS_PROCESS_TS=:timestamp  where JOB_ID=:jobId";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("timestamp", timestamp)
				.addValue("jobId", jobId);
		logger.info("Updating last timestamp in BFL_RIT_PROCESS_TS table.");
		appJdbcTemplate.update(sql, parameters);
		logger.info("updated...");
	}

	@Override
	public void insertLastProcessingTimestamp(String timestamp, Long jobId,  String jobName) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql =  "insert into " + TRAN_FLAG_DB + "BFL_RIT_PROCESS_TS(LAST_SUCCESS_PROCESS_TS, JOB_ID, JOB_NAME) values (:timestamp, :jobId, :jobName)";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("timestamp", timestamp)
				.addValue("jobId", jobId)
				.addValue("jobName", jobName);
		logger.info("Inserting last timestamp in BFL_RIT_PROCESS_TS table.");
		appJdbcTemplate.update(sql, parameters);
	}




	@Override
	public void persistItemAttributesForGroupWise(List<SqlParameterSource> param) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("ONLINE");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "Insert into " + WHDataBase + "ItemAttributes(ContNo , UPC , ItemName , GroupCode , Style , Color , Size , TrnDate , TrnTime , UserName , Upload , StyleQty , Remarks , Brand , Gender , Season , GenBarcode , subcategory) values (:ContNo , :UPC , :styleDescription , :GroupCode , :Style , :Color , :Size , :TrnDate , :TrnTime , :UserName , :Upload , :StyleQty , :Remarks , :Brand , :Gender , :Season , :GenBarcode , :subcategory)";
		logger.info("Inserting data in itemAttributes table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total itemAttributes records inserted: " + records.length);
	}

	
	@Override
	public void persistManifestItemAttributes(List<SqlParameterSource> param) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("ONLINE");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "Insert into " + WHDataBase + "ITEMATTRIBUTES(ContNo , UPC , ItemName , GroupCode , Style , Color , Size , TrnDate , TrnTime , UserName , Upload , StyleQty , Remarks , Brand , Gender , Season , GenBarcode , subcategory) values (:ContNo, :UPC, :ItemName, :GroupCode, :Style, :Color, :Size, :TrnDate, :TrnTime, :UserName, :Upload, :StyleQty, :Remarks, :Brand, :Gender, :Season, :GenBarcode, :subcategory)";
		logger.info("Inserting data in itemAttributes table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total itemAttributes records inserted: " + records.length);
	}
	
	@Override
	public void persistdelRFSalesPrice(ClearancesDTO rfSalesPrice) {
		String DbName = rfSalesPrice.getDataBaseName();
		try {
			DbName =  DbName + "..";
		} catch (Exception e) {
			logger.error("Error while getting warehouse data base name for Item Loc(intial price) :- " + ExceptionUtils.getStackTrace(e));
		}
		String sql =  "Insert into " + DbName + "delRFSalesPrice(Itemcode, Price, AedPrice, Trndate, Time1, remarks, userId, upc, delRemarks) values(:item, :retailRate, :salesRate, :trnDate, :trnTime, :remarks, :userId, :item, :remarks)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", rfSalesPrice.getItemCode())
			.addValue("remarks", "R")
			.addValue("salesRate", rfSalesPrice.getAedPrice())
			.addValue("retailRate", rfSalesPrice.getPrice())
			.addValue("trnDate", rfSalesPrice.getTrndate())
			.addValue("trnTime", rfSalesPrice.getTrnTime())
			.addValue("userId", rfSalesPrice.getUserId());
		logger.info("Inserting del RF sales price data in RFSalesPrice table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Total del RF sales prices data records insterted: " + records);
	}
	
	@Override
	public void updatedelRFSalesPrice(ClearancesDTO rfSalesPrice) {
		String DbName = rfSalesPrice.getDataBaseName();
		try {
			DbName =  DbName + "..";
		} catch (Exception e) {
			logger.error("Error while getting warehouse data base name for Item Loc(intial price) :- " + ExceptionUtils.getStackTrace(e));
		}
		String sql =  "update " + DbName + "delRFSalesPrice set Itemcode=:item, Price=:retailRate, AedPrice=:salesRate, Trndate=:trnDate, Time1=:trnTime, remarks=:remarks, userId=:userId, upc=:item WHERE upc=:item ";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("item", rfSalesPrice.getItemCode())
			.addValue("remarks", "R")
			.addValue("salesRate", rfSalesPrice.getAedPrice())
			.addValue("retailRate", Double.parseDouble(rfSalesPrice.getPrice()))
			.addValue("trnDate", rfSalesPrice.getTrndate())
			.addValue("trnTime", rfSalesPrice.getTrnTime())
			.addValue("userId", rfSalesPrice.getUserId());
		logger.info("Inserting del RF sales price data in RFSalesPrice table.");
		int records = appJdbcTemplate.update(sql, param);
		logger.info("Total del RF sales prices data records updated: " + records);
		if(records == 0) {
			persistdelRFSalesPrice(rfSalesPrice);
		}
	}
	
	@Override
	public List<ClearancesDTO> getDelRFSalesPrice(ClearancesDTO rfSalesPrice) {
		String DbName = rfSalesPrice.getDataBaseName();
		try {
			DbName =  DbName + "..";
		} catch (Exception e) {
			logger.error("Error while getting warehouse data base name for Item Loc(intial price) :- " + ExceptionUtils.getStackTrace(e));
		}
		String sql =  "SELECT itemcode, price retailrate, aedprice salesRate FROM " + DbName + "delRFSalesPrice with (nolock) WHERE itemcode = '" + rfSalesPrice.getItemCode() 
					+ "' and price = " + rfSalesPrice.getPrice();
		logger.info("Reading data from del RF sales price table");
		List<ClearancesDTO> salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClearancesDTO.class));
		return salesPriceData;
	}

	
	@Override
	public List<PurchaseOrdersDTO> getSuppName(String suppCode) {
		String sql = "select accode SuppCode from hodata..accmst with (nolock) where description = '" + suppCode + "'";
		List<PurchaseOrdersDTO> SuppName = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PurchaseOrdersDTO.class));
		return SuppName;
	}


	@Override
	public void persistBulkUSAOrgFile(List<SqlParameterSource> param) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "Insert into " + WHDataBase + "USAORGFILE(BOL, ContNo, upc, itemname, orgqty, orgretail, totalretail, orgcost, totalorgcost, style, color, size, clientcost, division, deptname, vendor, DhsCost, DhsSalesOrg, DHSSalesAdj, ItemCode, ItemIssued, TrnDate, GroupCode, GENDER, CountryOrigin, hscode, Currency, Material, COSTCURR, subcategory, SalesPriceCurrency, season, Status, SizeCheck, InvoiceNo, OrgRetExcel, TYPE1, SplitPO, LoadManifestType) values (:BOLNO, :ContNo, :UPC, :ItemName, :orgqty, :orgretail, :totalretail, :orgcost, :totalorgcost, :Style, :Color, :Size, :ClientCost, :division, :DeptName, :Vendor, :DHSCost, :DhsSalesOrg, :DHSSalesAdj, :Itemcode, :ItemIssued, :TrnDate, :GroupCode, :Gender, :CountryOrigin, :HSCode, :RRPCurrency, :Material, :COSTCURR, :subcategory, :SalesPriceCurrency, :Season, :Status, :SizeCheck, :InvoiceNo, :OrgRetExcel, :TYPE1, :SplitPO, :LoadManifestType)";
		logger.info("Inserting data in USAOrgFile table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total USAOrgFile records inserted: " + records.length);
	}
	
	@Override
	public void persistTempBarcodesData(List<SqlParameterSource> param) {
		String sql = "insert into RMS..TEMP_UPCBarCodes(BOLNO, TrnDate, UPC, DeptName, ItemName, OrgRetRate , Itemcode , ItemType , ShortSkirt , ShortUpdated , Size1 , SizeUpdated , WinterUpdated , SizeCheck , DHSCost , ClientCost , Vendor , GroupCode , Color , Style , shortname , TYPE1 , GENDER , CountryOrigin , Material , subcategory , HSCode , RRPCurrency) values (:ContNo , :TrnDate , :UPC , :DeptName , :ItemName , :OrgRetRate , :Itemcode , :ItemType , :ShortSkirt , :ShortUpdated , :Size1 , :SizeUpdated , :WinterUpdated , :SizeCheck , :DHSCost , :ClientCost , :Vendor , :GroupCode , :Color , :Style , :shortname , :TYPE1 , :Gender , :CountryOrigin , :Material , :subcategory , :HSCode , :RRPCurrency)";
		logger.info("Inserting data in TEMP_UPCBarCodes table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total TEMP_UPCBarCodes records inserted: " + records.length);
	}
	
	@Override
	public void persistTempItemAttributesData(List<SqlParameterSource> param) {
		String sql = "insert into RMS..Temp_ItemAttibutes(CONTNO, UPC, ItemName, GroupCode, Style, Color, Size, TrnDate, Upload, Remarks, Brand, Gender, Season, subcategory, StyleQty) "
					+ " values (:ContNo , :UPC , :ItemName, :GroupCode, :Style, :Color, :Size1, :TrnDate, 'Y', 'R', :Brand, :Gender, :ItemType, :subcategory, :StyleQty)";
		logger.info("Inserting data in Temp_ItemAttibutes table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total Temp_ItemAttibutes records inserted: " + records.length);
	}
	
	@Override
	public void persistUpcBarcodesLogDTO(List<SqlParameterSource> param) {
		String sql = "insert into USA..UPCBarcodes_RMS_Log(trndate, Itemcode, contno, datatype, oldData, newData, tablename) values (:trndate, :Itemcode, :contno, :datatype, :oldData, :newData, :tableName)";
		logger.info("Inserting data into UPCBarcodes_RMS_Log log table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total UPCBarcodes_RMS_Log records inserted: " + records.length);
	}
	
	@Override
	public void truncateTempUpcBarcodesData() {
		String sql = "truncate table RMS..TEMP_UPCBarCodes";
		SqlParameterSource param = new MapSqlParameterSource();
		appJdbcTemplate.update(sql, param);
		logger.info("Data Truncated from temp upcbarcodes table");
	}
	
	@Override
	public void truncateTempItemAttributesData() {
		String sql = "truncate table RMS..Temp_ItemAttibutes";
		SqlParameterSource param = new MapSqlParameterSource();
		appJdbcTemplate.update(sql, param);
		logger.info("Data Truncated from temp item masters table");
	}
	
	@Override
	public List<UpcBarcodesDTO> getTempBarcodesData(String contNo) {
		String sql = "SELECT UB.BOLNO, UB.TrnDate, UB.UPC, UB.DeptName, UB.ItemName, UB.OrgRetRate, UB.Itemcode, UB.UserID, UB.ItemType, UB.ShortSkirt, "
					+ "UB.ShortUpdated, UB.Size1, UB.SizeUpdated, UB.WinterUpdated, UB.SizeCheck, UB.DHSCost, UB.ClientCost, UB.Vendor, UB.GroupCode, UB.Color, "
					+ "UB.Style, UB.shortname, UB.TYPE1, UB.GENDER, UB.CountryOrigin, UB.Material, UB.subcategory, UB.HSCode, UB.RRPCurrency "
					+ "from RMS..TEMP_UPCBarCodes TUPC with (nolock) "
					+ "JOIN USA..UPCBARCODES UB ON (TUPC.UPC = UB.UPC) "
					+ "WHERE TUPC.BOLNO = '" + contNo + "'";
		List<UpcBarcodesDTO> upcBarcodesDTO = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UpcBarcodesDTO.class));
		logger.info("Fetch data from temp and upcbarcodes table");
		return upcBarcodesDTO;
	}
	
	@Override
	public List<UpcBarcodesDTO> getTempItemMasterData(String contNo) {
		String sql = "SELECT IM.ItemCode, IM.Description, IM.GroupCode "
					+ " from RMS..TEMP_UPCBarCodes TUPC with (nolock)  "
					+ " JOIN hodata..itemMaster IM ON (TUPC.UPC = IM.ItemCode) "
					+ " WHERE TUPC.BOLNO = '" + contNo + "'";
		List<UpcBarcodesDTO> upcBarcodesDTO = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UpcBarcodesDTO.class));
		logger.info("Fetch data from temp and upcbarcodes table");
		return upcBarcodesDTO;
	}


	@Override
	public void updateUpcBarcodesColorData(String currentDate) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
		SqlParameterSource sqlParam = new MapSqlParameterSource();
		paramList.add(sqlParam);
		String sql = "update UPC set Color=TUPC.Color, ShortUpdated= 'R-UPD" + currentDate + "' "
					+ " FROM " + WHDataBase + "UPCBarCodes UPC INNER JOIN RMS..TEMP_UPCBarCodes TUPC ON "
					+ " (TUPC.UPC = UPC.UPC AND TUPC.Color != UPC.Color AND TUPC.Color != 'NA' AND TUPC.Color != 'N/A') ";
		logger.info("updating data in upcBarcodes table for Color.");
		int [] records = appJdbcTemplate.batchUpdate(sql, paramList.toArray(new SqlParameterSource[0]));
		logger.info("Total upcBarcodes color records updated: " + records.length);
	}
	
	@Override
	public void updateUpcBarcodesGroupCodeData(String currentDate) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		List<SqlParameterSource> param = new ArrayList<SqlParameterSource>();
		SqlParameterSource sqlParam = new MapSqlParameterSource();
		param.add(sqlParam);
		String sql = "update UPC set GroupCode=TUPC.GroupCode, ShortUpdated= 'R-UPD" + currentDate + "' "
				+ " FROM " + WHDataBase + "UPCBarCodes UPC INNER JOIN RMS..TEMP_UPCBarCodes TUPC ON "
				+ " (TUPC.UPC = UPC.UPC AND TUPC.GROUPCODE != UPC.GROUPCODE AND TUPC.GROUPCODE != 'NA' AND TUPC.GROUPCODE != 'N/A') ";
		logger.info("updating data in upcBarcodes table for GroupCode.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total upcBarcodes GroupCode records updated: " + records.length);
	}
	
	@Override
	public void updateUpcBarcodesStyleData(String currentDate) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		List<SqlParameterSource> param = new ArrayList<SqlParameterSource>();
		SqlParameterSource sqlParam = new MapSqlParameterSource();
		param.add(sqlParam);
		String sql = "update UPC set Style=TUPC.Style, ShortUpdated= 'R-UPD" + currentDate + "' "
					+ " FROM " + WHDataBase + "UPCBarCodes UPC INNER JOIN RMS..TEMP_UPCBarCodes TUPC ON "
					+ " (TUPC.UPC = UPC.UPC AND TUPC.Style != UPC.Style AND TUPC.Style != 'NA' AND TUPC.Style != 'N/A') ";
		logger.info("updating data in upcBarcodes table for Style.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total upcBarcodes records updated: " + records.length);
	}
	
	@Override
	public void updateUpcBarcodesBrandData(String currentDate) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		List<SqlParameterSource> param = new ArrayList<SqlParameterSource>();
		SqlParameterSource sqlParam = new MapSqlParameterSource();
		param.add(sqlParam);
		String sql = "update UPC set Vendor=TUPC.Vendor, ShortUpdated= 'R-UPD" + currentDate + "' "
					+ " FROM " + WHDataBase + "UPCBarCodes UPC INNER JOIN RMS..TEMP_UPCBarCodes TUPC ON "
					+ " (TUPC.UPC = UPC.UPC AND TUPC.Vendor != UPC.Vendor AND TUPC.Vendor != 'NA' AND TUPC.Vendor != 'N/A') ";
		logger.info("updating data in upcBarcodes table for Brand.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total upcBarcodes records updated: " + records.length);
	}
	
	@Override
	public void updateUpcBarcodesItemNameData(String currentDate) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		List<SqlParameterSource> param = new ArrayList<SqlParameterSource>();
		SqlParameterSource sqlParam = new MapSqlParameterSource();
		param.add(sqlParam);
		String sql = "update UPC set itemname=TUPC.ItemName, ShortUpdated= 'R-UPD" + currentDate + "' "
					+ " FROM " + WHDataBase + "UPCBarCodes UPC INNER JOIN RMS..TEMP_UPCBarCodes TUPC ON "
					+ " (TUPC.UPC = UPC.UPC AND TUPC.ItemName != UPC.ItemName AND TUPC.ItemName != 'NA' AND TUPC.ItemName != 'N/A') ";
		logger.info("updating data in upcBarcodes table for Description.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total upcBarcodes records updated: " + records.length);
	}
	
	@Override
	public void updateItemMasterData(String currentDate) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		List<SqlParameterSource> param = new ArrayList<SqlParameterSource>();
		SqlParameterSource sqlParam = new MapSqlParameterSource();
		param.add(sqlParam);
//		String sql =  "update " + WHDataBase + "ItemMaster set GroupCode=:GroupCode WHERE upc=:UPC";
		String sql = "update IM set GroupCode=TUPC.GroupCode " + 
					" FROM HODATA..ItemMaster IM INNER JOIN RMS..TEMP_UPCBarCodes TUPC ON "
					+ " (TUPC.UPC = IM.ITEMCODE AND TUPC.GROUPCODE != IM.GROUPCODE AND TUPC.GROUPCODE != 'NA' AND TUPC.GROUPCODE != 'N/A')";
		logger.info("updating group code data in hodata..ItemMaster table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total itemMaster records updated: " + records.length);
	}
	
	@Override
	public void insertUpcBarcodesData(List<SqlParameterSource> param) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "insert into " + WHDataBase + "UPCBarCodes(BOLNO, TrnDate, UPC, DeptName, ItemName, OrgRetRate , Itemcode , UserID , ItemType , ShortSkirt , ShortUpdated , Size1 , SizeUpdated , WinterUpdated , SizeCheck , DHSCost , ClientCost , Vendor , GroupCode , Color , Style , shortname , TYPE1 , GENDER , CountryOrigin , Material , subcategory , HSCode , RRPCurrency) values (:BOLNO , :TrnDate , :UPC , :DeptName , :ItemName , :OrgRetRate , :Itemcode , :UserID , :ItemType , :ShortSkirt , :ShortUpdated , :Size1 , :SizeUpdated , :WinterUpdated , :SizeCheck , :DHSCost , :ClientCost , :Vendor , :GroupCode , :Color , :Style , :shortname , :TYPE1 , :Gender , :CountryOrigin , :Material , :subcategory , :HSCode , :RRPCurrency)";
		logger.info("Inserting data in UPCBarCodes table.");
		int [] records = appJdbcTemplate.batchUpdate(sql, param.toArray(new SqlParameterSource[0]));
		logger.info("Total UPCBarCodes records inserted: " + records.length);
	}



	@Override
	public void sendWMSDataInExportedTable(TransferConfigDTO transferConfigDTO) {
		logger.info("Exporting records in BFL_Trans_Exp table.");
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "Insert into " + TRAN_FLAG_DB + "BFL_WMS_EXP_DATA(BFL_Transfer_NO, BFL_TABLE_NAME, TRAN_TYPE, TRN_DATETIME, EXPORTED, ERROR, CREATE_DATETIME, RMS_Transfer_NO) values (:BFL_Transfer_No, :BFL_TABLE_NAME, :TRAN_TYPE, CAST(:TRN_DATETIME AS SMALLDATETIME), :EXPORTED, :ERROR, CAST(:CREATE_DATETIME AS SMALLDATETIME), :RMS_Transfer_No)";
		
		SqlParameterSource param = new	MapSqlParameterSource() 
				.addValue("BFL_Transfer_No", transferConfigDTO.getBflTransferNo())
				.addValue("BFL_TABLE_NAME", transferConfigDTO.getBflTableName())
				.addValue("TRAN_TYPE", transferConfigDTO.getTranType())
				.addValue("TRN_DATETIME", new Date())
				.addValue("EXPORTED", transferConfigDTO.getExported())
				.addValue("ERROR", transferConfigDTO.getError())
				.addValue("CREATE_DATETIME", new Date())
				.addValue("RMS_Transfer_No", transferConfigDTO.getRmsTransferNo());
		logger.info("Inserting Transactions in ExportedTable table with Flag"); 
		appJdbcTemplate.update(sql, param);
		logger.info("record insterted into ExportedTable: "); 
	}



	@Override
	public List<DepartmentDto> checkItemGroup(DepartmentDto departmentDto) {
		String sql = "select * from HODATA..ItemGroup with (nolock) where GroupCode = '" + departmentDto.getGroupCode() + "'";
		logger.info("Checking groups exist or not..." + departmentDto.getGroupCode());
		List<DepartmentDto> groupsData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DepartmentDto.class));
		return groupsData;
	}
	
	@Override
	public List<DepartmentDto> checkMerchHierarchy(DepartmentDto departmentDto) {
		String sql = "select groupcode groupCode, grpName groupName, department deptName, gender, brand, divisionY division from USA..usapriority with (nolock) where GroupCode = '" + departmentDto.getGroupCode() + "'";
		logger.info("Checking groups exist or not in Merch hierarcy...");
		List<DepartmentDto> groupsData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DepartmentDto.class));
		return groupsData;
	}
	
	@Override
	public List<ItemLocationDTO> checkItemGroup(String itemCode) {
		String sql = "select * from HODATA..ItemGroup with (nolock) where GroupCode = '" + itemCode + "'";
		logger.info("check for groups...");
		List<ItemLocationDTO> salesPriceData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemLocationDTO.class));
		return salesPriceData;
	}
	
	@Override
	public List<ItemMasterDTO> getItems(String itemCode) {
		logger.info("Extracting records from Sales Price table.");
		String sql = "select itemCode, groupCode, catCode from HODATA..itemMaster with (nolock) where itemcode = '" + itemCode + "'";
		logger.info("Reading data for Sales Price table");
		List<ItemMasterDTO> items = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemMasterDTO.class));
		return items;
	}
	
	@Override
	public List<ItemMasterDTO> getItemMasterData(String goLiveDate) {
		logger.info("Getting Item master from HO data base.");
//		String sql = "select top 10 itemcode, description, shortName, groupCode, catCode, remarks from hodata..ItemMaster where OpeningDate >= '27/07/2023'";
		String sql = "select a.itemcode, a.Description, OpeningDate, a.groupcode, U.OrgRetRate RRP, U.RRPCurrency rrpCurrency, a.shortName, "
					+ " U.style, U.Material productMaterial, U.Color Color, U.Size1 size, U.GENDER gender, U.BOLNO bolNo, "
					+ " isnull(isnull((select top 1 suppname from hodata..vUSAOrder "
					+ " where groupcode = a.groupcode and suppname like 'TS%' order by sn desc), "
					+ " (select top 1 Suppliername from usa..AutoCategory where CategoryCode = a.groupcode and Suppliername like 'TS%' order by trndate desc)),'') "
					+ " suppliercode, U.vendor, U.CountryOrigin, US.Brand brandName, U.itemtype, UA.FileLoc itemImage from hodata..ItemMaster "
					+ " a JOIN usa..UPCBarCodes U ON (a.ItemCode = u.UPC) "
					+ " JOIN USA..usapriority US ON (a.GroupCode = us.groupcode) "
					+ " LEFT JOIN USA..UPCADDRESS UA ON (a.ItemCode = UA.UPC) "
					+ " where OpeningDate >='28/02/2024' and a.GroupCode <> '' and A.ItemCode not in (SELECT ITEMCODE FROM RMS..BFL_ITEMS_EXP) "
//					+ " AND a.ITEMCODE IN ('M0110230012') "
					+ " order by OpeningDate ";
		List<ItemMasterDTO> itemMaster = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemMasterDTO.class));
		return itemMaster;
	}
	

	
	public List<ItemMasterDTO> getMissingItemsData(String databaseName) {
		logger.info("Getting Item master from HO data base.");
		String sql = "select a.itemcode, a.Description, OpeningDate, a.groupcode, U.OrgRetRate RRP, U.RRPCurrency rrpCurrency, a.shortName, "
			+ "U.style, U.Material productMaterial, U.Color Color, U.Size1 size, U.GENDER gender, U.BOLNO bolNo, "
			+ "isnull(isnull((select top 1 suppname from hodata..vUSAOrder "
			+ "where groupcode = a.groupcode and suppname like 'TS%' order by sn desc), "
			+ "(select top 1 Suppliername from usa..AutoCategory where CategoryCode = a.groupcode and Suppliername like 'TS%' order by trndate desc)),'') "
			+ "suppliercode, U.vendor, U.CountryOrigin, US.Brand brandName, U.itemtype, UA.FileLoc itemImage from " + databaseName + "..ItemMaster "
			+ "a JOIN usa..UPCBarCodes U ON (a.ItemCode = u.UPC) "
			+ "JOIN USA..usapriority US ON (a.GroupCode = us.groupcode) "
			+ "LEFT JOIN USA..UPCADDRESS UA ON (a.ItemCode = UA.UPC) "
			+ "where a.GroupCode <> '' "
			+ " and a.ITEMCODE not in (SELECT ITEMCODE FROM RMS..BFL_ITEMS_EXP) "
			+ "AND a.ITEMCODE IN (SELECT ITEMCODE FROM RMS..Missing_items) "
			+ "order by OpeningDate";
		List<ItemMasterDTO> itemMaster = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemMasterDTO.class));
		return itemMaster;
	}
	
	public void truncateResaItems() {
		String sql = "truncate table RMS..missing_resa_items";
		SqlParameterSource param = new MapSqlParameterSource();
		appJdbcTemplate.update(sql, param);
	}


	@Override
	public List<ItemMasterDTO> getItemData(String goLiveDate) {
		logger.info("Getting Item master from HO data base.");
		String sql = "";
//		String sql = "SELECT TOP 300 "
//				+ " SKU itemCode, Item_Description Description, Style, COO, Brand brandName, Group_Code, Group_Brand group_brand, DEPT dept, CLASS clas, "
//				+ " SUBCLASS subclass, SUPPLIER supplierCode, "
//				+ " SUBCATEGORY, HSCODE, Pack_Ind, "
//				+ " Season, Phases, RRPCURR rrpCurrency, RRP, AEDRRP, Selling_Retail salesRate, gender, Color, Size, Allocation_Size allocationSize, "
//				+ " Product_Material productMaterial, "
//				+ " Ref_No, Container_No, Project_Code proectCode, LWH_UOM, Weight_UOM, Gross_Weight, Net_Weight, ImageURL itemImage, BOL_NO bolNo, Active, UPC "
////				+ " FROM hodata..Itemmaster_03Oct23 WHERE SKU = ''";
//				+ " FROM hodata..Itemmaster_03Oct23 WHERE SKU not in (SELECT ITEMCODE FROM RMS..BFL_ITEMS_EXP)";
		List<ItemMasterDTO> itemMaster = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemMasterDTO.class));
		return itemMaster;
	}
	
	public List<ItemMasterDTO> getItemLocRangingData(String goLiveDate) {
		try {
			String GO_LIVE_DATE = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
			logger.info("Getting Item master Raning from HO data base.");
			String sql = "select itemCode, SalesRate, RetailRate from hodata..SalesPrice with (nolock) where TrnDate  >='" + GO_LIVE_DATE + "' and TrMode != 'R'";
			List<ItemMasterDTO> itemMaster = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemMasterDTO.class));
			return itemMaster;
		} catch (Exception e) {
			logger.error("Error occured while getting the property in ranging API : - " + e);
			return null;
		}
	}

	
	@Override
	public String getBrandName(String itemCode) {
		String sql = "select Vendor from usa..UPCBarCodes with (nolock) where upc = '" + itemCode + "'";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String brandName = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		return brandName;
	}
	
	public void setExportedItems(ItemMasterConfigDTO itemMasterConfigDTO) {
		logger.info("Exporting records in BFL_ITEMS_Exp table.");
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "Insert into " + TRAN_FLAG_DB + "BFL_ITEMS_EXP(CREATE_DATETIME, ERROR, Exported, ItemCode) values (:CREATE_DATETIME, :ERROR, :Exported, :ItemCode)";
		SqlParameterSource param = new	MapSqlParameterSource()
			.addValue("CREATE_DATETIME", itemMasterConfigDTO.getCREATE_DATETIME())
			.addValue("ERROR", itemMasterConfigDTO.getERROR())
			.addValue("Exported", itemMasterConfigDTO.getExported())
			.addValue("ItemCode", itemMasterConfigDTO.getItemCode());
		logger.info("Inserting Transactions in Exported table with Flag"); 
		appJdbcTemplate.update(sql, param);
		logger.info("record insterted into ExportedTable: ");
	}


	
	public List<UdaDTO> getContNoRefNo(String sql) {
		logger.info("Getting Cont # and Ref # from HO data base.");
//		String sql = "SELECT ContNo ContNo, ContNo refNo, bol bolNo FROM USA.." + tableName + " WHERE Itemcode = '" + itemCode +  "'";
		List<UdaDTO> udaDto = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UdaDTO.class));
		return udaDto;
	}
	
	@Override
	public String getSalesPrice(String itemCode) {
		String sql = "SELECT SalesRate FROM HODATA..SalesPrice with (nolock) where ItemCode = '" + itemCode + "'";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String salesPrice = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		return salesPrice;
	}
	
	@Override
	public String getAllocationSize(String itemCode) {
		String sql = "SELECT ToysType FROM TFL..ToysLib with (nolock) where ItemCode = '" + itemCode + "'";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String allocationSize = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		return allocationSize;
	}

	@Override
	public List<BrandMasterDTO> getBrands(BrandMasterDTO brands) {
		String sql = "SELECT BRAND_NAME brandName, Brand_description brandDescription from USA..BRANDMASTER with (nolock) WHERE UPPER(BRAND_NAME) = UPPER('" + brands.getBrandName().replace("'s", "s").replace("'S", "S") + "')";
		List<BrandMasterDTO> brandsDto = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BrandMasterDTO.class));
		return brandsDto;
	}
	
	@Override
	public void updateTCMItemsPrice(PriceChangeDTO tcmChangeDTO) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("BFLDATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "update " + WHDataBase + "pricechange set NewPrice=:NewPrice, TrnDate=:TrnDate, Userid=:Userid, Remarks=:Remarks, Time1=:Time where ItemCode=:ItemCode";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("OldPrice", tcmChangeDTO.getOldPrice())
			.addValue("NewPrice", tcmChangeDTO.getNewPrice())
			.addValue("TrnDate", tcmChangeDTO.getTrnDate())
			.addValue("Userid", tcmChangeDTO.getUserid())
			.addValue("Remarks", tcmChangeDTO.getRemarks())
			.addValue("Time", tcmChangeDTO.getTime())
			.addValue("ItemCode", tcmChangeDTO.getItemCode());
		int update = appJdbcTemplate.update(sql, param);
		if(update == 0) {
			persistTCMItemsPrice(tcmChangeDTO);
		}
	}
	
	@Override
	public List<PriceChangeDTO> getTCMItemsPrice(String itemCode) {
		String sql = "SELECT TOP 1 itemcode itemCode, oldprice oldPrice, newprice newPrice, trndate trnDate from BFLDATA..PriceChange with (nolock) "
				+ " Where itemcode = '" + itemCode + "' order by trndate desc, time1 desc";
		List<PriceChangeDTO> brandsDto = null;
		try {
			brandsDto = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PriceChangeDTO.class));
		} catch (Exception e) {
			brandsDto = new ArrayList<PriceChangeDTO>();
		}
		return brandsDto;
	}
	
	@Override
	public List<PriceChangeDTO> getTCMItemsSlashing(String itemCode) {
		String sql = "SELECT TOP 1 itemcode itemCode, oldprice oldPrice, newprice newPrice, trndate trnDate from BFLDATA..deadstockcheck with (nolock) "
				+ " Where itemcode = '" + itemCode + "' order by trndate desc";
		List<PriceChangeDTO> brandsDto = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PriceChangeDTO.class));
		try {
			brandsDto = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PriceChangeDTO.class));
		} catch (Exception e) {
			brandsDto = new ArrayList<PriceChangeDTO>();
		}
		return brandsDto;
	}
	
	@Override
	public void persistContNos(SqlParameterSource param) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("BFLDATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "insert into " + WHDataBase + "usaContNos (ContNo, RefNo, checkdate, time1, Remarks, SNo) values (:RefNo, :ContNo, :TrnDate, :time, :reamrks, :SNo)";
		appJdbcTemplate.update(sql, param);
		logger.info("Data inserted into usacontno");
	}

	
	public List<UsaContNoDTO> getContNos(String rmsContNo, String legacyContNo) {
		String sql = "Select contNo, refNo from BFLDATA..usaContNos with (nolock) WHERE RefNo = '" + rmsContNo + "' or RefNo = '" + legacyContNo + "'";
		List<UsaContNoDTO> usaContNoDTOs = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UsaContNoDTO.class));
		return usaContNoDTOs;
	}

	
	public String getUsaContNos() {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("BFLDATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "SELECT top 1 SNo FROM " + WHDataBase + "USACONTNOS with (nolock) order by Sno desc";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String allocationSize = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		return allocationSize;
	}
	
	public String getContReceipt() {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("BFLDATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "SELECT top 1 SN FROM " + WHDataBase + "CONTRECEIPT with (nolock) order by Sn desc";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String allocationSize = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		return allocationSize;
	}

	
	public void updateRefNoForPurchaseOrder(String refNo, String poNum, String sn, double amount, double vatAmt, String groupcode, String shipment, String POL) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("HODATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "update " + WHDataBase + "USAORderHeader set ContNo=:refNo, InvAmount=:amt, VatAmt=:vat, ItemGroups=:groupCode, SBLC=:shipment, POL=:POL where ORAPONo=:ORAPONo and SN=:SN";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("refNo", refNo)
			.addValue("ORAPONo", poNum)
			.addValue("SN", sn)
			.addValue("amt", amount)
			.addValue("vat", vatAmt)
			.addValue("groupCode", groupcode)
			.addValue("shipment", shipment)
			.addValue("POL", POL);
		appJdbcTemplate.update(sql, param);
	}
	
	@Override
	public List<ContainerDetailsDTO> getLegacyContainerNo(String contNo) {
		String sql = "SELECT legacy, rms, trndate, containerNo FROM USA..Legacy_RMS_Containers with (nolock) WHERE RMS = '" + contNo + "'";
		List<ContainerDetailsDTO> containerDetailsDATA = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ContainerDetailsDTO.class));
		return containerDetailsDATA;
	}
	
	@Override
	public List<UpcBarcodesDTO> getContainerExistDetails(String contNo, String legacyContNo, String tableName) {
		String sql = "SELECT ContNo, upc, groupcode FROM " + tableName + " with (nolock) WHERE CONTNO = '" + contNo + "'";
		if(null != legacyContNo && !legacyContNo.isEmpty()) {
			sql = sql + " or contNo = '" + legacyContNo + "'";
		}
		List<UpcBarcodesDTO> usaOrgFileData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UpcBarcodesDTO.class));
		return usaOrgFileData;
	}

	@Override
	public void persistTCMItemsPrice(PriceChangeDTO tcmChangeDTO) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("BFLDATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql = "insert into " + WHDataBase + "pricechange (OldPrice, NewPrice, TrnDate, Userid, Remarks, Time1, ItemCode) values (:OldPrice, :NewPrice, :TrnDate, :Userid, :Remarks, :Time, :ItemCode)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("OldPrice", tcmChangeDTO.getOldPrice())
			.addValue("NewPrice", tcmChangeDTO.getNewPrice())
			.addValue("TrnDate", tcmChangeDTO.getTrnDate())
			.addValue("Userid", tcmChangeDTO.getUserid())
			.addValue("Remarks", tcmChangeDTO.getRemarks())
			.addValue("Time", tcmChangeDTO.getTime())
			.addValue("ItemCode", tcmChangeDTO.getItemCode());
		appJdbcTemplate.update(sql, param);
		logger.info("Inserted Price of tcm Items");
	}
	
	@Override
	public void persistTCMItemsSlashing(PriceChangeDTO tcmChangeDTO) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("BFLDATA");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
//		Itemcode	NewPrice	TrnDate	userId	ItemName	OldPrice
		String sql = "insert into " + WHDataBase + "deadstockcheck (OldPrice, NewPrice, TrnDate, Userid, ItemCode, itemName) values (:OldPrice, :NewPrice, :TrnDate, :Userid, :ItemCode, :itemName)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("OldPrice", (int) Double.parseDouble(tcmChangeDTO.getOldPrice()))
			.addValue("NewPrice", (int) Double.parseDouble(tcmChangeDTO.getNewPrice()))
			.addValue("TrnDate", tcmChangeDTO.getTrnDate())
			.addValue("Userid", tcmChangeDTO.getUserid())
			.addValue("Remarks", tcmChangeDTO.getRemarks())
			.addValue("itemName", (null != tcmChangeDTO.getItemName() && !tcmChangeDTO.getItemName().isEmpty() && tcmChangeDTO.getItemName().length() > 20) ? tcmChangeDTO.getItemName().substring(0, 19) : tcmChangeDTO.getItemName())
			.addValue("Time", tcmChangeDTO.getTime())
			.addValue("ItemCode", tcmChangeDTO.getItemCode());
		appJdbcTemplate.update(sql, param);
		logger.info("Inserted Price of tcm Items");
	}

	public void persistBrands(BrandMasterDTO brand, Date entryDate) {
		String WHDataBase = "";
		try {
			WHDataBase = ConfigProperties.getInstance().getConfigValue("WHDataBase");
			if(null != WHDataBase && !WHDataBase.isEmpty()) {
				WHDataBase = WHDataBase + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching warehouse database from Application properties");
		}
		String sql =  "Insert into " + WHDataBase + "BRANDMASTER (Brand_Name, Brand_Description, Entrydate, Remarks) values(:brandName, :brandDescription, :entryDate, :remarks)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("brandName", brand.getBrandName())
			.addValue("brandDescription", brand.getBrandDescription())
			.addValue("remarks", "R")
			.addValue("userId", "")
			.addValue("entryDate", entryDate);
		appJdbcTemplate.update(sql, param);
		logger.info("Brand inserted into brand master : " + brand.getBrandName());
	}
	
}