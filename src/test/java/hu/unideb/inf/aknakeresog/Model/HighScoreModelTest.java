/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog.Model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Fricy
 */
public class HighScoreModelTest {
    HighScoreModel hsm;
    
    @Before
    public void setUp() {	
        hsm = new HighScoreModel();
    }

    @Test
    public void PlayerGSTest(){
       
        String bombsE = hsm.getBombsEasy();
        String namesE = hsm.getNamesEasy();
        
        assertEquals(hsm.getBombsEasy(),bombsE);
        assertEquals(hsm.getNamesEasy(),namesE);
        
        String bombsM = hsm.getBombsMedium();
        String namesM = hsm.getNamesMedium();
        
        assertEquals(hsm.getBombsMedium(),bombsM);
        assertEquals(hsm.getNamesMedium(),namesM);
        
        String bombsH = hsm.getBombsHard();
        String namesH = hsm.getNamesHard();
        
        assertEquals(hsm.getBombsHard(),bombsH);
        assertEquals(hsm.getNamesHard(),namesH);
        
        }
}
