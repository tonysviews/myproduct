package org.meveo.model.customEntities;

import org.meveo.model.CustomEntity;
import java.io.Serializable;
import java.util.List;
import org.meveo.model.persistence.DBStorageType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoanAccount implements CustomEntity, Serializable {

    public LoanAccount() {
    }

    public LoanAccount(String uuid) {
        this.uuid = uuid;
    }

    private String uuid;

    @JsonIgnore()
    private DBStorageType storages;

    private String loanaccountname;

    private String loanid;

    private Double sanctionamount;

    @Override()
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DBStorageType getStorages() {
        return storages;
    }

    public void setStorages(DBStorageType storages) {
        this.storages = storages;
    }

    public String getLoanaccountname() {
        return loanaccountname;
    }

    public void setLoanaccountname(String loanaccountname) {
        this.loanaccountname = loanaccountname;
    }

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public Double getSanctionamount() {
        return sanctionamount;
    }

    public void setSanctionamount(Double sanctionamount) {
        this.sanctionamount = sanctionamount;
    }

    @Override()
    public String getCetCode() {
        return "LoanAccount";
    }
}
