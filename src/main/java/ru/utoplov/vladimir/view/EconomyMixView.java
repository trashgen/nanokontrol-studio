package ru.utoplov.vladimir.view;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

// Same as Mix Scene but without leds. Good for long BT session.
public class EconomyMixView implements Scene {

    private final static String SCENE_NAME = "Economy Mix Scene";

    @Override
    public String getName() {
        return SCENE_NAME;
    }

    @Override
    public boolean handleMidiCommand(ShortMidiMessage msg) {
        return false;
    }
}
