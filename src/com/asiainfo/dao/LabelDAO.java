package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public interface LabelDAO {
	/**
	 * ��ѯlabel��Ϣ
	 * @return
	 */
	public List<Map<String, String>> selectLabel();
}
