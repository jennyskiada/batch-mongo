package gr.avalanche.mongobatch.batch.processors;

import gr.avalanche.mongobatch.domain.Campaign;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author eskiada
 */
@Component
public class CampaignProcessor implements ItemProcessor<Campaign, Campaign>{

    @Override
    public Campaign process(Campaign campaign) throws Exception {
        return campaign.getTokenId() == null ? null : campaign;
    }
}
