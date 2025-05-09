import javax.swing.*;
import java.awt.*;

public class Card extends JPanel {

    public Card(String imagePath, String text) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.BLACK);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(imageLabel);
        add(Box.createRigidArea(new Dimension(0, 8)));
        add(textLabel);

        setPreferredSize(new Dimension(160, 200));
    }
}
