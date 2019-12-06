package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SettableRangedValue;
import com.bitwig.extension.controller.api.TrackBank;
import ru.utoplov.vladimir.DeviceContext;

public class KnobControl extends ContinuousControl {

    final static int BUTTON_ID_FIRST = 13;
    final static int BUTTON_ID_LAST = 20;

    private int index;
    private double prevValue;
    private DeviceContext deviceContext;

    KnobControl(DeviceContext deviceContext, TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(trackBank, cursorTrack);
        this.index = index;
        this.deviceContext = deviceContext;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        if (!deviceContext.isCycleToggleStateActive()) {
            SettableRangedValue value = cursorTrack.sendBank().getItemAt(index).value();
            double currValue = msg.getData2();
            if (deviceContext.isSetPressed()) {
                value.inc(currValue >= prevValue ? 1 : -1, 512 + 256);
            } else {
                value.set(msg.getData2(), 128);
            }
            prevValue = currValue;
        }
    }
}
