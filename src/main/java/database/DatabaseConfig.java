package database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConfig {
    private static final String URL = "spring.datasource.url";
    private static final String USER = "spring.datasource.username";
    private static final String PASSWORD = "spring.datasource.password";
    private static final String DRIVER = "spring.datasource.driver-class-name";

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    public Connection loadDatabase(){

        try(InputStream inputStream = new FileInputStream("src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(inputStream);
            url = properties.getProperty(URL);
            user = properties.getProperty(USER);
            password = properties.getProperty(PASSWORD);
            driver = properties.getProperty(DRIVER);

           Class.forName(driver);
           Connection connection = DriverManager.getConnection(url,user,password);
           if(connection!=null)
            {
                return connection;
            }else{
               System.out.println("Error in Database Connectivity");
           }


        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }
}
