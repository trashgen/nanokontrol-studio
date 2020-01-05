package ru.utoplov.vladimir.controlset.continuousset.mix;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.SendBank;
import ru.utoplov.vladimir.controlset.continuousset.ContinuousControl;
import ru.utoplov.vladimir.controlset.stateset.MixStateControlSet;
import ru.utoplov.vladimir.core.ControllerContext;

public class WheelBackwardControl extends ContinuousControl {

    public final static int BUTTON_ID = 85;

    public WheelBackwardControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic(ShortMidiMessage msg) {
        int trackRecordPressedIndex = cc.getTrackRecordPressed();
        if (trackRecordPressedIndex == MixStateControlSet.BUTTON_TRACK_RECORD_STATE_NOT_PRESSED) {
            if (cc.isCycleToggleStateActive()) {
                cc.cursorTrack.volume().value().inc(-1, 512);
            } else if (cc.isSetPressed()) {
                cc.ArrangementPosition -= 4;
                cc.transport.setPosition(cc.ArrangementPosition);
            } else {
                cc.ArrangementPosition--;
                cc.transport.setPosition(cc.ArrangementPosition);
            }
        } else {
            SendBank sendBank = cc.cursorTrack.sendBank();
            if (sendBank != null) {
                sendBank.getItemAt(trackRecordPressedIndex).value().inc(-1, 512);
            }
        }
    }
}
