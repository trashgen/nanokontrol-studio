package ru.utoplov.vladimir.view;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import ru.utoplov.vladimir.ButtonSet;
import ru.utoplov.vladimir.buttons.continuousset.ContinuousControlSet;
import ru.utoplov.vladimir.buttons.simpleset.SimpleButtonSet;

import java.util.ArrayList;
import java.util.List;

public class MixScene implements Scene {

    private final static String SCENE_NAME = "Mix Scene";

    private final List<ButtonSet> buttonSets = new ArrayList<>();

    public MixScene(SimpleButtonSet simpleButtonSet, ContinuousControlSet continuousControlSet) {
        buttonSets.add(simpleButtonSet);
        buttonSets.add(continuousControlSet);
    }

    @Override
    public String getName() {
        return SCENE_NAME;
    }

    @Override
    public boolean handleMidiCommand(ShortMidiMessage msg) {
        for (ButtonSet buttonSet : buttonSets) {
            if (buttonSet.isValid(msg)) {
                return buttonSet.execute(msg);
            }
        }
        return false;
    }
}
