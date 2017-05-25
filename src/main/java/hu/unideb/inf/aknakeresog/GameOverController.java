/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.xml.transform.TransformerException;
/**
 * FXML Controller class
 *
 * @author Fricy
 */
public class GameOverController implements Initializable {

    private GameController gameController = new GameController();
//    private DbConnection dbConnect =  new DbConnection();
    private Dom dom;
    
    @FXML
    private Button btnGoMenu;
    
    @FXML
    private Button btnNewGame;
    
    @FXML
   private Label lbWonOrLost;
   
    @FXML
    private void handlebtnNewGame() throws IOException {
        gameController.newGame();
        
        Stage stage = (Stage) btnNewGame.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void handlebtnGoMenu(ActionEvent event) throws IOException{
        gameController.exit();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        Scene scene = new Scene(root);        
        
	Stage stage = (Stage)(btnGoMenu.getScene().getWindow());
		
        stage.setTitle("Aknakereső 1.0");
        stage.setScene(scene);
        stage.show();
    }
    
    public void param(GameController _gc){
        gameController = _gc;
        dom = new Dom();
        
        if(gameController.isWin()){
            lbWonOrLost.setText("Nyertél!");
        }else{
            lbWonOrLost.setText("Vesztettél!");
        }
        lbWonOrLost.setAlignment(Pos.CENTER);
//        DataBaseUpload();
        DomUpload();
    }
    
    private void DomUpload(){
        if(MainApp.bombs == 25){
            try {
                dom.DoInsert(MainApp.userName, gameController.getTbomb(), "easy");
            } catch (TransformerException ex) {
                Logger.getLogger(GameOverController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(MainApp.bombs == 50){
            try {
                dom.DoInsert(MainApp.userName, gameController.getTbomb(), "medium");
            } catch (TransformerException ex) {
                Logger.getLogger(GameOverController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(MainApp.bombs == 100){
            try {
                dom.DoInsert(MainApp.userName, gameController.getTbomb(), "hard");
            } catch (TransformerException ex) {
                Logger.getLogger(GameOverController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
