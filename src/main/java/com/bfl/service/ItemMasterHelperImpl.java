package com.bfl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfl.alerts.EmailService;
import com.bfl.dto.CountryDTO;
import com.bfl.dto.DepartmentDto;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.dto.MfcsSubclass;
import com.bfl.dto.SupplierDTO;
import com.bfl.dto.UdaDTO;
import com.bfl.gencode.merchhier.MerchHierarchyResponse;
import com.bfl.gencode.merchhier.BrandMaster.BrandMaster;
import com.bfl.gencode.merchhier.Country.CountryRequest;
import com.bfl.gencode.merchhier.CreateBrandMaster.BrandMasterRequest;
import com.bfl.gencode.merchhier.CreateBrandMasterResponse.BrandMasterResponse;
import com.bfl.gencode.merchhier.ItemMaster.Item;
import com.bfl.gencode.merchhier.ItemMaster.ItemMasterRequest;
import com.bfl.gencode.merchhier.ItemMaster.ItemUda;
import com.bfl.gencode.merchhier.ItemMaster.UdaFreeform;
import com.bfl.gencode.merchhier.ItemMaster.UdaLov;
import com.bfl.gencode.merchhier.ItemMasterLocRequest.ItemMasterLocRequest;
import com.bfl.gencode.merchhier.ItemMasterLocRequest.Location;
import com.bfl.gencode.merchhier.ItemMasterRequest.Assessment;
import com.bfl.gencode.merchhier.ItemMasterRequest.CountryOfManufacture;
import com.bfl.gencode.merchhier.ItemMasterRequest.CountryOfSourcing;
import com.bfl.gencode.merchhier.ItemMasterRequest.CustomFlexAttribute;
import com.bfl.gencode.merchhier.ItemMasterRequest.Dimension;
import com.bfl.gencode.merchhier.ItemMasterRequest.Ht;
import com.bfl.gencode.merchhier.ItemMasterRequest.Image;
import com.bfl.gencode.merchhier.ItemMasterRequest.ItemMasterReq;
import com.bfl.gencode.merchhier.ItemMasterRequest.RetailByZone;
import com.bfl.gencode.merchhier.ItemMasterRequest.Season;
import com.bfl.gencode.merchhier.ItemMasterRequest.Supplier;
import com.bfl.gencode.merchhier.ItemMasterRequest.Uda;
import com.bfl.gencode.merchhier.Supplier.SupplierRequest;
import com.bfl.request.FoundadtionRequest;

@Service
public class ItemMasterHelperImpl implements IItemMasterServiceHelper {
	
	@Autowired
	IWebServiceHelper helper;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IFoundationWMSDataService foundationDataService;
	
	Logger logger = LoggerFactory.getLogger(ItemMasterHelperImpl.class);
	
	public ItemMasterDTO getItemMaster(String fromDate, String toDate, String item, String token) {
		try {
			ItemMasterRequest response = getItemMasterResponse(fromDate, toDate, null, item, token);
			
			ItemMasterDTO itemMaster = null;
			
			if(response != null && response.getItems() != null && response.getItems().size() > 0) {
				itemMaster = parseResponse(response);
			}
			return itemMaster;
		} catch (Exception e) {
			return null;
		}
	}
	
	private ItemMasterRequest getItemMasterResponse(String startTime, String endTime, String url, String itemId, String token) {
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setToken(token);
		return helper.getItemLevelData(request, itemId);
	}
	
	private ItemMasterDTO parseResponse(ItemMasterRequest response) {
		ItemMasterDTO itemMasterDto = new ItemMasterDTO();
		if(response.getItems() != null) {
			for(Item item : response.getItems()) {
				itemMasterDto.setAction(item.getAction());
				itemMasterDto.setArabicItem(item.getItemDescriptionSecondary());
				itemMasterDto.setBatch(String.valueOf(0));
				itemMasterDto.setCatCode(String.valueOf(item.getClass_()));
				itemMasterDto.setCostcode(null);
				itemMasterDto.setCostPriceUpdated(null);
				itemMasterDto.setCostRate("0.01");
				itemMasterDto.setCreationDateTime(null);
				itemMasterDto.setDescription(item.getItemDescription());
				itemMasterDto.setGroupCode(String.valueOf(item.getDept()));
				itemMasterDto.setItemCode(item.getItem());
				itemMasterDto.setLocCode(null);
				if(null != item.getItemSupplier().get(0).getItemSupplierCountry().get(0).getMinimumOrderQuantity()) {
					itemMasterDto.setMinLevel(String.valueOf(item.getItemSupplier().get(0).getItemSupplierCountry().get(0).getMinimumOrderQuantity()));
				} else {
					itemMasterDto.setMinLevel(String.valueOf(0.00));
				}
				itemMasterDto.setOnOrder("0");
				itemMasterDto.setOpCostRate("0");
				itemMasterDto.setOpeningDate(item.getCreateDateTime());
				itemMasterDto.setOpeningStock("0");
				itemMasterDto.setRemarks(String.valueOf(item.getSubclass()));
				itemMasterDto.setReorderLevel("0");
				ItemUda itemUda = item.getItemUda();
				if(null != itemUda) {
					List<UdaLov> udaLov = itemUda.getUdaLov();
					List<UdaFreeform> udaFreeform = itemUda.getUdaFreeform();
					if(null != udaLov) {
						for(int i = 0; i < udaLov.size(); i++) {
							if("Season".equalsIgnoreCase(udaLov.get(i).getUdaDescription())) {
								//Season in BFL is used as one char value only....
								itemMasterDto.setItemType(String.valueOf(udaLov.get(i).getUdaValueDescription().charAt(0)));
								break;
							}
						}
					}
					if(null != udaFreeform) {
						for(int i =0; i < udaFreeform.size(); i++) {
							if("RRP".equalsIgnoreCase(udaFreeform.get(i).getUdaDescription())) {
								itemMasterDto.setRRP(udaFreeform.get(i).getUdaText());
							}
						}
					}
				}
				itemMasterDto.setShortName((item.getItemDescription() != null && item.getItemDescription().isEmpty()) ? item.getItemDescription().substring(0, 9) : "");
				itemMasterDto.setStockInHand("0");
				itemMasterDto.setToPrint("0");
				itemMasterDto.setTransfered(null);
				itemMasterDto.setUnitCode(item.getStandardUom());
				itemMasterDto.setUPC(item.getItem());
			}
		}
		return itemMasterDto;
	}

	
	public ItemMasterReq buildItemMaster(ItemMasterDTO itemMasterData) {
		logger.info("Inside buildItemMaster method");
		List<MfcsSubclass> deptClassSubClass = null;
		try {
//			deptClassSubClass = getDeptClassSubClass(itemMasterData.getGroupCode());
			deptClassSubClass = foundationDataService.getDeptClassSubclassData(itemMasterData.getGroupCode().toUpperCase());
		} catch (Exception e) {
			logger.error("Error occured while getting class subclass and department " + e.getMessage());
		}
		
		ItemMasterReq itemMasterRequest = new ItemMasterReq();
		
		List<com.bfl.gencode.merchhier.ItemMasterRequest.Item> items = new ArrayList<com.bfl.gencode.merchhier.ItemMasterRequest.Item>();
		
		com.bfl.gencode.merchhier.ItemMasterRequest.Item item = new com.bfl.gencode.merchhier.ItemMasterRequest.Item();
		
		item.setItem(itemMasterData.getItemCode().toUpperCase());
		item.setItemParent("");
		item.setItemGrandparent("");
		item.setItemNumberType("MANL");
		
		if(null != deptClassSubClass && deptClassSubClass.size() > 0) {
			item.setSubclass(Integer.parseInt(deptClassSubClass.get(0).getSubclass()));
			item.setClass_(Integer.parseInt(deptClassSubClass.get(0).get_class()));
			item.setDept(Integer.parseInt(deptClassSubClass.get(0).getDept()));
		}
		
		item.setItemLevel(1);
		item.setTranLevel(1);
		item.setStatus("A");
		item.setInventoryInd("Y");
		item.setItemDescription(itemMasterData.getDescription().toUpperCase().replace("|", ""));
		item.setShortDescription((null != itemMasterData.getShortName() && !itemMasterData.getShortName().trim().isEmpty()) ? itemMasterData.getShortName().toUpperCase().replace("|", "") : itemMasterData.getDescription().length() > 120 ? itemMasterData.getDescription().substring(0, 119).toUpperCase().replace("|", "") : itemMasterData.getDescription().toUpperCase().replace("|", ""));
		
		//this needs to check...
		item.setItemDescriptionSecondary(itemMasterData.getDescription().toUpperCase().replace("|", ""));
		
		item.setStandardUom("EA");
		item.setSellableInd("Y");
		item.setOrderableInd("Y");
		item.setSimplePackInd("N");
		item.setPackInd("N");
		item.setContainsInnerInd("N");
		item.setPackType("");
		item.setOrderAsType("");
		item.setDiff1("");
		item.setDiff2("");
		item.setDiff3("");
		item.setDiff4("");
		item.setStoreOrderMultiple("E");
		item.setForecastInd("N");
//		item.setUomConversionFactor();
//		item.setPackageSize();
//		item.setManufacturerRecommendedRetail(); // Need to check...
		item.setDataLoadingDestination("RMS");
		item.setHandlingTemperature("");
		item.setHandlingSensitivity("");
		item.setWasteType("");
//		item.setAverageWastePercentage();
		item.setCatchWeightInd("N");
		item.setOrderType("");
		item.setSaleType("");
		item.setCatchWeightUom("");
		item.setDepositItemType("");
		item.setContainerItem("");
		item.setPackageUom("");
		item.setFormatId("");
//		item.setPrefix();
		item.setItemTransformationInd("N");
		item.setProductClassification("");
		item.setMerchandiseInd("Y");
		item.setOriginalRetail(null);
		item.setRetailLabelType("");
		item.setRetailLabelValue(0);
		item.setDefaultWastePercentage(0);
		item.setItemServiceLevel("");
		item.setDepositInPricePerUom("I");
		item.setConstantDimensionInd("N");
		item.setGiftWrapInd("Y");
		item.setShipAloneInd("Y");
		item.setItemAggregateInd("N");
		item.setDiff1AggregateInd("N");
		item.setDiff2AggregateInd("N");
		item.setDiff3AggregateInd("N");
		item.setDiff4AggregateInd("N");
		item.setPerishableInd("N");
		item.setStorePackInventoryInd("N");
		item.setSohInquiryAtPackInd("N");
		item.setAipCaseType("");
		item.setCostZoneGroupId(1000);
		item.setItemSuppCountryLocHierarchyLevel("S");
		item.setItemZonePriceHierarchyLevel("S");
		item.setComments(itemMasterData.getRemarks());
		
		List<CustomFlexAttribute> listOfCustomFlexAttributes = getCustomFlexAttributes(itemMasterData);
		
//		List<UdaDTO> projectCodeAndBatchNo = foundationDataService.getProjectCodeAndBatchNo(itemMasterData.getItemCode());
		
		List<UdaDTO> projectCodeAndBatchNo = new ArrayList<UdaDTO>();
		
		List<Uda> listOfUDA = new ArrayList<Uda>();
		
		if(null != projectCodeAndBatchNo && projectCodeAndBatchNo.size() > 0) {
			if(null != projectCodeAndBatchNo.get(0).getProjectCode() && !projectCodeAndBatchNo.get(0).getProjectCode().trim().isEmpty()) {
				Uda uda = new Uda();
				uda.setDisplayType("FF");
				uda.setUdaId(1);
				uda.setUdaText(projectCodeAndBatchNo.get(0).getProjectCode());
				listOfUDA.add(uda);
				uda = new Uda();
				uda.setDisplayType("FF");
				uda.setUdaId(2);
				uda.setUdaText(projectCodeAndBatchNo.get(0).getBatchNo());
				listOfUDA.add(uda);
			}
		}
		
		List<UdaDTO> getContNoRefNo = foundationDataService.getContNoRefNo("SELECT ContNo ContNo, ContNo refNo, bol bolNo FROM USA..usaorgfile WHERE Itemcode = '" + itemMasterData.getItemCode() +  "'");
		
		if(null != getContNoRefNo && getContNoRefNo.size() > 0) {
			if(null != getContNoRefNo.get(0).getContNo() && !getContNoRefNo.get(0).getContNo().trim().isEmpty()) {
				Uda uda = new Uda();
				uda.setDisplayType("FF");
				uda.setUdaId(3);
				uda.setUdaText(getContNoRefNo.get(0).getContNo());
				listOfUDA.add(uda);
				
				uda = new Uda();
				uda.setDisplayType("FF");
				uda.setUdaId(4);
				uda.setUdaText(getContNoRefNo.get(0).getContNo());
				listOfUDA.add(uda);
			}
		} else {
			getContNoRefNo = foundationDataService.getContNoRefNo("SELECT ContNo ContNo, ContNo refNo, bol bolNo FROM USA..usaItemsall WHERE Itemcode = '" + itemMasterData.getItemCode() +  "'");
			if(null != getContNoRefNo && getContNoRefNo.size() > 0) {
				if(null != getContNoRefNo.get(0).getContNo() && !getContNoRefNo.get(0).getContNo().trim().isEmpty()) {
					Uda uda = new Uda();
					uda.setDisplayType("FF");
					uda.setUdaId(3);
					uda.setUdaText(getContNoRefNo.get(0).getContNo());
					listOfUDA.add(uda);
					
					uda = new Uda();
					uda.setDisplayType("FF");
					uda.setUdaId(4);
					uda.setUdaText(getContNoRefNo.get(0).getContNo());
					listOfUDA.add(uda);
				}
			} else {
				getContNoRefNo = foundationDataService.getContNoRefNo("SELECT bolNo FROM USA..upcbarcodes WHERE Itemcode = '" + itemMasterData.getItemCode() +  "'");
				if(null != getContNoRefNo && getContNoRefNo.size() > 0) {
					if(null != getContNoRefNo.get(0).getBolNo() && !getContNoRefNo.get(0).getBolNo().trim().isEmpty()) {
						Uda uda = new Uda();
						uda.setDisplayType("FF");
						uda.setUdaId(3);
						uda.setUdaText(getContNoRefNo.get(0).getBolNo());
						listOfUDA.add(uda);
						
						uda = new Uda();
						uda.setDisplayType("FF");
						uda.setUdaId(4);
						uda.setUdaText(getContNoRefNo.get(0).getBolNo());
						listOfUDA.add(uda);
					}
				}
			}
		}
		
		item.setUda(listOfUDA);
		
		item.setCustomFlexAttribute(listOfCustomFlexAttributes);
		
//		String brandName = foundationDataService.getBrandName(itemMasterData.getItemCode());
		
		if((null != itemMasterData.getVendor() && !itemMasterData.getVendor().trim().isEmpty()) || (null != itemMasterData.getBrandName() && !itemMasterData.getBrandName().trim().isEmpty())) {
			
			String brandName = (null != itemMasterData.getVendor() && !itemMasterData.getVendor().trim().isEmpty()) ? itemMasterData.getVendor() :  itemMasterData.getBrandName();
			
			if(brandName.contains("/")) {
				String [] aplitBrand = brandName.split("/");
				if(null != aplitBrand && aplitBrand.length > 0) {
					brandName = aplitBrand[0];
				}
			}
			
			if(brandName.contains("-")) {
				String [] aplitBrand = brandName.split("-");
				if(null != aplitBrand && aplitBrand.length > 0) {
					brandName = aplitBrand[0];
				}
			}
			
			try {
				getBrandMaster(brandName.trim().toUpperCase());
			} catch (Exception e) {
				logger.info("exception occured while checking or creating brand " + e);
			}
			item.setBrandName(brandName.trim().toUpperCase());
		} else {
			item.setBrandName("MIXED BRANDS");
		}
		
//		String supplierCode = itemMasterData.getSupplierCode();
		
		List<Supplier> listOfSupplier = new ArrayList<Supplier>();
		
		try {
//			SupplierRequest supplierRequest = getSupplierData(deptClassSubClass.get(0).getSuppCode());
			
			SupplierDTO supplierDTO = new SupplierDTO();
			
			supplierDTO.setCountryId(deptClassSubClass.get(0).getSuppCountry());
//			supplierDTO.setSupplier(1120);
			supplierDTO.setSupplier(Integer.parseInt(deptClassSubClass.get(0).getSupplier()));

			
			CountryDTO countryDTO = null;
			
			if(null != itemMasterData.getCountryOrigin() && !itemMasterData.getCountryOrigin().trim().isEmpty()) {
				CountryRequest countryInfo = getCountryInfo(itemMasterData.getCountryOrigin());
				for(com.bfl.gencode.merchhier.Country.Item countryData : countryInfo.getItems()) {
					countryDTO = new CountryDTO();
					countryDTO.setCountryId(countryData.getCountryId());
					countryDTO.setCountryDesc(countryData.getCountryDesc());
				}
			}
			
			listOfSupplier.add(setSupplierData(supplierDTO, countryDTO));
			
			item.setSupplier(listOfSupplier);
			
		} catch (Exception e) {
			logger.error("Error occured while fetching the data for Supplier : " + e);
		}
		
		List<RetailByZone> retailByZone = new ArrayList<RetailByZone>();
		
		String salesPrice = "";
		
		try {
			salesPrice = foundationDataService.getSalesPrice(itemMasterData.getItemCode());
		} catch (Exception e) {
			logger.error("Error while getting the price of Item code :- " + itemMasterData.getItemCode() + " and error message is :- " + e.getMessage());
			salesPrice = "0";
		}
		
		retailByZone.add(getRetailByZone(salesPrice));
		
		item.setRetailByZone(retailByZone);
		
		List<Season> season = new ArrayList<Season>();
		
		season.add(getSeasonId(itemMasterData.getItemType()));
		
		item.setSeason(season);
		
		List<Image> listOfImages = new ArrayList<Image>();
		
		if(null != itemMasterData.getItemImage() && !itemMasterData.getItemImage().trim().isEmpty()) {
			listOfImages.add(getImages(itemMasterData.getItemImage().trim(), itemMasterData.getItemCode().trim()));
		}
		
		item.setImage(listOfImages);
		
		items.add(item);
		
		itemMasterRequest.setItems(items);
		itemMasterRequest.setCollectionSize(1);
		
		return itemMasterRequest;
	}
	
	private List<CustomFlexAttribute> getCustomFlexAttributes(ItemMasterDTO itemMasterData) {
		
		List<CustomFlexAttribute> customFlexAttributes = new ArrayList<CustomFlexAttribute>();
		
		CustomFlexAttribute style = new CustomFlexAttribute();
		style.setName("STYLE");
		style.setValue(null != itemMasterData.getStyle() && !itemMasterData.getStyle().trim().isEmpty() ? itemMasterData.getStyle() : "");
		customFlexAttributes.add(style);
		
		CustomFlexAttribute color = new CustomFlexAttribute();
		color.setName("COLOR");
		color.setValue(null != itemMasterData.getColor() && !itemMasterData.getColor().trim().isEmpty() ? itemMasterData.getColor() : "NA");
		customFlexAttributes.add(color);
		
		CustomFlexAttribute itemSize = new CustomFlexAttribute();
		itemSize.setName("ITEM_SIZE");
		itemSize.setValue(null != itemMasterData.getSize() && !itemMasterData.getSize().trim().isEmpty() ? itemMasterData.getSize() : "NA");
		customFlexAttributes.add(itemSize);
		
		CustomFlexAttribute gender = new CustomFlexAttribute();
		gender.setName("GENDER");
		gender.setValue((null != itemMasterData.getGender() && !itemMasterData.getGender().trim().trim().isEmpty()) ? itemMasterData.getGender() : "N");
		customFlexAttributes.add(gender);
		
		CustomFlexAttribute rrpCurrency = new CustomFlexAttribute();
		rrpCurrency.setName("RRP_CURRENCY");
		rrpCurrency.setValue(null != itemMasterData.getRrpCurrency() && !itemMasterData.getRrpCurrency().trim().isEmpty() ? itemMasterData.getRrpCurrency() : "");
		customFlexAttributes.add(rrpCurrency);
		
		CustomFlexAttribute productMaterial = new CustomFlexAttribute();
		productMaterial.setName("PRODUCT_MATERIAL");
		productMaterial.setValue(null != itemMasterData.getProductMaterial() && !itemMasterData.getProductMaterial().trim().isEmpty() ? itemMasterData.getProductMaterial() : "");
		customFlexAttributes.add(productMaterial);
		
		String allocationSize = "";
		
		try {
			allocationSize = foundationDataService.getAllocationSize(itemMasterData.getItemCode());
		} catch (Exception e) {
			allocationSize = "";
		}
		
		CustomFlexAttribute allocation_size = new CustomFlexAttribute();
		allocation_size.setName("ALLOCATION_SIZE");
		allocation_size.setValue(null != allocationSize && !allocationSize.trim().isEmpty() ? itemMasterData.getAllocationSize() : "");
		customFlexAttributes.add(allocation_size);
		
		CustomFlexAttribute collection = new CustomFlexAttribute();
		collection.setName("COLLECTION");
		collection.setValue(null != itemMasterData.getCollection() && !itemMasterData.getCollection().trim().isEmpty() ? itemMasterData.getCollection() : "");
		customFlexAttributes.add(collection);
		
		CustomFlexAttribute char_Name = new CustomFlexAttribute();
		char_Name.setName("CHARACTER_NAME");
		char_Name.setValue(null != itemMasterData.getCharName() && !itemMasterData.getCharName().trim().isEmpty() ? itemMasterData.getCharName() : "");
		customFlexAttributes.add(char_Name);
		
		CustomFlexAttribute Bol_No = new CustomFlexAttribute();
		Bol_No.setName("BOL_NUMBER");
		Bol_No.setValue(null != itemMasterData.getBolNo() && !itemMasterData.getBolNo().trim().isEmpty() ? itemMasterData.getBolNo() : "");
		customFlexAttributes.add(Bol_No);
		
		CustomFlexAttribute RRP = new CustomFlexAttribute();
		RRP.setName("RRP");
		RRP.setValue(null != itemMasterData.getRRP() && !itemMasterData.getRRP().trim().isEmpty() ? itemMasterData.getRRP() : "");
		customFlexAttributes.add(RRP);
		
		return customFlexAttributes;
	}

	@Override
	public ItemMasterLocRequest buildItemRaningData(ItemMasterDTO itemMasterData, String loc, Double sellingUnitRetail, Supplier supplier) {
		
		ItemMasterLocRequest itemMasterLocRequest = new ItemMasterLocRequest();
		
		List<com.bfl.gencode.merchhier.ItemMasterLocRequest.Item> items = new ArrayList<>();
		
		com.bfl.gencode.merchhier.ItemMasterLocRequest.Item item = new com.bfl.gencode.merchhier.ItemMasterLocRequest.Item();
		
		item.setHierarchyLevel(loc);
		item.setItem(itemMasterData.getItemCode().toUpperCase());
		
		List<com.bfl.gencode.merchhier.ItemMasterLocRequest.Location> locations = new ArrayList<com.bfl.gencode.merchhier.ItemMasterLocRequest.Location>();
		
		if("W".equals(loc)) {
			Location location = getLocation(itemMasterData, sellingUnitRetail, supplier);
			locations.add(location);
		}
//		else if("S".equals(loc)) {
//			Location location = getLocation(itemMasterData);
//			locations.add(location);
//		}
		item.setLocations(locations);
		
		items.add(item);
		
		itemMasterLocRequest.setItems(items);
		itemMasterLocRequest.setCollectionSize(1);
		
		return itemMasterLocRequest;
	}
	
	private Location getLocation(ItemMasterDTO itemMasterData, Double sellingUnitRetail, Supplier supplier) {
		Location location = new Location();
		location.setCalculationBasis("");
		location.setCaptureTime("");
		location.setDailyWastePercent(0);
		location.setExternalUinInd("N");
		location.setHi(1);
		location.setLocalItemDescription(itemMasterData.getDescription());
		location.setLocalShortDescription(itemMasterData.getShortName());
		location.setHierarchyValue("11001");
		location.setPromotableInd("Y");
		location.setPurchaseRate("1");
//		location.setPrimarySupplier("1120");
		location.setPrimarySupplier(supplier.getSupplier());
		location.setPrimaryCountry(supplier.getCountryOfSourcing().get(0).getOriginCountry());
		location.setPurchaseType("");
		location.setReceiveAsType("");
		location.setRfidInd("N");
		location.setSourceMethod("S");
		location.setSourceWarehouse("");
		location.setStatus("A");
		location.setStoreOrderMultiple("E");
		location.setTaxableInd("Y");
		location.setTi(1);
		location.setUinLabel("");
		location.setUinType("");
		location.setUnitCost(String.valueOf(sellingUnitRetail));
		return location;
	}

	private Image getImages(String imageUrl, String imageName) {
		Image image = new Image();
		image.setImageAddress(imageUrl.trim());
		image.setDisplayPriority(1);
		image.setImageDescription(imageName.trim());
		image.setImageName(imageName.trim());
		image.setImageType("T");
		image.setPrimaryImageInd("Y");
		return image;
	}
	

	private RetailByZone getRetailByZone(String salesPrice) {
		RetailByZone retailByZone = new RetailByZone();
		retailByZone.setCountry("AE");
		retailByZone.setCurrencyCode("AED");
		retailByZone.setHierarchyId(1000);
		retailByZone.setMultiUnitRetail(null);
		retailByZone.setMultiUnits(null);
		retailByZone.setMultiUnitSellingUom(null);
		retailByZone.setSellingUnitRetail(Double.parseDouble(null != salesPrice && !salesPrice.isEmpty() ? salesPrice : "0"));
		retailByZone.setSellingUom("EA");
		return retailByZone;
	}
	

	
	private CountryRequest getCountryInfo(String countryDesc) throws Exception {
		String token = "";
		try {
			token = helper.getAuthToken();
		} catch (Exception e) {
			logger.error("Error occured while getting the token for Supplier " + e);
		}
		FoundadtionRequest request = new FoundadtionRequest();
//		request.setEndTime(endTime);
//		request.setStartTime(startTime);
		request.setUrl(null);
		request.setLimit(1000);
		request.setStatus("A");
		request.setToken(token);
		request.setCountry(countryDesc.toUpperCase());
		return helper.getCountryInfo(request);
	}
	
	private Supplier setSupplierData(SupplierDTO supplierDTO, CountryDTO countryDTO) {
		
		Supplier supplier = new Supplier();
		supplier.setSupplier(String.valueOf(supplierDTO.getSupplier()));
		supplier.setPrimarySupplierInd("Y");
		supplier.setVpn("");
		supplier.setSupplierLabel("");
//		supplier.setConsignmentRate();
		supplier.setSupplierDiscontinueDate("");
		supplier.setDirectShipInd("Y");
		supplier.setPalletName("PAL");
		supplier.setCaseName("CS");
		supplier.setInnerName("INR");
		supplier.setPrimaryCaseSize("");
		supplier.setSupplierDiff1("");
		supplier.setSupplierDiff2("");
		supplier.setSupplierDiff3("");
		supplier.setSupplierDiff4("");
//		supplier.setConcessionRate();
		supplier.setDefaultExpenseProfilesInd("Y");
		
		List<CountryOfSourcing> listOfCountryOfSourcing = new ArrayList<CountryOfSourcing>();
		
		CountryOfSourcing countryOfSourcing = new CountryOfSourcing();
		
		countryOfSourcing.setDefaultExpenseProfilesInd("Y");
		countryOfSourcing.setOriginCountry(supplierDTO.getCountryId());
		countryOfSourcing.setPrimaryCountryInd("Y");
		countryOfSourcing.setUnitCost(0.00);
		countryOfSourcing.setLeadTime(null);
		countryOfSourcing.setPickupLeadTime(null);
		countryOfSourcing.setMininumOrderQuantity(null);
		countryOfSourcing.setMaximumOrderQuantity(null);
		countryOfSourcing.setSupplierHierarchyLevel1("");
		countryOfSourcing.setSupplierHierarchyLevel2("");
		countryOfSourcing.setSupplierHierarchyLevel3("");
		countryOfSourcing.setDefaultUop("EA");
		countryOfSourcing.setSupplierPackSize(1);
		countryOfSourcing.setInnerPackSize(1);
		countryOfSourcing.setTi(1);
		countryOfSourcing.setHi(1);
		countryOfSourcing.setCostUom("EA");
		countryOfSourcing.setToleranceType("");
		countryOfSourcing.setMinimumTolerance(null);
		countryOfSourcing.setMaximumTolerance(null);
		countryOfSourcing.setSupplierHierarchyType1("");
		countryOfSourcing.setSupplierHierarchyType2("");
		countryOfSourcing.setSupplierHierarchyType3("");
		countryOfSourcing.setRoundLevel("C");
		countryOfSourcing.setRoundToInnerPercentage(50);
		countryOfSourcing.setRoundToCasePercentage(50);
		countryOfSourcing.setRoundToLayerPercentage(50);
		countryOfSourcing.setRoundToPalletPercentage(50);
		countryOfSourcing.setPackingMethod(null);
		countryOfSourcing.setPurchaseType("0");
		countryOfSourcing.setCalculationBasis("");
		countryOfSourcing.setPurchaseRate(null);
		
		List<Dimension> listOfDimension = new ArrayList<Dimension>();
		
		Dimension dimension = new Dimension();
		dimension.setPresentationMethod(null);
		dimension.setDimensionObject("EA");
		dimension.setTareWeight(null);
		dimension.setTareType(null);
		dimension.setLwhUom(null);
		dimension.setLength(null);
		dimension.setWidth(null);
		dimension.setHeight(null);
		dimension.setLiquidVolume(null);
		dimension.setLiquidVolumeUom(null);
		dimension.setStatisticalCase(null);
		dimension.setWeightUom(null);
		dimension.setWeight(null);
		dimension.setNetWeight(null);
		
		listOfDimension.add(dimension);

		
		countryOfSourcing.setDimension(listOfDimension);
		listOfCountryOfSourcing.add(countryOfSourcing);
		
		List<CountryOfManufacture> listOfCountryOfManufacture = null;
		
		if(null != countryDTO && null != countryDTO.getCountryId()) {
			listOfCountryOfManufacture = new ArrayList<CountryOfManufacture>();
			CountryOfManufacture countryOfManufacture = new CountryOfManufacture();
			
			countryOfManufacture.setManufacturerCountry(countryDTO.getCountryId());
			countryOfManufacture.setPrimaryManufacturerCountryInd("Y");
			
			listOfCountryOfManufacture.add(countryOfManufacture);
		}
		
		supplier.setCountryOfManufacture(listOfCountryOfManufacture);
		
		supplier.setCountryOfSourcing(listOfCountryOfSourcing);
		
		return supplier;
	}
	
	private void getBrandMaster(String brand) {
		BrandMaster response = null;
//		List<BrandMasterDTO> brands = new ArrayList<>();
		FoundadtionRequest request = new FoundadtionRequest();
		try {
			String token = helper.getAuthTokenForProd();
			request.setUrl(null);
			request.setLimit(1000);
			request.setStatus("A");
			request.setToken(token);
			request.setBrand(brand);
			do {
				if(response == null)
					response = getBrandData(request);
				else {
					List<com.bfl.gencode.merchhier.BrandMaster.Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
					if(links != null && links.size() > 0)
						response = getBrandData(request);
					else
						break;
				}
				//Parse webservice response
				if(null != response && null != response.getItems() && response.getItems().size() > 0) {
//					brands.addAll(parseBrandMasterResponse(response));
				} else {
					BrandMasterRequest createBrandMaster = createBrandMasterRequest(brand);
					BrandMasterResponse brandMasterResponse = createBrandMaster(request, createBrandMaster);
					logger.info(brandMasterResponse.getStatus());
				}
			} while(null != response && response.getHasMore());
		} catch (Exception e) {
			BrandMasterRequest createBrandMaster = createBrandMasterRequest(brand);
			try {
				BrandMasterResponse brandMasterResponse = createBrandMaster(request, createBrandMaster);
				logger.info(brandMasterResponse.getStatus());
			} catch (Exception e1) {
				logger.error("Error while creating brand Master " + e.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	private BrandMasterRequest createBrandMasterRequest(String brand) {
		BrandMasterRequest brandMasterRequest = new BrandMasterRequest();
		brandMasterRequest.setCollectionSize(1);
		List<com.bfl.gencode.merchhier.CreateBrandMaster.Item> items = new ArrayList<com.bfl.gencode.merchhier.CreateBrandMaster.Item>();
		com.bfl.gencode.merchhier.CreateBrandMaster.Item item = new com.bfl.gencode.merchhier.CreateBrandMaster.Item();
		item.setBrandDescription(brand);
		item.setBrandName(brand);
		item.setDeleteInd("");
		items.add(item);
		brandMasterRequest.setItems(items);
		
		return brandMasterRequest;
	}
	
	@SuppressWarnings("unused")
	private List<DepartmentDto> getDeptClassSubClass(String groupCode) throws Exception {
		
		MerchHierarchyResponse response = null;
		
		List<DepartmentDto> departments = new ArrayList<>();
		
		do {
			if(response == null)
				response = getDepartments(null, groupCode);
			else {
				List<com.bfl.gencode.merchhier.Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
				if(links != null && links.size() > 0)
					response = getDepartments(null, groupCode);
				else
					break;
			}
			//Parse webservice response
			if(null != response && null != response.getItems() && response.getItems().size() > 0) {
				departments.addAll(parseResponseMerchHierarchy(response));
			}
		} while(null != response && response.getHasMore());
		
		return departments;
	}
	
	private List<DepartmentDto> parseResponseMerchHierarchy(MerchHierarchyResponse response) {
		List<DepartmentDto> deptDtos = new ArrayList<DepartmentDto>();
		for(com.bfl.gencode.merchhier.Item item : response.getItems()) {
			DepartmentDto deptDto = new DepartmentDto();
			deptDto.set_class(item.get_class());
			deptDto.setGroup_Code(item.getGroup());
			deptDto.setSubclass(item.getSubclass());
			deptDto.setDepartment(item.getDepartment());
			deptDto.setSubclassId(item.getSubclassId());
			deptDtos.add(deptDto);
		}
		return deptDtos;
	}

	private Season getSeasonId(String seasontype) {
		Season season = new Season();
		int seasonId = 2;
		if("S".equals(seasontype)) {
			seasonId = 2;
		} else if("W".equals(seasontype)) {
			seasonId = 3;
		} else if("H".equals(seasontype)) {
			seasonId = 4;
		} 
		season.setSeasonId(seasonId);
		season.setPhaseId(1);
		return season;
	}
	
	private MerchHierarchyResponse getDepartments(String url, String groupCode) throws Exception {
		String token = helper.getAuthToken();
		FoundadtionRequest request = new FoundadtionRequest();
//		request.setEndTime(endTime);
//		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setStatus("A");
		request.setToken(token);
		request.setGroup(groupCode);
		return helper.getMerchHierarchyForItemCreation(request);
	}
	
	private BrandMaster getBrandData(FoundadtionRequest request) throws Exception {
		return helper.getBrandData(request);
	}
	
	private BrandMasterResponse createBrandMaster(FoundadtionRequest request, BrandMasterRequest brandMasterRequest) throws Exception {

		return helper.createBrandMaster(request, brandMasterRequest);
	}
	
	public ItemMasterReq buildItemMasterForProd(ItemMasterDTO itemMasterData) {
		
		logger.info("Inside buildItemMaster method");

		
		ItemMasterReq itemMasterRequest = new ItemMasterReq();
		
		List<com.bfl.gencode.merchhier.ItemMasterRequest.Item> items = new ArrayList<com.bfl.gencode.merchhier.ItemMasterRequest.Item>();
		
		com.bfl.gencode.merchhier.ItemMasterRequest.Item item = new com.bfl.gencode.merchhier.ItemMasterRequest.Item();
		
		item.setItem(itemMasterData.getItemCode());
		item.setItemParent("");
		item.setItemGrandparent("");
		item.setItemNumberType("MANL");
		
//		if(null != deptClassSubClass && deptClassSubClass.size() > 0) {
		item.setSubclass(Integer.parseInt(itemMasterData.getSubclass()));
		item.setClass_(Integer.parseInt(itemMasterData.getClas()));
		item.setDept(Integer.parseInt(itemMasterData.getDept()));
		item.setItemLevel(1);
		item.setTranLevel(1);
		item.setStatus("A");
		item.setInventoryInd("Y");
		String itemDesc = itemMasterData.getDescription().toUpperCase();
		
		itemDesc = itemDesc.contains("|") ? itemDesc.replace("|", " ") : itemDesc;
		String short_desc = itemMasterData.getDescription();
        short_desc = short_desc.contains("|") ? short_desc.replace("|", " ") : short_desc;
		
		item.setItemDescription(itemDesc);
		item.setShortDescription((null != short_desc && !short_desc.trim().isEmpty()) ? short_desc.toUpperCase() : short_desc.length() > 120 ? short_desc.substring(0, 119).toUpperCase() : short_desc.toUpperCase());
		
		//this needs to check...
		item.setItemDescriptionSecondary(itemMasterData.getSUBCATEGORY());
		
		item.setStandardUom("EA");
		item.setSellableInd("Y");
		item.setOrderableInd("Y");
		item.setSimplePackInd("N");
		item.setPackInd("N");
		item.setContainsInnerInd("N");
		item.setPackType("");
		item.setOrderAsType("");
		item.setDiff1("");
		item.setDiff2("");
		item.setDiff3("");
		item.setDiff4("");
		item.setStoreOrderMultiple("E");
		item.setForecastInd("N");
//		item.setUomConversionFactor();
//		item.setPackageSize();
		item.setManufacturerRecommendedRetail(Double.parseDouble(itemMasterData.getAEDRRP()));
		item.setDataLoadingDestination("RMS");
		item.setHandlingTemperature("");
		item.setHandlingSensitivity("");
		item.setWasteType("");
//		item.setAverageWastePercentage();
		item.setCatchWeightInd("N");
		item.setOrderType("");
		item.setSaleType("");
		item.setCatchWeightUom("");
		item.setDepositItemType("");
		item.setContainerItem("");
		item.setPackageUom("");
		item.setFormatId("");
//		item.setPrefix();
		item.setItemTransformationInd("N");
		item.setProductClassification("");
		item.setMerchandiseInd("Y");
		item.setOriginalRetail(null);
		item.setRetailLabelType("");
		item.setRetailLabelValue(0);
		item.setDefaultWastePercentage(0);
		item.setItemServiceLevel("");
		item.setDepositInPricePerUom("I");
		item.setConstantDimensionInd("N");
		item.setGiftWrapInd("Y");
		item.setShipAloneInd("Y");
		item.setItemAggregateInd("N");
		item.setDiff1AggregateInd("N");
		item.setDiff2AggregateInd("N");
		item.setDiff3AggregateInd("N");
		item.setDiff4AggregateInd("N");
		item.setPerishableInd("N");
		item.setStorePackInventoryInd("N");
		item.setSohInquiryAtPackInd("N");
		item.setAipCaseType("");
		item.setCostZoneGroupId(1000);
		item.setItemSuppCountryLocHierarchyLevel("S");
		item.setItemZonePriceHierarchyLevel("S");
		item.setComments(itemMasterData.getRemarks());
		
		List<CustomFlexAttribute> listOfCustomFlexAttributes = getCustomFlexAttributesForProd(itemMasterData);
		
		try {

			List<Uda> listOfUDA = new ArrayList<Uda>();
			
			Uda uda = new Uda();
			
			if(null != itemMasterData.getProjectCode() && !itemMasterData.getProjectCode().trim().isEmpty()) {
				uda.setDisplayType("FF");
				uda.setUdaId(1);
				uda.setUdaText(itemMasterData.getProjectCode());
				listOfUDA.add(uda);
			}
			
			if(null != itemMasterData.getProjectCode() && !itemMasterData.getProjectCode().trim().isEmpty()) {
				uda = new Uda();
				uda.setDisplayType("FF");
				uda.setUdaId(3);
				uda.setUdaText(itemMasterData.getRefNo());
				listOfUDA.add(uda);
			}
			
			if(null != itemMasterData.getProjectCode() && !itemMasterData.getProjectCode().trim().isEmpty()) {
				uda = new Uda();
				uda.setDisplayType("FF");
				uda.setUdaId(4);
				uda.setUdaText(itemMasterData.getRefNo());
				listOfUDA.add(uda);
			}
			
			item.setUda(listOfUDA);
			
			item.setCustomFlexAttribute(listOfCustomFlexAttributes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String brandName = itemMasterData.getBrandName();
		
		if("#N/A".equals(brandName)) {
			item.setBrandName(itemMasterData.getGroup_brand());
		} else {
			item.setBrandName(brandName);
		}
		
//		String supplierCode = itemMasterData.getSupplierCode();
		
		List<Supplier> listOfSupplier = new ArrayList<Supplier>();
		
		try {

			SupplierDTO supplierDTO = new SupplierDTO();
			supplierDTO.setCountryId(itemMasterData.getCoo());
			supplierDTO.setSupplier((int) Double.parseDouble(itemMasterData.getSupplierCode()));
			
			CountryDTO countryDTO = new CountryDTO();
			countryDTO.setCountryId(itemMasterData.getCoo());
			
			listOfSupplier.add(setSupplierDataForProd(supplierDTO, countryDTO));
			
			item.setSupplier(listOfSupplier);
			
		} catch (Exception e) {
			logger.error("Error occured while fetching the data for Supplier : " + e);
		}
		
		List<RetailByZone> retailByZone = new ArrayList<RetailByZone>();

		retailByZone.add(getRetailByZone(itemMasterData.getSalesRate()));
		
		item.setRetailByZone(retailByZone);
		
		List<Season> season = new ArrayList<Season>();
		
		season.add(getSeasonId(itemMasterData.getItemType()));
		
		item.setSeason(season);
		
		List<Image> listOfImages = new ArrayList<Image>();
		
		if(null != itemMasterData.getItemImage() && !itemMasterData.getItemImage().trim().isEmpty()) {
			listOfImages.add(getImages(itemMasterData.getItemImage().trim(), itemMasterData.getItemCode()));
		}
		
		item.setImage(listOfImages);

		
		items.add(item);
		
		itemMasterRequest.setItems(items);
		itemMasterRequest.setCollectionSize(1);
		
		return itemMasterRequest;
	}
	
	private Supplier setSupplierDataForProd(SupplierDTO supplierDTO, CountryDTO countryDTO) {
		
		Supplier supplier = new Supplier();
		supplier.setSupplier(String.valueOf(supplierDTO.getSupplier()));
		supplier.setPrimarySupplierInd("Y");
		supplier.setVpn("");
		supplier.setSupplierLabel("");
		supplier.setSupplierDiscontinueDate("");
		supplier.setDirectShipInd("Y");
		supplier.setPalletName("PAL");
		supplier.setCaseName("CS");
		supplier.setInnerName("INR");
		supplier.setPrimaryCaseSize("");
		supplier.setSupplierDiff1("");
		supplier.setSupplierDiff2("");
		supplier.setSupplierDiff3("");
		supplier.setSupplierDiff4("");
		
		supplier.setDefaultExpenseProfilesInd("Y");
		
		List<CountryOfSourcing> listOfCountryOfSourcing = new ArrayList<CountryOfSourcing>();
		
		CountryOfSourcing countryOfSourcing = new CountryOfSourcing();
		
		countryOfSourcing.setDefaultExpenseProfilesInd("Y");
		countryOfSourcing.setOriginCountry(supplierDTO.getCountryId());
		countryOfSourcing.setPrimaryCountryInd("Y");
		countryOfSourcing.setUnitCost(0.00);
		countryOfSourcing.setLeadTime(null);
		countryOfSourcing.setPickupLeadTime(null);
		countryOfSourcing.setMininumOrderQuantity(null);
		countryOfSourcing.setMaximumOrderQuantity(null);
		countryOfSourcing.setSupplierHierarchyLevel1("");
		countryOfSourcing.setSupplierHierarchyLevel2("");
		countryOfSourcing.setSupplierHierarchyLevel3("");
		countryOfSourcing.setDefaultUop("EA");
		countryOfSourcing.setSupplierPackSize(1);
		countryOfSourcing.setInnerPackSize(1);
		countryOfSourcing.setTi(1);
		countryOfSourcing.setHi(1);
		countryOfSourcing.setCostUom("EA");
		countryOfSourcing.setToleranceType("");
		countryOfSourcing.setMinimumTolerance(null);
		countryOfSourcing.setMaximumTolerance(null);
		countryOfSourcing.setSupplierHierarchyType1("");
		countryOfSourcing.setSupplierHierarchyType2("");
		countryOfSourcing.setSupplierHierarchyType3("");
		countryOfSourcing.setRoundLevel("C");
		countryOfSourcing.setRoundToInnerPercentage(50);
		countryOfSourcing.setRoundToCasePercentage(50);
		countryOfSourcing.setRoundToLayerPercentage(50);
		countryOfSourcing.setRoundToPalletPercentage(50);
		countryOfSourcing.setPackingMethod(null);
		countryOfSourcing.setPurchaseType("0");
		countryOfSourcing.setCalculationBasis("");
		countryOfSourcing.setPurchaseRate(null);
		
		List<Dimension> listOfDimension = new ArrayList<Dimension>();
		
		Dimension dimension = new Dimension();
		dimension.setPresentationMethod(null);
		dimension.setDimensionObject("EA");
		dimension.setTareWeight(null);
		dimension.setTareType(null);
		dimension.setLwhUom(null);
		dimension.setLength(null);
		dimension.setWidth(null);
		dimension.setHeight(null);
		dimension.setLiquidVolume(null);
		dimension.setLiquidVolumeUom(null);
		dimension.setStatisticalCase(null);
		dimension.setWeightUom(null);
		dimension.setWeight(null);
		dimension.setNetWeight(null);
		
		listOfDimension.add(dimension);
		
		countryOfSourcing.setDimension(listOfDimension);
		
		listOfCountryOfSourcing.add(countryOfSourcing);
		
		List<CountryOfManufacture> listOfCountryOfManufacture = null;
		
		if(null != countryDTO && null != countryDTO.getCountryId()) {
			listOfCountryOfManufacture = new ArrayList<CountryOfManufacture>();
			CountryOfManufacture countryOfManufacture = new CountryOfManufacture();
			
			countryOfManufacture.setManufacturerCountry(countryDTO.getCountryId());
			countryOfManufacture.setPrimaryManufacturerCountryInd("Y");
			
			listOfCountryOfManufacture.add(countryOfManufacture);
		}
		
		supplier.setCountryOfManufacture(listOfCountryOfManufacture);
		
		supplier.setCountryOfSourcing(listOfCountryOfSourcing);
		
		return supplier;
	}
	
	private List<CustomFlexAttribute> getCustomFlexAttributesForProd(ItemMasterDTO itemMasterData) {
		
		List<CustomFlexAttribute> customFlexAttributes = new ArrayList<CustomFlexAttribute>();
		
		CustomFlexAttribute style = new CustomFlexAttribute();
		style.setName("STYLE");
		style.setValue(null != itemMasterData.getStyle() && !itemMasterData.getStyle().isEmpty() ? itemMasterData.getStyle() : "");
		customFlexAttributes.add(style);
		
		CustomFlexAttribute color = new CustomFlexAttribute();
		color.setName("COLOR");
		color.setValue(null != itemMasterData.getColor() && !itemMasterData.getColor().isEmpty() ? itemMasterData.getColor() : "");
		customFlexAttributes.add(color);
		
		CustomFlexAttribute itemSize = new CustomFlexAttribute();
		itemSize.setName("ITEM_SIZE");
		itemSize.setValue(null != itemMasterData.getSize() && !itemMasterData.getSize().isEmpty() ? itemMasterData.getSize() : "");
		customFlexAttributes.add(itemSize);
		
		CustomFlexAttribute gender = new CustomFlexAttribute();
		gender.setName("GENDER");
		gender.setValue(null != itemMasterData.getGender() && !itemMasterData.getGender().isEmpty() ? itemMasterData.getGender() : "N");
		customFlexAttributes.add(gender);
		
		CustomFlexAttribute rrpCurrency = new CustomFlexAttribute();
		rrpCurrency.setName("RRP_CURRENCY");
		rrpCurrency.setValue(null != itemMasterData.getRrpCurrency() && !itemMasterData.getRrpCurrency().isEmpty() ? itemMasterData.getRrpCurrency() : "");
		customFlexAttributes.add(rrpCurrency);
		
		CustomFlexAttribute productMaterial = new CustomFlexAttribute();
		productMaterial.setName("PRODUCT_MATERIAL");
		productMaterial.setValue(null != itemMasterData.getProductMaterial() && !itemMasterData.getProductMaterial().isEmpty() ? itemMasterData.getProductMaterial() : "");
		customFlexAttributes.add(productMaterial);

		
		CustomFlexAttribute allocation_size = new CustomFlexAttribute();
		allocation_size.setName("ALLOCATION_SIZE");
		allocation_size.setValue(null != itemMasterData.getAllocationSize() && !itemMasterData.getAllocationSize().isEmpty() ? itemMasterData.getAllocationSize() : "");
		customFlexAttributes.add(allocation_size);
		
		CustomFlexAttribute collection = new CustomFlexAttribute();
		collection.setName("COLLECTION");
		collection.setValue(null != itemMasterData.getCollection() && !itemMasterData.getCollection().isEmpty() ? itemMasterData.getCollection() : "");
		customFlexAttributes.add(collection);
		
		CustomFlexAttribute char_Name = new CustomFlexAttribute();
		char_Name.setName("CHARACTER_NAME");
		char_Name.setValue(null != itemMasterData.getCharName() && !itemMasterData.getCharName().isEmpty() ? itemMasterData.getCharName() : "");
		customFlexAttributes.add(char_Name);
		
		CustomFlexAttribute Bol_No = new CustomFlexAttribute();
		Bol_No.setName("BOL_NUMBER");
		Bol_No.setValue(null != itemMasterData.getBolNo() && !itemMasterData.getBolNo().isEmpty() ? itemMasterData.getBolNo() : "");
		customFlexAttributes.add(Bol_No);
		
		CustomFlexAttribute RRP = new CustomFlexAttribute();
		RRP.setName("RRP");
		RRP.setValue(null != itemMasterData.getRRP() && !itemMasterData.getRRP().trim().isEmpty() ? itemMasterData.getRRP() : "");
		customFlexAttributes.add(RRP);
		
		return customFlexAttributes;
	}
	
}