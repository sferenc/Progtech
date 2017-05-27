package hu.unideb.inf.aknakeresog.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class GameController implements Initializable {

    private static Logger logger = (Logger) LoggerFactory.getLogger(MainController.class);

    private TableController TC;
    private int NumberOfBombs;
    private boolean WonOrLost = false;
    
    @FXML
    private Label lbUserName;
    
    @FXML
    private GridPane grPane;
    
    @FXML
    private Text tbomb;

    public int getTbomb() { // megtalált bombák száma
        NumberOfBombs = TC.getFoundBombs();
        return NumberOfBombs; 
    }
    
    @FXML
    public void handlembNewGame(ActionEvent event) throws IOException{
        newGame(); 
    }
    
    @FXML
    public void handlembMainMenu(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        Scene scene = new Scene(root);        
        
        logger.info("Ugras a Fomenube!");
    
	Stage stage = (Stage) grPane.getScene().getWindow();
		
        stage.setTitle("Aknakereső 1.0");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void handlembExit(ActionEvent event) {       
        exit();
    }
    
    @FXML
    public void handlembHelp(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/HelpScene.fxml"));
	Parent root = fxmlLoader.load();
        
        logger.info("Ugras a jatekszabalyokhoz!");
        
        Stage gm = new Stage();
        gm.setTitle("Aknakereső 1.0");
        gm.initStyle(StageStyle.DECORATED);
        gm.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        gm.setScene(scene);
        gm.show(); 
    }
    
    public void exit(){
        Stage stage = (Stage) grPane.getScene().getWindow();
        stage.close();
    
    }
    
    public void newGame() throws IOException{
        destroy();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/GameScene.fxml"));
        Scene scene = new Scene(root);
        
        logger.info("A jatek elkezdodott!");
        
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
            logger.info("A jatekos teruletet nyitott");
            if(TC.isBomb(grRow,grCol)){
                logger.info("Jatek Vege!");
                gameOver();
            }
        }
        if(event.isSecondaryButtonDown()){
            TC.setFlag(grRow, grCol);
        }
        if(TC.isGameOver()){
            logger.info("A jatekos megnyerte a jatekot");
            WonOrLost = true;
            NumberOfBombs = MainApp.bombs;
            gameOver();
        }
        tbomb.setText(Integer.toString(MainApp.bombs - TC.getFlags()));
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
        TC = new TableController(grPane);
        tbomb.setText(Integer.toString(MainApp.bombs - TC.getFlags()));
        if(TC.isGameOver()){
            try {
                gameOver();
            } catch (IOException ex) {
                logger.error("Jatek vege!");
                logger.error(ex.getMessage());
            }
        }
        lbUserName.setText(MainApp.userName);
    }
}
