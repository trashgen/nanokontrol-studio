package ru.utoplov.vladimir.view;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;

import java.util.HashMap;

public class MixView extends AbstractView {
    private Map<ManagerType, Manager> managers = new HashMap<>();

    @Override
    public void handleMidiCommand(ShortMidiMessage msg) {
        switch (getManagerType(msg)) {
            case MANAGER_TRANSPORT:
                managers.get(MANAGER_TRANSPORT).execute(msg);
                break;
        }
    }
}
