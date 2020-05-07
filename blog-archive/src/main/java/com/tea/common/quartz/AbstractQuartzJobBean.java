/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-4-25
 * Time: 上午2:57
 * To change this template use File | Settings | File Templates.
 */
package com.tea.common.quartz;

import com.tea.common.service.JobControlService;
import com.tea.common.service.JobLogService;
import com.tea.util.ExceptionUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.sql.Timestamp;

public abstract class AbstractQuartzJobBean extends QuartzJobBean {
    private static final Log log = LogFactory
            .getLog(AbstractQuartzJobBean.class);

    protected String jobId;
    protected String jobName;

    private JobLogService jobLogService;
    private JobControlService jobControlService;

    protected AbstractQuartzJobBean() {
    }

    @Override
    protected void executeInternal(JobExecutionContext ctx)
            throws JobExecutionException {

        log.info("===== job start...");

        long startTimeMillis = System.currentTimeMillis();
        long endTimeMillis;
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        String jobid = getJobId();
        String jobname = getJobName();

        // 定时任务开始时创建日志
        String jobLogId = jobLogService.jobStart(jobid, jobname, startTime);

        try {
//			if (!jobControlService.isJobProcessing(jobid, jobname)) {
            jobControlService.isJobProcessing(jobid, jobname);
            // 执行任务
            run(ctx);

            // 设置执行状态为未在执行
            jobControlService.setJobFinish(jobid);

            // 定时任务完成时更新日志
            endTimeMillis = System.currentTimeMillis();
            jobLogService.jobFinished(jobLogId,(endTimeMillis - startTimeMillis));
//			} else {
//				endTimeMillis = System.currentTimeMillis();
//				// 写出错日志
//				jobLogService.jobError(jobLogId, "上次定时任务未结束",
//						(endTimeMillis - startTimeMillis));
//			}
        } catch (Exception e) {
            // 设置执行状态为未在执行
            jobControlService.setJobFinish(jobid);
            // 写出错日志
            endTimeMillis = System.currentTimeMillis();
            jobLogService.jobError(jobLogId, ExceptionUtils.getFullStackTrace(e),(endTimeMillis - startTimeMillis));
           e.printStackTrace();
            ExceptionUtil.throwRuntimeException(e);
        } finally {
            endTimeMillis = System.currentTimeMillis();
            log.info("===== job finish,cost:"
                    + (endTimeMillis - startTimeMillis));
        }
    }

    /**
     * 定时任务执行入口
     *
     * @param arg0
     */
    public abstract void run(JobExecutionContext arg0);



    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public JobLogService getJobLogService() {
        return jobLogService;
    }

    public void setJobLogService(JobLogService jobLogService) {
        this.jobLogService = jobLogService;
    }

    public JobControlService getJobControlService() {
        return jobControlService;
    }

    public void setJobControlService(JobControlService jobControlService) {
        this.jobControlService = jobControlService;
    }
}
