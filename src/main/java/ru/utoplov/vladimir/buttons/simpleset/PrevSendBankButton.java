package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class PrevSendBankButton extends SimpleButton {

    final static int BUTTON_ID = 56;

    private ControllerContext controllerContext;

    PrevSendBankButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
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
