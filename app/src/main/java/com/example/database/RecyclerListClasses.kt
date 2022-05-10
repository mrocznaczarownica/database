package com.example.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerListClasses:RecyclerView.Adapter<RecyclerListClasses.MyVH>() {
    private var stdList:ArrayList<Students> = ArrayList()

    fun addItems(items:ArrayList<Students>){
        this.stdList = items
        notifyDataSetChanged()
    }

    class MyVH(itemView: View):RecyclerView.ViewHolder(itemView) {
        val id:TextView = itemView.findViewById(R.id.studentNum)
        var firstName:TextView = itemView.findViewById(R.id.studentFam)

        fun bindView(std:Students){
            id.text = std.id.toString()
            firstName.text = std.firstName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListClasses.MyVH {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.students_res, parent, false)
        return MyVH(root)
    }

    override fun onBindViewHolder(holder: RecyclerListClasses.MyVH, position: Int) {
        val info = stdList[position]
        holder.bindView(info)
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

}