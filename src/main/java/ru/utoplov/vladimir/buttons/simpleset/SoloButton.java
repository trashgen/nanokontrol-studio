package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class SoloButton extends SimpleButton {

    final static int BUTTON_ID_FIRST = 29;
    final static int BUTTON_ID_LAST = 36;

    private int index;
    private DeviceContext deviceContext;

    SoloButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank, int index) {
        super(transport, trackBank);
        this.index = index;
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        trackBank.getItemAt(index).solo().toggle();
        deviceContext.toggleLED(BUTTON_ID_FIRST + index, trackBank.getItemAt(index).solo().get());
    }

}
