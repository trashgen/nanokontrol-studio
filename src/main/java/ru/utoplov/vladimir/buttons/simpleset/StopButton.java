package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class StopButton extends SimpleButton {

    final static int BUTTON_ID = 63;

    private ControllerContext controllerContext;

    StopButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        transport.stop();
        controllerContext.ledOFF(PlayButton.BUTTON_ID);
        controllerContext.ledOFF(RecordButton.BUTTON_ID);
        if (controllerContext.isSetPressed()) {
            controllerContext.ArrangementPosition = 0;
        }
    }
}
