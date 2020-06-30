package com.example.databaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText entered_product_name;
    private TextView db_result;
    DBHelper hamroDBobject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entered_product_name= findViewById(R.id.entered_product_name_id);
        db_result= findViewById(R.id.db_result_id);

        hamroDBobject =new DBHelper(this, null,null,1 );
        db_result =findViewById(R.id.db_result_id);
        printDatabase();
    }

    public void add(View view) {
        //add button click garda kk garnay
        String name= entered_product_name.getText().toString(); ///user la enterd product name lincha
        if(name.isEmpty()){
            Toast.makeText(this, "name empty", Toast.LENGTH_SHORT).show();
        }
        else {
            Products prod = new Products(name);
            Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
            hamroDBobject.insertProduct(prod);
            printDatabase();
        }

    }

    public void delete(View view) {
        //delte button click garda kk garnay
        String name =entered_product_name.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this, "name empty", Toast.LENGTH_SHORT).show();
        }
        else {
            hamroDBobject.removeProduct(name);
            Toast.makeText(this, "product deleted", Toast.LENGTH_SHORT).show();
            printDatabase();
        }
    }

    public void printDatabase(){
        String dbString = hamroDBobject.db_Display();
        db_result.setText(dbString);
        entered_product_name.setText("");

    }
}
