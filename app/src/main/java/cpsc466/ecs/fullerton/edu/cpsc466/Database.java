package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SAVED_DATA.db";
    private static final String USER_TABLE_NAME = "User_Table";
    private static final String USER_COL_1 = "USERNAME";        // Primary Key for the table
    private static final String USER_COL_2 = "PASSWORD";    // Name for the table

    private static final String SAVED_PLAN_TABLE_NAME = "Saved_Plan_Table";
    private static final String SAVED_PLAN_COL_1 = "SAVED_PLAN_NAME";
    private static final String SAVED_PLAN_COL_2 = "SAVED_PLAN_DATE";
    private static final String SAVED_PLAN_COL_3 = "SAVED_PLAN_ROUT";        // Primary Key for the table
    private static final String SAVED_PLAN_COL_4 = "SAVED_PLAN_DISTANCE";
    private static final String SAVED_PLAN_COL_5 = "SAVED_PLAN_TIME";
    private static final String SAVED_PLAN_COL_6 = "USERNAME";

    public Database(Context context){
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String userTable = "CREATE TABLE " + USER_TABLE_NAME + " (" + USER_COL_1 + " TEXT PRIMARY KEY, " + USER_COL_2 + " TEXT)";
        String routDataTable = "CREATE TABLE " + SAVED_PLAN_TABLE_NAME + " (" + SAVED_PLAN_COL_1 + " TEXT PRIMARY KEY, " + SAVED_PLAN_COL_2 + " TEXT, "
                                                                            + SAVED_PLAN_COL_3 + " TEXT, " + SAVED_PLAN_COL_4 + " TEXT, "
                                                                            + SAVED_PLAN_COL_5 + " TEXT, " + SAVED_PLAN_COL_6 + " TEXT, "
                                                                            + "FOREIGN KEY(" + SAVED_PLAN_COL_6 + ") REFERENCES " + USER_TABLE_NAME + "(" + USER_COL_1 + "))";

        db.execSQL(routDataTable);
        db.execSQL(userTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SAVED_PLAN_TABLE_NAME);
        onCreate(db);
    }

    public void insertNewUser(String newUsername, String newPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_1, newUsername);
        contentValues.put(USER_COL_2, newPassword);
        db.insert(USER_TABLE_NAME, null, contentValues);
        db.close();
    }

    public boolean checkUsername(String newUsername){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.query(
                USER_TABLE_NAME,
                new String [] {USER_COL_1, USER_COL_2},
                USER_COL_1 + "=?",
                new String []{newUsername},
                null,null,null,null);
        if (data != null && data.getCount() > 0)
            data.moveToFirst();
        else {
            db.close();
            return false;
        }

        if(data.getString(0).equals(newUsername)) {
            db.close();
            return true;
        }
        else {
            db.close();
            return false;
        }
    }

    public boolean validateLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.query(
                USER_TABLE_NAME,
                new String [] {USER_COL_1, USER_COL_2},
                USER_COL_1 + "=?",
                new String []{username},
                null,null,null,null);
        if (data != null && data.getCount() > 0)
            data.moveToFirst();
        else {
            db.close();
            return false;
        }

        if(data.getString(1).equals(password)) {
            db.close();
            return true;
        }
        else {
            db.close();
            return false;
        }
    }

    public void insertNewRout(String newSavedPlanName, String newSavedPlanDate, String newSavedRout, String newRoutDistance, String newRoutTime, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SAVED_PLAN_COL_1, newSavedPlanName);
        contentValues.put(SAVED_PLAN_COL_2, newSavedPlanDate);
        contentValues.put(SAVED_PLAN_COL_3, newSavedRout);
        contentValues.put(SAVED_PLAN_COL_4, newRoutDistance);
        contentValues.put(SAVED_PLAN_COL_5, newRoutTime);
        contentValues.put(SAVED_PLAN_COL_6, username);
        db.insert(SAVED_PLAN_TABLE_NAME, null, contentValues);
        db.close();
    }

    public Cursor getListContents(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.query(
                SAVED_PLAN_TABLE_NAME,
                new String [] {SAVED_PLAN_COL_1, SAVED_PLAN_COL_2, SAVED_PLAN_COL_3,
                        SAVED_PLAN_COL_4, SAVED_PLAN_COL_5, SAVED_PLAN_COL_6},
                SAVED_PLAN_COL_6 + "=?",
                new String []{username},
                null,null,null,null);
        db.close();
        return data;
    }

    public void deleteRout(String planName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SAVED_PLAN_TABLE_NAME, SAVED_PLAN_COL_1 + " =?", new String[] {planName});
        db.close();
    }
}
