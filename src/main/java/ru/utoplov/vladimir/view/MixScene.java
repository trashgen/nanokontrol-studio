package ru.utoplov.vladimir.view;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.MappingManager;
import ru.utoplov.vladimir.trackstate.TrackStateMapping;
import ru.utoplov.vladimir.transport.TransportMapping;

import java.util.ArrayList;
import java.util.List;

public class MixScene implements Scene {

    private final static String SCENE_NAME = "Mix Scene";

    private final List<MappingManager> mappings = new ArrayList<>();

    public MixScene(TransportMapping transport, TrackStateMapping trackStateMapping) {
        mappings.add(transport);
        mappings.add(trackStateMapping);
    }

    @Override
    public String getName() {
        return SCENE_NAME;
    }

    @Override
    public void handleMidiCommand(ShortMidiMessage msg) {
        for (MappingManager mapping : mappings) {
            if (mapping.isValidMessage(msg)) {
                mapping.execute(msg);
                return;
            }
        }
    }
}
