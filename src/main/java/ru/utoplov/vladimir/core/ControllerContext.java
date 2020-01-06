package ru.utoplov.vladimir.core;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.stateset.MixStateControlSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerContext {

    public class ActiveRemote {
        public CursorRemoteControlsPage First;
        public CursorRemoteControlsPage Second;

        ActiveRemote(CursorRemoteControlsPage first, CursorRemoteControlsPage second) {
            First = first;
            Second = second;
            for (int i = 0; i < First.getParameterCount(); i++) {
                RemoteControl parameter = First.getParameter(i);
                parameter.markInterested();
                parameter.name().markInterested();
                parameter.value().markInterested();
            }
            for (int i = 0; i < Second.getParameterCount(); i++) {
                RemoteControl parameter = Second.getParameter(i);
                parameter.markInterested();
                parameter.name().markInterested();
                parameter.value().markInterested();
            }
        }
    }

    private static final int MIDI_CC_EVENT_ID = 0xB0;

    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_ONE = "trashgen_1";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_TWO = "trashgen_2";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_THREE = "trashgen_3";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_FOUR = "trashgen_4";

    private static final String NANO_KONTROL_STUDIO_DEVICE_ID = "NANO_KONTROL_STUDIO_DEVICE_ID";
    private static final String NANO_KONTROL_STUDIO_DEVICE_NAME = "NANO_KONTROL_STUDIO_DEVICE_NAME";

    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE";
    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO";

    private MidiOut midiOut;
    private final Map<Integer, Boolean> controlStates = new HashMap<>();

    public ActiveRemote remote;
    private int currentRemoteSetID = -1;
    private final List<ActiveRemote> remotes = new ArrayList<>();

    public Transport transport;
    public TrackBank trackBank;
    public DeviceBank deviceBank;
    public CursorTrack cursorTrack;
    public PinnableCursorDevice cursorDevice;
    public double ArrangementPosition;

    public ControllerContext(MidiOut midiOut, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        this.midiOut = midiOut;
        this.transport = transport;
        this.trackBank = trackBank;
        this.cursorTrack = cursorTrack;

        init();
        setRemoteControls();
    }

    public void setRemoteControls() {
        currentRemoteSetID = 0;
        remote = remotes.get(currentRemoteSetID);
    }

    public void nextRemoteControls() {
        ++currentRemoteSetID;
        if (currentRemoteSetID == remotes.size()) {
            currentRemoteSetID = 0;
        }
        remote = remotes.get(currentRemoteSetID);
    }

    public void prevRemoteControls() {
        --currentRemoteSetID;
        if (currentRemoteSetID < 0) {
            currentRemoteSetID = remotes.size() - 1;
        }
        remote = remotes.get(currentRemoteSetID);
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

        cursorDevice = cursorTrack.createCursorDevice(NANO_KONTROL_STUDIO_DEVICE_ID, NANO_KONTROL_STUDIO_DEVICE_NAME, 0, CursorDeviceFollowMode.FOLLOW_SELECTION);
        cursorDevice.name().markInterested();
        cursorDevice.isNested().markInterested();
        cursorDevice.isEnabled().markInterested();
        cursorDevice.isWindowOpen().markInterested();

        deviceBank = cursorTrack.createDeviceBank(32);
//        for (int i = 0; i < deviceBank.getSizeOfBank(); i++) {
//            deviceBank.getDevice(i).
//        }

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

        remotes.add(new ActiveRemote(
                cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE, 8, NANO_KONTROL_STUDIO_REMOTE_TAG_ONE),
                cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO, 8, NANO_KONTROL_STUDIO_REMOTE_TAG_TWO)
        ));
        remotes.add(new ActiveRemote(
                cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE, 8, NANO_KONTROL_STUDIO_REMOTE_TAG_THREE),
                cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO, 8, NANO_KONTROL_STUDIO_REMOTE_TAG_FOUR)
        ));
    }

}
