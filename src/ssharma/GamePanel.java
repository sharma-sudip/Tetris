
package ssharma;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


public class GamePanel extends JPanel{
       final int GRID_SIZE;
      int score=100;
     int board[][];
     int background[][];
      int x=300;
      int y=0;
     int dx=25;
     int dy=25;
     Timer timer;
     boolean rotation=false;
     boolean filled;
     boolean gravity=false;
     int DELAY;
     Shapes shapes;
     int rotationNumber;
     JButton button;
     JTextField textfield;
    GamePanel(){
        this.GRID_SIZE = 25;
        this.background=new int[600/GRID_SIZE][600/GRID_SIZE];
        board=new int[600/GRID_SIZE][600/GRID_SIZE];
        respawn();
        this.setPreferredSize(new Dimension(250,500));
         button=new JButton("PAUSE");
         textfield=new JTextField();
          textfield.setLocation(100,200);
          textfield.setPreferredSize(textfield.getPreferredSize());
         this.add(textfield);
         JLabel label=new JLabel("SCORE:");
         
         this.add(label);
          JLabel label1=new JLabel("DELAY:");
         
         this.add(label1);
         
         String lado=textfield.getText();
         System.out.println(lado);
         //this.add(button);
         DELAY=500;
         button.addActionListener(new myActionListener());
        this.setBackground(Color.white);
        timer=new Timer(DELAY, (ActionEvent e) -> {
            System.out.println(x);
            System.out.println(y);
            System.out.println(shapes.getLength()+ " "+ shapes.getWidth());
            moveDown();
            checkAndClearFullLines();
            label.setText("the score is:"+score);
            label1.setText("the delay is:"+DELAY);
            DELAY-=25;
        });
        timer.start();
        addKeyListener(new myKeyListener());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);  
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintBackground(g);
        draw(g);
    }
    public void draw(Graphics g){
        
            createShape(g);
            for(int i=0;i<250;i+=25){
                g.drawLine(i, 0, i, 500);
                
        }
            for(int i=0;i<500;i+=25){
               
                g.drawLine(0, i, 250, i);
        }
        
    }
    private void respawn() {
        x=125;
        y=0;
        Random random=new Random();
        int nextShape=random.nextInt(6);
        //int nextShape=1;
        switch(nextShape){
            case 0:
                this.shapes=new Shapes(new int[][]{{1,0},{1,0},{1,1}},Color.CYAN);
                break;
            case 1:
                this.shapes=new Shapes(new int[][]{{1,1},{1,1}},Color.GREEN);
                break;
            case 2:
                this.shapes=new Shapes(new int[][]{{0,1},{0,1},{1,1}},Color.ORANGE);
                break; 
            case 3:
                this.shapes=new Shapes(new int[][]{{1},{1},{1},{1}},Color.RED);
                break;
            case 4:
                this.shapes=new Shapes(new int[][]{{0,1},{0,1},{1,1}},Color.GREEN);
                break;
            case 5:
                this.shapes=new Shapes(new int[][]{{1,1,0},{0,1,1}},Color.MAGENTA);
                break;
        }
       
    }
    public class myActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(timer.isRunning()) timer.stop();
            else timer.start();
        }
        
    }

    public class myKeyListener implements KeyListener{
     @Override
     public void keyTyped(KeyEvent e) {
      if(e.getKeyChar()=='a'){
            System.out.println(e.getKeyChar());
            if(x<=0) x=0;
            else{
                if(!checkLeft()) x-=dx;  
            }
      } 
      if(e.getKeyChar()=='s'){
            System.out.println(e.getKeyChar());
            y+=dy; 
            if((y+shapes.getLength()*25>=500)){
                     y=500-shapes.getLength()*25;
            }
            else{
               checkCollision();
               System.out.println("false");
            }
      }
      if(e.getKeyChar()=='w'){
            System.out.println(e.getKeyChar());
            rotate();
            if((x+shapes.getWidth()*25)>=250)  x=250-shapes.getWidth()*25;
            if((y+shapes.getLength()*25>=500)){
                     y=500-shapes.getLength()*25;
            }
            if(x<=0) x=0;
            
      }
      if(e.getKeyChar()=='d'){
            System.out.println(e.getKeyChar());
            if((x+shapes.getWidth()*25)>=250) x=250-shapes.getWidth()*25;
             else {
              if (!checkRight()) {
                  x+=dx;
              }
          }   
      } 
      
    }
     @Override
        public void keyPressed(KeyEvent e) {
          
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }

    }
   
 
  void moveDown(){
      y+=dy;
      if(y+shapes.getLength()*GRID_SIZE>=500){ 
          y=500-shapes.getLength()*25;
            for(int i=0;i<shapes.getLength();i++){
                for(int j=0;j<shapes.getWidth();j++){
                    if(shapes.getShapes()[i][j]==1){
                        background[x/25+j][y/25+i]=1;
                    }
                }
            }
            respawn();
        }
        else{
            checkCollision();
        }
    }
  
  void createShape(Graphics g){
      Color color =shapes.getColor();
      g.setColor(color);
      int[][] newShapeArray=shapes.getShapes();
        for(int i=0;i<shapes.getLength();i++){
            for(int j=0;j<shapes.getWidth();j++){
                if(newShapeArray[i][j]==1){
                    g.fillRect(x+j*25, y+i*25, 25, 25);
                }
            }
            
        }
       
        repaint();
    
  }

  void paintBackground(Graphics g){
      
      for(int i=0;i<10;i++){
            for(int j=0;j<20;j++){
                if(background[i][j]==1){
                    g.fillRect(i*25, j*25, 25, 25);
                }
            }
            
        }
       repaint();
  }
  boolean checkCollision(){
      System.out.println(shapes.getLength()+ " "+ shapes.getWidth());
      int[][] newShape=shapes.getShapes();
      for(int i=0;i<newShape.length;i++){
            for(int j=0;j<newShape[0].length;j++){
                if(newShape[i][j]==1){
                    if(background[x/25+j][y/25+i+1]==1){
                        for(int k=0;k<shapes.getLength();k++){
                            for(int l=0;l<shapes.getWidth();l++){
                               if(shapes.getShapes()[k][l]==1){ 
                                   background[x/25+l][y/25+k]=1;
                                   System.out.println(k+" "+l);
                               }
                            }
                        }
                     respawn();
                    } 
                    
                }
            }
      }
      return false;
  }
  boolean checkRight(){
      for(int i=0;i<shapes.getLength();i++){
            for(int j=0;j<shapes.getWidth();j++){
                if(shapes.getShapes()[i][j]==1){
                    if(background[x/25+j+1][y/25+i]==1){
                        return true;
                    }
                }
            }
      }    
           return false;
  }
  boolean checkLeft(){
      for(int i=0;i<shapes.getLength();i++){
            for(int j=0;j<shapes.getWidth();j++){
                if(shapes.getShapes()[i][j]==1){
                    if(background[x/25+j-1][y/25+i]==1){
                        return true;
                    }
                }
            }
        }    
     return false;
  }
  void rotate(){
      
      int columns=shapes.getWidth();
      int rows=shapes.getLength();
      int[][] rotatedBoard=new int[columns][rows];
      for(int i=0;i<columns;i++){
            for(int j=0;j<rows;j++){
                rotatedBoard[i][j]=shapes.getShapes()[rows-1-j][i];
            }
      }
      shapes.setShape(rotatedBoard);
      System.out.println(shapes.getLength()+ " "+ shapes.getWidth());
  }
  void checkAndClearFullLines(){
      
      int j=0;
      for(int i=20;i>0;i--){
          while(j<10){
              if(background[j][i]==0){
                  break;  
              }
              j++;

            }
          if(j==10){
            System.out.println("the value of i is"+i);

              score+=10;
              DELAY=DELAY-100;
              for(int a=i;a>0;a--){
                for(int s=0;s<10;s++){
                 background[s][a]=background[s][a-1]; 
                  
              }
              }
          }
           j=0;
        }
      repaint();

  }
 

}
