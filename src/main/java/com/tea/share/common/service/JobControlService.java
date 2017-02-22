/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-5-11
 * Time: 下午8:58
 * To change this template use File | Settings | File Templates.
 */
package com.tea.share.common.service;

import com.tea.frame.util.DateUtil;
import com.tea.share.common.dao.JobControlDAO;
import com.tea.share.common.model.JobControl;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class JobControlService {
    private JobControlDAO jobControlDAO;
    public static String JOBCONTROL_FLAG_RUNNING = "1";

    /**
     * 根据jobid判断是否有定时任务在执行。有定时任务在执行则返回 true，否则返回false
     *
     * @return
     */
    public boolean isJobProcessing(String jobId, String jobName) {
        String selectSql = "select flag from c_job_control where jobid=? for update";
        List list = jobControlDAO.queryForList(selectSql, new Object[] { jobId });
        if (null == list || list.size() < 1) {
            Timestamp nowTime = DateUtil.getCurrentTimestamp();
            String user = "QUARTZ";
            JobControl model = new JobControl();
            model.setJobid(jobId);
            model.setJobname(jobName);
            model.setFlag(JOBCONTROL_FLAG_RUNNING);
            model.setCreate_time(nowTime);
            model.setCreator_id(user);
            model.setCreator_name(user);
            model.setCreator_orgid(user);
            model.setCreator_orgname(user);
            model.setUpdate_time(nowTime);
            model.setUpdater_id(user);
            model.setUpdater_name(user);
            model.setUpdater_orgid(user);
            model.setUpdater_orgname(user);
            model.setDelete_flag("0");
            jobControlDAO.create(model);
            return false;
        } else {
            Map map = (Map) list.get(0);
            String flag = (String) map.get("flag");
            if (JOBCONTROL_FLAG_RUNNING.equals(flag)) {
                //正在执行时返回true
                return true;
            } else {
                //未执行时放回false，同时修改状态为正在执行
                jobControlDAO.setJobProcessing(jobId);
                return false;
            }
        }
    }



    public void setJobFinish(String jobid) {
        jobControlDAO.update(jobid);
    }

    public JobControlDAO getJobControlDAO() {
        return jobControlDAO;
    }

    public void setJobControlDAO(JobControlDAO jobControlDAO) {
        this.jobControlDAO = jobControlDAO;
    }
}
