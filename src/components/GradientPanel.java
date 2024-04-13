package components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Cast Graphics to Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Define the gradient start and end colors
        Color color1 = new Color(0xEDF1D6); // Lighter color
        Color color2 = new Color(0x9DC08B); // Darker color

        // Create a gradient paint
        GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

        // Set the paint to draw the gradient
        g2d.setPaint(gradient);

        // Draw the gradient rectangle covering the entire panel
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gradient Background Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Create a content panel with null layout
            JPanel contentPane = new JPanel(null);
            contentPane.setBackground(Color.WHITE); // Set background color if needed

            // Add the GradientPanel to the content panel
            GradientPanel gradientPanel = new GradientPanel();
            gradientPanel.setBounds(0, 0, 400, 300);
            contentPane.add(gradientPanel);

            // Set the content pane of the frame
            frame.setContentPane(contentPane);
            
            frame.setVisible(true);
        });
    }
}
