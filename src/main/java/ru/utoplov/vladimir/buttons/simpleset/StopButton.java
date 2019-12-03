package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceControlContext;

import static ru.utoplov.vladimir.buttons.simpleset.SimpleButtonSet.BUTTON_TRANSPORT_PLAY;
import static ru.utoplov.vladimir.buttons.simpleset.SimpleButtonSet.BUTTON_TRANSPORT_RECORD;

public class StopButton extends SimpleButton {

    private DeviceControlContext deviceControlContext;

    StopButton(DeviceControlContext deviceControlContext, Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
        this.deviceControlContext = deviceControlContext;
    }

    @Override
    protected void logic() {
        transport.stop();
        deviceControlContext.ArrangementPosition = 0;
        deviceControlContext.midiOut.sendMidi(0xB0, BUTTON_TRANSPORT_PLAY, 0);
//        if (!transport.isArrangerRecordEnabled().get()) {
        deviceControlContext.midiOut.sendMidi(0xB0, BUTTON_TRANSPORT_RECORD, 0);
//        }
    }
}
