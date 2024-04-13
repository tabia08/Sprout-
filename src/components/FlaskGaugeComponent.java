package components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import java.io.FileWriter;
import java.io.IOException;

public class FlaskGaugeComponent extends JPanel {
    private int level;
    private Color flaskColor;
    private int targetLevel; // Add targetLevel field

    public FlaskGaugeComponent() {
        this.level = 0;
        this.flaskColor = Color.WHITE;
        this.targetLevel = 9000; // Initialize targetLevel
        setOpaque(false);
    }
    
    private void saveTargetLevel() {
        try {
            FileWriter writer = new FileWriter("targetLevel.txt");
            writer.write(Integer.toString(targetLevel));
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: Unable to save target level");
        }
    }
    
    
    
    public void setTargetLevel(int targetLevel) {
    this.targetLevel = targetLevel;
    saveTargetLevel(); // Save the target level to file
    repaint(); // Trigger repaint to update the gauge with the new target level
    }
    
    public int getTargetLevel() {
        return targetLevel;
    }
    
    public void setLevel(int currentLevel) {
        if (currentLevel > targetLevel) {
            currentLevel = targetLevel;
        }
        int scaledLevel = (int) ((double) currentLevel / targetLevel * 100); // Scale the level based on targetLevel
        this.level = scaledLevel;
        repaint(); // Trigger repaint to update the gauge with the new level
    }

    public void setFluidColor(Color fluidColor) {
        repaint();
    }

    public void setFlaskColor(Color flaskColor) {
        this.flaskColor = flaskColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2d = (Graphics2D) g;

        int outlineWidth = width / 2;
        int outlineHeight = height * 3 / 5;
        int outlineX = (width - outlineWidth) / 2;
        int outlineY = (height - outlineHeight) / 2;

        int shadowOffset = 8;
        g2d.setColor(new Color(0, 0, 0, 50));
        RoundRectangle2D shadowFlaskOutline = new RoundRectangle2D.Double(outlineX + shadowOffset, outlineY + shadowOffset, outlineWidth, outlineHeight, 25, 25);
        g2d.fill(shadowFlaskOutline);

        RoundRectangle2D flaskOutline = new RoundRectangle2D.Double(outlineX, outlineY, outlineWidth, outlineHeight, 25, 25);
        g2d.setColor(flaskColor);
        g2d.fill(flaskOutline);

        int fluidHeight = outlineHeight * level / 100;

        Color startColor = new Color(0x9DC08B);
        Color endColor = new Color(0x40513B);

        GradientPaint gradient = new GradientPaint(0, outlineY + outlineHeight - fluidHeight, startColor, 0, outlineY + outlineHeight, endColor);

        g2d.setPaint(gradient);

        RoundRectangle2D fluid = new RoundRectangle2D.Double(outlineX, outlineY + outlineHeight - fluidHeight, outlineWidth, fluidHeight, 25, 25);
        g2d.fill(fluid);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        repaint(); // Ensure continuous repainting
    }
}
