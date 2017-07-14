package gr.avalanche.mongobatch.batch.writers;

import gr.avalanche.mongobatch.domain.Campaign;
import gr.avalanche.mongobatch.persistence.CampaignRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author eskiada
 */
@Component
public class CampaignWriter implements ItemWriter<Campaign>{

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public void write(List<? extends Campaign> campaigns) throws Exception {
        //Bulk!
        campaignRepository.save(new java.util.ArrayList<Campaign>(campaigns));
    }
}
