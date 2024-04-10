package com.bfl.service;

import java.io.IOException;

import com.bfl.alerts.EmailTemplateDTO;
import com.bfl.dto.ContainerDetailsDTO;
import com.bfl.gencode.apex.requests.BFLIntegrationDataRequest;
import com.bfl.gencode.apex.requests.BflEwalletLogRequest;
import com.bfl.gencode.apex.requests.GiftCardEntryLogRequest;
import com.bfl.gencode.apex.requests.InvoiceReprintHistoryRequest;
import com.bfl.gencode.apex.requests.ItemDeleteRequest;
import com.bfl.gencode.apex.requests.MissingBarcodeDetailsRequest;
import com.bfl.gencode.apex.requests.MissingBarcodeHeaderRequest;
import com.bfl.gencode.apex.requests.PriceDetailAgeingRequest;
import com.bfl.gencode.apex.requests.PriceHeadAgeingRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.gencode.merchhier.MerchHierarchyResponse;
import com.bfl.gencode.merchhier.BrandMaster.BrandMaster;
import com.bfl.gencode.merchhier.BrandMaster.BrandResponse;
import com.bfl.gencode.merchhier.Cont.Response.ContainerDetailsResponse;
import com.bfl.gencode.merchhier.Country.CountryRequest;
import com.bfl.gencode.merchhier.CreateBrandMaster.BrandMasterRequest;
import com.bfl.gencode.merchhier.CreateBrandMasterResponse.BrandMasterResponse;
import com.bfl.gencode.merchhier.GroupSummaryDetails.GroupSumPurOrderDetailReport;
import com.bfl.gencode.merchhier.ItemMaster.ItemMasterRequest;
import com.bfl.gencode.merchhier.ItemMasterLocRequest.ItemMasterLocRequest;
import com.bfl.gencode.merchhier.ItemMasterRequest.ItemMasterReq;
import com.bfl.gencode.merchhier.ItemMasterResponse.ItemMasterResponse;
import com.bfl.gencode.merchhier.OrderAttachment.OrderAttachment;
import com.bfl.gencode.merchhier.PartialShipment.PartialShipmentResponse;
import com.bfl.gencode.merchhier.PriceChange.PriceChangeResponse;
import com.bfl.gencode.merchhier.PurchaseOrderDetails.PurchaseOrderDetails;
import com.bfl.gencode.merchhier.PurchaseOrders.PurchaseOrdersResponse;
import com.bfl.gencode.merchhier.StoreResponse.StoresResponse;
import com.bfl.gencode.merchhier.Supplier.SupplierRequest;
import com.bfl.gencode.merchhier.category.CategoryResponse;
import com.bfl.gencode.merchhier.deps.DepartmentResponse;
import com.bfl.gencode.merchhier.emailNotification.EmailNotificationResponse;
import com.bfl.gencode.merchhier.subcategory.SubCategoryResponse;
import com.bfl.gencode.merchhier.upcbarcodes.response.UpcBarcodeResponse;
import com.bfl.gencode.resa.MissingResaItems.MissingResaItemsResponse;
import com.bfl.gencode.resa.otherShopReturnResponse.OtherShopResponse;
import com.bfl.gencode.resa.sales.SalesRequest;
import com.bfl.gencode.resa.sales.response.SalesResponse;
import com.bfl.request.FoundadtionRequest;

public interface IWebServiceHelper {


	public String getAuthToken() throws Exception;
	
	public String getAuthTokenForProd() throws Exception;

	public EmailNotificationResponse getEmailNotifications(FoundadtionRequest request) throws Exception;
	
	public EmailNotificationResponse getEmailNotificationDetails(FoundadtionRequest req) throws Exception;
	
	public PurchaseOrderDetails getPurchaseDetailsData(FoundadtionRequest req, String OrderId) throws Exception;
	
	public GroupSumPurOrderDetailReport getGroupSumPurOrderDetailReport(FoundadtionRequest req, String OrderId) throws Exception;
	
	public PartialShipmentResponse getPartialShipmentData(FoundadtionRequest req, String orderNo) throws Exception;
	
	public PartialShipmentResponse getShipmentData(FoundadtionRequest req, String orderNo) throws Exception;
	
	public String getOneOrderAttachment(FoundadtionRequest req, int docSeq) throws Exception;
	
	public OrderAttachment getOrderAttachment(FoundadtionRequest req, String orderNo) throws Exception;
	
	public void updateNotificationEmailStatus(FoundadtionRequest req, EmailTemplateDTO emailTemplate) throws Exception;
	
	public void callOrdersProcedure(FoundadtionRequest req) throws Exception;


	public MerchHierarchyResponse getMerchHierarchyForItemCreation(FoundadtionRequest req) throws Exception;
	
	public DepartmentResponse getMerchHierarchyForWMS(FoundadtionRequest req) throws Exception;

	public SalesResponse sendSalesData(FoundadtionRequest request, SalesRequest convertDtoToRequestParam) throws Exception;
	
	public SalesResponse sendSalesDataForStage(FoundadtionRequest req, SalesRequest convertDtoToRequestParam) throws Exception;

	public SubCategoryResponse getSubClass(FoundadtionRequest request) throws Exception;
	
	public StoresResponse getStoresData(FoundadtionRequest request) throws Exception;
	
	public CategoryResponse getClass(FoundadtionRequest req) throws Exception;


	public ItemMasterRequest getItemLevelData(FoundadtionRequest req, String itemId);


	public PriceChangeResponse getBFLPriceChangeResponse(FoundadtionRequest req) throws IOException;

	public PriceChangeResponse getClearancesWHData(FoundadtionRequest req) throws IOException;
	
	public PurchaseOrdersResponse getPurchaseOrdersData(FoundadtionRequest req) throws IOException;
	
	public MissingResaItemsResponse getMissingResaItems(FoundadtionRequest req) throws IOException;


	public MissingBarcodeDetailsResponse sendBarcodeHeaderDetails(FoundadtionRequest request, MissingBarcodeDetailsRequest missingBarcodeDetailsRequest) throws IOException;

	public MissingBarcodeDetailsResponse sendInvoiceReprintHistoryData(FoundadtionRequest request, InvoiceReprintHistoryRequest invoiceReprintHistoryRequest) throws IOException;

	public MissingBarcodeDetailsResponse sendItemDeleteDetails(FoundadtionRequest request, ItemDeleteRequest itemDeleteRequest) throws IOException;

	public MissingBarcodeDetailsResponse sendEWalletLogData(FoundadtionRequest request,	BflEwalletLogRequest eWalletLogRequest) throws IOException;

	public MissingBarcodeDetailsResponse sendGiftCardLogData(FoundadtionRequest request, GiftCardEntryLogRequest eWalletLogRequest) throws IOException;

	public MissingBarcodeDetailsResponse sendPriceDetailAgeingData(FoundadtionRequest request, PriceDetailAgeingRequest priceDetailAgeingDataRequest) throws IOException;

	public MissingBarcodeDetailsResponse sendPriceHeaderAgeingData(FoundadtionRequest request, PriceHeadAgeingRequest priceHeadAgeingDataRequest) throws IOException;

	public MissingBarcodeDetailsResponse sendMissingBarcodeHeaderDataRequest(FoundadtionRequest request, MissingBarcodeHeaderRequest missingBarcodeHeaderRequest) throws IOException;
	
	public MissingBarcodeDetailsResponse sendBFLIntegrationDataRequest(FoundadtionRequest request, BFLIntegrationDataRequest bflIntegrationDataRequest) throws IOException;

	public SalesResponse sendStoreOpenData(FoundadtionRequest request, SalesRequest salesRequest) throws IOException;


	public UpcBarcodeResponse getUpcBarcodesData(FoundadtionRequest request) throws Exception;
	
	public void updateContainerStatus(FoundadtionRequest request, ContainerDetailsDTO containerDetailsDTO) throws Exception;
	
	public UpcBarcodeResponse getItemAttributesDATADataResponse(FoundadtionRequest request) throws Exception;
	
	public UpcBarcodeResponse getBaseOrders(FoundadtionRequest request) throws Exception;
	
	public ContainerDetailsResponse getContainerDetailsResponse(FoundadtionRequest request) throws Exception;

	public UpcBarcodeResponse getShipmentUploadResponse(FoundadtionRequest request) throws IOException;


	public OtherShopResponse getOtherShopData(FoundadtionRequest req, String invoiceDONo) throws Exception;


	public BrandMaster getBrandData(FoundadtionRequest request) throws Exception;
	
	public BrandMasterResponse createBrandMaster(FoundadtionRequest request, BrandMasterRequest brandMasterRequest) throws Exception;
	
	public BrandResponse getBrandMaster(FoundadtionRequest request) throws Exception;
	
	public SupplierRequest getSupplierData(FoundadtionRequest request) throws Exception;
	
	public CountryRequest getCountryInfo(FoundadtionRequest req) throws Exception;
	
	public ItemMasterResponse createItemMaster(FoundadtionRequest req, ItemMasterReq itemMaster);
	

	public ItemMasterRequest checkItemMasterExist(FoundadtionRequest request, String itemCode) throws Exception;

	public ItemMasterResponse itemLocationRaning(FoundadtionRequest request, ItemMasterLocRequest buildItemMaster) throws Exception;
	
}