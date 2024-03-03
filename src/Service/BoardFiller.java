/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entity.Board;
import java.util.Scanner;


/**
 *
 * @author Diego
 */
public class BoardFiller {
    private Board board;
    Scanner in = new Scanner(System.in).useDelimiter("\n");   

    public BoardFiller(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    
    public void showBoard(){
        String[][] dash = board.getBoard().clone();
         for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(" "+ dash[i][j] + " ");
            }
             System.out.println("");
        }
    }
    
     public static int[] randomizer(int size){
        return new int[]{(int) Math.floor(Math.random()*size),
            (int) Math.floor(Math.random()*size),(int) Math.floor(Math.random()*4)
        };
    }    
     
    public static boolean checker(int[] position, String[][] dash, String word){
        boolean fit=false;
        /*0:horizontal, 1:vertical, 2:diagonal asendente, 3:diagonal descendente*/
        int direction=position[2];
        switch (direction) {
            case 0:
                if ((position[1] + word.length()) < 15) {                    
                    for (int i = 0; i < word.length(); i++) {
                        String letra = word.substring(i, i + 1);
                        fit = dash[position[0]][position[1] + i].equals("?")
                                || (word.equalsIgnoreCase(dash[position[0]][position[1] + i]));
                        
                        if (!fit) {                         
                            break;
                        }
                    }
                }
                break;
            case 1:

                if (position[0] + word.length() < 15) {                    
                    for (int i = 0; i < word.length(); i++) {
                        String letra = word.substring(i, i + 1); 
                         fit= dash[position[0] + i][position[1]].equals("?")
                                || letra.equalsIgnoreCase(dash[position[0] + i][position[1]]);
                        if (!fit) {                            
                            break;
                        }
                    }
                }
                break;
                
            case 2:
                if ((position[0] - word.length() > 0) && (position[1] + word.length()) < 15) {                    
                    for (int i = 0; i < word.length(); i++) {
                        String letra = word.substring(i, i + 1);
                        fit = dash[position[0]-i][position[1] + i].equals("?")
                                || (letra.equalsIgnoreCase(dash[position[0]-i][position[1] + i]));
                        if (!fit) {                         
                            break;
                        }
                    }
                }
                break;
            case 3:
                if ((position[1] + word.length()) < 15 && (position[0] + word.length()) < 15) {                    
                    for (int i = 0; i < word.length(); i++) {
                        String letra = word.substring(i, i + 1);
                        fit = dash[position[0]+i][position[1] + i].equals("?")
                                || (word.equalsIgnoreCase(dash[position[0]+i][position[1] + i]));
                        if (!fit) {                         
                            break;
                        }
                    }
                }
                break;
                
            default:
                System.out.println("default");

        }

        return fit;
    }
         
    
    public void enterWord(int nWords){
        int wordsAdded=0;
        int size = board.getSize();
        String[][] dash = board.getBoard().clone();
        do {
            System.out.println("enter a word to add it in the word search");
            String word=in.nextLine().toUpperCase();
            boolean added=false;
            int counter=0;
            
            do{               
               int[] coordinates=randomizer(size);
               
               if(checker(coordinates,dash,word)){                   
                   added=true;
                   switch (coordinates[2]){
                       case 0:
                           for (int i = 0; i < word.length(); i++) {
                           dash[coordinates[0]][coordinates[1]+i]=word.substring(i, i+1);
                       }
                           break;
                       case 1:
                           for (int i = 0; i < word.length(); i++) {
                           dash[coordinates[0]+i][coordinates[1]]=word.substring(i, i+1);
                       }
                              break;
                       case 2:
                           for (int i = 0; i < word.length(); i++) {
                           dash[coordinates[0]-i][coordinates[1]+i]=word.substring(i, i+1);
                       }
                           break;
                       case 3:
                           for (int i = 0; i < word.length(); i++) {
                           dash[coordinates[0]+i][coordinates[1]+i]=word.substring(i, i+1);
                       }
                           break;
                   }
               }
               counter++;
               if(counter==10000){ 
                   System.out.println("could not add word");
                   wordsAdded--;
               }
               
            }while((!added)&&counter<10000);
            
            wordsAdded++;
                        
        } while (nWords>wordsAdded);
    }
    
    public String randomChar(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int i = new java.util.Random().nextInt(characters.length());
        String character = characters.substring(i, i+1);
        return character;
    }

    public void filler(String[][] dash) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (dash[i][j].equals("?")) {
                    String character = randomChar();
                    dash[i][j] = character;
                }

            }
        }
    }
    
    public void gameSetter(){
        System.out.println("how many words do you want to enter? (10 by default)");
        int nWords;
        try{
            nWords =in.nextInt();
        }catch(Exception e){
            nWords=10;
        }finally{
            in.nextLine();
        }      
      enterWord(nWords);
      filler(board.getBoard().clone());
      showBoard();
    }
}
