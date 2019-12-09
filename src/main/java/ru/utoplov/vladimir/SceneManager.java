package ru.utoplov.vladimir;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.MidiOut;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.buttons.StateControlSet;
import ru.utoplov.vladimir.buttons.continuousset.ContinuousControlSet;
import ru.utoplov.vladimir.buttons.simpleset.SimpleControlSet;
import ru.utoplov.vladimir.view.DeviceScene;
import ru.utoplov.vladimir.view.MixScene;
import ru.utoplov.vladimir.view.Scene;

import java.util.HashMap;
import java.util.Map;

import static ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition.KEY_DEVICE_MIX;
import static ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition.KEY_SCENE_MIX;

class SceneManager {

    private final Map<String, Scene> scenes = new HashMap<>();

    SceneManager(MidiOut midiOut, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        ControllerContext controllerContext = new ControllerContext(midiOut);
        trackBank.followCursorTrack(cursorTrack);
        scenes.put(KEY_SCENE_MIX, new MixScene()
                .addControlSet(new SimpleControlSet(controllerContext, transport, trackBank, cursorTrack))
                .addControlSet(new ContinuousControlSet(controllerContext, transport, trackBank, cursorTrack))
                .addControlSet(new StateControlSet(controllerContext)
                ));
        scenes.put(KEY_DEVICE_MIX, new DeviceScene());
    }

    Scene onSceneChange(final String data) {
        return scenes.get(data);
    }

    Scene getMixScene() {
        return scenes.get(KEY_SCENE_MIX);
    }

}
