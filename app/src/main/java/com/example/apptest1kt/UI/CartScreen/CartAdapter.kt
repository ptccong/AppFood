package com.example.apptest1kt.UI.CartScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest1kt.R
import com.example.apptest1kt.model.CartShop
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class CartAdapter (private val arrCart:ArrayList<CartShop>,val idUserF:Int):RecyclerView.Adapter<CartAdapter.ViewHolder>(){
    var databaseReference:DatabaseReference = FirebaseDatabase.getInstance().reference
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val tv_name_food_cart = itemView.findViewById<TextView>(R.id.tv_name_cart)
        val tv_price_food_cart = itemView.findViewById<TextView>(R.id.tv_price_cart)
        val img_food_cart=itemView.findViewById<ImageView>(R.id.img_food_cart)
        val tv_sl_cart = itemView.findViewById<TextView>(R.id.tv_sl_cart)
        val btn_them = itemView.findViewById<Button>(R.id.btn_them_cart)
        val btn_giam = itemView.findViewById<Button>(R.id.btn_giam_cart)
        val btn_xoa = itemView.findViewById<Button>(R.id.btn_xoa_cart)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val v = LayoutInflater.from(parent.context).inflate(R.layout.row_item_cart_shop,parent,false)
        return ViewHolder(v)
    }



    override fun getItemCount(): Int {
       return arrCart.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = arrCart[position]
        var dem = cart.amout
        holder.tv_name_food_cart.text=cart.productname
        holder.tv_price_food_cart.text=cart.price
        holder.tv_sl_cart.text= cart.amout.toString()
        Picasso.get().load(cart.img_food).into(holder.img_food_cart)
        holder.btn_them.setOnClickListener {
            dem+=1
            databaseReference.child("cartShop").child("${cart.idFood}").setValue(CartShop( cart.productname,cart.price,cart.img_food,idUserF,dem,cart.idFood))
            holder.tv_sl_cart.text= dem.toString()

        }
        holder.btn_giam.setOnClickListener {
            if (dem>0){
                dem-=1
                databaseReference.child("cartShop").child("${cart.idFood}").setValue(CartShop( cart.productname,cart.price,cart.img_food,idUserF,dem,cart.idFood))
                holder.tv_sl_cart.text= dem.toString()
            }
        }
        holder.btn_xoa.setOnClickListener {
            arrCart.removeAt(position)
            databaseReference.child("cartShop").child("${cart.idFood}")
                .removeValue { _, _ ->

                }
        }

    }

}