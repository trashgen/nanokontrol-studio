package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class PrevTrackBankButton extends SimpleButton {

    final static int BUTTON_ID = 60;

    private DeviceContext deviceContext;

    PrevTrackBankButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        if (deviceContext.isSetPressed()) {
            trackBank.scrollBackwards();
        } else {
            trackBank.scrollPageBackwards();
        }
    }
}
