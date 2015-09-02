package com.asiainfo.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.asiainfo.Iservice.IBusiCodeService;
import com.asiainfo.Iservice.IManageService;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.Action;
@Controller
public class ManageAction implements Action{
	public Logger log = Logger.getLogger(ManageAction.class);
	@Autowired
	private IManageService ms;
	@Autowired
	private IBusiCodeService service;

	@Override
	public String execute() throws Exception {
		Map<String,Map<String,Object>> map= ms.queryCitySendCOunt(null);
		ServletActionContext.getRequest().setAttribute("cityMap", map);

		return "cityTable";
	}
	private String scaleId;
	private String cityCount;
	public void updateCityCount()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("sendCount", cityCount);
		map.put("scaleId", scaleId);
		String result=ms.updateCityCount(map);
		ServletActionContext.getResponse().getWriter().print(result);
	}

	public void getProductCode()throws Exception{

	}

	private String bigName;
	private String proNmae;
	private String priceCode;
	private String busCode;
	private String proCode;
	public void setProductCode()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("priceCode", priceCode);
		map.put("busCode", busCode);
		map.put("proCode", proCode);
		map.put("bigName", StringUtils.convertToUTF(URLDecoder.decode(bigName, "iso-8859-1")));
		map.put("proNmae", StringUtils.convertToUTF(URLDecoder.decode(proNmae, "iso-8859-1")));
		if(ms.addProductInfo(map)){
			ServletActionContext.getResponse().getWriter().print("Y");
		}else{
			ServletActionContext.getResponse().getWriter().print("N");
		}
	}

	public String queryProduct()throws Exception{
		List<String> list= service.getEffectProduct();
		System.out.println(list);
		ServletActionContext.getRequest().setAttribute("busicode", list);
		return "setEffectProduct";
	}

	private String sendMs;
	public void searchActive()throws Exception{
		String result=service.searchActive(sendMs);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(result));
	}

	public String getBigName() {
		return bigName;
	}



	public void setBigName(String bigName) {
		this.bigName = bigName;
	}



	public String getProNmae() {
		return proNmae;
	}



	public void setProNmae(String proNmae) {
		this.proNmae = proNmae;
	}



	public String getPriceCode() {
		return priceCode;
	}



	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}



	public String getBusCode() {
		return busCode;
	}



	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}



	public String getProCode() {
		return proCode;
	}



	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getScaleId() {
		return scaleId;
	}

	public void setScaleId(String scaleId) {
		this.scaleId = scaleId;
	}

	public String getCityCount() {
		return cityCount;
	}

	public void setCityCount(String cityCount) {
		this.cityCount = cityCount;
	}

	public String getSendMs() {
		return sendMs;
	}

	public void setSendMs(String sendMs) {
		try {
			this.sendMs = StringUtils.convertToUTF(URLDecoder.decode(sendMs, "iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
		}
	}

}
