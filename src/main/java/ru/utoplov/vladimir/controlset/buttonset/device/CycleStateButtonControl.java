package ru.utoplov.vladimir.controlset.buttonset.device;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.PinnableCursorDevice;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;

public class CycleStateButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 54;

    private PinnableCursorDevice cursorDevice;

    public CycleStateButtonControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack, PinnableCursorDevice cursorDevice) {
        super(transport, trackBank, cursorTrack);
        this.cursorDevice = cursorDevice;
    }

    @Override
    protected void logic() {
        cursorDevice.isWindowOpen().toggle();
    }
}
