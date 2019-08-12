package com.aquent.serviceimpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aquent.model.PizaaOrder;
import com.aquent.model.Pizza;
import com.aquent.rowmapper.PizzaRowMapper;
import com.aquent.service.PizzaOrderService;
import com.aquent.sort.comparator.SortByAscendingOrder;

@Component
public class PizzaOrderServiceImpl implements PizzaOrderService {

	String file = "C:\\Users\\lshaik\\Documents\\Peojects\\PracticeWorkSpace\\SpringBootRestAPI\\sample_data_ordered.txt";

	 private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PizzaOrderServiceImpl.class);
	
	 
	 private JdbcTemplate jdbcTemplate;
	 @Autowired
	 private DataSource dataSource;
	 
	 private PizzaRowMapper rowMapper= new PizzaRowMapper();
	 
     @Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Pizza> readFile() throws IOException {
		LOG.info("PizzaOrderServiceImpl.readFile method called ");
		int i = 0;
		BufferedReader bufferedReader = null;
		List<Pizza> listPizzas = new ArrayList<Pizza>();
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String data;
			while ((data = bufferedReader.readLine()) != null) {
				if(i != 0) {
					String formatedString = data.trim().replaceAll(" +", " ");
					String[] arr = formatedString.split(" ");
					Pizza pizza = new Pizza();
					pizza.setOrder(arr[0]);
					pizza.setTime(Long.parseLong(arr[1]));
					listPizzas.add(pizza);

				}
				i++;

			}
			LOG.info("PizzaOrderServiceImpl.readFile found the list of pizzas" +listPizzas);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
		return listPizzas;
	}

	@Override
	public void saveFile() throws IOException {
		LOG.info("PizzaOrderServiceImpl.saveFile method called ");

		File file = new File("C:\\Users\\lshaik\\Desktop\\text.txt");
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write("Order Time");
		bw.newLine();
		List<Pizza> listPizzas = readFile();
		Collections.sort(listPizzas, new SortByAscendingOrder());
		if (!listPizzas.isEmpty()) {
			listPizzas.forEach(pizza -> {
				try {
					bw.write(pizza.getOrder() + " " + pizza.getTime());
					bw.newLine();

				} catch (IOException e) {
					e.printStackTrace();
				}
		});
			LOG.info("PizzaOrderServiceImpl.saveFile methiod copied into different file");

		}
		bw.close();
	}

	@Override
	public int insert(PizaaOrder pizaorder) {
		
		String sql= "INSERT into aquent_db.store(id,name,type,date) values(?,?,?,?)";
		
		Object[] params= new Object[] {
				pizaorder.getId(),
				pizaorder.getName(),
				pizaorder.getType(),
				pizaorder.getDate()
				
		};
				return jdbcTemplate.update(sql, params);		
	}

	@Override
	public List<PizaaOrder> findAll() {
		String Sql= "Select * from aquent_db.store";
		return jdbcTemplate.query(Sql, rowMapper);
	}

	@Override
	public PizaaOrder findById(int id) {
		String sql= "SELECT * FROM store WHERE id=?";
		return jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	


}
