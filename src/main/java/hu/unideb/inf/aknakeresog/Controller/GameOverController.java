package hu.unideb.inf.aknakeresog.Controller;

import hu.unideb.inf.aknakeresog.Dao.Dom;
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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * FXML Controller class
 *
 * @author Sándor Ferenc
 */
public class GameOverController implements Initializable {
    
    private static Logger logger = (Logger) LoggerFactory.getLogger(GameOverController.class);

    private GameController gameController = new GameController();

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
        
        logger.info("Uj jatek kezdodott!");
        
        Stage stage = (Stage) btnNewGame.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void handlebtnGoMenu(ActionEvent event) throws IOException{
        gameController.exit();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        logger.info("Ugras a fomenube!");
        
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
        DomUpload();
    }
    
    private void DomUpload(){
        if(MainApp.bombs == 25){
            logger.info("Jatekallas mentese!");
            dom.DoInsert(MainApp.userName, gameController.getTbomb(), "easy");
        }
        if(MainApp.bombs == 50){
            logger.info("Jatekallas mentese!");
            dom.DoInsert(MainApp.userName, gameController.getTbomb(), "medium");
        }
        if(MainApp.bombs == 100){
            logger.info("Jatekallas mentese!");
            dom.DoInsert(MainApp.userName, gameController.getTbomb(), "hard");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
