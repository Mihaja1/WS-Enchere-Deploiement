package com.example.enchere.connexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionPostgreSQL {
    
    Connection Connect;

    public ConnexionPostgreSQL(){}

    public Connection getConnect(){
        try{
            Class.forName("org.postgresql.Driver");
            this.Connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/enchere", "enchere", "enchere");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return this.Connect;
    }
}
