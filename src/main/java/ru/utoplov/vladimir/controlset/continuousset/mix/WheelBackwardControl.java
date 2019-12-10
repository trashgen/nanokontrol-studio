package ru.utoplov.vladimir.controlset.continuousset.mix;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.stateset.MixStateControlSet;
import ru.utoplov.vladimir.core.ControllerContext;

class WheelBackwardControl extends ContinuousControl {

    final static int BUTTON_ID = 85;

    private ControllerContext controllerContext;

    WheelBackwardControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        int trackRecordPressedIndex = controllerContext.getTrackRecordPressed();
        if (trackRecordPressedIndex == MixStateControlSet.BUTTON_TRACK_RECORD_STATE_NOT_PRESSED) {
            if (controllerContext.isCycleToggleStateActive()) {
                cursorTrack.volume().value().inc(-1, 512);
            } else if (controllerContext.isSetPressed()) {
                controllerContext.ArrangementPosition -= 4;
                transport.setPosition(controllerContext.ArrangementPosition);
            } else {
                controllerContext.ArrangementPosition--;
                transport.setPosition(controllerContext.ArrangementPosition);
            }
        } else {
            SendBank sendBank = cursorTrack.sendBank();
            if (sendBank != null) {
                sendBank.getItemAt(trackRecordPressedIndex).value().inc(-1, 512);
            }
        }
    }
}
