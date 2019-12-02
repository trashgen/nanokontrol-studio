package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

public class WheelForwardControl extends ContinuousControl {

    private Transport transport;

    private ControlContext controlContext;

    WheelForwardControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack, ControlContext controlContext) {
        super(trackBank, cursorTrack);
        this.transport = transport;
        this.controlContext = controlContext;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        controlContext.ArrangmentPosition++;
        transport.setPosition(controlContext.ArrangmentPosition);
    }
}
