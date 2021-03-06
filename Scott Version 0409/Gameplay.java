//package tilePuzzle;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

public class Gameplay implements ActionListener{
    private int size;
    private int moves;
    
    public void increaseMoves() {
        moves += 1;
    }
    
    public int getMoves() {
        return moves;
    }
    
    public void setSize(int newSize) {
        size = newSize;
    }
    public int getSize() {
        return size;
    }
    public ArrayList list;
	JFrame frame;
	JPanel gridPane,movesPane;
	
	public Gameplay(int size) {
		setSize(size);
	}
    public JPanel makeGrid(int size, boolean pictures) {
        boolean solvable = false;
        while (!solvable) {
            JButton[] tile = new JButton[size*size];
            gridPane = new JPanel(new GridLayout(size,size));
            list = new ArrayList();
            
            for (int x = 0; x < (size*size); x++) {
                if(x < (size*size)-1){
                    if (pictures == false) {
                        tile[x] = new JButton(""+(x+1));
                    }
                    else {
                        tile[x] = new JButton();
                        String filePath = (x + ".png");
                        tile[x].setIcon(new ImageIcon(filePath));
                    }
                }
                else {
                    if (pictures == false) {
                        tile[x] = new JButton("");
                    }
                    else {
                        tile[x] = new JButton();
                        String filePath = ("null.png");
                        tile[x].setIcon(new ImageIcon(filePath));
                    }
                }
    			tile[x].setFont(new Font("ARIAL", Font.BOLD, 70));
    			tile[x].setForeground(Color.ORANGE);
    			tile[x].setBackground(Color.BLACK);
    			tile[x].setFocusPainted(false);
                list.add(tile[x]);
            }
            Collections.shuffle(list);
            solvable = checkSolvability(list, size);
            if (solvable) {
                for(int x = 0; x < list.size(); x++) {
                    gridPane.add((JButton)list.get(x));
                    if (pictures == false) {
                        ((JButton)list.get(x)).setActionCommand("" + x);
                        ((JButton)list.get(x)).addActionListener(this);
                    }
                    else {
                        ((JButton)list.get(x)).setActionCommand("999" + x);
                        ((JButton)list.get(x)).addActionListener(this);
                    }
                }
            }
        }
        return gridPane;
    }
    
    public JPanel infoBar() {
        JPanel topPane = new JPanel(new FlowLayout());
        JButton pauseButton = new JButton("Pause");
        pauseButton.setActionCommand("10000");
        pauseButton.addActionListener(this);
        topPane.add(pauseButton);
        return topPane;
    }    
    
    public boolean checkSolvability(ArrayList gridList, int gridSize) {
        boolean solvable = false;
        int length = gridList.size();
        int numInversions = 0;
        for (int itemsInList = 0; itemsInList < length; itemsInList++) {
            for (int restOfList = itemsInList; restOfList < length; restOfList++) {
                try {
                    if (Integer.parseInt(((JButton)gridList.get(itemsInList)).getText())-Integer.parseInt(((JButton)gridList.get(restOfList)).getText()) > 0) {
                        numInversions ++;
                    }
                }
                catch (NumberFormatException e) {
                }
            }
        }
        if ((gridSize%2 != 0) && (numInversions%2 == 0)) {
            solvable = true;
        }
        else if ((gridSize%2 == 0) && (numInversions%2 == 0)) {
            solvable = true;
        }
        return solvable;
    }
    public boolean checkGame(ArrayList list) {
        boolean won = false;
        int counter = 1;
        for (int i = 0; i < size*size; i++) {
            if (counter == size*size) {
                won = true;
            }
            try {
                if (Integer.parseInt(((JButton) list.get(i)).getText()) == counter) {
                    counter++;
                }
            }
            catch (NumberFormatException e) {
            }
        }
        return won;
    }
    
    public boolean checkGamePicture(ArrayList list) {
        boolean won = false;
        int counter = 1;
        for (int i = 0; i < size*size; i++) {
            if (counter == size*size) {
                won = true;
            }
            try {
                if ((((JButton) list.get(i)).getIcon()).toString().equals(i + ".png")) {
                    counter++;
                }
            }
            catch (NumberFormatException e) {
            }
        }
        return won;
    }
    
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int commandInt = Integer.parseInt(command);
        if (e.getActionCommand().equals("0")) { //top left corner
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton rightButton = (JButton) list.get(1);
                JButton bottomButton = (JButton) list.get(3);
                if (rightButton.getText().equals("")) {
                    rightButton.setText(button.getText());
                    button.setText("");
                }
                else if (bottomButton.getText().equals("")) {
                    bottomButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("" + (size-1))) { //top right corner
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton bottomButton = (JButton) list.get(5);
                JButton leftButton = (JButton) list.get(1);
                if (bottomButton.getText().equals("")) {
                    bottomButton.setText(button.getText());
                    button.setText("");
                }
                else if (leftButton.getText().equals("")) {
                    leftButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("" + (size*(size-1)))) { //bottom left corner
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton rightButton = (JButton) list.get(7);
                JButton topButton = (JButton) list.get(3);
                if (rightButton.getText().equals("")) {
                    rightButton.setText(button.getText());
                    button.setText("");
                }
                else if (topButton.getText().equals("")) {
                    topButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("" + ((size*size)-1))) { //bottom right corner
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton topButton = (JButton) list.get(5);
                JButton leftButton = (JButton) list.get(7);
                if (topButton.getText().equals("")) {
                    topButton.setText(button.getText());
                    button.setText("");
                }
                else if (leftButton.getText().equals("")) {
                    leftButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("1")) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton rightButton = (JButton) list.get(2);
                JButton bottomButton = (JButton) list.get(4);
                JButton leftButton = (JButton) list.get(0);
                if (rightButton.getText().equals("")) {
                    rightButton.setText(button.getText());
                    button.setText("");
                }
                else if (bottomButton.getText().equals("")) {
                    bottomButton.setText(button.getText());
                    button.setText("");
                }
                else if (leftButton.getText().equals("")) {
                    leftButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("2")) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton bottomButton = (JButton) list.get(5);
                JButton leftButton = (JButton) list.get(1);
                if (bottomButton.getText().equals("")) {
                    bottomButton.setText(button.getText());
                    button.setText("");
                }
                else if (leftButton.getText().equals("")) {
                    leftButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("3")) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton rightButton = (JButton) list.get(4);
                JButton bottomButton = (JButton) list.get(6);
                JButton topButton = (JButton) list.get(0);
                if (rightButton.getText().equals("")) {
                    rightButton.setText(button.getText());
                    button.setText("");
                }
                else if (bottomButton.getText().equals("")) {
                    bottomButton.setText(button.getText());
                    button.setText("");
                }
                else if (topButton.getText().equals("")) {
                    topButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("4")) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton rightButton = (JButton) list.get(5);
                JButton bottomButton = (JButton) list.get(7);
                JButton topButton = (JButton) list.get(1);
                JButton leftButton = (JButton) list.get(3);
                if (rightButton.getText().equals("")) {
                    rightButton.setText(button.getText());
                    button.setText("");
                }
                else if (bottomButton.getText().equals("")) {
                    bottomButton.setText(button.getText());
                    button.setText("");
                }
                else if (topButton.getText().equals("")) {
                    topButton.setText(button.getText());
                    button.setText("");
                }
                else if (leftButton.getText().equals("")) {
                    leftButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("5")) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton leftButton = (JButton) list.get(4);
                JButton bottomButton = (JButton) list.get(8);
                JButton topButton = (JButton) list.get(2);
                if (leftButton.getText().equals("")) {
                    leftButton.setText(button.getText());
                    button.setText("");
                }
                else if (bottomButton.getText().equals("")) {
                    bottomButton.setText(button.getText());
                    button.setText("");
                }
                else if (topButton.getText().equals("")) {
                    topButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("6")) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton rightButton = (JButton) list.get(7);
                JButton topButton = (JButton) list.get(3);
                if (rightButton.getText().equals("")) {
                    rightButton.setText(button.getText());
                    button.setText("");
                }
                else if (topButton.getText().equals("")) {
                    topButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("7")) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton rightButton = (JButton) list.get(8);
                JButton topButton = (JButton) list.get(4);
                JButton leftButton = (JButton) list.get(6);
                if (rightButton.getText().equals("")) {
                    rightButton.setText(button.getText());
                    button.setText("");
                }
                else if (topButton.getText().equals("")) {
                    topButton.setText(button.getText());
                    button.setText("");
                }
                else if (leftButton.getText().equals("")) {
                    leftButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("8")) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                System.out.println("you hit the button and its empty");
            }
            else {
                JButton topButton = (JButton) list.get(5);
                JButton leftButton = (JButton) list.get(7);
                if (topButton.getText().equals("")) {
                    topButton.setText(button.getText());
                    button.setText("");
                }
                else if (leftButton.getText().equals("")) {
                    leftButton.setText(button.getText());
                    button.setText("");
                }
            }
            boolean won = checkGame(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("10000")) {
            PauseMenu pause = new PauseMenu();
        }
        else if (e.getActionCommand().equals("9990")) {
            JButton button = (JButton) e.getSource();
            JButton rightButton = (JButton) list.get(1);
            JButton bottomButton = (JButton) list.get(3);
            String rightIcon = (rightButton.getIcon()).toString();
            String bottomIcon = (bottomButton.getIcon()).toString();
            if (rightIcon.equals("null.png")) {
                rightButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (bottomIcon.equals("null.png")) {
                bottomButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            boolean won = checkGamePicture(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("9991")) {
            JButton button = (JButton) e.getSource();
            Icon icon = button.getIcon();
            JButton rightButton = (JButton) list.get(2);
            JButton bottomButton = (JButton) list.get(4);
            JButton leftButton = (JButton) list.get(0);
            String rightIcon = (rightButton.getIcon()).toString();
            String leftIcon = (leftButton.getIcon()).toString();
            String bottomIcon = (bottomButton.getIcon()).toString();
            if (rightIcon.equals("null.png")) {
                rightButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (bottomIcon.equals("null.png")) {
                bottomButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (leftIcon.equals("null.png")) {
                leftButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            boolean won = checkGamePicture(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("9992")) {
            JButton button = (JButton) e.getSource();
            Icon icon = button.getIcon();
            JButton bottomButton = (JButton) list.get(5);
            JButton leftButton = (JButton) list.get(1);
            String leftIcon = (leftButton.getIcon()).toString();
            String bottomIcon = (bottomButton.getIcon()).toString();
            if (bottomIcon.equals("null.png")) {
                bottomButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (leftIcon.equals("null.png")) {
                leftButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            boolean won = checkGamePicture(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("9993")) {
            JButton button = (JButton) e.getSource();
            Icon icon = button.getIcon();
            JButton rightButton = (JButton) list.get(4);
            JButton bottomButton = (JButton) list.get(6);
            JButton topButton = (JButton) list.get(0);
            String rightIcon = (rightButton.getIcon()).toString();
            String topIcon = (topButton.getIcon()).toString();
            String bottomIcon = (bottomButton.getIcon()).toString();
            if (rightIcon.equals("null.png")) {
                rightButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (bottomIcon.equals("null.png")) {
                bottomButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (topIcon.equals("null.png")) {
                topButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            boolean won = checkGamePicture(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("9994")) {
            JButton button = (JButton) e.getSource();
            Icon icon = button.getIcon();
            JButton rightButton = (JButton) list.get(5);
            JButton bottomButton = (JButton) list.get(7);
            JButton topButton = (JButton) list.get(1);
            JButton leftButton = (JButton) list.get(3);
            String rightIcon = (rightButton.getIcon()).toString();
            String leftIcon = (leftButton.getIcon()).toString();
            String topIcon = (topButton.getIcon()).toString();
            String bottomIcon = (bottomButton.getIcon()).toString();
            if (rightIcon.equals("null.png")) {
                rightButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (bottomIcon.equals("null.png")) {
                bottomButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (topIcon.equals("null.png")) {
                topButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (leftIcon.equals("null.png")) {
                leftButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            boolean won = checkGamePicture(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("9995")) {
            JButton button = (JButton) e.getSource();
            Icon icon = button.getIcon();
            JButton leftButton = (JButton) list.get(4);
            JButton bottomButton = (JButton) list.get(8);
            JButton topButton = (JButton) list.get(2);
            String leftIcon = (leftButton.getIcon()).toString();
            String topIcon = (topButton.getIcon()).toString();
            String bottomIcon = (bottomButton.getIcon()).toString();
            if (leftIcon.equals("null.png")) {
                leftButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (bottomIcon.equals("null.png")) {
                bottomButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (topIcon.equals("null.png")) {
                topButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            boolean won = checkGamePicture(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("9996")) {
            JButton button = (JButton) e.getSource();
            JButton rightButton = (JButton) list.get(7);
            JButton topButton = (JButton) list.get(3);
            String rightIcon = (rightButton.getIcon()).toString();
            String topIcon = (topButton.getIcon()).toString();
            if (rightIcon.equals("null.png")) {
                rightButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (topIcon.equals("null.png")) {
                topButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            boolean won = checkGamePicture(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("9997")) {
            JButton button = (JButton) e.getSource();
            Icon icon = button.getIcon();
            JButton rightButton = (JButton) list.get(8);
            JButton topButton = (JButton) list.get(4);
            JButton leftButton = (JButton) list.get(6);
            String rightIcon = (rightButton.getIcon()).toString();
            String leftIcon = (leftButton.getIcon()).toString();
            String topIcon = (topButton.getIcon()).toString();
            if (rightIcon.equals("null.png")) {
                rightButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (topIcon.equals("null.png")) {
                topButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (leftIcon.equals("null.png")) {
                leftButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            boolean won = checkGamePicture(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
        else if (e.getActionCommand().equals("9998")) {
            JButton button = (JButton) e.getSource();
            JButton leftButton = (JButton) list.get(7);
            JButton topButton = (JButton) list.get(5);
            String leftIcon = (leftButton.getIcon()).toString();
            String topIcon = (topButton.getIcon()).toString();
            if (leftIcon.equals("null.png")) {
                leftButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            else if (topIcon.equals("null.png")) {
                topButton.setIcon(new ImageIcon(button.getIcon().toString()));
                button.setIcon(new ImageIcon("null.png"));
            }
            boolean won = checkGamePicture(list);
            if (won == true) {
                System.out.println("YOU WON");
            }
        }
    }
}