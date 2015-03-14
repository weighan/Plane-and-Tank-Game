import javax.swing.*;
import java.awt.*;
import java.awt.Image;

public interface plane {

    void moveRight();
    void moveLeft();
    void moveUp();
    void moveDown();

    Image getImage();

    int getX();
    int getY();
    int getHP();

    boolean isAlive();

    Rectangle getBounds();
}
