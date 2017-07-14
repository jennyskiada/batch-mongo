package gr.avalanche.mongobatch.configuration.batch;

import gr.avalanche.mongobatch.batch.mappers.CampaignFieldSetMapper;
import gr.avalanche.mongobatch.batch.processors.CampaignProcessor;
import gr.avalanche.mongobatch.batch.tasklets.DeleteCampaignTasklet;
import gr.avalanche.mongobatch.batch.writers.CampaignWriter;
import gr.avalanche.mongobatch.domain.Campaign;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author eskiada
 */
@Configuration
public class FileReadingJob {

    @Autowired
    private CampaignFieldSetMapper campaignFieldSetMapper;

    @Autowired
    private CampaignProcessor campaignProcessor;

    @Autowired
    private CampaignWriter campaignWriter;

    @Autowired
    private DeleteCampaignTasklet deleteCampaignTasklet;

    @Autowired
    private JobBuilderFactory jobFilterFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job readFileJob(){
        return jobFilterFactory.get("fileReadJob")
            .incrementer(new RunIdIncrementer())
            .start(deleteCampaignsStep())
            .next(fileReadingStep())
            .build();
    }

    @Bean
    public Step deleteCampaignsStep(){
        return stepBuilderFactory.get("deleteCampaignsStep")
            .tasklet(deleteCampaignTasklet)
            .build();
    }

    @Bean
    public Step fileReadingStep(){
        return stepBuilderFactory.get("fileReadStep")
            .<Campaign, Campaign>chunk(500)
            .reader(reader())
            .processor(campaignProcessor)
            .writer(campaignWriter)
            .build();
    }

    @Bean
    public org.springframework.batch.item.ItemReader<Campaign> reader(){
        FlatFileItemReader<Campaign> reader = new FlatFileItemReader<Campaign>();
        reader.setResource(new ClassPathResource("files/customers.csv"));
        reader.setLineMapper(lineMapper());
        reader.setLinesToSkip(1);//Skip The Header
        reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        return reader;
    }

    @Bean
    public LineMapper lineMapper(){
        DefaultLineMapper<Campaign> lineMapper = new DefaultLineMapper<Campaign>();
        lineMapper.setLineTokenizer(lineTokenizer());
        lineMapper.setFieldSetMapper(campaignFieldSetMapper);
        return lineMapper;
    }

    @Bean
    public LineTokenizer lineTokenizer(){
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames(new String[]{"tokenId","cli","guid","msisdn","accountNumber","customerCode","afm","ucmId"});
        return lineTokenizer;
    }

}
