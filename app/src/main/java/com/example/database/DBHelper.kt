package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context:Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
companion object{
    private  val DATABASE_NAME = "studentsView.db"
    private val DATABASE_VERSION = 1

    private val Students = "Students"
    private val id = "id"
    private val firstName = "firstN"
}

    override fun onCreate(db: SQLiteDatabase?) {
        val student = ("CREATE TABLE $Students (" +
                "id PRIMARY KEY," +
                "firstName TEXT")
        db?.execSQL(student)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $Students")
        onCreate(db)
    }

    fun getStudent():ArrayList<Students>{
        val stdList:ArrayList<Students> = ArrayList()

        val query = "SELECT $id, $firstName FROM $Students"
        val db = this.readableDatabase

        val cursor:Cursor?
        cursor = db.rawQuery(query, null)

        var id:String
        var firstName:String
        if (cursor.moveToFirst()){
            do {
                id = cursor.getString(0)
                firstName = cursor.getString(1)
                val std = Students(id = id, firstName = firstName)
                stdList.add(std)
            }while (cursor.moveToNext())
        }
        return stdList
    }

    fun addStudent(std:Students):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", std.id)
        values.put("firstName", std.firstName)

        val success = db.insert("$Students", null, values)
        db.close()
        return success
    }
}