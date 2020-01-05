package ru.utoplov.vladimir.controlset.buttonset;

import com.bitwig.extension.controller.api.Track;
import ru.utoplov.vladimir.controlset.AbstractControlSet;
import ru.utoplov.vladimir.controlset.buttonset.mix.*;
import ru.utoplov.vladimir.core.ControllerContext;

public class MixButtonControlSet extends AbstractControlSet {

    public MixButtonControlSet(ControllerContext cc) {
        super(cc);

        for (int i = 0; i < cc.trackBank.getSizeOfBank(); i++) {
            Track track = cc.trackBank.getItemAt(i);
            track.mute().markInterested();
            track.solo().markInterested();
        }

        controls.put(BackwardButtonControl.BUTTON_ID, new BackwardButtonControl(cc));
        controls.put(ForwardButtonControl.BUTTON_ID, new ForwardButtonControl(cc));

        controls.put(RewindButtonControl.BUTTON_ID, new RewindButtonControl(cc));
        controls.put(StopButtonControl.BUTTON_ID, new StopButtonControl(cc));
        controls.put(PlayButtonControl.BUTTON_ID, new PlayButtonControl(cc));
        controls.put(RecordButtonControl.BUTTON_ID, new RecordButtonControl(cc));

        controls.put(PrevSendBankButtonControl.BUTTON_ID, new PrevSendBankButtonControl(cc));
        controls.put(NextSendBankButtonControl.BUTTON_ID, new NextSendBankButtonControl(cc));

        controls.put(PrevTrackBankButtonControl.BUTTON_ID, new PrevTrackBankButtonControl(cc));
        controls.put(NextTrackBankButtonControl.BUTTON_ID, new NextTrackBankButtonControl(cc));

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
