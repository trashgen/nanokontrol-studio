package ru.utoplov.vladimir.controlset.buttonset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.core.AbstractControl;

abstract public class ButtonControl extends AbstractControl {

    public ButtonControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
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
