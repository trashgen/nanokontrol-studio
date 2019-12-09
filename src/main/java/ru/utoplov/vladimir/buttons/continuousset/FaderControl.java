package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SettableRangedValue;
import com.bitwig.extension.controller.api.TrackBank;
import ru.utoplov.vladimir.DeviceContext;

public class FaderControl extends ContinuousControl {

    final static int BUTTON_ID_FIRST = 2;
    final static int BUTTON_ID_LAST = 9;

    private int index;
    private double prevValue;
    private DeviceContext deviceContext;

    FaderControl(DeviceContext deviceContext, TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(trackBank, cursorTrack);
        this.index = index;
        this.deviceContext = deviceContext;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        if (deviceContext.isCycleToggleStateActive()) {
            SettableRangedValue value = trackBank.getItemAt(index).volume().value();
            double currValue = msg.getData2();
            if (deviceContext.isSetPressed()) {
                double incr = currValue >= prevValue ? 1 : -1;
                value.inc(incr, 512 + 256);
            } else {
                value.set(msg.getData2(), 128);
            }
            prevValue = currValue;
        }
    }
}
