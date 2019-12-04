package ru.utoplov.vladimir;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

public interface ButtonSet {

    boolean isValid(ShortMidiMessage msg);
    boolean execute(ShortMidiMessage msg);

}
