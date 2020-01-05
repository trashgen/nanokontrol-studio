package ru.utoplov.vladimir.controlset.continuousset.device;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.controlset.continuousset.ContinuousControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class WheelForwardControl extends ContinuousControl {

    public final static int BUTTON_ID = 83;

    public WheelForwardControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic(ShortMidiMessage msg) {
        if (cc.isSetPressed()) {
            cc.ArrangementPosition += 4;
            cc.transport.setPosition(cc.ArrangementPosition);
        } else {
            cc.ArrangementPosition++;
            cc.transport.setPosition(cc.ArrangementPosition);
        }
    }
}
