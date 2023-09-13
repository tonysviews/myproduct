package org.meveo.script;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.meveo.admin.exception.BusinessException;
import org.meveo.api.exception.EntityDoesNotExistsException;
import org.meveo.api.persistence.CrossStorageApi;
import org.meveo.api.rest.technicalservice.impl.EndpointRequest;
import org.meveo.model.customEntities.Product;
import org.meveo.model.storage.Repository;
import org.meveo.service.script.Script;
import org.meveo.service.storage.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;


public class GetProductforBothQandP extends Script {

    private static final Logger LOG = LoggerFactory.getLogger(GetProductforBothQandP.class);

    @Inject
    private CrossStorageApi crossStorageApi;

    @Inject
    private RepositoryService repositoryService;

    private String uuid;

    private String result;

    public String getResult() {
        return this.result;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    private String loanid;
    
    public void setLoanid(String loanid) {
		this.loanid = loanid;
	}

    @Override
    public void execute(Map<String, Object> parameters) throws BusinessException {
        LOG.info("Get Product: {}", uuid);
        Repository defaultRepo = repositoryService.findDefaultRepository();
        Map<String, Object> resultMap = new HashMap<>();
        
        System.out.println("#######Path param : "+uuid);
        System.out.println("#######Query param : "+loanid);        
   
        try {
            Product product = crossStorageApi.find(defaultRepo, uuid, Product.class);
            
            resultMap.put("status", "success");
            resultMap.put("result", product);
        } catch (EntityDoesNotExistsException e) {
            String errorMessage = String.format("Product with id: %s does not exist.", uuid);
            LOG.error(errorMessage, e);
            resultMap.put("status", "fail");
            resultMap.put("result", errorMessage);
        }
        result = new Gson().toJson(resultMap);
        super.execute(parameters);
    }
}
