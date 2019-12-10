package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class PrevTrackBankButtonControl extends ButtonControl {

    final static int BUTTON_ID = 60;

    private ControllerContext controllerContext;

    PrevTrackBankButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
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
