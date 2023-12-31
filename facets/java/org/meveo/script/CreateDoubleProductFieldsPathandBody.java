package org.meveo.script;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import com.google.gson.Gson;
import org.meveo.admin.exception.BusinessException;
import org.meveo.api.exception.EntityDoesNotExistsException;
import org.meveo.api.persistence.CrossStorageApi;
import org.meveo.model.customEntities.LoanAccount;
import org.meveo.model.customEntities.Product;
import org.meveo.model.storage.Repository;
import org.meveo.service.script.Script;
import org.meveo.service.storage.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDoubleProductFieldsPathandBody extends Script {

    private static final Logger LOG = LoggerFactory.getLogger(CreateDoubleProductFieldsPathandBody.class);

    @Inject
    private CrossStorageApi crossStorageApi;

    @Inject
    private RepositoryService repositoryService;

    private Product product;

    private String result = null;

    public String getResult() {
        return this.result;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private String loanaccountname;

    private String loanid;

    private Double sanctionamount;

    public void setLoanaccountname(String loanaccountname) {
        this.loanaccountname = loanaccountname;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public void setSanctionamount(Double sanctionamount) {
        this.sanctionamount = sanctionamount;
    }

    @Override
    public void execute(Map<String, Object> parameters) throws BusinessException {
        LOG.info("Creating new product: {}", product);
        Repository defaultRepo = repositoryService.findDefaultRepository();
        String uuid = null;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            uuid = crossStorageApi.createOrUpdate(defaultRepo, product);
            LoanAccount newEntity = new LoanAccount();
            newEntity.setLoanid(loanid);
            newEntity.setLoanaccountname(loanaccountname);
            newEntity.setSanctionamount(sanctionamount);
            String uuid2 = crossStorageApi.createOrUpdate(defaultRepo, newEntity);
        } catch (Exception e) {
            String errorMessage = "Failed to create new product.";
            LOG.error(errorMessage, e);
            resultMap.put("status", "fail");
            resultMap.put("result", errorMessage);
        }
        if (uuid != null) {
            try {
                Product newProduct = crossStorageApi.find(defaultRepo, uuid, Product.class);
                resultMap.put("status", "success");
                resultMap.put("result", newProduct);
            } catch (EntityDoesNotExistsException e) {
                String errorMessage = "Failed to fetch new product";
                LOG.error(errorMessage, e);
                resultMap.put("status", "fail");
                resultMap.put("result", errorMessage);
            }
        } else {
            resultMap.put("status", "fail");
            resultMap.put("result", "New product uuid returned was null.");
        }
        result = new Gson().toJson(resultMap);
        super.execute(parameters);
    }
}
