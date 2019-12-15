package ru.utoplov.vladimir.controlset.buttonset.device;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;

public class RewindDeviceButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 62;

    private PinnableCursorDevice cursorDevice;
    private CursorRemoteControlsPage controlsPageBank;

    public RewindDeviceButtonControl(Transport transport, TrackBank trackBank, CursorTrack cursorTrack, PinnableCursorDevice cursorDevice, CursorRemoteControlsPage controlsPageBank) {
        super(transport, trackBank, cursorTrack);
        this.cursorDevice = cursorDevice;
        this.controlsPageBank = controlsPageBank;
    }

    @Override
    protected void logic() {
        // This works but only from pages of Remote params :(
        for (int i = 0; i < controlsPageBank.getParameterCount(); i++) {
            RemoteControl control = controlsPageBank.getParameter(i);
            String name = control.name().get();
            double value = control.value().get();
            cursorDevice.hashCode();
        }
    }

}
