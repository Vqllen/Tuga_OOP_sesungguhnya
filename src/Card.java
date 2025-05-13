import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Card extends JPanel {
    private String imagePath;
    private String text;

    public Card(String imagePath, String text) {
        this.imagePath = imagePath;
        this.text = text;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        // Add mouse listener to handle clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPopup();
            }
        });
    }

    private void showPopup() {
        // Get the root pane (top-level container)
        JRootPane rootPane = SwingUtilities.getRootPane(this);
        if (rootPane == null)
            return;

        // Create a dark overlay that ignores clicks except on the popup
        JPanel overlay = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create semi-transparent darkening effect
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlay.setOpaque(false);
        overlay.setBounds(0, 0, rootPane.getWidth(), rootPane.getHeight());

        // Create the popup content
        JPanel popupPanel = new JPanel(new BorderLayout());
        popupPanel.setBackground(new Color(60, 60, 60));
        popupPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        popupPanel.setPreferredSize(new Dimension(400, 500));

        // Position the popup below the navbar (adjust 70px to match your navbar height)
        JPanel popupContainer = new JPanel(new GridBagLayout());
        popupContainer.setOpaque(false);
        popupContainer.add(popupPanel);
        overlay.add(popupContainer, BorderLayout.CENTER);
        overlay.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0)); // Top margin for navbar

        // Add the image (larger version)
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel popupImage = new JLabel(new ImageIcon(scaledImage));
        popupImage.setHorizontalAlignment(JLabel.CENTER);

        // Add the text
        JLabel popupText = new JLabel(text, JLabel.CENTER);
        popupText.setForeground(Color.WHITE);
        popupText.setFont(new Font("Arial", Font.BOLD, 24));

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            rootPane.getLayeredPane().remove(overlay);
            rootPane.repaint();
        });

        popupPanel.add(popupImage, BorderLayout.CENTER);
        popupPanel.add(popupText, BorderLayout.NORTH);
        popupPanel.add(closeButton, BorderLayout.SOUTH);

        // Make overlay ignore clicks except on the popup panel
        overlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Only close if clicking outside the popup panel
                if (!SwingUtilities.isDescendingFrom(e.getComponent(), popupPanel)) {
                    rootPane.getLayeredPane().remove(overlay);
                    rootPane.repaint();
                }
            }
        });

        // Add the overlay to the root pane's layered pane
        rootPane.getLayeredPane().add(overlay, JLayeredPane.MODAL_LAYER);
        overlay.setVisible(true);
    }
}