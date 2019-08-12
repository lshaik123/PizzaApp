package com.aquent.serviceimpltest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aquent.model.PizaaOrder;
import com.aquent.model.Pizza;
import com.aquent.service.PizzaOrderService;
import com.aquent.serviceimpl.PizzaOrderServiceImpl;
import com.aquent.sort.comparator.SortByAscendingOrder;
import com.aquent.sort.comparator.SortByDescendingOrder;

public class PizzaServiceImplTest {

	@InjectMocks
	private PizzaOrderServiceImpl implTest = new PizzaOrderServiceImpl();
	
	

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Mock
	private PizzaOrderService pizzaOrderService;
	@Mock
	private DataSource dataSource;
	
	private SortByAscendingOrder ascendingOrder = new SortByAscendingOrder();

	private SortByDescendingOrder descendingOrder = new SortByDescendingOrder();
	
	@Mock
	private JdbcTemplate mockedJdbTemplate= new JdbcTemplate();
	
	private PizaaOrder order=getData();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testToReadTheTextFileFromSourceLocation() throws Exception {
		
		

		List<Pizza> listPizzas = implTest.readFile();

		assertEquals(listPizzas.size(), 3);
	}

	@Test
	public void testToWriteTheTextFileFromSourceLocation() throws Exception {

		// implTest.saveFile();

		/*
		 * ArgumentCaptor<Pizza> argument = ArgumentCaptor.forClass(Pizza.class);
		 * Mockito.verify(implTest, atLeastOnce()).saveFile();
		 * 
		 * argument.capture(); assertEquals("Veg", argument.capture().getOrder())
		 */;

	}

	@Test
	public void testForSortingBasedOnTimeandReturnSmallValue() {
		Pizza pizza = new Pizza();
		pizza.setTime(1);
		Pizza pizza2 = new Pizza();
		pizza2.setTime(2);
		int result = ascendingOrder.compare(pizza, pizza2);
		assertEquals(-1, result);
	}

	@Test
	public void testForSortByDescendingBasedOnTimeAndReturnHigherValue() {
		Pizza pizza = new Pizza();
		pizza.setTime(1);
		Pizza pizza2 = new Pizza();
		pizza2.setTime(2);
		int result = descendingOrder.compare(pizza, pizza2);
		assertEquals(1, result);
	}
	
	@Test
	public void testForInsertingtheData() {
		
		String sql= "Select * From User";
		Object[] params= new Object[]
				{
						order.getName(),
						order.getType(),
						order.getId(),
						order.getDate()		
				};
		when(pizzaOrderService.insert(order)).thenReturn(new Integer(1));
		verify(pizzaOrderService, atLeastOnce()).insert(order);
		verify(mockedJdbTemplate).update(sql, params);
	}
	
	private PizaaOrder getData() {
		PizaaOrder pizaOrder= new PizaaOrder();
		pizaOrder.setDate(121213);
		pizaOrder.setId(1);
		pizaOrder.setName("lalu");
		pizaOrder.setType("sandwitch");
		return pizaOrder;
		
	}
}
