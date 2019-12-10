package ru.utoplov.vladimir.core;

import com.bitwig.extension.controller.api.MidiOut;
import ru.utoplov.vladimir.controlset.stateset.MixStateControlSet;

import java.util.HashMap;
import java.util.Map;

public class ControllerContext {

    private static final int MIDI_CC_EVENT_ID = 0xB0;

    public double ArrangementPosition;

    private MidiOut midiOut;
    private final Map<Integer, Boolean> states = new HashMap<>();

    public ControllerContext(MidiOut midiOut) {
        this.midiOut = midiOut;

        states.put(MixStateControlSet.BUTTON_SET_STATE, false);
        states.put(MixStateControlSet.BUTTON_CYCLE_STATE, false);
        states.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_1, false);
        states.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_2, false);
        states.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_3, false);
        states.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_4, false);
        states.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_5, false);
        states.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_6, false);
        states.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_7, false);
        states.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_8, false);
    }

    public void updateStateControl(int buttonID, boolean state) {
        states.put(buttonID, state);
    }

    public boolean isCycleToggleStateActive() {
        return states.get(MixStateControlSet.BUTTON_CYCLE_STATE);
    }

    public boolean isSetPressed() {
        return states.get(MixStateControlSet.BUTTON_SET_STATE);
    }

    public int getTrackRecordPressed() {
        if (states.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_1)) {
            return 0;
        }
        if (states.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_2)) {
            return 1;
        }
        if (states.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_3)) {
            return 2;
        }
        if (states.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_4)) {
            return 3;
        }
        if (states.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_5)) {
            return 4;
        }
        if (states.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_6)) {
            return 5;
        }
        if (states.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_7)) {
            return 6;
        }
        if (states.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_8)) {
            return 7;
        }
        return MixStateControlSet.BUTTON_TRACK_RECORD_STATE_NOT_PRESSED;
    }

    public void ledOFF(int buttonID) {
        sendCC(buttonID, 0);
    }

    public void ledON(int buttonID) {
        sendCC(buttonID, 127);
    }

    public void toggleLED(int buttonID, boolean isOFF) {
        if (isOFF) {
            sendCC(buttonID, 0);
        } else {
            sendCC(buttonID, 127);
        }
    }

    public void sendSysex(final String data) {
        midiOut.sendSysex(data);
    }

    private void sendCC(int buttonID, int value) {
        midiOut.sendMidi(MIDI_CC_EVENT_ID, buttonID, value);
    }

}
