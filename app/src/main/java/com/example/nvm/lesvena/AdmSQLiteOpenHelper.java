package com.example.nvm.lesvena;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Mauricio on 8/1/2017.
 */

public class AdmSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lesvena";

    private Context mContext;

    public AdmSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public void createDataBase()  {
        File pathFile = mContext.getDatabasePath(DATABASE_NAME);
        boolean dbExist = checkDataBase(pathFile.getAbsolutePath());
        if(!dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase(pathFile);
            } catch (IOException e) {
                System.out.println("ERROR CREAR DATABASE!" + e.getMessage());
            }
        }
    }

    private boolean checkDataBase(String path) {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch(Exception e){
            // Database doesn't exist
        }
        if(checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase(File pathFile) throws IOException {
        InputStream myInput = mContext.getAssets().open("lesvena.db");
        OutputStream myOutput = new FileOutputStream(pathFile);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    /*
    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();
    }

*/
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
