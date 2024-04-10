package com.bfl.dao;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bfl.ConfigProperties;
import com.bfl.dto.InvoiceVatItemDTO;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.dto.PaymentsDto;
import com.bfl.dto.RTLogConfigDTO;
import com.bfl.dto.SalesDetailsDto;
import com.bfl.dto.SalesHeaderDto;
import com.bfl.dto.StoreDTO;

@Repository
public class OtherStoreReturnSalesDaoImpl implements IOtherStoreReturnSalesDao{

	private static final Logger logger = LoggerFactory.getLogger(SalesDataDaoImpl.class); 

	@Autowired
	@Qualifier("appJdbcTemplate")
	private NamedParameterJdbcTemplate appJdbcTemplate;

	@Override
	public List<SalesHeaderDto> getOtherStoreReturnSalesData(String fromDate) throws IOException {
		logger.info("Reading records from Other Store Return SalesHeader table.");
		String Count =  ConfigProperties.getInstance().getConfigValue("trn.count");
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "Select top " + Count + " RSH.SReturnNo ,RSH.SReturnDate ,RSH.TrnType ,RSH.DebitCode ,RSH.CreditCode ,RSH.RefType ,RSH.InvoiceDONo ,RSH.CustCode"
				+ "	,RSH.CostCode ,RSH.LocCode ,RSH.RepCode ,RSH.GrossAmount ,RSH.TotalDiscount ,RSH.NetAmount ,RSH.FCCode ,RSH.FCRate ,RSH.UserId ,RSH.ApprovedBy"
				+ "	,RSH.PreparedBy ,RSH.EntryMode ,RSH.RevMarginCode ,RSH.Remarks ,RSH.MobileNo ,RSH.Reason ,RSH.EmpName ,RSH.CreditNoteNo ,RSH.CreditInvNo"
				+ "	,RSH.CreditExpiryDt ,RSH.TrnDate, RSH.shop FROM SreturnsOthheader RSH WHERE NOT EXISTS "
				+ " (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB + "BFL_TRANS_EXP ES"
				+ "	WHERE ES.Exported='Y' and RSH.SReturnNo = ES.BFL_INVOICE_NO and RSH.LocCode=ES.BFL_REG_NO and RSH.CostCode = ES.BFL_STORE_ID) "
				+ " and RSH.SReturnDate >= '" + fromDate + "' and RSH.shop != 'ONLINE' "
				+ "	order by RSH.trndate DESC";
		List<SalesHeaderDto> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SalesHeaderDto.class));
		logger.info("Other Store Return Records: " +records);
		return records;
	}
	
	@Override
	public List<StoreDTO> getStoreDetials(String storeName) throws Exception {
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
		String sql = "Select STORE_NAME shopName, STORE_ID Rms_StoreId, STORE_SHORT_NAME storeShortName, VAT_REGION FROM " + TRAN_FLAG_DB + "BFL_STORE_CONFIG WHERE STORE_NAME = '" + storeName + "' and STATUS = 'A'";
		List<StoreDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StoreDTO.class));
		return records;
	}
	
	@Override
	public List<StoreDTO> getStoreFromStoreId(String storeId) throws Exception {
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
		String sql = "Select STORE_NAME shopName, STORE_ID Rms_StoreId, STORE_SHORT_NAME storeShortName, VAT_REGION FROM " + TRAN_FLAG_DB + "BFL_STORE_CONFIG WHERE STORE_ID = '" + storeId + "' and STATUS = 'A'";
		List<StoreDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StoreDTO.class));
		return records;
	}

	@Override
	public List<SalesDetailsDto> getOtherStoreReturnSalesDetailsData(String invoiceNo) {
		logger.info("Reading records from Other Store Return Sales Details table.");
		String sql = "Select RSD.SReturnNo ,RSD.ItemCode ,RSD.Quantity ,RSD.Rate ,RSD.Discount ,RSD.UnitCode ,RSD.BatchNo"
				+ "	,RSD.BasicQty ,RSD.BasicRate,RSD.ItemDesc from SreturnsOthDetail RSD where RSD.SReturnNo= '" + invoiceNo + "'";
		logger.info("Reading records from OtherStoreReturnSalesDetails table.");
		List<SalesDetailsDto> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SalesDetailsDto.class));
		logger.info("OtherStoreReturnSalesDetails records: " +records);
		return records;
	}

	@Override
	public List<PaymentsDto> getPaymentsData(String invoiceNo) {
		String sql = "Select PT.InvoiceNo,PT.PaymentType,PT.RefNo,PT.Amount from Payments PT where Pt.InvoiceNo = '" + invoiceNo +"'";
		logger.info("Reading records from payments table.");
		List<PaymentsDto> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PaymentsDto.class));
		logger.info("payment records: " +records);
		return records;
	}

	@Override
	public ItemMasterDTO getItemMasterDate(String itemCode) {
		logger.info("Reading records from item master table.");
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
	public List<RTLogConfigDTO> getDataFromRtlogConfig() {
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
		String sql = "Select RC.RECORD_TYPE,RC.BFL_VALUES,RC.RESA_VALUES,RC.RESA_CODES from " + TRAN_FLAG_DB + " BFL_RTLOG_CONFIG RC";
		List<RTLogConfigDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(RTLogConfigDTO.class));
		logger.info("Get Records from: BFL_RTLOG_CONFIG " +records);
		return records;
	}

	@Override
	public List<InvoiceVatItemDTO> getVatItemDetails(String invoiceNo, String itemCode) {
		logger.info("Reading records from InvoiceVatItem table.");
		String sql = "Select IVD.invoiceno,IVD.Itemcode,IVD.Discount,IVD.VatPer,IVD.VatAmt,IVD.VatCode,IVD.MRow,IVD.Loyalty from InvoiceVatItems IVD where invoiceno ='" + invoiceNo + "' and itemcode= '" + itemCode +"'";
		List<InvoiceVatItemDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(InvoiceVatItemDTO.class));
		logger.info("Invoice Vat records: " +records);
		return records;
	}


}
