import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
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

