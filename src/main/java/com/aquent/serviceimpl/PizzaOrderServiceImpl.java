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

import org.springframework.stereotype.Component;

import com.aquent.model.Pizza;
import com.aquent.service.PizzaOrderService;
import com.aquent.sort.comparator.SortByAscendingOrder;

@Component
public class PizzaOrderServiceImpl implements PizzaOrderService {

	String file = "C:\\Users\\lshaik\\Documents\\Peojects\\PracticeWorkSpace\\SpringBootRestAPI\\sample_data_ordered.txt";


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

	


}
