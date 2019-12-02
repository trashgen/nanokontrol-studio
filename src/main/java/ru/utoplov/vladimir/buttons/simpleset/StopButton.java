package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.buttons.continuousset.ControlContext;

public class StopButton extends SimpleButton {

    private ControlContext controlContext;

    StopButton(Transport transport, TrackBank trackBank, ControlContext controlContext) {
        super(transport, trackBank);
        this.controlContext = controlContext;
    }

    @Override
    protected void logic() {
        controlContext.ArrangmentPosition = 0;
        transport.stop();
    }
}
