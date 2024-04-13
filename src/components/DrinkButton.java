package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrinkButton extends JButton {

    private Color buttonColor = new Color(64, 81, 59); // Color: 40513B
    private Color hoverColor = new Color(92, 112, 82); // lighter color for hover effect

    public DrinkButton() {
        super();
        initialize();
    }

    public DrinkButton(String text) {
        super(text);
        initialize();
    }

    private void initialize() {
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(buttonColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (getModel().isArmed()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(buttonColor);
        }
        
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.dispose();

        super.paintComponent(g);
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint border
    }
}
