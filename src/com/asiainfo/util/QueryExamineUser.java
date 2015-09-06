package com.asiainfo.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.dao.ActiveDAO;
@Service("queryExamineUser")
public class QueryExamineUser implements InitializingBean{
	@Autowired
	public ActiveDAO dao;

	@Override
	public void afterPropertiesSet() throws Exception {
		List<Map<String,String>> list=dao.queryExamineUser();
		for (Map<String, String> map : list) {
			DataFinalUtil.setExamineUser(map.get("examine_useid"), map.get("city_id"));
		}

		//new Thread(new SendMsClientService(dao)).start();
	}
}
