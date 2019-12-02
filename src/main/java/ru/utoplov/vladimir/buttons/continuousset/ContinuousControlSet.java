package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.Parameter;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import ru.utoplov.vladimir.ButtonSet;

import java.util.HashMap;
import java.util.Map;

public class ContinuousControlSet implements ButtonSet {

    private final TrackBank trackBank;
    private final CursorTrack cursorTrack;

    private final Map<Integer, ContinuousControl> buttons = new HashMap<>();

    public ContinuousControlSet(TrackBank trackBank, CursorTrack cursorTrack) {
        this.trackBank = trackBank;
        this.cursorTrack = cursorTrack;

        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Track track = trackBank.getItemAt(i);
//            for (int j = 0; j < track.sendBank().getSizeOfBank(); j++) {
//                track.sendBank().getItemAt(j).markInterested();
//                track.sendBank().getItemAt(j).setIndication(true);
//                Send send = track.sendBank().getItemAt(j);
//                send.sendMode().
//            }

            Parameter parameter = trackBank.getItemAt(i).volume();
            parameter.markInterested();
            parameter.setIndication(true);
        }

        for (int i = BUTTON_FADER_1; i <= BUTTON_FADER_8; i++) {
            buttons.put(i, new FaderControl(trackBank, cursorTrack, i - BUTTON_FADER_1));
        }
        for (int i = BUTTON_KNOB_1; i <= BUTTON_KNOB_8; i++) {
            buttons.put(i, new KnobControl(trackBank, cursorTrack, i - BUTTON_KNOB_1));
        }
    }

    @Override
    public boolean isValid(ShortMidiMessage msg) {
        return false;
    }

    @Override
    public boolean execute(ShortMidiMessage msg) {
        return false;
    }

    private final static int BUTTON_FADER_1 = 2;
    private final static int BUTTON_FADER_8 = 9;

    private final static int BUTTON_KNOB_1 = 13;
    private final static int BUTTON_KNOB_8 = 20;

}
