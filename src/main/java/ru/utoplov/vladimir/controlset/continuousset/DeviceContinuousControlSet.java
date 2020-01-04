package ru.utoplov.vladimir.controlset.continuousset;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.AbstractControlSet;
import ru.utoplov.vladimir.controlset.continuousset.device.FaderControl;
import ru.utoplov.vladimir.controlset.continuousset.device.KnobControl;
import ru.utoplov.vladimir.controlset.continuousset.device.WheelBackwardControl;
import ru.utoplov.vladimir.controlset.continuousset.device.WheelForwardControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class DeviceContinuousControlSet extends AbstractControlSet {

    private static final String NANO_KONTROL_STUDIO_DEVICE_ID = "NANO_KONTROL_STUDIO_DEVICE_ID";
    private static final String NANO_KONTROL_STUDIO_DEVICE_NAME = "NANO_KONTROL_STUDIO_DEVICE_NAME";

    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE";
    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO";

    private CursorRemoteControlsPage controlsOne;
    private CursorRemoteControlsPage controlsTwo;

    public DeviceContinuousControlSet(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        transport.getPosition().markInterested();

        PinnableCursorDevice cursorDevice = cursorTrack.createCursorDevice(NANO_KONTROL_STUDIO_DEVICE_ID, NANO_KONTROL_STUDIO_DEVICE_NAME, 0, CursorDeviceFollowMode.FOLLOW_SELECTION);
        cursorDevice.name().markInterested();
        cursorDevice.isNested().markInterested();
        cursorDevice.isEnabled().markInterested();
        cursorDevice.isWindowOpen().markInterested();

        controlsOne = cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE, 8, "trashgen_1");
        for (int i = 0; i < controlsOne.getParameterCount(); i++) {
            RemoteControl parameter = controlsOne.getParameter(i);
            parameter.markInterested();
            parameter.name().markInterested();
            parameter.value().markInterested();
        }

        controlsTwo = cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO, 8, "trashgen_2");
        for (int i = 0; i < controlsTwo.getParameterCount(); i++) {
            RemoteControl parameter = controlsTwo.getParameter(i);
            parameter.markInterested();
            parameter.name().markInterested();
            parameter.value().markInterested();
        }

        controls.put(WheelBackwardControl.BUTTON_ID, new WheelBackwardControl(controllerContext, transport, trackBank, cursorTrack));
        controls.put(WheelForwardControl.BUTTON_ID, new WheelForwardControl(controllerContext, transport, trackBank, cursorTrack));

        for (int i = KnobControl.BUTTON_ID_FIRST; i <= KnobControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new KnobControl(controllerContext, controlsOne, transport, trackBank, cursorTrack, i - KnobControl.BUTTON_ID_FIRST));
        }
        for (int i = FaderControl.BUTTON_ID_FIRST; i <= FaderControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new FaderControl(controllerContext, controlsTwo, transport, trackBank, cursorTrack, i - FaderControl.BUTTON_ID_FIRST));
        }
    }

    @Override
    public void updateIndication(boolean enabled) {
        for (int i = 0; i < controlsOne.getParameterCount(); i++) {
            RemoteControl parameter = controlsOne.getParameter(i);
            parameter.setIndication(enabled);
        }
        for (int i = 0; i < controlsTwo.getParameterCount(); i++) {
            RemoteControl parameter = controlsTwo.getParameter(i);
            parameter.setIndication(enabled);
        }
    }

}
