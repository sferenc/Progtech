/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Fricy
 */
public class TableController{
    private TableModel m_TableModel;
    private ArrayList<ArrayList<ImageView>> m_imageViews;
    
    private Image m_imageBomb = new Image("/icons/bomb.png");
    private Image m_imageRedFlag = new Image("/icons/redFlag.png");
    private Image m_imageWhite = new Image("icons/white.jpg");
    private Image m_image1 = new Image("icons/number1.png");
    private Image m_image2 = new Image("icons/number2.png");
    private Image m_image3 = new Image("icons/number3.png");
    private Image m_image4 = new Image("icons/number4.png");
    private Image m_image5 = new Image("icons/number5.png");
    private Image m_image6 = new Image("icons/number6.png");
    private Image m_image7 = new Image("icons/number7.png");
    private Image m_image8 = new Image("icons/number8.png");
    
    private int m_flags = 0; // kezdetben egy zálszónk sincs
    
    public void setFlag(int _row, int _col){
        if(m_imageViews.get(_row).get(_col).isVisible() == false &&
           (MainApp.bombs - m_flags) > 0){ 
            m_imageViews.get(_row).get(_col).setImage(m_imageRedFlag);
            m_imageViews.get(_row).get(_col).setVisible(true);
            m_flags++;
        }else{ 
           if(m_imageViews.get(_row).get(_col).getImage().equals(m_imageRedFlag)){
            m_imageViews.get(_row).get(_col).setVisible(false);
            m_flags--;
            switch(m_TableModel.m_tableCells.get(_row).get(_col)){
                    case 0:
                        (m_imageViews.get(_row)).get(_col).setImage(m_imageWhite);
                        break;
                    case 1:
                        (m_imageViews.get(_row)).get(_col).setImage(m_image1);
                        break;
                    case 2:
                        (m_imageViews.get(_row)).get(_col).setImage(m_image2);
                        break;
                    case 3:
                        (m_imageViews.get(_row)).get(_col).setImage(m_image3);
                        break;
                    case 4:
                        (m_imageViews.get(_row)).get(_col).setImage(m_image4);
                        break;    
                    case 5:
                        (m_imageViews.get(_row)).get(_col).setImage(m_image5);
                        break;
                    case 6:
                        (m_imageViews.get(_row)).get(_col).setImage(m_image6);
                        break;
                    case 7:
                        (m_imageViews.get(_row)).get(_col).setImage(m_image7);
                        break;
                    case 8:
                        (m_imageViews.get(_row)).get(_col).setImage(m_image8);
                        break;
                    case 9:
                        (m_imageViews.get(_row)).get(_col).setImage(m_imageBomb);
                        break;
                    default:
                    break;
                }
            }
        }
    }
    public int getFlags(){ // flagek száma
        return m_flags;
    }
    public int getFoundBombs(){ // megtalált bombák száma
        int l_bombs = 0;
        for(int l_row = 0; l_row <=19; l_row++){
            for(int l_col=0; l_col <=19; l_col++){
                if(this.m_imageViews.get(l_row).get(l_col).getImage().equals(m_imageBomb)){
                    l_bombs++;
                }
            }
        }
        return MainApp.bombs - l_bombs;
    }
    
    public TableController(GridPane _gp) {
        m_TableModel = new TableModel();
        this.m_imageViews = new ArrayList<>();
  
        int l_row,l_col;
        
        //Mindent feltöltök egy fehér iconnal
        for(l_row = 0; l_row < 20; l_row++){
            ArrayList<ImageView> l_imageViews = new ArrayList<>();
            for(l_col = 0; l_col < 20; l_col++){    
                l_imageViews.add(reSize(new ImageView(m_imageWhite)));
                _gp.add(l_imageViews.get(l_col),l_row,l_col); 
            }
            m_imageViews.add(l_imageViews);
        }
        //Be is állitom a bombákat a helyükre
        for (Integer l_temp : m_TableModel.m_bombIndexes) {
            l_row = l_temp/20;
            l_col = (l_temp%20);
            m_imageViews.get(l_col).get(l_row).setImage(m_imageBomb);
            m_TableModel.m_tableCells.get(l_col).set(l_row, 9);
        }

        // FELTÖLTÉS
        for(l_row = 0; l_row <=19; l_row++){
            for(l_col=0; l_col <=19; l_col++){
                if(m_TableModel.m_tableCells.get(l_row).get(l_col) != 9)
                switch(m_TableModel.m_tableCells.get(l_row).get(l_col)){
                    case 0:
                        break;
                    case 1:
                        (m_imageViews.get(l_row)).get(l_col).setImage(m_image1);
                        break;
                    case 2:
                        (m_imageViews.get(l_row)).get(l_col).setImage(m_image2);
                        break;
                    case 3:
                        (m_imageViews.get(l_row)).get(l_col).setImage(m_image3);
                        break;
                    case 4:
                        (m_imageViews.get(l_row)).get(l_col).setImage(m_image4);
                        break;    
                    case 5:
                        (m_imageViews.get(l_row)).get(l_col).setImage(m_image5);
                        break;
                    case 6:
                        (m_imageViews.get(l_row)).get(l_col).setImage(m_image6);
                        break;
                    case 7:
                        (m_imageViews.get(l_row)).get(l_col).setImage(m_image7);
                        break;
                    case 8:
                        (m_imageViews.get(l_row)).get(l_col).setImage(m_image8);
                        break;
                    default:
                    break;
                }
            }
        }
    }
    
    public boolean isBomb(int _row, int _col){
        if(m_imageViews.get(_row).get(_col).getImage().equals(m_imageBomb)){    
            m_imageViews.get(_row).get(_col).setVisible(true);
            return true;
        }else{
            floodFill(_row,_col);
            return false;
            
        }
    }
     public boolean isFlag(int _row, int _col){
        if(m_imageViews.get(_row).get(_col).getImage().equals(m_imageRedFlag)){    
            return true;
        }else{
            return false;  
        }
    }
    
    private void floodFill(int _row, int _col){
        if(_row>=0 && _row<=19 && _col>=0 && _col<=19){    
            if(m_imageViews.get(_row).get(_col).getImage().equals(m_imageWhite) &&
               m_imageViews.get(_row).get(_col).isVisible() == false){
                m_imageViews.get(_row).get(_col).setVisible(true);
                floodFill(_row-1,_col);
                floodFill(_row+1,_col);
                floodFill(_row,_col-1);
                floodFill(_row,_col+1);
                floodFill(_row-1,_col-1);
                floodFill(_row+1,_col-1);
                floodFill(_row-1,_col+1);
                floodFill(_row+1,_col+1);
            }
            m_imageViews.get(_row).get(_col).setVisible(true);
        }
    }
    
    private ImageView reSize(ImageView _iv){
        _iv.setFitHeight(19);
        _iv.setFitWidth(19);
        _iv.setVisible(false);
        return _iv;
    }
    
    public boolean isGameOver(){
    int l_row, l_col, _tmp = 0;
        for(l_row = 0; l_row <20; l_row++){
            for(l_col=0; l_col <20; l_col++){  
                if((m_imageViews.get(l_row).get(l_col).isVisible() == false) &&
                   (!m_imageViews.get(l_row).get(l_col).getImage().equals(m_imageBomb))){
                   _tmp++;
                }                    
            }
        }
        if(_tmp == 0 ){
            for(l_row = 0; l_row <20; l_row++){
                for(l_col=0; l_col <20; l_col++){  
                    if(m_imageViews.get(l_row).get(l_col).isVisible() == false){}
                        m_imageViews.get(l_row).get(l_col).setVisible(true);
                }
            }
            return true;
        }
        
        return false;
        
    }
}