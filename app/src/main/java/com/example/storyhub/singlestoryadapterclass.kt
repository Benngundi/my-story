package com.example.storyhub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class singlestoryadapterclass(listData: ArrayList<singlestorymodelaclass?>) : RecyclerView.Adapter<singlestoryadapterclass.ViewHolder>() {
    private var listData: List<singlestorymodelaclass> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.onestoryrecycleviwitm, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is ViewHolder->{
                holder.bind((listData.get(position)))
            }
        }
    }

    class ViewHolder constructor(itemView:View):RecyclerView.ViewHolder(itemView){
        val storytitlet:TextView = itemView.findViewById(R.id.storytitle_onestory)
        val author: TextView = itemView.findViewById(R.id.authorname_onestory)
        val storycontent: TextView = itemView.findViewById(R.id.storycontent_onestory)
        val id: TextView = itemView.findViewById(R.id.txttime)

        fun bind(storytitle: singlestorymodelaclass){
            storytitlet.setText(storytitle.episodename)
            author.setText(storytitle.author)
            storycontent.setText(storytitle.name)
            id.setText(storytitle.id)
        }

    }

    override fun getItemCount(): Int {
        return  listData.size
    }


}




