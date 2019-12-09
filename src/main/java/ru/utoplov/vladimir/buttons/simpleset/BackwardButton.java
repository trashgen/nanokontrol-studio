package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SettableBeatTimeValue;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class BackwardButton extends SimpleButton {

    public final static int BUTTON_ID = 58;

    private ControllerContext controllerContext;

    BackwardButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        if (controllerContext.isSetPressed()) {
            SettableBeatTimeValue value = transport.getInPosition();
            value.set(transport.getPosition().get());
        }
    }
}
