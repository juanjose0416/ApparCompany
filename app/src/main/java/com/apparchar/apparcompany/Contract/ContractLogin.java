package com.apparchar.apparcompany.Contract;

public interface ContractLogin {
    interface ViewL{
        void showResult(String info);
        void crearEvento(String nit, String usuario);
    }
    interface PresenterL{
        void validar(String user,String pass);
    }
}
