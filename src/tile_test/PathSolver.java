/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile_test;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import static tile_test.Tile_Main.player;

/**
 *
 * @author Tim
 */
public class PathSolver implements Runnable{
    
    Tile[] tiles;
    Tile_Map tile_map;
    int target;
    
    int paths = 0;
    int shortestPath;
    String scores = "";
    LinkedList list;
    
    LinkedList solved_path = new LinkedList();
    
    public PathSolver(Player player,int target,Tile_Map tile_map)
    {
        this.tiles = tile_map.getTiles();
        this.tile_map = tile_map;
        this.target = target;
        
        //lookForPath(player.getPos(),player.getPos(),0);
        //System.out.print(scores);
        list = new LinkedList();
        list.add(player.getPos());  
        
    } 
    
    private void lookForPath(int tileID,int prev,int steps)
    {       
        Tile[] neighbours = tile_map.getNeighbours(tiles[tileID]);
        
        for(int i = 0; i< neighbours.length ; i++)
        {
            if(neighbours[i].isWalkable())
            {   
                if(neighbours[i].getId() == prev)
                    System.out.println("Stopped after " + steps + " steps");
                else if(neighbours[i].getId() == tileID)
                    System.out.println("Stopped after " + steps + " steps");
                else if(neighbours[i].getId() == target)
                {
                    System.out.println("Found Target after " + steps + " steps");
                    scores = scores + steps + "\n";
                    paths++; 
                }
                else if(steps > 60)
                {
                    System.out.println("Too many steps");
                }
                else if (paths > 4)
                {
                    break;
                }
                else
                {
                    System.out.println(tileID + " von zu " + neighbours[i].getId() + " Schritt: " + steps);
                    lookForPath(neighbours[i].getId(),tileID,steps+1);
                }
            }
        }
    }
    
    
    private void lookForPath_V2(LinkedList <Integer> list)
    {
        Tile[] neighbours = tile_map.getNeighbours(tiles[list.getLast()]);
        // System.out.println("Position:" + list.getLast());
        LinkedList <Tile> walkableNeighbours = new LinkedList<>();
        for(int i = 0; i< neighbours.length ; i++)
        {
            Tile tempTile = neighbours[i];
            if(tempTile.isWalkable() && !list.contains(tempTile.getId()))
            {
               walkableNeighbours.add(neighbours[i]);
            }
        }
            
        for(int i = 0; i<walkableNeighbours.size() ; i++)
        {
            Tile tempTile = walkableNeighbours.get(i);

            if(tempTile.getId() == target)
            {
                list.add(tempTile.getId());
               // System.out.println("Found after: " + list.size());  
                scores = scores + "Found after: " + list.size() + "\n";
                paths++;
                if(solved_path.size() <= 0 || list.size() < solved_path.size())
                {
                    solved_path = list;
                }
            }
            else if(list.contains(tempTile.getId()))
            {
                 System.out.println("Already visited"); 
                 //list.clear();
                 continue;

            }
            else if (list.size() < 100)
            {
                Platform.runLater(() ->
                {
                  // player.setPos(tempTile.getId());
                });

                try {
                    Thread.sleep(0);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PathSolver.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                list.add(tempTile.getId());
                lookForPath_V2((LinkedList)list.clone());
                list.pollLast();
            }
        }
    }
    
    
    public void goTo(LinkedList<Integer> path)          
    {
        
         
        for(int i = 0; i< path.size();i++)
        {
            Tile tempTile = tiles[path.get(i)];
            int pos = path.get(i);
            Platform.runLater(() ->
                {
                    //tempTile.mark();
                    player.setPos(pos);
                });
          
            
                try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PathSolver.class.getName()).log(Level.SEVERE, null, ex);
                    }   
           
        }
    }

    @Override
    public void run() {
       lookForPath_V2(list);
       System.out.println("Fastest way: " + solved_path.size());
        System.out.println("Paths: " + paths);
        goTo(solved_path);
        solved_path.clear();
    }
    
}
