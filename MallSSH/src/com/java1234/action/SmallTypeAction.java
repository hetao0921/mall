package com.java1234.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;
import net.sf.json.util.JSONStringer;
import net.sf.json.util.JSONUtils;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;

import com.java1234.model.BigType;
import com.java1234.model.PageBean;
import com.java1234.model.SmallType;
import com.java1234.service.BigTypeService;
import com.java1234.service.SmallTypeService;
import com.java1234.util.PropertiesUtil;
import com.opensymphony.xwork2.ActionSupport;

@Component
public class SmallTypeAction extends ActionSupport implements SessionAware,RequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private int action;
	private int page;
	private String result;
	private SmallTypeService sts;
	private BigTypeService bts;
	private SmallType smallType;
	private String smallTypeId;
	private Map<String,Object> dataMap;
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public SmallTypeService getSts() {
		return sts;
	}
	@Resource
	public void setSts(SmallTypeService sts) {
		this.sts = sts;
	}
	public BigTypeService getBts() {
		return bts;
	}
	@Resource
	public void setBts(BigTypeService bts) {
		this.bts = bts;
	}
	public SmallType getSmallType() {
		return smallType;
	}
	public void setSmallType(SmallType smallType) {
		this.smallType = smallType;
	}
	public String getSmallTypeId() {
		return smallTypeId;
	}
	public void setSmallTypeId(String smallTypeId) {
		this.smallTypeId = smallTypeId;
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	@Override
	public String execute() throws Exception {
		if(action==0) {
			if(smallTypeId!=null) {
				sts.deleteSmallType(Integer.parseInt(smallTypeId));
				request.put("result", "ɾ���ɹ�");
				return "bg-smallTypeResult";
			}
			int count = sts.smallTypeCount();
			int rows = Integer.parseInt(PropertiesUtil.getValue("pageSize"));//ÿһҳ�ļ�¼��
			int pageCount = count%rows==0?count/rows:count/rows+1;//��ҳ����
			int currentPage = page; //page;
			PageBean pageBean = new PageBean();
			pageBean.setPage(currentPage);//page��ǰ̨�������ĵ�ǰҳ��
			pageBean.setRows(rows);
			List<SmallType> stList = sts.selectSmallType(pageBean);
			request.put("count", count);//��������¼
			request.put("pageCount", pageCount);//����ҳ
			request.put("currentPage", currentPage);
			request.put("stList", stList);
			return "bg-smallTypeSelect";
		}
		if(action==1) {
			smallType.setCreateTime(new Date());
			sts.addSmallType(smallType);
			request.put("result", "��ӳɹ�");
			return "bg-smallTypeResult";
		}
		if(action==2) {
			List<BigType> btList = bts.selectBigType();
			request.put("btList", btList);
			return "bg-smallTypeInsert";
		}
		return null;
	}
	
	public String json(){
		System.out.println("--------"+result);
		List<SmallType> stList = sts.selectSmallTypeByBigId(result);
		System.out.println("--------11111111 "+stList.get(0).getId());
		dataMap=new HashMap<String,Object>();
		dataMap.put("name", "hehe");
		dataMap.put("age", 12);
		dataMap.put("stList", stList);
		dataMap.put("success", true);
		System.out.println("---"+dataMap.get("name"));
		return SUCCESS;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
