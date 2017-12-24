/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

/**
 *
 * @author mello
 */
public class Case {
    protected int i;
    protected int j;
    protected String im; // path d'une image
    
    public Case(int _i, int _j, String _im){ // Constructeur
        this.i = _i;
        this.j = _j;
        this.im =_im;
    }
    
    
    //__________________________ACCESSEURS / MUTATEURS__________________________
    
    public int getI(){
        return this.i;
    }
    
    public int getJ(){
        return this.j;
    }
    
    public void setI(int _i){
        this.i = _i;
    }
    
    public void setJ(int _j){
        this.j = _j;
    }
   
    public String getImage(){
        return this.im;
    }
    
    public void setImage(String str){
        this.im = str;
    }
    //__________________________________________________________________________
    
    
    public boolean estLibre (){ // si la case n'est pas une CaseSymbole ou CaseRail
        if ((this instanceof CaseSymbole) || (this instanceof CaseRail)){
            return false;
        }else{
            return true;
        }
    }
    public boolean estRail (){ // si la case n'est pas une CaseRail
        if (this instanceof CaseRail){
            return true;
        }else{
            return false;
        }
    }  
}
