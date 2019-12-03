package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceControlContext;

public class WheelForwardControl extends ContinuousControl {

    private Transport transport;

    private DeviceControlContext deviceControlContext;

    WheelForwardControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack, DeviceControlContext deviceControlContext) {
        super(trackBank, cursorTrack);
        this.transport = transport;
        this.deviceControlContext = deviceControlContext;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        deviceControlContext.ArrangementPosition++;
        transport.setPosition(deviceControlContext.ArrangementPosition);
    }
}
