package com.example.apptest1kt.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartShop {
    private String productname;
    private String price;
    private String img_food;
    private  int idUser;
    private int amout;
    public   int idFood;



    public CartShop() {
    }

    public CartShop(String productname, String price, String img_food, int idUser,int amout,int idFood) {
        this.productname = productname;
        this.price = price;
        this.img_food = img_food;
        this.idUser = idUser;
        this.amout=amout;
        this.idFood=idFood;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getAmout() {
        return amout;
    }

    public void setAmout(int amout) {
        this.amout = amout;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg_food() {
        return img_food;
    }

    public void setImg_food(String img_food) {
        this.img_food = img_food;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
