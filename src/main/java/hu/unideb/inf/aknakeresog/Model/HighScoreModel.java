package hu.unideb.inf.aknakeresog.Model;

import hu.unideb.inf.aknakeresog.Dao.Dom;

    /**
     * Az eredménytáblát implementáló osztály, stringekben tartalmazza az eredménytábla adatait.
     * @author Sándor Ferenc
     *
     */
public class HighScoreModel {
   
    /**
     * Ezen az objektumon keresztül érjük el az xml fájlt.
     */
    private Dom dom;
    
    /**
     * Az könnyű játékmód eredményeinek 3.oszlopa.
     */
    private String m_bombsEasy = "";
    
    /**
     * Az könnyű játékmód eredményeinek 2.oszlopa.
     */
    private String m_namesEasy = "";

    /**
     * Az közepes nehézségű játékmód eredményeinek 3.oszlopa.
     */
    private String m_bombsMedium = "";
    
    /**
     * Az közepes nehézségű játékmód eredményeinek 2.oszlopa.
     */
    private String m_namesMedium = "";

    /**
     * Az nehéz játékmód eredményeinek 3.oszlopa.
     */
    private String m_bombsHard = "";
    
    /**
     * Az nehéz játékmód eredményeinek 2.oszlopa.
     */
    private String m_namesHard = "";

    /**
     * A HighScoreModel osztály konstruktora. 
     * Példányosít egy Dom osztályt, aminek segítségével lekérdezi az eredményeket.
     * 
     */
    public HighScoreModel(){
        dom = new Dom();
    }
    
    /**
     * Visszaadja a könnyű játékmód eredményeinek 3.oszlopát, amit a bombák száma reprezentál. 
     * Az adatok között sortörések találhatóak. 
     * @return a könnyű játékmód eredményeinek 3.oszlopa.
     */
    public String getBombsEasy() {
        String mode = "easy";
        m_bombsEasy = dom.getBombs(mode);
        return m_bombsEasy;
    }

    /**
     * Visszaadja a könnyű játékmód eredményeinek 2.oszlopát, amit a játékosok nevei reprezentálnak. 
     * Az adatok között sortörések találhatóak. 
     * @return a könnyű játékmód eredményeinek 2.oszlopa.
     */
    public String getNamesEasy() {
        String mode = "easy";
        m_namesEasy = dom.getNames(mode);
        return m_namesEasy;
    }

    /**
     * Visszaadja a közepes nehézségű játékmód eredményeinek 3.oszlopát, amit a bombák száma reprezentál. 
     * Az adatok között sortörések találhatóak. 
     * @return a közepes nehézségű játékmód eredményeinek 3.oszlopa.
     */
    public String getBombsMedium() {
        String mode = "medium";
        m_bombsMedium = dom.getBombs(mode);
        return m_bombsMedium;
    }
    
    /**
     * Visszaadja a közepes nehézségű játékmód eredményeinek 2.oszlopát, amit a játékosok nevei reprezentálnak. 
     * Az adatok között sortörések találhatóak. 
     * @return a közepes nehézségű játékmód eredményeinek 2.oszlopa.
     */
    public String getNamesMedium() {
        String mode = "medium";
        m_namesMedium = dom.getNames(mode);
        return m_namesMedium;
    }

    /**
     * Visszaadja az nehéz játékmód eredményeinek 3.oszlopát, amit a bombák száma reprezentál. 
     * Az adatok között sortörések találhatóak. 
     * @return az nehéz játékmód eredményeinek 3.oszlopa.
     */
    public String getBombsHard() {
        String mode = "hard";
        m_bombsHard = dom.getBombs(mode);
        return m_bombsHard;
    }

    /**
     * Visszaadja a nehéz játékmód eredményeinek 2.oszlopát, amit a játékosok nevei reprezentálnak. 
     * Az adatok között sortörések találhatóak. 
     * @return a nehéz játékmód eredményeinek 2.oszlopa.
     */
    public String getNamesHard() {
        String mode = "hard";
        m_namesHard = dom.getNames(mode);
        return m_namesHard;
    }
    
}
