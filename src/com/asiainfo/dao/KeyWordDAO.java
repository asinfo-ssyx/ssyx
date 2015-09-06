package com.asiainfo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.asiainfo.bean.KeyWord;

@Repository
public interface KeyWordDAO {
	
	public List<KeyWord> selectKeyWordByWord(String s);
}
