import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //in initial stage it will null
    static Connection connection=null;

    //getConnection, that is going to setup the connection
    public static Connection getConnection(){
       if(connection!=null){
           //(if the connection instance is not null, it means, already have connection instance)
           return connection;
       }
       //if it is not null, we need 3 values
        String user="root";
        String pswd="PHW#84#jeor";
        String db="searchengineapp";
        return getConnection(user, pswd, db);
    }
    private static Connection getConnection(String user, String pswd, String db){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + user + "&password=" + pswd);
        }
        catch (SQLException | ClassNotFoundException sqlException){
            sqlException.printStackTrace();
        }
        return  connection;
    }
}
