import model.World;
import view.Frame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String width = JOptionPane.showInputDialog("Podaj szerokość dla świata: ");
        String height = JOptionPane.showInputDialog("Podaj wysokość dla świata: ");

        int widthInt = Integer.parseInt(width);
        int heightInt = Integer.parseInt(height);

        World world = new World(widthInt, heightInt);

        Frame frame = new Frame(world);
        frame.setVisible(true);
    }
}