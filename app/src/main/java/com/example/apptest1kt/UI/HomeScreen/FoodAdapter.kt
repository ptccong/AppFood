package com.example.apptest1kt.UI.HomeScreen

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest1kt.R
import com.example.apptest1kt.UI.DetailFood.DetailFood
import com.example.apptest1kt.model.FoodModel
import com.squareup.picasso.Picasso


class FoodAdapter(private val listFood:ArrayList<FoodModel>, private val context:Context,private val idUserF:Int):RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val nameFood: TextView = itemView.findViewById(R.id.tv_name_food)
        val priceFood: TextView =  itemView.findViewById(R.id.tv_price)
        val imgFood: ImageView = itemView.findViewById(R.id.img_food)
       // val detail: TextView = itemView.findViewById(R.id.tv_detail)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.row_item_data_food,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val food = listFood[position]
        holder.nameFood.text=food.productname
        holder.priceFood.text=food.price+"$"
        //holder.detail.text=food.detail
        Picasso.get().load(food.imageFood).into(holder.imgFood)
        holder.itemView.setOnClickListener{
            val intent=Intent(context,DetailFood::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("idFood",food.idFood)
            intent.putExtra("idUserF",idUserF)
            context.startActivity(intent)
        }
    }
}