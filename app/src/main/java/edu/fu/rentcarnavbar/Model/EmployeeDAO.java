package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Object.Employee;

class EmployeeDAO extends DBOpenHepler {

    public EmployeeDAO(@Nullable Context context) {
        super(context);
    }

    /**
     * The method to get all student from database and add to list
     * @return List data from table User in database
     */
    public List<Employee> getAllEmployee() {
        //khoi tao list student
        List<Employee> list = new ArrayList<>();
        String sqlQuery = " SELECT * FROM employee";
        Cursor result = null;

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            result = db.rawQuery(sqlQuery, null);
            result.moveToFirst();

            if (result.moveToFirst()){
                do {
                    //add data to object
                    Employee employee = new Employee();
                    employee.setId(result.getInt(0));
                    employee.setUsername(result.getString(1));
                    employee.setPassword(result.getString(2));
                    employee.setName(result.getString(3));
                    employee.setEmail(result.getString(4));
                    employee.setAddress(result.getString(5));
                    employee.setStatus(result.getInt(6));
                    list.add(employee);
                } while (result.moveToNext());
            }
        } finally {
            result.close();
        }

        return list;
    }

    /**
     * The method to insert new user into database
     * @param employee
     */
    public void insert(Employee employee) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //put values to context value
        values.put("u_id", employee.getId());
        values.put("u_username", employee.getUsername());
        values.put("u_password", employee.getPassword());
        values.put("u_name", employee.getName());
        values.put("u_email", employee.getEmail());
        values.put("u_address", employee.getAddress());
        values.put("u_status", employee.getStatus());

        //insert vao bang
        db.insert("employee", null, values);

        db.close();
    }

    /**
     * The method to get a user from database by id
     * @param id
     * @return A user is match with passed param id
     */
    public Employee getEmployeeById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query("employee", null, "emp_id = ?", new String[] {String.valueOf(id)}, null, null, null);
            cursor.moveToFirst();
            Employee employee = new Employee(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getInt(6));
            return employee;
        } finally {
            cursor.close();
        }
    }
}
