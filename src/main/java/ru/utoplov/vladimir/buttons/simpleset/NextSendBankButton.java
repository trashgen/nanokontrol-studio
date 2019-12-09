package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class NextSendBankButton extends SimpleButton {

    final static int BUTTON_ID = 57;

    private CursorTrack cursorTrack;
    private ControllerContext controllerContext;

    NextSendBankButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
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
