package ui;

import model.Dealer;
import model.Gambler;
import model.ListOfGamblers;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    private JLabel scoreboardWinsLabel;
    private JButton scoreboardBackToMenuButton;


    private Dealer dealer;
    private ListOfGamblers listOfGamblers;
    private static final String SAVELOCATION = "./data/BlackJackGame.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final int xSize = 1280;
    private static final int ySize = 720;
    private static final ImageIcon image = new ImageIcon("./data/featureDriver.png");

    @SuppressWarnings("methodlength")
    public BlackjackGUI() {
        welcomeMenuNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfGamblers = new ListOfGamblers(numPlayerSelectBox.getSelectedIndex() + 1,
                        Integer.parseInt(startingMoneyTextField.getText()));
                jsonWriter = new JsonWriter(SAVELOCATION);
                jsonReader = new JsonReader(SAVELOCATION);
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
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGameData();
                JOptionPane.showMessageDialog(null, "Saved game data to " + SAVELOCATION,
                        "Saved Successfully", JOptionPane.INFORMATION_MESSAGE, image);
            }
        });
        loadSGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGameData();
                JOptionPane.showMessageDialog(null, "Loaded game data from " + SAVELOCATION,
                        "Loaded Successfully", JOptionPane.INFORMATION_MESSAGE, image);
            }
        });
        viewScoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiCardLayout.removeAll();
                guiCardLayout.add(scoreboardJPanel);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
                addGamblerData();
            }
        });
        scoreboardBackToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiCardLayout.removeAll();
                guiCardLayout.add(gameMenuCardLayout);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
            }
        });
    }

    private void addGamblerData() {
        scoreboardPlayerJPanel.setLayout(new BoxLayout(scoreboardPlayerJPanel, BoxLayout.Y_AXIS));
        scoreboardWinsJPanel.setLayout(new BoxLayout(scoreboardWinsJPanel, BoxLayout.Y_AXIS));
        scoreboardLossesJPanel.setLayout(new BoxLayout(scoreboardLossesJPanel, BoxLayout.Y_AXIS));
        scoreboardDrawsJPanel.setLayout(new BoxLayout(scoreboardDrawsJPanel, BoxLayout.Y_AXIS));
        scoreboardBalanceJPanel.setLayout(new BoxLayout(scoreboardBalanceJPanel, BoxLayout.Y_AXIS));
        scoreboardPlayerJPanel.removeAll();
        scoreboardWinsJPanel.removeAll();
        scoreboardLossesJPanel.removeAll();
        scoreboardDrawsJPanel.removeAll();
        scoreboardBalanceJPanel.removeAll();
        for (Gambler gambler: listOfGamblers.getGamblers()) {
            scoreboardPlayerJPanel.add(new JLabel("Player " + gambler.getGamblerID(),
                    SwingConstants.CENTER));
            scoreboardWinsJPanel.add(new JLabel(Integer.toString(gambler.getWins()),
                    SwingConstants.CENTER));
            scoreboardLossesJPanel.add(new JLabel(Integer.toString(gambler.getLosses(),
                    SwingConstants.CENTER)));
            scoreboardDrawsJPanel.add(new JLabel(Integer.toString(gambler.getDraws(),
                    SwingConstants.CENTER)));
            scoreboardBalanceJPanel.add(new JLabel(Integer.toString(gambler.getBalance(),
                    SwingConstants.CENTER)));
        }
    }


    // EFFECTS: saves the gambler data to file
    private void saveGameData() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfGamblers);
            jsonWriter.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not save",
                    "Failed to save", JOptionPane.INFORMATION_MESSAGE, image);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads gambler data from file
    private void loadGameData() {
        try {
            listOfGamblers = jsonReader.read();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not load",
                    "Failed to save", JOptionPane.INFORMATION_MESSAGE, image);
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("BlackjackGUI");
        frame.setContentPane(new BlackjackGUI().guiCardLayout);
        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);

    }
}
