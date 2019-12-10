package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class NextTrackBankButtonControl extends ButtonControl {

    final static int BUTTON_ID = 61;

    private ControllerContext controllerContext;

    NextTrackBankButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        if (controllerContext.isSetPressed()) {
            trackBank.scrollForwards();
        } else {
            trackBank.scrollPageForwards();
        }
    }
}
