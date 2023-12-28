package com.tessuitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        val arrowBackButton = findViewById<ImageView>(R.id.btn_panahKiri)
        recyclerView = findViewById(R.id.rv_user)

        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        recyclerView.layoutManager = LinearLayoutManager(this)
        ambilData()

        //btn arrowBack to second screen
        arrowBackButton.setOnClickListener {
            val intent = Intent(this, SecondScreen::class.java)
            startActivity(intent)
        }

        swipeRefreshLayout.setOnRefreshListener {
            ambilData()
        }
    }

    private fun ambilData(){
        RetrofitClient.getApi().getAllUser().enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val res = response.body()?.data
                if (res != null) {
                    adapter = UserAdapter(res)
                    recyclerView.adapter = adapter
                    Log.d("data:" , res.toString())
                    // Berhenti animasi refresh setelah jeda 2 detik
                    Handler().postDelayed({
                        swipeRefreshLayout.isRefreshing = false
                    }, 2000)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("data failed", t.message.toString())
                // Berhenti animasi refresh setelah jeda 2 detik
                Handler().postDelayed({
                    swipeRefreshLayout.isRefreshing = false
                }, 2000)
            }

        })

    }
}