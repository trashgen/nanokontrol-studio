package ru.utoplov.vladimir.view;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

public interface Scene {
    String getName();

    boolean handleMidiCommand(ShortMidiMessage msg);
}
