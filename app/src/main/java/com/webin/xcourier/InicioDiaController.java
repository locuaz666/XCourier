package com.webin.xcourier;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jonathan Malpica on 3/29/2015.
 */
public class InicioDiaController extends ActionBarActivity {

    private ImageButton btnImgEncender;
    private ImageButton btnImgApagado;
    private TextView lblNroRemiAsigValue;
    private TextView lblUser;
    private TextView lblFecHorIniValue;
    private TextView lblFecHorFinValue;
    private TextView lblRemiOffLineValue;

    private Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_dia_view);

        //inicializar variables
        iniVariables();
    }

    private void iniVariables() {

        res = getResources();

        btnImgEncender = (ImageButton)findViewById(R.id.btnImgEncender);
        btnImgApagado = (ImageButton)findViewById(R.id.btnImgApagado);
        lblNroRemiAsigValue = (TextView)findViewById(R.id.lblNroRemiAsigValue);
        lblUser = (TextView)findViewById(R.id.lblUser);
        lblFecHorIniValue = (TextView)findViewById(R.id.lblFecHorIniValue);
        lblFecHorFinValue = (TextView)findViewById(R.id.lblFecHorFinValue);
        lblRemiOffLineValue = (TextView)findViewById(R.id.lblRemiOffLineValue);

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

    public void iniDia(View view){

        String urlString ="";
        try {
            urlString = URLEncoder.encode("http://190.102.134.78/COURIER_WCF/Xcourier.svc/rest/DayEndInit?usr=rfelix&activity=0&gpsLat=4.6778290000&gpsAlti=-74.0523374000&operation=0", "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                Log.d("BookFinder", jsonObject.toString());
            }
            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                Toast.makeText(getApplicationContext(), "Error: "+ statusCode + " "+throwable.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("BookFinder", statusCode + " "+throwable.getMessage());
            }
        });

        lblNroRemiAsigValue.setText("69");
        lblFecHorIniValue.setText(new SimpleDateFormat(res.getString(R.string.format_date)).format(new Date()));
        lblUser.setText("Jonathan Malpica Núñez");
        lblRemiOffLineValue.setText("0");
    }

    public void finDia(View view){
        lblFecHorFinValue.setText(new SimpleDateFormat(res.getString(R.string.format_date)).format(new Date()));

    }

    public void irViewLstEntregas(View view){
        Intent i = new Intent(this, ListaRemitosController.class);
        startActivity(i);
    }

}