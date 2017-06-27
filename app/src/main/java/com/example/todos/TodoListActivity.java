package com.example.todos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.todos.data.DatabaseHelper;
import com.example.todos.data.TodosContract;

public class TodoListActivity extends AppCompatActivity {

    String[] itemname ={
            "Get theatre tickets",
            "Order pizza for tonight",
            "Buy groceries",
            "Running session at 19.30",
            "Call Uncle Sam"
    };


    private void updateTodo(){
        int id = 1;
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String [] args = {String.valueOf(id)};
        ContentValues values = new ContentValues();
        values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Call Mr Bond");

    }


    private void readData(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String [] projection = {TodosContract.TodosEntry.COLUMN_TEXT,
                TodosContract.TodosEntry.COLUMN_CREATED,
                TodosContract.TodosEntry.COLUMN_EXPIRED,
                TodosContract.TodosEntry.COLUMN_DONE,
                TodosContract.TodosEntry.COLUMN_CATEGORY};

            String selection = TodosContract.TodosEntry.COLUMN_CATEGORY + " = ?";
            String [] selectionArgs = {"1"};

        Cursor c = db.query(TodosContract.TodosEntry.TABLE_NAME, projection,selection,selectionArgs,null,null,null);

        int i =c.getCount();
        Log.d("Record Count", String.valueOf(i));

        String rowContent = "";
        while(c.moveToNext())
        {
         for(i=0;i<=4; i++){
             rowContent += c.getString(i) + " - ";
         }
         Log.i("Row " + String.valueOf(c.getPosition()), rowContent);
            rowContent= "";
        }
        c.close();


        }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //CreateTodo();

        readData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView lv=(ListView) findViewById(R.id.lvTodos);
//adds the custom layout
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.todo_list_item,
                R.id.tvNote,itemname));
//adds the click event to the listView, reading the content
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(TodoListActivity.this, TodoActivity.class);
                String content= (String) lv.getItemAtPosition(pos);
                intent.putExtra("Content", content);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void CreateTodo() {

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        helper  = new DatabaseHelper(this);
        db = helper.getWritableDatabase();
        //rawQuery
        String query = "INSERT INTO todos ("
                + TodosContract.TodosEntry.COLUMN_TEXT + ","
                + TodosContract.TodosEntry.COLUMN_CATEGORY + ","
                + TodosContract.TodosEntry.COLUMN_CREATED + ","
                + TodosContract.TodosEntry.COLUMN_EXPIRED + ","
                + TodosContract.TodosEntry.COLUMN_DONE + ")"
                + " VALUES (\"Go to the gym\", 1, \"2016-01-01\", \"\", 0)";
        db.execSQL(query);//*/
/*
        //Insert method; returns the todo ID
        ContentValues values = new ContentValues();
        values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Call Mr Bean");
        values.put(TodosContract.TodosEntry.COLUMN_CATEGORY, 1);
        values.put(TodosContract.TodosEntry.COLUMN_CREATED, "2016-01-02");
        values.put(TodosContract.TodosEntry.COLUMN_DONE, 0);
        // insert row
        long todo_id = db.insert(TodosContract.TodosEntry.TABLE_NAME, null, values);
        return todo_id;
        */
    }
}