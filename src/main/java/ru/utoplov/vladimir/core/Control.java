package ru.utoplov.vladimir.core;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

public interface Control {

    void execute(ShortMidiMessage msg);

}
