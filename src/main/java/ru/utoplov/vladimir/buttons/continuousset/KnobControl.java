package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;

public class KnobControl extends ContinuousControl {

    private int index;

    KnobControl(TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(trackBank, cursorTrack);
        this.index = index;
    }

    @Override
    void logic() {

    }
}
