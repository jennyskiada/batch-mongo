package gr.avalanche.mongobatch.service;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepExecution;

import java.util.List;

/**
 * BatchService Interface
 * @author npapadopoulos
 */
public interface BatchService {

    /**
     * Get A List Of The Registered Job Names
     * @return List Of Job Names
     */
    List<String> getJobNames();

    /**
     * Get A List Of Job Executions Given A JobInstanceId
     * @param jobInstanceId The Job's Instance Id
     * @return List Of Job Executions
     */
    List<JobExecution> getJobExecutions(long jobInstanceId);

    /**
     * Get A List Of Step Executions Given A JobExecutionId
     * @param jobExecutionId The Job's Execution Id
     * @return List Of Step Executions
     */
    List<StepExecution> getStepExecutions(long jobExecutionId);

    List<JobInstance> getJobInstances(String jobName, int start, int count);

    int jobInstanceCount(String jobName);
}
