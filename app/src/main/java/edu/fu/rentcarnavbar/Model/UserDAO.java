package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Object.User;

public class UserDAO extends DBOpenHepler {

    SQLiteDatabase db;

    public UserDAO(@Nullable Context context) {

        super(context);
        initData();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        super.onCreate(db);

    }

    public void initData() {
        List<User> list = new ArrayList<>();
        list = getAllUser();

        if(list.size() == 0) {

            User u1 = new User("1", "123", "Minh Tan", "tan@gmail.com", "address", "34567", 1);
            User u2 = new User("2", "1234567", "Minh Tam", "hello@gmail.com", "address2", "34567557", 1);
            insert(u1);
            insert(u2);
        }
    }

    /**
     * The method to get all student from database and add to list
     * @return List data from table User in database
     */
    public List<User> getAllUser() {
        //khoi tao list student
        List<User> list = new ArrayList<>();
        String sqlQuery = " SELECT * FROM user";
        Cursor result = null;

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            result = db.rawQuery(sqlQuery, null);
            result.moveToFirst();

            if (result.moveToFirst()){
                do {
                    //add data to object
                    User user = new User();
                    user.setId(result.getString(0));
                    user.setPhone(result.getString(1));
                    user.setName(result.getString(2));
                    user.setEmail(result.getString(3));
                    user.setAddress(result.getString(4));
                    user.setIdentity(result.getString(5));
                    user.setStatus(result.getInt(6));
                    list.add(user);
                } while (result.moveToNext());
            }
        } finally {
            result.close();
        }

        return list;
    }

    /**
     * The method to insert new user into database
     * @param user
     */
    public void insert(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //put values to context value
        values.put("u_id", user.getId());
        values.put("u_phone", user.getPhone());
        values.put("u_name", user.getName());
        values.put("u_email", user.getEmail());
        values.put("u_address", user.getAddress());
        values.put("u_identity", user.getIdentity());
        values.put("u_status", user.getStatus());


        //insert vao bang
        db.insert("user", null, values);

        db.close();
    }

    /**
     * The method to update user in database
     * @param id
     * @param phone
     * @param address
     */
    public void update(String id, String phone, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //put values to context value
        values.put("u_phone", phone);
        values.put("u_address", address);

        db.update("user", values, "u_id = ?", new String[] { id });
        db.close();
    }

    /**
     * The method to get a user from database by id
     * @param id
     * @return A user is match with passed param id
     */
    public User getUserById(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query("user", null, "u_id = ?", new String[] {id}, null, null, null);
            cursor.moveToFirst();
            User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getInt(6));
            return user;
        } finally {
            cursor.close();
        }
    }
}
