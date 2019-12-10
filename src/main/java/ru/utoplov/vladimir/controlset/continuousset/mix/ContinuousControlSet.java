package ru.utoplov.vladimir.controlset.continuousset.mix;

import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.ControllerContext;
import ru.utoplov.vladimir.controlset.AbstractControlSet;


public class ContinuousControlSet extends AbstractControlSet {

    public ContinuousControlSet(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        transport.getPosition().markInterested();

//        cursorTrack.volume().value().markInterested();

        for (int i = 0; i < cursorTrack.sendBank().getSizeOfBank(); i++) {
            Send send = cursorTrack.sendBank().getItemAt(i);
            send.markInterested();
            send.setIndication(true);
            send.value().markInterested();
        }

        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Parameter parameter = trackBank.getItemAt(i).volume();
            parameter.markInterested();
            parameter.setIndication(true);
        }

        controls.put(WheelBackwardControl.BUTTON_ID, new WheelBackwardControl(controllerContext, transport, trackBank, cursorTrack));
        controls.put(WheelForwardControl.BUTTON_ID, new WheelForwardControl(controllerContext, transport, trackBank, cursorTrack));

        for (int i = FaderControl.BUTTON_ID_FIRST; i <= FaderControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new FaderControl(controllerContext, transport, trackBank, cursorTrack, i - FaderControl.BUTTON_ID_FIRST));
        }
        for (int i = KnobControl.BUTTON_ID_FIRST; i <= KnobControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new KnobControl(controllerContext, transport, trackBank, cursorTrack, i - KnobControl.BUTTON_ID_FIRST));
        }
    }

}
