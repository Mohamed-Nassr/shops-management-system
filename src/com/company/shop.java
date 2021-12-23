package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class shop {
    protected Statement s= null;
    protected Connection c=new database().connect();
    protected ResultSet result=null;
    protected int resultUpdate;
    protected String sql;
    public boolean codeExist(int id){
        try {
            this.s = this.c.createStatement();
        } catch (SQLException e) {
            System.out.println("one");
        }
        this.sql="select COUNT(1) FROM shop WHERE  product_id='"+id+"'";
        try {
            this.result = this.s.executeQuery(this.sql);
            try {
                if (this.result.next() && this.result.getInt("COUNT(1)") == 1) {
                    return true;
                } else
                    return false;

            }catch (SQLException e){}
            System.out.println("inner");

        } catch (SQLException e) {
            System.out.println("Two");
        }
        return true;
    }
    public boolean addItem(int price,int number,int id){
        this.sql="INSERT into shop ( price, number, product_id) VALUES ('"+(price*130)/100+"', '"+number+"','"+id+"')";
        try {
            this.s = this.c.createStatement();
            this.resultUpdate = this.s.executeUpdate(this.sql);
        } catch (SQLException e ) {
        }
        if ( this.resultUpdate== 1) {
            return true;
        } else if(this.resultUpdate==0)
            return false;
        return true;
    }
    public ArrayList<Integer> getItem(int product_id){
        ArrayList<Integer> item = new ArrayList<Integer>();
        try {
            this.s = this.c.createStatement();
        } catch (SQLException e) {
            System.out.println("one");
        }
        this.sql="select id,number FROM shop WHERE product_id='"+product_id+"'";
        try {
            this.result = this.s.executeQuery(this.sql);
            try {
                while (this.result.next() ) {
                    item.add(this.result.getInt("id"));
                    item.add(this.result.getInt("number"));
                }

            }catch (SQLException e){}
            System.out.println("inner");

        } catch (SQLException e) {
            System.out.println("Two");
        }
        return item;
    }
    public  boolean editItem(int price,int number,int product_id,int old_product_id){
        ArrayList<Integer> item = new ArrayList<Integer>();
        item=this.getItem(old_product_id);
        number +=item.get(1);
        this.sql="update  shop SET  price='"+(price*130)/100+"', number='"+number+"', product_id = '"+product_id+"' where product_id='"+old_product_id+"'";
        try {
            this.s = this.c.createStatement();
            this.resultUpdate = this.s.executeUpdate(this.sql);
        } catch (SQLException e ) {
        }
        if ( this.resultUpdate== 1) {
            return true;
        } else if(this.resultUpdate==0)
            return false;
        return true;
    }
    public ArrayList<String> itemDetails(int code){
        ArrayList<String> itemInfo = new ArrayList<>();
        try {
            this.s = this.c.createStatement();
        } catch (SQLException e) {
            System.out.println("one");
        }
        this.sql="select shop.*,products.type FROM shop INNER JOIN products on shop.product_id=products.id WHERE products.code='"+code+"'";
        try {
            this.result = this.s.executeQuery(this.sql);
            try {
                while (this.result.next() ) {
                    itemInfo.add(this.result.getString("id"));
                    itemInfo.add(this.result.getString("price"));
                    itemInfo.add(this.result.getString("product_id"));
                    itemInfo.add(this.result.getString("number"));
                    itemInfo.add(this.result.getString("type"));
                }

            }catch (SQLException e){}
            System.out.println("inner");

        } catch (SQLException e) {
            System.out.println("Two");
        }
        return itemInfo;
    }
    //used in orders class
    public void updateShop(int num,int product_id){
        this.sql="update  shop SET  number='"+num+"' where product_id='"+product_id+"'";
        try {
            this.s = this.c.createStatement();
            this.resultUpdate = this.s.executeUpdate(this.sql);
        } catch (SQLException e ) {
        }
    }
    public ArrayList<String> allItems(){
        ArrayList<String> items = new ArrayList<>();
        try {
            this.s = this.c.createStatement();
        } catch (SQLException e) {
            System.out.println("one");
        }
        this.sql="select shop.price,shop.number,products.type,products.code FROM shop INNER JOIN products on shop.product_id=products.id" +
                " ORDER BY products.code ASC ";
        try {
            this.result = this.s.executeQuery(this.sql);
            try {
                while (this.result.next() ) {
                items.add(this.result.getString("type"));
                items.add(this.result.getString("code"));
                items.add(this.result.getString("price"));
                items.add(this.result.getString("number"));
                }

            }catch (SQLException e){}
            System.out.println("inner");

        } catch (SQLException e) {
            System.out.println("Two");
        }
        return items;
    }
}
