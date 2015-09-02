package com.asiainfo.action;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.Iservice.IActiveService;
import com.asiainfo.Iservice.IUserGroupService;
import com.asiainfo.bean.UserGroupInfo;
import com.asiainfo.util.DataBaseJdbc;
import com.asiainfo.util.PasswordUtil;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserGroupAction extends ActionSupport {
	public Logger log = Logger.getLogger(UserGroupAction.class);
	@Autowired
	public IActiveService aservice;
	private UserGroupInfo userGroupInfo;

	@Autowired
	private IUserGroupService userGroupService;

	public UserGroupInfo getUserGroupInfo() {
		return userGroupInfo;
	}

	public void setUserGroupInfo(UserGroupInfo userGroupInfo) {
		this.userGroupInfo = userGroupInfo;
	}

	public IUserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	@Override
	//查询所有标签，并拼接html
	public String execute() throws Exception {
		Map<String, List<String>> map = userGroupService.selectLabel();
		HttpServletRequest request = ServletActionContext.getRequest();
		//基础属性类html拼接
		String htmla = "<table>";
		List<String> lista = map.get("a");
		for (int i = 0; i < lista.size(); i++) {
			htmla += "<tr><td>"
					+ "<input type='checkbox' name='"
					+ lista.get(0) + "' value='"
					+ lista.get(0) + "'>"
					+ lista.get(0)
					+ "</input>"
					+ ": <label id='opr_" + lista.get(0) + "'>=</label>"
					+ " <select id='val_" + lista.get(0) + "'><option value=''></option><option value='qq'>全球通</option>"
					+ "<option value='sz'>神州行</option><option value='dt'>动感地带</option></select>"
					+ "</td></tr>";
		}
		htmla += "</table>";

		//用户价值类html拼接
		String htmlb = "<table>";
		List<String> listb = map.get("b");
		for (int i = 0; i < listb.size(); i++) {
			htmlb += "<tr><td>"
					+ "<input type='checkbox' name='"
					+ listb.get(0) + "' value='"
					+ listb.get(0) + "'>"
					+ listb.get(0)
					+ ": <select id='opr_" + listb.get(0)+ "'><option value='>'>大于</option>"
					+ " <option value='<'>小于</option><option value='='>等于</option></select>"
					+ "<input type='textfield' id='val_" + listb.get(0) + "'/>"
					+ "</td></tr>";
		}
		htmlb += "</table>";


		//设置返回字符串
		request.setAttribute("htmla", htmla);
		request.setAttribute("htmlb", htmlb);
		return "showList";
	}
	//查询用户群
	public void searchGroup() {
		HttpServletResponse response = ServletActionContext.getResponse();
		String result = "";
		List<String> list = userGroupService.selectGroup();
		for(int i=0;i<list.size();i++){
			if (i!= list.size()-1){
				result += list.get(i) + "&";
			}else {
				result += list.get(i);
			}
		}
		System.out.println(result);
		try {
			response.getWriter().print(StringUtils.convertToISO(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//新增客户群插入
	public void userGroupInsert() {
		HttpServletRequest req = ServletActionContext.getRequest();
		Map<String, String> map = new HashMap<String, String>();
		String groupName = StringUtils.convertToUTF(req.getParameter("groupName"));
		String sql = req.getParameter("sql");
		//System.out.println(sql);
		map.put("group_name", groupName);
		map.put("search_sql", sql);

		userGroupService.insertGroup(map);

	}
	//
	public void searchGroupNameByCode() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String activeCode = getActiveCode();
		String result = "";
		List<UserGroupInfo> list = userGroupService.select(activeCode);
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				result += list.get(i).getGroupName() + "&";
			} else {
				result += list.get(i).getGroupName();
			}
		}
		System.out.println(result);
		try {
			response.getWriter().print(StringUtils.convertToISO(result));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/*
	 * public void selectType() { HttpServletResponse response =
	 * ServletActionContext.getResponse(); String result = ""; List<String> list
	 * = userGroupService.selectType(); for(int i=0;i<list.size();i++){ if (i!=
	 * list.size()-1){ result += list.get(i) + "&"; }else { result +=
	 * list.get(i); } } System.out.println(result); try {
	 * response.getWriter().print(StringUtils.convertToISO(result)); } catch
	 * (IOException e) { e.printStackTrace(); } }
	 */
	//新增客户群各属性插入
	public void userGroupValueInsert(){
		HttpServletRequest req = ServletActionContext.getRequest();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();

		String groupName = StringUtils.convertToUTF(req.getParameter("groupName"));
		String value = req.getParameter("attr_value");
		//System.out.println(value);
		String[] s = value.split(",,");
		for(String temp : s){
			Map<String, String> map = new HashMap<String, String>();
			map.put("group_name", groupName);
			String[] s2 = temp.split(",");
			map.put("column_name", s2[0]);
			map.put("operator", s2[1]);
			map.put("value", s2[2]);
			list.add(map);
		}

		userGroupService.insertGroupValue(list);
	}

	private String dataBaseS;

	//选中客户群插入
	public void selectedUserGroupInsert() throws IOException{
		HttpServletRequest req = ServletActionContext.getRequest();
		String activeCode = getActiveCode();
		String groupName = StringUtils.convertToUTF(URLDecoder.decode(req.getParameter("groupName"), "iso-8859-1"));
		String searchSql=StringUtils.convertToUTF(URLDecoder.decode(req.getParameter("searchsql"), "iso-8859-1"));
		String customNum=req.getParameter("customNum");

		//判断是否已经选择用户群
		if(userGroupService.isSubUserGroup(activeCode)){
			ServletActionContext.getResponse().getWriter().print("R");
		}else{
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map  = new HashMap<String, String>();
			map.put("active_code", activeCode);
			map.put("group_name", groupName);
			map.put("searchSql", searchSql);
			map.put("customNum", customNum);
			map.put("dataBaseS", dataBaseS);
			list.add(map);
			if(userGroupService.insert(list)){
				ServletActionContext.getResponse().getWriter().print("Y");
			}else{
				ServletActionContext.getResponse().getWriter().print("N");
			}
		}
	}

	private String getActiveCode(){
		return ServletActionContext.getRequest().getParameter("activeCode");
	}

	@Autowired
	private DataBaseJdbc db2;

	private Map<String,Object> getUserMap(){
		@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("perUser");
		return map;
	}

	@Autowired
	IUserGroupService service;

	/**
	 * 获取登入标签库连接方法
	 * @throws Exception
	 */
	public void getUserGroupParamUrl() throws Exception{
		db2.getJdbcParam();
		Connection conn=db2.get134DB2Connection();
		Statement stmt=conn.createStatement();
		String sql="SELECT current timestamp nowtime FROM sysibm.sysdummy1";
		ResultSet set=stmt.executeQuery(sql);
		String nowtime="";
		//获取134时间戳
		while(set.next()) {
			nowtime=set.getString("nowtime");
		}
		set.close();
		stmt.close();
		conn.close();
		//根据vgop登入账号，获取标签库登入用户名密码
		
//		Map<String,String> map=service.getBqkUserInfo((String)getUserMap().get("userId"));
//		if(map!=null){
//			user=map.get("bq_user");
//			password=map.get("bq_password");
//		}else{
//			user="master";
//			password="A275FEA8BD5FD244ADCA57A6DF5AC12A";
//		}

		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString();//获得本机IP
		String port=ServletActionContext.getRequest().getLocalPort()+"";
		String ipAdd=ip+":"+port;

		
		//user="master";
		//password="A275FEA8BD5FD244ADCA57A6DF5AC12A";		
	
		
		db2.getJdbcParam();
		Connection con=db2.get53DB2Connection();
		Statement st=con.createStatement();
		String userId=(String)getUserMap().get("userId");
		String user = "vgop"+userId;
		String sqll="select * from kpi.power_user_info where login_no='"+user+"'";
		ResultSet result=st.executeQuery(sqll);
		//String user="";
		String password="";
		while(result.next()) {
			//user=result.getString("bq_user");
			password=result.getString("password");
		}
		result.close();
		st.close();
		con.close();
		
		log.info("获取134db2  当前时间（用于加密）："+nowtime+"ipAddipAdd="+ipAdd);
		//String url="master&pwd=4EF94B6F181E904F797BF613DE1C113B"; vgop"+getUserMap().get("userId")+
		//String param = "loginNo=master&pwd=A275FEA8BD5FD244ADCA57A6DF5AC12A&ip_add="+ipAdd+"&op_time="+nowtime;
		String param = "loginNo="+user+"&pwd="+password+"&ip_add="+ipAdd+"&op_time="+nowtime;
		String entParam = PasswordUtil.encrypt(param);
		ServletActionContext.getResponse().getWriter().print(entParam);
	}

	public String getDataBaseS() {
		return dataBaseS;
	}

	public void setDataBaseS(String dataBaseS) {
		this.dataBaseS = dataBaseS;
	}


}
