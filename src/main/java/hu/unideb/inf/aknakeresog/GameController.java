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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameController implements Initializable {

    private TableController TC;
    private boolean WonOrLost = false;
    
    @FXML
    private Label lbUserName;
    
    @FXML
    private GridPane grPane;
    
    @FXML
    private Text tbomb;
    
    @FXML
    public void handlembNewGame(ActionEvent event) throws IOException{
        newGame(); 
    }
    
    @FXML
    public void handlembMainMenu(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        Scene scene = new Scene(root);        
    
	Stage stage = (Stage) grPane.getScene().getWindow();
		
        stage.setTitle("Aknakereső 1.0");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void handlembExit(ActionEvent event) {       
        exit();
    }
    
    public void exit(){
        Stage stage = (Stage) grPane.getScene().getWindow();
        stage.close();
    
    }
    
    public void newGame() throws IOException{
        destroy();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/GameScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) grPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }
    
     public void gameOver() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GameOverScene.fxml"));
	Parent root = fxmlLoader.load();
        fxmlLoader.<GameOverController>getController().param(this);
        
        Stage gm = new Stage();
        gm.setTitle("Aknakereső 1.0");
        gm.initStyle(StageStyle.DECORATED);
        gm.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        gm.setScene(scene);
        gm.show();
    }
    
    @FXML
    private void mouseClicked(MouseEvent event) throws IOException {
        // lekérem hogy az esemény milyen komponensen következett be            
        int grRow = (int) ((event.getSceneX() - grPane.getLayoutX() - grPane.getPadding().getLeft()) / 20);
        int grCol = (int) ((event.getSceneY() - grPane.getLayoutY() - grPane.getPadding().getTop()) / 20);
            
        if(event.isPrimaryButtonDown()){
           if(TC.isBomb(grRow,grCol))
            gameOver();
        }
        if(event.isSecondaryButtonDown()){
            TC.setFlag(grRow, grCol);
        }
        if(TC.isGameOver()){
            WonOrLost = true;
            gameOver();
        }
    if(TC.getNotFoundBombs()>=0)
        tbomb.setText(Integer.toString(TC.getNotFoundBombs()));
    }
    public boolean isWin(){
        return WonOrLost == true;
    }
    
    public void destroy(){
        TC = null;
        grPane.getChildren().clear();   
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb){
        tbomb.setText(Integer.toString(MainApp.bombs));
        TC = new TableController(grPane,MainApp.bombs);
        if(TC.isGameOver()){
            try {
                gameOver();
            } catch (IOException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lbUserName.setText(MainApp.userName);
    }
}
