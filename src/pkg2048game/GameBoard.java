/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2048game;

/**
 *
 * @author i
 */
import java.util.Random;

public class GameBoard {
    private int[][] board;
    private int score;
    private Random random;

    public GameBoard() {

        board = new int[4][4];
        random = new Random();

        initializeBoard();
    }

    public void initializeBoard() {

        score = 0;

        for(int i = 0; i < 4; i++) {

            for(int j = 0; j < 4; j++) {

                board[i][j] = 0;}}

        spawnTile();
        spawnTile();
    }

    public int[][] getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    } 
    
    public void spawnTile() {

    int emptyCount = 0;

    for(int i = 0; i < 4; i++) {

        for(int j = 0; j < 4; j++) {

            if(board[i][j] == 0) {

                emptyCount++;
            }
        }
    }

    if(emptyCount == 0) {

        return;
    }

    int row;
    int col;

    do {

        row = random.nextInt(4);
        col = random.nextInt(4);

    } while(board[row][col] != 0);

    int value;

    if(random.nextInt(10) < 9) {

        value = 2;

    } else {

        value = 4;
    }

    board[row][col] = value;
}
    
    public void moveLeft() {
        for(int i = 0; i < 4; i++) {

        int[] temp = new int[4];

        int index = 0;

        // geser smua angka ke kiri
        for(int j = 0; j < 4; j++) {

            if(board[i][j] != 0) {

                temp[index] = board[i][j];

                index++;
            }
        }

        // merge angka sama
        for(int j = 0; j < 3; j++) {

            if(temp[j] != 0 && temp[j] == temp[j + 1]) {

                temp[j] *= 2;

                score += temp[j];

                temp[j + 1] = 0;
            }
        }

        // geser lagi setelah merge
        int[] newRow = new int[4];

        index = 0;

        for(int j = 0; j < 4; j++) {

            if(temp[j] != 0) {

                newRow[index] = temp[j];

                index++;
            }
        }

        board[i] = newRow;
    }

    spawnTile();
}
    public void moveRight() {

    for(int i = 0; i < 4; i++) {

        int[] temp = new int[4];

        int index = 3;

        // geser ke kanan
        for(int j = 3; j >= 0; j--) {

            if(board[i][j] != 0) {

                temp[index] = board[i][j];

                index--;
            }
        }

        // merge
        for(int j = 3; j > 0; j--) {

            if(temp[j] != 0 && temp[j] == temp[j - 1]) {

                temp[j] *= 2;

                score += temp[j];

                temp[j - 1] = 0;
            }
        }

        // rapihkan lagi
        int[] newRow = new int[4];

        index = 3;

        for(int j = 3; j >= 0; j--) {

            if(temp[j] != 0) {

                newRow[index] = temp[j];

                index--;
            }
        }

        board[i] = newRow;
    }

    spawnTile();
}
    public void moveUp() {

    for(int j = 0; j < 4; j++) {

        int[] temp = new int[4];

        int index = 0;

        // geser atas
        for(int i = 0; i < 4; i++) {

            if(board[i][j] != 0) {

                temp[index] = board[i][j];

                index++;
            }
        }

        // merge
        for(int i = 0; i < 3; i++) {

            if(temp[i] != 0 && temp[i] == temp[i + 1]) {

                temp[i] *= 2;

                score += temp[i];

                temp[i + 1] = 0;
            }
        }

        // rapihkan
        int[] newCol = new int[4];

        index = 0;

        for(int i = 0; i < 4; i++) {

            if(temp[i] != 0) {

                newCol[index] = temp[i];

                index++;
            }
        }

        for(int i = 0; i < 4; i++) {

            board[i][j] = newCol[i];
        }
    }

    spawnTile();
}
    public void moveDown() {

    for(int j = 0; j < 4; j++) {

        int[] temp = new int[4];

        int index = 3;

        // geser bawah
        for(int i = 3; i >= 0; i--) {

            if(board[i][j] != 0) {

                temp[index] = board[i][j];

                index--;
            }
        }

        // merge
        for(int i = 3; i > 0; i--) {

            if(temp[i] != 0 && temp[i] == temp[i - 1]) {

                temp[i] *= 2;

                score += temp[i];

                temp[i - 1] = 0;
            }
        }

        // rapihkan
        int[] newCol = new int[4];

        index = 3;

        for(int i = 3; i >= 0; i--) {

            if(temp[i] != 0) {

                newCol[index] = temp[i];

                index--;
            }
        }

        for(int i = 0; i < 4; i++) {

            board[i][j] = newCol[i];
        }
    }

    spawnTile();
}
    public boolean isWin() {

    for(int i = 0; i < 4; i++) {

        for(int j = 0; j < 4; j++) {

            if(board[i][j] == 2048) {

                return true;
            }
        }
    }

    return false;
}
    public boolean isGameOver() {

    // cek masih ada kosong
    for(int i = 0; i < 4; i++) {

        for(int j = 0; j < 4; j++) {

            if(board[i][j] == 0) {

                return false;
            }
        }
    }

    // cek horizontal
    for(int i = 0; i < 4; i++) {

        for(int j = 0; j < 3; j++) {

            if(board[i][j] == board[i][j + 1]) {

                return false;
            }
        }
    }

    // cek vertical
    for(int j = 0; j < 4; j++) {

        for(int i = 0; i < 3; i++) {

            if(board[i][j] == board[i + 1][j]) {

                return false;
            }
        }
    }

    return true;
}
}
