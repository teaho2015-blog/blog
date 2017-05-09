/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-5-11
 * Time: 下午8:58
 * To change this template use File | Settings | File Templates.
 */
package com.tea.common.service;

import com.tea.common.dao.JobLogDAO;
import com.tea.common.model.JobLog;
import com.tea.util.UUIDGenerator;

import java.sql.Timestamp;

public class JobLogService {
    private JobLogDAO jobLogDAO;



    /**
     * 定时任务开始时创建日志
     *
     * @param jobId
     * @param jobName
     * @param executeTime
     * @return
     */
    public String jobStart(String jobId, String jobName, Timestamp executeTime) {
        String id = UUIDGenerator.generateUUID();
        JobLog jobLog = new JobLog();
        jobLog.setId(id);
        jobLog.setJobid(jobId);
        jobLog.setJobname(jobName);
        jobLog.setExecutetime(executeTime);
        jobLog.setExecuteflag("01");

        jobLog.setCreator_id("QUARTZ");
        jobLog.setCreator_name("QUARTZ");
        jobLog.setCreator_orgid("QUARTZ");
        jobLog.setCreator_orgname("QUARTZ");
        jobLog.setCreate_time(executeTime);

        jobLog.setUpdater_id("QUARTZ");
        jobLog.setUpdater_name("QUARTZ");
        jobLog.setUpdater_orgid("QUARTZ");
        jobLog.setUpdater_orgname("QUARTZ");
        jobLog.setUpdate_time(executeTime);
        jobLogDAO.create(jobLog);
        return id;
    }


    public void jobFinished(String jobLogId, long executeCount) {
        jobLogDAO.updateExecuteCountById(jobLogId, executeCount);
    }

    /**
     * 定时任务执行出错时更新日志
     *
     */
    public void jobError(String jobLogId, String fullStackTrace, long executeCount) {
        jobLogDAO.updateErrMsg(jobLogId, fullStackTrace, executeCount);
    }

    public JobLogDAO getJobLogDAO() {
        return jobLogDAO;
    }

    public void setJobLogDAO(JobLogDAO jobLogDAO) {
        this.jobLogDAO = jobLogDAO;
    }
}
