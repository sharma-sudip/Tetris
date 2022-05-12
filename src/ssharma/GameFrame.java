/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssharma;

import javax.swing.JFrame;

/**
 *
 * @author Sudip
 */
public class GameFrame extends JFrame {
    GameFrame(){
        this.add(new GamePanel());
        this.setVisible(true);
        this.setTitle("Tetris");
        this.pack();
    
}
    
}
