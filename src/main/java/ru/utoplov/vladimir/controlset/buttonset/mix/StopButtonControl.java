package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class StopButtonControl extends ButtonControl {

    final static int BUTTON_ID = 63;

    private ControllerContext controllerContext;

    StopButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        transport.stop();
        controllerContext.ledOFF(PlayButtonControl.BUTTON_ID);
        controllerContext.ledOFF(RecordButtonControl.BUTTON_ID);
        if (controllerContext.isSetPressed()) {
            controllerContext.ArrangementPosition = 0;
        }
    }
}
