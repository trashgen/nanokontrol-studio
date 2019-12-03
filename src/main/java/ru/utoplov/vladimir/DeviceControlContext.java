package ru.utoplov.vladimir;

import com.bitwig.extension.controller.api.MidiOut;

public class DeviceControlContext {

    public MidiOut midiOut;
    public double ArrangementPosition;

    public DeviceControlContext(MidiOut midiOut) {
        this.midiOut = midiOut;
    }

}
