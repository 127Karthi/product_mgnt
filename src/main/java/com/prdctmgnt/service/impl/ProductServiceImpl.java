package com.prdctmgnt.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prdctmgnt.constants.AppConstants;
import com.prdctmgnt.model.ApprovalRequest;
import com.prdctmgnt.model.Product;
import com.prdctmgnt.model.ProductSearchInput;
import com.prdctmgnt.repository.ApprovalRequestRepository;
import com.prdctmgnt.repository.ProductRepository;
import com.prdctmgnt.service.ProductService;

import jakarta.transaction.Transactional;
@Component
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	ApprovalRequestRepository apprvlReqRepo;
	
	//public static Long apprvlreqId = 1l;

	@Override
	public List<Product> getAllActiveProducts() throws Exception{
		return (List<Product>) productRepo.getAllActiveProducts();
	}

	@Override
	public List<Product> searchProducts(ProductSearchInput search) throws Exception {
		return productRepo.searchProducts(search.getProductName(), search.getMinPrice(), search.getMaxPrice(), 
				search.getMinPostedDate(), search.getMaxPostedDate());
	}

	@Override
	public Product createProduct(Product product) throws Exception {
		if(product.getPrdctPrice().longValue() > AppConstants.APPRVL_RQRD_PRICE_THRESHOLD) {
			ApprovalRequest apprvlReq = new ApprovalRequest();
			
			apprvlReq.setPrdctName(product.getPrdctName());
			apprvlReq.setPrdctPrice(product.getPrdctPrice());
			apprvlReq.setPrdctSts(product.getPrdctSts());
			apprvlReq.setApprvlReqType(AppConstants.APPRVL_REQ_TYPE_CREATE);
			apprvlReq.setApprvlInitBy(AppConstants.SYSTEM);
			apprvlReq.setApprvlInitDt(new Date(System.currentTimeMillis()));
			apprvlReq.setApprvlSts(AppConstants.APPRVL_REQ_STS_INIT);
			apprvlReq.setCreatedDt(new Timestamp(System.currentTimeMillis()));
			apprvlReq.setCreatedBy(AppConstants.SYSTEM);
			
			product.setPrdctSts(AppConstants.PRDCT_STS_PEND_APPRVL);
			
			apprvlReqRepo.save(apprvlReq);
			
		}else {
			product.setPrdctSts(AppConstants.PRDCT_STS_APPROVED);
			product.setCreatedDt(new Timestamp(System.currentTimeMillis()));
			product.setCreatedBy(AppConstants.SYSTEM);
			product.setIsDeleted(AppConstants.NO);
			productRepo.save(product);
		}
		return product;
		
	}
	
	@Override
	public Product updateProduct(Product prdct) throws Exception {
		
		Optional<Product> p = productRepo.findById(prdct.getProductId());
		Product product = null;
		if(p.isPresent()) {
			product = p.get();
		}else {
			return null;
		}
		
		cancelExistingApprvlRqstsByPrdctId(product.getProductId());
		
		if((product.getPrdctPrice().longValue()/2) < (prdct.getPrdctPrice().longValue() - product.getPrdctPrice().longValue())) {
			ApprovalRequest apprvlReq = new ApprovalRequest();
			/*
			 * apprvlReq.setApprovalReqId(apprvlreqId); apprvlreqId++;
			 */
			apprvlReq.setProductId(product.getProductId());
			apprvlReq.setPrdctName(prdct.getPrdctName());
			apprvlReq.setPrdctPrice(prdct.getPrdctPrice());
			apprvlReq.setPrdctSts(prdct.getPrdctSts());
			apprvlReq.setApprvlReqType(AppConstants.APPRVL_REQ_TYPE_UPDATE);
			apprvlReq.setApprvlInitBy(AppConstants.SYSTEM);
			apprvlReq.setApprvlInitDt(new Date(System.currentTimeMillis()));
			apprvlReq.setApprvlSts(AppConstants.APPRVL_REQ_STS_INIT);
			apprvlReq.setCreatedDt(new Timestamp(System.currentTimeMillis()));
			apprvlReq.setCreatedBy(AppConstants.SYSTEM);
			
			product.setPrdctSts(AppConstants.PRDCT_STS_PEND_APPRVL);
			
			apprvlReqRepo.save(apprvlReq);
			
		}else {
			product.setPrdctSts(AppConstants.PRDCT_STS_APPROVED);
			product.setCreatedDt(new Timestamp(System.currentTimeMillis()));
			product.setCreatedBy(AppConstants.SYSTEM);
			product.setIsDeleted(AppConstants.NO);
			productRepo.save(product);
		}
		return product;
		
	}

	@Override
	public String deleteProduct(Long productId) throws Exception {
		
		Optional<Product> p = productRepo.findById(productId);
		Product product = null;
		if(p.isPresent()) {
			product = p.get();
		}else {
			return AppConstants.INFO_NOT_FOUND;
		}
		
		cancelExistingApprvlRqstsByPrdctId(productId);
		
		ApprovalRequest apprvlReq = new ApprovalRequest();
		/*
		 * apprvlReq.setApprovalReqId(apprvlreqId); apprvlreqId++;
		 */
		apprvlReq.setProductId(product.getProductId());
		apprvlReq.setPrdctName(product.getPrdctName());
		apprvlReq.setPrdctPrice(product.getPrdctPrice());
		apprvlReq.setPrdctSts(product.getPrdctSts());
		apprvlReq.setApprvlReqType(AppConstants.APPRVL_REQ_TYPE_DELETE);
		apprvlReq.setApprvlInitBy(AppConstants.SYSTEM);
		apprvlReq.setApprvlInitDt(new Date(System.currentTimeMillis()));
		apprvlReq.setApprvlSts(AppConstants.APPRVL_REQ_STS_INIT);
		apprvlReq.setCreatedDt(new Timestamp(System.currentTimeMillis()));
		apprvlReq.setCreatedBy(AppConstants.SYSTEM);
		
		apprvlReqRepo.save(apprvlReq);
		
		return AppConstants.SUCCESS;
	}
	
	private void cancelExistingApprvlRqstsByPrdctId(Long prdctId) {
		List<ApprovalRequest> exstApprvlreq = apprvlReqRepo.getExistingOpenApprvlRqstByPrdctId(prdctId);
		for(ApprovalRequest appReq: exstApprvlreq) {
			updateApprvlRqstStatus(appReq, AppConstants.APPRVL_REQ_STS_CNCLD);
		}
	}
	
	@Override
	public List<ApprovalRequest> getOpenApprovalReqs() throws Exception{
		// TODO Auto-generated method stub
		return (List<ApprovalRequest>) apprvlReqRepo.getAllOpenApprovalreqs();
	}
	
	@Override
	@Transactional
	public Product approve(Long apprvlId) throws Exception{
		
		ApprovalRequest apprvlReq = apprvlReqRepo.getExistingOpenApprvlRqstByApprvlRqstId(apprvlId);
		Product product = null;
		if(apprvlReq == null) {
			return null;
		}
		
		if(AppConstants.APPRVL_REQ_TYPE_CREATE.equalsIgnoreCase(apprvlReq.getApprvlReqType())) {
			product = createProduct(apprvlReq);
			updateApprvlRqstStatus(apprvlReq, AppConstants.APPRVL_REQ_STS_APPRVD);
		}else if(AppConstants.APPRVL_REQ_TYPE_UPDATE.equalsIgnoreCase(apprvlReq.getApprvlReqType())) {
			Optional<Product> prdct = productRepo.findById(apprvlReq.getProductId());
			
			if(prdct.isPresent()) {
				product = prdct.get();
				product = updateProduct(product,apprvlReq);
				updateApprvlRqstStatus(apprvlReq, AppConstants.APPRVL_REQ_STS_APPRVD);
			}else {
				return null;
			}
		}else if(AppConstants.APPRVL_REQ_TYPE_DELETE.equalsIgnoreCase(apprvlReq.getApprvlReqType())) {
			Optional<Product> prdct = productRepo.findById(apprvlReq.getProductId());
			
			if(prdct.isPresent()) {
				product = prdct.get();
				product = deleteProduct(product);
				updateApprvlRqstStatus(apprvlReq, AppConstants.APPRVL_REQ_STS_APPRVD);
			}else {
				return null;
			}
		}
		
		return product;
	}
	
	
	
	private void updateApprvlRqstStatus(ApprovalRequest apprvlReq, String status) {
		
		if(apprvlReq != null) {
			apprvlReq.setApprvlSts(status);
			apprvlReq.setActionDt(new Date(System.currentTimeMillis()));
			apprvlReq.setActionBy(AppConstants.ADMIN);
			apprvlReq.setUpdatedDt(new Timestamp(System.currentTimeMillis()));
			apprvlReq.setUpdatedBy(AppConstants.ADMIN);
			
			apprvlReqRepo.save(apprvlReq);
		}
	}
	
	
	private Product createProduct(ApprovalRequest apprvlReq) {
		Product product = new Product();
		
		product.setPrdctApprvdBy(AppConstants.ADMIN);
		product.setPrdctApprvdDt(new Date(System.currentTimeMillis()));
		product.setPrdctPrice(apprvlReq.getPrdctPrice());
		product.setPrdctName(apprvlReq.getPrdctName());
		product.setPrdctSts(AppConstants.PRDCT_STS_APPROVED);
		product.setPrdctPostDt(new Date(System.currentTimeMillis()));
		product.setPrdctInStock(AppConstants.YES);
		product.setCreatedDt(new Timestamp(System.currentTimeMillis()));
		product.setCreatedBy(AppConstants.ADMIN);
		product.setIsDeleted(AppConstants.NO);
		
		productRepo.save(product);
		return product;
	}
	
	
	private Product updateProduct(Product product, ApprovalRequest apprvlReq) {
				
		product.setPrdctApprvdBy(AppConstants.ADMIN);
		product.setPrdctApprvdDt(new Date(System.currentTimeMillis()));
		product.setPrdctPrice(apprvlReq.getPrdctPrice());
		product.setPrdctName(apprvlReq.getPrdctName());
		product.setPrdctSts(AppConstants.PRDCT_STS_APPROVED);
		//product.setPrdctPostDt(new Date(System.currentTimeMillis()));
		product.setPrdctInStock(AppConstants.YES);
		product.setUpdatedDt(new Timestamp(System.currentTimeMillis()));
		product.setUpdatedBy(AppConstants.ADMIN);
		product.setIsDeleted(AppConstants.NO);
		
		productRepo.save(product);
		return product;
	}
	
	
	private Product deleteProduct(Product product) {
		
		product.setPrdctSts(AppConstants.PRDCT_STS_DELETED);
		product.setPrdctInStock(AppConstants.NO);
		product.setUpdatedDt(new Timestamp(System.currentTimeMillis()));
		product.setUpdatedBy(AppConstants.ADMIN);
		product.setIsDeleted(AppConstants.YES);
		
		productRepo.save(product);
		return product;
	}
	
	
	@Override
	public String reject(Long apprvlId) throws Exception{
		ApprovalRequest apprvlReq = apprvlReqRepo.getExistingOpenApprvlRqstByApprvlRqstId(apprvlId);
		if(apprvlReq == null) {
			return AppConstants.INFO_NOT_FOUND;
		}
		
		updateApprvlRqstStatus(apprvlReq, AppConstants.APPRVL_REQ_STS_RJCTD);
		
		return AppConstants.SUCCESS;
	}

}
