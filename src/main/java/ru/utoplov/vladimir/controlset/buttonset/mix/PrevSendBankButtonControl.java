package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class PrevSendBankButtonControl extends ButtonControl {

    final static int BUTTON_ID = 56;

    private ControllerContext controllerContext;

    PrevSendBankButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        if (controllerContext.isSetPressed()) {
            cursorTrack.sendBank().scrollBackwards();
        } else {
            cursorTrack.sendBank().scrollPageBackwards();
        }
    }
}
