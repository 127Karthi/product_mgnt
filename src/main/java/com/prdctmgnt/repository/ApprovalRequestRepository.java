package com.prdctmgnt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prdctmgnt.model.ApprovalRequest;

public interface ApprovalRequestRepository extends CrudRepository<ApprovalRequest, Long> {
	
	@Query(value=" select * from approval_request where apprvl_sts = 'INIT' order by created_dt asc", nativeQuery = true)
	public List<ApprovalRequest> getAllOpenApprovalreqs();
	
	@Query(value=" select * from approval_request where apprvl_sts = 'INIT' and prdct_id = :prdctId order by created_dt desc", nativeQuery = true)
	public List<ApprovalRequest> getExistingOpenApprvlRqstByPrdctId(@Param("prdctId") Long prdctId);
	
	@Query(value=" select * from approval_request where apprvl_sts = 'INIT' and apprvl_req_id = :apprvlRqstId ", nativeQuery = true)
	public ApprovalRequest getExistingOpenApprvlRqstByApprvlRqstId(@Param("apprvlRqstId") Long apprvlRqstId);

}
