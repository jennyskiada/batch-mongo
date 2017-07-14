package gr.avalanche.mongobatch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BatchService Implementation
 * @author npapadopoulos
 */
@Service
public class BatchServiceImpl implements BatchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchServiceImpl.class);

    @Autowired
    private JobExplorer jobExplorer;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getJobNames() {
        return jobExplorer.getJobNames();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobExecution> getJobExecutions(long jobInstanceId) {
        JobInstance jobInstance = jobExplorer.getJobInstance(jobInstanceId);
        return jobExplorer.getJobExecutions(jobInstance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StepExecution> getStepExecutions(long jobExecutionId) {
        return (List<StepExecution>) jobExplorer.getJobExecution(jobExecutionId).getStepExecutions();
    }

    @Override
    public List<JobInstance> getJobInstances(String jobName, int start, int count) {
        return jobExplorer.getJobInstances(jobName, start, count);
    }

    @Override
    public int jobInstanceCount(String jobName) {
        int count = 0;
        try {
            count = jobExplorer.getJobInstanceCount(jobName);
        } catch (NoSuchJobException exception){
            LOGGER.error("Exception", exception);
        }
        return count;
    }
}
