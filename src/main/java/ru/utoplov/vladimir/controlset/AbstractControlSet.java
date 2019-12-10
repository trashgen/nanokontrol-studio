package ru.utoplov.vladimir.controlset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.core.Control;
import ru.utoplov.vladimir.core.ControlSet;

import java.util.HashMap;
import java.util.Map;

public class AbstractControlSet implements ControlSet {

    protected final Map<Integer, Control> controls = new HashMap<>();

    @Override
    public boolean isValid(ShortMidiMessage msg) {
        return controls.get(msg.getData1()) != null;
    }

    @Override
    public boolean execute(ShortMidiMessage msg) {
        Control control = controls.get(msg.getData1());
        if (control != null) {
            control.execute(msg);
            return true;
        }
        return false;
    }

}
