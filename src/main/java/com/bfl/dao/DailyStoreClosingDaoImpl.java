package com.bfl.dao;

import java.io.IOException;
import java.util.List;

import com.bfl.dto.CloseShopDTO;
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
import com.bfl.dto.DailyStoreClosingDTO;

@Repository
public class DailyStoreClosingDaoImpl implements IDailyStoreClosingDao {

	private static final Logger logger = LoggerFactory.getLogger(DailyStoreClosingDaoImpl.class);

	@Autowired
	@Qualifier("appJdbcTemplate")
	private NamedParameterJdbcTemplate appJdbcTemplate;

	@Override
	public List<DailyStoreClosingDTO> getDailyStoreClosingDetails(String fromDate) {
//		String sql = "Select top 1 DC.TrnDate, DC.TrnTime, DC.Cashier, DC.CashAmt, DC.CardAmt, DC.UserId, DC.ShopName, DC.dataname, DC.CostCode, DC.EntryDate, DC.Expenses, DC.TimeFrom, DC.TimeTo, DC.BeamAmt, DC.sn from dailyClosing DC WHERE  NOT EXISTS (SELECT BFL_INVOICE_NO FROM BFL_TRANS_EXP ES WHERE ES.Exported = 'Y' and DC.sn = ES.BFL_INVOICE_NO and DC.CostCode = ES.BFL_STORE_ID) order by DC.TrnDate DESC";
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
//		String sql = "Select top 1 DC.TrnDate, DC.TrnTime, DC.Cashier, DC.CashAmt, DC.CardAmt, DC.UserId, DC.EntryDate, DC.Expenses, DC.TimeFrom, DC.TimeTo, DC.BeamAmt, DC.sn from dailyClosing DC WHERE  NOT EXISTS (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB +" BFL_TRANS_EXP ES WHERE ES.Exported = 'Y' and DC.sn = ES.BFL_INVOICE_NO) order by DC.TrnDate DESC";
		String sql = "Select DC.TrnDate, DC.TrnTime, DC.Cashier, DC.CashAmt, DC.CardAmt, DC.UserId, DC.EntryDate, DC.Expenses, "
					+ " DC.TimeFrom, DC.TimeTo, DC.BeamAmt, DC.sn, Dc.SystemName from dailyClosing DC WHERE "
					+ " NOT EXISTS (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB + "BFL_TRANS_EXP ES WHERE ES.Exported = 'Y' and DC.sn = ES.BFL_INVOICE_NO) "
					+ " and DC.EntryDate >= '" + fromDate + "' and DC.SystemName <> '' order by DC.TrnDate DESC";
//		String sql = "Select top 1 DC.TrnDate, DC.TrnTime, DC.Cashier, DC.CashAmt, DC.CardAmt, DC.UserId, DC.ShopName, DC.dataname, DC.CostCode, DC.EntryDate, DC.Expenses, DC.TimeFrom, DC.TimeTo, DC.BeamAmt, DC.sn from dailyClosing DC WHERE  NOT EXISTS (SELECT BFL_INVOICE_NO FROM BFL_TRANS_EXP ES WHERE ES.Exported = 'Y' and DC.sn = ES.BFL_INVOICE_NO and DC.CostCode = ES.BFL_STORE_ID) order by DC.TrnDate DESC";
		logger.info("Reading records from daily store closing table.");
		List<DailyStoreClosingDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DailyStoreClosingDTO.class));
		logger.info("dailyClosing records: " +records);
		return records;
	}

	@Override
	public List<CloseShopDTO> getStoreClosingInfo(String fromDate) {
		String TRAN_FLAG_DB = "";
		try {
			TRAN_FLAG_DB = ConfigProperties.getInstance().getConfigValue("TRAN_FLAG_DB");
			if(null != TRAN_FLAG_DB && !TRAN_FLAG_DB.isEmpty()) {
				TRAN_FLAG_DB = TRAN_FLAG_DB + "..";
			}
		} catch (IOException e) {
			logger.error("Error occured while getting the Tran Flag DB details " + e);
		}
		String sql = "Select CS.CloseDate,CS.CloseTime, CS.Code, CS.userid, CS.CompName, CS.TrnDateTime, CS.Remarks from closeshop "
				+ " CS WHERE NOT EXISTS (SELECT BFL_INVOICE_NO FROM " + TRAN_FLAG_DB + " BFL_TRANS_EXP ES WHERE "
				+ " CS.CloseDate = ES.STORE_CLOSE_DATE) and CS.CloseDate >= '" + fromDate + "'  ORDER BY closedate DESC";
		List<CloseShopDTO> records = appJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CloseShopDTO.class));
		logger.info("CLOSESHOP records: " + records);
		return records;
	}

	@Override
	public String getTotalTransactionsCount(String sourceOFDate) {
		logger.info("Getting total transactions  from sales,return and otherreturn tables.");
		String sql =  "select ((select count(InvoiceNo) from salesheader where InvoiceDate= '" + sourceOFDate + "') + "
				+ "(select count(SReturnNo) as c from  SReturnsHeader where SReturnDate='" + sourceOFDate + "') + "
				+ "(select count(SReturnNo) from  SreturnsOthheader where SReturnDate='" + sourceOFDate + "')) ";
		SqlParameterSource parameters = new MapSqlParameterSource();
		String count = appJdbcTemplate.queryForObject(sql, parameters, String.class);
		logger.info("Trans Count: "+ count);
		return count;
	}

}
