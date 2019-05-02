package com.apparchar.apparcompany.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparcompany.Contract.ContractLogin;
import com.apparchar.apparcompany.Presentador.LoginPresenter;
import com.apparchar.apparcompany.R;

public class LoginCompany extends AppCompatActivity implements ContractLogin.ViewL {

    private TextView ver1, ver2;
    private EditText texto2, texto1;
    private ContractLogin.PresenterL presenter;
    public static String nitE="",userE="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_company);
        texto1=findViewById(R.id.texto);
        texto2=findViewById(R.id.texto3);
        presenter=new LoginPresenter(this);

    }

    public void logIn(View view) {
        String usuario=texto1.getText().toString();
        String pass=texto2.getText().toString();
        presenter.validar(usuario,pass);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void registrar(View view) {
        Intent next=new Intent(this, Empresa.class);
        startActivity(next);
    }


    @Override
    public void showResult(String info) {
        Toast.makeText(this,info,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void crearEvento(String nit, String usuario) {
        Intent intent=new Intent(this,crearEvento1.class);
        nitE=nit;
        userE=usuario;
        startActivity(intent);
    }
}
