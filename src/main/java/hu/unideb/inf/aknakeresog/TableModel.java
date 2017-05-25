/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Fricy
 */
public class TableModel {
    public ArrayList<ArrayList<Integer>> m_tableCells;
    public ArrayList<Integer> m_bombIndexes;


    public TableModel() {
        m_tableCells = new ArrayList<>();
        m_bombIndexes = new ArrayList<>();
    
        Random l_rand = new Random();
        Set<Integer> l_set = new HashSet<>();
        int l_row, l_col, l_tmp;
        //kinullázom a tömböt
        for(l_row = 0; l_row < 20; l_row++){
            ArrayList<Integer> l_tableCells = new ArrayList<>();
            for(l_col = 0; l_col < 20; l_col++){    
                l_tableCells.add(0);
            }
            m_tableCells.add(l_tableCells);
        }
        //legenerálom az indexeket hol legyenek bombák a bombIndexesbe
        do{
            l_tmp = l_rand.nextInt(399)+1;   
            l_set.add(l_tmp);     
        }while(l_set.size()<MainApp.bombs);
        m_bombIndexes = new ArrayList<>(l_set);
        //Be is állitom a bombákat a helyükre
        for (Integer l_temp : m_bombIndexes) {
            l_row = l_temp/20;
            l_col = l_temp%20;
            m_tableCells.get(l_col).set(l_row, 9);
        }

        // FELTÖLTÉS
        for(l_row = 0; l_row <=19; l_row++){
            for(l_col=0; l_col <=19; l_col++){
                if(this.m_tableCells.get(l_row).get(l_col) != 9)
                switch(checkNeighbors(l_row,l_col)){
                    case 0:
                        break;
                    case 1:
                        m_tableCells.get(l_row).set(l_col,1);
                        break;
                    case 2:
                        m_tableCells.get(l_row).set(l_col,2);
                        break;
                    case 3:
                        m_tableCells.get(l_row).set(l_col,3);
                        break;
                    case 4:
                        m_tableCells.get(l_row).set(l_col,4);
                        break;    
                    case 5:
                        m_tableCells.get(l_row).set(l_col,5);
                        break;
                    case 6:
                        m_tableCells.get(l_row).set(l_col,6);
                        break;
                    case 7:
                        m_tableCells.get(l_row).set(l_col,7);
                        break;
                    case 8:
                        m_tableCells.get(l_row).set(l_col,8);
                        break;
                    default:
                    break;
                }
            }
        }
    } 
    
    private int checkNeighbors(int _row, int _col){
        int l_numberOfBombs = 0;
        if( _row - 1 >= 0 && _col - 1 >= 0 && this.m_tableCells.get(_row - 1).get(_col - 1) == 9){
            l_numberOfBombs++;
        }
        if( _row - 1 >= 0 && this.m_tableCells.get(_row - 1).get(_col)  == 9){
            l_numberOfBombs++;
        }
        if( _row - 1 >= 0 && _col + 1 <= 19 && this.m_tableCells.get(_row - 1).get(_col + 1) == 9){
            l_numberOfBombs++;
        }
        if( _col - 1 >= 0 && this.m_tableCells.get(_row).get(_col - 1) == 9){
            l_numberOfBombs++;
        }
        if( _col + 1 <= 19 && this.m_tableCells.get(_row).get(_col + 1) == 9){
            l_numberOfBombs++;
        }
        if( _row + 1 <= 19 && _col - 1 >= 0 && this.m_tableCells.get(_row + 1).get(_col - 1) == 9){
            l_numberOfBombs++;
        }
        if( _row + 1 <= 19 && this.m_tableCells.get(_row + 1).get(_col) == 9){
            l_numberOfBombs++;
        }
        if( _row + 1 <= 19 && _col + 1 <= 19 && this.m_tableCells.get(_row + 1).get(_col + 1) == 9){
            l_numberOfBombs++;
        }
        return l_numberOfBombs;
    }
}
