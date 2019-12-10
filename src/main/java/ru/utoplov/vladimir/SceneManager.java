package ru.utoplov.vladimir;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.MidiOut;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.mix.SimpleControlSet;
import ru.utoplov.vladimir.controlset.continuousset.mix.ContinuousControlSet;
import ru.utoplov.vladimir.controlset.stateset.MixStateControlSet;
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
                .addControlSet(new MixStateControlSet(controllerContext)
                ));
        scenes.put(KEY_DEVICE_MIX, new DeviceScene()
                .addControlSet(new SimpleControlSet(controllerContext, transport, trackBank, cursorTrack)));
    }

    Scene onSceneChange(final String data) {
        Scene scene = scenes.get(data);
        if (scene == null) {
            scene = scenes.get(KEY_SCENE_MIX);
        }
        return scene;
    }

    Scene getMixScene() {
        return scenes.get(KEY_SCENE_MIX);
    }

}
