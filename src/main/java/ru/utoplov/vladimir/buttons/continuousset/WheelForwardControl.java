package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

class WheelForwardControl extends ContinuousControl {

    final static int BUTTON_ID = 83;

    private Transport transport;

    private DeviceContext deviceContext;

    WheelForwardControl(DeviceContext deviceContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(trackBank, cursorTrack);
        this.transport = transport;
        this.deviceContext = deviceContext;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        if (deviceContext.isCycleToggleStateActive()) {
            cursorTrack.volume().value().inc(1, 1024);
        } else {
            deviceContext.ArrangementPosition++;
            transport.setPosition(deviceContext.ArrangementPosition);
        }
    }
}
