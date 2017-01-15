package com.hlideal.common.session;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SessionLocal implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userid = "";

	private String username = "";

	private String usertype = "";

	private String userphone = "";

	private String useremail = "";

	private String truename = "";

	private String companyid = "";

	private String companyname = "";

	private String rolenames = "";

	private String roleids = "";

	private String remoteaddr = null;

	private String deviceid = "";

	private String devicetoken = "";

	private String cerno = "";

	private Date lastDate = null;
	private Map<String, Object> attibuteMap = new HashMap<String, Object>();

	public void addAttibute(String key, Object object) {
		attibuteMap.put(key, object);
	}

	public Object getAttibute(String key) {
		return attibuteMap.get(key);
	}

	public Object removeObject(String key) {
		return attibuteMap.remove(key);
	}

	public void setAttibuteMap(Map<String, Object> attibuteMap) {
		this.attibuteMap = attibuteMap;
	}

	public Map<String, Object> getAttibuteMap() {
		return this.attibuteMap;
	}

	public SessionLocal() {
	}

	public SessionLocal(String userid, String username, String useremail, String truename, String companyid, String companyname, String rolenames, String roleids, String remoteaddr) {
		this.userid = userid;
		this.username = username;
		this.useremail = useremail;
		this.truename = truename;
		this.companyid = companyid;
		this.companyname = companyname;
		this.rolenames = rolenames;
		this.roleids = roleids;
		this.remoteaddr = remoteaddr;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getRolenames() {
		return rolenames;
	}

	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}

	public String getRemoteaddr() {
		return remoteaddr;
	}

	public void setRemoteaddr(String remoteaddr) {
		this.remoteaddr = remoteaddr;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getDevicetoken() {
		return devicetoken;
	}

	public void setDevicetoken(String devicetoken) {
		this.devicetoken = devicetoken;
	}

	public String getCerno() {
		return cerno;
	}

	public void setCerno(String cerno) {
		this.cerno = cerno;
	}

}
