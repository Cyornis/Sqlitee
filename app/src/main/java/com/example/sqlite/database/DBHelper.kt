package com.example.sqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context:Context,factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "READY TO START THE PROJECT"
        private const val DATABASE_VERSION  = 1
        const val TABLE_NAME = "To_Do_table"
        const val ID_COL = "id"
        const val NAME_COL = "name"
        const val AGE_COL = "age"
    }

    //below method is for creating the database by a sqlite query
    override fun onCreate(db: SQLiteDatabase?) {
      // val query = ( "CREATE TABLE" + TABLE_NAME +"(" +ID_COL+"Integer Primary key," +NAME_COL+ "text" +AGE_COL+"text"+")" )
        val query = """CREATE TABLE $TABLE_NAME ($ID_COL INTEGER PRIMARY KEY , $NAME_COL TEXT, $AGE_COL TEXT);"""
        db?.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      //this method will check if table already exist
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    //this method is for adding data in our database
    fun addName(name:String,age:String){

        val values =  ContentValues()
        values.put(NAME_COL,name)
        values.put(AGE_COL,age)

        val db:SQLiteDatabase = writableDatabase
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    //this method is to get the data from the database
    fun getName():Cursor{

         val db:SQLiteDatabase = readableDatabase
      //   return db.rawQuery("SELECT * FROM" + TABLE_NAME, null)
        return db.rawQuery("SELECT * FROM $TABLE_NAME",null)
    }

}