package com.asiainfo.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.asiainfo.Iservice.IActiveService;
import com.asiainfo.Iservice.IManageService;
import com.asiainfo.Iservice.IUploadService;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.ActionSupport;


@Controller
public class UploadAction extends  ActionSupport{

	private static final long serialVersionUID = 7329615755852421742L;

	public Logger log = Logger.getLogger(UploadAction.class);

	private File userFile;
	private String userFileFileName;//文件名称xzxa 
	private String userFileContentType;
	private String upType;

	private String userGroupName;
	private String groupType;
	@Autowired
	public IUploadService service;
	@Autowired
	private IManageService ms;
	@Autowired
	public IActiveService activeService;
	
	public void upLoadFile() {
		String separator = File.separator;
		String path = ServletActionContext.getServletContext().
				getRealPath(separator)+"upload"+separator+"file"+separator+"ug"+separator;//用户上传路径
		log.info("pathpathpathpath:"+path);
		log.info("userGroupName:"+userGroupName);

		try{
			String newFileName=getActiveCode()+"_pt";
			if(groupType.equals("0")){
				newFileName=UUID.randomUUID().toString().replaceAll("-", "");
			}
			File savefile = new File(new File(path), newFileName);
			log.info("savefile:"+savefile);
			log.info("userFileFileName:"+userFileFileName);
			log.info("userFileContentType:"+userFileContentType);
			log.info("userfile:"+userFile);
			if(userFile!=null){
				if (judgeUploadPhoneCount(userFile)==false){
					System.out.println("超出上限");
					 ServletActionContext.getResponse().getWriter().print("N");
				}else{
				if (!savefile.getParentFile().exists())
		            savefile.getParentFile().mkdirs();
		        log.info("savefile.getParentFile()="+savefile.getParentFile());
		        FileUtils.copyFile(userFile, savefile);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("active_code", getActiveCode());
				map.put("gl_code", newFileName);
				map.put("gl_name", userGroupName);
				map.put("group_type", groupType);
				map.put("city_id", getLoginUser().get("cityId"));
				if("0".equals(groupType)){
					map.put("table_name", "gl_user_group_table");
				}else{
					map.put("table_name", getActiveCode());
				}

				service.uploadUsersService(map);
				int insertCon=0;
		        if(groupType.equals("0")){
					//0为渠道上传,入库操作
		        	insertCon=channelFileToDB(savefile,"channel_upload_info",(String)getLoginUser().get("cityId"),newFileName);
				}else{
					//1为客户群上传，入库操作
					insertCon=groupFileToDB(savefile,getActiveCode(),(String)getLoginUser().get("cityId"));
				}
		        ServletActionContext.getResponse().getWriter().print("Y,"+insertCon+",");
			 }
			}
		}catch(Exception e){
			log.error("upLoadFile error:"+e.getMessage());
			 try {
				ServletActionContext.getResponse().getWriter().print("E");
			} catch (IOException e1) {
			}
		}


	}
    public void getUploadCount() throws Exception{
    	Map<String,Object> map=getLoginUser();
		String cityId=(String)map.get("cityId");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int w=cal.get(Calendar.DAY_OF_WEEK)-1;//周日是1
		if(w<=0)
			w=7;
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("cityId", cityId);
		map1.put("day", w);
//		Map<String,Object> activeInfoMap=activeService.getActiveInfo1(getActiveCode());
//		Date startTime =(Date) activeInfoMap.get("begin_time");
//		Date endTime =(Date) activeInfoMap.get("end_time");
//	    int days=daysBetween(startTime,endTime);
		Map<String,Object> map2= ms.queryCitySendCountByCityId(map1);
		String count ="";
		if(map2!=null)
		count =(String) map2.get("send_count");
	    try {
			ServletActionContext.getResponse().getWriter().print(count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public File glFile;
    public String glUser;//=ucode 过滤客户的识别码 在gl_user_group 中有
    public String glCz;//选择的操作 1：批量删除  2：批量增加 3： 删除文件  4：替换文件
    /**
     * 对需要过滤的客户群进行管理
     */
    public void editGlUserGroup(){
    	String ucode="";
    	String mes="N";
    	String separator = File.separator;
    	String path=ServletActionContext.getServletContext().getRealPath(separator)+"upload"+separator+"file"+separator+"gl"+separator;
    	String newFileName=getActiveCode()+"_gl";
   	 try {
    	File savefile = new File(new File(path), newFileName);
    	log.info("path :"+path);
    	if(glFile!=null){
    		if (!savefile.getParentFile().exists())
	            savefile.getParentFile().mkdirs();
				FileUtils.copyFile(glFile, savefile);
    	 }
        if(glUser!=null){
    		  ucode =glUser;
          }    	
    	if(glCz!=null){
       	  log.info("glCz :"+glCz);
       	   if(glCz.equals("3")){//删除文件
       		   Map<String,Object> map =new HashMap<String, Object>();
       		   map.put("gl_code", ucode);
       		   service.deleteGlGroupName(map);
       		   //删除134数据库上的数据
       		 deleteChannelFileToDB(ucode, "channel_upload_info");
       		   mes="Y";
       	   }
       	   else if(glCz.equals("4")){//替换文件
       		   //先删除134上对应的数据
       		 deleteChannelFileToDB(ucode, "channel_upload_info");
       		   // 再添加新的数据到对应的ucode
       		 if(getActiveCode()!=null && !getActiveCode().equals("") && (String)getLoginUser().get("cityId") !=null && !((String)getLoginUser().get("cityId")).equals("")){    		 
      			int k=channelFileToDB(savefile, getActiveCode(), (String)getLoginUser().get("cityId"), ucode);
      		   log.info("插入新的号码："+k);
       		   mes="Y"; 
      		  } 
       		   mes="Y";
       	     }
           }  
    	 HttpServletResponse response = ServletActionContext.getResponse();
    	 response.setContentType("text/html;charset=UTF-8");
         response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
 	   ServletActionContext.getResponse().getWriter().print(mes);
		} catch (IOException e) {
				// TODO Auto-generated catch block
		   log.info("errorMessage :" +e.getMessage());
		   try {
			   ServletActionContext.getResponse().getWriter().print(mes);
		    } catch (IOException e1) {
			// TODO Auto-generated catch block
			
		  }
		}
    	
    }
//	public static void main(String[] args) {
//		File file=new File("D:\\workspace\\DemoProject\\WebContent\\upload\\file\\ug\\active_201501201122355_pt");
//		try {
//			new UploadAction().groupFileToDB(file,"table_name","2");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public int channelFileToDB(File file,String tableName,String city_id,String uniqueCode) throws IOException{
		List<String> channelList=this.doPhones(file);
		service.insertChannelBatch(channelList,city_id,uniqueCode,tableName);
		return channelList.size();
	}

	public  int groupFileToDB(File file,String tableName,String city_id) throws IOException{
		System.out.println("groupFileToDB invoked....");
		List<String> groupList=this.doPhones(file);
		System.out.println("上传文件号码数量"+groupList.size());
		service.insertGroupBatch(groupList,city_id,tableName);
		return groupList.size();
	}
	
	public void deleteChannelFileToDB(String uniqueCode,String tableName){
		service.deleteChannelBatch(uniqueCode, tableName);
	}

    /**
     * 判断上传文件电话号码是否超过上限
     * @param file
     * @return
     */
    public boolean judgeUploadPhoneCount(File file){
    	boolean b=false;
    	try {
			int amount=doPhones(file).size();
			//获取上限量
			Map<String,Object> map=getLoginUser();
			String cityId=(String)map.get("cityId");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int w=cal.get(Calendar.DAY_OF_WEEK)-1;//周日是1
			if(w<=0)
				w=7;
			System.out.println("今天是星期： "+w);
			Map<String,Object> map1=new HashMap<String,Object>();
			map1.put("cityId", cityId);
			map1.put("day", w);
//			Map<String,Object> activeInfoMap=activeService.getActiveInfo1(getActiveCode());
//			Date startTime =(Date) activeInfoMap.get("begin_time");
//			Date endTime =(Date) activeInfoMap.get("end_time");
//		    int days=daysBetween(startTime,endTime);
//			log.info("between days :"+days);
			Map<String,Object> cityMap=ms.queryCitySendCountByCityId(map1);		
			if(amount>(Integer.valueOf((String) cityMap.get("send_count")))){
				b=false;
			}
//			if (amount>10) b=false;
			else b=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	return b;
    }
	public List<String> doPhones(File file) throws IOException{
		System.out.println("doPhones invoked...");
		List<String> phoneList=new ArrayList<String>();
		BufferedReader br=new BufferedReader(new FileReader(file));
		while(true){
			String phone=br.readLine();
			if(phone==null || phone.equals("")){
				break;
			}
			phoneList.add(phone.trim());
			System.out.println("phone:"+phone);
		}
		System.out.println("111");
		br.close();
		System.out.println("222");
		return phoneList;
	}

	public void isRuserGroupName() throws IOException{
		Map<String,String> map=new HashMap<String,String>();
		map.put("gl_name", userGroupName);
		String s=service.isRuserGroupName(map);
		ServletActionContext.getResponse().getWriter().print(s);
	}


	public String glUserGroup(){
		@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("perUser");
		String cityId=(String) map.get("cityId");
		ServletActionContext.getRequest().setAttribute("glkhq", service.getGlUserGroup(cityId));
		return "glUserGroup";
	}
	public String glUserGroup1(){
		@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("perUser");
		String cityId=(String) map.get("cityId");
		ServletActionContext.getRequest().setAttribute("glkhq", service.getGlUserGroup(cityId));
		return "glUserGroup1";
	}


	public Map<String,Object> getLoginUser(){
		@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("perUser");
		return map;
	}

	private String userGroup;
	public void subGlUserGroup() throws IOException{

		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", getActiveCode());
		map.put("userGroup", StringUtils.convertToUTF(URLDecoder.decode(userGroup, "iso-8859-1")));
		try {
			if(service.insertGlUserGroup(map)){
				ServletActionContext.getResponse().getWriter().print("Y");
			}else{
				ServletActionContext.getResponse().getWriter().print("N");
			}
		} catch (IOException e) {
		}
	}
	 public  int daysBetween(Date smdate,Date bdate) throws ParseException    
	    {    
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        smdate=sdf.parse(sdf.format(smdate));  
	        bdate=sdf.parse(sdf.format(bdate));  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days))+1;           
	    }   
	public String getActiveCode(){
		return ServletActionContext.getRequest().getParameter("activeCode");
	}
	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public String getUpType() {
		return upType;
	}

	public void setUpType(String upType) {
		this.upType = upType;
	}

	public String getUserFileFileName() {
		return userFileFileName;
	}

	public void setUserFileFileName(String userFileFileName) {
		this.userFileFileName = userFileFileName;
	}

	public String getUserFileContentType() {
		return userFileContentType;
	}

	public void setUserFileContentType(String userFileContentType) {
		this.userFileContentType = userFileContentType;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		try {
			this.userGroupName = StringUtils.convertToUTF(URLDecoder.decode(userGroupName, "iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
		}
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public File getGlFile() {
		return glFile;
	}
	public void setGlFile(File glFile) {
		this.glFile = glFile;
	}
	public String getGlUser() {
		return glUser;
	}
	public void setGlUser(String glUser) {
		this.glUser = glUser;
	}
	public String getGlCz() {
		return glCz;
	}
	public void setGlCz(String glCz) {
		this.glCz = glCz;
	}

}
