package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceControlContext;

import static ru.utoplov.vladimir.buttons.simpleset.SimpleButtonSet.BUTTON_MUTE_1;

public class MuteButton extends SimpleButton {

    private int index;
    private DeviceControlContext deviceControlContext;

    MuteButton(DeviceControlContext deviceControlContext, Transport transport, TrackBank trackBank, int index) {
        super(transport, trackBank);
        this.index = index;
        this.deviceControlContext = deviceControlContext;
    }

    @Override
    protected void logic() {
        trackBank.getItemAt(index).mute().toggle();
        deviceControlContext.midiOut.sendMidi(0xB0, BUTTON_MUTE_1 + index, trackBank.getItemAt(index).mute().get() ? 0 : 127);
    }
}
