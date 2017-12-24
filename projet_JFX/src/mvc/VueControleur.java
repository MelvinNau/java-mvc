
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.* ;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;



/**
 *
 * @author freder
 */
public class VueControleur extends Application {
    
    // modèle : ce qui réalise le calcule de l'expression
    Modele m;
    
    // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
    BorderPane border = new BorderPane();
        
    // permet de placer les diffrents boutons dans une grille
    GridPane gPane;
    
    // grille d'affichage
    Grille grilleVue;
    
    // notre stage
    Stage primaryStage = new Stage();
    
    
  
    public void afficher(Grille grilleVue){ // créer un gridPane en fonction de la taille et du contenu de notre GrilleVue
        for(int i = 0; i < grilleVue.getX(); i++){
            for(int j = 0; j < grilleVue.getY(); j++){ 
                Image image;
                ImageView image_view;
                image = new Image(grilleVue.getCase(i,j).getImage()); 
                image_view = new ImageView(image);
                image_view.setFitHeight(100);
                image_view.setFitWidth(100);
                ((ImageView)gPane.getChildren().get(grilleVue.getY()*i + j)).setImage(image);
            }
        }
    }
    
    public int verifieGagnant(){ // on vérifie si la grille a été bien remplie
        int num = 0;
        for (int i = 0; i < m.getGrille().getX();i++){
            for (int j = 0; j < m.getGrille().getY();j++){
                if((m.getGrille().getCase(i, j) instanceof CaseSymbole)|| (m.getGrille().getCase(i, j) instanceof CaseRail)){
                    num += 1;  // on incrémente un entier a chaque fois qu'une case est occupée              
                }
            }     
        }

        if((m.getGrille().getNumPile() == m.getGrille().getNbChemins()) && (m.getGrille().getX()*m.getGrille().getY() == num)){
            System.out.println("Gagné"); // si toutes cases sont remplies, le niveau est gagné
            return 1;
        }else{
            if(m.getGrille().getNumPile() > m.getGrille().getNbChemins()){
                return -1; // gestion du cas d'erreur où il y a plus de chemins que possible
            }
        }
        return 0;          
    }
    
    public void test_case(Case derniere, Case avantDerniere, Case avantAvDerniere){
        CaseRail tmp;
        
        int xDer = derniere.getI(); // coordonnées de la case actuelle (dernière dans la pile)
        int yDer = derniere.getJ();

        int xAvDer = avantDerniere.getI(); // coordonnées de l'avant-dernière case
        int yAvDer = avantDerniere.getJ();

        int xAvAvDer = avantAvDerniere.getI(); // coordonnées de l'avant-avant-dernière case
        int yAvAvDer = avantAvDerniere.getJ();
        
        
        // placement des rails et de leurs images en fonction du parcours de la souris au fil des cases
        if(derniere instanceof CaseRail){ 

        }else{
            // Pour CENTRE
            if (derniere instanceof CaseSymbole){
                //System.out.println("Je suis sur une case symbole");  
            }else{
                tmp =  new CaseRail(xDer, yDer,"File:centre.png",Rail.CENTRE);
                m.getGrille().setCase(xDer, yDer, tmp);
            }

            // Pour HORIZONTAL
            if ((derniere.getJ()==avantDerniere.getJ()+1) && ((derniere.getI()==avantDerniere.getI())) 
             &&(derniere.getJ()==avantAvDerniere.getJ()+2) && ((derniere.getI()==avantAvDerniere.getI()))||
                ((derniere.getJ()==avantDerniere.getJ()-1) && ((derniere.getI()==avantDerniere.getI())) 
            &&(derniere.getJ()==avantAvDerniere.getJ()-2) && ((derniere.getI()==avantAvDerniere.getI())))) {

                tmp =  new CaseRail(xAvDer, yAvDer,"File:horizontal.png",Rail.HORIZONTAL);
                m.getGrille().setCase(xAvDer, yAvDer, tmp);
            }

            // Pour VERTICAL
            if ((derniere.getJ()==avantDerniere.getJ()) && ((derniere.getI()==avantDerniere.getI()+1)) 
             &&(derniere.getJ()==avantAvDerniere.getJ()) && ((derniere.getI()==avantAvDerniere.getI()+2))||
                ((derniere.getJ()==avantDerniere.getJ()) && ((derniere.getI()==avantDerniere.getI()-1)) 
            &&(derniere.getJ()==avantAvDerniere.getJ()) && ((derniere.getI()==avantAvDerniere.getI()-2)))) {

                tmp =  new CaseRail(xAvDer, yAvDer,"File:vertical.png",Rail.VERTICAL);
                m.getGrille().setCase(xAvDer, yAvDer, tmp);
            }

            // Pour BG
            if ((derniere.getJ()==avantDerniere.getJ()) && ((derniere.getI()==avantDerniere.getI()+1)) 
             &&(derniere.getJ()==avantAvDerniere.getJ()+1) && ((derniere.getI()==avantAvDerniere.getI()+1))||
                ((derniere.getJ()==avantDerniere.getJ()-1) && ((derniere.getI()==avantDerniere.getI())) 
            &&(derniere.getJ()==avantAvDerniere.getJ()-1) && ((derniere.getI()==avantAvDerniere.getI()-1)))) {

                tmp =  new CaseRail(xAvDer, yAvDer,"File:bg.png",Rail.BG);
                m.getGrille().setCase(xAvDer, yAvDer, tmp);
            }
            // Pour BD
            if ((derniere.getJ()==avantDerniere.getJ()) && ((derniere.getI()==avantDerniere.getI()+1)) 
            &&(derniere.getJ()==avantAvDerniere.getJ()-1) && ((derniere.getI()==avantAvDerniere.getI()+1))||
                ((derniere.getJ()==avantDerniere.getJ()+1) && ((derniere.getI()==avantDerniere.getI())) 
            &&(derniere.getJ()==avantAvDerniere.getJ()+1) && ((derniere.getI()==avantAvDerniere.getI()-1)))) {

                tmp =  new CaseRail(xAvDer, yAvDer,"File:bd.png",Rail.BD);
                m.getGrille().setCase(xAvDer, yAvDer, tmp);
            }

            // Pour HG
            if ((derniere.getJ()==avantDerniere.getJ()-1) && ((derniere.getI()==avantDerniere.getI())) 
            &&(derniere.getJ()==avantAvDerniere.getJ()-1) && ((derniere.getI()==avantAvDerniere.getI()+1))||
                ((derniere.getJ()==avantDerniere.getJ()) && ((derniere.getI()==avantDerniere.getI()-1)) 
            &&(derniere.getJ()==avantAvDerniere.getJ()+1) && ((derniere.getI()==avantAvDerniere.getI()-1)))) {

                tmp =  new CaseRail(xAvDer, yAvDer,"File:hg.png",Rail.HG);
                m.getGrille().setCase(xAvDer, yAvDer, tmp);
            }

            // Pour HD
            if ((derniere.getJ()==avantDerniere.getJ()+1) && ((derniere.getI()==avantDerniere.getI())) 
            &&(derniere.getJ()==avantAvDerniere.getJ()+1) && ((derniere.getI()==avantAvDerniere.getI()+1)) ||
                ((derniere.getJ()==avantDerniere.getJ()) && ((derniere.getI()==avantDerniere.getI()-1)) 
            &&(derniere.getJ()==avantAvDerniere.getJ()-1) && ((derniere.getI()==avantAvDerniere.getI()-1)))) {

                tmp =  new CaseRail(xAvDer, yAvDer,"File:hd.png",Rail.HD);
                m.getGrille().setCase(xAvDer, yAvDer, tmp);
            }
        }
    }
    
    
    public void changement_niveau(int numNiveau){ // gestion du changement de niveaux
        
        // on affiche le numéro de niveau dans le titre de la fenêtre
        if(numNiveau <= m.nbNiveaux){
            primaryStage.setTitle("Niveau "+m.getNumNiveau());
        }else{
        // si tous les niveaux sont remplis, on indique que le jeu est fini
            primaryStage.setTitle("FINI");
        }
        
        // le niveau 1 est affiché au début; pour les autres on relance la fenêtre  
        if (numNiveau>1){
            Stage stage = (Stage) primaryStage.getScene().getWindow();
            stage.close();
        }
        //on set les niveaux (grilles) correspondants au num de niveau
        switch(numNiveau){
            case 1:
                m.setGrille(m.niveau1());
                break;
                
            case 2:
                m.setGrille(m.niveau2());
                break;
                
            case 3:
                m.setGrille(m.niveau3());
                break;
                
            case 4:
                m.setGrille(m.niveau4());
                break;
            case 5:
                m.setGrille(m.niveau5());
                break;
        }
    }
    
    
    // procédure qui créé notre vue, et sur laquelle on va jouer
    
    public void initialisation(int numNiveau){
        
        // première création du gridPane
        changement_niveau(numNiveau);
        gPane = new GridPane();
         
        int column = 0;
        int row = 0;
        Image image;
        ImageView image_view;
        for (int i = 0; i < m.getGrille().getX() ; i++){
            for (int j = 0; j < m.getGrille().getY() ; j++){

                image = new Image("File:transparent.png"); 
                image_view = new ImageView(image);
                image_view.setFitHeight(100);
                image_view.setFitWidth(100);
                gPane.add(image_view,column++,row);
                if (column > m.getGrille().getX() - 1) {
                    column = 0;
                    row++;
                }
            }
        }
        
        border = new BorderPane();
        border.setCenter(gPane);     
        Scene scene = new Scene(border, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
        
 
        grilleVue = new Grille(m.getGrille());
        afficher(grilleVue);

        
        // fonction de mise à jour
        m.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                for (int i = 0; i < m.getGrille().getX() ; i++){
                    for (int j = 0; j < m.getGrille().getY() ; j++){ // on parcours la grille du modèle
                        
                        if (grilleVue.getCase(i, j) != m.getGrille().getCase(i,j) ){ // si une case est différente dans la grilleVue
                            
                            grilleVue.setCase(i,j,m.getGrille().getCase(i,j)); // on la remplace
                        }                        
                    }
                }
                afficher(grilleVue);  
            }           
        });

        
        // Annulation d'un chemin en cliquant dessus
         gPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                int yClick = m.getGrille().coordinate((int) event.getX()); // coordonnées du clic
                int xClick = m.getGrille().coordinate((int) event.getY());
                
                boolean tout_depiler = false;
                if (m.getGrille().getCase(xClick,yClick) instanceof CaseRail){ // si on clique sur un Rail

                    for (int k=0; k<m.getGrille().getChemin(m.getGrille().getNumPile()-1).nb();k++){ // et qu'il appartient à une des cases du dernier chemmin (dernière pile)
                        int i_case = m.getGrille().getCase(xClick,yClick).getI();
                        int j_case = m.getGrille().getCase(xClick,yClick).getJ();
                        int i_pile = m.getGrille().getChemin(m.getGrille().getNumPile()-1).getElement(k).getI();
                        int j_pile = m.getGrille().getChemin(m.getGrille().getNumPile()-1).getElement(k).getJ();
                        if (i_case == i_pile && j_pile == j_case){
                            
                            for (int l=0; l<m.getGrille().getChemin(m.getGrille().getNumPile()-1).nb();l++){ // on écrase toutes les cases du dernier chemin
                                Case pasOk = m.getGrille().getChemin(m.getGrille().getNumPile()-1).list().get(l);
                                m.getGrille().setCase(pasOk.getI(), pasOk.getJ(), pasOk);
                            }
                            tout_depiler = true; // et on dépile
                        }
                    }
                    if (tout_depiler){
                        m.getGrille().setNumPile(m.getGrille().getNumPile()-1);                
                    }
                    m.maj(); // mise à jour de l'affichage
                }
            }    
        });
        
        gPane.setOnDragDetected(new EventHandler<MouseEvent>() { // Démarrage d'un DnD
            @Override
            public void handle(MouseEvent event) {
                
                // Coordonnées de la souris lorsqu'on démarre le DnD
                int yDepart = m.getGrille().coordinate((int) event.getX());
                int xDepart = m.getGrille().coordinate((int) event.getY());
                
                // Si les coordonées de la souris sont sur une case symbole
                if(m.getGrille().getCase(xDepart, yDepart) instanceof CaseSymbole){
                    
                    javafx.scene.input.Dragboard db = gPane.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    
                    switch(m.getGrille().getNumPile()){
                        
                        case 0: // et que c'est le premier chemin du niveau                      
                            content.putString("DD");
                            db.setContent(content);
                            m.startDD(xDepart,yDepart, m.getGrille().getNumPile()); // alors on lance le DnD
                            event.consume();
                            break;
                        
                        //sinon, on vérifie qu'on ne démarre pas sur des cases utilisées dans les chemins précendents
                        case 1:
                            if ((m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-1).getDepart())) 
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-1).getArrivee()))){
                                
                                content.putString("DD");
                                db.setContent(content);
                                m.startDD(xDepart,yDepart, m.getGrille().getNumPile()); // on lance le DnD
                                event.consume();
                                break;
                            }
                            
                        case 2:
                            if ((m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-1).getDepart())) 
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-1).getArrivee()))
                                    && (m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-2).getDepart())) 
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-2).getArrivee()))){
                                
                                content.putString("DD");
                                db.setContent(content);
                                m.startDD(xDepart,yDepart, m.getGrille().getNumPile()); // on lance le DnD
                                event.consume();
                                break;
                            }
                            
                        case 3:
                            if ((m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-1).getDepart())) 
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-1).getArrivee()))
                                    && (m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-2).getDepart())) 
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-2).getArrivee()))
                                    && (m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-3).getDepart())) 
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-3).getArrivee()))){
                                
                                content.putString("DD");
                                db.setContent(content);
                                m.startDD(xDepart,yDepart, m.getGrille().getNumPile()); // on lance le DnD
                                event.consume();
                                break;
                            }
                            
                        case 4:
                            if ((m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-1).getDepart()))
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-1).getArrivee()))
                                    && (m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-2).getDepart()))
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-2).getArrivee()))
                                    && (m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-3).getDepart())) 
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-3).getArrivee()))
                                    && (m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-4).getDepart())) 
                                        &&(m.getGrille().getCase(xDepart, yDepart)!=(m.getGrille().getChemin(m.getGrille().getNumPile()-4).getArrivee()))){
                                
                                content.putString("DD");
                                db.setContent(content);
                                m.startDD(xDepart,yDepart, m.getGrille().getNumPile()); // on lance le DnD
                                event.consume();
                                break;
                            }       
                    }                
                }else{
                    System.out.println("Je dois commencer sur une case symbole");
                }
            }
        });
        
        
        
        gPane.setOnDragOver(new EventHandler<DragEvent>() { // DnD en cours
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);
                
                // Coordonnées acutelles de la souris
                int ySouris = m.getGrille().coordinate((int) event.getX());
                int xSouris = m.getGrille().coordinate((int) event.getY());

                Case derniere = m.getGrille().chemin[m.getGrille().getNumPile()].last();
                Case avantDerniere = m.getGrille().chemin[m.getGrille().getNumPile()].beforeLast();
                Case avantAvDerniere = m.getGrille().chemin[m.getGrille().getNumPile()].beforeBL();
                
                test_case(derniere, avantDerniere, avantAvDerniere); // on pose les cases au fur et à mesure qu'on se déplace

                m.enterDD(xSouris, ySouris, m.getGrille().getNumPile()); // on lance la fonction du modèle
                event.consume();
            }
        });
        
        
        gPane.setOnDragDropped(new EventHandler<DragEvent>() { // Fin du DnD
            public void handle(DragEvent event) {
                
                // on récupère les coordonnées de fin du chemin
                int yFin = m.getGrille().coordinate((int) event.getX());
                int xFin = m.getGrille().coordinate((int) event.getY());
             
                // et les coordonnées départ du chemin
                int yDepart = m.getGrille().getChemin(m.getGrille().getNumPile()).getDepart().getJ();
                int xDepart = m.getGrille().getChemin(m.getGrille().getNumPile()).getDepart().getI();
                
                m.stopDD(xDepart, yDepart, xFin, yFin); // on lance la fonction du modèle avec les bonnes coordonnées
                
                Case derniere = m.getGrille().chemin[m.getGrille().getNumPile()].last();
                Case avantDerniere = m.getGrille().chemin[m.getGrille().getNumPile()].beforeLast();
                Case avantAvDerniere = m.getGrille().chemin[m.getGrille().getNumPile()].beforeBL();
                
                int xDer = derniere.getI();
                int yDer = derniere.getJ(); 

                int xAvDer = avantDerniere.getI();
                int yAvDer = avantDerniere.getJ();
                
                test_case(derniere, avantDerniere, avantAvDerniere); // vérification des cases parcourues pour modifier le chemin
                
                m.maj(); // et mettre à jour l'affichage          
                
                // on incrémente le numéro de pile si le chemin est terminé correctement
                if(m.getGrille().getCase(xFin,yFin) instanceof CaseSymbole){
                    if(((CaseSymbole) (m.getGrille().getCase(xDepart,yDepart))).getSymbole() == ((CaseSymbole) (m.getGrille().getCase(xFin,yFin))).getSymbole()){
                        m.getGrille().setNumPile(m.getGrille().getNumPile()+1);
                    }
                }
                       
                if(verifieGagnant() == 1){ // on lance le nouveau niveau si on a gagné sur cette grille
                    m.setNumNiveau(m.getNumNiveau()+1);                                 
                    initialisation(m.getNumNiveau());
                }
                if(verifieGagnant() == -1){
                    primaryStage.setTitle("Perdu");
                    System.out.println("Perdu");
                }
                event.consume();
            }       
        });

    }
    
    
    @Override
    public void start(Stage primaryStage) {
        
        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();
         
        initialisation(m.getNumNiveau());
        
        m.maj();       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);     
    }
}
