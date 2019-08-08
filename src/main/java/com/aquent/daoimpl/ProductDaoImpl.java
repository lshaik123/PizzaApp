package com.aquent.daoimpl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.aquent.dao.ProductDao;
import com.aquent.model.Product;

@Component
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private DataSource dataSource;
	
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveProdct(Product product) {
	
		String sql= "INSERT INTO store (name, date, type) values(?,?,?)";
		Object[] params= new Object[] {
				product.getName(),
				product.getDate(),
				product.getType()
				
		};
		jdbcTemplate.update(sql, params);
		
	}

	@Override
	public void updateProdct(Product product) {
		// TODO Auto-generated method stub
		
	}

}
