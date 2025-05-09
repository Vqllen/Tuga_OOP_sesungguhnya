import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {
    private final int RESIZE_MARGIN = 2;
    private Point dragStart = null;
    private int resizeDir = Cursor.DEFAULT_CURSOR;

    public Frame(String title) {
        super(title);
        setUndecorated(true);
        setSize(1400, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel outerPanel = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        outerPanel.setBackground(new Color(0, 0, 0, 0));
        outerPanel.setOpaque(false);
        setContentPane(outerPanel);

        // Actual visible content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBounds(RESIZE_MARGIN, RESIZE_MARGIN,
                getWidth() - RESIZE_MARGIN * 2, getHeight() - RESIZE_MARGIN * 2);
        contentPanel.setBackground(Color.DARK_GRAY);
        outerPanel.add(contentPanel);

        // Add resizing behavior
        MouseAdapter resizeListener = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int w = getWidth();
                int h = getHeight();

                if (x <= RESIZE_MARGIN && y <= RESIZE_MARGIN)
                    setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                else if (x >= w - RESIZE_MARGIN && y <= RESIZE_MARGIN)
                    setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                else if (x <= RESIZE_MARGIN && y >= h - RESIZE_MARGIN)
                    setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                else if (x >= w - RESIZE_MARGIN && y >= h - RESIZE_MARGIN)
                    setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                else if (x <= RESIZE_MARGIN)
                    setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                else if (x >= w - RESIZE_MARGIN)
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                else if (y <= RESIZE_MARGIN)
                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                else if (y >= h - RESIZE_MARGIN)
                    setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                else
                    setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = e.getPoint();
                resizeDir = getCursor().getType();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (resizeDir == Cursor.DEFAULT_CURSOR)
                    return;

                Point current = e.getLocationOnScreen();
                Point start = getLocationOnScreen();
                int dx = current.x - (start.x + dragStart.x);
                int dy = current.y - (start.y + dragStart.y);

                Rectangle bounds = getBounds();

                switch (resizeDir) {
                    case Cursor.NW_RESIZE_CURSOR:
                        bounds.x += dx;
                        bounds.y += dy;
                        bounds.width -= dx;
                        bounds.height -= dy;
                        break;
                    case Cursor.NE_RESIZE_CURSOR:
                        bounds.y += dy;
                        bounds.width += dx;
                        bounds.height -= dy;
                        break;
                    case Cursor.SW_RESIZE_CURSOR:
                        bounds.x += dx;
                        bounds.width -= dx;
                        bounds.height += dy;
                        break;
                    case Cursor.SE_RESIZE_CURSOR:
                        bounds.width += dx;
                        bounds.height += dy;
                        break;
                    case Cursor.W_RESIZE_CURSOR:
                        bounds.x += dx;
                        bounds.width -= dx;
                        break;
                    case Cursor.E_RESIZE_CURSOR:
                        bounds.width += dx;
                        break;
                    case Cursor.N_RESIZE_CURSOR:
                        bounds.y += dy;
                        bounds.height -= dy;
                        break;
                    case Cursor.S_RESIZE_CURSOR:
                        bounds.height += dy;
                        break;
                }

                if (bounds.width < 800)
                    bounds.width = 800;
                if (bounds.height < 600)
                    bounds.height = 600;

                setBounds(bounds);
                dragStart = e.getPoint();

                // Update inner content panel size
                contentPanel.setBounds(RESIZE_MARGIN, RESIZE_MARGIN,
                        getWidth() - RESIZE_MARGIN * 2, getHeight() - RESIZE_MARGIN * 2);
                contentPanel.revalidate();
            }
        };

        outerPanel.addMouseListener(resizeListener);
        outerPanel.addMouseMotionListener(resizeListener);

        // Expose inner content panel to App.java
        setLayout(null);
        this.contentPanel = contentPanel;
    }

    private JPanel contentPanel;

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
