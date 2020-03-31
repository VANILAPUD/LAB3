package com.mirea.lab3_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students.db";
    static final String TABLE_NAME = "student";
    private static final int SCHEMA = 1;
    static final String COLUMN_ID = "_id";
    static final String COLUMN_FULL_NAME = "name";
    static final String COLUMN_TIME_TO_ADD = "time";

    private ArrayList<String> studentsList;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
        fillArrayList();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FULL_NAME + " TEXT, " +
                COLUMN_TIME_TO_ADD + " DATETIME DEFAULT CURRENT_TIME" +
                ");");

        insertData(db);
    }

    public void insertData(SQLiteDatabase db) {
        Random random = new Random();
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        int pos;

        while (randomNumbers.size() != 5) {
            pos = random.nextInt(studentsList.size());
            if (randomNumbers.indexOf(pos) == -1) {
                randomNumbers.add(pos);
            }
        }

        ContentValues values = new ContentValues();
        for (int i : randomNumbers) {
            String name = studentsList.get(i);

            values.put(COLUMN_FULL_NAME, name);
            db.insert(TABLE_NAME, null, values);
        }
    }

    public String getName(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_FULL_NAME + " FROM " + TABLE_NAME + ";", null);
        ArrayList<String> names = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(0);
                names.add(name);
            }
            while(cursor.moveToNext());
        }
        cursor.close();

        for (String name: studentsList) {
            if (names.indexOf(name) == -1) {
                return name;
            }
        }

        return studentsList.get(0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void fillArrayList() {
        studentsList = new ArrayList<>();
        studentsList.add("Александров Александр Иванович");  studentsList.add("Ананьева Мария Александровна");
        studentsList.add("Благирев Михаил Михайлович");      studentsList.add("Галактионов Данил Александрович");
        studentsList.add("Деревлев Егор Сергеевич");         studentsList.add("Дорохин Михаил Александрович");
        studentsList.add("Зубреев Антон Игоревич");          studentsList.add("Иванов Виталий Андреевич");
        studentsList.add("Казакевич Игорь Дмитриевич");      studentsList.add("Кащеев Максим Игоревич");
        studentsList.add("Кубракова Ирина Дмитриевна");      studentsList.add("Лалаян Альберт Григорьевич");
        studentsList.add("Муравкин Даниил Александрович");   studentsList.add("Овчинников Игорь Андреевич");
        studentsList.add("Пылаев Кирилл Дмитриевич");        studentsList.add("Радашевский Дмитрий Дмитриевич");
        studentsList.add("Садовая Софья Сергеевна");         studentsList.add("Симон Никита Андреевич");
        studentsList.add("Соколов Максим Игоревич");         studentsList.add("Сорокин Глеб Олегович");
        studentsList.add("Топоркова Ольга Евгеньевна");      studentsList.add("Федодеев Артем Евгеньевич");
        studentsList.add("Фуфурин Артемий Михайлович");      studentsList.add("Хаустов Иван Кириллович");
        studentsList.add("Хмыз Лев Владимирович");           studentsList.add("Чемерев Егор Михайлович");
        studentsList.add("Чухаев Михаил Михайлович");        studentsList.add("Шаульский Вадим Александрович");
        studentsList.add("Шошников Иван Кириллович");        studentsList.add("Юркевич Глеб Артёмович");
    }
}
