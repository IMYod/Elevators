package GUI;

import Main.Settings;

import javax.swing.*;
import java.awt.*;

public class Building extends JPanel {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Building::createAndShowGui);
    }

    private static void createAndShowGui() {
      Building building = new Building();
      JFrame frame = new JFrame("Building");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(building);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
      frame.setSize(100 * (Settings.elevatorsAmount + 1),40 * (Settings.floors));
    }


    private static final float FIELD_PTS = 32f;
    private static final int GAP = 3;
    private static final Color BG = Color.BLACK;
    private static final Color SOLVED_BG = Color.LIGHT_GRAY;
    public static final int TIMER_DELAY = 2 * 1000;
    private Cell[][] elevatorsGrid;
    private Cell[] floorsGrid;

    public Building() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        mainPanel.setBackground(BG);

        JPanel elevatorsPanel = new JPanel(new GridLayout(Settings.floors, Settings.elevatorsAmount));
        elevatorsGrid = new Cell[Settings.floors][Settings.elevatorsAmount];
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

}