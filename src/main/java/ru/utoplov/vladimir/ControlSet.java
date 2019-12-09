package ru.utoplov.vladimir;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

public interface ControlSet {

    boolean isValid(ShortMidiMessage msg);

    boolean execute(ShortMidiMessage msg);

}
