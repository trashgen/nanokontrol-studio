package ru.utoplov.vladimir;

import com.bitwig.extension.controller.api.MidiOut;
import ru.utoplov.vladimir.buttons.StateControlSet;

import java.util.HashMap;
import java.util.Map;

public class DeviceContext {

    private static final int MIDI_CC_EVENT_ID = 0xB0;

    public double ArrangementPosition;

    private MidiOut midiOut;
    private final Map<Integer, Boolean> states = new HashMap<>();

    public DeviceContext(MidiOut midiOut) {
        this.midiOut = midiOut;

        states.put(StateControlSet.BUTTON_SET_STATE, false);
        states.put(StateControlSet.BUTTON_CYCLE_STATE, false);
        states.put(StateControlSet.BUTTON_TRACK_RECORD_STATE_1, false);
        states.put(StateControlSet.BUTTON_TRACK_RECORD_STATE_2, false);
        states.put(StateControlSet.BUTTON_TRACK_RECORD_STATE_3, false);
        states.put(StateControlSet.BUTTON_TRACK_RECORD_STATE_4, false);
        states.put(StateControlSet.BUTTON_TRACK_RECORD_STATE_5, false);
        states.put(StateControlSet.BUTTON_TRACK_RECORD_STATE_6, false);
        states.put(StateControlSet.BUTTON_TRACK_RECORD_STATE_7, false);
        states.put(StateControlSet.BUTTON_TRACK_RECORD_STATE_8, false);
    }

    public void updateStateControl(int buttonID, boolean state) {
        states.put(buttonID, state);
    }

    public boolean isCycleToggleStateActive() {
        return states.get(StateControlSet.BUTTON_CYCLE_STATE);
    }

    public boolean isSetPressed() {
        return states.get(StateControlSet.BUTTON_SET_STATE);
    }

    public int getTrackRecordPressed(int buttonID) {
        // TODO : Rework !
        Boolean state = states.get(buttonID);
        if (state != null && state) {
            return buttonID - StateControlSet.BUTTON_TRACK_RECORD_STATE_1;
        }
        return StateControlSet.BUTTON_TRACK_RECORD_STATE_NOT_PRESSED;


//        if ( != null) {
//            return 0;
//        }
//        if (states.get(StateControlSet.BUTTON_TRACK_RECORD_STATE_2) != null) {
//            return 1;
//        }
//        if (states.get(StateControlSet.BUTTON_TRACK_RECORD_STATE_3) != null) {
//            return 2;
//        }
//        if (states.get(StateControlSet.BUTTON_TRACK_RECORD_STATE_4) != null) {
//            return 3;
//        }
//        if (states.get(StateControlSet.BUTTON_TRACK_RECORD_STATE_5) != null) {
//            return 4;
//        }
//        if (states.get(StateControlSet.BUTTON_TRACK_RECORD_STATE_6) != null) {
//            return 5;
//        }
//        if (states.get(StateControlSet.BUTTON_TRACK_RECORD_STATE_7) != null) {
//            return 6;
//        }
//        if (states.get(StateControlSet.BUTTON_TRACK_RECORD_STATE_8) != null) {
//            return 7;
//        }
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

    private void sendCC(int buttonID, int value) {
        midiOut.sendMidi(MIDI_CC_EVENT_ID, buttonID, value);
    }

}
