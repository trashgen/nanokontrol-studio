package ru.utoplov.vladimir.controlset.buttonset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.core.AbstractControl;
import ru.utoplov.vladimir.core.ControllerContext;

abstract public class ButtonControl extends AbstractControl {

    public ButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    public void execute(ShortMidiMessage msg) {
        if (msg.getData2() != 0) {
            return;
        }
        logic();
    }

    abstract protected void logic();

}
