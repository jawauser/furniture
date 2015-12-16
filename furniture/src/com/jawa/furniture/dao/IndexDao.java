package com.jawa.furniture.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class IndexDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> list(){
		String sql="";
		return this.jdbcTemplate.queryForList(sql);
	}
}
