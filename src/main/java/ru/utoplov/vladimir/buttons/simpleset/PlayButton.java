package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceControlContext;

import static ru.utoplov.vladimir.buttons.simpleset.SimpleButtonSet.BUTTON_TRANSPORT_PLAY;

public class PlayButton extends SimpleButton {

    private DeviceControlContext deviceControlContext;

    public PlayButton(DeviceControlContext deviceControlContext, Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
        this.deviceControlContext = deviceControlContext;
    }

    @Override
    protected void logic() {
        transport.play();
        deviceControlContext.midiOut.sendMidi(0xB0, BUTTON_TRANSPORT_PLAY, transport.isPlaying().get() ? 0 : 127);
    }

}