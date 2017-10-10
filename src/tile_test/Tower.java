/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile_test;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Tim
 */
public class Tower extends Tile{
    
    private ImageView imv;
    private int id;
    
    public Tower(int id,Image img)
    {
        super(id,img);
        this.id = id;
        imv = new ImageView(img);
        
        imv.setTranslateX((id%32)*16-256);
        imv.setTranslateY((id/32)*16-256);
        
        final Animation animation = new SpriteAnimation(
                imv,
                Duration.millis(200),
                2, 2,
                0, 0,
                16, 16);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    
    public ImageView getImv() {
        return imv;
    }

    @Override
    public int getId() {
        return id;
    }
    
    
}
