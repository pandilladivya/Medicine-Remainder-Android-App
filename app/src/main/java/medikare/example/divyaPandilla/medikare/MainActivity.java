package medikare.example.divyaPandilla.medikare;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toolbar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    TextView addmedicine_tv;
    private ArrayList mymed = new ArrayList();
   static SQLiteDatabase db= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addmedicine_tv = (TextView) findViewById(R.id.addmedicine_tv);
        dbhelp dbh = new dbhelp(this,"kmit.db",null,1);
        db = dbh.getWritableDatabase();
        //show();
        displayResultList();


        addmedicine_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddMedicine.class);
                startActivity(i);

            }
        });

    }

   /*public  void show()
    {
        Cursor result = db.rawQuery("select *from medicine", null);
        TableLayout tb = (TableLayout)findViewById(R.id.table);
        tb.removeAllViews();
        if(result.moveToFirst())  // true if only first row exists
        {
            do {
                TableRow tr = new TableRow(getApplicationContext());
                TextView t1 = new TextView(getApplicationContext());
                TextView t2 = new TextView(getApplicationContext());
                TextView t3 = new TextView(getApplicationContext());
                int id = result.getInt(0);
                String name = result.getString(1);
                String  time = result.getString(2);
                t1.setText(id+"   ");
                t2.setText(name+"   ");
                t3.setText(time + "   ");
                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.BLACK);
                t1.setTextSize(20);
                t2.setTextSize(20);
                t3.setTextSize(20);
                tr.addView(t1);
                tr.addView(t2);
                tr.addView(t3);
                tb.addView(tr);
                tb.setBackgroundColor(Color.GRAY);


            }
            while(result.moveToNext());

        }

    }
*/
    private void displayResultList() {

       /* TextView tView = new TextView(this);
        tView.setText("This data is retrieved from the database and only 4 " +
                "of the results are displayed");
        ListView lv = (ListView)findViewById(R.id.list);

        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, AddMedicine.results));

        lv.setTextFilterEnabled(true);*/

        Cursor result = db.rawQuery("select *from medicine", null);

        if(result.moveToFirst())  // true if only first row exists
        {
            do {

                int id = result.getInt(0);
                String name = result.getString(1);
                String  time = result.getString(2);
                String row = String.valueOf(id)+"\n"+name+" "+time;
                mymed.add(row);


            }
            while(result.moveToNext());

        }


        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mymed));
        getListView().setTextFilterEnabled(true);




    }




}
