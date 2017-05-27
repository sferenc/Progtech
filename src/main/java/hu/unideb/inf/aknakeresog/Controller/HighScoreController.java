/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog.Controller;

import hu.unideb.inf.aknakeresog.Model.HighScoreModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HighScoreController implements Initializable {

    private static Logger logger = (Logger) LoggerFactory.getLogger(MainController.class);

    
    private final ToggleGroup group = new ToggleGroup();
    private final HighScoreModel hsm = new HighScoreModel();

    
    @FXML
    private Label lbIdsColumn;
    
    @FXML
    private Label lbNamesColumn;

    @FXML
    private Label lbBombsColumn;
    
    @FXML
    private Label lbNumberOfHS;
    
    @FXML
    private RadioButton rbtnFirst;
    
    @FXML
    private RadioButton rbtnSecond;
    
    @FXML
    private RadioButton rbtnThird;
    
    @FXML
    private void handleRbtnAction(ActionEvent event) {
        RadioButton b = (RadioButton) event.getSource();
        
        if(b.getId().equals("rbtnFirst")){
            
            logger.info("25 bombas eredmenytabla betoltese!");

            lbNumberOfHS.setText("25");
            lbNamesColumn.setText(hsm.getNamesEasy());
            lbBombsColumn.setText(hsm.getBombsEasy());
        }
        if(b.getId().equals("rbtnSecond")){
      
            logger.info("50 bombas eredmenytabla betoltese!");
            
            lbNumberOfHS.setText("50");
            lbNamesColumn.setText(hsm.getNamesMedium());
            lbBombsColumn.setText(hsm.getBombsMedium());
        
        }
        if(b.getId().equals("rbtnThird")){
        
            logger.info("100 bombas eredmenytabla betoltese!");
            
            lbNumberOfHS.setText("100");
            lbNamesColumn.setText(hsm.getNamesHard());
            lbBombsColumn.setText(hsm.getBombsHard());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rbtnFirst.setToggleGroup(group);
            rbtnFirst.setSelected(true);
        rbtnSecond.setToggleGroup(group);
        rbtnThird.setToggleGroup(group);
        String ID = "";
        
        for(int i=10;i>0;i--){
            ID = ID + i + ".\n\n";
        }
        
        logger.info("25 bombas eredmenytabla betoltese!");
        
        lbIdsColumn.setText(ID);
        lbNamesColumn.setText(hsm.getNamesEasy());
        lbBombsColumn.setText(hsm.getBombsEasy());
        
    }    
    
}
