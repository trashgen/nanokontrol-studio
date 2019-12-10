package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class NextSendBankButtonControl extends ButtonControl {

    final static int BUTTON_ID = 57;

    private CursorTrack cursorTrack;
    private ControllerContext controllerContext;

    NextSendBankButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.cursorTrack = cursorTrack;
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        if (controllerContext.isSetPressed()) {
            cursorTrack.sendBank().scrollForwards();
        } else {
            cursorTrack.sendBank().scrollPageForwards();
        }
    }
}
