package com.prdctmgnt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prdctmgnt.constants.AppConstants;
import com.prdctmgnt.model.Product;
import com.prdctmgnt.model.ProductSearchInput;
import com.prdctmgnt.service.ProductService;

@RestController
@RequestMapping(path="/api")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
		
	/**
	 * List all the active products
	 * @return
	 */
	@GetMapping("/products")
	public ResponseEntity<Object> getActiveProducts(){
		       
		try {
			return generateResponse("Success", HttpStatus.OK, productService.getAllActiveProducts());
		}catch(Exception e) {
			e.printStackTrace();
			return generateResponse("Exception occured: "+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		
	}
	
	/**
	 * Search product based on the name, min and max price range, min and max posted date range combinations
	 * @param search
	 * @return
	 */
	@GetMapping("/products/search")
	public ResponseEntity<Object> searchProducts(@RequestBody ProductSearchInput search){
		try {
			return generateResponse("Success", HttpStatus.OK, productService.searchProducts(search));
		}catch(Exception e) {
			e.printStackTrace();
			return generateResponse("Exception occured: "+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	/**
	 *  Create new product, 
	 * @param product
	 * @return
	 */
	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		
		try {
			
			if(product.getPrdctPrice().longValue() > AppConstants.MAX_PRICE_THRESHOLD) {
				return generateResponse("Product price exceeded price threshold.", HttpStatus.OK, null);
			}
			
			product = productService.createProduct(product);
			if(AppConstants.PRDCT_STS_PEND_APPRVL.equalsIgnoreCase(product.getPrdctSts())) {
				return generateResponse("Approval Request Created to Create product "+product.getPrdctName(), HttpStatus.OK, product);
			}else {
				return generateResponse("Product Created.", HttpStatus.OK, product);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return generateResponse("Exception occured: "+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product){
		try {
			
			product = productService.updateProduct(product);
			if(AppConstants.PRDCT_STS_PEND_APPRVL.equalsIgnoreCase(product.getPrdctSts())) {
				return generateResponse("Approval Request Created to Update product "+product.getPrdctName(), HttpStatus.OK, null);
			}else {
				return generateResponse("Product Updated.", HttpStatus.OK, null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return generateResponse("Exception occured: "+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable ("productId") Long productId){
		try {
			
			String sts = productService.deleteProduct(productId);
			if(AppConstants.SUCCESS.equalsIgnoreCase(sts)) {
				return generateResponse("Approval Request Created to delete product "+productId, HttpStatus.OK, null);
			}else {
				return generateResponse("Unable to create Approval Request to delete product "+productId, HttpStatus.OK, null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return generateResponse("Exception occured: "+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	
	@GetMapping("/products/approval-queue")
	public ResponseEntity<Object> getOpenApprovalRequests(){
		       
		try {
			return generateResponse("Success", HttpStatus.OK, productService.getOpenApprovalReqs());
		}catch(Exception e) {
			e.printStackTrace();
			return generateResponse("Exception occured: "+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		
	}
	
	
	@PutMapping("/products/approval-queue/{apprvlId}/approve")
	public ResponseEntity<Object> approve(@PathVariable Long apprvlId){
		try {
			
			Product product = productService.approve(apprvlId);
			
			if(product == null) {
				return generateResponse("Product Not Found.", HttpStatus.OK, null);
			}
			
			if(AppConstants.PRDCT_STS_APPROVED.equalsIgnoreCase(product.getPrdctSts())) {
				return generateResponse("Product "+product.getPrdctName()+" is Created/Updated.", HttpStatus.OK, null);
			}else if(AppConstants.PRDCT_STS_DELETED.equalsIgnoreCase(product.getPrdctSts())){
				return generateResponse("Product "+product.getPrdctName()+" is Deleted.", HttpStatus.OK, null);
			}else {
				return generateResponse("Product Not Found.", HttpStatus.OK, null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return generateResponse("Exception occured: "+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	
	@PutMapping("/products/approval-queue/{apprvlId}/reject")
	public ResponseEntity<Object> reject(@PathVariable Long apprvlId){
		try {
			
			String result = productService.reject(apprvlId);
			
			if(AppConstants.SUCCESS.equalsIgnoreCase(result)){
				return generateResponse("Approval request is rejected.", HttpStatus.OK, null);
			}else {
				return generateResponse("Approval request not found.", HttpStatus.OK, null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return generateResponse("Exception occured: "+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	private static ResponseEntity<Object> generateResponse(String msg, HttpStatus status, Object object){
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", msg);
        map.put("status", status);
        map.put("data", object);
        
        return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

}
