package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;

public class KnobControl extends ContinuousControl {

    final static int BUTTON_ID_FIRST = 13;
    final static int BUTTON_ID_LAST = 20;

    private int index;

    KnobControl(TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(trackBank, cursorTrack);
        this.index = index;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        cursorTrack.sendBank().getItemAt(index).value().set(msg.getData2(), 128);
    }
}
