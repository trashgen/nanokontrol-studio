package ru.utoplov.vladimir.controlset.continuousset.device;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.controlset.continuousset.ContinuousControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class WheelBackwardControl extends ContinuousControl {

    public final static int BUTTON_ID = 85;

    public WheelBackwardControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic(ShortMidiMessage msg) {
        if (cc.isSetPressed()) {
            cc.ArrangementPosition -= 4;
            cc.transport.setPosition(cc.ArrangementPosition);
        } else {
            cc.ArrangementPosition--;
            cc.transport.setPosition(cc.ArrangementPosition);
        }
    }
}
