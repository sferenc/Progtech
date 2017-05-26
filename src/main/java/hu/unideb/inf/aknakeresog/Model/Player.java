/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.aknakeresog.Model;

/**
 *
 * @author Fricy
 */
public class Player {
    private String name;
    private int bombs;

    public Player() {
        this.name = "";
        this.bombs = -1;
    }
    
    public Player(String Name, int bombs) {
        this.name = Name;
        this.bombs = bombs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public String getName() {
        return name;
    }

    public int getBombs() {
        return bombs;
    }
}
