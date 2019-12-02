package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;

public class FaderControl extends ContinuousControl {

    private int index;

    FaderControl(TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(trackBank, cursorTrack);
        this.index = index;
    }

    @Override
    void logic() {
//        trackBank.getItemAt(index).volume().value().set(msg.getData2(), 128);
    }
}
