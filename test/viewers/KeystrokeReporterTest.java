package viewers;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static randomness.ExtendedRandom.chooseCharacterFromBlock;

class KeystrokeReporterTest {

    private static KeyEvent makeKeyEvent(int keyEventType) {
        long when = System.currentTimeMillis();
        int modifiers = 0;
        int keyCode = 0;
        char keyChar
                = chooseCharacterFromBlock(Character.UnicodeBlock.BASIC_LATIN);
        return new KeyEvent(new Component() {}, keyEventType, when, modifiers,
                keyCode, keyChar);
    }

}
