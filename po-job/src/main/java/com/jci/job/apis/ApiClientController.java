/**
 * 
 */
package com.jci.job.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.azure.BatchInsertReq;
import com.jci.job.azure.BatchInsertRes;
import com.jci.job.entity.PoEntity;
import com.jci.job.service.ApiClientServiceImpl;
import com.microsoft.azure.storage.table.TableEntity;

/**
 * @author cdevdat
 *
 */
@RestController
public class ApiClientController {

	@Autowired
	ApiClientServiceImpl service;
	private static final Logger LOG = LoggerFactory.getLogger(ApiClientController.class);

	@RequestMapping(value = "/getPoDetails", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public PoDetailsRes getPoDetails() {
		LOG.info("### Starting ApigeeClientController.getPoDetails ####");
		//PoRequest request = new PoRequest();
		PoDetailsRes response = service.getPoDetails();
		LOG.info("response-->"+response);
		LOG.info("### Ending ApigeeClientController.getPoDetails ####");
		return response;
	}
	
	@RequestMapping(value = "/insertDummyData", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void insertDummyData() {
		LOG.info("### Starting ApigeeClientController.insertDummyData ####");
		//ResponseEntity<PoItemsEntity> responseEntity = service.getPoItem();
		
		PoEntity entity = new PoEntity("SAP_PO", "1");
		//entity.setOrderNumber("4765421");
		BatchInsertReq req = new BatchInsertReq();
		req.setErpName("SAP");

		HashMap<String, List<TableEntity>> tableNameToEntityMap = new HashMap<String, List<TableEntity>>();
		List<TableEntity> list = new ArrayList<TableEntity>();
		list.add(entity);
		
		tableNameToEntityMap.put("PODETAILS", list);
		req.setTableNameToEntityMap(tableNameToEntityMap);
		
		//BatchInsertRes res = service.batchInsert(req);
		
		LOG.info("### Ending ApigeeClientController.insertDummyData ####");

	}
}