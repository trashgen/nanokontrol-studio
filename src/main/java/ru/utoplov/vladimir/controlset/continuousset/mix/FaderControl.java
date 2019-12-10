package ru.utoplov.vladimir.controlset.continuousset.mix;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SettableRangedValue;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.core.ControllerContext;

class FaderControl extends ContinuousControl {

    final static int BUTTON_ID_FIRST = 2;
    final static int BUTTON_ID_LAST = 9;

    private int index;
    private double prevValue;
    private ControllerContext controllerContext;

    FaderControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(transport, trackBank, cursorTrack);
        this.index = index;
        this.controllerContext = controllerContext;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        if (controllerContext.isCycleToggleStateActive()) {
            SettableRangedValue value = trackBank.getItemAt(index).volume().value();
            double currValue = msg.getData2();
            if (controllerContext.isSetPressed()) {
                double incr = currValue >= prevValue ? 1 : -1;
                value.inc(incr, 512 + 256);
            } else {
                value.set(msg.getData2(), 128);
            }
            prevValue = currValue;
        }
    }
}
