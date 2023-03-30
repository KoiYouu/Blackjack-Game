package ui;

import model.Cards;
import model.Dealer;
import model.Gambler;
import model.ListOfGamblers;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.Clip;

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
    private JLabel whichPlayersTurnLabel;
    private JButton toggleMusic;


    private Dealer dealer; // blackjack dealer
    private ListOfGamblers listOfGamblers; // List of all the gamblers
    private static final String SAVELOCATION = "./data/BlackJackGame.json"; // save location
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private int delay = 3000; // milliseconds
    private Timer timer;
    private static int playersTurn = 0;
    private Clip clip; // music player

    private static final int xSize = 1280;
    private static final int ySize = 720;
    private static final ImageIcon image = new ImageIcon("./data/featureDriver.png");
    private static final ImageIcon frontCard = new ImageIcon("./data/frontCard.png");
    private static final ImageIcon backCard = new ImageIcon("./data/backCard.png");
    private static final String musicLocation = "./data/bangerMusic.wav";

    // MODIFIES: this, gambler, dealer, list of gambler
    // EFFECTS: starts the gui for blackjack and handles the gui inputs
    public BlackjackGUI() {
        toggleMusic = new JButton("Mute");
        menuBottomRowButtonJPanel.add(toggleMusic);
        playMusic(musicLocation);

        toggleMusicHandler();

        welcomeMenuNextButtonHandler();
        welcomeMenuStartingMoneyTextFieldKeyHandler();

        saveGameButtonHandler();

        loadGameButtonHandler();

        viewScoreboardButtonHandler();
        scoreboardBackToMenuButtonHandler();

        startingBalanceAddPlayerKeyHandler();
        addPlayerButtonHandler();
        addPlayerConfirmButtonHandler();

        addBalanceButtonHandler();
        addBalanceTextFieldKeyHandler();
        addBalanceConfirmButtonHandler();

        playButtonHandler();
    }

    // MODIFIES: clip, this
    // EFFECTS: button handler that mutes and unmutes the music, then changes the text to be unmute or muted
    private void toggleMusicHandler() {
        toggleMusic.addActionListener(e -> {
            if (toggleMusic.getText().equals("Mute")) {
                clip.stop();
                toggleMusic.setText("Unmute");
            } else {
                clip.start();
                toggleMusic.setText("Mute");
            }
        });
    }

    // MODIFIES: clip, audioinputstream
    // EFFECTS: plays music
    public void playMusic(String musicLocation) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(musicLocation).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Unexpected");
        }
    }

    // MODIFIES: this, dealer, listOfGambler, gambler, jsonWriter, jsonReader
    // EFFECTS: creates the dealer, listOfGamblers, jsonWriter, jsonReader, and moves the gui to the main menu
    public void welcomeMenuNextButtonHandler() {
        welcomeMenuNextButton.addActionListener(new ActionListener() {
            @Override // welcome screen next button handler
            public void actionPerformed(ActionEvent e) {
                dealer = new Dealer();
                listOfGamblers = new ListOfGamblers(numPlayerSelectBox.getSelectedIndex() + 1,
                        Integer.parseInt(startingMoneyTextField.getText()));
                jsonWriter = new JsonWriter(SAVELOCATION);
                jsonReader = new JsonReader(SAVELOCATION);
                moveToMainMenu();
            }
        });
    }

    // MODIFIES: this, dealer, listOfGambler
    // EFFECTS: handles the logic for gameplay, ensures players are valid to start, collects each players bets,
    // moves to the gameplay screen, sets up the scene, gives the dealer and players 2 cards, displays the cards,
    // and gives the inputs to the players
    public void playButtonHandler() {
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playersValidToStart();
                collectBets();
                moveToPlay();
                setScene();
                preGameSetup();
                displayCards();
                playersTurn();

            }
        });
    }

    // MODIFIES: this, gambler
    // EFFECTS: button handler to move from addBalance to menu, adds balance to gambler, and resets text box
    public void addBalanceConfirmButtonHandler() {
        addBalanceConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfGamblers.getGamblers(
                        addBalanceSelectPlayerComboBox.getSelectedIndex()).addBalance(
                                Integer.parseInt(addBalanceTextField.getText()));
                addBalanceTextField.setText("");
                moveToMainMenu();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: key handler to make sure that addBalance field is only numbers
    public void addBalanceTextFieldKeyHandler() {
        addBalanceTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: button handler to move from menu to addBalance menu and sets the ComboBox and text
    public void addBalanceButtonHandler() {
        addBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBalanceSelectPlayerLabel.setText("Select Players 1 - " + listOfGamblers.getGamblers().size());
                String[] comboBoxValues = new String[listOfGamblers.getGamblers().size()];
                for (int i = 0; i < listOfGamblers.getGamblers().size(); i++) {
                    comboBoxValues[i] = Integer.toString(listOfGamblers.getGamblers(i).getGamblerID());
                }
                moveToAddBalance();
                addBalanceSelectPlayerComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues));
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: button handler to move from addPlayer menu to menu and adds newly created player
    public void addPlayerConfirmButtonHandler() {
        addPlayerConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfGamblers.getGamblers().add(
                        new Gambler(Integer.parseInt(startingBalanceAddPlayerJPanel.getText())));
                startingBalanceAddPlayerJPanel.setText("");
                moveToMainMenu();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: button handler to move from menu to addPlayer menu only if players < 4
    public void addPlayerButtonHandler() {
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listOfGamblers.getGamblers().size() >= 4) {
                    JOptionPane.showMessageDialog(null, "Already 4 players, cannot add more",
                            "Full Players", JOptionPane.INFORMATION_MESSAGE, image);
                } else {
                    moveToAddPlayer();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: key handler to ensure only numbers are entered in addPlayer starting balance
    public void startingBalanceAddPlayerKeyHandler() {
        startingBalanceAddPlayerJPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS:  button handler to go from scoreboard back to menu
    public void scoreboardBackToMenuButtonHandler() {
        scoreboardBackToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToMainMenu();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: button handler to move from menu to scoreboard
    public void viewScoreboardButtonHandler() {
        viewScoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToScoreboard();
                addGamblerData();
            }
        });
    }

    // MODIFIES: this, listOfGambler
    // EFFECTS: loads game data and displays message
    public void loadGameButtonHandler() {
        loadSGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGameData();
                JOptionPane.showMessageDialog(null, "Loaded game data from " + SAVELOCATION,
                        "Loaded Successfully", JOptionPane.INFORMATION_MESSAGE, image);
            }
        });
    }

    // EFFECTS: saves game data and displays message
    public void saveGameButtonHandler() {
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGameData();
                JOptionPane.showMessageDialog(null, "Saved game data to " + SAVELOCATION,
                        "Saved Successfully", JOptionPane.INFORMATION_MESSAGE, image);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: key handler for welcome screen to insure only numbers entered
    public void welcomeMenuStartingMoneyTextFieldKeyHandler() {
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
    }

    // MODIFIES: this, gambler
    // EFFECTS: button handler, gives the player who hit it a card, and checks if they busted or not, if they busted
    // moves the turn to the next player, and if this was the last player to bus it moves to the dealers turn and ends
    // the game. Also sets the label for whos turn it is
    private void hitButtonHandler() {
        hitButton.addActionListener(new ActionListener() {
            @Override // gameplay hit button handler
            public void actionPerformed(ActionEvent e) {
                listOfGamblers.getGamblers(playersTurn).hitCard();
                displayCards();
                if (listOfGamblers.getGamblers(playersTurn).isStand()
                        || listOfGamblers.getGamblers(playersTurn).handValueHard() > 21
                        || listOfGamblers.getGamblers(playersTurn).handValueSoft() == 21) {
                    listOfGamblers.getGamblers(playersTurn).setStand();
                    playersTurn++;
                    if (checkIfDealerTurn()) {
                        dealersTurn();
                        endGame();
                    }
                    whichPlayersTurnLabel.setText("Players " + listOfGamblers.getGamblers(playersTurn).getGamblerID()
                            + "'s turn");
                }
            }
        });
    }

    // MODIFIES: this, gambler
    // EFFECTS: button handler, gives the player who hit it a card, doubles the players bet
    // and checks if they busted or not, if they busted, moves the turn to the next player,
    // and if this was the last player to bus it moves to the dealers turn and ends the game.
    // Also sets the label for whos turn it is
    private void doubleButtonHandler() {
        doubleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfGamblers.getGamblers(playersTurn).gamblerDouble();
                displayCards();
                if (listOfGamblers.getGamblers(playersTurn).isStand()
                        || listOfGamblers.getGamblers(playersTurn).handValueHard() > 21
                        || listOfGamblers.getGamblers(playersTurn).handValueSoft() == 21) {
                    listOfGamblers.getGamblers(playersTurn).setStand();
                    playersTurn++;
                    whichPlayersTurnLabel.setText("Players " + listOfGamblers.getGamblers(playersTurn).getGamblerID()
                            + "'s turn");
                    if (checkIfDealerTurn()) {
                        dealersTurn();
                        endGame();
                    }
                }
            }
        });
    }

    // MODIFIES: this, gambler
    // EFFECTS: button handler that sets the current gambler to stand and moves the player to the next turn,
    // also checks if its the dealers turn and if it is does the dealers turn and ends the game
    private void standButtonHandler() {
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfGamblers.getGamblers(playersTurn).setStand();
                displayCards();
                playersTurn++;
                whichPlayersTurnLabel.setText("Players " + listOfGamblers.getGamblers(playersTurn).getGamblerID()
                        + "'s turn");
                if (checkIfDealerTurn()) {
                    dealersTurn();
                    endGame();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: moves to add balance screen
    public void moveToAddBalance() {
        guiCardLayout.removeAll();
        guiCardLayout.add(addBalanceJPanel);
        guiCardLayout.repaint();
        guiCardLayout.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: moves to add player screen
    public void moveToAddPlayer() {
        guiCardLayout.removeAll();
        guiCardLayout.add(addPlayerJPanel);
        guiCardLayout.repaint();
        guiCardLayout.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: moves to scoreboard screen
    public void moveToScoreboard() {
        guiCardLayout.removeAll();
        guiCardLayout.add(scoreboardJPanel);
        guiCardLayout.repaint();
        guiCardLayout.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: moves to main menu
    public void moveToMainMenu() {
        guiCardLayout.removeAll();
        guiCardLayout.add(gameMenuCardLayout);
        guiCardLayout.repaint();
        guiCardLayout.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: moves to play screen
    public void moveToPlay() {
        guiCardLayout.removeAll();
        guiCardLayout.add(gameplayJPanel);
        guiCardLayout.repaint();
        guiCardLayout.revalidate();
    }

    // MODIFIES: This, ListOfGamblers
    // EFFECTS: Creates new labels for each gambler's stats and puts them onto the JFrame for display
    public void addGamblerData() {
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
    public void saveGameData() {
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
    public void loadGameData() {
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
                moveToAddBalance();
                addBalanceSelectPlayerComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes all the displayed cards
    public void resetCardPanels() {
        dealersCardsJPanel.removeAll();
        topLeftPlayersCardsJPanel.removeAll();
        bottomLeftPlayersCardsJPanel.removeAll();
        topRightPlayersCardsJPanel.removeAll();
        bottomRightPlayersCardsJPanel.removeAll();
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: displays each player and dealers cards by creating new JLabels for each of them and
    // adding them to the correct panel location and updates the players total hand total
    public void displayCards() {
        resetCardPanels();
        int count = 0;
        dealerCardTotalLabel.setText(getHandValueDealer(dealer));
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
                placeCardsInCorrectPanel(count, temp, getHandValueGambler(gambler));
            }
            count++;
        }
    }

    // MODIFIES: this
    // EFFECTS: places the objects at the correct location
    public void placeCardsInCorrectPanel(int count, JLabel temp, String handValue) {
        if (count == 0) {
            topLeftPlayersCardsJPanel.add(temp);
            topLeftPlayersCardTotalLabel.setText(handValue);
        } else if (count == 1) {
            bottomLeftPlayersCardsJPanel.add(temp);
            bottomLeftPlayersCardTotalLabel.setText(handValue);
        } else if (count == 2) {
            topRightPlayersCardsJPanel.add(temp);
            topRightPlayersCardTotalLabel.setText(handValue);
        } else {
            bottomRightPlayersCardsJPanel.add(temp);
            bottomRightPlayersCardTotalLabel.setText(handValue);
        }
    }


    // EFFECTS: returns a string of the gamblers hand value
    public String getHandValueGambler(Gambler gambler) {
        if (gambler.checkAceInHand() && gambler.handValueHard() < 12) {
            return gambler.handValueHard() + "/" + gambler.handValueSoft();
        } else {
            return Integer.toString(gambler.handValueHard());
        }
    }

    // EFFECTS: returns a string of the dealers hand value
    public String getHandValueDealer(Dealer dealer) {
        if (dealer.checkAceInHand() && dealer.handValueHard() < 12) {
            return dealer.handValueHard() + "/" + dealer.handValueSoft();
        } else {
            return Integer.toString(dealer.handValueHard());
        }
    }

    // MODIFIES: this
    // EFFECTS: properly renames each player section
    public void setScene() {
        int count = 0;
        for (Gambler gambler: listOfGamblers.getGamblers()) {
            if (count == 0) {
                topLeftPlayersLabel.setText("Player " + gambler.getGamblerID() + "'s Cards:");
            } else if (count == 1) {
                bottomLeftPlayersLabel.setText("Player " + gambler.getGamblerID() + "'s Cards:");
            } else if (count == 2) {
                topRightPlayersLabel.setText("Player " + gambler.getGamblerID() + "'s Cards:");
            } else if (count == 3) {
                bottomRightPlayersLabel.setText("Player " + gambler.getGamblerID() + "'s Cards:");
            }
            count++;
            whichPlayersTurnLabel.setText("Players " + listOfGamblers.getGamblers(playersTurn).getGamblerID()
                    + "'s turn");
        }
    }

    // MODIFIES: dealer, player
    // EFFECTS: starts the game of blackjack off by giving the deal 2 cards, 1 face up and another face down, then
    // gives every player 2 cards and displays the current state of the game.
    public void preGameSetup() {
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

    // MODIFIES: this, dealer
    // EFFECTS: does the dealers turn and displays the cards
    private void dealersTurn() {
        dealer.dealersTurn();
        displayCards();
    }

    // MODIFIES: this, gambler
    // EFFECTS: handles the players turn and gives them option to hit stand or double
    private void playersTurn() {
        hitButtonHandler();
        standButtonHandler();
        doubleButtonHandler();
    }

    // EFFECTS: checks if its the dealers turn
    public boolean checkIfDealerTurn() {
        for (Gambler gambler: listOfGamblers.getGamblers()) {
            if (!gambler.isStand()) {
                return false;
            }
        }
        return true;
    }

    // MODIFIES: this, listOfGambler, gambler, dealer
    // EFFECTS: displays cards, pays out players who won, sets the plays turn to 0, starts a timer that ends the game
    // in 3 seconds and displays a message that the game ended
    public void endGame() {
        displayCards();
        payout();
        playersTurn = 0;
        // create a timer with a delay of 3 seconds (3000 milliseconds)
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Round Finished!", "Finished Round",
                        JOptionPane.INFORMATION_MESSAGE, image);
                moveToMainMenu();
                timer.stop();
            }
        });
        timer.start();

    }

    // MODIFIES: gambler
    // EFFECTS: gives plays their bet * 2 back to balance depending if they won or lost
    public void payout() {
        for (Gambler gambler : listOfGamblers.getGamblers()) {
            if (gambler.handValueHard() <= 21 && dealer.handValueHard() > 21) {
                gambler.gamblerWin();
            } else if (gambler.handValueHard() <= 21 && gambler.handValueHard() > dealer.handValueHard()) {
                gambler.gamblerWin();
            } else if (gambler.handValueHard() <= 21 && gambler.handValueHard() == dealer.handValueHard()) {
                gambler.gamblerPush();
            } else {
                gambler.gamblerLoss();
            }
        }
    }



    // MODIFIES: gambler, listOfGambler
    // EFFECTS: collects bets from all gamblers and sets their bets
    public void collectBets() { //TODO MAKE IT ONLY COLLECT VALID BETS
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(xSize,ySize);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
