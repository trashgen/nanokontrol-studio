package ru.utoplov.vladimir.controlset.buttonset.device;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.PinnableCursorDevice;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;

public class NextDeviceButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 57;

    private PinnableCursorDevice cursorDevice;

    public NextDeviceButtonControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack, PinnableCursorDevice cursorDevice) {
        super(transport, trackBank, cursorTrack);
        this.cursorDevice = cursorDevice;
    }

    @Override
    protected void logic() {
        cursorDevice.selectNext();
        Boolean isNested = cursorDevice.isNested().get();
        String name = cursorDevice.name().get().toLowerCase();
        if (name.equals("fabfilter one")) {
            cursorDevice.hashCode();
        }
    }
}
