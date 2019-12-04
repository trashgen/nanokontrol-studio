package ru.utoplov.vladimir.buttons;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.ButtonSet;
import ru.utoplov.vladimir.DeviceContext;

import java.util.Arrays;

public class StateControlSet implements ButtonSet {

    public static final int BUTTON_CYCLE_STATE = 54;
    public final static int BUTTON_SET_STATE = 55;
    public final static int BUTTON_TRANSPORT_BACKWARD = 58;
    public final static int BUTTON_TRANSPORT_FAST_FORWARD = 59;

    private final DeviceContext deviceContext;

    public StateControlSet(DeviceContext deviceContext) {
        this.deviceContext = deviceContext;
    }

    @Override
    public boolean isValid(ShortMidiMessage msg) {
        return Arrays
                .stream(new Integer[]{
                        BUTTON_CYCLE_STATE,
                        BUTTON_SET_STATE,
                        BUTTON_TRANSPORT_BACKWARD,
                        BUTTON_TRANSPORT_FAST_FORWARD})
                .anyMatch(code -> msg.getData1() == code);
    }

    @Override
    public boolean execute(ShortMidiMessage msg) {
        switch (msg.getData2()) {
            case 0:
                deviceContext.updateStateControl(msg.getData1(), false);
                deviceContext.ledOFF(msg.getData1());
                return true;
            case 127:
                deviceContext.updateStateControl(msg.getData1(), true);
                deviceContext.ledON(msg.getData1());
                return true;
        }
        return false;
    }

}
