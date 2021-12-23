package com.company;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class orders {
    private static int total=0;
    protected Statement s= null;
    protected Connection c=new database().connect();
    protected ResultSet result=null;
    protected int resultUpdate;
    protected String sql;
    protected shop shopItem;
    public boolean makeOrder(int user_id, String time, int code, int  num){
        shopItem=new shop();
        ArrayList<String>  sales;
        sales=shopItem.itemDetails(code);

            this.sql="INSERT into orders ( time, user_id, sales_id,number) VALUES ('"+time+"', '"+user_id+"'," +
                    "'"+Integer.parseInt(sales.get(0))+"','"+num+"')";


            try {
                this.s = this.c.createStatement();
                this.resultUpdate = this.s.executeUpdate(this.sql);
            } catch (SQLException e ) {
            }
            if ( this.resultUpdate== 1) {
                this.printOrder(user_id,time,Integer.parseInt(sales.get(0)),num,Float.parseFloat(sales.get(1)));
                shopItem.updateShop(Integer.parseInt(sales.get(3))-num,Integer.parseInt(sales.get(2)));
                return true;
            } else if(this.resultUpdate==0)
                return false;

    return true;
    }
    private void printOrder (int user_id, String time,int  sales,int  num,float price){
            total +=(price*num);
            System.out.println("A new order");
            System.out.print((user_id) +" "+user_id+" "+sales+" "+num+" at  "+time);
        }

    public int getPrice(){
        return total;
    }
    public void setPrice(){
        total=0;
    }
    public void myOrders(int user_id){
        ArrayList<Integer> items = new ArrayList<>();
        try {
            this.s = this.c.createStatement();
        } catch (SQLException e) {
            System.out.println("one");
        }
        this.sql="select shop.price,shop.number,products.type,products.code FROM orders";
        try {
            this.result = this.s.executeQuery(this.sql);
            try {
                while (this.result.next() ) {
                    System.out.println(this.result.getString("type")+"   "+this.result.getString("code")
                            + "   "+this.result.getString("price")+"        "  +this.result.getString("number")
                    );
                }

            }catch (SQLException e){}
            System.out.println("inner");

        } catch (SQLException e) {
            System.out.println("Two");
        }
    }
}
