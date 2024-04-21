package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    EditText uname,pswd;
    Button login,cancel;
    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        uname=findViewById(R.id.editText1);
        pswd=findViewById((R.id.editText2));
        login=findViewById(R.id.button1);
        cancel=findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name= uname.getText().toString();
                String password=pswd.getText().toString();
                int id=checkUser(new User(name,password));
                if(id==-1)
                {
                    Snackbar.make(v,"User "+name+" doesnt exit",Snackbar.LENGTH_LONG).show();
                }
                else{
                    Snackbar.make(v,"user "+name+" exists",Snackbar.LENGTH_LONG).show();
                    Intent i=new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(i);
                }
            }
        });
        db=new DbHandler(MainActivity.this);
        db.addUser(new User("Sadath","1234"));
        db.addUser(new User("Samuel","1234"));
    }
    public int checkUser(User user)
    {
        return db.checkUser(user);
    }
}