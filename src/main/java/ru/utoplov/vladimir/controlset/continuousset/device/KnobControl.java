package ru.utoplov.vladimir.controlset.continuousset.device;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.RemoteControl;
import ru.utoplov.vladimir.controlset.continuousset.ContinuousControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class KnobControl extends ContinuousControl {

    public final static int BUTTON_ID_FIRST = 13;
    public final static int BUTTON_ID_LAST = 20;

    private int index;

    public KnobControl(ControllerContext cc, int index) {
        super(cc);
        this.index = index;
    }

    @Override
    protected void logic(ShortMidiMessage msg) {
        if (cc.isCycleToggleStateActive()) {
            RemoteControl parameter = cc.controlsOne.getParameter(index);
            parameter.set(msg.getData2(), 128);
        }
    }
}
