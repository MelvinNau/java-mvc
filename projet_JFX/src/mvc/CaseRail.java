/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

/**
 *
 * @author Arthur
 */
public class CaseRail extends Case{
    
    protected Rail r;
    
    public CaseRail(int _x, int _y, String _im, Rail r){ // Constructeur
        super(_x,_y,_im); // attributes de Case
        this.r = r;       // 1 rail
    }
    
    public void setRail(Rail rail){
        this.r = rail;
    }
    
    public Rail getRail(){
        return this.r;
    }
    
}
