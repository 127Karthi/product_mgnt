package com.prdctmgnt.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prdctmgnt.model.Product;

public interface ProductRepository extends CrudRepository<Product,Long>{
	
	@Query(value=" select * from product where prdct_sts = 'APPRVD' order by prdct_post_dt desc", nativeQuery = true)
	public List<Product> getAllActiveProducts();
	
	@Query(value=" select * from product where (case when :prdctName is not null and :prdctName <> '' then prdct_name = :prdctName else true end) \r\n"
			+ " and (case when :minPrice is not null and :maxPrice is not null then prdct_price between :minPrice and :maxPrice else true end) \r\n"
			+ " and (case when :minPostDt is not null and :maxPostDt is not null then prdct_post_dt between :minPostDt and :maxPostDt else true end) ", nativeQuery = true)
	public List<Product> searchProducts(@Param("prdctName") String prdctName, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice,
			@Param("minPostDt") Date minPostDt, @Param("maxPostDt") Date maxPostDt);

}
