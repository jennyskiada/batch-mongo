package gr.avalanche.mongobatch.batch.tasklets;

import gr.avalanche.mongobatch.domain.Campaign;
import gr.avalanche.mongobatch.persistence.CampaignRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author eskiada
 */
@Component
public class DeleteCampaignTasklet implements Tasklet {

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Campaign> campaigns = campaignRepository.findCampaignsByCampaignId("1");
        if(campaigns != null){
            //Bulk
            campaignRepository.delete(campaigns);
        }
        return RepeatStatus.FINISHED;
    }
}

