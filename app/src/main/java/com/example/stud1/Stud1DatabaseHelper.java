package com.example.stud1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
// class with methods KRUD
public class Stud1DatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    private SQLiteDatabase mDB;

    public Stud1DatabaseHelper(Context context) {
        super(context, Constant.DB_TITLE, null, 1);
        this.mContext = context;
    }
// method that create table for Data base
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //todo add method for create database
        Log.d(Constant.LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        sqLiteDatabase.execSQL("create table mytable ("
                + Constant.DB_ROW_ID + " integer primary key autoincrement,"
                + Constant.DB_ROW_NAME + " text,"
                + Constant.DB_ROW_SURNAME + " text,"
                + Constant.DB_ROW_FATHERNAME + " text,"
                + Constant.DB_ROW_AGE + " text"
                + ");");
        this.mDB = sqLiteDatabase;
    }

    //?????????
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //todo add method for insert
    //method that insert rows
    public void insetToDatabase(String name, String surname, String fathername, String age) {
        this.mDB.beginTransaction();
        ContentValues cv = new ContentValues();

        cv.put(Constant.DB_ROW_NAME, name);
        cv.put(Constant.DB_ROW_SURNAME, surname);
        cv.put(Constant.DB_ROW_FATHERNAME, fathername);
        cv.put(Constant.DB_ROW_AGE, age);

        long rowID = this.mDB.insert(Constant.DB_TABLE_TITLE, null, cv);
        Log.d(Constant.LOG_TAG, "row inserted, ID = " + rowID);

        this.mDB.close();
    }

    //todo add method for query all rows of database
    // method that query all rows
    public List<Person> queryAllRows() {
        this.mDB.beginTransaction();
        Log.d(Constant.LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = this.mDB.query(Constant.DB_TABLE_TITLE, null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false

        List<Person> persons = new ArrayList<>();
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(Constant.DB_ROW_ID);
            int nameColIndex = c.getColumnIndex(Constant.DB_ROW_NAME);
            int surnameColIndex = c.getColumnIndex(Constant.DB_ROW_SURNAME);
            int fatherNameColIndex = c.getColumnIndex(Constant.DB_ROW_FATHERNAME);
            int ageColIndex = c.getColumnIndex(Constant.DB_ROW_AGE);

            do {
                // add to list data for any Person
                persons.add(new Person(c.getInt(idColIndex),
                        c.getString(nameColIndex),
                        c.getString(surnameColIndex),
                        c.getString(fatherNameColIndex),
                        c.getString(ageColIndex)));
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d(Constant.LOG_TAG,
                        Constant.DB_ROW_ID + " = " + c.getInt(idColIndex) +
                                ", " + Constant.DB_ROW_NAME + " = " + c.getString(nameColIndex) +
                                ", " + Constant.DB_ROW_SURNAME + " = " + c.getString(surnameColIndex) +
                                ", " + Constant.DB_ROW_FATHERNAME + " = " + c.getString(fatherNameColIndex) +
                                ", " + Constant.DB_ROW_AGE + " = " + c.getString(ageColIndex));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            Log.d(Constant.LOG_TAG, "0 rows");
        c.close();
        this.mDB.close();
        return persons;
    }

    //todo add method for query by name
   // method that query  row  by name
    public Person queryByName(String name) {
        this.mDB.beginTransaction();
        Log.d(Constant.LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = this.mDB.query(Constant.DB_TABLE_TITLE, new String[]{Constant.DB_ROW_NAME}, name, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        Person person = null;

        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(Constant.DB_ROW_ID);
            int nameColIndex = c.getColumnIndex(Constant.DB_ROW_NAME);
            int surnameColIndex = c.getColumnIndex(Constant.DB_ROW_SURNAME);
            int fatherNameColIndex = c.getColumnIndex(Constant.DB_ROW_FATHERNAME);
            int ageColIndex = c.getColumnIndex(Constant.DB_ROW_AGE);

            do {

                person = new Person(c.getInt(idColIndex),
                        c.getString(nameColIndex),
                        c.getString(surnameColIndex),
                        c.getString(fatherNameColIndex),
                        c.getString(ageColIndex));
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d(Constant.LOG_TAG,
                        Constant.DB_ROW_ID + " = " + c.getInt(idColIndex) +
                                ", " + Constant.DB_ROW_NAME + " = " + c.getString(nameColIndex) +
                                ", " + Constant.DB_ROW_SURNAME + " = " + c.getString(surnameColIndex) +
                                ", " + Constant.DB_ROW_FATHERNAME + " = " + c.getString(fatherNameColIndex) +
                                ", " + Constant.DB_ROW_AGE + " = " + c.getString(ageColIndex));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            Log.d(Constant.LOG_TAG, "0 rows");
        c.close();
        this.mDB.close();
        return person;
    }

    //todo add method for delete by name
    //method that delete row by name
    public void deleteByName(String name) {
        this.mDB.beginTransaction();
        Log.d(Constant.LOG_TAG, "--- Clear by name: ---" + name);
        int clearCount = this.mDB.delete(Constant.DB_TABLE_TITLE, name, new String[]{Constant.DB_ROW_NAME});
        Log.d(Constant.LOG_TAG, "deleted by name = " + clearCount);
        this.mDB.close();
    }

    //todo add method for delete all rows
    //method that delete all rows
    public void deleteAllRows() {
        this.mDB.beginTransaction();
        Log.d(Constant.LOG_TAG, "--- Clear" + Constant.DB_TABLE_TITLE + ":---");
        // удаляем все записи
        int clearCount = this.mDB.delete(Constant.DB_TABLE_TITLE, null, null);
        Log.d(Constant.LOG_TAG, "deleted rows count = " + clearCount);
        this.mDB.close();
    }

    //todo add method for update
    //method that update table
    public void updateRow(String name, String surname, String fathername, String age) {
        this.mDB.beginTransaction();
        Log.d(Constant.LOG_TAG, "--- Update" + Constant.DB_TABLE_TITLE + ": ---");
        ContentValues cv = new ContentValues();

        cv.put(Constant.DB_ROW_NAME, name);
        cv.put(Constant.DB_ROW_SURNAME, surname);
        cv.put(Constant.DB_ROW_FATHERNAME, fathername);
        cv.put(Constant.DB_ROW_AGE, age);

        int updatedCount = this.mDB.update(Constant.DB_TABLE_TITLE, cv, name, new String[]{Constant.DB_ROW_NAME});
        Log.d(Constant.LOG_TAG, "update rows count = " + updatedCount);
        this.mDB.close();
    }
}
