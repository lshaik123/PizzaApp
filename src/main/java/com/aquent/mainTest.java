package com.aquent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import com.aquent.serviceimpl.PizzaOrderServiceImpl;

public class mainTest {
	static String file="C:\\Users\\lshaik\\Documents\\Peojects\\PracticeWorkSpace\\SpringBootRestAPI\\pizzaOrder.txt";

	public static void main(String[] args) throws IOException {
	
         ArrayList sort= readFile();
         Collections.sort(sort);
         
		System.out.println(sort);
		saveFile("destination.txt", sort);

	}
	
	public static ArrayList readFile() throws IOException {
		BufferedReader input=null;
		ArrayList list= new ArrayList<>();
		try {
			input= new BufferedReader(new FileReader(file));
			String data;
			while((data=input.readLine())!= null) {
				list.add(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public static void saveFile(String FileName, ArrayList list) throws IOException {
		  ArrayList<String> list1= readFile();
		  Collections.sort(list1);
		  Path destination= Paths.get(file);
		  Files.write(destination, list1, Charset.defaultCharset());	
		}
}
