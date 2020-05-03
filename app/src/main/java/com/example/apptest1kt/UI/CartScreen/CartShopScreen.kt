package com.example.apptest1kt.UI.CartScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.apptest1kt.R
import com.example.apptest1kt.model.CartShop
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_cart_shop_s_creen.*
import kotlinx.android.synthetic.main.row_item_cart_shop.*
import kotlin.text.toDouble as toDouble

class CartShopScreen : AppCompatActivity() {
    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var arrCart:ArrayList<CartShop> = ArrayList()
    var soLuong:Double= 0.0
    var tongTien:Double=0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_shop_s_creen)
        val intent = intent
        val idV = intent.getIntExtra("idV",125)
        val recyclerView = findViewById<RecyclerView>(R.id.lv_data_cart)
        recyclerView.adapter=CartAdapter(arrCart,idV)
        recyclerView.layoutManager= StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        getDtaCart(idV)

    }
    private  fun getDtaCart(idV:Int){
        database.child("cartShop").addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                var cart: CartShop?=p0.getValue(CartShop::class.java)
                if (idV==cart?.idUser){
                    arrCart.add(CartShop(cart?.productname,cart?.price,cart?.img_food,cart?.idUser,cart?.amout,cart?.idFood))
                    soLuong+=cart?.amout
                    tongTien+=cart?.price.toDouble()*cart?.amout
                    val recyclerView = findViewById<RecyclerView>(R.id.lv_data_cart)
                    recyclerView.adapter=CartAdapter(arrCart,idV)
                    tv_tong_sl.text=soLuong.toString()
                    tv_tong_gia.text=tongTien.toString()
                }

            }

            override fun onChildRemoved(p0: DataSnapshot) {
                arrCart.clear()
                val recyclerView = findViewById<RecyclerView>(R.id.lv_data_cart)
                recyclerView.adapter=CartAdapter(arrCart,idV)


                }

        })
    }

}
