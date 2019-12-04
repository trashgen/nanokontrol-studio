package ru.utoplov.vladimir;

import com.bitwig.extension.controller.api.MidiOut;

public class DeviceContext {

    public double ArrangementPosition;

    private MidiOut midiOut;

    public DeviceContext(MidiOut midiOut) {
        this.midiOut = midiOut;
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
        midiOut.sendMidi(0xB0, buttonID, value);
    }

}
