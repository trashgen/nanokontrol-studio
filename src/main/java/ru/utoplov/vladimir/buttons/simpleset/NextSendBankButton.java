package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class NextSendBankButton extends SimpleButton {

    final static int BUTTON_ID = 57;

    private CursorTrack cursorTrack;
    private DeviceContext deviceContext;

    NextSendBankButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank);
        this.cursorTrack = cursorTrack;
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        if (deviceContext.isSetPressed()) {
            cursorTrack.sendBank().scrollForwards();
        } else {
            cursorTrack.sendBank().scrollPageForwards();
        }
    }
}
