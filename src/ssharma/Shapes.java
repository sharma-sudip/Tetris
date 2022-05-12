/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssharma;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Sudip
 */
public class Shapes {
    
    Color color;
    int[][] Shape;
    
    Shapes(int[][] shape, Color color){
      this.Shape=shape;
      this.color=color;
     
    }
    
 int[][] getShapes(){
     return Shape;
 }
 int getWidth(){
     return Shape[0].length;
 }
  int getLength(){
     return Shape.length;
 }
  Color getColor(){
      return this.color;
  }
  void setShape(int[][] board){
      this.Shape=board;
  }
    
}
