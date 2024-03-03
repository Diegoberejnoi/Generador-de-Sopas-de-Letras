/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Diego
 */
public class Board {
    private String[][] board;
    private int size;

    public Board(int size) {
        this.size = size;
        this.board= new String[size][size];
        boardInitializer();
    }

    public Board() {
        this.size = 15;
        this.board= new String[size][size];
        boardInitializer();
    }

    public String[][] getBoard() {
        return board;
    }
    
    private void boardInitializer(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j]="?";
            }
        }
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    

    
    
}
