package com.webin.xcourier;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Marcial Araujo on 3/29/2015.
 */

public class InicioSessionController extends ActionBarActivity implements View.OnClickListener {
Button btnsession;
EditText txtUser;
EditText txtPass;
TextView txtReturn;
LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_session_view);
        btnsession = (Button) findViewById(R.id.btnIngresar);
        txtUser = (EditText) findViewById(R.id.editText);
        txtPass = (EditText) findViewById(R.id.editText2);
        btnsession.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIngresar:
                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();
                if (!user.equals("")&&!pass.equals("")){
                    loginService = new LoginService();
                    loginService.execute(user, pass);
                }else{
                    Toast.makeText(this, "Debe introducir usuario y contraseña", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }

    private class LoginService extends AsyncTask<String, String, Boolean>{
        JSONObject responseJSON;

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean result = true;
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://190.102.134.78/COURIER_WCF/Xcourier.svc/rest/ValidarUsuario");
            post.setHeader("content-type","application/Json");

            JSONObject dato = new JSONObject();
            try {
                dato.put("usr", params[0]);
                dato.put("pass", params[1]);
                dato.put("token", "11f4aebd66702cd867d6f");
                try {
                    StringEntity entity = new StringEntity(dato.toString());
                    post.setEntity(entity);
                    try {
                        HttpResponse resp = httpClient.execute(post);
                        String respStr = EntityUtils.toString(resp.getEntity());
                        JSONObject respJSON = new JSONObject(respStr);
                        if(!respJSON.getBoolean("Exito")){
                            result=false;
                        }else{
                            responseJSON = respJSON;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(Boolean result){
            if(result){
                try {
                    JSONObject datos = responseJSON.getJSONObject("Datos");
                    setContentView(R.layout.inicio_dia_view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
