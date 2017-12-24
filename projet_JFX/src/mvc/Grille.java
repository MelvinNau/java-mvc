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
public class Grille {
    protected int x;
    protected int y;
    protected Case tab[][];     // tableau 2D de cases
    protected Pile chemin[];    // tableau de piles
    protected int nbChemins;    // nombre de piles
    protected int numPile;      // numéro de la pile actuelle
            
    public Grille(int _x, int _y){   // Constructeur   
        this.x = _x;
        this.y = _y;
        tab = new Case[_x][_y];
        nbChemins = 10;
        chemin = new Pile[nbChemins];
        numPile = 0;
        
        for (int i = 0; i < this.x ; i++){
            for (int j = 0 ; j < this.y ; j++){
                this.tab[i][j] = new Case(i, j,"File:transparent.png"); // toutes les cases sont vides à la construction, donc on charge une image transparente
            }
        }
    }
    
    
    public Grille(Grille g){ // Constructeur par copie
        this.x=g.getX();
        this.y=g.getY();
        this.tab = new Case[g.getX()][g.getY()];
        this.nbChemins = g.nbChemins;
        this.chemin = new Pile[g.nbChemins];
       
        for (int i = 0; i < g.x ; i++){ // on copie le tableau de cases
            for (int j = 0 ; j < g.y ; j++){
                this.tab[i][j] = g.getCase(i,j);
            }
        }
        
        for(int i = 0; i < g.nbChemins;i++){ // on copie le tableau de piles
            this.chemin[i] = g.chemin[i];
        }
    }
    
    
    //________________________ACCESSEURS / MUTATEURS____________________________
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setX(int _x){
        this.x = _x;
    }
     
    public void setY(int _y){
        this.y = _y;
    }
    
    public Case getCase(int _i, int _j){
        return this.tab[_i][_j];
    }

    public void setCase(int i, int j, Case nouvelle){
        this.tab[i][j] = nouvelle;

        if(nouvelle instanceof CaseRail){       
            this.tab[i][j] = (CaseRail) nouvelle;
        }
    }

    public Pile getChemin(int numChemin){
        return this.chemin[numChemin];
    }
    
    public void setChemin(Pile pile, int numChemin){
        this.chemin[numChemin] = pile;
    }
    
    public int getNbChemins(){
        return this.nbChemins;
    }

    public void setNbChemins(int nb){
        this.nbChemins = nb;
    }

    public int getNumPile(){
        return this.numPile;
    }

    public void setNumPile(int nb){
        this.numPile = nb;
    }
    
    //__________________________________________________________________________
   
    
    public void afficher(){ // affiche la grille dans la console
        for (int i = 0; i < this.x ; i++){
            for (int j = 0 ; j < this.y ; j++){
                if(tab[i][j] instanceof CaseSymbole){
                    System.out.print(" " + ((CaseSymbole) (this.tab[i][j])).getSymbole());
                }else{
                    if(tab[i][j] instanceof CaseRail){ 
                        System.out.print(" " + ((CaseRail) (this.tab[i][j])).getRail());
                    }else{
                        System.out.print(" VIDE ");   
                    }
                }
            }
            System.out.println();
        }
    }
    
    
    public boolean voisin(Case actuelle, Case precedente){ // indique si une case est située autour de la case actuelle
        if ((((precedente.getI()+1) == actuelle.getI()) && (precedente.getJ() == actuelle.getJ())) ||
            (((precedente.getI()-1) == actuelle.getI()) && (precedente.getJ() == actuelle.getJ())) ||
            ((precedente.getI() == actuelle.getI()) && ((precedente.getJ()+1) == actuelle.getJ())) ||
            ((precedente.getI() == actuelle.getI()) && ((precedente.getJ()-1) == actuelle.getJ()))) {
            return true;
        }else{
            return false;
        }
    }
    
    
    public void valider(Pile p, int numPile){
        chemin[numPile] = p;
    }
    
    
    public void invalider(Pile p, int numPile){
        p.clear();
        chemin[numPile] = p;
    }
    

    public int coordinate(int _coord){ // permet d'avoir les coordonées de notre grille en fonction de la position de la souris
        if (_coord < 100){
           _coord = 0; 
        }else{
            double traitement = (double) _coord;
            _coord = (int) traitement / 100;
        }
           
        return _coord;
    }
}
