package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

abstract class SimpleButton {

    protected final TrackBank trackBank;
    protected final Transport transport;

    SimpleButton(Transport transport, TrackBank trackBank) {
        this.trackBank = trackBank;
        this.transport = transport;
    }

    void execute(ShortMidiMessage msg) {
        if (msg.getData2() != 0) {
            return;
        }
        logic();
    }

    abstract protected void logic();

}
