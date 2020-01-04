package ru.utoplov.vladimir.controlset.buttonset;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.AbstractControlSet;
import ru.utoplov.vladimir.controlset.buttonset.device.*;
import ru.utoplov.vladimir.core.ControllerContext;

public class DeviceButtonControlSet extends AbstractControlSet {

    private static final String NANO_KONTROL_STUDIO_DEVICE_ID = "NANO_KONTROL_STUDIO_DEVICE_ID";
    private static final String NANO_KONTROL_STUDIO_DEVICE_NAME = "NANO_KONTROL_STUDIO_DEVICE_NAME";

    private DeviceBank deviceBank;
    private PinnableCursorDevice cursorDevice;

    public DeviceButtonControlSet(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        deviceBank = cursorTrack.createDeviceBank(1);
        deviceBank.canScrollForwards().markInterested();

        cursorDevice = cursorTrack.createCursorDevice(NANO_KONTROL_STUDIO_DEVICE_ID, NANO_KONTROL_STUDIO_DEVICE_NAME, 0, CursorDeviceFollowMode.FOLLOW_SELECTION);
        cursorDevice.name().markInterested();
        cursorDevice.isNested().markInterested();
        cursorDevice.isEnabled().markInterested();
        cursorDevice.isWindowOpen().markInterested();

        controls.put(CycleStateButtonControl.BUTTON_ID, new CycleStateButtonControl(transport, trackBank, cursorTrack, cursorDevice));
        controls.put(SetButtonControl.BUTTON_ID, new SetButtonControl(transport, trackBank, cursorTrack, cursorDevice));
        controls.put(NextDeviceButtonControl.BUTTON_ID, new NextDeviceButtonControl(transport, trackBank, cursorTrack, cursorDevice));
        controls.put(PrevDeviceButtonControl.BUTTON_ID, new PrevDeviceButtonControl(transport, trackBank, cursorTrack, cursorDevice));
//        controls.put(RewindDeviceButtonControl.BUTTON_ID, new RewindDeviceButtonControl(controllerContext, transport, trackBank, cursorTrack, cursorDevice));

        for (int i = MuteButtonControl.BUTTON_ID_FIRST; i <= MuteButtonControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new MuteButtonControl(controllerContext, transport, trackBank, cursorTrack, i - MuteButtonControl.BUTTON_ID_FIRST));
        }
        for (int i = SoloButtonControl.BUTTON_ID_FIRST; i <= SoloButtonControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new SoloButtonControl(controllerContext, transport, trackBank, cursorTrack, i - SoloButtonControl.BUTTON_ID_FIRST));
        }
        for (int i = SelectButtonControl.BUTTON_ID_FIRST; i <= SelectButtonControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new SelectButtonControl(controllerContext, transport, trackBank, cursorTrack, i - SelectButtonControl.BUTTON_ID_FIRST));
        }
    }

}
