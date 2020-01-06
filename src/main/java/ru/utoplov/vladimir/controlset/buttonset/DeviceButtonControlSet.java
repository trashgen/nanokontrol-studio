package ru.utoplov.vladimir.controlset.buttonset;

import ru.utoplov.vladimir.controlset.AbstractControlSet;
import ru.utoplov.vladimir.controlset.buttonset.common.*;
import ru.utoplov.vladimir.controlset.buttonset.device.*;
import ru.utoplov.vladimir.core.ControllerContext;

public class DeviceButtonControlSet extends AbstractControlSet {

    public DeviceButtonControlSet(ControllerContext cc) {
        super(cc);

//        controls.put(SetButtonControl.BUTTON_ID, new SetButtonControl(cc));
        controls.put(RewindDeviceButtonControl.BUTTON_ID, new RewindDeviceButtonControl(cc));

        controls.put(PrevMarkerButtonControl.BUTTON_ID, new PrevMarkerButtonControl(cc));
        controls.put(NextMarkerButtonControl.BUTTON_ID, new NextMarkerButtonControl(cc));
        controls.put(PrevTrackButtonControl.BUTTON_ID, new PrevTrackButtonControl(cc));
        controls.put(NextTrackButtonControl.BUTTON_ID, new NextTrackButtonControl(cc));

        controls.put(StopButtonControl.BUTTON_ID, new StopButtonControl(cc));
        controls.put(PlayButtonControl.BUTTON_ID, new PlayButtonControl(cc));
        controls.put(RecordButtonControl.BUTTON_ID, new RecordButtonControl(cc));

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
