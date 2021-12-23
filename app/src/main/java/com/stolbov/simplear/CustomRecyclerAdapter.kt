package com.stolbov.simplear

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(private var size: Int) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    companion object {
        val KEY_EXTRA = "key"
        var hasPermission = false
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView? = null
        var itemText: TextView? = null

        init {
            itemImage = itemView.findViewById(R.id.image_item)
            itemText = itemView.findViewById(R.id.text_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mainContext = holder.itemView.context
        holder.itemText!!.text =
            holder.itemText!!.context.resources.getStringArray(R.array.items_names)[position]
        val imageArray = arrayOf(R.drawable.table, R.drawable.leika)
        holder.itemImage!!.setImageResource(imageArray[position])
        val modelsArray = arrayOf("table.glb", "leika.glb")
        holder.itemView.setOnClickListener {
            if (hasPermission)
                startARIntent(holder, modelsArray, position)
            else
                Toast.makeText(mainContext, "Необходимо разрешение для работы с камерой", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startARIntent(
        holder: MyViewHolder,
        modelsArray: Array<String>,
        position: Int
    ) {
        val intent = Intent(holder.itemView.context, ARActivity::class.java)
        intent.putExtra(KEY_EXTRA, modelsArray[position])
        holder.itemView.context.startActivity(intent)
    }

    override fun getItemCount(): Int = size


}