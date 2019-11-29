package ru.utoplov.vladimir.view;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

public interface View {
    void handleMidiCommand(ShortMidiMessage msg);
}
