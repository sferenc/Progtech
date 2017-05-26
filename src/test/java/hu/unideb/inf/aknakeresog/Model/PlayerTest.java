/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog.Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest {
    private Player player = new Player("Test",1);
    
    @Test
    public void PlayerGSTest(){
       
        assertEquals(player.getBombs(),1);
        assertEquals(player.getName(),"Test");
        
        player.setBombs(2);
        player.setName("Test2");
        
        assertEquals(player.getBombs(),2);
        assertEquals(player.getName(),"Test2");
    }
}
