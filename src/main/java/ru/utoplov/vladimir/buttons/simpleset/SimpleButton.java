package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.buttons.AbstractControl;

abstract class SimpleButton extends AbstractControl {

    public SimpleButton(Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
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
