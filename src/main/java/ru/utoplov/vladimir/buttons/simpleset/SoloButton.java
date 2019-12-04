package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.SoloValue;
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
        SoloValue solo = trackBank.getItemAt(index).solo();
        if (deviceContext.isSetPressed()) {
            solo.toggle(true);
            for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    deviceContext.ledOFF(i);
                }
            }
        } else {
            solo.toggle();
        }
        deviceContext.toggleLED(BUTTON_ID_FIRST + index, solo.get());
    }

}
