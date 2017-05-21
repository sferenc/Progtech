package hu.unideb.inf.aknakeresog;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbConnection {
    private Connection myConnection;
    private Statement myStatement;
    private ResultSet myResultSet;
    
    public DbConnection() {
        try{ 
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/progtech?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
 
            myStatement = myConnection.createStatement();                    
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public ResultSet dbSelect(String SQLquery){
        try {
            myResultSet = myStatement.executeQuery(SQLquery);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myResultSet; 
    }
}
