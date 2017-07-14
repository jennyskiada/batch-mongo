package gr.avalanche.mongobatch.persistence;

import gr.avalanche.mongobatch.domain.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Campaing Repository Interface
 * @author eskiada
 */
public interface CampaignRepository extends MongoRepository<Campaign, Long>{

    Campaign findCampaignByCampaignId(String campaignId);

    List<Campaign> findCampaignsByCampaignId(String campaignId);
}

