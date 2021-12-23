package com.company;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class Main {

    public static void main(String[] args) {
	/*product product=new product();
	System.out.print(product.addProduct("cloths",10,13,10,15,10,product.clients().get(0)));*/
	//System.out.println(product.sesson("winter"));
	shop afify=new shop();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new Date();
        afify.allItems();


    }
}
