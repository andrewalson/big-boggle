import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    static ArrayList dice = new ArrayList<String[]>();

    static void defineDice(){
        dice.add(new String[]{"A", "G", "E", "M", "E", "U"});
        dice.add(new String[]{"D", "R", "L", "H", "O", "N"});
        dice.add(new String[]{"N", "A", "N", "G", "M", "E"});
        dice.add(new String[]{"R", "I", "A", "S", "A", "F"});
        dice.add(new String[]{"N", "N", "N", "D", "E", "A"});
        dice.add(new String[]{"P", "T", "I", "E", "C", "L"});
        dice.add(new String[]{"W", "T", "O", "N", "O", "U"});
        dice.add(new String[]{"T", "O", "O", "T", "O", "U"});
        dice.add(new String[]{"Y", "A", "S", "F", "R", "I"});
        dice.add(new String[]{"E", "A", "E", "A", "E", "E"});
        dice.add(new String[]{"N", "E", "S", "C", "T", "C"});
        dice.add(new String[]{"T", "M", "T", "T", "O", "E"});
        dice.add(new String[]{"G", "O", "V", "R", "R", "W"});
        dice.add(new String[]{"C", "S", "I", "T", "E", "P"});
        dice.add(new String[]{"Y", "S", "P", "F", "R", "I"});
        dice.add(new String[]{"H", "O", "H", "L", "D", "R"});
        dice.add(new String[]{"T", "I", "E", "T", "I", "I"});
        dice.add(new String[]{"Z", "B", "K", "J", "X", "C"});
        dice.add(new String[]{"N", "O", "T", "D", "D", "H"});
        dice.add(new String[]{"H", "D", "L", "H", "O", "N"});
        dice.add(new String[]{"L", "I", "C", "T", "I", "E"});
        dice.add(new String[]{"R", "A", "A", "S", "F", "A"});
        dice.add(new String[]{"W", "K", "I", "L", "Q", "P"});
        dice.add(new String[]{"E", "N", "S", "S", "U", "S"});
        dice.add(new String[]{"E", "E", "A", "E", "E", "M"});
//        dice.add(new String[]{"Th", "He", "Er", "Qu", "An", "In"});

    }

    private String[] chosenLetters = new String[25];

    private JPanel[][] board = new JPanel[5][5];
    public Board(){
        visualAttributes();

        chooseDice();

        generateGrid();
    }

    public void visualAttributes(){
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(500, 450));
        this.setBorder(BorderFactory.createLineBorder(Color.white, 5));

        GridLayout layout = new GridLayout(5, 5);
        layout.setHgap(1);
        layout.setVgap(1);
        this.setLayout(layout);
    }

    public void generateGrid(){

        //create counter starting at 0, increase by 1 in for loop
        //replace jlabel x w chosen letters at counter location
        int counter = 0;
        for (int row = 0; row < 5; row++){
            for (int col = 0; col < 5; col++){
                JPanel die = new JPanel();
                die.setLayout(new GridBagLayout());
                JLabel jLabel = new JLabel(chosenLetters[counter]);
                jLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
                die.add(jLabel);
                this.add(die);
                board[row][col] = die;
                counter++;
            }
        }
    }

    public void chooseDice(){
        ArrayList dice2 = new ArrayList<String[]>();
        for (int i = 0; i < dice.size(); i++){
            String[] newDice = new String[6];
            System.arraycopy(dice.get(i),0, newDice,0,6);
            dice2.add(newDice);
        }

        int counter = 0;

        //dice2 = <[a,b,c,d,e,f], [e,f,g,h,i,j]...>
        //while there is at least one item in dice2
        while (dice2.size() > 1) {

            //get random # to index from dice2
            int random = (int) (Math.random() * dice2.size());

            //Make new String array that holds onto one individual die
            String[] die = (String[]) dice2.remove(random);

            //Reset random # 0-6
            random = (int) (Math.random() * 6);

            chosenLetters[counter] = die[random];
            counter++;
        }
    }

    public void setBoard(){

    }


}
