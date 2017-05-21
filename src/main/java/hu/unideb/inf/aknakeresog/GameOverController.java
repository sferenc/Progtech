/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
/**
 * FXML Controller class
 *
 * @author Fricy
 */
public class GameOverController implements Initializable {

    private GameController gameController = new GameController();
    
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
        if(gameController.isWin()){
            lbWonOrLost.setText("Nyertél!");
        }else{
            lbWonOrLost.setText("Vesztettél!");
        }
        lbWonOrLost.setAlignment(Pos.CENTER);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
     
    }    
    
}
