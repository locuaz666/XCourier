package com.webin.xcourier;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jonathan Malpica on 3/29/2015.
 */
public class ListaRemitosController extends ActionBarActivity {

    private ImageButton btnImgEncender;
    private ImageButton btnImgApagado;
    private TextView lblNroRemiAsigValue;
    private TextView lblUser;
    private TextView lblFecHorIniValue;
    private TextView lblFecHorFinValue;
    private GridView tblRemitos;

    private Resources res;


    private String[] remitos = { "Remito 1", "Remito 2", "Remito 3", "Remito 4",
            "Remito 5", "Remito 6", "Remito 7", "Remito 8", "Remito 9", "Remito 10" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_remitos_view);

        //inicializar variables
        iniVariables();
        cargarLstRemitos();

        lblUser.setText("Jonathan Malpica Núñez");
    }

    private void cargarLstRemitos() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, remitos);
        tblRemitos.setAdapter(adapter);
//        tblRemitos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView parent, View view, int position, long id) {
//                tv1.setText("Población de "+ lv1.getItemAtPosition(position) + " es "+ habitantes[position]);
//            }
//        });

    }

    private void iniVariables() {

        res = getResources();

//        btnImgEncender = (ImageButton)findViewById(R.id.btnImgEncender);
//        btnImgApagado = (ImageButton)findViewById(R.id.btnImgApagado);
//        lblNroRemiAsigValue = (TextView)findViewById(R.id.lblNroRemiAsigValue);
        lblUser = (TextView)findViewById(R.id.lblUser);
//        lblFecHorIniValue = (TextView)findViewById(R.id.lblFecHorIniValue);
//        lblFecHorFinValue = (TextView)findViewById(R.id.lblFecHorFinValue);
        tblRemitos = (GridView)findViewById(R.id.tblRemitos);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio_session, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}