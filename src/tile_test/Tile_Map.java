/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile_test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author Tim
 */
public class Tile_Map {
    
    private String map = "";
    private static Tile[] tiles = new Tile[1024];
  
    private LinkedList <Tower> towers;
    private static Image[] imgs = new Image[4];
        
    public Tile_Map()
    {
        this.towers = new LinkedList();
        imgs = loadImages();
        readMap("map.txt");
        
        
        System.out.println("Map dimensions" + Math.sqrt(map.length()));
        
        for(int i = 0; i < 1024;i++)
        {
            //System.out.println("Char at: " + i + " = " + map.charAt(i));
            int matType = Integer.parseInt(map.substring(i,i+1));
            if(matType == 3)
            {
                tiles[i] = new Tower(i, imgs[matType]);
                towers.add((Tower)tiles[i]);
            }
            
            else
            {
                tiles[i] = new Tile(i,imgs[matType]);      
            }
            
            if(matType == 1)
            {
                tiles[i].setWalkable(true); 
               
            }
        }       
    }
    
    public Tile[] getNeighbours(Tile tile)
    {
        Tile[] neighbours = new Tile[4];
        int pos = tile.getId();
        try{
        
        neighbours[0] = tiles[pos-32];
      
        
        neighbours[1] = tiles[pos-1];
        neighbours[2] = tiles[pos+1];
                
      
        neighbours[3] = tiles[pos+32];
        
        }
        
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.out.println(ex.getMessage().toString());
        }
        
                
       return neighbours;
    }
    public void readMap(String name)
    {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("Resources/maps/" + name));
            String line;
            while((line = in.readLine()) != null)
            {
                
                map = map + line.substring(0,32);
                System.out.println(line);
            }   in.close(); 
           
        } 
        catch (FileNotFoundException ex) {
                Logger.getLogger(Tile_Map.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(Tile_Map.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Tile_Map.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     static private Image[] loadImages()
    {
        Image img[] = new Image[4];
        try{
            for(int i = 1 ;i < 5; i++)
            {
                InputStream is = Files.newInputStream(Paths.get("Resources/tiles/tile" + i + ".png"));
                img[i-1] = new Image(is);
                is.close();   
            }  
        }
        catch (IOException ex)
        {} 
        
        return img;        
     
    }

    public static Tile[] getTiles() {
        return tiles;
    }
     
     
}
