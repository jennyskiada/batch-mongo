package gr.avalanche.mongobatch.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Campaign Domain Object
 * @author eskiada
 */
@Document(collection = "notifications.campaign")
public class Campaign {

    @Id
    private String id;
    private String tokenId;
    private String cli;
    private String guid;
    private String msisdn;
    private String accountNumber;
    private String customerCode;
    private String afm;
    private String ucmId;

    private String campaignId ="1";

    public String getCampaignId() {return campaignId;}

    public void setCampaignId(String campaignId) {this.campaignId = campaignId;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getTokenId() {return tokenId;}

    public void setTokenId(String tokenId) {this.tokenId = tokenId;}

    public String getCli() {return cli;}

    public void setCli(String cli) {this.cli = cli;}

    public String getGuid() {return guid;}

    public void setGuid(String guid) {this.guid = guid;}

    public String getMsisdn() {return msisdn;}

    public void setMsisdn(String msisdn) {this.msisdn = msisdn;}

    public String getAccountNumber() {return accountNumber;}

    public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}

    public String getCustomerCode() {return customerCode;}

    public void setCustomerCode(String customerCode) {this.customerCode = customerCode;}

    public String getAfm() {return afm;}

    public void setAfm(String afm) {this.afm = afm;}

    public String getUcmId() {return ucmId;}

    public void setUcmId(String ucmId) {this.ucmId = ucmId;}

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("tokenId", tokenId)
            .append("cli", cli)
            .append("guid", guid)
            .append("msisdn", msisdn)
            .append("accountNumber", accountNumber)
            .append("customerCode", customerCode)
            .append("afm", afm)
            .append("ucmId", ucmId)
            .toString();
    }
}
