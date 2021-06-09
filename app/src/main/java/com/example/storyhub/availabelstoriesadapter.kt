package com.example.storyhub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class availabelstoriesadapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var item:List<availablestoriesmodelclass> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return storyviewHolder(LayoutInflater.from(parent.context).inflate(R.layout.availablestories_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is storyviewHolder->{
                holder.bind(item.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return  item.size
     }
    fun submitlist(storylist:List<availablestoriesmodelclass>){
        item= storylist
    }

    class storyviewHolder constructor(itemView:View):RecyclerView.ViewHolder(itemView){
        val profile:ImageView= itemView.findViewById(R.id.storyprofilephoto)
        val name:TextView= itemView.findViewById(R.id.storytitle_onestory)
        val mssge:TextView= itemView.findViewById(R.id.storycontent_onestory)
        val Time:TextView= itemView.findViewById(R.id.txttime)

        fun bind(storytitle:availablestoriesmodelclass){
            profile.setImageResource(storytitle.image)
            name.setText(storytitle.name)
            mssge.setText(storytitle.message)
            Time.setText(storytitle.time)
        }

    }
}