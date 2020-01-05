package ru.utoplov.vladimir;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.MidiOut;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.DeviceButtonControlSet;
import ru.utoplov.vladimir.controlset.buttonset.MixButtonControlSet;
import ru.utoplov.vladimir.controlset.continuousset.DeviceContinuousControlSet;
import ru.utoplov.vladimir.controlset.continuousset.MixContinuousControlSet;
import ru.utoplov.vladimir.controlset.stateset.DeviceStateControlSet;
import ru.utoplov.vladimir.controlset.stateset.MixStateControlSet;
import ru.utoplov.vladimir.core.ControllerContext;
import ru.utoplov.vladimir.view.DeviceScene;
import ru.utoplov.vladimir.view.MixScene;
import ru.utoplov.vladimir.view.Scene;

import java.util.HashMap;
import java.util.Map;

import static ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition.KEY_DEVICE_MIX;
import static ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition.KEY_SCENE_MIX;

class SceneManager {

    private ControllerContext controllerContext;
    private final Map<String, Scene> scenes = new HashMap<>();

    SceneManager(MidiOut midiOut, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        controllerContext = new ControllerContext(midiOut, transport, trackBank, cursorTrack);
        trackBank.followCursorTrack(cursorTrack);
        cursorTrack.isPinned().markInterested();
        scenes.put(KEY_SCENE_MIX, new MixScene()
                .addControlSet(new MixButtonControlSet(controllerContext))
                .addControlSet(new MixContinuousControlSet(controllerContext))
                .addControlSet(new MixStateControlSet(controllerContext)));
        scenes.put(KEY_DEVICE_MIX, new DeviceScene()
                .addControlSet(new DeviceButtonControlSet(controllerContext))
                .addControlSet(new DeviceContinuousControlSet(controllerContext))
                .addControlSet(new DeviceStateControlSet(controllerContext)));
    }

    Scene onSceneChange(final String data) {
        Scene scene = scenes.get(data);
        if (scene == null) {
            scene = scenes.get(KEY_SCENE_MIX);
        }
        controllerContext.resetState();
        return scene;
    }

    Scene getMixScene() {
        return scenes.get(KEY_SCENE_MIX);
    }

}
