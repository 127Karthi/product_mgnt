package com.prdctmgnt.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.prdctmgnt.model.ApprovalRequest;
import com.prdctmgnt.model.Product;
import com.prdctmgnt.model.ProductSearchInput;
@Component
public interface ProductService {
	
	List<Product> getAllActiveProducts() throws Exception;
	
	List<Product> searchProducts(ProductSearchInput search) throws Exception;
	
	Product createProduct(Product product) throws Exception;
	
	Product updateProduct(Product product) throws Exception;
	
	String deleteProduct(Long productId) throws Exception;

	List<ApprovalRequest> getOpenApprovalReqs() throws Exception;

	Product approve(Long apprvlId) throws Exception;

	String reject(Long apprvlId) throws Exception;

}
