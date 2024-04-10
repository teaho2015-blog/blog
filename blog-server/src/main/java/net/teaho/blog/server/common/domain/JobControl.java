package net.teaho.blog.server.common.domain;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * table name:C_JOB_CONTROL
 */
 @Table(name = "C_JOB_CONTROL")
public class JobControl implements RowMapper {
		@Id
		private String id;
		private String jobid;
		private String jobname;
		private String flag;
		private String creator_id;
		private String creator_name;
		private Timestamp create_time;
		private String creator_orgid;
		private String creator_orgname;
		private String updater_id;
		private String updater_name;
		private String updater_orgname;
		private String updater_orgid;
		private Timestamp update_time;
		private String delete_flag;
		private String deleter_id;
		private String deleter_name;
		private String deleter_orgid;
		private String deleter_orgname;
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getJobid(){
		return jobid;
	}
	
	public void setJobid(String jobid){
		this.jobid = jobid;
	}
	
	public String getJobname(){
		return jobname;
	}
	
	public void setJobname(String jobname){
		this.jobname = jobname;
	}
	
	public String getFlag(){
		return flag;
	}
	
	public void setFlag(String flag){
		this.flag = flag;
	}
	
	public String getCreator_id(){
		return creator_id;
	}
	
	public void setCreator_id(String creator_id){
		this.creator_id = creator_id;
	}
	
	public String getCreator_name(){
		return creator_name;
	}
	
	public void setCreator_name(String creator_name){
		this.creator_name = creator_name;
	}
	
	public Timestamp getCreate_time(){
		return create_time;
	}
	
	public void setCreate_time(Timestamp create_time){
		this.create_time = create_time;
	}
	
	public String getCreator_orgid(){
		return creator_orgid;
	}
	
	public void setCreator_orgid(String creator_orgid){
		this.creator_orgid = creator_orgid;
	}
	
	public String getCreator_orgname(){
		return creator_orgname;
	}
	
	public void setCreator_orgname(String creator_orgname){
		this.creator_orgname = creator_orgname;
	}
	
	public String getUpdater_id(){
		return updater_id;
	}
	
	public void setUpdater_id(String updater_id){
		this.updater_id = updater_id;
	}
	
	public String getUpdater_name(){
		return updater_name;
	}
	
	public void setUpdater_name(String updater_name){
		this.updater_name = updater_name;
	}
	
	public String getUpdater_orgname(){
		return updater_orgname;
	}
	
	public void setUpdater_orgname(String updater_orgname){
		this.updater_orgname = updater_orgname;
	}
	
	public String getUpdater_orgid(){
		return updater_orgid;
	}
	
	public void setUpdater_orgid(String updater_orgid){
		this.updater_orgid = updater_orgid;
	}
	
	public Timestamp getUpdate_time(){
		return update_time;
	}
	
	public void setUpdate_time(Timestamp update_time){
		this.update_time = update_time;
	}
	
	public String getDelete_flag(){
		return delete_flag;
	}
	
	public void setDelete_flag(String delete_flag){
		this.delete_flag = delete_flag;
	}
	
	public String getDeleter_id(){
		return deleter_id;
	}
	
	public void setDeleter_id(String deleter_id){
		this.deleter_id = deleter_id;
	}
	
	public String getDeleter_name(){
		return deleter_name;
	}
	
	public void setDeleter_name(String deleter_name){
		this.deleter_name = deleter_name;
	}
	
	public String getDeleter_orgid(){
		return deleter_orgid;
	}
	
	public void setDeleter_orgid(String deleter_orgid){
		this.deleter_orgid = deleter_orgid;
	}
	
	public String getDeleter_orgname(){
		return deleter_orgname;
	}
	
	public void setDeleter_orgname(String deleter_orgname){
		this.deleter_orgname = deleter_orgname;
	}

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobControl jobControl = new JobControl();
				jobControl.setId(rs.getString("id"));
						jobControl.setJobid(rs.getString("jobid"));
						jobControl.setJobname(rs.getString("jobname"));
						jobControl.setFlag(rs.getString("flag"));
						jobControl.setCreator_id(rs.getString("creator_id"));
						jobControl.setCreator_name(rs.getString("creator_name"));
						jobControl.setCreate_time(rs.getTimestamp("create_time"));
						jobControl.setCreator_orgid(rs.getString("creator_orgid"));
						jobControl.setCreator_orgname(rs.getString("creator_orgname"));
						jobControl.setUpdater_id(rs.getString("updater_id"));
						jobControl.setUpdater_name(rs.getString("updater_name"));
						jobControl.setUpdater_orgname(rs.getString("updater_orgname"));
						jobControl.setUpdater_orgid(rs.getString("updater_orgid"));
						jobControl.setUpdate_time(rs.getTimestamp("update_time"));
						jobControl.setDelete_flag(rs.getString("delete_flag"));
						jobControl.setDeleter_id(rs.getString("deleter_id"));
						jobControl.setDeleter_name(rs.getString("deleter_name"));
						jobControl.setDeleter_orgid(rs.getString("deleter_orgid"));
						jobControl.setDeleter_orgname(rs.getString("deleter_orgname"));
						return jobControl;
	}
}
