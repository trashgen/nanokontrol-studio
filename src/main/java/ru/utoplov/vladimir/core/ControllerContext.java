package ru.utoplov.vladimir.core;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.stateset.MixStateControlSet;
import ru.utoplov.vladimir.view.Scene;

import java.util.HashMap;
import java.util.Map;

public class ControllerContext {

    private static final int MIDI_CC_EVENT_ID = 0xB0;

    private static final String NANO_KONTROL_STUDIO_CURSOR_ID = "NANO_KONTROL_STUDIO_CURSOR_ID";
    private static final String NANO_KONTROL_STUDIO_CURSOR_NAME = "NANO_KONTROL_STUDIO_CURSOR_NAME";

    private static final String NANO_KONTROL_STUDIO_DEVICE_ID = "NANO_KONTROL_STUDIO_DEVICE_ID";
    private static final String NANO_KONTROL_STUDIO_DEVICE_NAME = "NANO_KONTROL_STUDIO_DEVICE_NAME";

    private MidiOut midiOut;
    private final Map<Integer, Boolean> controlStates = new HashMap<>();

    private ControllerHost host;

    public Scene scene;
    public Transport transport;
    public TrackBank trackBank;
    public CursorTrack cursorTrack;
    public RemoteControl remote;
    public PinnableCursorDevice cursorDevice;
    public double ArrangementPosition;

    public ControllerContext(ControllerHost host) {
        this.host = host;
        midiOut = host.getMidiOutPort(0);
        transport = host.createTransport();
        trackBank = host.createTrackBank(8, 0, 0, true);
        cursorTrack = host.createCursorTrack(NANO_KONTROL_STUDIO_CURSOR_ID, NANO_KONTROL_STUDIO_CURSOR_NAME, 8, 0, true);
        cursorDevice = cursorTrack.createCursorDevice(NANO_KONTROL_STUDIO_DEVICE_ID, NANO_KONTROL_STUDIO_DEVICE_NAME, 0, CursorDeviceFollowMode.FOLLOW_SELECTION);

        init();
    }

    public void showMappingInfo() {
        String pattern = "Mapping for ['%s' : '%s'] -> '%s'";
        String trackName = cursorTrack.name().get();
        String deviceName = cursorDevice.name().get();
        String result = String.format(pattern, trackName, deviceName, remote.getName() != null ? remote.getName() : "Not any nanoKONTROL pages");
        host.showPopupNotification(result);
    }

    public void resetState() {
        controlStates.put(MixStateControlSet.BUTTON_SET_STATE, false);
        controlStates.put(MixStateControlSet.BUTTON_CYCLE_STATE, false);
        controlStates.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_1, false);
        controlStates.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_2, false);
        controlStates.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_3, false);
        controlStates.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_4, false);
        controlStates.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_5, false);
        controlStates.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_6, false);
        controlStates.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_7, false);
        controlStates.put(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_8, false);
    }

    public void updateStateControl(int buttonID, boolean state) {
        controlStates.put(buttonID, state);
    }

    public boolean isCycleToggleStateActive() {
        return controlStates.get(MixStateControlSet.BUTTON_CYCLE_STATE);
    }

    public boolean isSetPressed() {
        return controlStates.get(MixStateControlSet.BUTTON_SET_STATE);
    }

    public int getTrackRecordPressed() {
        if (controlStates.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_1)) {
            return 0;
        }
        if (controlStates.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_2)) {
            return 1;
        }
        if (controlStates.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_3)) {
            return 2;
        }
        if (controlStates.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_4)) {
            return 3;
        }
        if (controlStates.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_5)) {
            return 4;
        }
        if (controlStates.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_6)) {
            return 5;
        }
        if (controlStates.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_7)) {
            return 6;
        }
        if (controlStates.get(MixStateControlSet.BUTTON_TRACK_RECORD_STATE_8)) {
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

    private void sendCC(int buttonID, int value) {
        midiOut.sendMidi(MIDI_CC_EVENT_ID, buttonID, value);
    }

    private void init() {
        resetState();

        remote = new RemoteControl(cursorDevice);
        trackBank.followCursorTrack(cursorTrack);

        cursorTrack.name().markInterested();
        cursorTrack.isPinned().markInterested();

        transport.isPlaying().markInterested();
        transport.getPosition().markInterested();
        transport.getInPosition().markInterested();
        transport.isArrangerRecordEnabled().markInterested();

        cursorDevice.name().markInterested();
        cursorDevice.isNested().markInterested();
        cursorDevice.isEnabled().markInterested();
        cursorDevice.isWindowOpen().markInterested();

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
