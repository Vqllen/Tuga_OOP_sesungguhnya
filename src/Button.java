import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String isi) {
        super(isi);

        Dimension buttonSize = new Dimension(170, 50);
        this.setPreferredSize(buttonSize);
        this.setMaximumSize(buttonSize);

        this.setBackground(new Color(60, 60, 60));
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setContentAreaFilled(true);

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
