package ru.utoplov.vladimir.controlset.buttonset.device;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.PinnableCursorDevice;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class RewindDeviceButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 62;

    private PinnableCursorDevice cursorDevice;
    private ControllerContext controllerContext;

    public RewindDeviceButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, PinnableCursorDevice cursorDevice) {
        super(transport, trackBank, cursorTrack);
        this.cursorDevice = cursorDevice;
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        cursorDevice.isWindowOpen().toggle();
    }

}
