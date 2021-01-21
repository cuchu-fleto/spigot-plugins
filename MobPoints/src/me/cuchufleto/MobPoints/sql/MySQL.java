package me.cuchufleto.MobPoints.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private String host = "localhost"; //should all be in config
    private String port = "3306";
    private String database = "cuchufleto_plugins";
    private String username = "root";
    private String password = "";
    private Connection connection;

    public void connect() throws ClassNotFoundException, SQLException {
        if(!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + //url start
                    host + ":" + port + "/" + database + //connection info
                    "?useSSL=false", username, password); //authentication
        }
    }

    public void disconnect(){
        if(isConnected()){
            try{
                connection.close(); //try to close
            } catch (SQLException e){
                e.printStackTrace(); //print error if close doesn't work
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public boolean isConnected(){
        return (connection == null ? false : true);
    }
}
