package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;

public class RewindButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 62;

    public RewindButtonControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
    }

    @Override
    protected void logic() {
        transport.rewind();
    }

}
