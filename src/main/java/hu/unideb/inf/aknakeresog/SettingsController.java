/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class SettingsController implements Initializable {

    final ToggleGroup group = new ToggleGroup();    

    @FXML
    private Button btnSave;
    
    @FXML
    private TextField userName;

    @FXML
    private TextField NumberOfBombs;
    
    @FXML
    private Label lbWarn;
    
    @FXML
    private RadioButton rbtnFirst;
    
    @FXML
    private RadioButton rbtnSecond;
    
    @FXML
    private RadioButton rbtnThird;
    
    @FXML
    private RadioButton rbtnFourth;

    @FXML
    private void handlebtnSave(){
        boolean warn = false;
        if(group.getSelectedToggle().equals(rbtnFirst)){
           MainApp.bombs = 25; 
        }
        if(group.getSelectedToggle().equals(rbtnFourth)){
            String l_bombs = NumberOfBombs.getText();
            
            if(l_bombs.equals("")){
                lbWarn.setText("A mező üres!");
                warn=true;

            }else{
                if(isNumber(l_bombs) || (l_bombs.startsWith("-") &&
                                            isNumber(l_bombs.substring(1)))){
                    Integer Szam = Integer.parseInt(l_bombs);
                    if(Szam >= 1 && Szam <= 200){
                        MainApp.bombs = Szam;
                    }else{
                        lbWarn.setText("A megadott érték nincs benne az intervallumban!");
                        warn=true;
                    }
                }else{
                lbWarn.setText("A megadott érték nem szám!");
                warn=true;
                }
            }
        }
        
        if(userName.getText().equals("")){
            lbWarn.setText("A mező üres!");
            warn=true;
        }else{
            MainApp.userName = userName.getText();
        }
        if(!warn){
            Stage stage = (Stage)(btnSave.getScene().getWindow());
            stage.close();
        }
    }
    
    private boolean isNumber(String text){
        boolean tmp = true;
        for(int i=0; i<text.length();i++){
            if(!Character.isDigit(text.charAt(i))){
                tmp = false;
            }
        }
        return tmp;
    }
    
    @FXML
    private void handleRbtnAction(ActionEvent event) {
        RadioButton b = (RadioButton) event.getSource();
        
        if(b.getId().equals("rbtnFirst")){
            MainApp.bombs = 25;
            NumberOfBombs.setDisable(true);
        }
        if(b.getId().equals("rbtnSecond")){
            MainApp.bombs = 50;
            NumberOfBombs.setDisable(true);
        }
        if(b.getId().equals("rbtnThird")){
            MainApp.bombs = 100;
            NumberOfBombs.setDisable(true);
        }
        if(b.getId().equals("rbtnFourth")){
            NumberOfBombs.setDisable(false);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NumberOfBombs.setDisable(true);
        rbtnFirst.setToggleGroup(group);
            rbtnFirst.setSelected(true);
            
        rbtnSecond.setToggleGroup(group);
        rbtnThird.setToggleGroup(group);
        rbtnFourth.setToggleGroup(group);
    }    
    
}
