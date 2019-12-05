package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;
import ru.utoplov.vladimir.buttons.StateControlSet;

class WheelForwardControl extends ContinuousControl {

    final static int BUTTON_ID = 83;

    private Transport transport;

    private DeviceContext deviceContext;

    WheelForwardControl(DeviceContext deviceContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(trackBank, cursorTrack);
        this.transport = transport;
        this.deviceContext = deviceContext;
    }

    @Override
    void logic(ShortMidiMessage msg) {
        int trackRecordPressedIndex = deviceContext.getTrackRecordPressed();
        if (trackRecordPressedIndex == StateControlSet.BUTTON_TRACK_RECORD_STATE_NOT_PRESSED) {
            if (deviceContext.isCycleToggleStateActive()) {
                cursorTrack.volume().value().inc(1, 512);
            } else if (deviceContext.isSetPressed()) {
                deviceContext.ArrangementPosition += 4;
                transport.setPosition(deviceContext.ArrangementPosition);
            } else {
                deviceContext.ArrangementPosition++;
                transport.setPosition(deviceContext.ArrangementPosition);
            }
        } else {
            SendBank sendBank = cursorTrack.sendBank();
            if (sendBank != null) {
                cursorTrack.sendBank().getItemAt(trackRecordPressedIndex).value().inc(1, 512);
            }
        }
    }
}
