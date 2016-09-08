package com.jeon.dbtest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class DBManager extends SQLiteOpenHelper {
    private static DBManager instance;
    private static final String MAIN_CATEGORY_TABLE_CREATE = "create table maincategory (_id integer primary key autoincrement," + "server_id integer," + "name text not null);";
    private static final String KEYWORD_TABLE_CREATE = "create table keyword (_id integer primary key autoincrement," + "keyword_id integer," + "name text not null);";
    private static final String SUB_CATEGORY_TABLE_CREATE = "create table sub_category" + "(_id integer primary key autoincrement," + "sub_server_id integer ," + "name text not null," + "server_id integer);";

    private static final String CATEGORY_KEYWORD_TABLE_CREATE = "create table category_key" + "(_id integer primary key autoincrement," + "keyword_id integer," + "server_id integer );";
    private static final String DATABASE_NAME = "category_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBManager";

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }


    private DBManager() {
        super(MyApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + CategoryKeywordData.MainCateory.TABLE + "(" +
                CategoryKeywordData.MainCateory._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CategoryKeywordData.MainCateory.SERVER_ID + " INTEGER," +
                CategoryKeywordData.MainCateory.CULUMN_NAME + " TEXT);";
        ;
        db.execSQL(sql);
        db.execSQL(CATEGORY_KEYWORD_TABLE_CREATE);
        db.execSQL(KEYWORD_TABLE_CREATE);
        db.execSQL(SUB_CATEGORY_TABLE_CREATE);


        Log.i("db", "디비생성");
    }

    ContentValues values = new ContentValues();

    public long addCategory(int server_id, String category) {
        if (checkMainCategory(category) == -1) {

            SQLiteDatabase db = getWritableDatabase();
            values.clear();
            values.put(CategoryKeywordData.MainCateory.SERVER_ID, server_id);
            values.put(CategoryKeywordData.MainCateory.CULUMN_NAME, category);
            return db.insert(CategoryKeywordData.MainCateory.TABLE, null, values);

        }
        return -1;


    }


    public long addSubCategory(int sub_server_id, String category, int main_server_id) {
        if (checkSubCategory(category) == -1) {

            SQLiteDatabase db = getWritableDatabase();
            values.clear();
            values.put(CategoryKeywordData.SubCategory.SERVER_ID, sub_server_id);
            values.put(CategoryKeywordData.SubCategory.CULUMN_NAME, category);
            values.put(CategoryKeywordData.SubCategory.MAIN_SERVER_ID, main_server_id);
            return db.insert(CategoryKeywordData.SubCategory.TABLE, null, values);

        }
        return -1;

    }

    public long addKeyword(int keyword_id, String keyword) {
        if (checkkeyword(keyword) == -1) {

            SQLiteDatabase db = getWritableDatabase();
            values.clear();
            values.put(CategoryKeywordData.Keyword.SERVER_ID, keyword_id);
            values.put(CategoryKeywordData.Keyword.CULUMN_NAME, keyword);

            return db.insert(CategoryKeywordData.Keyword.TABLE, null, values);

        }
        return -1;

    }

    public long addCategoryKeyword(int keword_id, int main_server_id) {


        SQLiteDatabase db = getWritableDatabase();
        values.clear();
        values.put(CategoryKeywordData.Category_Key.KEYWORD_ID, keword_id);
        values.put(CategoryKeywordData.Category_Key.MAIN_SERVER_ID, main_server_id);

        return db.insert(CategoryKeywordData.Category_Key.TABLE, null, values);


    }

    public long checkkeyword(String keyword) {
        String selection = CategoryKeywordData.Keyword.CULUMN_NAME + "=?";
        String[] args = {"" + keyword};
        String[] colums = {CategoryKeywordData.Keyword.CULUMN_NAME};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(CategoryKeywordData.Keyword.TABLE, colums, selection, args, null, null, null);
        try {
            if (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndex(CategoryKeywordData.Keyword.CULUMN_NAME));
                return id;
            }
        } finally {
            c.close();
        }
        return -1;
    }

    public long checkSubCategory(String category) {
        String selection = CategoryKeywordData.SubCategory.CULUMN_NAME + "=?";
        String[] args = {"" + category};
        String[] colums = {CategoryKeywordData.SubCategory.CULUMN_NAME};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(CategoryKeywordData.SubCategory.TABLE, colums, selection, args, null, null, null);
        try {
            if (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndex(CategoryKeywordData.SubCategory.CULUMN_NAME));
                return id;
            }
        } finally {
            c.close();
        }
        return -1;
    }

    public long checkMainCategory(String category) {
        String selection = CategoryKeywordData.MainCateory.CULUMN_NAME + "=?";
        String[] args = {"" + category};
        String[] colums = {CategoryKeywordData.MainCateory.CULUMN_NAME};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(CategoryKeywordData.MainCateory.TABLE, colums, selection, args, null, null, null);
        try {
            if (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndex(CategoryKeywordData.MainCateory.CULUMN_NAME));
                return id;
            }
        } finally {
            c.close();
        }
        return -1;
    }

    public void getMainCategoryIndex(String main_category) {

    }

//    public List<String> getSubCategoryIndex(int main_server_id) {
//        String selection = CategoryKeywordData.MainCateory.CULUMN_NAME + "=?";
//        String[] args = {"" + main_server_id};
//        String[] colums = {CategoryKeywordData.MainCateory.CULUMN_NAME};
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.query(CategoryKeywordData.MainCateory.TABLE, colums, selection, args, null, null, null);
//        try {
//            if (c.moveToNext()) {
//                long id = c.getLong(c.getColumnIndex(CategoryKeywordData.MainCateory.CULUMN_NAME));
//                return id;
//            }
//        } finally {
//            c.close();
//        }
//
//
//    }

    public void getKeyword(int server_id) {
    }


//소분류 목록 가져오기
    public List<String> getSubCategories(int main_server_id) {
        List<String> categries = new ArrayList<>();
        String selectQuery = "SELECT*FROM" + CategoryKeywordData.MainCateory.TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    categries.add(cursor.getString(1));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }


        return categries;
    }

    // 대분류 목록 가져오기
    public Map<Integer,String> getMainCategories() {
        Map<Integer,String> main_category_map = new HashMap<>();
//        List<Map<String,String>> categoryList = new ArrayList<>();

        String selectQuery = "SELECT*FROM " + CategoryKeywordData.MainCateory.TABLE + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

               main_category_map.put(Integer.parseInt(cursor.getString(1)),cursor.getString(2));

            } while (cursor.moveToNext());
        }
        return main_category_map;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        Log.w(TAG, "Upgrading database from version " + old_version + " to " + new_version
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS keyword");
        onCreate(db);

    }

}

