package ru.utoplov.vladimir.trackstate;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.Parameter;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;

public class MixSceneTrackStateMapping extends TrackStateMapping {

    public MixSceneTrackStateMapping(TrackBank trackBank, CursorTrack cursorTrack) {
        super(trackBank, cursorTrack);
        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Track track = trackBank.getItemAt(i);
            track.name().markInterested();
            track.mute().markInterested();
            track.solo().markInterested();
//            for (int j = 0; j < track.sendBank().getSizeOfBank(); j++) {
//                track.sendBank().getItemAt(j).markInterested();
//                track.sendBank().getItemAt(j).setIndication(true);
//                Send send = track.sendBank().getItemAt(j);
//                send.sendMode().
//            }

            Parameter parameter = trackBank.getItemAt(i).volume();
            parameter.markInterested();
            parameter.setIndication(true);
        }
    }

    @Override
    protected void onSolo(int index) {
        trackBank.getItemAt(index).solo().toggle();
    }

    @Override
    protected void onMute(int index) {
        trackBank.getItemAt(index).mute().toggle();
    }

    @Override
    protected void onRecord(int index) {
//        trackBank.getItemAt(index).rec().toggle(true);
    }

    @Override
    protected void onSelect(int index) {
        trackBank.getItemAt(index).selectInMixer();
    }

}
