package com.aquent.serviceimpltest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aquent.model.Pizza;
import com.aquent.service.PizzaOrderService;
import com.aquent.serviceimpl.PizzaOrderServiceImpl;

public class PizzaServiceImplTest {

	@Spy
	private PizzaOrderServiceImpl implTest = new PizzaOrderServiceImpl();
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	@Mock
	private PizzaOrderService pizzaOrderService;
	@Mock
	private DataSource dataSource;
	
	@Test
	public void testToReadTheTextFileFromSourceLocation() throws Exception {
		List<Pizza> listPizzas = implTest.readFile();
	    
	    assertEquals(listPizzas.size(), 3);
	}
	
	@Test
	public void testToWriteTheTextFileFromSourceLocation() throws Exception {

        // implTest.saveFile();
		
		ArgumentCaptor<Pizza> argument = ArgumentCaptor.forClass(Pizza.class);
		Mockito.verify(implTest, atLeastOnce()).saveFile();
		argument.capture();
		assertEquals("Veg", argument.capture().getOrder());

	}

}
