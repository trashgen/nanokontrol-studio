package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.buttons.AbstractControl;

abstract class ContinuousControl extends AbstractControl {

    ContinuousControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
    }

    @Override
    public void execute(ShortMidiMessage msg) {
        logic(msg);
    }

    abstract void logic(ShortMidiMessage msg);

}
