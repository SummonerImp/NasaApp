package com.example.nasaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;

public class SavedContents extends AppCompatActivity{
    private ListView listView;
    private NasaApodDAO nasaDao;
    private List<NasaApod> nasaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_contents);
        listView = findViewById(R.id.listView);
        nasaDao = new NasaApodDAO(this);
        nasaData = nasaDao.selectAll();
        ArrayAdapter<NasaApod> adapter = new ArrayAdapter<NasaApod>(this, android.R.layout.simple_list_item_1, nasaData);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        final Intent intent = new Intent(this, Result.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NasaApod na = (NasaApod)listView.getItemAtPosition(i);
                intent.putExtra("objNasa", na);
                startActivity(intent);
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_context_saved_content,menu);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo adapterMenu = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final NasaApod naDelete = nasaData.get(adapterMenu.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção").setMessage("Tem certeza que deseja excluir este dado?").setNegativeButton("NÃO", null).setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nasaData.remove(naDelete);
                        nasaDao.delete(naDelete);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void openContent(MenuItem item){
        try {
            AdapterView.AdapterContextMenuInfo adapterMenu = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            NasaApod naOpen = nasaData.get(adapterMenu.position);
            Intent it = new Intent(this, Result.class);
            it.putExtra("objNasa", naOpen);
            startActivity(it);
        }
        catch (Exception e){
            Log.i("erro", e.toString());
        }
    }
}