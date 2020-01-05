package ru.utoplov.vladimir.core;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.stateset.MixStateControlSet;

import java.util.HashMap;
import java.util.Map;

public class ControllerContext {

    private static final int MIDI_CC_EVENT_ID = 0xB0;

    private static final String NANO_KONTROL_STUDIO_DEVICE_ID = "NANO_KONTROL_STUDIO_DEVICE_ID";
    private static final String NANO_KONTROL_STUDIO_DEVICE_NAME = "NANO_KONTROL_STUDIO_DEVICE_NAME";

    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE";
    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO";

    private MidiOut midiOut;
    private final Map<Integer, Boolean> states = new HashMap<>();

    public Transport transport;
    public TrackBank trackBank;
    public CursorTrack cursorTrack;
    public PinnableCursorDevice cursorDevice;
    public CursorRemoteControlsPage controlsOne;
    public CursorRemoteControlsPage controlsTwo;
    public double ArrangementPosition;

    public ControllerContext(MidiOut midiOut, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        this.midiOut = midiOut;
        this.transport = transport;
        this.trackBank = trackBank;
        this.cursorTrack = cursorTrack;

        cursorDevice = cursorTrack.createCursorDevice(NANO_KONTROL_STUDIO_DEVICE_ID, NANO_KONTROL_STUDIO_DEVICE_NAME, 0, CursorDeviceFollowMode.FOLLOW_SELECTION);
        cursorDevice.name().markInterested();
        cursorDevice.isNested().markInterested();
        cursorDevice.isEnabled().markInterested();
        cursorDevice.isWindowOpen().markInterested();

        controlsOne = cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE, 8, "trashgen_1");
        for (int i = 0; i < controlsOne.getParameterCount(); i++) {
            RemoteControl parameter = controlsOne.getParameter(i);
            parameter.markInterested();
            parameter.name().markInterested();
            parameter.value().markInterested();
        }

        controlsTwo = cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO, 8, "trashgen_2");
        for (int i = 0; i < controlsTwo.getParameterCount(); i++) {
            RemoteControl parameter = controlsTwo.getParameter(i);
            parameter.markInterested();
            parameter.name().markInterested();
            parameter.value().markInterested();
        }

        init();
    }

    public void resetState() {
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

    private void init() {
        resetState();

        transport.isPlaying().markInterested();
        transport.getPosition().markInterested();
        transport.getInPosition().markInterested();
        transport.isArrangerRecordEnabled().markInterested();

        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Parameter parameter = trackBank.getItemAt(i).volume();
            parameter.markInterested();
            parameter.setIndication(true);
        }

        for (int i = 0; i < cursorTrack.sendBank().getSizeOfBank(); i++) {
            Send send = cursorTrack.sendBank().getItemAt(i);
            send.markInterested();
            send.setIndication(true);
            send.value().markInterested();
        }

    }
}
