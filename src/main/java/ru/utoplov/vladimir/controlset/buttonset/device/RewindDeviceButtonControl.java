package ru.utoplov.vladimir.controlset.buttonset.device;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class RewindDeviceButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 62;

    private PinnableCursorDevice cursorDevice;
    private ControllerContext controllerContext;
    private CursorRemoteControlsPage controlsPageBank;

    public RewindDeviceButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, PinnableCursorDevice cursorDevice, CursorRemoteControlsPage controlsPageBank) {
        super(transport, trackBank, cursorTrack);
        this.cursorDevice = cursorDevice;
        this.controllerContext = controllerContext;
        this.controlsPageBank = controlsPageBank;
    }

    @Override
    protected void logic() {
        cursorDevice.isWindowOpen().toggle();
        // This works but only from pages of Remote params :(
//        for (int i = 0; i < controlsPageBank.getParameterCount(); i++) {
//            RemoteControl control = controlsPageBank.getParameter(i);
//            String name = control.name().get();
//            double value = control.value().get();
//            cursorDevice.hashCode();
//        }
    }

}
