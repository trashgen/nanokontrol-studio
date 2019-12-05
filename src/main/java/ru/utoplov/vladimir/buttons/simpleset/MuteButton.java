package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.SettableBooleanValue;
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
        SettableBooleanValue mute = trackBank.getItemAt(index).mute();
        mute.toggle();
        deviceContext.toggleLED(BUTTON_ID_FIRST + index, mute.get());
        if (deviceContext.isSetPressed()) {
            for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    trackBank.getItemAt(i).mute().set(false);
                }
            }
            for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    deviceContext.ledOFF(i);
                }
            }
        }
    }
}
