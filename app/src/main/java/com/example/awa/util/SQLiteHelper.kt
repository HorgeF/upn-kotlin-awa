package com.example.awa.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "awa_alert.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE IF NOT EXISTS usuarios "+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " nombres_usu TEXT NOT NULL, "+
                " dni_usu INTEGER NOT NULL, "+
                " peso_usu INTEGER NOT NULL, "+
                " celular_usu TEXT NOT NULL, "+
                " correo TEXT NOT NULL);"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS personas")
        onCreate(db)
    }
}