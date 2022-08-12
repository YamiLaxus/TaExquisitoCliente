package com.phonedev.taexqusito.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.phonedev.taexqusito.R
import com.phonedev.taexqusito.models.Productos

class ProductosTodosAdapter(private val productsList: List<Productos>) :
    RecyclerView.Adapter<ProductosTodosAdapter.ViewHolder>() {

    var onItemClick: ((Productos) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun render(productos: Productos) {
            val img = itemView.findViewById<ImageView>(R.id.ivRound)
            Glide.with(img).load(productos.img).into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_new_rv, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productsList[position]
        holder.render(item)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int = productsList.size


}