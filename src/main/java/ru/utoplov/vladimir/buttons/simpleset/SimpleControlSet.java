package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;
import ru.utoplov.vladimir.buttons.AbstractControlSet;

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

        controls.put(BackwardButton.BUTTON_ID, new BackwardButton(controllerContext, transport, trackBank, cursorTrack));
        controls.put(ForwardButton.BUTTON_ID, new ForwardButton(controllerContext, transport, trackBank, cursorTrack));

        controls.put(RewindButton.BUTTON_ID, new RewindButton(controllerContext, transport, trackBank, cursorTrack));
        controls.put(StopButton.BUTTON_ID, new StopButton(controllerContext, transport, trackBank, cursorTrack));
        controls.put(PlayButton.BUTTON_ID, new PlayButton(controllerContext, transport, trackBank, cursorTrack));
        controls.put(RecordButton.BUTTON_ID, new RecordButton(controllerContext, transport, trackBank, cursorTrack));

        controls.put(PrevSendBankButton.BUTTON_ID, new PrevSendBankButton(controllerContext, transport, trackBank, cursorTrack));
        controls.put(NextSendBankButton.BUTTON_ID, new NextSendBankButton(controllerContext, transport, trackBank, cursorTrack));

        controls.put(PrevTrackBankButton.BUTTON_ID, new PrevTrackBankButton(controllerContext, transport, trackBank, cursorTrack));
        controls.put(NextTrackBankButton.BUTTON_ID, new NextTrackBankButton(controllerContext, transport, trackBank, cursorTrack));

        for (int i = MuteButton.BUTTON_ID_FIRST; i <= MuteButton.BUTTON_ID_LAST; i++) {
            controls.put(i, new MuteButton(controllerContext, transport, trackBank, cursorTrack, i - MuteButton.BUTTON_ID_FIRST));
        }
        for (int i = SoloButton.BUTTON_ID_FIRST; i <= SoloButton.BUTTON_ID_LAST; i++) {
            controls.put(i, new SoloButton(controllerContext, transport, trackBank, cursorTrack, i - SoloButton.BUTTON_ID_FIRST));
        }
        for (int i = SelectButton.BUTTON_ID_FIRST; i <= SelectButton.BUTTON_ID_LAST; i++) {
            controls.put(i, new SelectButton(controllerContext, transport, trackBank, cursorTrack, i - SelectButton.BUTTON_ID_FIRST));
        }
    }

}
