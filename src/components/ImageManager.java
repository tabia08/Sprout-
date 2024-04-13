package components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageManager {
    private JLabel imageLabel;
    private ImageIcon[] images;

    public ImageManager(JLabel imageLabel, ImageIcon[] images) {
        this.imageLabel = imageLabel;
        this.images = images;
    }

    public void updateImage(int currentLevel, int targetLevel) {
        // Calculate the progress ratio
        double progressRatio = (double) currentLevel / targetLevel;

        // Calculate the index of the image based on progress
        int imageIndex = (int) (progressRatio * 5);
        if (imageIndex < 0) {
            imageIndex = 0;
        } else if (imageIndex >= images.length) {
            imageIndex = images.length - 1;
        }

        // Update the image
        imageLabel.setIcon(images[imageIndex]);
    }
}
