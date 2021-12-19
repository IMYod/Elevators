package GUI;

import Elevators.Elevator;
import Main.Settings;
import Floors.Floor;

import javax.swing.*;
import java.awt.*;

/*Display as following:
    Rows: Floors in the building
    Left column: Passengers who reached their floor
    Right column: Passengers that waiting to a elevator
    Intermediate columns: A series of elevators
        the floor on which it is located
        the number of passengers in the elevator
*/

public class Building extends JPanel {

    public static Building createAndShowGui(Building building) {
      JFrame frame = new JFrame("Building");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(building);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
      frame.setSize(100 * (Settings.elevators + 1),40 * (Settings.floors));
      return building;
    }

    private static final float FIELD_PTS = 15f;
    private static final int GAP = 3;
    private static final Color BG = Color.BLACK;
    //Left column
    private Cell[] leavesGrid;
    //Intermediate columns
    private Cell[][] elevatorsGrid;
    //Right column
    private Cell[] floorsGrid;

    public Building() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        mainPanel.setBackground(BG);

        JPanel leavesPanel = new JPanel(new GridLayout(Settings.floors, 1));
        leavesGrid = new Cell[Settings.floors];
        for (int row = 0; row < leavesGrid.length; row++) {
            leavesPanel.add(leavesGrid[row] = createField());
            leavesGrid[row].setBackground(Color.gray);
        }
        mainPanel.add(leavesPanel);

        JPanel elevatorsPanel = new JPanel(new GridLayout(Settings.floors, Settings.elevators));
        elevatorsGrid = new Cell[Settings.floors][Settings.elevators];
        for (int row = 0; row < elevatorsGrid.length; row++) {
            for (int col = 0; col < elevatorsGrid[row].length; col++) {
                elevatorsPanel.add(elevatorsGrid[row][col] = createField());
            }
        }
        mainPanel.add(elevatorsPanel);

        JPanel floorsPanel = new JPanel(new GridLayout(Settings.floors, 1));
        floorsGrid = new Cell[Settings.floors];
        for (int row = 0; row < floorsGrid.length; row++) {
            floorsPanel.add(floorsGrid[row] = createField());
        }
        mainPanel.add(floorsPanel);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }

    private Cell createField() {
        Cell field = new Cell();
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(field.getFont().deriveFont(Font.BOLD, FIELD_PTS));
        return field;
    }

    //Retrieving the updated values
    public void update() {
        int floorsAmount = Settings.floors;
        for (int i = 0; i < floorsAmount; ++i) {
            int personsAtFloor = Floor.getFloor(i).amountPassengers();
            floorsGrid[floorsAmount - i - 1].setNum(personsAtFloor);
        }

        for (int i = 0; i < Settings.elevators; ++i) {
            Elevator elevator = Elevator.getElevator(i);
            int personsAtFloor = elevator.amountInside();
            int currentFloor = elevator.getCurrentFloor().getId();
            for (int j = 0; j < floorsAmount; ++j)
                elevatorsGrid[floorsAmount - 1 - j][i].removeElevator();
            elevatorsGrid[floorsAmount - 1 - currentFloor][i].setElevator(personsAtFloor);
        }

        for (int i = 0; i < floorsAmount; ++i) {
            int leavingAtFloor = Elevator.getLeavingStatics().getLeavingStatics(Floor.getFloor(i));
            leavesGrid[floorsAmount - i - 1].setNum(leavingAtFloor);
        }
    }
}
