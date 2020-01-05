package ru.utoplov.vladimir.controlset.continuousset.device;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.controlset.continuousset.ContinuousControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class FaderControl extends ContinuousControl {

    public final static int BUTTON_ID_FIRST = 2;
    public final static int BUTTON_ID_LAST = 9;

    private int index;
    private CursorRemoteControlsPage controls;
    private ControllerContext controllerContext;

    public FaderControl(ControllerContext controllerContext, CursorRemoteControlsPage controls, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(transport, trackBank, cursorTrack);
        this.index = index;
        this.controls = controls;
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic(ShortMidiMessage msg) {
        if (controllerContext.isCycleToggleStateActive()) {
            RemoteControl parameter = controls.getParameter(index);
            parameter.set(msg.getData2(), 128);
        }
    }
}
