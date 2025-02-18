package com.example.napp.data.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.napp.data.model.Task;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final SqliteDatabaseHelper database;
    private final SQLiteDatabase db;
    private static DatabaseManager instance;

    private DatabaseManager(Context context) {
        database = SqliteDatabaseHelper.getInstance(context);
        db = database.getWritableDatabase();
    }

    public static synchronized DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    public boolean addData(Task task) {
        ContentValues values = new ContentValues();
        values.put("id", task.getId());
        values.put("image", task.getImage());
        values.put("name", task.getName());
        values.put("description", task.getDescription());
        values.put("position", task.getPosition());
        long result = db.insert(SqliteDatabaseHelper.TABLE_NAME, null, values);

        if (result == -1) {
            Log.d("DatabaseManager", "Lỗi thêm dữ liệu: ID=" + task.getId());
            return false;
        } else {
            Log.d("DatabaseManager", "Thêm dữ liệu thành công: ID=" + task.getId());
            return true;
        }
    }

    public List<Task> getData() {
        List<Task> dataList = new ArrayList<>();
        String sort = " ORDER BY position ASC";
        Cursor cursor = db.rawQuery("SELECT * FROM " + SqliteDatabaseHelper.TABLE_NAME + sort, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0); // ID
                int image = cursor.getInt(1); // image
                String name = cursor.getString(2); // name
                String ds = cursor.getString(3); // description
                int index = cursor.getInt(4); // position

                Log.d("DatabaseManager", "Fetched task: ID=" + id + ", Image=" + image + ", Name=" + name + ", Position=" + index);

                dataList.add(new Task(image, name, ds, index, id));
            }
            cursor.close();
        }

        return dataList;
    }

    public boolean dropTable() {
        //Xoa bang
        String dropTableQuery = "DROP TABLE IF EXISTS " + SqliteDatabaseHelper.TABLE_NAME;
        // Tao lai bang de tranh table = null , do phai upgradeVersion
        String createTable = "CREATE TABLE IF NOT EXISTS " + SqliteDatabaseHelper.TABLE_NAME + " (" +
                "id TEXT PRIMARY KEY, " +
                "image INTEGER, " +
                "name TEXT, " +
                "description TEXT, " +
                "position INTEGER)";
        try {
            db.execSQL(dropTableQuery);
            db.execSQL(createTable);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ;
            return false;
        }
    }

    public boolean deleteData(String id) {
        int result = db.delete(SqliteDatabaseHelper.TABLE_NAME, "id = ?", new String[]{id});
        return result > 0;
    }

    public boolean updatePostion(Task task, int postion) {
        ContentValues values = new ContentValues();
        values.put("position", postion);
        boolean result = db.update(SqliteDatabaseHelper.TABLE_NAME, values, "id = ?", new String[]{task.getId()}) > 0;

        if (result) {
            Log.d("DatabaseManager", "Cập nhật position thành công: name=" + task.getName() + ", Position=" + task.getPosition());
        } else {
            Log.d("DatabaseManager", "Lỗi cập nhật " + task.getPosition() + " : ID=" + task.getId());
        }

        return result;
    }


}
