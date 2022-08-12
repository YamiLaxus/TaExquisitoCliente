package com.phonedev.taexqusito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.phonedev.taexqusito.adapter.ProductosTodosAdapter
import com.phonedev.taexqusito.databinding.ActivityMainBinding
import com.phonedev.taexqusito.entities.Constants.BASE_URL
import com.phonedev.taexqusito.entities.WebServices
import com.phonedev.taexqusito.models.Productos
import com.phonedev.taexqusito.ui.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var productosList: List<Productos>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.hide()
        val user = FirebaseAuth.getInstance()
        binding.txtName.text = user.currentUser!!.displayName.toString()

        getNewProducts()

        clickButtons()
    }

    private fun clickButtons() {
        binding.btnBebidaCaliente.setOnClickListener {
            getHotDrinking()
        }
        binding.btnBebidaFria.setOnClickListener {
            getCouldDrinking()
        }
        binding.btnPostres.setOnClickListener {
            getPostres()
        }
        binding.btnComidaRapida.setOnClickListener {
            getFastFood()
        }
        binding.btnComidaCompleto.setOnClickListener {
            getFood()
        }
        binding.btnGolocinas.setOnClickListener {
            getGolocinas()
        }
        binding.btnTodo.setOnClickListener {
            getNewProducts()
        }

    }

    private fun getNewProducts() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WebServices::class.java)

        val retrofitData = retrofitBuilder.getAllProducts()
        retrofitData.enqueue(object : Callback<List<Productos>?> {
            override fun onResponse(
                call: Call<List<Productos>?>,
                response: Response<List<Productos>?>
            ) {
                val responseBody = response.body()!!
                val adapter = ProductosTodosAdapter(responseBody)

                productosList = responseBody
                binding.recyclerViewNew.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.recyclerViewNew.adapter = adapter
                binding.recyclerViewNew.setHasFixedSize(true)
                adapter.onItemClick = {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("product", it)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Productos>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getHotDrinking() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WebServices::class.java)

        val retrofitData = retrofitBuilder.getComidaBebidaCaliente()
        retrofitData.enqueue(object : Callback<List<Productos>?> {
            override fun onResponse(
                call: Call<List<Productos>?>,
                response: Response<List<Productos>?>
            ) {
                val responseBody = response.body()!!
                val adapter = ProductosTodosAdapter(responseBody)

                productosList = responseBody
                binding.recyclerViewNew.layoutManager = GridLayoutManager(this@MainActivity, 1)
                binding.recyclerViewNew.isHorizontalScrollBarEnabled
                binding.recyclerViewNew.adapter = adapter
                adapter.onItemClick = {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("product", it)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Productos>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getCouldDrinking() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WebServices::class.java)

        val retrofitData = retrofitBuilder.getComidaBebidaFria()
        retrofitData.enqueue(object : Callback<List<Productos>?> {
            override fun onResponse(
                call: Call<List<Productos>?>,
                response: Response<List<Productos>?>
            ) {
                val responseBody = response.body()!!
                val adapter = ProductosTodosAdapter(responseBody)

                productosList = responseBody
                binding.recyclerViewNew.layoutManager = GridLayoutManager(this@MainActivity, 1)
                binding.recyclerViewNew.isHorizontalScrollBarEnabled
                binding.recyclerViewNew.adapter = adapter
                adapter.onItemClick = {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("product", it)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Productos>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getPostres() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WebServices::class.java)

        val retrofitData = retrofitBuilder.getPostres()
        retrofitData.enqueue(object : Callback<List<Productos>?> {
            override fun onResponse(
                call: Call<List<Productos>?>,
                response: Response<List<Productos>?>
            ) {
                val responseBody = response.body()!!
                val adapter = ProductosTodosAdapter(responseBody)

                productosList = responseBody
                binding.recyclerViewNew.layoutManager = GridLayoutManager(this@MainActivity, 1)
                binding.recyclerViewNew.isHorizontalScrollBarEnabled
                binding.recyclerViewNew.adapter = adapter
                adapter.onItemClick = {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("product", it)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Productos>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getFastFood() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WebServices::class.java)

        val retrofitData = retrofitBuilder.getComidaRapida()
        retrofitData.enqueue(object : Callback<List<Productos>?> {
            override fun onResponse(
                call: Call<List<Productos>?>,
                response: Response<List<Productos>?>
            ) {
                val responseBody = response.body()!!
                val adapter = ProductosTodosAdapter(responseBody)

                productosList = responseBody
                binding.recyclerViewNew.layoutManager = GridLayoutManager(this@MainActivity, 1)
                binding.recyclerViewNew.isHorizontalScrollBarEnabled
                binding.recyclerViewNew.adapter = adapter
                adapter.onItemClick = {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("product", it)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Productos>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getFood() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WebServices::class.java)

        val retrofitData = retrofitBuilder.getComidaComida()
        retrofitData.enqueue(object : Callback<List<Productos>?> {
            override fun onResponse(
                call: Call<List<Productos>?>,
                response: Response<List<Productos>?>
            ) {
                val responseBody = response.body()!!
                val adapter = ProductosTodosAdapter(responseBody)

                productosList = responseBody
                binding.recyclerViewNew.layoutManager = GridLayoutManager(this@MainActivity, 1)
                binding.recyclerViewNew.isHorizontalScrollBarEnabled
                binding.recyclerViewNew.adapter = adapter
                adapter.onItemClick = {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("product", it)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Productos>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getGolocinas() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WebServices::class.java)

        val retrofitData = retrofitBuilder.getComidaGolocinas()
        retrofitData.enqueue(object : Callback<List<Productos>?> {
            override fun onResponse(
                call: Call<List<Productos>?>,
                response: Response<List<Productos>?>
            ) {
                val responseBody = response.body()!!
                val adapter = ProductosTodosAdapter(responseBody)

                productosList = responseBody
                binding.recyclerViewNew.layoutManager = GridLayoutManager(this@MainActivity, 1)
                binding.recyclerViewNew.isHorizontalScrollBarEnabled
                binding.recyclerViewNew.adapter = adapter
                adapter.onItemClick = {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("product", it)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Productos>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}