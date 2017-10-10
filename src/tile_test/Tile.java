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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *
 * @author Tim
 */
public class Tile {
    
  
    ImageView imv;
    private int id;
    private boolean walkable = false;
    private int x,y;
   
    public Tile(int id,Image img)
    {
        this.id = id;
        imv = new ImageView(img);
       
        x = (id%32)*16-256;
        y = (id/32)*16-256;

        imv.setTranslateX(x);
        imv.setTranslateY(y);
    }
    
    public void blink()
    {
        imv.toFront();
        ScaleTransition scaleTransition = 
            new ScaleTransition(Duration.millis(100), imv);
        //scaleTransition.setFromX(1);
        //scaleTransition.setFromY(1);
        scaleTransition.setToX(2f);
        scaleTransition.setToY(2f);
        //scaleTransition.setByX(2f);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        
        scaleTransition.play();
    }
    
    public void mark()
    {
        imv.toFront();
        ScaleTransition scaleTransition = 
            new ScaleTransition(Duration.millis(1000), imv);
        scaleTransition.setFromX(1.5f);
        scaleTransition.setFromY(1.5f);
        scaleTransition.setToX(1f);
        scaleTransition.setToY(1f);
        //scaleTransition.setByX(2f);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        
        scaleTransition.play();
    }
    
    public void setX(int x)
    {
        imv.setTranslateX(x);   
        this.x = x;
    }

    public void setY(int y)
    {
        imv.setTranslateY(y);     
        this.y = y;
    }  

    public ImageView getImv() {
        return imv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
    
    public void print()
    {
         System.out.println(id + ". Tile clicked. Walkable?: " +walkable);
    }
    
    
   
    
    
    
    
    
    
   
}


