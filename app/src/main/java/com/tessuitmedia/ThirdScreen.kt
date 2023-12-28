package com.tessuitmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tessuitmedia.adapter.UserAdapter
import com.tessuitmedia.models.UserResponse
import com.tessuitmedia.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ThirdScreen : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        recyclerView = findViewById(R.id.rv_user)
        recyclerView.layoutManager = LinearLayoutManager(this)
        ambilData()
    }

    private fun ambilData(){
        RetrofitClient.getApi().getAllUser().enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val res = response.body()?.data
                if (res != null) {
                    adapter = UserAdapter(res)
                    recyclerView.adapter = adapter
                    Log.d("data:" , res.toString())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("data failed", t.message.toString())
            }

        })
    }
}