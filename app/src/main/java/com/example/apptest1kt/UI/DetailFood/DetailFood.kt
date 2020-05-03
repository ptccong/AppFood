package com.example.apptest1kt.UI.DetailFood


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.apptest1kt.R
import com.example.apptest1kt.Retrofit.Retrofit2
import com.example.apptest1kt.UI.CartScreen.CartShopScreen
import com.example.apptest1kt.UI.HomeScreen.UserHome
import com.example.apptest1kt.model.CartShop
import com.example.apptest1kt.model.FoodModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_food.*
import retrofit2.Call
import retrofit2.Response

class DetailFood : AppCompatActivity(),View.OnClickListener {
    private var retrofit2: Retrofit2 = Retrofit2()
    private var arrFoodId:ArrayList<FoodModel> = ArrayList()
    private var databaseReference:DatabaseReference =FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_food)
        getFoodId()
        btnAction()

    }
    private fun getFoodId(){
        val intent =intent
        val idFoods = intent.getIntExtra("idFood",12)
        retrofit2.connectRetrofit().getFoodId(idFoods).enqueue(object :retrofit2.Callback<FoodModel>{
            override fun onFailure(call: Call<FoodModel>, t: Throwable) {
                d("error",t.toString())
            }

            override fun onResponse(call: Call<FoodModel>, response: Response<FoodModel>) {
               arrFoodId.addAll(listOf(response.body()!!))
                tv_name_detail.text = arrFoodId[0].productname
                tv_price_detail.text = arrFoodId[0].price
                tv_detail_food.text = arrFoodId[0].detail
                Picasso.get().load(arrFoodId[0].imageFood).into(img_food_detail)
            }
        })
    }
    private fun btnAction(){
        btn_addCart.setOnClickListener(this)
        btn_favou.setOnClickListener(this)
    }
    private fun addProduct(){
        val intent = intent
        val idUserF = intent.getIntExtra("idUserF",123)
        val idFoods = intent.getIntExtra("idFood",12)
        databaseReference.child("cartShop").child("$idFoods").setValue(CartShop( arrFoodId[0].productname,arrFoodId[0].price,arrFoodId[0].imageFood,idUserF,1,arrFoodId[0].idFood))
        Toast.makeText(this,"Đã thêm vào giở hàng",Toast.LENGTH_LONG).show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_shop,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.btn_img_cart){
            val idV = intent.getIntExtra("idUserF",123)
            val intent = Intent(this, CartShopScreen::class.java)
            intent.putExtra("idV",idV)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onClick(v: View?) {
      when(v?.id){
          R.id.btn_addCart->{
              addProduct()
          }
      }
    }
}
