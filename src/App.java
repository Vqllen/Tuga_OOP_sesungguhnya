import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {

        Frame frame = new Frame("Honkai Star Rail");

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel topPanel = new JPanel();

        leftPanel.setBackground(new Color(45, 45, 45));
        rightPanel.setBackground(new Color(63, 64, 66));
        topPanel.setBackground(new Color(35, 35, 35));

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new GridLayout(0, 4, 10, 10));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        rightPanel.add(new Card("lib/edith.png", "Edith"));
        rightPanel.add(new Card("lib/eudora.png", "Eudora"));
        rightPanel.add(new Card("lib/kadita.png", "Kadita"));
        rightPanel.add(new Card("lib/luoyi.png", "Luo Yi"));
        rightPanel.add(new Card("lib/miya.png", "Miya"));
        rightPanel.add(new Card("lib/edith.png", "Edith"));
        rightPanel.add(new Card("lib/eudora.png", "Eudora"));
        rightPanel.add(new Card("lib/kadita.png", "Kadita"));
        rightPanel.add(new Card("lib/luoyi.png", "Luo Yi"));
        rightPanel.add(new Card("lib/miya.png", "Miya"));
        rightPanel.add(new Card("lib/edith.png", "Edith"));
        rightPanel.add(new Card("lib/eudora.png", "Eudora"));
        rightPanel.add(new Card("lib/kadita.png", "Kadita"));
        rightPanel.add(new Card("lib/luoyi.png", "Luo Yi"));
        rightPanel.add(new Card("lib/miya.png", "Miya"));
        rightPanel.add(new Card("lib/edith.png", "Edith"));
        rightPanel.add(new Card("lib/eudora.png", "Eudora"));
        rightPanel.add(new Card("lib/kadita.png", "Kadita"));
        rightPanel.add(new Card("lib/luoyi.png", "Luo Yi"));

        rightPanel.setPreferredSize(new Dimension(1000, 1000));

        JScrollPane scrollPane = new JScrollPane(rightPanel);
        scrollPane.setBorder(null);

        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, scrollPane);
        splitpane.setDividerLocation(190);
        splitpane.setDividerSize(4);
        splitpane.setBorder(null);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel title = new JLabel("Mobile Legends: Bang Bang Characters List");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.setPreferredSize(new Dimension(frame.getWidth(), 70));
        topPanel.add(title);

        JPanel rightButtonsPanel = new JPanel();
        rightButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightButtonsPanel.setOpaque(false);

        Button button1 = new Button("Join Discord");
        Button button2 = new Button("Buy us a Ko-Fi");
        button2.addActionListener(e -> System.exit(0));

        button1.setPreferredSize(new Dimension(130, 40));
        button1.setBackground(new Color(65, 89, 199));

        button2.setPreferredSize(new Dimension(150, 45));
        button2.setBackground(new Color(207, 70, 96));

        rightButtonsPanel.add(button1);
        rightButtonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        rightButtonsPanel.add(button2);

        topPanel.add(rightButtonsPanel);

        // Access the contentPanel from Frame to add the components to the actual frame
        JPanel contentPanel = frame.getContentPanel();
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(splitpane, BorderLayout.CENTER);

        leftPanel.add(new Button("Home"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(new Button("Characters"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(new Button("Tier Lists"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(new Button("Pro Builds"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(new Button("Emblem"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(new Button("Skins"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(new Button("History"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(new Button("Favourites"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        frame.setUndecorated(true);
        setvisible(frame);
    }

    public static void setvisible(Window obj) {
        obj.setVisible(true);
    }
}
