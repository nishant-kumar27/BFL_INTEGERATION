package com.bfl.service;

import com.bfl.dto.ItemMasterDTO;
import com.bfl.gencode.merchhier.ItemMasterLocRequest.ItemMasterLocRequest;
import com.bfl.gencode.merchhier.ItemMasterRequest.ItemMasterReq;
import com.bfl.gencode.merchhier.ItemMasterRequest.Supplier;

public interface IItemMasterServiceHelper {
	
	public ItemMasterDTO getItemMaster(String fromDate, String toDate, String Item, String token);
	
	public ItemMasterReq buildItemMaster(ItemMasterDTO itemMasterData);
	
	public ItemMasterLocRequest buildItemRaningData(ItemMasterDTO itemMasterData, String location, Double sellingUnitRetail, Supplier supplier);
	
}