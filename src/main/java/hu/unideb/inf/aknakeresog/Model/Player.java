package hu.unideb.inf.aknakeresog.Model;

    /**
     * Egy segéd osztály ami a HS.xml fájlból tárol név és bomba eredmény párost.
     * @author Sándor Ferenc
     */
public class Player {
    
    /**
     * A játékos neve.
     */
    private String name;
    
    /**
     * A játékos által elért eredmény, megtalált bombák száma.
     */
    private int bombs;

    /**
     * A Player osztály konstruktora. 
     * Alapértékek beállítását végzi.
     * 
     */
    public Player() {
        this.name = "";
        this.bombs = -1;
    }
    
    /**
     * A Player osztály konstruktora. 
     * @param _Name A játékos neve
     * @param _bombs A játékos által elért bombaszám.
     * 
     */
    public Player(String _Name, int _bombs) {
        this.name = _Name;
        this.bombs = _bombs;
    }

    /**
     * Beállítja a játékos nevét.
     * @param _name A játékos neve
     */
    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * Beállítja a bombák számát.
     * @param _bombs A bombák száma
     */
    public void setBombs(int _bombs) {
        this.bombs = _bombs;
    }

    /**
     * Egy játékosnevet ad vissza.
     * @return a jatekos neve.
     */
    public String getName() {
        return name;
    }

    /**
     * A játékoshoz tartozó elért bomba számot adja vissza.
     * @return a bombák száma.
     */
    public int getBombs() {
        return bombs;
    }
}
