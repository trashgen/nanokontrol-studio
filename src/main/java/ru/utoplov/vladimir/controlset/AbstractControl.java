package ru.utoplov.vladimir.controlset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.Control;

abstract public class AbstractControl implements Control {

    protected final TrackBank trackBank;
    protected final Transport transport;
    protected final CursorTrack cursorTrack;

    public AbstractControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        this.trackBank = trackBank;
        this.transport = transport;
        this.cursorTrack = cursorTrack;
    }


}
