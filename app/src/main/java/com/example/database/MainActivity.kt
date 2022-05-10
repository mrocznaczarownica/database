package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView
    lateinit var sqlHelper:DBHelper

    private var adapter:RecyclerListClasses?=null
    private var std:Students?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sqlHelper = DBHelper(this)

        recyclerView = findViewById(R.id.studentsView)
        adapter = RecyclerListClasses()
        recyclerView.adapter = adapter

        val stdList = sqlHelper.getStudent()
        adapter?.addItems(stdList)
    }

    fun addClick(view: android.view.View) {
        val id:EditText = findViewById(R.id.studentId)
        val firstN:EditText = findViewById(R.id.studentName)

        if(!id.text.toString().isEmpty()&&firstN.text.toString().isEmpty()){
            sqlHelper = DBHelper(this)
            val addId = sqlHelper.addStudent(std = id)
            val addFam = sqlHelper.addStudent(std.firstName = firstN)
            adapter.addItems(add)
        }
    }
}