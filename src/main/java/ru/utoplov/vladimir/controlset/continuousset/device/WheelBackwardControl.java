package ru.utoplov.vladimir.controlset.continuousset.device;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.continuousset.ContinuousControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class WheelBackwardControl extends ContinuousControl {

    public final static int BUTTON_ID = 85;

    private ControllerContext controllerContext;

    public WheelBackwardControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic(ShortMidiMessage msg) {
        if (controllerContext.isSetPressed()) {
            controllerContext.ArrangementPosition -= 4;
            transport.setPosition(controllerContext.ArrangementPosition);
        } else {
            controllerContext.ArrangementPosition--;
            transport.setPosition(controllerContext.ArrangementPosition);
        }
    }
}
