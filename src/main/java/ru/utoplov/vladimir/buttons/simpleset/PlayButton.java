package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class PlayButton extends SimpleButton {

    final static int BUTTON_ID = 80;

    private ControllerContext controllerContext;

    public PlayButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        transport.setPosition(controllerContext.ArrangementPosition);
        transport.play();
        controllerContext.toggleLED(BUTTON_ID, transport.isPlaying().get());
    }

}