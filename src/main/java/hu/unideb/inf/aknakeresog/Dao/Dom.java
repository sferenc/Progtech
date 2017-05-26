/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog.Dao;

import hu.unideb.inf.aknakeresog.Model.Player;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dom {
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private File highScoreFile;
    private Document doc;
    private ArrayList<Player> statics; // ideiglenes tároló
    private ArrayList<Player> bestTen;

    public Dom(){
        statics = new ArrayList<>();
        bestTen = new ArrayList<>();
        
        highScoreFile = new File("c:/Users/Fricy/Documents/NetBeansProjects/Aknakeresog/src/main/resources/highscore/HS.xml");

        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            
            if(!highScoreFile.exists()){
                DoCreateFile();
            }
            
            doc = dBuilder.parse(highScoreFile);
        } catch (SAXException ex) {
            Logger.getLogger(Dom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Dom.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
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
            StreamResult result = new StreamResult(highScoreFile);

            transformer.transform(source, result);

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Dom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Dom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    public void DoInsert(String _name, int _bombs, String _mode) throws TransformerConfigurationException, TransformerException{
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
            Transformer transformer = tFactory.newTransformer();
    
            DOMSource source = new DOMSource(doc);
            
            StreamResult result = new StreamResult(highScoreFile);
            transformer.transform(source, result);
    }
    
    public String getNames(String _mode){
        staticsDownload(_mode);
        String l_names = "";
            
            for(int i=0; i<bestTen.size();i++){
                l_names = l_names + bestTen.get(i).getName() + "\n\n";
            }
            return l_names;
    }
    
    public String getBombs(String _mode){
        staticsDownload(_mode);
        String l_bombs = "";
            for(int i=0; i<bestTen.size();i++){
                l_bombs = l_bombs + bestTen.get(i).getBombs() + "\n\n";
            }
        return l_bombs;
    }
}
