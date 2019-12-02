package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;

public abstract class ContinuousControl {

    protected final TrackBank trackBank;
    protected final CursorTrack cursorTrack;

    ContinuousControl(TrackBank trackBank, CursorTrack cursorTrack) {
        this.trackBank = trackBank;
        this.cursorTrack = cursorTrack;
    }

    void execute(ShortMidiMessage msg) {
        logic(msg);
    }

    abstract void logic(ShortMidiMessage msg);

}
