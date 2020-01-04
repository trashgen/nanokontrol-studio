package ru.utoplov.vladimir.core;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

public interface ControlSet {

    boolean isValid(ShortMidiMessage msg);

    boolean execute(ShortMidiMessage msg);

    default void updateIndication(boolean enabled) {
    }

}
