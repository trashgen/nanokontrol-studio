package ru.utoplov.vladimir.controlset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.core.ControllerContext;

public class AbstractStateControlSet extends AbstractControlSet {

    public static final int BUTTON_TRACK_RECORD_STATE_1 = 38;
    public static final int BUTTON_TRACK_RECORD_STATE_2 = 39;
    public static final int BUTTON_TRACK_RECORD_STATE_3 = 40;
    public static final int BUTTON_TRACK_RECORD_STATE_4 = 41;
    public static final int BUTTON_TRACK_RECORD_STATE_5 = 42;
    public static final int BUTTON_TRACK_RECORD_STATE_6 = 43;
    public static final int BUTTON_TRACK_RECORD_STATE_7 = 44;
    public static final int BUTTON_TRACK_RECORD_STATE_8 = 45;
    public static final int BUTTON_TRACK_RECORD_STATE_NOT_PRESSED = -1;
    public static final int BUTTON_CYCLE_STATE = 54;
    public static final int BUTTON_SET_STATE = 55;

    protected final ControllerContext controllerContext;

    public AbstractStateControlSet(ControllerContext controllerContext) {
        this.controllerContext = controllerContext;
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
