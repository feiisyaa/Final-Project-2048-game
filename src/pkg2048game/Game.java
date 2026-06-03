package pkg2048game;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;


public class Game extends javax.swing.JFrame {
    private javax.swing.JLayeredPane layeredPane;
    private GameBoard gameBoard;
    private int highScore = 0;
    private boolean gameOverShown = false;
    private boolean winShown = false;
    private boolean reached128 = false;
    
    public Game() {
    initComponents();
    

    gameBoard = new GameBoard();       
    loadHighScore();
    setLocationRelativeTo(null);

    updateBoard();
    requestFocusInWindow();

    setFocusable(true);
    
    if(gameBoard.isWin()) {

    lblStatus.setText("YOU WIN!");}

    else if(gameBoard.isGameOver()) {

    lblStatus.setText("GAME OVER");}

    else {

    lblStatus.setText("Playing");

}
    setFocusable(true);
    }
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Game.class.getName());

    private void setTileColor(JLabel label, int value) {

    switch(value) {
        case 0:
            label.setBackground(new java.awt.Color(205, 193, 180));
            break;
        case 2:
            label.setBackground(new java.awt.Color(238, 228, 218));
            break;
        case 4:
            label.setBackground(new java.awt.Color(237, 224, 200));
            break;
        case 8:
            label.setBackground(new java.awt.Color(242, 177, 121));
            break;
        case 16:
            label.setBackground(new java.awt.Color(245, 149, 99));
            break;
        case 32:
            label.setBackground(new java.awt.Color(246, 124, 95));
            break;
        case 64:
            label.setBackground(new java.awt.Color(246, 94, 59));
            break;
        case 128:
            label.setBackground(new java.awt.Color(237, 207, 114));
            break;
        case 256:
            label.setBackground(new java.awt.Color(237, 204, 97));
            break;
        case 512:
            label.setBackground(new java.awt.Color(237, 200, 80));
            break;
        case 1024:
            label.setBackground(new java.awt.Color(237, 197, 63));
            break;
        case 2048:
            label.setBackground(new java.awt.Color(237, 194, 46));
            break;
        default:
            label.setBackground(new java.awt.Color(60, 58, 50));
            break;
    }}
    
    
    private void play256Sound() {

    try {

        File file = new File("256.wav");

        AudioInputStream audio =
                AudioSystem.getAudioInputStream(file);

        Clip clip = AudioSystem.getClip();

        clip.open(audio);

        clip.start();

    } catch(Exception e) {

        e.printStackTrace();
    }
}
    
    private void playGameOverSound() {
        
    try {

        File file = new File("gameover.wav");

        AudioInputStream audio =
                AudioSystem.getAudioInputStream(file);

        Clip clip = AudioSystem.getClip();

        clip.open(audio);
        FloatControl gainControl =
        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        gainControl.setValue(6.0f); // tambah volume
        clip.start();

    } catch(Exception e) {

        e.printStackTrace();
    }
}
    
    private void playWinSound() {

    try {

        File file = new File("win.wav");

        AudioInputStream audio =
                AudioSystem.getAudioInputStream(file);

        Clip clip = AudioSystem.getClip();

        clip.open(audio);

        clip.start();

    } catch(Exception e) {

        e.printStackTrace();
    }
}
    
    

    private void updateBoard() {

    int[][] board = gameBoard.getBoard();

    JLabel[][] labels = {

        {tile00, tile01, tile02, tile03},
        {tile10, tile11, tile12, tile13},
        {tile20, tile21, tile22, tile23},
        {tile30, tile31, tile32, tile33}
    };

    for(int i = 0; i < 4; i++) {

        for(int j = 0; j < 4; j++) {
            
            if(board[i][j] == 128 && !reached128) {

            reached128 = true;

            show256Message();}
            
            if(board[i][j] == 0) {

                labels[i][j].setText("");

            } else {

                labels[i][j].setText(
                    String.valueOf(board[i][j])
    
                );
            }
            setTileColor(labels[i][j], board[i][j]);
        }
    }

    lblScore.setText(
        "Score: " + gameBoard.getScore());
    
    if(gameBoard.isWin()) {

        lblStatus.setText("YOU WIN!");
        if(!winShown) {

        winShown = true;

        showWin();
    }
    }

    else if(gameBoard.isGameOver()) {

        lblStatus.setText("GAME OVER");
        if(!gameOverShown) {

        gameOverShown = true;
        showGameOver();

    }
    }

    else {

        lblStatus.setText("Playing");
    }

    // skor trtinggi
    if(gameBoard.getScore() > highScore) {

        highScore = gameBoard.getScore();
        saveHighScore();
    }

    lblHighScore.setText(
        "High Score: " + highScore
    );   
}
    private void showGameOver() {
    SoundManager.pauseBackgroundMusic();
    playGameOverSound();
    if(SoundManager.isMusicEnabled()) {
    SoundManager.resumeBackgroundMusic();}
    Object[] options = {
        "Play Again",
        "Exit"
    };

    int choice = JOptionPane.showOptionDialog(
        this,
        "<html><div style='text-align:center;'>"
        + "<h1>GAME OVER</h1>"
        + "<br>Your Score : " + gameBoard.getScore()
        + "</div></html>",
        "2048 Game",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.ERROR_MESSAGE,
        null,
        options,
        options[0]
    );

    if(choice == 0) {

        gameBoard.initializeBoard();
        if(SoundManager.isMusicEnabled()) {
        SoundManager.resumeBackgroundMusic();}
        gameOverShown = false;
        winShown = false;
        updateBoard();
        requestFocusInWindow();

    } else {

        System.exit(0);

    }
}
   private void showWin() {
    SoundManager.pauseBackgroundMusic();
    playWinSound();
    if(SoundManager.isMusicEnabled()) {
    SoundManager.resumeBackgroundMusic();}
    Object[] options = {
        "Continue",
        "Restart"
    };

    int choice = JOptionPane.showOptionDialog(
        this,
        "<html><div style='text-align:center;'>"
        + "<h1>✅ YOU WIN!</h1>"
        + "<br>You reached 2048!"
        + "<br><br>Score : " + gameBoard.getScore()
        + "</div></html>",
        "2048 Game",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        options,
        options[0]
    );

    if(choice == 1) {

        gameBoard.initializeBoard();
        if(SoundManager.isMusicEnabled()) {
        SoundManager.resumeBackgroundMusic();}
        winShown = false;
        gameOverShown = false;

        updateBoard();

        requestFocusInWindow();
    }
}
    private void show256Message() {
    SoundManager.pauseBackgroundMusic();
    play256Sound();

    JOptionPane.showMessageDialog(
        this,
        "<html><center>"
        + "<h1>✨ AMAZING ✨</h1>"
        + "<br>You reached 128!"
        + "</center></html>",
        "Achievement",
        JOptionPane.INFORMATION_MESSAGE       
    );
    if(SoundManager.isMusicEnabled()) {
        SoundManager.resumeBackgroundMusic();
    }
}
    private void loadHighScore() {

    try {

        File file = new File("highscore.txt");

        BufferedReader br =
                new BufferedReader(new FileReader(file));

        highScore = Integer.parseInt(br.readLine());

        br.close();

    } catch(Exception e) {

        highScore = 0;

    }
}
   private void saveHighScore() {

    try {

        FileWriter writer =
                new FileWriter("highscore.txt");

        writer.write(String.valueOf(highScore));

        writer.close();

    } catch(Exception e) {

        e.printStackTrace();

    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoPanel = new javax.swing.JPanel();
        lblScore = new javax.swing.JLabel();
        lblHighScore = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        btnRestart = new javax.swing.JButton();
        boardPanel = new javax.swing.JPanel();
        tile00 = new javax.swing.JLabel();
        tile01 = new javax.swing.JLabel();
        tile02 = new javax.swing.JLabel();
        tile03 = new javax.swing.JLabel();
        tile10 = new javax.swing.JLabel();
        tile11 = new javax.swing.JLabel();
        tile12 = new javax.swing.JLabel();
        tile13 = new javax.swing.JLabel();
        tile20 = new javax.swing.JLabel();
        tile21 = new javax.swing.JLabel();
        tile22 = new javax.swing.JLabel();
        tile23 = new javax.swing.JLabel();
        tile30 = new javax.swing.JLabel();
        tile31 = new javax.swing.JLabel();
        tile32 = new javax.swing.JLabel();
        tile33 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        lblScore.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        lblScore.setText("jLabel2");

        lblHighScore.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        lblHighScore.setText("jLabel3");

        lblStatus.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        lblStatus.setText("jLabel1");
        lblStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        btnRestart.setBackground(new java.awt.Color(102, 102, 255));
        btnRestart.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        btnRestart.setForeground(new java.awt.Color(255, 255, 255));
        btnRestart.setText("Restart");
        btnRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(lblScore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHighScore)
                        .addGap(49, 49, 49))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(lblStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRestart)
                        .addGap(59, 59, 59))))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblScore)
                    .addComponent(lblHighScore, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(btnRestart))
                .addGap(30, 30, 30))
        );

        boardPanel.setBackground(new java.awt.Color(255, 255, 255));
        boardPanel.setPreferredSize(new java.awt.Dimension(400, 500));
        boardPanel.setLayout(new java.awt.GridLayout(4, 4, 5, 5));

        tile00.setBackground(new java.awt.Color(204, 204, 204));
        tile00.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile00.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile00.setOpaque(true);
        boardPanel.add(tile00);

        tile01.setBackground(new java.awt.Color(204, 204, 204));
        tile01.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile01.setOpaque(true);
        boardPanel.add(tile01);

        tile02.setBackground(new java.awt.Color(204, 204, 204));
        tile02.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile02.setOpaque(true);
        boardPanel.add(tile02);

        tile03.setBackground(new java.awt.Color(204, 204, 204));
        tile03.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile03.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile03.setOpaque(true);
        boardPanel.add(tile03);

        tile10.setBackground(new java.awt.Color(204, 204, 204));
        tile10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile10.setOpaque(true);
        boardPanel.add(tile10);

        tile11.setBackground(new java.awt.Color(204, 204, 204));
        tile11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile11.setOpaque(true);
        boardPanel.add(tile11);

        tile12.setBackground(new java.awt.Color(204, 204, 204));
        tile12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile12.setOpaque(true);
        boardPanel.add(tile12);

        tile13.setBackground(new java.awt.Color(204, 204, 204));
        tile13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile13.setOpaque(true);
        boardPanel.add(tile13);

        tile20.setBackground(new java.awt.Color(204, 204, 204));
        tile20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile20.setOpaque(true);
        boardPanel.add(tile20);

        tile21.setBackground(new java.awt.Color(204, 204, 204));
        tile21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile21.setOpaque(true);
        boardPanel.add(tile21);

        tile22.setBackground(new java.awt.Color(204, 204, 204));
        tile22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile22.setOpaque(true);
        boardPanel.add(tile22);

        tile23.setBackground(new java.awt.Color(204, 204, 204));
        tile23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile23.setOpaque(true);
        boardPanel.add(tile23);

        tile30.setBackground(new java.awt.Color(204, 204, 204));
        tile30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile30.setOpaque(true);
        boardPanel.add(tile30);

        tile31.setBackground(new java.awt.Color(204, 204, 204));
        tile31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile31.setOpaque(true);
        boardPanel.add(tile31);

        tile32.setBackground(new java.awt.Color(204, 204, 204));
        tile32.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile32.setOpaque(true);
        boardPanel.add(tile32);

        tile33.setBackground(new java.awt.Color(204, 204, 204));
        tile33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tile33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tile33.setOpaque(true);
        boardPanel.add(tile33);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestartActionPerformed
    gameBoard.initializeBoard();
    gameOverShown = false;
    winShown = false;
    updateBoard();

    requestFocusInWindow();
        
    }//GEN-LAST:event_btnRestartActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_LEFT) {

        gameBoard.moveLeft();

        updateBoard();
    }
        if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {

        gameBoard.moveRight();

        updateBoard();
    }
        if(evt.getKeyCode() == KeyEvent.VK_UP) {

        gameBoard.moveUp();

        updateBoard();
}
        if(evt.getKeyCode() == KeyEvent.VK_DOWN) {

        gameBoard.moveDown();

        updateBoard();
}
    }//GEN-LAST:event_formKeyPressed

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {

    java.awt.EventQueue.invokeLater(() -> {new Home().setVisible(true);
    });

}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JButton btnRestart;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel lblHighScore;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel tile00;
    private javax.swing.JLabel tile01;
    private javax.swing.JLabel tile02;
    private javax.swing.JLabel tile03;
    private javax.swing.JLabel tile10;
    private javax.swing.JLabel tile11;
    private javax.swing.JLabel tile12;
    private javax.swing.JLabel tile13;
    private javax.swing.JLabel tile20;
    private javax.swing.JLabel tile21;
    private javax.swing.JLabel tile22;
    private javax.swing.JLabel tile23;
    private javax.swing.JLabel tile30;
    private javax.swing.JLabel tile31;
    private javax.swing.JLabel tile32;
    private javax.swing.JLabel tile33;
    // End of variables declaration//GEN-END:variables
}


