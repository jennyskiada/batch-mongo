package gr.avalanche.mongobatch.batch.mappers;

import gr.avalanche.mongobatch.domain.Campaign;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

/**
 * @author eskiada
 */
@Component
public class CampaignFieldSetMapper implements FieldSetMapper<Campaign>{

    @Override
    public Campaign mapFieldSet(FieldSet fieldSet) throws BindException {
        Campaign result = new Campaign();
        result.setAccountNumber(fieldSet.readString("accountNumber"));
        result.setAfm(fieldSet.readString("afm"));
        result.setCli(fieldSet.readString("cli"));
        result.setCustomerCode(fieldSet.readString("customerCode"));
        result.setGuid(fieldSet.readString("guid"));
        result.setMsisdn(fieldSet.readString("msisdn"));
        result.setUcmId(fieldSet.readString("ucmId"));
        result.setTokenId(fieldSet.readString("tokenId"));
        return result;
    }
}
