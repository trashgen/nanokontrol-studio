package ru.utoplov.vladimir;

import com.bitwig.extension.controller.api.ControllerHost;
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

import static ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition.*;

class SceneManager {

    private ControllerContext cc;
    private final Map<String, Scene> scenes = new HashMap<>();

    SceneManager(ControllerHost host) {
        cc = new ControllerContext(host);
        scenes.put(KEY_SCENE_MIX_FIRST, new MixScene()
                .addControlSet(new MixButtonControlSet(cc))
                .addControlSet(new MixContinuousControlSet(cc))
                .addControlSet(new MixStateControlSet(cc)));
        scenes.put(KEY_DEVICE_FIRST, new DeviceScene(cc)
                .addControlSet(new DeviceButtonControlSet(cc))
                .addControlSet(new DeviceContinuousControlSet(cc))
                .addControlSet(new DeviceStateControlSet(cc)));
        scenes.put(KEY_SCENE_MIX_SECOND, new MixScene()
                .addControlSet(new MixButtonControlSet(cc))
                .addControlSet(new MixContinuousControlSet(cc))
                .addControlSet(new MixStateControlSet(cc)));
        scenes.put(KEY_DEVICE_SECOND, new DeviceScene(cc)
                .addControlSet(new DeviceButtonControlSet(cc))
                .addControlSet(new DeviceContinuousControlSet(cc))
                .addControlSet(new DeviceStateControlSet(cc)));
        scenes.put(KEY_SCENE_MIX_THIRD, new MixScene()
                .addControlSet(new MixButtonControlSet(cc))
                .addControlSet(new MixContinuousControlSet(cc))
                .addControlSet(new MixStateControlSet(cc)));
        cc.scene = scenes.get(KEY_SCENE_MIX_FIRST);
    }

    void onSceneChange(final String data) {
        cc.resetState();

        cc.scene.cleanUp();
        cc.scene = scenes.get(data);
        cc.scene.init();
    }

    Scene getCurrentScene() {
        return cc.scene;
    }

}
