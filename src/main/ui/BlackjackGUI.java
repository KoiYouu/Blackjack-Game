package ui;

import model.Dealer;
import model.ListOfGamblers;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

public class BlackjackGUI extends JPanel {
    private JPanel guiCardLayout;
    private JTextField startingMoneyTextField;
    private JLabel welcomeText;
    private JComboBox numPlayerSelectBox;
    private JPanel gameMenuCardLayout;
    private JPanel welcomeJPanel;
    private JButton playButton;
    private JButton viewScoreboardButton;
    private JButton addBalanceButton;
    private JButton addPlayerButton;
    private JButton saveGameButton;
    private JButton loadSGameButton;
    private JLabel blackJackDisplayLabel;
    private JPanel gameMainMenuJPanel;
    private JPanel textDisplayJPanel;
    private JPanel menuBottomRowButtonJPanel;
    private JPanel menuTopRowButtonJPanel;
    private JButton welcomeMenuNextButton;
    private JPanel gameplayJPanel;
    private JPanel scoreboardJPanel;
    private JPanel addPlayerJPanel;
    private JPanel addBalanceJPanel;
    private JLabel scoreboardPlayerLabel;
    private JLabel scoreboardWinLabel;
    private JLabel scoreboardLossesLabel;
    private JLabel scoreboardDrawsLabel;
    private JLabel scoreboardBalanceLabel;
    private JPanel scoreboardPlayerJPanel;
    private JPanel scoreboardLossesJPanel;
    private JPanel scoreboardDrawsJPanel;
    private JPanel scoreboardBalanceJPanel;
    private JPanel scoreboardWinsJPanel;
    private JLabel scoreboardLabel;
    private JPanel numPlayerJPanel;
    private JPanel startingMoneyJPanel;
    private JLabel numPlayerLabel;
    private JLabel startingMoneyLabel;


    private Dealer dealer;
    private ListOfGamblers listOfGamblers;
    private static final String SAVELOCATION = "./data/BlackJackGame.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final int xSize = 1280;
    private static final int ySize = 720;

    @SuppressWarnings("methodlength")
    public BlackjackGUI() {
        welcomeMenuNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfGamblers = new ListOfGamblers(numPlayerSelectBox.getSelectedIndex() + 1,
                        Integer.parseInt(startingMoneyTextField.getText()));
                guiCardLayout.removeAll();
                guiCardLayout.add(gameMenuCardLayout);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
            }
        });
        startingMoneyTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        numPlayerSelectBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BlackjackGUI");
        frame.setContentPane(new BlackjackGUI().guiCardLayout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
