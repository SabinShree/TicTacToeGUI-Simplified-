import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    private JButton[][] buttons = new JButton[3][3];
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel gamePanel;
    private JPanel menuPanel;
    private JButton resetButton;
    private JButton newGameButton;
    private int currentPlayer = 1;
    private String player1 = "X";
    private String player2 = "Y";

    public TicTacToeGUI() {

        frame = new JFrame("Tic Tac Toe");
        mainPanel = new JPanel(new BorderLayout());
        gamePanel = new JPanel(new GridLayout(3, 3));
        menuPanel = new JPanel(new BorderLayout());
        resetButton = new JButton("Reset Game");
        resetButton.setBackground(Color.gray);
        resetButton.setForeground(Color.white);
        menuPanel.add(resetButton, BorderLayout.WEST);
        newGameButton = new JButton("New Game");
        JButton quigame = new JButton("Quit Game");
        quigame.setBackground(Color.RED);
        quigame.setForeground(new Color(244, 222, 222));
        menuPanel.add(quigame, BorderLayout.EAST);
        quigame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max = JOptionPane.showConfirmDialog(gamePanel, "Do you really want to quit the game", "Game Exit", JOptionPane.YES_NO_CANCEL_OPTION);
                if (max <= 0) {
                    System.exit(0);
                }
            }
        });
        newGameButton.setBackground(Color.GREEN);
        newGameButton.setForeground(Color.BLUE);
        newGameButton.addActionListener(new ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                res();
            }
        });

        menuPanel.add(newGameButton, BorderLayout.CENTER);

        menuPanel.setBackground(new Color(0, 0, 0));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorder(new LineBorder(Color.black, 1, true));
                buttons[i][j].setVisible(true);
                gamePanel.add(buttons[i][j]);
                buttons[i][j].addActionListener(e -> {
                    for (int i1 = 0; i1 < 3; i1++) {
                        for (int j1 = 0; j1 < 3; j1++) {
                            if (e.getSource() == buttons[i1][j1]) {
                                if (currentPlayer == 1) {
                                    System.out.println("Selected " + currentPlayer);
                                    buttons[i1][j1].setText(player1);
                                    buttons[i1][j1].setFont(new Font("Arial", Font.BOLD, 23));
                                    buttons[i1][j1].setForeground(Color.RED);
                                    buttons[i1][j1].setEnabled(false);
                                    if (finalWinner()) {
                                        JOptionPane.showMessageDialog(mainPanel, "Game won by Player 1", "Winner", JOptionPane.PLAIN_MESSAGE);
                                        for (int q = 0; q < 3; q++) {
                                            for (int jq = 0; jq < 3; jq++) {
                                                buttons[q][jq].setText("");
                                                buttons[q][jq].setEnabled(true);
                                            }
                                        }
                                        break;
                                    }
                                    currentPlayer = 2;

                                } else if (currentPlayer == 2) {
                                    if (e.getSource() == buttons[i1][j1]) {
                                        buttons[i1][j1].setText(player2);
                                        System.out.println("Selected " + currentPlayer);
                                        buttons[i1][j1].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 23));
                                        buttons[i1][j1].setForeground(new Color(70, 150, 120));
                                        buttons[i1][j1].setEnabled(false);
                                        if (finalWinner()) {
                                            JOptionPane.showMessageDialog(mainPanel, "Game won by Player 2", "Winner", JOptionPane.PLAIN_MESSAGE);
                                            for (int q = 0; q < 3; q++) {
                                                for (int jq = 0; jq < 3; jq++) {
                                                    buttons[q][jq].setText("");
                                                    buttons[q][jq].setEnabled(true);
                                                }
                                            }
                                            break;
                                        }
                                        currentPlayer = 1;

                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

        mainPanel.setPreferredSize(new Dimension(400, 400));
        gamePanel.setPreferredSize(new Dimension(300, 310));
        menuPanel.setPreferredSize(new Dimension(300, 50));
        mainPanel.add(gamePanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        resetGame();
    }
    
    public void res() {
     for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                        buttons[i][j].setEnabled(true);
                    }
                }
    }

    public void resetGame() {
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               res();
            }
        });
    }

    public boolean finalWinner() {
        return checkRowWinner() || checkColumnWinner() || checkDiagonalWinner();
    }

    public boolean checkRowWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkWinner(buttons[i][0].getText(), buttons[i][1].getText(), buttons[i][2].getText())) return true;
        }
        return false;
    }

    public boolean checkColumnWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkWinner(buttons[0][i].getText(), buttons[1][i].getText(), buttons[2][i].getText())) return true;
        }
        return false;
    }

    public boolean checkDiagonalWinner() {
        if (checkWinner(buttons[0][0].getText(), buttons[1][1].getText(), buttons[2][2].getText()) || (checkWinner(buttons[0][2].getText(), buttons[1][1].getText(), buttons[2][0].getText())))
            return true;
        else return false;
    }

    public boolean checkWinner(String first, String second, String third) {
        return (!first.equals("")) && (first.equals(second) && (second.equals(third)));
    }

    public static void main(String[] args) {
        new TicTacToeGUI();


    }


}
