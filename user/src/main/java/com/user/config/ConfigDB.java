package com.user.config;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class ConfigDB {

    public static Logger logger = Logger.getLogger(ConfigDB.class);

    @Autowired
    Environment env;

    public Connection getConnection() throws SQLException {
        String url =env.getProperty("spring.datasource.url");
        String user = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");
        Connection con = null;
        try {
            logger.info("Initialisation de la database");
            con = java.sql.DriverManager.getConnection(url,user,password);
            return con;
        }catch (SQLException e){
            e.printStackTrace();
        }
        con.close();
        return null;
    }

    public void closeConnection(Connection con){
        if (con != null){
            try {
                con.close();
                logger.info("Fermeture de la database");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
