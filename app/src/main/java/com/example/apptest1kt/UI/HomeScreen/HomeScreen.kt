package com.example.apptest1kt.UI.HomeScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.apptest1kt.R
import com.example.apptest1kt.Retrofit.Retrofit2
import com.example.apptest1kt.UI.AboutScreen.AboutScreen
import com.example.apptest1kt.UI.CartScreen.CartShopScreen
import com.example.apptest1kt.UI.ContractScreen.ContractScreen
import com.example.apptest1kt.UI.DetailFood.DetailFood
import com.example.apptest1kt.UI.Favorites.FavoritesScreen
import com.example.apptest1kt.UI.HistoryScreen.HistoryScreen
import com.example.apptest1kt.UI.LoginScreeen.LoginScreen
import com.example.apptest1kt.model.FoodModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.header_home_screen.*
import retrofit2.Call
import retrofit2.Response

class HomeScreen : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{
    private lateinit var mDrawer:DrawerLayout
    private lateinit var mToggle: ActionBarDrawerToggle
    private   var retrofit2: Retrofit2 = Retrofit2()
    private var listUserHome:ArrayList<UserHome> = ArrayList()
    private var listFood:ArrayList<FoodModel> = ArrayList()
    var idUser:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        var intent =intent
        idUser = intent.getIntExtra("idUser",123)
        actionDrawer()
        getDataFormId(idUser)
        getFood(idUser)
        init(idUser)

    }

    private fun init(idUser:Int){
        val recyclerView = findViewById<RecyclerView>(R.id.data_food)
        recyclerView.layoutManager= StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter=FoodAdapter(listFood,this,idUser)
    }
    private fun getFood(idUser: Int){
        retrofit2.connectRetrofit().getFood().enqueue(object :retrofit2.Callback<List<FoodModel>>{
            override fun onFailure(call: Call<List<FoodModel>>, t: Throwable) {
                d("erorDataFood",t.toString())
            }
            override fun onResponse(call: Call<List<FoodModel>>, response: Response<List<FoodModel>>) {
                d("dataFood",response.body()!!.toString())
               listFood.addAll(response.body()!!)
                val recyclerView = findViewById<RecyclerView>(R.id.data_food)
                recyclerView.adapter=FoodAdapter(listFood,applicationContext,idUser)
            }

        })
    }
    private fun getDataFormId(idUser: Int){
        retrofit2.connectRetrofit().getUserHome(idUser).enqueue(object:retrofit2.Callback<UserHome>{
            override fun onFailure(call: Call<UserHome>, t: Throwable) {
                d("dataUL",t.toString())
            }
            override fun onResponse(call: Call<UserHome>, response: Response<UserHome>) {
                listUserHome.addAll(listOf(response.body()!!))
                tv_name.text = listUserHome[0].fullname
                tv_phone_home.text=listUserHome[0].phonenumber
                tv_address.text = listUserHome[0].address
                //url = listUserHome[0].avata
                d("dataUL",listUserHome.toString())

            }

        })
       // Picasso.get().load(url).into(img_food)
    }

    private fun actionDrawer(){
        mDrawer= findViewById(R.id.home_drawer)
        mToggle = ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close)
        mDrawer.addDrawerListener(mToggle)
        mToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    //bắt sự kiện cho cartshop
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.btn_img_cart){
            val intent = Intent(this,CartShopScreen::class.java)
            intent.putExtra("idV",idUser)
            startActivity(intent)
        }
       if (mToggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
    //tạo icon cart trên toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_shop,menu)
        return super.onCreateOptionsMenu(menu)
    }
    // bắt sự kiên cho item navigation
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home-> {
                startActivity(Intent(applicationContext,HomeScreen::class.java))
            }
            R.id.about->{
                startActivity(Intent(applicationContext,AboutScreen::class.java))
            }
            R.id.history->{
                startActivity(Intent(applicationContext,HistoryScreen::class.java))
            }
            R.id.favourites->{
                startActivity(Intent(applicationContext,FavoritesScreen::class.java))
            }
            R.id.contract->{
                startActivity(Intent(applicationContext,ContractScreen::class.java))
            }
            R.id.log_out->{
                startActivity(Intent(applicationContext,LoginScreen::class.java))
            }
        }
        return false
    }


}




