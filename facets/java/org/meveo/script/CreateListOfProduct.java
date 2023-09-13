package org.meveo.script;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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

public class CreateListOfProduct extends Script {

    private static final Logger LOG = LoggerFactory.getLogger(CreateMyProduct.class);

    @Inject
    private CrossStorageApi crossStorageApi;

    @Inject
    private RepositoryService repositoryService;

    private List<Product> products;

    private String result = null;

    public String getResult() {
        return this.result;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public void execute(Map<String, Object> parameters) throws BusinessException {
        LOG.info("Creating new product: {}", products);
        Repository defaultRepo = repositoryService.findDefaultRepository();
        String uuid = null;
        Map<String, Object> resultMap = new HashMap<>();
        if (parameters.get("request") != null) {
            EndpointRequest req = (EndpointRequest) parameters.get("request");
            if (req.getContextPath().equalsIgnoreCase("/meveo")) {
                products = getvalues(products);
            }
        }
        try {
            for (Product p : products) {
                uuid = crossStorageApi.createOrUpdate(defaultRepo, p);
            }
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

    private List<Product> getvalues(List<Product> products) {
        ObjectMapper oMapper = new ObjectMapper();
        List<Product> list = oMapper.convertValue(products, new TypeReference<List<Product>>() {
        });
        return list;
    }
}
