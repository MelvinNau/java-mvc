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
public class CaseSymbole extends Case{
    
    protected Symbole s;
    
    public CaseSymbole(int _x, int _y, String _im, Symbole s){ // Constructeur
        super(_x,_y,_im); // attributs de Case
        this.s = s;       // 1 symbole
    }
    
    public void setSymbole(Symbole symbole){
        this.s = symbole;
    }
    
    public Symbole getSymbole(){
        return this.s;
    }
}
