package ru.utoplov.vladimir.controlset.continuousset.mix;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.SettableRangedValue;
import ru.utoplov.vladimir.controlset.continuousset.ContinuousControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class KnobControl extends ContinuousControl {

    public final static int BUTTON_ID_FIRST = 13;
    public final static int BUTTON_ID_LAST = 20;

    private int index;
    private double prevValue;

    public KnobControl(ControllerContext cc, int index) {
        super(cc);
        this.index = index;
    }

    @Override
    protected void logic(ShortMidiMessage msg) {
        if (cc.isCycleToggleStateActive()) {
            SettableRangedValue value = cc.cursorTrack.sendBank().getItemAt(index).value();
            double currValue = msg.getData2();
            if (cc.isSetPressed()) {
                value.inc(currValue >= prevValue ? 1 : -1, 512 + 256);
            } else {
                value.set(msg.getData2(), 128);
            }
            prevValue = currValue;
        }
    }
}
