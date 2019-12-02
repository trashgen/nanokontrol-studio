package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

public class WheelControl extends ContinuousControl {

    private Transport transport;

    WheelControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(trackBank, cursorTrack);
        this.transport = transport;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        transport.setPosition(msg.getData2());
    }
}
