package ru.utoplov.vladimir.controlset.stateset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.ControllerContext;
import ru.utoplov.vladimir.controlset.AbstractStateControlSet;

import java.util.Arrays;

public class MixStateControlSet extends AbstractStateControlSet {

    public MixStateControlSet(ControllerContext controllerContext) {
        super(controllerContext);
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

}
