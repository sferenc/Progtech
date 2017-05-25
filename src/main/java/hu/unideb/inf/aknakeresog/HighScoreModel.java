package hu.unideb.inf.aknakeresog;

public class HighScoreModel {
   
    private Dom dom;
    private String m_bombsEasy = ""; // Megtalált bombák
    private String m_namesEasy = "";

    private String m_bombsMedium = ""; 
    private String m_namesMedium = "";

    private String m_bombsHard = ""; 
    private String m_namesHard = "";

    public HighScoreModel(){
        dom = new Dom();
    }
    
    public String getBombsEasy() {
        String mode = "easy";
        m_bombsEasy = dom.getBombs(mode);
        return m_bombsEasy;
    }

    public String getNamesEasy() {
        String mode = "easy";
        m_namesEasy = dom.getNames(mode);
        return m_namesEasy;
    }

    public String getBombsMedium() {
        String mode = "medium";
        m_bombsMedium = dom.getBombs(mode);
        return m_bombsMedium;
    }

    public String getNamesMedium() {
        String mode = "medium";
        m_namesMedium = dom.getNames(mode);
        return m_namesMedium;
    }

    public String getBombsHard() {
        String mode = "hard";
        m_bombsHard = dom.getBombs(mode);
        return m_bombsHard;
    }

    public String getNamesHard() {
        String mode = "hard";
        m_namesHard = dom.getNames(mode);
        return m_namesHard;
    }
    
}
