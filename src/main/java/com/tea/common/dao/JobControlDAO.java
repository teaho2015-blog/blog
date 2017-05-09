/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-5-11
 * Time: 下午9:32
 * To change this template use File | Settings | File Templates.
 */
package com.tea.common.dao;

import com.tea.common.model.JobControl;
import com.tea.util.UUIDGenerator;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Types;
import java.util.List;

public class JobControlDAO {
    private JdbcTemplate jdbcTemplate;

    public JobControl create(JobControl model) {
        String sql = "insert into C_JOB_CONTROL(id,jobid,jobname,flag,creator_id,creator_name,create_time,creator_orgid,creator_orgname,updater_id,updater_name,updater_orgname,updater_orgid,update_time,delete_flag,deleter_id,deleter_name,deleter_orgid,deleter_orgname) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        if ("".equals(model.getId()) || null == model.getId()) {
            model.setId(UUIDGenerator.generateUUID());
        }
        jdbcTemplate.update(sql, new Object[]{
                model.getId(),
                model.getJobid(),
                model.getJobname(),
                model.getFlag(),
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
        }, new int[]{
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

    public List queryForList(String sql, Object[] args) {
        return getJdbcTemplate().query(sql, args, createColumnMapRowMapper());
    }

    /**
     * Create a new RowMapper for reading columns as key-value pairs.
     *
     * @return the RowMapper to use
     * @see org.springframework.jdbc.core.ColumnMapRowMapper
     */
    protected RowMapper createColumnMapRowMapper() {
        return new ColumnMapRowMapper();
    }

    /**
     * 根据jobid设置定时任务的状态为正在执行
     *
     * @param jobid
     */
    public void setJobProcessing(String jobid) {
        String sql = "update c_job_control t set flag='1' where jobid=? and flag='0'";
        jdbcTemplate.update(sql, new Object[]{jobid},
                new int[]{Types.VARCHAR});
    }

    /**
     * 根据jobid设置定时任务的状态为未执行
     *
     * @param jobid
     */
    public void setJobFinish(String jobid) {
        String sql = "update c_job_control t set flag='0' where jobid=? and flag='1'";
        jdbcTemplate.update(sql, new Object[]{jobid},
                new int[]{Types.VARCHAR});
    }

    public void update(String jobid) {
        String sql = "update c_job_control t set flag='0' where jobid=? and flag='1'";
        jdbcTemplate.update(sql, new Object[] { jobid },
                new int[] { Types.VARCHAR });
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
