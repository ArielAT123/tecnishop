package ClasesDatos.CardObjects;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Card<E> extends JPanel {
    protected E elemento;
    protected ImageIcon imagen;
    protected JLabel imageLabel;
    protected JLabel nameLabel;
    protected JLabel anotherLable;
    protected JButton button;
    
    public Card(E elemento, ImageIcon image) {
        this.elemento = elemento;
        this.imagen = image;
        
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
    }
    
    protected void addImageLabel(JLabel label) {
        this.imageLabel = label;
        add(label, BorderLayout.CENTER);
    }
    
    protected void addNameLabel(JLabel label) {
        this.nameLabel = label;
        add(label, BorderLayout.NORTH);
    }
    
    protected void addAnotherLabel(JLabel label) {
        this.anotherLable = label;
        add(label, BorderLayout.SOUTH);
    }
    
    protected void addButton(JButton button) {
        this.button = button;
        add(button, BorderLayout.PAGE_END);
    }
}