/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generador.sopa.de.letras;

import Entity.Board;
import Service.BoardFiller;
import java.util.Scanner;

/**
 *
 * @author Diego
 */
public class GeneradorSopaDeLetras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        
        System.out.println("enter the board size (15 by default)");
        int size;
        try{
            size =in.nextInt();
        }catch(Exception e){
            size=15;
        }finally{
            in.nextLine();
        }
        
      Board board = new Board(size);
      BoardFiller boardFiller= new BoardFiller(board);
      
      boardFiller.gameSetter();
      
        
    }
}
    