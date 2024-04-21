package com.example.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

 class DbHandler extends SQLiteOpenHelper {
     private static final int Db_version=1;
     private static final String Db_name="users",table_name="user",user_id="id",user_name="name",user_password="password";
    public DbHandler(MainActivity a) {
        super(a,Db_name,null,Db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String create_table=" CREATE TABLE " + table_name + "(" + user_id + " INTEGER PRIMARY KEY, " + user_name + " TEXT, " + user_password + " TEXT" + ")";
        db.execSQL(create_table);
    }

    public void onUpgrade(SQLiteDatabase db,int old_version,int new_version)
    {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);
    }
    public void addUser(User usr) {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(user_name,usr.getName());
        cv.put(user_password,usr.getPassword());
        db.insert(table_name,null,cv);
        db.close();
    }

    public int checkUser(User us) {
        int id=-1;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT id  FROM user WHERE name=? AND password=? ",new String[]
                {
                        us.getName(),us.getPassword()
                });
        if(cur.getCount()>0)
        {
            cur.moveToFirst();
            id=cur.getInt(0);
            cur.close();

        }
        return id;
    }
}
