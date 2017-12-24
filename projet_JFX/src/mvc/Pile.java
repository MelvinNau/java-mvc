/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class Pile {
    
    
    protected ArrayList<Case> liste ; //tableau (liste) de cases pour sauvegarder un chemin
    protected CaseSymbole depart;     // 1ère case du chemin
    protected CaseSymbole arrivee;    // dernière case du chemin
    
    
    //____________________________ACCESSEURS / MUTATEURS____________________
    
    public Pile(){
        liste = new ArrayList<Case>() ;
    }
    
    public CaseSymbole getDepart(){
            return this.depart;
    }
    
    public CaseSymbole getArrivee(){
            return this.arrivee;
    }
    
    public void setDepart(CaseSymbole _depart){
        depart = _depart;
    }
    
    public void setArrivee(CaseSymbole _arrivee){
        arrivee = _arrivee;
    }
    

    public Case getElement(int i){
        return liste.get(i);
    }
    
    public ArrayList<Case> list(){
        return liste;
    }  
    
    //______________________________________________________________________
    
    public void put(Case n) { // ajouter un élément à la liste
        liste.add(n);
    }
    
    
    public Case last() { // dernier élément de la liste

        if (estVide()){ 
            throw new AssertionError();
        }
        return liste.get(liste.size() - 1);
    }
  
    public Case beforeLast() { // avant-dernier élément de la liste

        if (estVide()){ 
            throw new AssertionError();
        }
        if (liste.size() < 2)
            return liste.get(0);
        else
            return liste.get(liste.size() - 2);
    }
    
    public Case beforeBL() { // avant-avant-dernier élément de la liste

        if (estVide()){ 
            throw new AssertionError();
        }
        if (liste.size() < 3)
            return liste.get(0);
        else
            return liste.get(liste.size() - 3);
    }

    public int nb() { // taille de la liste
        return liste.size();
    }   
    
    public boolean estVide() {
        return liste.isEmpty();
    }
    
    public void clear() {
        liste = new ArrayList<Case>();
    }
}
