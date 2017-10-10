/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile_test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


/**
 *
 * @author Tim
 */
public class Player {
    int pos = -1;
    ImageView imv;
     int x,y;
     boolean hasFinished = true;
      
    private TranslateTransition transition;
    
    
    public Player()
    {
        imv = new ImageView(loadImage());
        transition= new TranslateTransition();
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setNode(imv);
        
        
        transition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                hasFinished = true;
            }
        });
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        hasFinished = false;
        this.pos = pos;
        
        transition.setFromX(x);
        transition.setFromY(y);
        
        x = (pos%32)*16-256;
        y = (pos/32)*16-256;
       // System.out.println("New pos: " + pos);
        imv.setTranslateX(x);
        imv.setTranslateY(y);
        
        
        
        imv.toFront();
        
        transition.setToX(x);
        transition.setToY(y);
        transition.setDuration(Duration.seconds(0.4));
        transition.play();
    }
    

    public ImageView getImv() {
        return imv;
    }
    
    
    
     static private Image loadImage()
    {
        Image img = null;
        
        try{
          
               InputStream is = Files.newInputStream(Paths.get("Resources/characters/char1.png"));
               img = new Image(is);
               is.close();   
            
        }
        catch (IOException ex)
        {} 
        
        return img;        
     
    }   
}

