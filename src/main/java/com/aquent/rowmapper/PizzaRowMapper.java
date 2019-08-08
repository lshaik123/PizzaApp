package com.aquent.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aquent.model.PizaaOrder;

public class PizzaRowMapper implements RowMapper<PizaaOrder>{

	@Override
	public PizaaOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
		PizaaOrder pizzaOrder= new PizaaOrder();
		pizzaOrder.setName(rs.getString("name"));
		pizzaOrder.setType(rs.getString("type"));
		pizzaOrder.setDate(rs.getDate("date"));
		
		return pizzaOrder;
	}

}
