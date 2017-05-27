/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog.Model;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Fricy
 */
public class TableModelTest {
    TableModel TM;
	
    @Before
    public void setUp() {	
        TM = new TableModel();
    }

    @Test
    public void TableModelConstructorTest(){
        int counter = 0;
        for(int l_row = 0; l_row <=19; l_row++){
        for(int l_col=0; l_col <=19; l_col++){
                if(TM.getTableCells().get(l_row).get(l_col) == 9){
                    counter++;
                }
            }
        }
        assertEquals(25,counter);
    }
    
    @Test
    public void TableModelHashTest(){
        ArrayList<Integer> bombindexes = TM.getBombIndexes();
        int[] tomb = new int[400];
        
        for(int i=0;i<400;i++){
            tomb[i]=0;
        }
        for(int i=0; i<bombindexes.size();i++){
            tomb[bombindexes.get(i)]++;
            assertEquals(1,tomb[bombindexes.get(i)]);
        }    
    }
    
}
