package br.anderson.acessosistema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    private Usuariologin user;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onResume(){
        super.onResume();
        Intent intent = new Intent(this, Usuariologin.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    protected void onPause(){
        super.onPause();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Usuariologin.MeuBinder b = (Usuariologin.MeuBinder) iBinder;
        user = b.getService();
    }


    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        user=null;
    }

    public void onClick(View v){
        EditText f1 = (EditText)findViewById(R.id.id1);
        EditText f2 = (EditText)findViewById(R.id.id2);
        String login = f1.getText().toString();
        String senha = f2.getText().toString();
        String status = user.validar(login, senha)?"Acesso autorizado":"Não esta";
        Toast.makeText(this, status, Toast.LENGTH_LONG).show();
    }

}