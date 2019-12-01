package ru.utoplov.vladimir;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

public abstract class MappingManager {

    public abstract boolean isValidMessage(ShortMidiMessage msg);

    public abstract void execute(ShortMidiMessage msg);

}