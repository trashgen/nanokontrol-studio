package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class SelectButton extends SimpleButton {

    final static int BUTTON_ID_FIRST = 46;
    final static int BUTTON_ID_LAST = 53;

    private int index;
    private DeviceContext deviceContext;

    SelectButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank, int index) {
        super(transport, trackBank);
        this.index = index;
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        trackBank.getItemAt(index).selectInMixer();
        deviceContext.ledON(BUTTON_ID_FIRST + index);
        for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
            if ((i - BUTTON_ID_FIRST) != index) {
                deviceContext.ledOFF(i);
            }
        }
    }

}
