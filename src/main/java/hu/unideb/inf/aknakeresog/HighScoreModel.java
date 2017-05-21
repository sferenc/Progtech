package hu.unideb.inf.aknakeresog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HighScoreModel {
    private final DbConnection dbConnect;
    private String m_bombsEasy = ""; // Megtalált bombák
    private String m_namesEasy = "";

    private String m_bombsMedium = ""; 
    private String m_namesMedium = "";

    private String m_bombsHard = ""; 
    private String m_namesHard = "";

    public HighScoreModel() {
        dbConnect = new DbConnection();
    }
    
    public String getBombsEasy() {
        ResultSet rs = dbConnect.dbSelect("SELECT * FROM hseasy ORDER BY bombs desc LIMIT 10");
        m_bombsEasy = "";
        try {
            while(rs.next()){
                m_bombsEasy = m_bombsEasy + rs.getString("Bombs") + "\n\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m_bombsEasy;
    }

    public String getNamesEasy() {
       ResultSet rs = dbConnect.dbSelect("SELECT * FROM hseasy ORDER BY bombs desc LIMIT 10");
       m_namesEasy = ""; 
       try {
            while(rs.next()){
                m_namesEasy = m_namesEasy + rs.getString("Name") + "\n\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m_namesEasy;
    }

    public String getBombsMedium() {
        ResultSet rs = dbConnect.dbSelect("SELECT * FROM hsmedium ORDER BY bombs desc LIMIT 10");
        m_bombsMedium = "";
        try {
            while(rs.next()){
                m_bombsMedium = m_bombsMedium + rs.getString("Bombs") + "\n\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m_bombsMedium;
    }

    public String getNamesMedium() {
        ResultSet rs = dbConnect.dbSelect("SELECT * FROM hsmedium ORDER BY bombs desc LIMIT 10");
        m_namesMedium = "";
        try {
            while(rs.next()){
                m_namesMedium = m_namesMedium + rs.getString("Name") + "\n\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m_namesMedium;
    }

    public String getBombsHard() {
        ResultSet rs = dbConnect.dbSelect("SELECT * FROM hshard ORDER BY bombs desc LIMIT 10");
        m_bombsHard = "";
        try {
            while(rs.next()){
                m_bombsHard = m_bombsHard + rs.getString("Bombs") + "\n\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m_bombsHard;
    }

    public String getNamesHard() {
        ResultSet rs = dbConnect.dbSelect("SELECT * FROM hshard ORDER BY bombs desc LIMIT 10");
        m_namesHard = "";
        try {
            while(rs.next()){
                m_namesHard = m_namesHard + rs.getString("Name") + "\n\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m_namesHard;
    }
    
}
