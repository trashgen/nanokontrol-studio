package ru.utoplov.vladimir;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.MidiOut;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.buttons.StateControlSet;
import ru.utoplov.vladimir.buttons.continuousset.ContinuousControlSet;
import ru.utoplov.vladimir.buttons.simpleset.SimpleButtonSet;
import ru.utoplov.vladimir.view.MixScene;
import ru.utoplov.vladimir.view.Scene;

class SceneManager {

    private static final String SYS_EX_PATTERN = "f0 42 40 00 01 37 02 00 00 4f ?? f7";

    private Scene mixScene;
    private final DeviceContext deviceContext;

    // Scene button values
//    public final static Map<String, Scene> SysexHandlers = new HashMap<String, Scene>() {{
//        put("f042400001370200004f00f7", new MixScene());
//        put("f042400001370200004f01f7", new SendView());
//        put("f042400001370200004f02f7", new EconomyMixView());
//        put("f042400001370200004f03f7", null);
//        put("f042400001370200004f04f7", null);
//    }};

    SceneManager(MidiOut midiOut, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        deviceContext = new DeviceContext(midiOut);
        trackBank.followCursorTrack(cursorTrack);
        mixScene = new MixScene(
                new SimpleButtonSet(deviceContext, transport, trackBank, cursorTrack),
                new ContinuousControlSet(deviceContext, transport, trackBank, cursorTrack),
                new StateControlSet(deviceContext)
        );
    }

//    function onSysex(data)
//    {
//        //logf("sysex [{0}]", data);
//
//        // change volume for selected track
//        if (data.matchesHexPattern("f0 7f 7f 04 01 ?? F7"))
//        {
//            var value = data.hexByteAt(5);
//            logGraphite("global", "slider", 8, value);
//            cursorTrack.getVolume().set(value, 128);
//        }
//    }

    Scene onSceneChange(final String data) {
        return mixScene;
    }

    Scene getFirstScene() {
        return mixScene;
    }

}
