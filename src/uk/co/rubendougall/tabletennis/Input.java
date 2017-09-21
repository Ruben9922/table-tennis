package uk.co.rubendougall.tabletennis;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

class Input {
    private Set<KeyCode> pressedKeys = new HashSet<>();

    public Input() {
    }

    void handleKeyPressed(KeyEvent event) {
        pressedKeys.add(event.getCode());
    }

    void handleKeyReleased(KeyEvent event) {
        pressedKeys.remove(event.getCode());
    }

    boolean isKeyPressed(KeyCode code) {
        return pressedKeys.contains(code);
    }
}
