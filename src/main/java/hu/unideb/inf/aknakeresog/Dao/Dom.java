package hu.unideb.inf.aknakeresog.Dao;

import hu.unideb.inf.aknakeresog.Controller.MainController;
import hu.unideb.inf.aknakeresog.Model.Player;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

    /**
     * Az osztály xml fájlból tölt be és xml fájba menti az adatokat. 
     * @author Sándor Ferenc
     */
public class Dom {
    
    /**
     * A logolásért felelős adattag.
     */
    private static Logger logger = (Logger) LoggerFactory.getLogger(MainController.class);

    /**
     * Az xml feldolgozásáért felelős adattag.
     */
    private DocumentBuilderFactory dbFactory;
    
    /**
     * Az xml feldolgozásáért felelős adattag.
     */
    private DocumentBuilder dBuilder;
    
    /**
     * Az xml fájl.
     */
     private URL highScoreFile;

    
    /**
     * Az xml feldolgozásáért felelős adattag.
     */
    private Document doc;
    
    /**
     * Ideiglenes lista, ami tárolja az xml-ből kinyert adatokat.
     */
    private ArrayList<Player> statics;
    
    /**
     * Lista, ami tárolja az xml-ből kinyert 10 legjobb eredményt.
     */
    private ArrayList<Player> bestTen;

    /**
     * A Dom osztály konstruktora. 
     * Inicializálja az adattagokat.
     */
    public Dom(){
        statics = new ArrayList<>();
        bestTen = new ArrayList<>();
        
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            
            URL highScoreFile = Dom.class.getClassLoader().getResource("/highscore/HS.xml");
            if (highScoreFile != null) {
                DoCreateFile();
            }
            
            doc = dBuilder.parse(getClass().getResourceAsStream("/highscore/HS.xml"));
        } catch (SAXException ex) {
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } catch (ParserConfigurationException ex) {
            logger.error(ex.getMessage());
        }
        
        
    }
    
    /**
     * Abban az esetben ha az xml fájlt nem éri el a konstruktor, ez a metódus létrehozza azt.
     */
    private void DoCreateFile(){
        
        doc = dBuilder.newDocument();
        Element gyokerElem = doc.createElement("highscore"); // létrehoztam de még külön bele kell tenni
        doc.appendChild(gyokerElem); // hozzáadtam
        Element easy = doc.createElement("easy");
        gyokerElem.appendChild(easy);
        Element medium = doc.createElement("medium");
        gyokerElem.appendChild(medium);
        Element hard = doc.createElement("hard");
        gyokerElem.appendChild(hard);

        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer;

            transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(highScoreFile.getFile());

            transformer.transform(source, result);
            logger.info("xml fajl letrehozasa!");

        } catch (TransformerConfigurationException ex) {
            logger.error(ex.getMessage());
        } catch (TransformerException ex) {
            logger.error(ex.getMessage());
        }
    }
    
    /**
     * Lekéri az adott nehézségi szintnek megfelelő eredményeket az xml fájlból.
     * @param _mode nehézségi szint
     */
    private void staticsDownload(String _mode){
        statics.clear();
        
        NodeList nodeList = doc.getChildNodes();
          
            Element hse = (Element) nodeList.item(0);//highscore ban vagyunk 
            NodeList modeList = hse.getElementsByTagName(_mode);
            
            Element mode = (Element) modeList.item(0);//easy ben vagyunk
            NodeList playerList = mode.getElementsByTagName("player");
         
            for(int i=0; i<playerList.getLength();i++){
                Player tmp = new Player();
                Element player = (Element) playerList.item(i);
                tmp.setName(player.getElementsByTagName("name").item(0).getTextContent());
                tmp.setBombs(Integer.parseInt(player.getElementsByTagName("bombs").item(0).getTextContent()));
                statics.add(tmp);
            }
            bestTen();
        
    }
    
    /**
     * Az xml fájlból beolvasott adatokat rendezi bombák száma szerint és kiválasztja az első 10 legjobb eredményt.
     */
    private void bestTen(){
        int iteration, index=0;
        if(statics.size()>=10){
            iteration = 10;
        }else{
            iteration = statics.size();
        }
        
        bestTen.clear();
        while(iteration > 0){
            Player maxPlayer = new Player();
            for(int i=0;i<statics.size();i++){
                if(statics.get(i).getBombs() > maxPlayer.getBombs()){
                    maxPlayer.setName(statics.get(i).getName());
                    maxPlayer.setBombs(statics.get(i).getBombs());
                    index = i;
                }
            }
            statics.remove(index);
            bestTen.add(maxPlayer);
            iteration--;
        }
    }
    
    /**
     * Elementi xml fájlba az adott játékállást.
     * @param _name a játékos neve
     * @param _bombs a megtalált bombák száma
     * @param _mode a nehézségi szint
     */
    public void DoInsert(String _name, int _bombs, String _mode){
        Element newPlayer = doc.createElement("player");
        Element newName = doc.createElement("name");
            newName.setTextContent(_name);
        Element newBombs = doc.createElement("bombs");
            newBombs.setTextContent(String.valueOf(_bombs));

        newPlayer.appendChild(newName);
        newPlayer.appendChild(newBombs);


        Node mode = doc.getElementsByTagName(_mode).item(0);    
        mode.appendChild(newPlayer);

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer;

        try {
            transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(highScoreFile);
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            logger.error(ex.getMessage());
        }
    }
    
    /**
     * Visszaadja a 10 legjobb játékost, az adott nehézségi szinttől függően.
     * @param _mode a nehézségi szint
     * @return a 10 legjobb játékos
     */
    public String getNames(String _mode){
        staticsDownload(_mode);
        String l_names = "";
            
            for(int i=0; i<bestTen.size();i++){
                l_names = l_names + bestTen.get(i).getName() + "\n\n";
            }
            return l_names;
    }
    
    /**
     * Visszaadja a 10 legjobb játékos által elért eredményt egy stringben.
     * @param _mode a nehézségi szint
     * @return 10 legjobb eredmény
     */
    public String getBombs(String _mode){
        staticsDownload(_mode);
        String l_bombs = "";
            for(int i=0; i<bestTen.size();i++){
                l_bombs = l_bombs + bestTen.get(i).getBombs() + "\n\n";
            }
        return l_bombs;
    }
}
