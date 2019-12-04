package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;

public class FaderControl extends ContinuousControl {

    final static int BUTTON_ID_FIRST = 2;
    final static int BUTTON_ID_LAST = 9;

    private int index;

    FaderControl(TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(trackBank, cursorTrack);
        this.index = index;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        trackBank.getItemAt(index).volume().value().set(msg.getData2(), 128);
    }
}
