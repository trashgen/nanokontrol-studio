package ru.utoplov.vladimir.buttons.continuousset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.ButtonSet;
import ru.utoplov.vladimir.DeviceControlContext;

import java.util.HashMap;
import java.util.Map;

public class ContinuousControlSet implements ButtonSet {

    private final TrackBank trackBank;
    private final Transport transport;
    private final CursorTrack cursorTrack;

    private final Map<Integer, ContinuousControl> controls = new HashMap<>();

    public ContinuousControlSet(Transport transport, TrackBank trackBank, CursorTrack cursorTrack, DeviceControlContext deviceControlContext) {
        this.trackBank = trackBank;
        this.transport = transport;
        this.cursorTrack = cursorTrack;

        transport.getPosition().markInterested();

        for (int i = 0; i < this.cursorTrack.sendBank().getSizeOfBank(); i++) {
            Send send = this.cursorTrack.sendBank().getItemAt(i);
            send.markInterested();
            send.setIndication(true);
            send.value().markInterested();
        }

        for (int i = 0; i < this.trackBank.getSizeOfBank(); i++) {
            Parameter parameter = trackBank.getItemAt(i).volume();
            parameter.markInterested();
            parameter.setIndication(true);
        }

        controls.put(BUTTON_WHEEL_BACKWARD, new WheelBackwardControl(transport, trackBank, cursorTrack, deviceControlContext));
        controls.put(BUTTON_WHEEL_FORWARD, new WheelForwardControl(transport, trackBank, cursorTrack, deviceControlContext));

        for (int i = BUTTON_FADER_1; i <= BUTTON_FADER_8; i++) {
            controls.put(i, new FaderControl(trackBank, cursorTrack, i - BUTTON_FADER_1));
        }
        for (int i = BUTTON_KNOB_1; i <= BUTTON_KNOB_8; i++) {
            controls.put(i, new KnobControl(trackBank, cursorTrack, i - BUTTON_KNOB_1));
        }
    }

    @Override
    public boolean isValid(ShortMidiMessage msg) {
        return controls.keySet().stream().anyMatch(code -> msg.getData1() == code);
    }

    @Override
    public boolean execute(ShortMidiMessage msg) {
        ContinuousControl button = controls.get(msg.getData1());
        button.execute(msg);
        return true;
    }

    private final static int BUTTON_FADER_1 = 2;
    private final static int BUTTON_FADER_8 = 9;

    private final static int BUTTON_KNOB_1 = 13;
    private final static int BUTTON_KNOB_8 = 20;

    private final static int BUTTON_WHEEL = 86;
    private final static int BUTTON_WHEEL_BACKWARD = 85;
    private final static int BUTTON_WHEEL_FORWARD = 83;

}
