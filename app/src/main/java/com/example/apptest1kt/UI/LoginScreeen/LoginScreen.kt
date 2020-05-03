package com.example.apptest1kt.UI.LoginScreeen
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apptest1kt.R
import com.example.apptest1kt.Retrofit.Retrofit2
import com.example.apptest1kt.UI.HomeScreen.HomeScreen
import com.example.apptest1kt.UI.RegisterScreen.Resistors
import kotlinx.android.synthetic.main.activity_login_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginScreen : AppCompatActivity(),View.OnClickListener {
    private  var lisUserLogin:ArrayList<UserLogin> = ArrayList()
    private val retrofit:Retrofit2 = Retrofit2()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        getData()
        btnAction()
    }
    private fun getData(){
       retrofit.connectRetrofit().getAllUser().enqueue(object :Callback<List<UserLogin>>{
           override fun onFailure(call: Call<List<UserLogin>>, t: Throwable) {
              d("errorLogin",t.toString())
           }
           override fun onResponse(call: Call<List<UserLogin>>, response: Response<List<UserLogin>>) {
               d("dataLogin",response.body()!!.toString())
               lisUserLogin.addAll(response.body()!!)
           }

       })
    }
    private fun login(): Boolean {
        for (i in lisUserLogin.indices) {
            if (lisUserLogin[i].username.equals(edt_username.text.toString()) && lisUserLogin[i].password.equals(edt_password.text.toString())) {
                val idUser = lisUserLogin[i].idUser
                val intent=Intent(this, HomeScreen::class.java)
                intent.putExtra("idUser",idUser)
                startActivity(intent)
                return true

            }
        }
        return false
    }
    private fun btnAction(){
        btn_login.setOnClickListener(this)
        btn_register.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        if (v?.id==R.id.btn_login){
            if (login()){
                Toast.makeText(this,"Đăng nhập thành công ",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show()
            }
        }else if (v?.id==R.id.btn_register){
            startActivity(Intent(this,Resistors::class.java))
        }

    }
}
