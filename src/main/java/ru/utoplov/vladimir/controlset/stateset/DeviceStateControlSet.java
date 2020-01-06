package ru.utoplov.vladimir.controlset.stateset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.controlset.AbstractStateControlSet;
import ru.utoplov.vladimir.core.ControllerContext;

import java.util.Arrays;

public class DeviceStateControlSet extends AbstractStateControlSet {

    public DeviceStateControlSet(ControllerContext controllerContext) {
        super(controllerContext);
    }

    @Override
    public boolean isValid(ShortMidiMessage msg) {
        return Arrays.stream(new Integer[]{BUTTON_CYCLE_STATE, BUTTON_SET_STATE}).anyMatch(code -> msg.getData1() == code);
    }

}