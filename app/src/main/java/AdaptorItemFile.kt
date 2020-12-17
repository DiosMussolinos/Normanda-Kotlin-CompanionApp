package com.example.freshexample

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.normanda_capp.R
import org.w3c.dom.Text

class AdaptorItemFile(private val itemList:ArrayList<Items>): RecyclerView.Adapter<AdaptorItemFile.CusterViewHolder>() {

    class CusterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var img:ImageView = itemView.findViewById(R.id.item_image)
        var name:TextView = itemView.findViewById(R.id.item_text)
        var cost:TextView = itemView.findViewById(R.id.item_value)
        var stat:TextView = itemView.findViewById(R.id.item_stats)
        val lore:TextView = itemView.findViewById(R.id.item_lore)
        val bag:ImageView = itemView.findViewById(R.id.gold_bag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusterViewHolder{

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CusterViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: CusterViewHolder, position: Int) {

        val currentItem = itemList[position]
        holder.img.setImageResource(currentItem.Image)
        holder.name.text = currentItem.name
        holder.cost.text = currentItem.cost
        holder.stat.text = currentItem.stat
        holder.lore.text = currentItem.lore
        holder.bag.setImageResource(currentItem.bag)


//data class Items(val Image:Int, val name:String, val cost:String, val stat:String, val lore:String)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}