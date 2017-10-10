/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile_test;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author Tim
 */
public class Tile_Main extends Application {
    
    static Tile_Map tm;
    static Player player;
    
    @Override
    public void start(Stage primaryStage) {
       
        
        StackPane root = new StackPane();
       
        
        
        for (int i = 0 ; i < Tile_Map.getTiles().length ; i++)
        {
            Tile tempTile = Tile_Map.getTiles()[i];
            int tempPos = i;
            root.getChildren().add(tempTile.getImv());
            
            tempTile.getImv().setOnMouseClicked(new EventHandler<MouseEvent>() {            

            @Override
            public void handle(MouseEvent event) {
                tempTile.print();
                if(tempTile.isWalkable() && event.getButton() == MouseButton.PRIMARY)
                {
                   if(player.getPos() < 0)
                   {
                       player.setPos(tempPos);
                   }
                   
                   else
                   {
                       movePlayerto(tempPos);
                   }

                    
                }
                
                if(event.getButton() == MouseButton.SECONDARY)
                {
                    for (int i = 0 ;i < tm.getNeighbours(tempTile).length ;i++ )
                    {
                        if(tm.getNeighbours(tempTile)[i].isWalkable())
                        {
                            System.out.println("Neighbours: " + tm.getNeighbours(tempTile)[i].getId());
                            
                            Tile neighbour =  tm.getNeighbours(tempTile)[i];
                             Platform.runLater(() ->
                            {
                                neighbour.blink();
                            });
                            
                        }
                    } 
                }
                
            }
        });
        }        
        
        root.getChildren().add(player.getImv());
        Scene scene = new Scene(root, 512, 512);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void movePlayerto(int target)
    {
          Thread searcher = new Thread(new PathSolver(player, target,tm));
          searcher.start();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        tm = new Tile_Map();
        player = new Player();
        launch(args);
        
        
    }
    
    
    
    
    
}
