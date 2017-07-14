package gr.avalanche.mongobatch.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ScheduledTasks Class For Scheduling The Execution Of Spring Batch Jobs et. al.
 * @author npapadopoulos
 */
@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(cron = "0 0 10 1 * ?")
    public void sampleTask() { //TODO Delete This Task
        LOGGER.info("sampleTask() Executed.");
    }
}
