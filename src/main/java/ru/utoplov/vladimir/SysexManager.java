package ru.utoplov.vladimir;

import java.util.HashMap;
import java.util.Map;

public class SysexManager {

    // Scene button values
    public final static Map<String, SceneView> SysexHandlers = new HashMap<String, SceneView>() {{
        put("f042400001370200004f00f7", new BaseView());
        put("f042400001370200004f01f7", new StubView());
        put("f042400001370200004f02f7", new StubView());
        put("f042400001370200004f03f7", new StubView());
        put("f042400001370200004f04f7", new StubView());
    }};

}
