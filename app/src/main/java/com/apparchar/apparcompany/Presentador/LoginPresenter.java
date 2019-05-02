package com.apparchar.apparcompany.Presentador;

import android.content.Context;

import com.apparchar.apparcompany.Conexion.MyLoopjTask;
import com.apparchar.apparcompany.Conexion.OnLoopjCompleted;
import com.apparchar.apparcompany.Contract.ContractLogin;
import com.apparchar.apparcompany.Modelo.ClienteM;
import com.apparchar.apparcompany.Modelo.EmpresaM;
import com.apparchar.apparcompany.Modelo.EmpresaPKM;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;

public class LoginPresenter implements ContractLogin.PresenterL, OnLoopjCompleted {

    ContractLogin.ViewL vista;
    RequestParams params;
    String nit;
    String usuarioF = "";
    private String sql = "", sql1;
    private ClienteM cliente;
    private EmpresaM empresa;
    private EmpresaPKM empresaPKM;


    public LoginPresenter(ContractLogin.ViewL vista) {
        this.vista = vista;
        cliente = new ClienteM();
        empresa = new EmpresaM();
        empresaPKM = new EmpresaPKM();
    }

    @Override
    public void validar(String usuario, String contrasenia) {
        String resultado = "";
        if (usuario.equals("") || contrasenia.equals(""))
            vista.showResult("Llene todos los campos");
        else {
            params = new RequestParams();
            Gson g = new Gson();
                empresa.setContrasenia(contrasenia);
                empresaPKM.setUsuario(usuario);
                empresa.setEmpresaPK(empresaPKM);
                usuarioF = usuario;
                String envio = g.toJson(empresa);
                params.put("login", envio);

                String nameServlet = "SERVEmpresa";
                MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
                loopjTask.executeLoopjCall();
            }

    }

    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
            JsonElement nit = jo.get("nit");
            JsonElement count = jo.get("count");
            String nitR = nit.getAsString();
            int countR = count.getAsInt();
            if (countR == 1) {
                vista.showResult("Correcto");
                vista.crearEvento(nitR, usuarioF);
            } else {
                vista.showResult("Usuario y/o contraseña incorrectos");
            }
        }
    }

