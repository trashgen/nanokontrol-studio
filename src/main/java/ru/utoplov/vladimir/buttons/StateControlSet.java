package ru.utoplov.vladimir.buttons;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.ControllerContext;

import java.util.Arrays;

public class StateControlSet extends AbstractControlSet {

    public static final int BUTTON_TRACK_RECORD_STATE_1 = 38;
    public static final int BUTTON_TRACK_RECORD_STATE_2 = 39;
    public static final int BUTTON_TRACK_RECORD_STATE_3 = 40;
    public static final int BUTTON_TRACK_RECORD_STATE_4 = 41;
    public static final int BUTTON_TRACK_RECORD_STATE_5 = 42;
    public static final int BUTTON_TRACK_RECORD_STATE_6 = 43;
    public static final int BUTTON_TRACK_RECORD_STATE_7 = 44;
    public final static int BUTTON_TRACK_RECORD_STATE_8 = 45;
    public final static int BUTTON_TRACK_RECORD_STATE_NOT_PRESSED = -1;

    public static final int BUTTON_CYCLE_STATE = 54;
    public final static int BUTTON_SET_STATE = 55;

    private final ControllerContext controllerContext;

    public StateControlSet(ControllerContext controllerContext) {
        this.controllerContext = controllerContext;
    }

    @Override
    public boolean isValid(ShortMidiMessage msg) {
        return Arrays
                .stream(new Integer[]{
                        BUTTON_CYCLE_STATE,
                        BUTTON_SET_STATE,
                        BUTTON_TRACK_RECORD_STATE_1,
                        BUTTON_TRACK_RECORD_STATE_2,
                        BUTTON_TRACK_RECORD_STATE_3,
                        BUTTON_TRACK_RECORD_STATE_4,
                        BUTTON_TRACK_RECORD_STATE_5,
                        BUTTON_TRACK_RECORD_STATE_6,
                        BUTTON_TRACK_RECORD_STATE_7,
                        BUTTON_TRACK_RECORD_STATE_8})
                .anyMatch(code -> msg.getData1() == code);
    }

    @Override
    public boolean execute(ShortMidiMessage msg) {
        switch (msg.getData2()) {
            case 0:
                controllerContext.updateStateControl(msg.getData1(), false);
                controllerContext.ledOFF(msg.getData1());
                return true;
            case 127:
                controllerContext.updateStateControl(msg.getData1(), true);
                controllerContext.ledON(msg.getData1());
                return true;
        }
        return false;
    }

}
