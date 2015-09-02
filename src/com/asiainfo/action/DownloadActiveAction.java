package com.asiainfo.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.asiainfo.Iservice.EffectService;
import com.asiainfo.Iservice.IActiveService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class DownloadActiveAction extends ActionSupport {
	public Logger log = Logger.getLogger(DownloadActiveAction.class);
	private static final long serialVersionUID = -6683621474509288793L;
	private String filename;
    private String directory;
    private String activeCode;
    private String path="";
    private String startTime = "";
    private String endTime = "";


	@Autowired
	public IActiveService service;
	
	@Autowired
	public EffectService effectService;

	/**
	 * ���ػ��Ϣ
	 * @return
	 * @throws Exception
	 */
    public String downloadFile() throws Exception{
    	path=ServletActionContext.getServletContext().getRealPath("/");
    	String dir =path+ directory + filename ;
    	File file=new File(dir);
    	System.out.println(file);
    	if(file.exists()){
    		return "success";
    	}else{
    		file.createNewFile();
    		Map<String,Object> map=service.getActiveInfo(activeCode);
    		createDownloadFile(map,file);
    		return "success";
    	}
    }

    public InputStream getInputStream() throws Exception {
      String dir = directory + filename;
      return ServletActionContext.getServletContext().getResourceAsStream(dir);//���dir��Resource�µ����·��

    }


    private void createDownloadFile(Map<String,Object> map,File file){
    	FileWriter fw=null;
		try {
			fw = new FileWriter(file);
			fw.write("������Ϣ");
	    	fw.write("\n");
			Map<String,Object> activeInfo=(Map<String, Object>) map.get("activeInfo");
	    	System.out.println(activeInfo);

	    	fw.write("	�����:"+activeInfo.get("active_name"));
	    	fw.write("\n");
	    	fw.write("	�����:"+activeInfo.get("active_title"));
	    	fw.write("\n");
	    	fw.write("	�����:"+activeInfo.get("begin_time")+" ��  "+activeInfo.get("end_time"));
	    	fw.write("\n");
	    	fw.write("	Ч�����٣�");
	    	fw.write("\n");
	    	if("1".equals(activeInfo.get("active_ms_type"))){
	    		fw.write("		�� �ͣ���Ʒ�Ƽ�    "+"��Ʒ���� :"+activeInfo.get("b_active_code"));
	    	}else if("2".equals(activeInfo.get("active_ms_type"))){
	    		fw.write("		�� �ͣ���������    "+"URL��ַ :"+activeInfo.get("url"));
	    	}

	    	@SuppressWarnings("unchecked")
			List<Map<String,Object>> trigger=(List<Map<String, Object>>) map.get("trigger");
	    	fw.write("\n");
	    	fw.write("\n");
	    	fw.write("��������");
	    	fw.write("\n");
	    	for (Map<String, Object> map2 : trigger) {
	    		if("1".equals(map2.get("trigger_type"))){
	    			fw.write("	�ؼ���");
	    		}else if("2".equals(map2.get("trigger_type"))){
	    			fw.write("	APP");
	    		}else if("4".equals(map2.get("trigger_type"))){
	    			fw.write("	�ն˻���");
	    		}else if("0".equals(map2.get("trigger_type"))){
	    			fw.write("	λ��");
	    		}
	    		fw.write("\n");
	    		fw.write("		"+map2.get("trigger_ms").toString().replaceAll("</span><span>", ","));
	    		fw.write("\n");
			}
	    	fw.write("\n");
	    	fw.write("�û�Ⱥ");
	    	fw.write("\n");
	    	@SuppressWarnings("unchecked")
	    	List<Map<String,Object>> userGroup=(List<Map<String, Object>>) map.get("userGroup");
	    	for (Map<String, Object> map2 : userGroup) {
	    		fw.write("	"+map2.get("table_col").toString());
	    		fw.write("\n");
			}
	    	fw.write("\n");
	    	fw.write("�ͻ��Ӵ�����");
	    	fw.write("\n");
	    	@SuppressWarnings("unchecked")
	    	List<Map<String,Object>> gluserGroup=(List<Map<String, Object>>) map.get("gluserGroup");
	    	for (Map<String, Object> map2 : gluserGroup) {
	    		fw.write("	"+map2.get("table_col").toString()+",");
			}
	    	fw.write("\n");
	    	fw.write("\n");
	    	fw.write("��������������");
	    	fw.write("\n");
	    	@SuppressWarnings("unchecked")
	    	List<Map<String,Object>> sendMessage=(List<Map<String, Object>>) map.get("sendMessage");
	    	for (Map<String, Object> map2 : sendMessage) {
	    		if("1".equals(map2.get("send_type"))){
	    			fw.write("	����:10086����");
	    		}else if("2".equals(map2.get("send_type"))){
	    			fw.write("	����:���ϳ���");
	    		}else if("3".equals(map2.get("send_type"))){
	    			fw.write("	����:Ӫ������ǰ̨");
	    		}else if("4".equals(map2.get("send_type"))){

	    		}else if("5".equals(map2.get("send_type"))){
	    			fw.write("	����:����");
	    		}else if("6".equals(map2.get("send_type"))){
	    			fw.write("	����:����");
	    		}else if("7".equals(map2.get("send_type"))){
	    			fw.write("	����:΢��");
	    		}else if("8".equals(map2.get("send_type"))){
	    			fw.write("	����:Ӫ������ǰ̨");
	    		}
	    		fw.write("\n");
	    		fw.write("		����:"+map2.get("send_ms"));
	    		fw.write("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fw.close();
			} catch (IOException e) {
			}
        }

    }
    
    


    private String TimeToString(Timestamp time){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�����ʽ
        return df.format(time);
    }

    public String downLoadPhone(){
    	FileWriter fw=null;
    	path=ServletActionContext.getServletContext().getRealPath("/");
    	String dir =path+ directory + filename ;
    	File file=new File(dir);
    	System.out.println(file);
		try {
			file.createNewFile();
    		List<Map<String,String>> phoneList=service.getDownLoadPhone(activeCode);
    		fw= new FileWriter(file);
    		fw.write("�������");
    		fw.write("\r\n");
    		for (Map<String, String> map : phoneList) {
    			fw.write(map.get("phoneNo")+"\r\n");
			}
		} catch (Exception e) {
			log.error("���ɶ��������ļ�����"+e.getMessage());
		}finally{
			try {
				fw.close();
			} catch (IOException e) {
			}
        }
		return "success";
    }

    
    
    /**
	 * ���ػ��Ϣ
	 * @return
	 * @throws Exception
	 */
    public String downloatScene() throws Exception{
    	//��ѯ����
    	Map<String,Object> map=new HashMap<String,Object>();
		if(!"".equals(startTime))map.put("startTime", startTime);
		if(!"".equals(endTime)) map.put("endTime", endTime);
		
		Map<String,List<Map<String,Object>>> smap = null;
		try{
			smap=effectService.queryActiveCountList(map);
		}catch(Exception ex){
			log.error("��ѯ�������ݳ���");
		}
		
		createDownloadSceneFile(smap);
		
		return SUCCESS;  
    	
    }
    
    
    //���� excel�ļ�
    public void createDownloadSceneFile(Map<String,List<Map<String,Object>>> dataMap){
    	
    	HSSFWorkbook wk = new HSSFWorkbook(); 
    	HSSFCellStyle cellStyle = wk.createCellStyle();
    	cellStyle.setWrapText(true);
    	HSSFSheet sheet1 = wk.createSheet("����ʹ�����");
    	HSSFRow titleRow = sheet1.createRow(0);
    	int column = 0;
    	HSSFCell cell = titleRow.createCell(column++, HSSFCell.CELL_TYPE_STRING);
    	cell.setCellValue("����");
    	for(Map<String, Object> sceneMap : dataMap.get("titleList")){
    		cell = titleRow.createCell(column++, HSSFCell.CELL_TYPE_STRING);
    		cell.setCellStyle(cellStyle);
        	cell.setCellValue((String)sceneMap.get("scene_name"));
    	}
    	cell = titleRow.createCell(column++, HSSFCell.CELL_TYPE_STRING);
		cell.setCellStyle(cellStyle);
    	cell.setCellValue("�ϼ�");
    	
    	List<Map<String,Object>> countList=dataMap.get("countList");
		List<Map<String,Object>> titleList=dataMap.get("titleList");
		Map<String,Object> cmap=null;
		Map<String,Object> smap=null;
		String sceneKey="";
		for(int i=0;i<countList.size();i++){
			cmap=countList.get(i);
			
			HSSFRow dataRow = sheet1.createRow(i+1);
			int column_data = 0;
			cell = dataRow.createCell(column_data++, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue((String)cmap.get("cityName"));
			
			for(int j=0;j<titleList.size();j++){
				smap=titleList.get(j);
				sceneKey=smap.get("scene_id").toString();
				cell = dataRow.createCell(column_data++, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue((Long)cmap.get(sceneKey));
			}
			cell = dataRow.createCell(column_data++, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue((Long)cmap.get("cityCount"));
			
		}
    	
    	path=ServletActionContext.getServletContext().getRealPath("/");
    	String dir =path+ directory + filename ;
    	File file=new File(dir);
    	FileOutputStream out = null;
    	try{
	    	out=new FileOutputStream(file);  
	    	wk.write(out);
    	}catch(Exception ex)
    	{
    		log.error("���� Excel �ļ�����");
    	}finally{
    		try {
    			if(null != out)
				out.close();
    			wk.close();
			} catch (IOException e) {
				log.error("�ر�������");
			}
    	}
    }
    
    

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
