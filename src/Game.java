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

    private ArrayList wordList = new ArrayList<String>();


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
