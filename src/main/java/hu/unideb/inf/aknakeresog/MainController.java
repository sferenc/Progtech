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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Fricy
 */
public class MainController implements Initializable {
    
    @FXML
    private Button btnNewGame;
    
    @FXML
    private Button btnExit;
    
    @FXML
    private void handlebtnNewGame(ActionEvent event) throws IOException{
	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GameScene.fxml"));
	Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root);        
        
	
	Stage stage = (Stage)(btnNewGame.getScene().getWindow());
		
        stage.setTitle("Aknakereső 1.0");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handlebtnSettings() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SettingsScene.fxml"));
	Parent root = fxmlLoader.load();
        
        Stage gm = new Stage();
        gm.setTitle("Aknakereső 1.0");
        gm.initStyle(StageStyle.DECORATED);
        gm.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        gm.setScene(scene);
        gm.show();
    }
    
    @FXML
    private void handlebtnHighScore() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/HighScoreScene.fxml"));
	Parent root = fxmlLoader.load();
        
        Stage gm = new Stage();
        gm.setTitle("Aknakereső 1.0");
        gm.initStyle(StageStyle.DECORATED);
        gm.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        gm.setScene(scene);
        gm.show();
    }
    
    @FXML
    private void handlebtnExit(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
