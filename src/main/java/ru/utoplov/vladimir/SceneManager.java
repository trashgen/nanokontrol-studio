package ru.utoplov.vladimir;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.trackstate.MixSceneTrackStateMapping;
import ru.utoplov.vladimir.transport.MixSceneTransportMapping;
import ru.utoplov.vladimir.view.MixScene;
import ru.utoplov.vladimir.view.Scene;

class SceneManager {

    private Scene mixScene;

    // Scene button values
//    public final static Map<String, Scene> SysexHandlers = new HashMap<String, Scene>() {{
//        put("f042400001370200004f00f7", new MixScene());
//        put("f042400001370200004f01f7", new SendView());
//        put("f042400001370200004f02f7", new EconomyMixView());
//        put("f042400001370200004f03f7", null);
//        put("f042400001370200004f04f7", null);
//    }};

    SceneManager(Transport transport, TrackBank trackBank) {
        mixScene = new MixScene(
                new MixSceneTransportMapping(transport),
                new MixSceneTrackStateMapping(trackBank));
    }

    Scene onSceneChange(final String data) {
        return mixScene;
    }

    Scene getFirstScene() {
        return mixScene;
    }

}
