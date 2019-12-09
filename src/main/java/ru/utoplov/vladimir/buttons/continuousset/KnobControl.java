package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SettableRangedValue;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

class KnobControl extends ContinuousControl {

    final static int BUTTON_ID_FIRST = 13;
    final static int BUTTON_ID_LAST = 20;

    private int index;
    private double prevValue;
    private ControllerContext controllerContext;

    KnobControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(transport, trackBank, cursorTrack);
        this.index = index;
        this.controllerContext = controllerContext;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        if (controllerContext.isCycleToggleStateActive()) {
            SettableRangedValue value = cursorTrack.sendBank().getItemAt(index).value();
            double currValue = msg.getData2();
            if (controllerContext.isSetPressed()) {
                value.inc(currValue >= prevValue ? 1 : -1, 512 + 256);
            } else {
                value.set(msg.getData2(), 128);
            }
            prevValue = currValue;
        }
    }
}
