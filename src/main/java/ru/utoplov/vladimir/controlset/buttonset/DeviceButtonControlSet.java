package ru.utoplov.vladimir.controlset.buttonset;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.AbstractControlSet;
import ru.utoplov.vladimir.controlset.buttonset.device.NextDeviceButtonControl;
import ru.utoplov.vladimir.controlset.buttonset.device.PrevDeviceButtonControl;
import ru.utoplov.vladimir.controlset.buttonset.device.RewindDeviceButtonControl;
import ru.utoplov.vladimir.controlset.buttonset.device.SetButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class DeviceButtonControlSet extends AbstractControlSet {

    private static final String NANO_KONTROL_STUDIO_DEVICE_ID = "NANO_KONTROL_STUDIO_DEVICE_ID";
    private static final String NANO_KONTROL_STUDIO_DEVICE_NAME = "NANO_KONTROL_STUDIO_DEVICE_NAME";
    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME";

    private DeviceBank deviceBank;
    private PinnableCursorDevice cursorDevice;
    private CursorRemoteControlsPage controlsPageBank;

    public DeviceButtonControlSet(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        deviceBank = cursorTrack.createDeviceBank(1);
        deviceBank.canScrollForwards().markInterested();

        cursorDevice = cursorTrack.createCursorDevice(NANO_KONTROL_STUDIO_DEVICE_ID, NANO_KONTROL_STUDIO_DEVICE_NAME, 0, CursorDeviceFollowMode.FOLLOW_SELECTION);
        cursorDevice.name().markInterested();
        cursorDevice.isNested().markInterested();
        cursorDevice.isEnabled().markInterested();
        cursorDevice.isWindowOpen().markInterested();

//        cursorDevice.addDirectParameterIdObserver(names -> {
//            for (int i = 0; i < names.length; i++) {
//                String name = names[i];
//                name.isEmpty();
//            }
//        });

        controlsPageBank = cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME, 8, "");
        for (int i = 0; i < controlsPageBank.getParameterCount(); i++) {
            RemoteControl parameter = controlsPageBank.getParameter(i);
            parameter.markInterested();
            parameter.name().markInterested();
            parameter.value().markInterested();
        }

        controls.put(SetButtonControl.BUTTON_ID, new SetButtonControl(transport, trackBank, cursorTrack, cursorDevice));
        controls.put(NextDeviceButtonControl.BUTTON_ID, new NextDeviceButtonControl(transport, trackBank, cursorTrack, cursorDevice));
        controls.put(PrevDeviceButtonControl.BUTTON_ID, new PrevDeviceButtonControl(transport, trackBank, cursorTrack, cursorDevice));
        controls.put(RewindDeviceButtonControl.BUTTON_ID, new RewindDeviceButtonControl(transport, trackBank, cursorTrack, cursorDevice, controlsPageBank));
    }

    @Override
    public void updateIndication(boolean enabled) {
        for (int i = 0; i < controlsPageBank.getParameterCount(); i++) {
            RemoteControl parameter = controlsPageBank.getParameter(i);
            parameter.setIndication(enabled);
        }
    }

}
