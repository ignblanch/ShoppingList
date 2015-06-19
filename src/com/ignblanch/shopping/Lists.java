package com.ignblanch.shopping;

import java.util.ArrayList;

// Both fragments interact with these lists
public class Lists {
	private static ArrayList<String> products = new ArrayList<String>();
	private static ArrayList<String> shoppingList = new ArrayList<String>();
	
	public static void addProducts(String product){
		products.add(product);
	}
	
	public static void removeProducts(String product){
		products.remove(product);
	}
	
	public static ArrayList<String> getProducts(){
		return products;
	}
	
	public static void addItem(String item){
		shoppingList.add(item);
	}
	
	public static ArrayList<String> getItems(){
		return shoppingList;
	}
	
	public static void removeItem(String item){
		shoppingList.remove(item);
	}
	
	


}
