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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.aquent.model.PizaaOrder;
import com.aquent.model.Pizza;
import com.aquent.rowmapper.PizzaRowMapper;
import com.aquent.service.PizzaOrderService;

@Component
public class PizzaOrderServiceImpl implements PizzaOrderService {

	String file = "C:\\Users\\lshaik\\Documents\\Peojects\\PracticeWorkSpace\\SpringBootRestAPI\\sample_data_ordered.txt";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PizzaOrderService pizzaOrderService;
	@Autowired
	private DataSource dataSource;

	private PizzaRowMapper rowMapper = new PizzaRowMapper();

	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Pizza> readFile() throws IOException {
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
		return listPizzas;
	}

	@Override
	public void saveFile() throws IOException {
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
		}
		bw.close();
	}

	@Override
	public PizaaOrder saveOrder(PizaaOrder pizzaOrder) {
		String sql = "INSERT INTO store (name, date, type) values(?,?,?)";
		Object[] params = new Object[] { pizzaOrder.getName(), pizzaOrder.getDate(), pizzaOrder.getType()

		};
		jdbcTemplate.update(sql, params);
		return pizzaOrder;

	}

	@Override
	public List<PizaaOrder> findAll() {
		String sql = "Select * from aquent_db.store";

		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	@Query("select s from PizaOrder s where like %?1% ")
	public PizaaOrder findByName(String name) {

		String sql = "SELECT * from  aquent_db.store WHERE name=?";

		return jdbcTemplate.queryForObject(sql, rowMapper, name);
	}

	@Override
	public List<PizaaOrder> findAlls(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pizza save(Pizza pizza) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sortByValue() {
		// TODO Auto-generated method stub

	}

}
