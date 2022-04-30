package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "M-Expenses";
    // User Detail
    private static final String USER_TABLE_NAME = "Users";
    public static final String Uid="Uid";
    public static final String UName="UName";
    public static final String UPassword="UPassword";
    // Trip Detail
    private static final String TRIP_TABLE_NAME = "Trips";
    public static final String Tid="Tid";
    public static final String TName="TName";
    public static final String TDestination="TDestination";
    public static final String TDate="TDate";
    public static final String TDescription="TDescription";
    public static final String TRisk="TRisk";
    public static final String TDelete="TDelete";
    public static final String TActive="TActive";
    public static final String TCreatedDate="TCreatedDate";
    public static final String TCreatedBy="TCreatedBy";
    public static final String TModifiedDate="TModifiedDate";
    public static final String TModifiedBy="TModifiedBy";
    public static final String TDeletedDate="TDeletedDate";
    public static final String TDeletedBy="TDeletedBy";
    // Expenses Detail
    private static final String EXPENSES_TABLE_NAME = "Expenses";
    public static final String Eid="Eid";
    public static final String EType="EType";
    public static final String EAmount="EAmount";
    public static final String EDate="EDate";
    public static final String EComment="EComment";
    public static final String EDelete="EDelete";
    public static final String EActive="EActive";
    public static final String ECreatedDate="ECreatedDate";
    public static final String ECreatedBy="ECreatedBy";
    public static final String EModifiedDate="EModifiedDate";
    public static final String EModifiedBy="EModifiedBy";
    public static final String EDeletedDate="EDeletedDate";
    public static final String EDeletedBy="EDeletedBy";


    private SQLiteDatabase database;

    private static final String DATABASE_CREATE_USER_TABLE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT, " +
                    "   %s TEXT)",
            USER_TABLE_NAME, Uid, UName, UPassword);

    private static final String DATABASE_CREATE_TRIP_TABLE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT,"+
                    "   %s TEXT,"+
                    "   %s INTEGER,"+
                    "   %s INTEGER,"+
                    "   %s INTEGER,"+
                    "   %s INTEGER,"+
                    "   %s TEXT,"+
                    "   %s INTEGER,"+
                    "   %s TEXT,"+
                    "   %s INTEGER,"+
                    "   %s TEXT,"+
                    "   %s INTEGER"+
                    " )",
            TRIP_TABLE_NAME, Tid, TName, TDestination, TDate,TDescription,TRisk,TDelete,TActive,
            Uid,TCreatedDate,TCreatedBy,TModifiedDate,TModifiedBy,TDeletedDate,TDeletedBy);

    private static final String DATABASE_CREATE_EXPENSES_TABLE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT, " +
                    "   %s REAL, " +
                    "   %s TEXT, " +
                    "   %s TEXT,"+
                    "   %s INTEGER,"+
                    "   %s INTEGER,"+
                    "   %s TEXT,"+
                    "   %s INTEGER,"+
                    "   %s TEXT,"+
                    "   %s INTEGER,"+
                    "   %s TEXT,"+
                    "   %s INTEGER,"+
                    "   %s INTEGER,"+
                    "   %s INTEGER"+
                    " )",
            EXPENSES_TABLE_NAME, Eid, EType, EAmount,EDate, EComment,EDelete,EActive,ECreatedDate,ECreatedBy,
            EModifiedDate,EModifiedBy,EDeletedDate,EDeletedBy,Tid,Uid);



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
        database = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_USER_TABLE);
        db.execSQL(DATABASE_CREATE_TRIP_TABLE);
        db.execSQL(DATABASE_CREATE_EXPENSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRIP_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSES_TABLE_NAME);

        Log.v(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(db);
    }

    public ArrayList<Trip> getTripName(int uid) {
        Cursor results  = database.rawQuery("SELECT "+Tid+", "+TName+" FROM "+TRIP_TABLE_NAME+"  WHERE "+ Uid +" = "+uid +" AND "+TActive+ " = 1 AND "+TDelete+" = 0", null);

        ArrayList<Trip> TirpList = new ArrayList<>();
        results.moveToFirst();
        while (!results.isAfterLast()) {
            Trip t = new Trip(results.getInt(0),results.getString(1));
            TirpList.add(t);
            results.moveToNext();
        }
        return TirpList;
    }

    public Trip getTripDetail(int tid){
        Trip t = new Trip();
        Cursor results  = database.rawQuery("SELECT * FROM "+TRIP_TABLE_NAME+"  WHERE "+ Tid +" = "+tid +" AND "+TActive+ " = 1 AND "+TDelete+" = 0", null);
        results.moveToFirst();
        while (!results.isAfterLast()) {
            t.Tid=results.getInt(0);
            t.Tname=results.getString(1);
            t.TDestination=results.getString(2);
            t.TDate= results.getString(3);
            t.TDescription=results.getString(4);
            t.TRisk=results.getInt(5)>0?true:false;
            t.TDelete=results.getInt(6)>0?true:false;
            t.TActive=results.getInt(7)>0?true:false;
            t.TCreatedDate=new Date(results.getLong(8));
            t.TCreatedBy=results.getInt(9);
            t.TModifiedDate=new Date(results.getLong(10));
            t.TModifiedBy=results.getInt(11);
            t.TDeletedDate=new Date(results.getLong(12));
            t.TDeletedBy=results.getInt(13);
            results.moveToNext();
        }
        return t;
    }

    public long InsertTripDetail(String tName,String tDestination,String tDate, String tDescription,boolean tRisk, int uid){
        Date date =new Date();

        ContentValues rowValues = new ContentValues();

        rowValues.put(TName, tName);
        rowValues.put(TDestination, tDestination);
        rowValues.put(TDate, String.valueOf(tDate));
        rowValues.put(TDescription, tDescription);
        rowValues.put(TRisk, tRisk);
        rowValues.put(TDelete, 0);
        rowValues.put(TActive, 1);
        rowValues.put(TCreatedDate, String.valueOf(date));
        rowValues.put(TCreatedBy, uid);
        rowValues.put(Uid, uid);

        return database.insertOrThrow(TRIP_TABLE_NAME, null, rowValues);
    }

    public long UpdateTripDetail(int tId, String tName,String tDestination,String tDate, String tDescription,boolean tRisk, int uid){
        Date date =new Date();

        ContentValues rowValues = new ContentValues();

        rowValues.put(TName, tName);
        rowValues.put(TDestination, tDestination);
        rowValues.put(TDate, String.valueOf(tDate));
        rowValues.put(TDescription, tDescription);
        rowValues.put(TRisk, tRisk);
        rowValues.put(TModifiedDate, String.valueOf(date));
        rowValues.put(TModifiedBy, uid);

        return database.update(TRIP_TABLE_NAME, rowValues, " Tid = ?", new String[]{String.valueOf(tId)});
    }

    public long DeleteTripDetail(int tId, int uid){
        Date date =new Date();

        ContentValues rowValues = new ContentValues();

        rowValues.put(TDelete, 1);
        rowValues.put(TActive, 0);
        rowValues.put(TDeletedDate, String.valueOf(date));
        rowValues.put(TDeletedBy, uid);

        ContentValues rowValues1 = new ContentValues();

        rowValues1.put(EDelete, 1);
        rowValues1.put(EActive, 0);
        rowValues1.put(EDeletedDate, String.valueOf(date));
        rowValues1.put(EDeletedBy, uid);

        long returnValue=database.update(EXPENSES_TABLE_NAME, rowValues1, " Tid = ?", new String[]{String.valueOf(tId)});

        return database.update(TRIP_TABLE_NAME, rowValues, " Tid = ?", new String[]{String.valueOf(tId)});
    }

    public long DeleteAllTripDetail(int uid){
        Date date =new Date();

        ContentValues rowValues = new ContentValues();

        rowValues.put(TDelete, 1);
        rowValues.put(TActive, 0);
        rowValues.put(TDeletedDate, String.valueOf(date));
        rowValues.put(TDeletedBy, uid);

        ContentValues rowValues1 = new ContentValues();

        rowValues1.put(EDelete, 1);
        rowValues1.put(EActive, 0);
        rowValues1.put(EDeletedDate, String.valueOf(date));
        rowValues1.put(EDeletedBy, uid);

        long returnValue=database.update(EXPENSES_TABLE_NAME, rowValues1, " Uid = ?", new String[]{String.valueOf(uid)});

        return database.update(TRIP_TABLE_NAME, rowValues, " Uid = ?", new String[]{String.valueOf(uid)});
    }

    public ArrayList<Expenses> getExpensesName(int tid) {
        Cursor results  = database.rawQuery("SELECT "+Eid+", "+EType+" FROM "+EXPENSES_TABLE_NAME+"  WHERE "+ Tid +" = "+tid +" AND "+EActive+ " = 1 AND "+EDelete+" = 0", null);

        ArrayList<Expenses> ExpensesList = new ArrayList<>();
        results.moveToFirst();
        while (!results.isAfterLast()) {
            Expenses t = new Expenses(results.getInt(0),results.getString(1));
            ExpensesList.add(t);
            results.moveToNext();
        }
        return ExpensesList;
    }

    public Expenses getExpenseDetail(int eid){
        Expenses t = new Expenses();
        Cursor results  = database.rawQuery("SELECT * FROM "+EXPENSES_TABLE_NAME+"  WHERE "+ Eid +" = "+eid +" AND "+EActive+ " = 1 AND "+EDelete+" = 0", null);
        results.moveToFirst();
        while (!results.isAfterLast()) {
            t.Eid=results.getInt(0);
            t.EType=results.getString(1);
            t.EAmount= Double.parseDouble(results.getString(2));
            t.EDate= results.getString(3);
            t.EComment=results.getString(4);
            t.EDelete=results.getInt(5)>0?true:false;
            t.EActive=results.getInt(6)>0?true:false;
            t.ECreatedDate=new Date(results.getLong(7));
            t.ECreatedBy=results.getInt(8);
            t.EModifiedDate=new Date(results.getLong(9));
            t.EModifiedBy=results.getInt(10);
            t.EDeletedDate=new Date(results.getLong(11));
            t.EDeletedBy=results.getInt(12);
            results.moveToNext();
        }
        return t;
    }

    public long InsertExpensesDetail(String eType,double eAmount,String eDate, String eComment, int tid, int uid){
        Date date =new Date();
        ContentValues rowValues = new ContentValues();
        rowValues.put(EType, eType);
        rowValues.put(EAmount, eAmount);
        rowValues.put(EDate, String.valueOf(eDate));
        rowValues.put(EComment, eComment);
        rowValues.put(EDelete, 0);
        rowValues.put(EActive, 1);
        rowValues.put(ECreatedDate, String.valueOf(date));
        rowValues.put(ECreatedBy, uid);
        rowValues.put(Tid, tid);
        rowValues.put(Uid, uid);

        return database.insertOrThrow(EXPENSES_TABLE_NAME, null, rowValues);
    }

    public String GetJSONString(int uid){
        boolean isFirstTrip=true;
        String JSONString="{";
        Cursor results  = database.rawQuery("SELECT "+UName+" FROM "+USER_TABLE_NAME+"  WHERE "+ Uid +" = "+uid , null);
        JSONString=JSONString+"\"userId\":";
        results.moveToFirst();
        while (!results.isAfterLast()) {
            JSONString=JSONString+"\""+results.getString(0)+"\",";
            results.moveToNext();
        }
        JSONString=JSONString+"\"detailList\":[{";

        Cursor results1  = database.rawQuery("SELECT * FROM "+TRIP_TABLE_NAME+"  WHERE "+ Uid +" = "+uid +" AND "+TActive+ " = 1 AND "+TDelete+" = 0", null);

        results1.moveToFirst();
        while (!results1.isAfterLast()) {
            if(isFirstTrip){
                //JSONString=JSONString+"{";
                isFirstTrip=false;
            }
            else{
                JSONString=JSONString+"},{";
            }
            JSONString=JSONString+"\"name\":\""+results1.getString(1)+"\",";
            JSONString=JSONString+"\"destination\":\""+results1.getString(2)+"\",";
            JSONString=JSONString+"\"date\":\""+results1.getString(3)+"\",";
            JSONString=JSONString+"\"description\":\""+results1.getString(4)+"\",";
            String risk="";
            if(results1.getInt(5)==0)
                risk="false";
            else
                risk="true";
            JSONString=JSONString+"\"risk_assessment\":\""+risk+"\",";

            JSONString=JSONString+"\"expensesList\":[{";
            boolean isFirstExpenses=true;
            Cursor results2  = database.rawQuery("SELECT * FROM "+EXPENSES_TABLE_NAME+"  WHERE "+ Tid +" = "+results1.getInt(0) +" AND "+EActive+ " = 1 AND "+EDelete+" = 0", null);
            results2.moveToFirst();
            while (!results2.isAfterLast()) {
                if(isFirstExpenses){
                    //JSONString=JSONString+"{";
                    isFirstExpenses=false;
                }
                else{
                    JSONString=JSONString+"},{";
                }
                JSONString=JSONString+"\"expense_type\":\""+results2.getString(1)+"\",";
                JSONString=JSONString+"\"expense_amount\":\""+results2.getDouble(2)+"\",";
                JSONString=JSONString+"\"expense_date\":\""+results2.getString(3)+"\",";
                JSONString=JSONString+"\"expense_comment\":\""+results2.getString(4)+"\"";
                //JSONString=JSONString+"}";
                results2.moveToNext();
            }
            JSONString=JSONString+"}]";
            //JSONString=JSONString+"}";
            results1.moveToNext();
        }

        JSONString=JSONString+"}]";
        JSONString=JSONString+"}";
        return JSONString;
    }

    public ArrayList<Trip> AdvanceSearchList(int uid, String tname, String dest){
        String Query="SELECT "+Tid+", "+TName+" FROM "+TRIP_TABLE_NAME+"  WHERE "+ Uid +" = "+uid +
                " AND "+TActive+ " = 1 AND "+TDelete+" = 0";// AND ( "+TName+" like \"%"+tname+"%\" OR "+TDestination+" like \"%"+dest+"%\" OR "+TDate+" like \"%"+Date+"%\" )";

        if(!tname.isEmpty())
        {
            Query=Query+" AND "+ TName+" like \"%"+tname+"%\"";
        }
        if(!dest.isEmpty())
        {

            Query=Query+" AND "+TDestination+" like \"%"+dest+"%\"";
        }

        Cursor results  = database.rawQuery(Query, null);

        ArrayList<Trip> TirpList = new ArrayList<>();
        results.moveToFirst();
        while (!results.isAfterLast()) {
            Trip t = new Trip(results.getInt(0),results.getString(1));
            TirpList.add(t);
            results.moveToNext();
        }
        return TirpList;
    }

//Sai Raj
    public Boolean insertData(String username, String password)
    {
        //SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UName, username);
        contentValues.put(UPassword, password);
        long result = database.insert("Users", null, contentValues);

        if (result == -1)
        {
            return false;
        } else
        {
            return true;
        }
    }
    public Boolean checkusername(String username)
    {
        //SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from Users where UName = ?",new String[] {username});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int checkusernamepassword(String username ,String password)
    {
        //SQLiteDatabase myDB = this.getWritableDatabase();
        //Cursor cursor = database.rawQuery("select * from Users where UName = ? and UPassword = ?",new String[] {username , password});
        int uid=0;
        Cursor results  = database.rawQuery("select * from Users where UName = ? and UPassword = ?",new String[] {username , password});
        results.moveToFirst();
        while (!results.isAfterLast()) {
            uid=results.getInt(0);
            results.moveToNext();
        }
        return uid;
    }

    public boolean resetpassword(int uid, String oldpassword, String newpassword){
        String rawQuery = "Select * from "+USER_TABLE_NAME+ " Where Uid = " + uid;
        Cursor results  = database.rawQuery(rawQuery,null);
        String pass="";
        results.moveToFirst();
        while (!results.isAfterLast()) {
            pass=results.getString(2);
            results.moveToNext();
        }

        if(oldpassword.equals(pass)) {
            //String rawQuery1 = "UPDATE " + USER_TABLE_NAME + " SET UPassword = \"" + newpassword + "\" Where Uid = " + uid;

            ContentValues rowValues = new ContentValues();
            rowValues.put(UPassword, newpassword);

            database.update(USER_TABLE_NAME, rowValues, " Uid = ?", new String[]{String.valueOf(uid)});
            //Cursor results1 = database.rawQuery(rawQuery1, null);
            return true;
        }
        else{
            return false;
        }

    }

}
