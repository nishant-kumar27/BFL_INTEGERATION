package com.bfl.dao;

import java.io.IOException;
import java.util.ArrayList;
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

@Repository
public class SalesDataDaoImpl implements ISalesDataDao {

	private static final Logger logger = LoggerFactory.getLogger(SalesDataDaoImpl.class); 

	@Autowired
	@Qualifier("appJdbcTemplate")
	private NamedParameterJdbcTemplate appJdbcTemplate;

	@Override
	public List<SalesHeaderDto> getSalesData(String fromDate) throws IOException {
		logger.info("Reading records from SalesHeader table.");
		String Count =  ConfigProperties.getInstance().getConfigValue("trn.count");
		String TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
		if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
			TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
		}
		String sql = "Select TOP " + Count + " SH.InvoiceNo, SH.InvoiceDate, SH.TrnType, SH.DebitCode, SH.CreditCode, SH.CustCode, SH.CostCode, SH.LocCode, "
				+ " SH.RepCode, SH.PaymentTerms, SH.GrossAmount, SH.TotalDiscount, SH.Expenses, SH.Netamount, SH.details discountReason, SH.PaidAmount, "
				+ " SH.SalesType, SH.DONo, SH.LPONo, SH.LPODate, SH.FCCode, SH.FCRate, SH.UserId, SH.PreparedBy, SH.ApprovedBy, SH.ReturnAmount, "
				+ " SH.details, SH.EntryMode, SH.costofsales, SH.Name, SH.Addr1, SH.Addr2, SH.Addr3, SH.pobox, SH.Tel, SH.Fax, SH.AreaCode, SH.LockInvoice, "
				+ " SH.Time1, SH.CardName, SH.CardNo, SH.CashAmt, SH.CreditAmt, SH.MobileNo, SH.ManualCard, SH.CrYes,SH.CreditNoteNo,SH.CreditNoteAmt, "
				+ " SH.Export,SH.LoyaltyCardNo, SH.LoyaltyPoint, SH.VoucherNo,SH.VoucherAmt,SH.BeamCardNo,SH.BeamAmt, SH.trnDate trndate, SH.CardAppCode, "
				+ " SH.CardRecptNo, SH.CreditNoteNo1, SH.CreditNoteAmt1, SH.CreditNoteNo2, SH.CreditNoteAmt2, SH.CreditNoteNo3, SH.CreditNoteAmt3, "
				+ " SH.CreditNoteNo4,SH.CreditNoteAmt4, SH.TotVohamount FROM SalesHeader SH WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " 
				+ TRAN_FLAG_DB + "BFL_TRANS_EXP ES "
				+ " WHERE ES.Exported='Y' and SH.InvoiceNo= ES.BFL_INVOICE_NO and SH.LocCode=ES.BFL_REG_NO and"
				+ " SH.CostCode = ES.BFL_STORE_ID) and SH.InvoiceDate >= '" + fromDate + "'"
//				+ " and SH.InvoiceNo in ('FSBI0029586', 'FSBI0029588', 'FSBI0029589', 'FSBI0029590', 'FSBI0029591')"
//				+ " and SH.InvoiceNo = 'FS70465988'"
				+ " order by SH.trndate ASC";
		List<SalesHeaderDto> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SalesHeaderDto.class));
		logger.info("Header records: " +records);
		return records;
	}
	
	@Override
	public List<StoreDTO> getStoreDetailsFromStoreId(String storeId) {
		logger.info("Reading records from Store table.");
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "Select STORE_NAME shopName, STORE_ID Rms_StoreId, STORE_SHORT_NAME storeShortName, VAT_REGION FROM " + TRAN_FLAG_DB + "BFL_STORE_CONFIG WHERE STORE_ID = '" + storeId + "'";
		List<StoreDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StoreDTO.class));
		return records;
	}

	@Override
	public List<SalesDetailsDto> getSalesDetailsData(String invoiceNo) {
		logger.info("Reading records from SalesDetails table.");
		String sql = "select SD.InvoiceNo,SD.ItemCode,SD.ItemDescription,SD.Quantity,SD.Rate,SD.Discount,SD.ReturnQty,SD.DONo,SD.QuotNo,"
				+ " SD.UnitCode,SD.BatchNo,SD.BasicQty,SD.BasicRate,SD.CostRate,SD.BasicReturnQty,SD.ItemRemarks,SD.LocCode,SD.RowNo MRow,"
				+ " SD.ExactRate,SD.TrfNo,SD.RFID from SalesDetail SD where SD.InvoiceNo = '" + invoiceNo + "'";
//		logger.info("Reading records from SalesDetails table.");
		List<SalesDetailsDto> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SalesDetailsDto.class));
//		logger.info("Header records: " +records);
		return records;
	}

	@Override
	public List<PaymentsDto> getPaymentsData(String invoiceNo) {
		String sql = "Select PT.InvoiceNo,PT.PaymentType,PT.RefNo,PT.Amount from Payments PT where Pt.InvoiceNo = '" + invoiceNo +"'";
//		logger.info("Reading records from payments table.");
		List<PaymentsDto> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PaymentsDto.class));
//		logger.info("payment records: " + records);
		return records;
	}
	
	public List<CreditNoteHistoryDTO> getCreditNoteHistory(String invoiceNo) {
		String sql = "Select SReturnNo, CrNoteNo, TotAmt, usedAmt, ReturnDate from CRNoteHistory where SReturnNo = '" + invoiceNo + "'";
		List<CreditNoteHistoryDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CreditNoteHistoryDTO.class));
		return records;
	}
	
	public List<CreditNoteHistoryDTO> getCreditNoteHistoryForSales(String invoiceNo) {
		String sql = "Select invoiceNo, crNoteNo, totalAmt, usedAmt, walletRef, walletAmt, newCrNoteNo, reIssdAmt from CreditNoteHistory where invoiceNo = '" + invoiceNo + "'";
		List<CreditNoteHistoryDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CreditNoteHistoryDTO.class));
		return records;
	}
	
	@Override
	public List<CreditCardDTO> getCreditCardData(String invoiceNo) {
		String sql = "select cc.Invoiceno as ccInvoice,cc.CCardName as ccname,cc.CCardNo as ccno,cc.CCreditAmt as ccamt,cc.CCardAppCode as cccode,cc.CCardRecptNo as ccrectno,cc.Bank as ccbank FROM CreditCardUse CC where CC.Invoiceno= '" + invoiceNo +"'";
//		logger.info("Reading records from Creditcarduse table.");
		List<CreditCardDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CreditCardDTO.class));
//		logger.info("credit card records: " +records);
		return records;
	}

	@Override
	public ItemMasterDTO getItemMasterDate(String itemCode) {
//		logger.info("Reading records from item master table.");
		String sql = "Select IM.ItemCode,IM.Description,IM.ShortName,IM.UnitCode,IM.GroupCode,IM.CatCode,IM.MinLevel,IM.ReorderLevel,IM.StockInHand,IM.OnOrder,IM.CostRate,"
				+ " IM.OpeningStock,IM.OpCostRate,IM.Batch,IM.OpeningDate,IM.CostPriceUpdated,IM.Remarks,IM.costcode,IM.ItemType,IM.ArabicItem,IM.Transfered,IM.ToPrint from"
				+ " ItemMaster IM where itemcode= '" + itemCode + "'";
		List<ItemMasterDTO> itemRecords = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ItemMasterDTO.class));
		ItemMasterDTO records = null;
		if(null != itemRecords && itemRecords.size() > 0) {
			records = itemRecords.get(0);
		}
		logger.info("Reading Latest Invoice records from itemMaster: " +records);
		return records;
	}	

	@Override 
	public void sendDataInExportedTable(TransactionConfigDTO transactionConfigDTO) {
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
		String sql = "Insert into " + TRAN_FLAG_DB + "BFL_Trans_Exp(Store_ID, REG_ID, TRAN_SEQ, BUSINESS_DATE, BFL_STORE_ID, BFL_REG_NO, BFL_INVOICE_NO, TRAN_TYPE, TRN_DATETIME, Exported, ERROR, CREATE_DATETIME, STORE_CLOSE_DATE) values(:Store_ID, :REG_ID, :TRAN_SEQ,:BUSINESS_DATE, :BFL_STORE_ID, :BFL_REG_NO, :BFL_INVOICE_NO, :TRAN_TYPE, :TRN_DATETIME, :Exported, :ERROR, :CREATE_DATETIME, :STORE_CLOSE_DATE)";
		SqlParameterSource param = new	MapSqlParameterSource()
			.addValue("Store_ID", transactionConfigDTO.getStore_ID())
			.addValue("REG_ID", transactionConfigDTO.getREG_ID())
			.addValue("TRAN_SEQ", transactionConfigDTO.getTRAN_SEQ())
			.addValue("BUSINESS_DATE", transactionConfigDTO.getBUSINESS_DATE())
			.addValue("BFL_STORE_ID", transactionConfigDTO.getBFL_STORE_ID())
			.addValue("BFL_REG_NO", transactionConfigDTO.getBFL_REG_NO())
			.addValue("BFL_INVOICE_NO", transactionConfigDTO.getBFL_INVOICE_NO())
			.addValue("TRAN_TYPE", transactionConfigDTO.getTRAN_TYPE())
			.addValue("Exported", transactionConfigDTO.getExported())
			.addValue("TRN_DATETIME", new Date())
			.addValue("ERROR", transactionConfigDTO.getERROR())
			.addValue("STORE_CLOSE_DATE", transactionConfigDTO.getSTORE_CLOSE_DATE())
			.addValue("CREATE_DATETIME", new Date());
		logger.info("Inserting Transactions in ExportedTable table with Flag"); 
		appJdbcTemplate.update(sql, param);
		logger.info("record insterted into ExportedTable: "); 
	}
	
	@Override
	public List<RTLogConfigDTO> getDataFromRtlogConfig(String locCode, String tranType) {
		logger.info("Reading records from BFL_RTLOG_CONFIG table.");
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
//		BFLCodes -- CashierId
//		BFLVALUES -- locCode
		String sql = "select RC.RECORD_TYPE,RC.BFL_VALUES,RC.RESA_VALUES,RC.RESA_CODES, RC.BFL_CODES from " + TRAN_FLAG_DB + "BFL_RTLOG_CONFIG RC where RC.RECORD_TYPE = 'REGISTER_ID' ";
		
		if(null != locCode && !locCode.isEmpty()) {
			sql = sql.concat(" and BFL_VALUES = '" + locCode + "'");
		}
//		else if("DailyClosing".equals(locCode)) {
//			sql = sql + " and BFL_CODES = '" + cashierId + "'";
//		}
		sql = sql.concat(" order by RESA_VALUES desc");
		
//		if(null != locCode && !locCode.isEmpty() && null != cashierId && !cashierId.isEmpty() && !"DailyClosing".equals(locCode)) {
//			sql = sql + " and BFL_CODES = '" + cashierId + "' and BFL_VALUES = '" + locCode + "'";
//		} else if("DailyClosing".equals(locCode)) {
//			sql = sql + " and BFL_CODES = '" + cashierId + "'";
//		}
//		sql = sql + " order by RESA_VALUES desc";
		List<RTLogConfigDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(RTLogConfigDTO.class));
		logger.info("Get Records from: BFL_RTLOG_CONFIG " + records.size());
		return records;
	}
	
	@Override
	public List<RTLogConfigDTO> getLatestDataFromRtlogConfig() {
		logger.info("Reading records from BFL_RTLOG_CONFIG table.");
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "select RC.RECORD_TYPE,RC.BFL_VALUES,RC.RESA_VALUES,RC.RESA_CODES, RC.BFL_CODES from " + TRAN_FLAG_DB + "BFL_RTLOG_CONFIG RC where RC.RECORD_TYPE = 'REGISTER_ID' ";
		sql = sql.concat(" order by RESA_VALUES desc");
		List<RTLogConfigDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(RTLogConfigDTO.class));
		logger.info("Get Records from: BFL_RTLOG_CONFIG " +records);
		return records;
	}
	
	@Override
	public List<SalesHeaderDto> getStationId(String cashierId, String invoiceDate) {
		logger.info("Reading records from BFL_RTLOG_CONFIG table.");
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "select LocCode from SalesHeader WHERE PreparedBy = '" + cashierId + "' and InvoiceDate = '" + invoiceDate + "' order by InvoiceNo desc";
		List<SalesHeaderDto> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SalesHeaderDto.class));
		logger.info("Get Records from: BFL_RTLOG_CONFIG " + records);
		return records;
	}
	
	@Override
	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode, String row) {
		logger.info("Reading records from InvoiceVatItem table.");
		String sql = "select Top 1 IVD.invoiceno,IVD.Itemcode,IVD.Discount,IVD.VatPer,IVD.VatAmt,IVD.VatCode,IVD.MRow,IVD.Loyalty from InvoiceVatItems IVD "
				+ " where invoiceno ='" + invoiceNo + "' and itemcode= '" + itemCode +"' and IVD.MRow = '" + row + "'";
		List<InvoiceVatItemDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(InvoiceVatItemDTO.class));
		logger.info("Invoice Vat records: " +records);
		return records;
	}

	
	@Override
	public String getNextPayRollTransSequence() {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql =  "SELECT NEXT VALUE FOR " + TRAN_FLAG_DB + "TRANS_PAYROLL_SEQ";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String transSeq = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		return transSeq;
	}
	/*
	@Override
	public List<SalesHeaderDto> getSalesHeaderFirstTransaction() {
		logger.info("getting first transaction for store open");
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "Select TOP 1 SH.InvoiceNo,SH.InvoiceDate,SH.CustCode,SH.CostCode,SH.LocCode,SH.UserId,SH.PreparedBy, SH.ApprovedBy,SH.Time1,SH.trndate FROM SalesHeader SH"
				+ " WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB + " BFL_TRANS_EXP ES WHERE ES.Exported='Y' and SH.InvoiceDate= ES.BUSINESS_DATE"
				+ ") order by InvoiceDate desc";
		List<SalesHeaderDto> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SalesHeaderDto.class));
		return records;
	}
	*/

	
	@Override
	public List<VATDTO> getVatDetails(String vat_REGION, int vatPer) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "SELECT VAT_REGION vatRegion, VAT_CODE vatCode, VAT_CODE_RATE vatCodeRate, VAT_TYPE vatType from " + TRAN_FLAG_DB + "BFL_VAT_REGION WHERE VAT_REGION = '" + vat_REGION  + "' and VAT_CODE_RATE = '" + vatPer + "'";
		List<VATDTO> vatDTO = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(VATDTO.class));
		return vatDTO;
	}
	
	@Override
	public String getOriginaTransactionNo(String invoiceNo) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "select TRAN_SEQ FROM " + TRAN_FLAG_DB + "BFL_TRANS_EXP "
					+ " WHERE TRAN_TYPE = 'SALE' and BFL_INVOICE_NO = '" + invoiceNo + "' and EXPORTED = 'Y'";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String transSeq = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		return transSeq;
	}
	
	public String getLastTransactionNo(String registerNo) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql =  "SELECT TOP 1 TRAN_SEQ FROM " + TRAN_FLAG_DB + "BFL_TRANS_EXP WHERE REG_ID = '" + registerNo + "' order by TRAN_SEQ desc";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String transSeq = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		return transSeq;
	}
	
	public void setRTLogConfigDTO(List<RTLogConfigDTO> rtlogData) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "Insert into " + TRAN_FLAG_DB + " BFL_RTLOG_CONFIG(RECORD_TYPE, BFL_VALUES, BFL_CODES, RESA_VALUES, RESA_CODES) values(:RECORD_TYPE, :BFL_VALUES, :BFL_CODES, :RESA_VALUES, :RESA_CODES)";
		logger.info("Inserting Transactions in for maintaining the register/ Cashier mapping");
		List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
		for(RTLogConfigDTO rtLogConfigDTO : rtlogData) {
			SqlParameterSource param = new	MapSqlParameterSource() 
				.addValue("RECORD_TYPE", rtLogConfigDTO.getRECORD_TYPE())
				.addValue("BFL_VALUES", rtLogConfigDTO.getBFL_VALUES())
				.addValue("BFL_CODES", rtLogConfigDTO.getBFL_CODES())
				.addValue("RESA_VALUES", rtLogConfigDTO.getRESA_VALUES()) 
				.addValue("RESA_CODES", rtLogConfigDTO.getRESA_CODES());
			paramList.add(param);
		}
		int [] records = appJdbcTemplate.batchUpdate(sql, paramList.toArray(new SqlParameterSource[0]));
		logger.info("record insterted for maintaining the register/ Cashier mapping and No of records are :: " + records.length); 
	
	}
}