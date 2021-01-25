package com.example.stud1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
// In this class calling methods for work with Data base and assign this methods to Buttons

public class MainActivity extends Activity implements View.OnClickListener {

    Button btnAdd, btnRead, btnReadByName, btnUpdate, btnDeleteByName, btnClear;
    EditText etName, etSurname, etFatherName, etAge;
// method that called Buttons and set method OnClick for Buttons
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btn_main_insert);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btn_main_query);
        btnRead.setOnClickListener(this);

        btnReadByName = (Button) findViewById(R.id.btn_main_query_by_name);
        btnReadByName.setOnClickListener(this);

        btnUpdate = (Button) findViewById(R.id.btn_main_update);
        btnUpdate.setOnClickListener(this);

        btnDeleteByName = (Button) findViewById(R.id.btn_main_delete_by_name);
        btnDeleteByName.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btn_main_delete_all);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.et_main_name);
        etSurname = (EditText) findViewById(R.id.et_main_surname);
        etFatherName = (EditText) findViewById(R.id.et_main_fathername);
        etAge = (EditText) findViewById(R.id.et_main_age);

        // создаем объект для создания и управления версиями БД
    }

//method that handles a button click
    @Override
    public void onClick(View v) {
        // получаем данные из полей ввода
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String fatherName = etFatherName.getText().toString();
        String age = etAge.getText().toString();

        switch (v.getId()) {
            case R.id.btn_main_insert:
                Stud1App.getDBHelper().insetToDatabase(name, surname, fatherName, age);
                break;
            case R.id.btn_main_query:
                Stud1App.getDBHelper().queryAllRows();
                break;
            case R.id.btn_main_query_by_name:
                Stud1App.getDBHelper().queryByName(name);
                break;
            case R.id.btn_main_update:
                Stud1App.getDBHelper().updateRow(name, surname, fatherName, age);
                break;
            case R.id.btn_main_delete_by_name:
                Stud1App.getDBHelper().deleteByName(name);
                break;
            case R.id.btn_main_delete_all:
                Stud1App.getDBHelper().deleteAllRows();
                break;
        }
    }

}