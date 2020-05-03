package com.example.apptest1kt.UI.RegisterScreen


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import com.example.apptest1kt.R
import com.example.apptest1kt.Retrofit.Retrofit2
import com.example.apptest1kt.UI.LoginScreeen.LoginScreen
import kotlinx.android.synthetic.main.activity_resgistor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Resistors : AppCompatActivity(),View.OnClickListener {
    private var retrofit2:Retrofit2 = Retrofit2()
    private var listSize:ArrayList<UserId> = ArrayList()
    private var  id:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resgistor)
        btnAction()
        getId()

    }
    private fun getId(){
        retrofit2.connectRetrofit().getUserId().enqueue(object :Callback<List<UserId>>{
            override fun onFailure(call: Call<List<UserId>>, t: Throwable) {
               d("errorGetId",t.toString())
            }

            override fun onResponse(call: Call<List<UserId>>, response: Response<List<UserId>>) {
              listSize.addAll(response.body()!!)
                 id = listSize.size+1
                d("lenghUser",id.toString())
            }

        })


    }
    private fun postUser(){
        val token:String  = id.toString() + edt_username_dk.text.toString()
        if (edt_username_dk.length()!=0&&edt_password_dk.length()!=0&&edt_email.length()!=0&&edt_phoneNumber.length()!=0){
            retrofit2.connectRetrofit().postUser(UserRegistor(edt_username_dk.text.toString(),edt_password_dk.text.toString(),edt_email.text.toString(),edt_phoneNumber.text.toString(),edt_full_name.text.toString(),token)).enqueue(
                object : Callback<List<UserRegistor>> {
                    override fun onFailure(call: Call<List<UserRegistor>>, t: Throwable) {
                        d("errorPost",t.toString())
                    }
                    override fun onResponse(call: Call<List<UserRegistor>>, response: Response<List<UserRegistor>>) {
                      d("dataPost",response.body().toString())
                    }
                })
            Toast.makeText(this,"Đăng ký thành công",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,LoginScreen::class.java))

        }else{
            Toast.makeText(this,"Vui lòng điền đủ thông tin đăng ký",Toast.LENGTH_SHORT).show()
        }

    }
    private fun btnAction(){
        btn_res_dk.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
       if (v?.id==R.id.btn_res_dk){
           postUser()
       }
    }
}
