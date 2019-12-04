package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class MuteButton extends SimpleButton {

    final static int BUTTON_ID_FIRST = 21;
    final static int BUTTON_ID_LAST = 28;

    private int index;
    private DeviceContext deviceContext;

    MuteButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank, int index) {
        super(transport, trackBank);
        this.index = index;
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        trackBank.getItemAt(index).mute().toggle();
        deviceContext.toggleLED(BUTTON_ID_FIRST + index, trackBank.getItemAt(index).mute().get());
    }
}
