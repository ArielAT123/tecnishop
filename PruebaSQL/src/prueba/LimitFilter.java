/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Ernesto
 */
public class LimitFilter extends DocumentFilter {
    private int limit;

    public LimitFilter(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;
        if ((fb.getDocument().getLength() + string.length()) <= limit) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) return;
        if ((fb.getDocument().getLength() - length + text.length()) <= limit) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}