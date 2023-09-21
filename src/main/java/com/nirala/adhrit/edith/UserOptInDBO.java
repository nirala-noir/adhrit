package com.meta.verse.edith;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "user_opt_in_infos")
public class UserOptInDBO {

    @Id
    private String id;

    @Field("msisdn")
    @Indexed
    private String msisdn;

    @Field("msisdnMasked")
    private String msisdnMasked;

    @Field("msisdnWithoutCountryCode")
    @Indexed
    private String msisdnWithoutCountryCode;

    @Field("msisdnCountryCode")
    @Indexed
    private String msisdnCountryCode;

    @Field("msisdnWithoutCountryCodeMasked")
    private String msisdnWithoutCountryCodeMasked;

    @Field("enterpriseId")
    @Indexed
    private String enterpriseId;

    @Field("enterpriseName")
    private String enterpriseName;

    @Field("appName")
    @Indexed
    private String appName;

    @Field("optInStatus")
    @Indexed
    private int optInStatus = 1;

    @Field("source")
    @Indexed
    private String source;

    @Field("createdOn")
    private Date createdOn;

    @Field("generatedOn")
    @Indexed
    private Date generatedOn = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getOptInStatus() {
        return optInStatus;
    }

    public void setOptInStatus(int optInStatus) {
        this.optInStatus = optInStatus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(Date generatedOn) {
        this.generatedOn = generatedOn;
    }

    public String getMsisdnMasked() {
        return msisdnMasked;
    }

    public void setMsisdnMasked(String msisdnMasked) {
        this.msisdnMasked = msisdnMasked;
    }

    public String getMsisdnWithoutCountryCode() {
        return msisdnWithoutCountryCode;
    }

    public void setMsisdnWithoutCountryCode(String msisdnWithoutCountryCode) {
        this.msisdnWithoutCountryCode = msisdnWithoutCountryCode;
    }

    public String getMsisdnCountryCode() {
        return msisdnCountryCode;
    }

    public void setMsisdnCountryCode(String msisdnCountryCode) {
        this.msisdnCountryCode = msisdnCountryCode;
    }

    public String getMsisdnWithoutCountryCodeMasked() {
        return msisdnWithoutCountryCodeMasked;
    }

    public void setMsisdnWithoutCountryCodeMasked(String msisdnWithoutCountryCodeMasked) {
        this.msisdnWithoutCountryCodeMasked = msisdnWithoutCountryCodeMasked;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
