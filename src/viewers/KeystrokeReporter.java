package viewers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public final class KeystrokeReporter extends JFrame implements ActionListener,
        KeyListener {

    private static final int DEFAULT_COLUMNS = 20;

    private JLabel keyId = new JLabel();

    private JLabel eventId = new JLabel();

    private JLabel modifiers = new JLabel();

    @Override
    public void actionPerformed(ActionEvent event) {
        //
    }

    @Override
    public void keyReleased(KeyEvent event) {
        this.keyId.setText(KeyEvent.getKeyText(event.getKeyCode()));
        this.eventId.setText("A key has been released");
        this.modifiers.setText(InputEvent.getModifiersExText(event.getModifiersEx()));
    }

    @Override
    public void keyPressed(KeyEvent event) {
        this.keyId.setText(KeyEvent.getKeyText(event.getKeyCode()));
        this.eventId.setText("A key has been pressed");
    }

    @Override
    public void keyTyped(KeyEvent event) {
        this.keyId.setText(KeyEvent.getKeyText(event.getKeyCode()));
        this.eventId.setText("A key has been typed");
        this.modifiers.setText(InputEvent.getModifiersExText(event.getModifiersEx()));
    }

    public KeystrokeReporter() {
        this.add(this.keyId, BorderLayout.PAGE_START);
        this.add(this.eventId, BorderLayout.CENTER);
        this.add(this.modifiers, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {
        KeystrokeReporter frame = new KeystrokeReporter();
        Dimension preferredSize = new Dimension(400, 400);
        frame.setPreferredSize(preferredSize);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.addKeyListener(frame);
        frame.setVisible(true);
    }

}
