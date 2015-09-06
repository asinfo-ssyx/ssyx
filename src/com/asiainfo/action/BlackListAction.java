package com.asiainfo.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.asiainfo.Iservice.BlackListService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

@Controller
public class BlackListAction implements Action {
	
	public Logger log = Logger.getLogger(BlackListAction.class);
	public String phoneNumber;	
	/**
	 * 备注
	 */
	public String descinfo;
	//类别：1：黑名单；2：白名单；3：红名单；4：测试号码
	public Integer type;
	//显示记录条数
	public Integer showNum;
	//显示的页数
	public Integer pageNum;
	//操作消息
	public String message;
	// myFile属性用来封装上传的文件  
    private File myFile;      
    // myFileContentType属性用来封装上传文件的类型  
    private String myFileContentType;  
    // myFileFileName属性用来封装上传文件的文件名  
    private String myFileFileName;  

	public  String[] phoneNumbers;
	@Autowired
	public BlackListService blackListService;
	/* 
	 * 显示黑名单和查询黑名单
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.Action#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("black list ....");
		System.out.println("showNum"+showNum);
		System.out.println("pageNum"+pageNum);
		Map<String,Object> map=new HashMap<String,Object>();
		if(phoneNumber !=null)System.out.println("phoneNumber :"+phoneNumber);
		HttpServletRequest  request = ServletActionContext.getRequest();
		map.put("phoneNumber", phoneNumber);
		map.put("showNum", showNum);
		map.put("pageNum", pageNum);
		List<Map<String,Object>> blackList= blackListService.showBlackList(map);
		Map<String,Object> page =blackListService.queryBlackListCount(map);
		System.out.println(blackList.size());
		request.setAttribute("blackList", blackList);
		request.setAttribute("page", page);
		return "blackListTable";
	}
	
	/**
	 * 插入一个电话号码到黑名单
	 * @return
	 * @throws Exception
	 */
	public void insert() throws Exception{	
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> map1=new HashMap<String,Object>();
		if(phoneNumber !=null)System.out.println("phoneNumber :"+phoneNumber);
		map.put("phoneNumber", phoneNumber);
		map.put("descinfo",descinfo );
		map.put("type",type);
		map1.put("showNum", 15);
		map1.put("pageNum",1);
		map1.put("phoneNumber", phoneNumber);
		List<Map<String,Object>>  list=blackListService.showBlackList(map1);
		if(list.size()>0){
			message="电话号码已存在！";
		}
		else{
			blackListService.insertUserToBlackList(map);
			message="添加成功！";
		}
		HttpServletResponse response  =ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
	     response.getWriter().print(message);
	 	} 
	
	/**
	 * 批量删除黑名单
	 * @return
	 * @throws Exception
	 */
	public void delete() throws Exception{
		System.out.println("delete is visited");
		HttpServletRequest  request = ServletActionContext.getRequest();
		String phones=request.getParameter("phones");
		System.out.println("phones :"+phones);
		System.out.println("json qian");
		Gson gs = new Gson();
		phoneNumbers=gs.fromJson(phones, String[].class);
		for(int i=0;i<phoneNumbers.length;i++){
			blackListService.deleteUserFromBlackList(phoneNumbers[i]);
		}	
		HttpServletResponse response  =ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
	    response.getWriter().print("删除成功!");
	}
	/**
	 * 读取txt文件将数据导入黑名单
	 * @return
	 * @throws Exception
	 */
	public String leadInData() throws Exception{
		String separator = File.separator;
		Map<String,Object> map1=new HashMap<String,Object>();
		HttpServletRequest  request = ServletActionContext.getRequest();
		if(request.getParameter("typeList1")!=null)
		type=Integer.valueOf(request.getParameter("typeList1"));
        InputStream is = new FileInputStream(getMyFile());  
        String uploadPath = ServletActionContext.getServletContext().getRealPath(separator+"upload");  
        System.out.println(uploadPath);
        File toFile = new File(uploadPath, this.getMyFileFileName());  
        OutputStream os = new FileOutputStream(toFile);  
        byte[] buffer = new byte[1024];  
        int length = 0;  
        while ((length = is.read(buffer)) > 0) {  
            os.write(buffer, 0, length);  
        }  
        System.out.println("上传文件名"+myFileFileName);  
        System.out.println("上传文件类型"+myFileContentType);  
        is.close();  
        os.close();  
       //将文件按行读取，插入黑名单中
        FileReader reader = new FileReader(uploadPath+separator+this.getMyFileFileName());
        BufferedReader br = new BufferedReader(reader);
        Map<String,Object> map=new HashMap<String,Object>();
        String str =null;
        while((str = br.readLine())!=null) {
        	map.put("phoneNumber", str);
        	map.put("type", type);
        	map1.put("showNum", 15);
      		map1.put("pageNum",1);
      		map1.put("phoneNumber", str);
      		List<Map<String,Object>>  list=blackListService.showBlackList(map1);
      		if(list.size()>0){
      		 System.out.println("电话已存在！");
      		}
      		else{
      			blackListService.insertUserToBlackList(map);
      		}
              str=null;
        }
        br.close();
        reader.close();
        return "blackList";  
		
	}
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescinfo() {
		return descinfo;
	}

	public void setDescinfo(String descinfo) {
		this.descinfo = descinfo;
	}
	
	public String[] getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	

}
