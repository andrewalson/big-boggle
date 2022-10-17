import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class Game {

    private Board board;
    private JFrame window;
    private JPanel container;
    private JPanel boardPanel;
    private JPanel inputBox;

    private ArrayList<String> wordList = new ArrayList<String>();


    public Game(){
        Board.defineDice();
        initializeWindow();
        initializeContainer();
        setBoardPanel();
        generateBoard();
        generateInputBox();
        window.setVisible(true);
    }

    private void initializeWindow(){
        window = new JFrame();
        window.setSize(1400, 700);
        window.setTitle("Biggle");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void generateBoard(){
        board = new Board();
        boardPanel.add(board);

    }

    private void generateInputBox(){
        inputBox = new JPanel();
        inputBox.setPreferredSize(new Dimension(380, 550));
        inputBox.setBackground(Color.blue);

        JPanel wordListPanel = new JPanel();
        wordListPanel.setPreferredSize(new Dimension(360, 490));
        wordListPanel.setLayout(new BoxLayout(wordListPanel, BoxLayout.Y_AXIS));

        JTextField wordEntryField = new JTextField();

        JButton submitButton = new JButton("Submit Words!");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] lettersOnBoard = board.getLettersOnBoard();

                //for each word in word list
                for (String word : wordList){
                    //create boolean variable for word found no/yes
                    boolean wordFound = false;
                    int currentIndexInWord = 0;
                    //to keep track of which letter we are on
                    String currentLetter = String.valueOf(word.charAt(currentIndexInWord));
                    //loop over each board slot left to right & top to bottom
                    for (int row = 0; row < 5; row++){
                        for (int col = 0; col < 5; col++){
                            //if the word is still not found & letter currently searching at
                            if (!wordFound && lettersOnBoard[row][col].equals(currentLetter)){
                                boolean[][] used = new boolean[5][5];
                                used[row][col] = true;

                                int currentRow = row;
                                int currentCol = col;

                                boolean letterFound = true;
                                currentIndexInWord++;

                                while (currentIndexInWord < word.length() && letterFound){
                                    letterFound = false;
                                    currentLetter = String.valueOf(word.charAt(currentIndexInWord));
                                    //check letter above
                                    if (currentRow != 0 && !used[currentRow - 1][currentCol] && !letterFound){
                                        if (lettersOnBoard[currentRow-1][currentCol].equals(currentLetter)){
                                            currentRow--;
                                            used[currentRow][currentCol] = true;
                                            letterFound = true;
                                        }
                                    }
                                    //check letter up and right
                                    if (currentRow != 0 && currentCol != 4 && !used[currentRow - 1][currentCol + 1]){
                                        if (lettersOnBoard[currentRow-1][currentCol+1].equals(currentLetter)){

                                        }
                                        currentRow--;
                                        currentCol++;
                                        used[currentRow][currentCol] = true;
                                        letterFound = true;
                                    }
                                    //check letter to the right
                                    if (currentCol != 4 && !used[currentRow][currentCol] && !letterFound){
                                        if (lettersOnBoard[currentRow][currentCol+1].equals(currentLetter)){
                                            currentCol++;
                                            used[currentRow][currentCol] = true;
                                            letterFound = true;
                                        }
                                    }
                                    //check bottom right
                                    if (currentRow != 4 && currentRow != 4 && !used[currentRow][currentCol] && !letterFound){
                                        if (lettersOnBoard[currentRow+1][currentCol+1].equals(currentLetter)){
                                            currentRow++;
                                            currentCol++;
                                            used[currentRow][currentCol] = true;
                                            letterFound = true;
                                        }
                                    }
                                    //check bottom middle
                                    if (currentRow != 4 && !used[currentRow][currentCol] && !letterFound){
                                        if (lettersOnBoard[currentRow+1][currentCol].equals(currentLetter)){
                                            currentRow++;
                                            used[currentRow][currentCol] = true;
                                            letterFound = true;
                                        }
                                    }
                                    //bottom left
                                    if (currentRow != 4 && currentCol != 0 && !used[currentRow][currentCol] && !letterFound){
                                        if (lettersOnBoard[currentRow+1][currentCol-1].equals(currentLetter)){
                                            currentRow++;
                                            currentCol--;
                                            used[currentRow][currentCol] = true;
                                            letterFound = true;
                                        }
                                    }
                                    //middle left
                                    if (currentCol != 0 && !used[currentRow][currentCol] && !letterFound){
                                        if (lettersOnBoard[currentRow][currentCol-1].equals(currentLetter)){
                                            currentCol--;
                                            used[currentRow][currentCol] = true;
                                            letterFound = true;
                                        }
                                    }
                                    //top left
                                    if (currentRow != 0 && currentCol != 0 && !used[currentRow][currentCol] && !letterFound){
                                        if (lettersOnBoard[currentRow-1][currentCol-1].equals(currentLetter)){
                                            currentRow--;
                                            currentCol--;
                                            used[currentRow][currentCol] = true;
                                            letterFound = true;
                                        }
                                    }

                                    if (letterFound){
                                        currentIndexInWord++;
                                    } else {
                                        break;
                                    }
                                }
                                if (letterFound){
                                    wordFound = true;
                                }

                            }
                        }

                    }

                    if (!wordFound){
                        Component[] components = wordListPanel.getComponents();
                        for (Component component : components){


                        }
                    }
                }
            }
        });

        wordEntryField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String word = wordEntryField.getText();
                word = word.toUpperCase();

                wordList.add(word);
                JLabel wordInList = new JLabel(word);
                wordInList.setFont(new Font("Arial", Font.BOLD, 20));
                wordListPanel.add(wordInList);

                window.validate();
                wordEntryField.setText("");

            }
        });

        wordEntryField.setPreferredSize(new Dimension(360, 20));

        inputBox.add(wordListPanel);
        inputBox.add(wordEntryField);
        container.add(inputBox);
        inputBox.add(submitButton);
    }

    private void initializeContainer(){
        container = new JPanel();
        container.setBackground(Color.orange);
        window.add(container);
    }

    private void setBoardPanel(){
        boardPanel = new JPanel();
        boardPanel.setBackground(Color.green);
        boardPanel.setPreferredSize(new Dimension(600, 550));
        container.add(boardPanel);
    }

}
