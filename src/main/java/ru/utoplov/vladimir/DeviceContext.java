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

        states.put(StateControlSet.BUTTON_CYCLE_STATE, false);
        states.put(StateControlSet.BUTTON_SET_STATE, false);
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

    public void ledON(int buttonID) {
        sendCC(buttonID, 0);
    }

    public void ledOFF(int buttonID) {
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
