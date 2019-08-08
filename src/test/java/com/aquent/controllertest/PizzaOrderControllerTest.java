
package com.aquent.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aquent.controller.PizzaOrderController;

@RunWith(SpringRunner.class)
@WebMvcTest(PizzaOrderController.class)
public class PizzaOrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testForPizzaOrderController_ReadfileFromSource() throws Exception {
		mockMvc.perform(get("/pizza/getdata").
				contentType(MediaType.APPLICATION_JSON)).
		andExpect(status().isOk());

	}

	@Test
	public void testForPizzaOrderController_writefileFromSource() throws Exception {
		mockMvc.perform(get("/pizza/writedata").
				contentType(MediaType.APPLICATION_JSON)).
		andExpect(status().isOk());

	}

}
