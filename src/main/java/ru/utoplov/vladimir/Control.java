package ru.utoplov.vladimir;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

public interface Control {

    void execute(ShortMidiMessage msg);

}
