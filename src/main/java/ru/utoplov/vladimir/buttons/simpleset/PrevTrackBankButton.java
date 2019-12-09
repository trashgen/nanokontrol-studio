package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class PrevTrackBankButton extends SimpleButton {

    final static int BUTTON_ID = 60;

    private ControllerContext controllerContext;

    PrevTrackBankButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        if (controllerContext.isSetPressed()) {
            trackBank.scrollBackwards();
        } else {
            trackBank.scrollPageBackwards();
        }
    }
}
