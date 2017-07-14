package gr.avalanche.mongobatch.controllers;

import gr.avalanche.mongobatch.service.BatchService;
import gr.avalanche.mongobatch.utils.Constants;
import gr.avalanche.mongobatch.utils.Pager;
import gr.avalanche.mongobatch.utils.ApplicationUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Batch Controller Class
 * @author eskiada, npapadopoulos
 */
@Controller
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private JobLauncher asynchronousJobLauncher;

    @RequestMapping(value="/launch")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String launchJob(@RequestParam("jobName") String jobName, HttpServletRequest request, RedirectAttributes redirectAttributes) throws NoSuchJobException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        asynchronousJobLauncher.run( //Asynchronous Job Launcher In Order To Not Monopolize The Web Container's Thread
            jobRegistry.getJob(request.getParameter("jobName")),
            getJobParameters(request)
        );
        redirectAttributes.addFlashAttribute(Constants.SUCCESS_MESSAGE_LABEL, "Job With Name '" + jobName + "' Launched Successfully.");
        return Constants.HOME_PAGE_REDIRECT;
    }

    @RequestMapping("/jobs")
    public String jobs(Model model){
        model.addAttribute("jobNames", batchService.getJobNames());
        return Constants.JOBS;
    }

    @RequestMapping("/jobInstances")
    public String jobInstances (@RequestParam("jobName") String jobName,
            @RequestParam(value = "startFrom", required = false) String startFrom,
            @RequestParam(value ="pageSize", required = false) String resultsPerPage,
            Model model, HttpServletRequest request){
        int resultsStartFrom = ApplicationUtils.toInt(startFrom, Constants.START_FROM);
        int pageSize = ApplicationUtils.toInt(resultsPerPage, Constants.PAGE_SIZE);
        model.addAttribute("pager", new Pager(request.getRequestURI(), pageSize, resultsStartFrom, batchService.jobInstanceCount(jobName), ApplicationUtils.buildRequestParameters(request)));
        model.addAttribute("jobName", jobName);
        model.addAttribute("jobInstances", batchService.getJobInstances(jobName, resultsStartFrom, pageSize));
        return Constants.JOB_INSTANCES_PAGE;
    }

    @RequestMapping("/jobExecutions")
    public String jobExecutions(@RequestParam("jobName") String jobName, @RequestParam("jobInstanceId") long jobInstanceId, Model model) {
        model.addAttribute("jobName", jobName);
        model.addAttribute("jobExecutions", batchService.getJobExecutions(jobInstanceId));
        return Constants.JOB_EXECUTIONS_PAGE;
    }

    @RequestMapping("/stepExecutions")
    public String stepExecutions(@RequestParam("jobName") String jobName, @RequestParam("jobExecutionId") long jobExecutionId, Model model) { //jobInstanceId Not Show Here But It's Required For The Breadcrumb
        model.addAttribute("jobName", jobName);
        model.addAttribute("stepExecutions", batchService.getStepExecutions(jobExecutionId));
        return Constants.STEP_EXECUTIONS_PAGE;
    }

    /**
     * Create JobParameters Object From The HttpServletRequest's Parameters
     * @param request HttpServletRequest
     * @return JobParameters
     */
    private JobParameters getJobParameters(HttpServletRequest request) {
        JobParametersBuilder builder = new JobParametersBuilder();
        Map<String, String> requestParameters = new LinkedHashMap<String, String>();
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()) {
            String name = names.nextElement();
            requestParameters.put(name, request.getParameter(name));
        }
        return builder.toJobParameters();
    }
}
