package com.phonedev.taexqusito.entities

import com.phonedev.taexqusito.models.Productos
import com.phonedev.taexqusito.models.Tiendas
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface WebServices {
    @GET("mostrar_productos.php")
    fun getAllProducts(): Call<List<Productos>>

    @GET("mostrar_por_categoria.php?tipo_comida=POSTRE")
    fun getPostres(): Call<List<Productos>>

    @GET("mostrar_por_categoria.php?tipo_comida=COMIDA RAPIDA")
    fun getComidaRapida(): Call<List<Productos>>

    @GET("mostrar_por_categoria.php?tipo_comida=BEBIDA FRIA")
    fun getComidaBebidaFria(): Call<List<Productos>>

    @GET("mostrar_por_categoria.php?tipo_comida=BEBIDA CALIENTE")
    fun getComidaBebidaCaliente(): Call<List<Productos>>

    @GET("mostrar_por_categoria.php?tipo_comida=COMIDA")
    fun getComidaComida(): Call<List<Productos>>

    @GET("mostrar_por_categoria.php?tipo_comida=GOLOCINA")
    fun getComidaGolocinas(): Call<List<Productos>>

    @GET
    fun getStore(@Url string: String): Call<List<Tiendas>>
}