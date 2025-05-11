import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("Component.focusWidth", 0);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        new Form().setVisible(true);
    }
}

