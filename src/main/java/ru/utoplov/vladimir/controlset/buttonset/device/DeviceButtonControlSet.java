package ru.utoplov.vladimir.controlset.buttonset.device;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.AbstractControlSet;
import ru.utoplov.vladimir.core.ControllerContext;

public class DeviceButtonControlSet extends AbstractControlSet {

    private static final String NANO_KONTROL_STUDIO_DEVICE_ID = "NANO_KONTROL_STUDIO_DEVICE_ID";
    private static final String NANO_KONTROL_STUDIO_DEVICE_NAME = "NANO_KONTROL_STUDIO_DEVICE_NAME";

    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME";

    private PinnableCursorDevice cursorDevice;
    private CursorRemoteControlsPage controlsPageBank;

    public DeviceButtonControlSet(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        cursorDevice = cursorTrack.createCursorDevice(NANO_KONTROL_STUDIO_DEVICE_ID, NANO_KONTROL_STUDIO_DEVICE_NAME, 0, CursorDeviceFollowMode.FOLLOW_SELECTION);
        cursorDevice.isEnabled().markInterested();
        cursorDevice.isWindowOpen().markInterested();

        controlsPageBank = cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME, 4, "");
        for (int i = 0; i < controlsPageBank.getParameterCount(); i++) {
            RemoteControl parameter = controlsPageBank.getParameter(i);
            parameter.setIndication(true);

            parameter.markInterested();
            parameter.name().markInterested();
            parameter.value().markInterested();
        }

        controls.put(SetButtonControl.BUTTON_ID, new SetButtonControl(controllerContext, transport, trackBank, cursorTrack, cursorDevice, controlsPageBank));
    }

}
