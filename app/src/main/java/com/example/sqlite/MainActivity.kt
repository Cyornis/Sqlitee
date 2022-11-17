package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sqlite.database.DBHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addName =  findViewById<Button>(R.id.addName)
        val printName = findViewById<Button>(R.id.printName)
        val enterName = findViewById<EditText>(R.id.enterName)
        val enterAge = findViewById<EditText>(R.id.enterAge)
        val Name = findViewById<TextView>(R.id.Name)
        val Age = findViewById<TextView>(R.id.Age)
        val deleteBtn = findViewById<Button>(R.id.deleteName)



        addName.setOnClickListener {
            val db = DBHelper(this,null)
            val name =  enterName.text.toString()
            val age =  enterAge.text.toString()

            db.addName(name,age)
            Toast.makeText(this,name+"added to database",Toast.LENGTH_SHORT).show()
            enterName.text.clear()
            enterAge.text.clear()
        }

        printName.setOnClickListener {
            val db = DBHelper(this,null)
            val cursor= db.getName()

            cursor.moveToFirst()
            Name.append(cursor.getColumnIndex(DBHelper.NAME_COL).toString())
            Age.append(cursor.getColumnIndex(DBHelper.AGE_COL).toString())

            while (cursor.moveToNext()){
                Name.append(cursor.getColumnIndex(DBHelper.NAME_COL).toString())
                Age.append(cursor.getColumnIndex(DBHelper.AGE_COL).toString())
            }
            cursor.close()
        }

        deleteBtn.setOnClickListener {
            val db = DBHelper(this,null)
            db.deleteData()
            Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show()
        }
    }
}