package com.bfl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bfl.ConfigProperties;
import com.bfl.alerts.EmailTemplateDTO;
import com.bfl.api.logging.LoggingHelper;
import com.bfl.dto.ContainerDetailsDTO;
import com.bfl.dto.ItemMasterConfigDTO;
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
import com.bfl.gencode.auth.AuthResponse;
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

@Service
public class WebServiceHelperImpl implements IWebServiceHelper {
	
	Logger logger = LoggerFactory.getLogger(WebServiceHelperImpl.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	IFoundationWMSDataService foundationDataService;


	@Override
	public EmailNotificationResponse getEmailNotifications(FoundadtionRequest req) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.notificationqueue.url").concat("?status=" + req.getStatus());
			url = url.concat("&limit="+req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		EmailNotificationResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, EmailNotificationResponse.class).getBody();
//		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public EmailNotificationResponse getEmailNotificationDetails(FoundadtionRequest req) throws Exception {
		String url = req.getUrl();
		if(url == null) {
//			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.notificationdetails.url").concat("?status=" + req.getStatus());
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.notificationdetails.url");
			url = url.concat("?&limit="+req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
//		LoggingHelper.logRequest(entity);
		EmailNotificationResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, EmailNotificationResponse.class).getBody();
//		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public PurchaseOrderDetails getPurchaseDetailsData(FoundadtionRequest req, String OrderId) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.purchaseOrderDetails");
			url = url.concat("?&PM_order_no=" + OrderId);
			url = url.concat("&limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		PurchaseOrderDetails response = restTemplate.exchange(url, HttpMethod.GET, entity, PurchaseOrderDetails.class).getBody();
		return response;
	}
	
	public GroupSumPurOrderDetailReport getGroupSumPurOrderDetailReport(FoundadtionRequest req, String OrderId) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.groupSummaryReport");
			url = url.concat("?&PM_order_no=" + OrderId);
			url = url.concat("&limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
//		LoggingHelper.logRequest(entity);
		GroupSumPurOrderDetailReport response = restTemplate.exchange(url, HttpMethod.GET, entity, GroupSumPurOrderDetailReport.class).getBody();
//		LoggingHelper.logResponse(response);
		return response;
	}
	
	public PartialShipmentResponse getPartialShipmentData(FoundadtionRequest req, String orderNo) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.partialShipment.url");
			url = url.concat("?&PM_order_no=" + orderNo);
			url = url.concat("&limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
//		LoggingHelper.logRequest(entity);
		PartialShipmentResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, PartialShipmentResponse.class).getBody();
//		LoggingHelper.logResponse(response);
		return response;
	}
	
	public PartialShipmentResponse getShipmentData(FoundadtionRequest req, String orderNo) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.shipmentData.url");
			url = url.concat("?&limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		PartialShipmentResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, PartialShipmentResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	public String getOneOrderAttachment(FoundadtionRequest req, int docSeq) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.attachment.url").concat("?&docSeq=" + docSeq);
//			url = url.concat("&limit="+req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
//		LoggingHelper.logRequest(entity);
		String body = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
//		LoggingHelper.logResponse(body);
		return body;
	}
	
	@Override
	public OrderAttachment getOrderAttachment(FoundadtionRequest req, String orderNo) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.orderAttachment.url").concat("?&orderNo=" + orderNo);
			url = url.concat("&limit="+req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
//		String body = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
//		System.out.println(body);
		OrderAttachment response = restTemplate.exchange(url, HttpMethod.GET, entity, OrderAttachment.class).getBody();
//		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public void callOrdersProcedure(FoundadtionRequest req) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.call.order.procedure");
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		restTemplate.exchange(url, HttpMethod.GET, entity, EmailNotificationResponse.class);
	}
	


	@Override
	public void updateNotificationEmailStatus(FoundadtionRequest req, EmailTemplateDTO emailTemplate) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.updateNotificationStatus");
		}
		HttpEntity<EmailTemplateDTO> entity = new HttpEntity<EmailTemplateDTO>(emailTemplate, getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		restTemplate.exchange(url, HttpMethod.PUT, entity, EmailNotificationResponse.class);
	}

	
	@Override
	public MerchHierarchyResponse getMerchHierarchyForItemCreation(FoundadtionRequest req) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getMerchHierarchy.url").concat("?&groupCode=" + req.getGroup() + "&");
			url = url.concat("limit="+req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		MerchHierarchyResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, MerchHierarchyResponse.class).getBody();
		return response;
	}
	
	@Override
	public BrandMaster getBrandData(FoundadtionRequest request) throws Exception {
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.brandMaster.url").concat("?&brandName=" + request.getBrand() + "&");
			url = url.concat("limit="+request.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(request.getToken()));
		BrandMaster response = restTemplate.exchange(url, HttpMethod.GET, entity, BrandMaster.class).getBody();
		return response;
	}
	
	@Override
	public BrandMasterResponse createBrandMaster(FoundadtionRequest request, BrandMasterRequest brandMasterRequest) throws Exception {
		ResponseEntity<BrandMasterResponse> response = null;
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.createBrandMaster.url");
		}
//		List<BrandMasterRequest> brandRequest = new ArrayList<>();
//		brandRequest.add(brandMasterRequest);
		HttpEntity<BrandMasterRequest> entity = new HttpEntity<BrandMasterRequest>(brandMasterRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		response = restTemplate.exchange(url, HttpMethod.PUT, entity, BrandMasterResponse.class);
		LoggingHelper.logResponse(response);
		if(null != response && response.getStatusCode().is2xxSuccessful())
			return response.getBody();
		else
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
//		return null;
	}
	
	@Override
	public BrandResponse getBrandMaster(FoundadtionRequest request) throws Exception {
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.brands.url").concat("?");
			if(StringUtils.isNotEmpty(request.getStartTime()))
				url = url.concat("&fromdate=" + request.getStartTime()).concat("&");
			if(StringUtils.isNotEmpty(request.getEndTime()))
				url = url.concat("todate=" + request.getEndTime()).concat("&");
			url = url.concat("limit=" + request.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(request.getToken()));
		BrandResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, BrandResponse.class).getBody();
		return response;
	}
	
	@Override
	public SupplierRequest getSupplierData(FoundadtionRequest req) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getSupplierInfo.url").concat("?&sup_code="+req.getSupplierCode() + "&");
			url = url.concat("limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		SupplierRequest response = restTemplate.exchange(url, HttpMethod.GET, entity, SupplierRequest.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public CountryRequest getCountryInfo(FoundadtionRequest req) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getCountryInfo.url").concat("?&COUNTRY_DESC="+req.getCountry() + "&");
			url = url.concat("limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		CountryRequest response = restTemplate.exchange(url, HttpMethod.GET, entity, CountryRequest.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public ItemMasterResponse createItemMaster(FoundadtionRequest req, ItemMasterReq itemMaster) {
		try {
			ResponseEntity<ItemMasterResponse> response = null;
			String url = req.getUrl();
			if(url == null) {
				url =  ConfigProperties.getInstance().getConfigValue("mirs.api.createItemMaster.url");
			}
//			List<ItemMasterReq> listOfSalesRequest = new ArrayList<>();
//			listOfSalesRequest.add(itemMaster);
			HttpEntity<ItemMasterReq> entity = new HttpEntity<ItemMasterReq>(itemMaster, getMIRSHeaders(req.getToken()));
			LoggingHelper.logRequest(entity);
			response = restTemplate.exchange(url, HttpMethod.POST, entity, ItemMasterResponse.class);
			LoggingHelper.logResponse(response);
			if(null != response && response.getStatusCode().is2xxSuccessful())
				return response.getBody();
			else {
				return null;
			}
//				throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		} catch (Exception e) {
			logger.error("Error While creating itemMasetr for Item Code :- " + itemMaster.getItems().get(0).getItem());
			String message = e.getMessage();
			ItemMasterConfigDTO itemMasterConfigDTO = getItemMasterConfig(message, "N", itemMaster);
			foundationDataService.setExportedItems(itemMasterConfigDTO);
			return null;
		}
	}

	
	@Override
	public ItemMasterRequest checkItemMasterExist(FoundadtionRequest request, String itemCode) throws Exception {
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.checkItem.url").concat("?&itemCode="+request.getCountry() + "&");
			url = url.concat("limit=" + request.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(request.getToken()));
		ItemMasterRequest response = restTemplate.exchange(url, HttpMethod.GET, entity, ItemMasterRequest.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	public ItemMasterResponse itemLocationRaning(FoundadtionRequest request, ItemMasterLocRequest itemMaster) throws Exception {
		ResponseEntity<ItemMasterResponse> response = null;
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.locationRaning.url");
		}
		HttpEntity<ItemMasterLocRequest> entity = new HttpEntity<ItemMasterLocRequest>(itemMaster, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		response = restTemplate.exchange(url, HttpMethod.POST, entity, ItemMasterResponse.class);
		LoggingHelper.logResponse(response);
		if(null != response && response.getStatusCode().is2xxSuccessful())
			return response.getBody();
		else
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
	}
	
	private ItemMasterConfigDTO getItemMasterConfig(String message, String exported, ItemMasterReq buildItemMaster) {
		ItemMasterConfigDTO itemMasterConfigDTO = new ItemMasterConfigDTO();
		itemMasterConfigDTO.setCREATE_DATETIME(new Date());
		itemMasterConfigDTO.setExported(exported);
		itemMasterConfigDTO.setERROR(message);
		itemMasterConfigDTO.setItemCode(buildItemMaster.getItems().get(0).getItem());
		return itemMasterConfigDTO;
	}


	@Override
	public DepartmentResponse getMerchHierarchyForWMS(FoundadtionRequest req) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchHierarchy.url").concat("?status="+req.getStatus());
			if(StringUtils.isNotEmpty(req.getStartTime()))
				url = url.concat("&fromdate="+req.getStartTime()).concat("&");
			if(StringUtils.isNotEmpty(req.getEndTime()))
				url = url.concat("todate="+req.getEndTime()).concat("&");
			url = url.concat("limit="+req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
//		LoggingHelper.logRequest(entity);
		DepartmentResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, DepartmentResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}

	@Override
	public SubCategoryResponse getSubClass(FoundadtionRequest req) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.deps.url").concat("?");
			if(StringUtils.isNotEmpty(req.getStartTime())) {
				url = url.concat("&fromdate=" + req.getStartTime());
			}
			if(StringUtils.isNotEmpty(req.getEndTime())) {
				url = url.concat("&todate=" + req.getEndTime()).concat("&");
			}
			url = url.concat("limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		SubCategoryResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, SubCategoryResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public StoresResponse getStoresData(FoundadtionRequest request) throws Exception {
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.prod.stores").concat("?");
			if(StringUtils.isNotEmpty(request.getStartTime())) {
				url = url.concat("&since=" + request.getStartTime()).concat("&");
			}
			if(StringUtils.isNotEmpty(request.getEndTime())) {
				url = url.concat("before=" + request.getEndTime()).concat("&");
			}
			url = url.concat("limit=" + request.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		StoresResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, StoresResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}


	@Override
	public CategoryResponse getClass(FoundadtionRequest req) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.subcategory.url").concat("?");
			if(StringUtils.isNotEmpty(req.getStartTime())) {
				url = url.concat("&since="+req.getStartTime()).concat("&");
			}
			if(StringUtils.isNotEmpty(req.getEndTime())) {
				url = url.concat("before="+req.getEndTime()).concat("&");
			}
			url = url.concat("limit="+req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		CategoryResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, CategoryResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}


	@Override
	public SalesResponse sendSalesData(FoundadtionRequest req, SalesRequest convertDtoToRequestParam) throws Exception {
		ResponseEntity<SalesResponse> response = null;
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.resa.prod.sales");
		}
		List<SalesRequest> listOfSalesRequest = new ArrayList<>();
		listOfSalesRequest.add(convertDtoToRequestParam);
		HttpEntity<List<SalesRequest>> entity = new HttpEntity<List<SalesRequest>>(listOfSalesRequest, getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		response = restTemplate.exchange(url, HttpMethod.POST, entity, SalesResponse.class);
		LoggingHelper.logResponse(response);
		if(null != response && response.getStatusCode().is2xxSuccessful())
			return response.getBody();
		else
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
	}
	
	@Override
	public SalesResponse sendSalesDataForStage(FoundadtionRequest req, SalesRequest convertDtoToRequestParam) throws Exception {
		ResponseEntity<SalesResponse> response = null;
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.resa.sales");
		}
		List<SalesRequest> listOfSalesRequest = new ArrayList<>();
		listOfSalesRequest.add(convertDtoToRequestParam);
		HttpEntity<List<SalesRequest>> entity = new HttpEntity<List<SalesRequest>>(listOfSalesRequest, getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		response = restTemplate.exchange(url, HttpMethod.POST, entity, SalesResponse.class);
		LoggingHelper.logResponse(response);
		if(null != response && response.getStatusCode().is2xxSuccessful())
			return response.getBody();
		else
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
//		return null;
	}
	
	@Override
	public OtherShopResponse getOtherShopData(FoundadtionRequest req, String invoiceDONo) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.resa.prod.otherShopReturns").concat("?&TRAN_NO=" + invoiceDONo);
			url = url.concat("&limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
//		LoggingHelper.logRequest(entity);
		OtherShopResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, OtherShopResponse.class).getBody();
//		LoggingHelper.logResponse(response);
		return response;
	}


	@Override
	public MissingBarcodeDetailsResponse sendBarcodeHeaderDetails(FoundadtionRequest req, MissingBarcodeDetailsRequest missingBarcodeDetailsRequest) throws IOException {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.prod.missingBarcodeDetails");
		}
		HttpEntity<MissingBarcodeDetailsRequest> entity = new HttpEntity<MissingBarcodeDetailsRequest>(missingBarcodeDetailsRequest, getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		ResponseEntity<MissingBarcodeDetailsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, MissingBarcodeDetailsResponse.class);
		if(null != response && response.getStatusCodeValue() == 555) {
			return response.getBody();
		} else if(null != response && response.getStatusCode().is2xxSuccessful()) {
			if(null != response.getBody()) {
				return response.getBody();
			} else {
				MissingBarcodeDetailsResponse missingBarcodeDetailsResponse = new MissingBarcodeDetailsResponse();
				missingBarcodeDetailsResponse.setCode(String.valueOf(response.getStatusCodeValue()));
				return missingBarcodeDetailsResponse;
			}
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		}
	}

	@Override
	public MissingBarcodeDetailsResponse sendInvoiceReprintHistoryData(FoundadtionRequest request, InvoiceReprintHistoryRequest invoiceReprintHistoryRequest) throws IOException{
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.prod.invoiceReprintHistory");
		}
		HttpEntity<InvoiceReprintHistoryRequest> entity = new HttpEntity<InvoiceReprintHistoryRequest>(invoiceReprintHistoryRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		ResponseEntity<MissingBarcodeDetailsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, MissingBarcodeDetailsResponse.class);
		if(null != response && response.getStatusCodeValue() == 555) {
			return response.getBody();
		} else if(response != null && response.getStatusCode().is2xxSuccessful()) {
			if(null != response.getBody()) {
				return response.getBody();
			} else {
				MissingBarcodeDetailsResponse missingBarcodeDetailsResponse = new MissingBarcodeDetailsResponse();
				missingBarcodeDetailsResponse.setCode(String.valueOf(response.getStatusCodeValue()));
				return missingBarcodeDetailsResponse;
			}
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		}
	}

	@Override
	public MissingBarcodeDetailsResponse sendItemDeleteDetails(FoundadtionRequest request, ItemDeleteRequest itemDeleteRequest) throws IOException {

		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.prod.itemDelete");
		}
		HttpEntity<ItemDeleteRequest> entity = new HttpEntity<ItemDeleteRequest>(itemDeleteRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		ResponseEntity<MissingBarcodeDetailsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, MissingBarcodeDetailsResponse.class);
		if(null != response && response.getStatusCodeValue() == 555) {
			return response.getBody();
		} else if(response != null && response.getStatusCode().is2xxSuccessful()) {
			if(null != response.getBody()) {
				return response.getBody();
			} else {
				MissingBarcodeDetailsResponse missingBarcodeDetailsResponse = new MissingBarcodeDetailsResponse();
				missingBarcodeDetailsResponse.setCode(String.valueOf(response.getStatusCodeValue()));
				return missingBarcodeDetailsResponse;
			}
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		}
	}


	@Override
	public MissingBarcodeDetailsResponse sendEWalletLogData(FoundadtionRequest request, BflEwalletLogRequest eWalletLogRequest) throws IOException {

		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.prod.eWalletData");
		}
		HttpEntity<BflEwalletLogRequest> entity = new HttpEntity<BflEwalletLogRequest>(eWalletLogRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		ResponseEntity<MissingBarcodeDetailsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, MissingBarcodeDetailsResponse.class);
		if(null != response && response.getStatusCodeValue() == 555) {
			return response.getBody();
		} else if(response != null && response.getStatusCode().is2xxSuccessful()) {
			if(null != response.getBody()) {
				return response.getBody();
			} else {
				MissingBarcodeDetailsResponse missingBarcodeDetailsResponse = new MissingBarcodeDetailsResponse();
				missingBarcodeDetailsResponse.setCode(String.valueOf(response.getStatusCodeValue()));
				return missingBarcodeDetailsResponse;
			}
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		}

	}

	@Override
	public MissingBarcodeDetailsResponse sendGiftCardLogData(FoundadtionRequest request, GiftCardEntryLogRequest eWalletLogRequest) throws IOException {
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.prod.giftCardData");
		}
		HttpEntity<GiftCardEntryLogRequest> entity = new HttpEntity<GiftCardEntryLogRequest>(eWalletLogRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		ResponseEntity<MissingBarcodeDetailsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, MissingBarcodeDetailsResponse.class);
		if(null != response && response.getStatusCodeValue() == 555) {
			return response.getBody();
		} else if(null != response && response.getStatusCode().is2xxSuccessful()) {
			if(null != response.getBody()) {
				return response.getBody();
			} else {
				MissingBarcodeDetailsResponse missingBarcodeDetailsResponse = new MissingBarcodeDetailsResponse();
				missingBarcodeDetailsResponse.setCode(String.valueOf(response.getStatusCodeValue()));
				return missingBarcodeDetailsResponse;
			}
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		}
	}

	@Override
	public MissingBarcodeDetailsResponse sendPriceDetailAgeingData(FoundadtionRequest request, PriceDetailAgeingRequest priceDetailAgeingDataRequest) throws IOException {
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.prod.priceDetailAgeingData");
		}
		HttpEntity<PriceDetailAgeingRequest> entity = new HttpEntity<PriceDetailAgeingRequest>(priceDetailAgeingDataRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		ResponseEntity<MissingBarcodeDetailsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, MissingBarcodeDetailsResponse.class);
		if(null != response && response.getStatusCodeValue() == 555) {
			return response.getBody();
		} else if(null != response && response.getStatusCode().is2xxSuccessful()) {
			if(null != response.getBody()) {
				return response.getBody();
			} else {
				MissingBarcodeDetailsResponse missingBarcodeDetailsResponse = new MissingBarcodeDetailsResponse();
				missingBarcodeDetailsResponse.setCode(String.valueOf(response.getStatusCodeValue()));
				return missingBarcodeDetailsResponse;
			}
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		}
	}

	@Override
	public MissingBarcodeDetailsResponse sendPriceHeaderAgeingData(FoundadtionRequest request, PriceHeadAgeingRequest priceHeadAgeingDataRequest) throws IOException {
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.prod.priceHeaderAgeingData");
		}
		HttpEntity<PriceHeadAgeingRequest> entity = new HttpEntity<PriceHeadAgeingRequest>(priceHeadAgeingDataRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		ResponseEntity<MissingBarcodeDetailsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, MissingBarcodeDetailsResponse.class);
		if(null != response && response.getStatusCodeValue() == 555) {
			return response.getBody();
		} else if(response != null && response.getStatusCode().is2xxSuccessful()) {
			if(null != response.getBody()) {
				return response.getBody();
			} else {
				MissingBarcodeDetailsResponse missingBarcodeDetailsResponse = new MissingBarcodeDetailsResponse();
				missingBarcodeDetailsResponse.setCode(String.valueOf(response.getStatusCodeValue()));
				return missingBarcodeDetailsResponse;
			}
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		}
	}

	@Override
	public MissingBarcodeDetailsResponse sendMissingBarcodeHeaderDataRequest(FoundadtionRequest request, MissingBarcodeHeaderRequest missingBarcodeHeaderRequest) throws IOException {
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.prod.missingBarcodeHeaders");
		}
		HttpEntity<MissingBarcodeHeaderRequest> entity = new HttpEntity<MissingBarcodeHeaderRequest>(missingBarcodeHeaderRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		ResponseEntity<MissingBarcodeDetailsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, MissingBarcodeDetailsResponse.class);
		if(null != response && response.getStatusCodeValue() == 555) {
			return response.getBody();
		} else if(response != null && response.getStatusCode().is2xxSuccessful()) {
			if(null != response.getBody()) {
				return response.getBody();
			} else {
				MissingBarcodeDetailsResponse missingBarcodeDetailsResponse = new MissingBarcodeDetailsResponse();
				missingBarcodeDetailsResponse.setCode(String.valueOf(response.getStatusCodeValue()));
				return missingBarcodeDetailsResponse;
			}
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		}
	}
	
	public MissingBarcodeDetailsResponse sendBFLIntegrationDataRequest(FoundadtionRequest request, BFLIntegrationDataRequest bflIntegrationDataRequest) throws IOException {
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.integration.data");
		}
		HttpEntity<BFLIntegrationDataRequest> entity = new HttpEntity<BFLIntegrationDataRequest>(bflIntegrationDataRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		ResponseEntity<MissingBarcodeDetailsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, MissingBarcodeDetailsResponse.class);
		if(null != response && response.getStatusCodeValue() == 555) {
			return response.getBody();
		} else if(response != null && response.getStatusCode().is2xxSuccessful()) {
			if(null != response.getBody()) {
				return response.getBody();
			} else {
				MissingBarcodeDetailsResponse missingBarcodeDetailsResponse = new MissingBarcodeDetailsResponse();
				missingBarcodeDetailsResponse.setCode(String.valueOf(response.getStatusCodeValue()));
				return missingBarcodeDetailsResponse;
			}
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
		}
	}


	@Override
	public SalesResponse sendStoreOpenData(FoundadtionRequest request, SalesRequest salesRequest) throws IOException {
		ResponseEntity<SalesResponse> response = null;
		String url = request.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.resa.sales");
		}
		List<SalesRequest> listOfSalesRequest = new ArrayList<>();
		listOfSalesRequest.add(salesRequest);
		HttpEntity<List<SalesRequest>> entity = new HttpEntity<List<SalesRequest>>(listOfSalesRequest, getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		response = restTemplate.exchange(url, HttpMethod.POST, entity, SalesResponse.class);
		LoggingHelper.logResponse(response);
		if(response!=null && response.getStatusCode().is2xxSuccessful())
			return response.getBody();
		else
			throw new HttpClientErrorException(response.getStatusCode(), LoggingHelper.getResponseString(response));
	}


	
	@Override
	public String getAuthToken() throws Exception {
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("grant_type", ConfigProperties.getInstance().getConfigValue("rics.api.grantType"));
		map.add("scope", ConfigProperties.getInstance().getConfigValue("rics.api.scope"));
		String url = ConfigProperties.getInstance().getConfigValue("rics.api.auth.url");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,getAuthAPIHeaders());
//		LoggingHelper.logRequest(entity);
		ResponseEntity<AuthResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, AuthResponse.class);
//		LoggingHelper.logResponse(response);
		if(response!=null && response.getStatusCode().is2xxSuccessful()) {
			return response.getBody().getTokenType()+ " " +response.getBody().getAccessToken();
		}
		else
			throw new Exception("Failed to get Auth token.");
	}
	
	@Override
	public String getAuthTokenForProd() throws Exception {
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("grant_type", ConfigProperties.getInstance().getConfigValue("rics.api.grantType.prod"));
		map.add("scope", ConfigProperties.getInstance().getConfigValue("rics.api.scope.prod"));
		String url = ConfigProperties.getInstance().getConfigValue("rics.api.auth.url");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, getProdAuthAPIHeaders());
//		LoggingHelper.logRequest(entity);
		ResponseEntity<AuthResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, AuthResponse.class);
//		LoggingHelper.logResponse(response);
		if(response!=null && response.getStatusCode().is2xxSuccessful()) {
			return response.getBody().getTokenType()+ " " +response.getBody().getAccessToken();
		}
		else
			throw new Exception("Failed to get Auth token.");
	}


	@Override
	public ItemMasterRequest getItemLevelData(FoundadtionRequest req, String itemId) {
		try {
			String url = req.getUrl();
			if(url == null) {
				url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.itemMaster.url");
				if(null != itemId) {
					url = url.concat("/" + itemId);
				} else {
					url = url.concat("?");
					if(StringUtils.isNotEmpty(req.getStartTime()))
						url = url.concat("&since=" + req.getStartTime()).concat("&");
					if(StringUtils.isNotEmpty(req.getEndTime()))
						url = url.concat("before=" + req.getEndTime()).concat("&");
					url = url.concat("limit="+req.getLimit());
				}
			}
			HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
			LoggingHelper.logRequest(entity);
			ItemMasterRequest response = restTemplate.exchange(url, HttpMethod.GET, entity, ItemMasterRequest.class).getBody();
//			LoggingHelper.logResponse(response);
			return response;
		} catch (Exception e) {
			return null;
		}
	}


	
	@Override
	public PriceChangeResponse getBFLPriceChangeResponse(FoundadtionRequest req) throws IOException {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.bflpriceChange.url").concat("?");
			if(StringUtils.isNotEmpty(req.getStartTime()))
				url = url.concat("&fromdate=" + req.getStartTime()).concat("&");
			if(StringUtils.isNotEmpty(req.getEndTime()))
				url = url.concat("todate=" + req.getEndTime()).concat("&");
			url = url.concat("limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		PriceChangeResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, PriceChangeResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}


	
	@Override
	public PriceChangeResponse getClearancesWHData(FoundadtionRequest req) throws IOException {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.rpm.clearance").concat("?");
			if(StringUtils.isNotEmpty(req.getStartTime()))
				url = url.concat("&fromdate=" + req.getStartTime()).concat("&");
			if(StringUtils.isNotEmpty(req.getEndTime()))
				url = url.concat("todate=" + req.getEndTime()).concat("&");
			url = url.concat("limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		PriceChangeResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, PriceChangeResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public PurchaseOrdersResponse getPurchaseOrdersData(FoundadtionRequest req) throws IOException  {
		String url = req.getUrl();
		if(url == null) {
//			String Warehouse_Id = ConfigProperties.getInstance().getConfigValue("WAREHOUSE_ID");
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getPurchaseOrdersData").concat("?");
			if(StringUtils.isNotEmpty(req.getStartTime())) {
				url = url.concat("&fromdate=" + req.getStartTime());
			} if(StringUtils.isNotEmpty(req.getEndTime())) {
				url = url.concat("&todate=" + req.getEndTime()).concat("&");
			}
//			if(null != Warehouse_Id && !Warehouse_Id.isEmpty())
//				url = url.concat("locType=" + "W").concat("&");
//				url = url.concat("locType=" + "W").concat("&").concat("nodeid=" + Warehouse_Id).concat("&");
			url = url.concat("locType=" + "W").concat("&").concat("limit="+req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		PurchaseOrdersResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, PurchaseOrdersResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public MissingResaItemsResponse getMissingResaItems(FoundadtionRequest req) throws IOException {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getMissingResaItems").concat("?");
			if(StringUtils.isNotEmpty(req.getStartTime())) {
				url = url.concat("&fromdate=" + req.getStartTime());
			}
			url = url.concat("&limit=" + req.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		MissingResaItemsResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, MissingResaItemsResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}


	/**
	 * Get API specific headers
	 */
	private HttpHeaders getAuthAPIHeaders() throws IOException{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String clientId = ConfigProperties.getInstance().getConfigValue("rics.api.clientId");
		String clientSecret = ConfigProperties.getInstance().getConfigValue("rics.api.clientSecret");
		String encoding = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}
	
	private HttpHeaders getProdAuthAPIHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String clientId = ConfigProperties.getInstance().getConfigValue("rics.api.clientId.prod");
		String clientSecret = ConfigProperties.getInstance().getConfigValue("rics.api.clientSecret.prod");
		String encoding = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}


	private HttpHeaders getMIRSHeaders(String token) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}


	@Override
	public UpcBarcodeResponse getUpcBarcodesData(FoundadtionRequest request) throws Exception {
		String url = request.getUrl();
//		String countryId = ConfigProperties.getInstance().getConfigValue("COUNTRY_ID");
		if(url == null) {
//			mirs.api.getWmsupcbarcodes
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getWmsupcbarcodes").concat("?&");
//			if(StringUtils.isNotEmpty(request.getStartTime()))
//				url = url.concat("fromdate="+request.getStartTime()).concat("&");
//			if(StringUtils.isNotEmpty(request.getEndTime()))
//				url = url.concat("todate="+request.getEndTime()).concat("&");
			if(StringUtils.isNotEmpty(request.getContNo()))
				url = url.concat("contNo=" + request.getContNo()).concat("&");
//			if(null != countryId && !countryId.isEmpty())
//				url = url.concat("countryId="+countryId).concat("&");
			url = url.concat("limit=" + request.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		UpcBarcodeResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, UpcBarcodeResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public void updateContainerStatus(FoundadtionRequest req, ContainerDetailsDTO containerDetailsDTO) throws Exception {
		String url = req.getUrl();
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.merchhier.updateContStatus.url");
		}
		HttpEntity<ContainerDetailsDTO> entity = new HttpEntity<ContainerDetailsDTO>(containerDetailsDTO, getMIRSHeaders(req.getToken()));
		LoggingHelper.logRequest(entity);
		restTemplate.exchange(url, HttpMethod.PUT, entity, ContainerDetailsDTO.class);
	}
	
	public UpcBarcodeResponse getItemAttributesDATADataResponse(FoundadtionRequest request) throws Exception {
		String url = request.getUrl();
//		String countryId = ConfigProperties.getInstance().getConfigValue("COUNTRY_ID");
		if(url == null) {
//			mirs.api.getWmsupcbarcodes
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getWmsItemAttributes").concat("?&");
//			if(StringUtils.isNotEmpty(request.getStartTime()))
//				url = url.concat("fromdate="+request.getStartTime()).concat("&");
//			if(StringUtils.isNotEmpty(request.getEndTime()))
//				url = url.concat("todate="+request.getEndTime()).concat("&");
			if(StringUtils.isNotEmpty(request.getContNo()))
				url = url.concat("contNo=" + request.getContNo()).concat("&");
//			if(null != countryId && !countryId.isEmpty())
//				url = url.concat("countryId="+countryId).concat("&");
			url = url.concat("limit=" + request.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		UpcBarcodeResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, UpcBarcodeResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	public UpcBarcodeResponse getBaseOrders(FoundadtionRequest request) throws Exception {
		String url = request.getUrl();
//		String countryId = ConfigProperties.getInstance().getConfigValue("COUNTRY_ID");
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getBaseOrders.url").concat("?&");
			if(StringUtils.isNotEmpty(request.getContNo()))
				url = url.concat("contNo=" + request.getContNo()).concat("&");
//			if(StringUtils.isNotEmpty(request.getStartTime()))
//				url = url.concat("fromdate="+request.getStartTime()).concat("&");
//			if(StringUtils.isNotEmpty(request.getEndTime()))
//				url = url.concat("todate="+request.getEndTime()).concat("&");
//			if(null != countryId && !countryId.isEmpty())
//				url = url.concat("countryId="+countryId).concat("&");
			url = url.concat("limit="+request.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		UpcBarcodeResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, UpcBarcodeResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}
	
	@Override
	public ContainerDetailsResponse getContainerDetailsResponse(FoundadtionRequest request) throws Exception {
		String url = request.getUrl();
//		String countryId = ConfigProperties.getInstance().getConfigValue("COUNTRY_ID");
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getContDetails.url").concat("?&");
			if(StringUtils.isNotEmpty(request.getStartTime()))
				url = url.concat("fromdate="+request.getStartTime()).concat("&");
			if(StringUtils.isNotEmpty(request.getEndTime()))
				url = url.concat("todate="+request.getEndTime()).concat("&");
//			if(null != countryId && !countryId.isEmpty())
//				url = url.concat("countryId="+countryId).concat("&");
			url = url.concat("limit="+request.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		ContainerDetailsResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, ContainerDetailsResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}

	
	public UpcBarcodeResponse getShipmentUploadResponse(FoundadtionRequest request) throws IOException {
		String url = request.getUrl();
//		String countryId = ConfigProperties.getInstance().getConfigValue("COUNTRY_ID");
		if(url == null) {
			url =  ConfigProperties.getInstance().getConfigValue("mirs.api.getWMSGroupWiseManifest").concat("?&");
			if(StringUtils.isNotEmpty(request.getContNo()))
				url = url.concat("contNo=" + request.getContNo()).concat("&");
			   url = url.concat("limit="+request.getLimit());
		}
		HttpEntity<String> entity = new HttpEntity<String>(getMIRSHeaders(request.getToken()));
		LoggingHelper.logRequest(entity);
		UpcBarcodeResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, UpcBarcodeResponse.class).getBody();
		LoggingHelper.logResponse(response);
		return response;
	}



}