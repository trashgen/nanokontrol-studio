package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class PrevSendBankButton extends SimpleButton {

    final static int BUTTON_ID = 56;

    private CursorTrack cursorTrack;
    private DeviceContext deviceContext;

    PrevSendBankButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank);
        this.cursorTrack = cursorTrack;
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        if (deviceContext.isSetPressed()) {
            cursorTrack.sendBank().scrollBackwards();
        } else {
            cursorTrack.sendBank().scrollPageBackwards();
        }
    }
}
