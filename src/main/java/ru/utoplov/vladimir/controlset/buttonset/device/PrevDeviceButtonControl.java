package ru.utoplov.vladimir.controlset.buttonset.device;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.PinnableCursorDevice;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;

public class PrevDeviceButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 56;

    private PinnableCursorDevice cursorDevice;

    public PrevDeviceButtonControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack, PinnableCursorDevice cursorDevice) {
        super(transport, trackBank, cursorTrack);
        this.cursorDevice = cursorDevice;
    }

    @Override
    protected void logic() {
        cursorDevice.selectPrevious();
        String name = cursorDevice.name().get();
        if (name.equals("FABFILTER ONE")) {
            cursorDevice.hashCode();
        }
    }
}
