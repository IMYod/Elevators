package GUI;

import javax.swing.*;
import java.awt.*;

//Cell in building GUI
public class Cell extends JTextField {
    private int num;

    public Cell() {
        setEditable(false);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        this.setText(toString());
    }

    @Override
    public String toString() {
        if (num == 0) return "";
        return Integer.toString(getNum());
    }

    public void setElevator(int _num) {
        setBackground(Color.ORANGE);
        setNum(_num);
    }
    public void removeElevator() {
        setBackground(Color.WHITE);
        setNum(0);
    }


}
