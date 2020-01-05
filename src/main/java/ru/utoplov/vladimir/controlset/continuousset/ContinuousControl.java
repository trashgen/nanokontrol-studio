package ru.utoplov.vladimir.controlset.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.core.AbstractControl;
import ru.utoplov.vladimir.core.ControllerContext;

public abstract class ContinuousControl extends AbstractControl {

    public ContinuousControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    public void execute(ShortMidiMessage msg) {
        logic(msg);
    }

    protected abstract void logic(ShortMidiMessage msg);

}
