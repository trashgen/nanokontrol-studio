package ru.utoplov.vladimir.view;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

public class SendView implements Scene {

    private final static String SCENE_NAME = "Send Scene";

    @Override
    public String getName() {
        return SCENE_NAME;
    }

    @Override
    public void handleMidiCommand(ShortMidiMessage msg) {

    }
}
