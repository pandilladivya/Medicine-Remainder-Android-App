package medikare.example.divyaPandilla.medikare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by divya Pandilla on 14-03-2016.
 */
public class dbhelp extends SQLiteOpenHelper {

    public static String tableName = "medicine";

    public dbhelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "create table medicine(id integer primary key autoincrement,name text,time text,dosage integer,purpose text);";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String str = "drop table medicine";
        db.execSQL(str);
        onCreate(db);

    }
}
