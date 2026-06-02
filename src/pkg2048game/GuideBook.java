package pkg2048game;

import javax.swing.*;
import java.awt.*;

public class GuideBook extends JFrame {

    public GuideBook() {

        setTitle("Guide Book");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        getContentPane().setBackground(new Color(24, 24, 24));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(24, 24, 24));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 30, 30));
        header.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JLabel title = new JLabel(
                "<html><center>Cara Bermain <font color='#5C54FF'>2048</font></center></html>"
        );

        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);

        header.add(title);

        // ================= VISUAL SECTION =================
        JPanel visualSection = new JPanel(new GridLayout(1, 2, 20, 20));
        visualSection.setBackground(new Color(24, 24, 24));
        visualSection.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ===== Keyboard =====
        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setBackground(new Color(35, 35, 35));

        keyboardPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        "Keyboard",
                        0,
                        0,
                        new Font("Segoe UI", Font.BOLD, 18),
                        Color.WHITE
                )
        );

        JLabel keyboardLabel = new JLabel(
                "<html><center>"
                + "<div style='font-size:50px'>↑</div>"
                + "<div style='font-size:50px'>← ↓ →</div>"
                + "<br>"
                + "<div style='font-size:16px'>Gunakan tombol panah keyboard</div>"
                + "</center></html>"
        );

        keyboardLabel.setForeground(Color.WHITE);

        keyboardPanel.add(keyboardLabel);

        // ===== Board Example =====
        JPanel boardPanel = new JPanel(new GridLayout(4, 4, 8, 8));
        boardPanel.setBackground(new Color(187, 173, 160));

        boardPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        "Contoh Papan Awal",
                        0,
                        0,
                        new Font("Segoe UI", Font.BOLD, 18),
                        Color.WHITE
                )
        );

        for (int i = 0; i < 16; i++) {

            JPanel tile = new JPanel(new BorderLayout());

            tile.setBackground(new Color(205, 193, 180));

            boardPanel.add(tile);
        }

        JPanel tile2 = (JPanel) boardPanel.getComponent(1);
        tile2.setBackground(new Color(238, 228, 218));

        JLabel lbl2 = new JLabel("2", SwingConstants.CENTER);
        lbl2.setFont(new Font("Segoe UI", Font.BOLD, 28));
        tile2.add(lbl2);

        JPanel tile4 = (JPanel) boardPanel.getComponent(6);
        tile4.setBackground(new Color(237, 224, 200));

        JLabel lbl4 = new JLabel("4", SwingConstants.CENTER);
        lbl4.setFont(new Font("Segoe UI", Font.BOLD, 28));
        tile4.add(lbl4);

        visualSection.add(keyboardPanel);
        visualSection.add(boardPanel);

        // ================= INFO SECTION =================
        JPanel infoSection = new JPanel(new GridLayout(2, 2, 20, 20));

        infoSection.setBackground(new Color(24, 24, 24));

        infoSection.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        infoSection.add(createCard(
                "1. Papan 4x4",
                "Permainan dimulai pada papan berukuran 4 baris × 4 kolom. Dua tile awal akan muncul secara acak."
        ));

        infoSection.add(createCard(
                "2. Geser Tile",
                "Gunakan tombol panah (↑ ↓ ← →) untuk menggeser semua tile ke satu arah."
        ));

        infoSection.add(createCard(
                "3. Gabungkan Tile",
                "Jika dua tile dengan angka yang sama bertemu, keduanya akan bergabung menjadi satu tile baru."
        ));

        infoSection.add(createCard(
                "4. Menang",
                "Terus gabungkan angka hingga berhasil mendapatkan tile bernilai 2048."
        ));

        // ================= BACK BUTTON =================
        JButton btnBack = new JButton("Back");

        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBack.setBackground(new Color(92, 84, 255));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);

        btnBack.addActionListener(e -> dispose());

        // ================= ADD COMPONENT =================
        mainPanel.add(header);
        mainPanel.add(visualSection);
        mainPanel.add(infoSection);

        JScrollPane scrollPane = new JScrollPane(mainPanel);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
    }

    private JPanel createCard(String title, String description) {

        JPanel card = new JPanel(new BorderLayout());

        card.setBackground(new Color(35, 35, 35));

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel lblTitle = new JLabel(title);

        lblTitle.setForeground(Color.WHITE);

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        JLabel lblDesc = new JLabel(
                "<html><body style='width:250px'>" +
                description +
                "</body></html>"
        );

        lblDesc.setForeground(Color.LIGHT_GRAY);

        lblDesc.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblDesc, BorderLayout.CENTER);

        return card;
    }
}
