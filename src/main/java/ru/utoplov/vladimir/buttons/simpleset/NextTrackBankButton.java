package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class NextTrackBankButton extends SimpleButton {

    final static int BUTTON_ID = 61;

    private ControllerContext controllerContext;

    NextTrackBankButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
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
