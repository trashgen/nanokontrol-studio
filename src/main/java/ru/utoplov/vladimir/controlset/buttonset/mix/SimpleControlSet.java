package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;
import ru.utoplov.vladimir.controlset.AbstractControlSet;

public class SimpleControlSet extends AbstractControlSet {

    public SimpleControlSet(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        transport.isPlaying().markInterested();
        transport.getInPosition().markInterested();
        transport.isArrangerRecordEnabled().markInterested();

        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Track track = trackBank.getItemAt(i);
            track.mute().markInterested();
            track.solo().markInterested();
        }

        controls.put(BackwardButtonControl.BUTTON_ID, new BackwardButtonControl(controllerContext, transport, trackBank, cursorTrack));
        controls.put(ForwardButtonControl.BUTTON_ID, new ForwardButtonControl(controllerContext, transport, trackBank, cursorTrack));

        controls.put(RewindButtonControl.BUTTON_ID, new RewindButtonControl(transport, trackBank, cursorTrack));
        controls.put(StopButtonControl.BUTTON_ID, new StopButtonControl(controllerContext, transport, trackBank, cursorTrack));
        controls.put(PlayButtonControl.BUTTON_ID, new PlayButtonControl(controllerContext, transport, trackBank, cursorTrack));
        controls.put(RecordButtonControl.BUTTON_ID, new RecordButtonControl(controllerContext, transport, trackBank, cursorTrack));

        controls.put(PrevSendBankButtonControl.BUTTON_ID, new PrevSendBankButtonControl(controllerContext, transport, trackBank, cursorTrack));
        controls.put(NextSendBankButtonControl.BUTTON_ID, new NextSendBankButtonControl(controllerContext, transport, trackBank, cursorTrack));

        controls.put(PrevTrackBankButtonControl.BUTTON_ID, new PrevTrackBankButtonControl(controllerContext, transport, trackBank, cursorTrack));
        controls.put(NextTrackBankButtonControl.BUTTON_ID, new NextTrackBankButtonControl(controllerContext, transport, trackBank, cursorTrack));

        for (int i = MuteButtonControl.BUTTON_ID_FIRST; i <= MuteButtonControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new MuteButtonControl(controllerContext, transport, trackBank, cursorTrack, i - MuteButtonControl.BUTTON_ID_FIRST));
        }
        for (int i = SoloButtonControl.BUTTON_ID_FIRST; i <= SoloButtonControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new SoloButtonControl(controllerContext, transport, trackBank, cursorTrack, i - SoloButtonControl.BUTTON_ID_FIRST));
        }
        for (int i = SelectButtonControl.BUTTON_ID_FIRST; i <= SelectButtonControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new SelectButtonControl(controllerContext, transport, trackBank, cursorTrack, i - SelectButtonControl.BUTTON_ID_FIRST));
        }
    }

}
