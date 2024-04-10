package com.bfl.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.bfl.ConfigProperties;
import com.bfl.dto.ApexDataExportDTO;
import com.bfl.dto.BflEwalletLogDTO;
import com.bfl.dto.BflIntegrationDataDTO;
import com.bfl.dto.GiftCardEntryLogDTO;
import com.bfl.dto.InvoiceReprintHistoryDTO;
import com.bfl.dto.ItemDeleteDTO;
import com.bfl.dto.MissingBarcodeDetailDTO;
import com.bfl.dto.MissingBarcodeHeaderDTO;
import com.bfl.dto.PriceDetailsAgeingDTO;
import com.bfl.dto.PriceHeaderAgeingDTO;
import com.bfl.dto.StoreConfigDTO;


@Repository
public class FoundationDataDaoImpl implements IFoundationDataDao {

	private static final Logger logger = LoggerFactory.getLogger(FoundationDataDaoImpl.class); 

	@Autowired
	@Qualifier("appJdbcTemplate")
	private NamedParameterJdbcTemplate appJdbcTemplate;

//	@Override
//	public void persistDepartments(List<DepartmentDto> departments) {
//		String sql =  "Insert into ItemGroup (GroupCode,Description) values(:GroupCode, :Description)";
//		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
//		for(DepartmentDto departmentDto : departments) {
//			SqlParameterSource param = new MapSqlParameterSource()
//				.addValue("GroupCode", departmentDto.getGroupCode())
//				.addValue("Description", departmentDto.getGroupName());
////				.addValue("ShortName", departmentDto.getShortName());
//			paramList.add(param);
//		}
//		logger.info("Inserting Deprtments in ItemGroup table.");
//		int [] records = appJdbcTemplate.batchUpdate(sql, paramList.toArray(new SqlParameterSource[0]));
//		logger.info("Total Deprtment records insterted: " + records.length);
//	}
	
	@Override
	public void persistStoresData(StoreConfigDTO storeDto) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql =  "Insert into " + TRAN_FLAG_DB + "BFL_STORE_CONFIG (COUNTRY, STORE_NAME, STORE_ID, STORE_SHORT_NAME, VAT_REGION, STATUS) values(:COUNTRY, :STORE_NAME, :STORE_ID, :STORE_SHORT_NAME, :VAT_REGION, :STATUS)";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("COUNTRY", storeDto.getCountry())
			.addValue("STORE_NAME", storeDto.getStoreName())
			.addValue("STORE_ID", storeDto.getStoreId())
			.addValue("STORE_SHORT_NAME", storeDto.getStoreShortName())
			.addValue("VAT_REGION", storeDto.getVatRegion())
			.addValue("STATUS", storeDto.getStatus());
		logger.info("Inserting store in Store Config Dto table.");
		appJdbcTemplate.update(sql, param);
	}
	
	@Override
	public void updateStoresData(StoreConfigDTO storeDto) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "update " + TRAN_FLAG_DB + "BFL_STORE_CONFIG set COUNTRY=:COUNTRY, STORE_ID=:STORE_ID, STORE_SHORT_NAME=:STORE_SHORT_NAME, VAT_REGION=:VAT_REGION, STATUS=:STATUS where STORE_SHORT_NAME=:STORE_SHORT_NAME";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("COUNTRY", storeDto.getCountry())
			.addValue("STORE_ID", storeDto.getStoreId())
			.addValue("STORE_SHORT_NAME", storeDto.getStoreShortName())
			.addValue("VAT_REGION", storeDto.getVatRegion())
			.addValue("STATUS", storeDto.getStatus());
		logger.info("updating data in Stores Config table.");
		int update = appJdbcTemplate.update(sql, param);
		if(update == 0) {
			persistStoresData(storeDto);
		}
	}


	@Override
	public List<MissingBarcodeDetailDTO> getMissingBarcodeDetailsRequest(String trnType) {
		int Count = 1;
		String TRAN_FLAG_DB = "";
		String goLiveDate = "";
		try {
			Count = Integer.parseInt(ConfigProperties.getInstance().getConfigValue("pst.count"));
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching count from Application properties");
		}
		String sql = "select Top " + Count + " mbd.EntryNo as entryNo, mbd.Itemcode as itemCode, mbd.Itemname as itemDesc, mbd.quantity as quantity, "
				+ " mbd.Price as price from MissingBarcodeDetail mbd JOIN MissingBarcodeHeader mbh ON mbd.EntryNo = mbh.EntryNo "
				+ " WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB + "BFL_EXP_DATA ES WHERE ES.Exported='Y' and mbd.EntryNo = ES.BFL_INVOICE_NO "
				+ " and ES.TRAN_TYPE = '" + trnType + "') "
				+ " and mbh.trnDate >= '" + goLiveDate + "' order by mbd.EntryNo DESC";
		logger.info("Reading data for missing barcode details");
		List<MissingBarcodeDetailDTO> missingBarcodeDetails = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(MissingBarcodeDetailDTO.class));
		return missingBarcodeDetails;
	}


	@Override
	public String getLastProcessingTimestamp(Long jobId) {
		String sql = "select LAST_SUCCESS_PROCESS_TS from BFL_RIT_PROCESS_TS where JOB_ID='"+jobId+"'";
		SqlParameterSource parameters = new MapSqlParameterSource();
		logger.info("Reading last download timestamp from LAST_INVOICE_DOWNLOAD_TIMESTAMP table.");
		String lastTimestamp = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		logger.info("lastTimestamp: "+lastTimestamp);
		return lastTimestamp;
	}


	@Override
	public void updateLastProcessingTimestamp(String timestamp, Long jobId, String jobName) {
		String sql =  "update BFL_RIT_PROCESS_TS set LAST_SUCCESS_PROCESS_TS=:timestamp where JOB_ID=:jobId";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("timestamp", timestamp)
				.addValue("jobId", jobId);
		logger.info("Updating last timestamp in BFL_RIT_PROCESS_TS table.");
		appJdbcTemplate.update(sql, parameters);
		logger.info("updated...");
	}


	@Override
	public void insertLastProcessingTimestamp(String timestamp, Long jobId,  String jobName) {
		String sql =  "insert into BFL_RIT_PROCESS_TS(LAST_SUCCESS_PROCESS_TS, JOB_ID, JOB_NAME) values (:timestamp, :jobId, :jobName)";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("timestamp", timestamp)
				.addValue("jobId", jobId)
				.addValue("jobName", jobName);
		logger.info("Inserting last timestamp in BFL_RIT_PROCESS_TS table.");
		appJdbcTemplate.update(sql, parameters);
		logger.info("Saved...");
	}


	@Override 
	public void sendDataInExportedTable(ApexDataExportDTO dataToBeExported) {
		logger.info("Exporting records in BFL_Data_Exp table.");
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "Insert into " + TRAN_FLAG_DB + "BFL_EXP_DATA(BFL_INVOICE_NO,BFL_TABLE_NAME,TRAN_TYPE,TRN_DATETIME,EXPORTED,ERROR,CREATE_DATETIME) values(:BFL_INVOICE_NO, :BFL_TABLE_NAME, :TRAN_TYPE,:TRN_DATETIME, :EXPORTED, :ERROR, :CREATE_DATETIME)";
		SqlParameterSource param = new	MapSqlParameterSource() 
				.addValue("BFL_INVOICE_NO", dataToBeExported.getInvoiceNo())
				.addValue("BFL_TABLE_NAME", dataToBeExported.getTable_Name())
				.addValue("TRAN_TYPE", dataToBeExported.getTran_Type())
				.addValue("TRN_DATETIME", new Date()) 
				.addValue("EXPORTED", dataToBeExported.getExported())
				.addValue("ERROR", dataToBeExported.getError())
				.addValue("CREATE_DATETIME", new Date());
		logger.info("Inserting Transactions in BFL_EXP_DATA table with Flag"); 
		appJdbcTemplate.update(sql, param);
		logger.info("record insterted into BFL_EXP_DATA: "); 
	}

	@Override
	public List<InvoiceReprintHistoryDTO> getInvoiceReprintHistoryRequest(String trnType) {
		logger.info("Extracting records from tmpPrintHistory table.");
		int Count = 1;
		String TRAN_FLAG_DB = "";
		String goLiveDate = "";
		try {
			Count = Integer.parseInt(ConfigProperties.getInstance().getConfigValue("pst.count"));
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching count from Application properties");
		}
		String sql = "Select TOP " + Count + " PH.invoiceno,PH.trndate, PH.userid, PH.Reason,PH.MgrCode,PH.MgrPass,PH.MgrName from tmpPrintHistory PH "
				+ " WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB +"BFL_EXP_DATA ES WHERE ES.Exported='Y' and "
				+ " PH.invoiceno = ES.BFL_INVOICE_NO and TRAN_TYPE = '" + trnType + "') "
				+ " and PH.trnDate >= '" + goLiveDate + "' order by PH.trndate ASC";
		logger.info("Reading data for invoice reprint history details");
		List<InvoiceReprintHistoryDTO> invoiceReprintHistory = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(InvoiceReprintHistoryDTO.class));
		return invoiceReprintHistory;
	}

	@Override
	public List<ItemDeleteDTO> getItemDeleteRequest(String trnType) {
		logger.info("Extracting records from ItemDelete table.");
		int Count = 1;
		String TRAN_FLAG_DB = "";
		String goLiveDate = "";
		try {
			Count = Integer.parseInt(ConfigProperties.getInstance().getConfigValue("pst.count"));
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching count from Application properties");
		}
		String sql = "Select Top " + Count + " ID.TrnDate, ID.Time1, ID.Invoiceno, ID.ItemCode, ID.Qty, ID.SPrice, "
				+ " ID.UserId, ID.CompName, ID.ApprovedBy, ID.Reason, ID.username from ItemDelete ID WHERE NOT EXISTS "
				+ " (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB + "BFL_EXP_DATA ES WHERE ES.Exported='Y' "
				+ " and ID.Invoiceno = ES.BFL_INVOICE_NO and ES.TRAN_TYPE = '" + trnType + "') "
				+ " and ID.TrnDate >= '" + goLiveDate + "' " + " order by ID.TrnDate ASC";
		logger.info("Reading data for ItemDelete details");
		List<ItemDeleteDTO> itemDelete = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemDeleteDTO.class));
		return itemDelete;
	}
	
	@Override
	public List<BflEwalletLogDTO> getBflEwalletLogRequest(String trnType) {
		logger.info("Extracting records from EWallet table.");
		int Count = 1;
		String TRAN_FLAG_DB = "";
		String goLiveDate = "";
		try {
			Count = Integer.parseInt(ConfigProperties.getInstance().getConfigValue("pst.count"));
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching count from Application properties");
		}
		String sql = "Select TOP " + Count + " EW.SN, EW.EntryDate,EW.CardNo,EW.Amount,EW.PayMode,EW.Mobile,EW.status,EW.Trnno,EW.Trndate,EW.RefNo,EW.userid,EW.trntime,EW.confirmationno, "
				+ "	EW.Normal_Reload,EW.Credit_Reload,EW.Loyalty_Reload,EW.Loyalty_Reload,EW.Other_Reload,EW.Points_Added,EW.previouspoints,EW.pointsbalance,EW.previousBalance,EW.balance,EW.bonusAmount, "
				+ "	EW.confirmed_balance,EW.cardtype,EW.Recordno,EW.Closed,EW.reconciled,EW.OnlineCode from EWalletLog EW "
				+ "	WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB + "BFL_EXP_DATA ES WHERE ES.Exported='Y' "
				+ " and EW.SN = ES.BFL_INVOICE_NO and ES.TRAN_TYPE = '" + trnType + "') "
				+ " and EW.EntryDate >= '" + goLiveDate + "' "
				+ " order by EW.EntryDate ASC";
		logger.info("Reading data for bfl Ewallet Log");
		List<BflEwalletLogDTO> bflEWalletLog = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BflEwalletLogDTO.class));
		return bflEWalletLog;
	}

	@Override
	public List<GiftCardEntryLogDTO> getGiftCardLogRequest(String trnType) {
		logger.info("Extracting records from GiftCardEntry table.");
		int Count = 1;
		String TRAN_FLAG_DB = "";
		String goLiveDate = "";
		try {
			Count = Integer.parseInt(ConfigProperties.getInstance().getConfigValue("pst.count"));
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching count from Application properties");
		}
		String sql = "Select Top " + Count + " GC.SN, GC.EntryDate, GC.CardNo, GC.Amount, GC.PayMode, GC.Mobile, GC.status, GC.Trnno, GC.Trndate, GC.RefNo, "
					+ " GC.userid, GC.firstName, GC.middleName, GC.lastName, GC.email, GC.gender from GiftCardEntry GC "
					+ " WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB + "BFL_EXP_DATA ES WHERE ES.Exported='Y' "
					+ " and GC.SN = ES.BFL_INVOICE_NO and TRAN_TYPE = '" + trnType + "') and GC.EntryDate >= '" + goLiveDate + "' order by GC.EntryDate DESC";
		logger.info("Reading data for bfl giftcard Log");
		List<GiftCardEntryLogDTO> bflGiftCardLog = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(GiftCardEntryLogDTO.class));
		return bflGiftCardLog;
	}

	@Override
	public List<PriceDetailsAgeingDTO> getPriceDetailsAgeingRequest(String trnType) {
		logger.info("Extracting records from PriceDetailsAgeing table.");
		int Count = 1;
		String TRAN_FLAG_DB = "";
		String goLiveDate = "";
		try {
			Count = Integer.parseInt(ConfigProperties.getInstance().getConfigValue("pst.count"));
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching properties values from Application properties");
		}
		String sql = "Select TOP " + Count + " PA.EntryNo, PA.ItemCode, PA.Quantity, PA.SalesPrice, PA.NewPrice, PA.TrfNo, PA.TrfDate, PA.IDNo, "
				+ " PA.Status, PA.RFId from PriceDetailAgeing PA WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " 
				+ TRAN_FLAG_DB + "BFL_EXP_DATA ES WHERE ES.Exported='Y' and PA.EntryNo = ES.BFL_INVOICE_NO "
				+ " and ES.TRAN_TYPE = '" + trnType + "') "
				+ " and PA.TrfDate >= '" + goLiveDate + "' "
				+ " order by PA.EntryNo DESC";
		logger.info("Reading data for bfl PriceDetailsAgeing");
		List<PriceDetailsAgeingDTO> bflPriceDetailAgeingLog = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PriceDetailsAgeingDTO.class));
		return bflPriceDetailAgeingLog;
	}

	@Override
	public List<PriceHeaderAgeingDTO> getPriceHeadAgeingRequest(String trnType) {
		logger.info("Extracting records from PriceHeadAgeing table.");
		int Count = 1;
		String TRAN_FLAG_DB = "";
		String goLiveDate = "";
		try {
			Count = Integer.parseInt(ConfigProperties.getInstance().getConfigValue("pst.count"));
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching count from Application properties");
		}
		String sql = "select TOP " + Count + " PHA.EntryNo, PHA.EntryDate, PHA.EntryType, PHA.SubLocCode, PHA.Remarks, PHA.UserId, PHA.Delivery, PHA.TrfNo1, "
				+ " PHA.TrfNo2, PHA.LabelType, PHA.AgeingOther from PriceHeaderAgeing PHA WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " 
				+ TRAN_FLAG_DB + "BFL_EXP_DATA ES WHERE ES.Exported='Y' and PHA.EntryNo = ES.BFL_INVOICE_NO "
				+ " and ES.TRAN_TYPE = '" + trnType + "') "
				+ " and PHA.EntryDate >= '" + goLiveDate + "' order by PHA.EntryNo DESC";
		logger.info("Reading data for bfl PriceHeadAgeing ");
		List<PriceHeaderAgeingDTO> bflPriceHeaderAgeingLog = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PriceHeaderAgeingDTO.class));
		return bflPriceHeaderAgeingLog;
	}

	@Override
	public List<StoreConfigDTO> getStoreDTO(String storeId) {
		logger.info("Extracting records from DataSettings table.");
		String BFLDATA = "";
		try {
			BFLDATA = ConfigProperties.getInstance().getConfigValue("BFLDATA");
			if(null != BFLDATA && !BFLDATA.trim().isEmpty()) {
				BFLDATA = BFLDATA  + "..";
			}
		} catch (Exception e) {
			logger.error("Error occured while getting datasettings database name : " + e);
			BFLDATA = "";
		}
		//		String sql = "select shopName, dataname, unitCode, fcCode, fcRate, costCodeFrom, locCodeFrom, costCodeTo, locCodeTo, tcmItemCode, usaItemCode, transfer, targetDatabase, costCode, itemdisc, tcmTarget, storeId, shopEmail, Rms_StoreId from DataSettings where Rms_StoreId = '" + location + "' and dataName = '" + databaseName + "'";
		String sql = "SELECT COUNTRY country, STORE_NAME storeName, STORE_ID storeId, STORE_SHORT_NAME storeShortName, VAT_REGION vatRegion, STATUS status FROM RMS..BFL_STORE_CONFIG WHERE STORE_ID = '" + storeId + "'"; 
		//		String sql = "select shopName, dataname, unitCode, fcCode, fcRate, costCodeFrom, locCodeFrom, costCodeTo, locCodeTo, tcmItemCode, usaItemCode, transfer, targetDatabase, costCode, itemdisc, tcmTarget, storeId, shopEmail, Rms_StoreId from DataSettings where Rms_StoreId = '" + location + "'";
		logger.info("Reading data for data settings");
		List<StoreConfigDTO> storeDto = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StoreConfigDTO.class));
		return storeDto;
	}

	@Override
	public List<MissingBarcodeHeaderDTO> getMissingBarcodeHeaderRequest(String trnType) {
		logger.info("Extracting records from MissingBarcodeHeader table.");
		int Count = 1;
		String TRAN_FLAG_DB = "";
		String goLiveDate = "";
		try {
			Count = Integer.parseInt(ConfigProperties.getInstance().getConfigValue("pst.count"));
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error while fetching count from Application properties");
		}
		String sql = "Select TOP " + Count + " MBA.EntryNo, MBA.Trndate, MBA.Time1,MBA.Remarks,MBA.PreparedBy,MBA.ShopManager,MBA.BarcodePrintDate, "
				+ " MBA.DispatchDate,MBA.ReceivedBy,MBA.ReceivedDate,MBA.UserId,MBA.Loccode from MissingBarcodeHeader MBA "
				+ " WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB + "BFL_EXP_DATA ES WHERE ES.Exported='Y' "
				+ " and MBA.EntryNo = ES.BFL_INVOICE_NO and TRAN_TYPE = '" + trnType + "') and MBA.Trndate >= '" + goLiveDate + "' "
				+ " order by MBA.EntryNo DESC ";
		logger.info("Reading data for bfl MissingBarcodeHeader ");
		List<MissingBarcodeHeaderDTO> missingBarcodeHeaderData = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(MissingBarcodeHeaderDTO.class));
		return missingBarcodeHeaderData;
	}
	
	@Override
	public List<BflIntegrationDataDTO> getBflIntegrationDataRequest() {
		logger.info("Extracting records from BFL_INTEGRATION_DATA table.");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		Date dateBefore1Day = cal.getTime();
		String business_date = new SimpleDateFormat("dd/MM/YYYY").format(dateBefore1Day);
		
		String sql = "SELECT CONTAINER_MAPPING containerMapping, BUSINESS_DATE businessDate, ITEM_GROUPS itemGroups, ITEMS items, ITEM_RECLASSIFY itemReclassify, "
					+ "ORDER_NEW orderNew, ORDER_MOD orderMod, ORDER_CANCELLED orderCancelled, SHIPMENTS shipments, CONTAINER_ID containerId, MANIFEST_UPLOAD manifestUpload, "
					+ "PRICE_CHANGES priceChanges, PROMOTIONS promotions, CLEARANCES slashing, BRANDS brands, SUPPLIERS suppliers, DIVISION division, DEPT dept, "
					+ "CATEGORY category, SUBCATEGORY subCategory From RMS..BFL_INTEGRATION_DATA WHERE business_date = '" + business_date + "'";
		logger.info("Reading data for bfl BFL_INTEGRATION_DATA ");
		List<BflIntegrationDataDTO> bflIntegrationDataDTOs = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BflIntegrationDataDTO.class));
		return bflIntegrationDataDTOs;
	}



}