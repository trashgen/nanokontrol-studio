package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SettableBeatTimeValue;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class ForwardButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 59;

    private ControllerContext controllerContext;

    ForwardButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        if (controllerContext.isSetPressed()) {
            SettableBeatTimeValue beatValue = transport.getOutPosition();
            beatValue.set(transport.getPosition().get());
            controllerContext.ArrangementPosition = transport.getInPosition().get();
            transport.setPosition(transport.getInPosition().get());
        }
    }
}
