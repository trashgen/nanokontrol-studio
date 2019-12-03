package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceControlContext;

public class SelectButton extends SimpleButton {

    private int index;
    private DeviceControlContext deviceControlContext;

    SelectButton(DeviceControlContext deviceControlContext, Transport transport, TrackBank trackBank, int index) {
        super(transport, trackBank);
        this.index = index;
        this.deviceControlContext = deviceControlContext;
    }

    @Override
    protected void logic() {
        trackBank.getItemAt(index).selectInMixer();
//        deviceControlContext.midiOut.sendMidi(0xB0, BUTTON_SELECT_1 + index, trackBank.getItemAt(index).iss ? 0 : 127);
    }
}
