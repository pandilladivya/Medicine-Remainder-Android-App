package medikare.example.divyaPandilla.medikare;

import android.app.Activity;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by divya Pandilla on 20-12-2015.
 */
public class AddMedicine extends Activity {


    public ArrayList results = new ArrayList();
    private String tableName = dbhelp.tableName;
    static SQLiteDatabase db= null;
    EditText name_et,dosage_et,purpose_et,id_et;
    ListView lv;
    Button submit_bt;
    TextView timepicker_tv;
    Calendar calendar = Calendar.getInstance();
    static final int dialog_id=0;//to get appropriate dialog
    int hour=0,minute=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmedicine);



        dbhelp dbh = new dbhelp(this,"kmit.db",null,1);
        db = dbh.getWritableDatabase();


        timepicker_tv = (TextView)findViewById(R.id.timepicker_tv);
        submit_bt = (Button)findViewById(R.id.submit_bt);
        name_et = (EditText)findViewById(R.id.name_et);
        dosage_et = (EditText)findViewById(R.id.dosage_et);
        purpose_et = (EditText)findViewById(R.id.purpose_et);


        showTimePickerDialog();


        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insert();


                // show();


            }
        });
    }



  private void insert() {

       String name = name_et.getText().toString();
        String time = String.valueOf(hour + minute);
        int dosage = Integer.parseInt(dosage_et.getText().toString());
        String purpose= purpose_et.getText().toString();


        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("time",time);
        cv.put("dosage", dosage);
        cv.put("purpose", purpose);
        long r = db.insert("medicine",null,cv);

        if(r==-1)
            Toast.makeText(getApplicationContext(), "Text not inserted", Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(getApplicationContext(), "Text inserted", Toast.LENGTH_LONG).show();

            Log.d("ff",results.toString());
        }


    }


  public void showTimePickerDialog() {
      // bt_submit = (Button)findViewById(R.id.picktime_button);
      timepicker_tv.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              new TimePickerDialog(AddMedicine.this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
          }
      });
    }
    TimePickerDialog.OnTimeSetListener onTimeSetListener=
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int hour_minute) {
                    hour = hourOfDay;
                    minute = hour_minute;
                    timepicker_tv.setText(String.valueOf(hour) + ":" + String.valueOf(minute));

                }
            };

   public void openAndQueryDatabase() {
        try {

            Cursor c = db.rawQuery("SELECT id, name, time FROM " +
                    tableName, null);

            if (c != null ) {
                if (c.moveToFirst()) {
                    do {

                        int id = c.getInt(c.getColumnIndex("id"));
                        String Name = c.getString(c.getColumnIndex("name"));
                        String time = c.getString(c.getColumnIndex("time"));
                      //  int dosage = c.getInt(c.getColumnIndex("dosage"));
                        results.add("ID "+ id +"Name: " + Name + "Time: " + time);
                    }while (c.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            if (db != null)
                db.execSQL("DELETE FROM " + tableName);
            db.close();
        }


    }

    public ArrayList get()
    {
        return results;
    }



}
