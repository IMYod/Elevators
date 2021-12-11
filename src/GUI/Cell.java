package GUI;

import javax.swing.*;
import java.util.Random;

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
}
