package ru.utoplov.vladimir.controlset.buttonset.device;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class SetButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 55;

    private ControllerContext controllerContext;
    private PinnableCursorDevice cursorDevice;
    private CursorRemoteControlsPage controlsPageBank;

    SetButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, PinnableCursorDevice cursorDevice, CursorRemoteControlsPage controlsPageBank) {
        super(transport, trackBank, cursorTrack);
        this.cursorDevice = cursorDevice;
        this.controlsPageBank = controlsPageBank;
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        cursorDevice.isWindowOpen().toggle();
    }
}
