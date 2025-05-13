import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {
    private final int RESIZE_MARGIN = 5; // Invisible resize margin
    private Point dragStart = null;
    private int resizeDir = Cursor.DEFAULT_CURSOR;
    private JPanel contentPanel;

    public Frame(String title) {
        super(title);
        setUndecorated(true);
        setSize(1400, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create transparent root pane
        setRootPane(new JRootPane());
        getRootPane().setDoubleBuffered(true);

        // Create main content panel (this will be the only visible panel)
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);
        setContentPane(contentPanel); // Set as content pane directly

        // Add invisible mouse listeners for resizing
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = e.getPoint();
                resizeDir = getResizeDirection(e.getPoint());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(getResizeDirection(e.getPoint())));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (resizeDir == Cursor.DEFAULT_CURSOR || dragStart == null)
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
            }
        });
    }

    private int getResizeDirection(Point p) {
        int x = p.x;
        int y = p.y;
        int w = getWidth();
        int h = getHeight();

        if (x <= RESIZE_MARGIN && y <= RESIZE_MARGIN)
            return Cursor.NW_RESIZE_CURSOR;
        if (x >= w - RESIZE_MARGIN && y <= RESIZE_MARGIN)
            return Cursor.NE_RESIZE_CURSOR;
        if (x <= RESIZE_MARGIN && y >= h - RESIZE_MARGIN)
            return Cursor.SW_RESIZE_CURSOR;
        if (x >= w - RESIZE_MARGIN && y >= h - RESIZE_MARGIN)
            return Cursor.SE_RESIZE_CURSOR;
        if (x <= RESIZE_MARGIN)
            return Cursor.W_RESIZE_CURSOR;
        if (x >= w - RESIZE_MARGIN)
            return Cursor.E_RESIZE_CURSOR;
        if (y <= RESIZE_MARGIN)
            return Cursor.N_RESIZE_CURSOR;
        if (y >= h - RESIZE_MARGIN)
            return Cursor.S_RESIZE_CURSOR;

        return Cursor.DEFAULT_CURSOR;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}