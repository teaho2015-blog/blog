/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-5-11
 * Time: 下午9:29
 * To change this template use File | Settings | File Templates.
 */
package net.teaho.blog.server.common.dao;

import net.teaho.blog.server.common.model.JobLog;
import net.teaho.blog.server.util.UUIDGenerator;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.sql.Types;

public class JobLogDAO {
    private JdbcTemplate jdbcTemplate;

    /**
     * @param model
     * 新增记录
     */
    public JobLog create(final JobLog model){
        String sql = "insert into C_JOB_LOG(id,jobid,jobname,executeflag,executetime,completetime,executecount,errormsg,creator_id,creator_name,create_time,creator_orgid,creator_orgname,updater_id,updater_name,updater_orgname,updater_orgid,update_time,delete_flag,deleter_id,deleter_name,deleter_orgid,deleter_orgname) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        if ("".equals(model.getId()) || null == model.getId()) {
            model.setId(UUIDGenerator.generateUUID());
        }
        jdbcTemplate.update(sql,new Object[]{
                model.getId(),
                model.getJobid(),
                model.getJobname(),
                model.getExecuteflag(),
                model.getExecutetime(),
                model.getCompletetime(),
                model.getExecutecount(),
                model.getErrormsg(),
                model.getCreator_id(),
                model.getCreator_name(),
                model.getCreate_time(),
                model.getCreator_orgid(),
                model.getCreator_orgname(),
                model.getUpdater_id(),
                model.getUpdater_name(),
                model.getUpdater_orgname(),
                model.getUpdater_orgid(),
                model.getUpdate_time(),
                model.getDelete_flag(),
                model.getDeleter_id(),
                model.getDeleter_name(),
                model.getDeleter_orgid(),
                model.getDeleter_orgname()
        },new int[]{
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.TIMESTAMP,
                Types.TIMESTAMP,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.TIMESTAMP,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.TIMESTAMP,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR
        });
        return model;
    }

    /**
     * 定时任务完成时更新日志
     *
     */
    public void updateExecuteCountById(String id, long executecount) {
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        String sql = "update c_job_log set completetime=?,update_time=?,executecount=?,executeflag='02'  where id=?";
        jdbcTemplate.update(sql, new Object[]{endTime, endTime, executecount,
                id}, new int[]{Types.TIMESTAMP, Types.TIMESTAMP,
                Types.VARCHAR, Types.VARCHAR});
    }

    public void updateErrMsg(String jobLogId, String fullStackTrace, long executeCount) {
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        String sql = "update c_job_log set completetime=?,executeflag='03',errormsg=?,update_time=?,executecount=? where id=?";
        jdbcTemplate.update(sql, new Object[] { endTime, fullStackTrace, endTime,
                executeCount, jobLogId }, new int[] { Types.TIMESTAMP, Types.CLOB,
                Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR });
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
