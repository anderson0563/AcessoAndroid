package br.anderson.acessosistema;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Usuariologin extends Service {

    private final IBinder mbidner = new MeuBinder();

    public class MeuBinder extends Binder {
        Usuariologin getService(){
            return Usuariologin.this;
        }
    }

    public IBinder onBind(Intent intent){
        return mbidner;
    }

    public boolean validar(String login, String senha){
        if(login.equals("admin") && senha.equals("12345678"))
            return true;
        return false;
    }
}