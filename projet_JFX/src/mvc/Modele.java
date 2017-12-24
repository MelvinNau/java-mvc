/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.Observable;
import mvc.libInterpreteurExpr.Node;
import java.util.Scanner;

/**
 *
 * @author fred
 */
public class Modele extends Observable {
    protected Grille grille;
    protected int numNiveau; // numéro du niveau en cours
    protected int nbNiveaux; // nombre de niveaux

    public Modele () {
        this.grille = new Grille(10,10);
        this.numNiveau = 1; // on commence par le niveau 1
        this.nbNiveaux = 5; // 5 niveaux sont disponibles
    }
    
    
    //______________________________ACCESSEURS / MUTATEURS______________________
    
    public Grille getGrille() {
        return this.grille;       
    }
    
    public void setGrille(Grille niveau){
        
        //On fixe la bonne taille 
        grille.setX(niveau.getX());
        grille.setY(niveau.getY());
        grille.nbChemins = niveau.nbChemins;
        grille.numPile = niveau.numPile;

        for (int i = 0; i < grille.getX() ; i++){
            for (int j = 0; j < grille.getY() ; j++){
                grille.tab[i][j] = niveau.tab[i][j];
            }
        }
        
        for (int i = 0; i< grille.nbChemins;i++){
            grille.chemin[i] = niveau.chemin[i];
        }
    }
    
    public int getNumNiveau() {
        return this.numNiveau;       
    }
    
    public void setNumNiveau(int num){
        this.numNiveau = num;
    }
    
    //__________________________________________________________________________
    
    
    public void initPiles(Grille grille){ // créer le nombre de piles adéquat en fonction du nombre de paires de symboles
        int nbSymboles = 0;
        for (int i = 0; i < grille.getX() ; i++){
            for (int j = 0; j < grille.getY() ; j++){
                if(grille.getCase(i,j) instanceof CaseSymbole){
                    nbSymboles++;
                }
            }
        }
        grille.setNbChemins(nbSymboles/2);
        for (int k = 0; k < nbSymboles ; k++){
            grille.chemin[k] = new Pile();//
        }
    }
            
    //______________________________NIVEAUX DU JEU______________________________
    
    public Grille niveau1() {
        Grille grille1 = new Grille(3,3);
              
        grille1.tab[0][0] = new CaseSymbole(0,0,"File:spirale.png",Symbole.SPIRALE);
        grille1.tab[2][1] = new CaseSymbole(2,1,"File:spirale.png",Symbole.SPIRALE);
        grille1.tab[0][2] = new CaseSymbole(0,2,"File:etoile.png",Symbole.ETOILE);
        grille1.tab[2][2] = new CaseSymbole(2,2,"File:etoile.png",Symbole.ETOILE);
        
        initPiles(grille1);
        
        return grille1;
    }
    
    public Grille niveau2() {

        Grille grille2 = new Grille(4,4);
              
        grille2.tab[0][0] = new CaseSymbole(0,0,"File:triangle.png",Symbole.TRIANGLE);
        grille2.tab[0][3] = new CaseSymbole(0,3,"File:triangle.png",Symbole.TRIANGLE);
        grille2.tab[3][0] = new CaseSymbole(3,0,"File:rond.png",Symbole.ROND);
        grille2.tab[3][3] = new CaseSymbole(3,3,"File:rond.png",Symbole.ROND);
        
        initPiles(grille2);
   
        return grille2;
    }
    
    public Grille niveau3() {
        Grille grille3 = new Grille(5,5);
              
        grille3.tab[0][0] = new CaseSymbole(0,0,"File:spirale.png",Symbole.SPIRALE);
        grille3.tab[2][1] = new CaseSymbole(2,1,"File:spirale.png",Symbole.SPIRALE);
        grille3.tab[0][2] = new CaseSymbole(0,2,"File:etoile.png",Symbole.ETOILE);
        grille3.tab[1][4] = new CaseSymbole(1,4,"File:etoile.png",Symbole.ETOILE);
        grille3.tab[2][2] = new CaseSymbole(2,2,"File:triangle.png",Symbole.TRIANGLE);
        grille3.tab[4][4] = new CaseSymbole(4,4,"File:triangle.png",Symbole.TRIANGLE);
        
        initPiles(grille3);
        
        return grille3;
    }
    
    
    public Grille niveau4() {
        Grille grille4 = new Grille(4,4);
        
        grille4.tab[0][0] = new CaseSymbole(0,0,"File:spirale.png",Symbole.SPIRALE);
        grille4.tab[3][0] = new CaseSymbole(3,0,"File:spirale.png",Symbole.SPIRALE);
        grille4.tab[0][2] = new CaseSymbole(0,2,"File:etoile.png",Symbole.ETOILE);
        grille4.tab[3][2] = new CaseSymbole(3,2,"File:etoile.png",Symbole.ETOILE);
        
        initPiles(grille4);
        
        return grille4;
    }
    
    public Grille niveau5() {
        Grille grille5 = new Grille(7,7);
        
        grille5.tab[0][0] = new CaseSymbole(0,0,"File:etoile.png",Symbole.ETOILE);
        grille5.tab[4][6] = new CaseSymbole(4,6,"File:etoile.png",Symbole.ETOILE);
        grille5.tab[3][2] = new CaseSymbole(3,2,"File:spirale.png",Symbole.SPIRALE);
        grille5.tab[5][1] = new CaseSymbole(5,1,"File:spirale.png",Symbole.SPIRALE);
        grille5.tab[1][5] = new CaseSymbole(1,5,"File:rond.png",Symbole.ROND);
        grille5.tab[6][4] = new CaseSymbole(6,4,"File:rond.png",Symbole.ROND);
        grille5.tab[1][2] = new CaseSymbole(1,2,"File:triangle.png",Symbole.TRIANGLE);
        grille5.tab[6][3] = new CaseSymbole(6,3,"File:triangle.png",Symbole.TRIANGLE);
        
        initPiles(grille5);
        
        return grille5;
    }
    //__________________________________________________________________________
    
    
    public void maj(){ // Mise ajour, notifie l'observeur
        this.setChanged();
        this.notifyObservers();
    }

    //_______________________FONCTIONS DE DRAG AND DROP_________________________    
 
    
    public void startDD(int i, int j, int numPile){ // on démarre le DnD
        if(this.getGrille().getCase(i,j) instanceof CaseSymbole){ // si c'est une CaseSymbole         
            this.getGrille().getChemin(numPile).put(this.getGrille().getCase(i,j)); // on pile la case dans le 1er chemin
            this.getGrille().getChemin(numPile).setDepart((CaseSymbole) this.getGrille().getCase(i, j)); // on sauvegarde la case de départ
        }      
        //this.setChanged();
        //this.notifyObservers();
    }
    

    

    public void enterDD(int i, int j, int numPile) { // on glisse avec le DnD
        
        //On verifie les voisins et si la case et les libres
        if(this.getGrille().getCase(i,j).estLibre() && getGrille().voisin(this.getGrille().getCase(i,j), this.getGrille().chemin[numPile].last())){
            this.getGrille().chemin[numPile].put(this.getGrille().getCase(i,j)); // on pile la case    
        } 
        this.setChanged();
        this.notifyObservers();  
    }
    
    
    
    
    public void stopDD(int i1, int j1, int i2, int j2){
        
        if(this.getGrille().getCase(i2,j2) instanceof CaseSymbole){ // Si la case d'arrivée est une case symbole
            
            if((((CaseSymbole) (this.getGrille().getCase(i1,j1))).getSymbole() == ((CaseSymbole) (this.getGrille().getCase(i2,j2))).getSymbole()) // on vérifie que ce soit le même symbole
                    && (((CaseSymbole) (this.getGrille().getCase(i1,j1))) != ((CaseSymbole) (this.getGrille().getCase(i2,j2))))){ // mais que ce ne soit pas la même case qu'au départ              

                this.getGrille().getChemin(this.getGrille().getNumPile()).put(this.getGrille().getCase(i2,j2)); // on empile car on est arrivé sur une bonne case
                this.getGrille().getChemin(this.getGrille().getNumPile()).setArrivee((CaseSymbole) this.getGrille().getCase(i2, j2)); // on sauvegarde la case d'arrivée
 
            }else{ // si ce n'est pas le bon symbole
                System.out.println("Pas le bon symbole");
                
                for(int i = 0; i < getGrille().chemin[this.getGrille().getNumPile()].nb(); i++){ // boucle pour remplacer les cases 
                    
                    Case pasOk = getGrille().chemin[this.getGrille().getNumPile()].list().get(i);
                    this.getGrille().setCase(pasOk.getI(), pasOk.getJ(), pasOk);
                    this.setChanged();
                    this.notifyObservers();
                } 
                getGrille().chemin[this.getGrille().getNumPile()].clear(); // on nettoie le chemin

            }
        }else{ // si ce n'est pas une CaseSymbole
            System.out.println("Pas un symbole");
            System.out.println("Je ne valide pas");

            for(int i = 0; i < getGrille().chemin[this.getGrille().getNumPile()].nb(); i++){ // boucle pour remplacer les cases 
                Case pasOk = getGrille().chemin[this.getGrille().getNumPile()].list().get(i);
                this.getGrille().setCase(pasOk.getI(), pasOk.getJ(), pasOk);
            }
            getGrille().chemin[this.getGrille().getNumPile()].clear(); // on nettoie le chemin
        }
        this.setChanged();
        this.notifyObservers();
    }
}