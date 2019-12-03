package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceControlContext;

import static ru.utoplov.vladimir.buttons.simpleset.SimpleButtonSet.BUTTON_TRANSPORT_RECORD;

public class RecordButton extends SimpleButton {

    private DeviceControlContext deviceControlContext;

    RecordButton(DeviceControlContext deviceControlContext, Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
        this.deviceControlContext = deviceControlContext;
    }

    @Override
    protected void logic() {
        transport.record();
        deviceControlContext.midiOut.sendMidi(0xB0, BUTTON_TRANSPORT_RECORD, transport.isArrangerRecordEnabled().get() ? 0 : 127);
    }
}
