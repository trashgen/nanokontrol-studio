package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.ButtonSet;
import ru.utoplov.vladimir.DeviceContext;

import java.util.HashMap;
import java.util.Map;

public class ContinuousControlSet implements ButtonSet {

    private final Map<Integer, ContinuousControl> controls = new HashMap<>();

    public ContinuousControlSet(Transport transport, TrackBank trackBank, CursorTrack cursorTrack, DeviceContext deviceContext) {
        transport.getPosition().markInterested();

        for (int i = 0; i < cursorTrack.sendBank().getSizeOfBank(); i++) {
            Send send = cursorTrack.sendBank().getItemAt(i);
            send.markInterested();
            send.setIndication(true);
            send.value().markInterested();
        }

        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Parameter parameter = trackBank.getItemAt(i).volume();
            parameter.markInterested();
            parameter.setIndication(true);
        }

        controls.put(WheelBackwardControl.BUTTON_ID, new WheelBackwardControl(deviceContext, transport, trackBank, cursorTrack));
        controls.put(WheelForwardControl.BUTTON_ID, new WheelForwardControl(deviceContext, transport, trackBank, cursorTrack));

        for (int i = FaderControl.BUTTON_ID_FIRST; i <= FaderControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new FaderControl(trackBank, cursorTrack, i - FaderControl.BUTTON_ID_FIRST));
        }
        for (int i = KnobControl.BUTTON_ID_FIRST; i <= KnobControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new KnobControl(trackBank, cursorTrack, i - KnobControl.BUTTON_ID_FIRST));
        }
    }

    @Override
    public boolean isValid(ShortMidiMessage msg) {
        return controls.get(msg.getData1()) != null;
    }

    @Override
    public boolean execute(ShortMidiMessage msg) {
        ContinuousControl button = controls.get(msg.getData1());
        if (button != null) {
            button.execute(msg);
            return true;
        }
        return false;
    }

}
