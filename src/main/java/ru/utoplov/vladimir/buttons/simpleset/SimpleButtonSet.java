package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ButtonSet;
import ru.utoplov.vladimir.DeviceContext;

import java.util.HashMap;
import java.util.Map;

public class SimpleButtonSet implements ButtonSet {

    private final Map<Integer, SimpleButton> buttons = new HashMap<>();

    public SimpleButtonSet(DeviceContext deviceContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        transport.isPlaying().markInterested();
        transport.isArrangerRecordEnabled().markInterested();

        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Track track = trackBank.getItemAt(i);
            track.mute().markInterested();
            track.solo().markInterested();
        }

        buttons.put(RewindButton.BUTTON_ID, new RewindButton(transport, trackBank));
        buttons.put(StopButton.BUTTON_ID, new StopButton(deviceContext, transport, trackBank));
        buttons.put(PlayButton.BUTTON_ID, new PlayButton(deviceContext, transport, trackBank));
        buttons.put(RecordButton.BUTTON_ID, new RecordButton(deviceContext, transport, trackBank));

        buttons.put(PrevSendBankButton.BUTTON_ID, new PrevSendBankButton(deviceContext, transport, trackBank, cursorTrack));
        buttons.put(NextSendBankButton.BUTTON_ID, new NextSendBankButton(deviceContext, transport, trackBank, cursorTrack));

        buttons.put(PrevTrackBankButton.BUTTON_ID, new PrevTrackBankButton(deviceContext, transport, trackBank));
        buttons.put(NextTrackBankButton.BUTTON_ID, new NextTrackBankButton(deviceContext, transport, trackBank));

        for (int i = MuteButton.BUTTON_ID_FIRST; i <= MuteButton.BUTTON_ID_LAST; i++) {
            buttons.put(i, new MuteButton(deviceContext, transport, trackBank, i - MuteButton.BUTTON_ID_FIRST));
        }
        for (int i = SoloButton.BUTTON_ID_FIRST; i <= SoloButton.BUTTON_ID_LAST; i++) {
            buttons.put(i, new SoloButton(deviceContext, transport, trackBank, i - SoloButton.BUTTON_ID_FIRST));
        }
        for (int i = SelectButton.BUTTON_ID_FIRST; i <= SelectButton.BUTTON_ID_LAST; i++) {
            buttons.put(i, new SelectButton(deviceContext, transport, trackBank, i - SelectButton.BUTTON_ID_FIRST));
        }
    }

    @Override
    public boolean isValid(ShortMidiMessage msg) {
        return buttons.get(msg.getData1()) != null;
    }

    @Override
    public boolean execute(ShortMidiMessage msg) {
        SimpleButton button = buttons.get(msg.getData1());
        if (button != null) {
            button.execute(msg);
            return true;
        }
        return false;
    }

}
