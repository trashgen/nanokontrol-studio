package ru.utoplov.vladimir.controlset.continuousset.mix;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.SettableRangedValue;
import ru.utoplov.vladimir.controlset.continuousset.ContinuousControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class FaderControl extends ContinuousControl {

    public final static int BUTTON_ID_FIRST = 2;
    public final static int BUTTON_ID_LAST = 9;

    private int index;
    private double prevValue;

    public FaderControl(ControllerContext cc, int index) {
        super(cc);
        this.index = index;
    }

    @Override
    protected void logic(ShortMidiMessage msg) {
        if (cc.isCycleToggleStateActive()) {
            SettableRangedValue value = cc.trackBank.getItemAt(index).volume().value();
            double currValue = msg.getData2();
            if (cc.isSetPressed()) {
                double incr = currValue >= prevValue ? 1 : -1;
                value.inc(incr, 512 + 256);
            } else {
                value.set(msg.getData2(), 128);
            }
            prevValue = currValue;
        }
    }
}
