import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Resizer extends MouseAdapter {
    private static final int RESIZE_MARGIN = 10;
    private JFrame frame;
    private boolean resizing = false;
    private Point prevPt;

    public Resizer(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isInResizeArea(e)) {
            resizing = true;
            prevPt = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resizing = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (resizing) {
            int dx = e.getX() - prevPt.x;
            int dy = e.getY() - prevPt.y;
            frame.setSize(frame.getWidth() + dx, frame.getHeight() + dy);
            prevPt = e.getPoint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isInResizeArea(e)) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        } else {
            frame.setCursor(Cursor.getDefaultCursor());
        }
    }

    private boolean isInResizeArea(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int w = frame.getWidth();
        int h = frame.getHeight();
        return x >= w - RESIZE_MARGIN && y >= h - RESIZE_MARGIN;
    }
}
