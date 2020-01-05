package ru.utoplov.vladimir.controlset.buttonset;

import ru.utoplov.vladimir.controlset.AbstractControlSet;
import ru.utoplov.vladimir.controlset.buttonset.device.*;
import ru.utoplov.vladimir.core.ControllerContext;

public class DeviceButtonControlSet extends AbstractControlSet {

    public DeviceButtonControlSet(ControllerContext cc) {
        super(cc);

        controls.put(SetButtonControl.BUTTON_ID, new SetButtonControl(cc));
        controls.put(RewindDeviceButtonControl.BUTTON_ID, new RewindDeviceButtonControl(cc));

        for (int i = MuteButtonControl.BUTTON_ID_FIRST; i <= MuteButtonControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new MuteButtonControl(cc, i - MuteButtonControl.BUTTON_ID_FIRST));
        }
        for (int i = SoloButtonControl.BUTTON_ID_FIRST; i <= SoloButtonControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new SoloButtonControl(cc, i - SoloButtonControl.BUTTON_ID_FIRST));
        }
        for (int i = SelectButtonControl.BUTTON_ID_FIRST; i <= SelectButtonControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new SelectButtonControl(cc, i - SelectButtonControl.BUTTON_ID_FIRST));
        }
    }

}
