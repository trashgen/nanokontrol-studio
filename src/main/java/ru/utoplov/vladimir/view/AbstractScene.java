package ru.utoplov.vladimir.view;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.core.ControlSet;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractScene implements Scene {

    protected final List<ControlSet> controlSets = new ArrayList<>();

    public AbstractScene addControlSet(ControlSet controlSet) {
        controlSets.add(controlSet);
        return this;
    }

    @Override
    public boolean handleMidiCommand(ShortMidiMessage msg) {
        for (ControlSet controlSet : controlSets) {
            if (controlSet.isValid(msg)) {
                return controlSet.execute(msg);
            }
        }
        return false;
    }

}
