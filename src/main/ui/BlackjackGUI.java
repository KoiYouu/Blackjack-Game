package ui;

import model.Cards;
import model.Dealer;
import model.Gambler;
import model.ListOfGamblers;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// GUI interface for blackjack game, handles some logic, and also where the game starts

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
    private JLabel addPlayerLabel;
    private JLabel addPlayerHelpLabel;
    private JPanel addPlayerBalanceJPanel;
    private JTextField startingBalanceAddPlayerJPanel;
    private JButton addPlayerConfirmButton;
    private JLabel addBalanceLabel;
    private JLabel addBalanceSelectPlayerLabel;
    private JButton addBalanceConfirmButton;
    private JPanel addBalanceSelectPlayerJPanel;
    private JPanel addBalanceWriteBalanceJPanel;
    private JComboBox addBalanceSelectPlayerComboBox;
    private JTextField addBalanceTextField;
    private JPanel dealerJPanel;
    private JLabel dealersLabel;
    private JPanel playersJPanel;
    private JPanel bottomLeftPlayersJPanel;
    private JPanel topRightPlayersJPanel;
    private JPanel topLeftPlayersJPanel;
    private JPanel bottomRightPlayersJPanel;
    private JPanel topLeftPlayersCardsJPanel;
    private JPanel bottomRightPlayersCardsJPanel;
    private JPanel bottomLeftPlayersCardsJPanel;
    private JPanel topRightPlayersCardsJPanel;
    private JPanel topLeftPlayersInfoJPanel;
    private JLabel topLeftPlayersCardTotalLabel;
    private JPanel bottomLeftPlayersInfoJPanel;
    private JPanel bottomRightPlayersInfoJPanel;
    private JPanel topRightPlayersInfoJPanel;
    private JLabel topLeftPlayersLabel;
    private JLabel topRightPlayersLabel;
    private JLabel bottomRightPlayersLabel;
    private JLabel bottomLeftPlayersLabel;
    private JLabel topRightPlayersCardTotalLabel;
    private JLabel bottomLeftPlayersCardTotalLabel;
    private JLabel bottomRightPlayersCardTotalLabel;
    private JLabel dealerCardTotalLabel;
    private JPanel dealersCardsJPanel;
    private JPanel playSectionJPanel;
    private JButton hitButton;
    private JButton doubleButton;
    private JButton standButton;
    private JPanel dealerInfoJPanel;


    private Dealer dealer;
    private ListOfGamblers listOfGamblers;
    private static final String SAVELOCATION = "./data/BlackJackGame.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final int xSize = 640;
    private static final int ySize = 480;
    private static final ImageIcon image = new ImageIcon("./data/featureDriver.png");
    private static final ImageIcon frontCard = new ImageIcon("./data/frontCard.png");
    private static final ImageIcon backCard = new ImageIcon("./data/backCard.png");

    @SuppressWarnings("methodlength")
    public BlackjackGUI() {
        welcomeMenuNextButton.addActionListener(new ActionListener() {
            @Override // welcome screen next button handler
            public void actionPerformed(ActionEvent e) {
                dealer = new Dealer();
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
            @Override // key handler for welcome screen to insure only numbers entered
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        saveGameButton.addActionListener(new ActionListener() {
            @Override // saves game data and displays message
            public void actionPerformed(ActionEvent e) {
                saveGameData();
                JOptionPane.showMessageDialog(null, "Saved game data to " + SAVELOCATION,
                        "Saved Successfully", JOptionPane.INFORMATION_MESSAGE, image);
            }
        });
        loadSGameButton.addActionListener(new ActionListener() {
            @Override  // loads game data and displays message
            public void actionPerformed(ActionEvent e) {
                loadGameData();
                JOptionPane.showMessageDialog(null, "Loaded game data from " + SAVELOCATION,
                        "Loaded Successfully", JOptionPane.INFORMATION_MESSAGE, image);
            }
        });
        viewScoreboardButton.addActionListener(new ActionListener() {
            @Override  // button handler to move from menu to scoreboard
            public void actionPerformed(ActionEvent e) {
                guiCardLayout.removeAll();
                guiCardLayout.add(scoreboardJPanel);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
                addGamblerData();
            }
        });
        scoreboardBackToMenuButton.addActionListener(new ActionListener() {
            @Override // button handler to go from scoreboard back to menu
            public void actionPerformed(ActionEvent e) {
                guiCardLayout.removeAll();
                guiCardLayout.add(gameMenuCardLayout);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
            }
        });
        startingBalanceAddPlayerJPanel.addKeyListener(new KeyAdapter() {
            @Override // key handler to ensure only numbers are entered in addPlayer starting balance
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        addPlayerButton.addActionListener(new ActionListener() {
            @Override // button handler to move from menu to addPlayer menu only if players < 4
            public void actionPerformed(ActionEvent e) {
                if (listOfGamblers.getGamblers().size() >= 4) {
                    JOptionPane.showMessageDialog(null, "Already 4 players, cannot add more",
                            "Full Players", JOptionPane.INFORMATION_MESSAGE, image);
                } else {
                    guiCardLayout.removeAll();
                    guiCardLayout.add(addPlayerJPanel);
                    guiCardLayout.repaint();
                    guiCardLayout.revalidate();
                }
            }
        });
        addPlayerConfirmButton.addActionListener(new ActionListener() {
            @Override // button handler to move from addPlayer menu to menu and adds newly created player
            public void actionPerformed(ActionEvent e) {
                listOfGamblers.getGamblers().add(
                        new Gambler(Integer.parseInt(startingBalanceAddPlayerJPanel.getText())));
                startingBalanceAddPlayerJPanel.setText("");
                guiCardLayout.removeAll();
                guiCardLayout.add(gameMenuCardLayout);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
            }
        });
        addBalanceButton.addActionListener(new ActionListener() {
            @Override  // button handler to move from menu to addBalance menu and sets the ComboBox and text
            public void actionPerformed(ActionEvent e) {
                addBalanceSelectPlayerLabel.setText("Select Players 1 - " + listOfGamblers.getGamblers().size());
                String[] comboBoxValues = new String[listOfGamblers.getGamblers().size()];
                for (int i = 0; i < listOfGamblers.getGamblers().size(); i++) {
                    comboBoxValues[i] = Integer.toString(listOfGamblers.getGamblers(i).getGamblerID());
                }
                guiCardLayout.removeAll();
                guiCardLayout.add(addBalanceJPanel);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
                addBalanceSelectPlayerComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues));
            }
        });

        addBalanceTextField.addKeyListener(new KeyAdapter() {
            @Override // key handler to make sure that addBalance field is only numbers
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        addBalanceConfirmButton.addActionListener(new ActionListener() {
            @Override // button handler to move from addBalance to menu, adds balance, and resets text box
            public void actionPerformed(ActionEvent e) {
                listOfGamblers.getGamblers(
                        addBalanceSelectPlayerComboBox.getSelectedIndex()).addBalance(
                                Integer.parseInt(addBalanceTextField.getText()));
                addBalanceTextField.setText("");
                guiCardLayout.removeAll();
                guiCardLayout.add(gameMenuCardLayout);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override   // button handler to move from menu to playing, collects players bets, ensures every player
                        // is valid to start, and does the pregame setup for cards
            public void actionPerformed(ActionEvent e) {
                playersValidToStart();
                collectBets();
                guiCardLayout.removeAll();
                guiCardLayout.add(gameplayJPanel);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
                setScene();
                startGame();
                displayCards();
            }
        });
    }

    // MODIFIES: This, ListOfGamblers
    // EFFECTS: Creates new labels for each gambler's stats and puts them onto the JFrame for display
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

    // EFFECTS: checks if players are valid to start the game by seeing if they have a positive balance
    public void playersValidToStart() {
        for (Gambler gambler: listOfGamblers.getGamblers()) {
            if (gambler.getBalance() <= 0) {
                JOptionPane.showMessageDialog(null, "Not enough balance on player" + gambler.getGamblerID()
                        + "please add balance", "Not Enough Money", JOptionPane.INFORMATION_MESSAGE, image);
                addBalanceSelectPlayerLabel.setText("Select Players 1 - " + listOfGamblers.getGamblers().size());
                String[] comboBoxValues = new String[listOfGamblers.getGamblers().size()];
                for (int i = 0; i < listOfGamblers.getGamblers().size(); i++) {
                    comboBoxValues[i] = Integer.toString(listOfGamblers.getGamblers(i).getGamblerID());
                }
                guiCardLayout.removeAll();
                guiCardLayout.add(addBalanceJPanel);
                guiCardLayout.repaint();
                guiCardLayout.revalidate();
                addBalanceSelectPlayerComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues));
            }
        }
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: displays each player and dealers cards by creating new JLabels for each of them and
    // adding them to the correct panel location and updates the players total hand total
    public void displayCards() {
        int count = 0;
        for (Cards card: dealer.getHand()) {
            if (card.getFacingUp()) {
                String cardName = card.getCardName();
                String suit = card.getSuit();
                JLabel temp = new JLabel(cardName + "  " + suit, frontCard, JLabel.CENTER);
                temp.setHorizontalTextPosition(JLabel.CENTER);
                dealersCardsJPanel.add(temp);
            } else {
                dealersCardsJPanel.add(new JLabel(backCard, JLabel.CENTER));
            }
        }
        for (Gambler gambler: listOfGamblers.getGamblers()) {
            for (Cards card: gambler.getHand()) {
                String cardName = card.getCardName();
                String suit = card.getSuit();
                JLabel temp = new JLabel(cardName + "  " + suit, frontCard, JLabel.CENTER);
                temp.setHorizontalTextPosition(JLabel.CENTER);
                if (count == 0) {
                    topLeftPlayersCardsJPanel.add(temp);
                } else if (count == 1) {
                    bottomLeftPlayersCardsJPanel.add(temp);
                } else if (count == 2) {
                    topRightPlayersCardsJPanel.add(temp);
                } else {
                    bottomRightPlayersCardsJPanel.add(temp);
                }
            }
            count++;
        }
    }

    // MODIFIES: this
    // EFFECTS: properly renames each player section
    public void setScene() {
        int count = 0;
        for (Gambler gambler: listOfGamblers.getGamblers()) {
            if (count == 0) {
                topLeftPlayersLabel.setText("Player " + gambler.getGamblerID() + "\'s Cards:");
            } else if (count == 1) {
                bottomLeftPlayersLabel.setText("Player " + gambler.getGamblerID() + "\'s Cards:");
            } else if (count == 2) {
                topRightPlayersLabel.setText("Player " + gambler.getGamblerID() + "\'s Cards:");
            } else if (count == 3) {
                bottomRightPlayersLabel.setText("Player " + gambler.getGamblerID() + "\'s Cards:");
            }
            count++;
        }
    }

    // MODIFIES: dealer, player
    // EFFECTS: starts the game of blackjack off by giving the deal 2 cards, 1 face up and another face down, then
    // gives every player 2 cards and displays the current state of the game.
    public void startGame() {
        System.out.println("Dealers turn!\n");
        dealer.startingDealerTurn();
        System.out.println("Dealer draws: " + dealer.getDealersCards()  + " Hand total: "
                + dealer.getHand().get(0).getValue() + "\n");
        System.out.println("Now drawing 2 cards for each player:\n");
        for (int i = 0; i < listOfGamblers.getGamblers().size(); i++) {
            listOfGamblers.getGamblers(i).hitCard();
            listOfGamblers.getGamblers(i).hitCard();
        }
        System.out.println(listOfGamblers.getAllGamblersCards());
    }

    //TODO MAKE IT ONLY COLLECT VALID BETS
    // EFFECTS: collects bets from all gamblers
    public void collectBets() {
        for (Gambler gambler: listOfGamblers.getGamblers()) {
            int bet = Integer.parseInt(JOptionPane.showInputDialog("Player" + gambler.getGamblerID()
                    + "How much would you like to bet?"));
            gambler.setBet(bet);
        }
    }

    // EFFECTS: starts the program
    public static void main(String[] args) {

        JFrame frame = new JFrame("BlackjackGUI");
        frame.setContentPane(new BlackjackGUI().guiCardLayout);
        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(xSize,ySize);
        frame.setResizable(true);
        frame.setVisible(true);

    }
}
