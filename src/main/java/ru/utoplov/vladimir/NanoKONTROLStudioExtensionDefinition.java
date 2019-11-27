package ru.utoplov.vladimir;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;
import ru.utoplov.vladimir.scenes.BaseView;
import ru.utoplov.vladimir.scenes.SceneView;
import ru.utoplov.vladimir.scenes.StubView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NanoKONTROLStudioExtensionDefinition extends ControllerExtensionDefinition {
    private static final UUID DRIVER_ID = UUID.fromString("dc010196-7101-4519-ad10-ab5634f76da5");

    public final static int BUTTON_FADER_1 = 2;
    public final static int BUTTON_FADER_2 = 3;
    public final static int BUTTON_FADER_3 = 4;
    public final static int BUTTON_FADER_4 = 5;
    public final static int BUTTON_FADER_5 = 6;
    public final static int BUTTON_FADER_6 = 8;
    public final static int BUTTON_FADER_7 = 9;
    public final static int BUTTON_FADER_8 = 12;

    public final static int BUTTON_KNOB_1 = 13;
    public final static int BUTTON_KNOB_2 = 14;
    public final static int BUTTON_KNOB_3 = 15;
    public final static int BUTTON_KNOB_4 = 16;
    public final static int BUTTON_KNOB_5 = 17;
    public final static int BUTTON_KNOB_6 = 18;
    public final static int BUTTON_KNOB_7 = 19;
    public final static int BUTTON_KNOB_8 = 20;

    public final static int BUTTON_SOLO_1 = 29;
    public final static int BUTTON_SOLO_2 = 30;
    public final static int BUTTON_SOLO_3 = 31;
    public final static int BUTTON_SOLO_4 = 33;
    public final static int BUTTON_SOLO_5 = 34;
    public final static int BUTTON_SOLO_6 = 35;
    public final static int BUTTON_SOLO_7 = 36;
    public final static int BUTTON_SOLO_8 = 37;

    public final static int BUTTON_MUTE_1 = 21;
    public final static int BUTTON_MUTE_2 = 22;
    public final static int BUTTON_MUTE_3 = 23;
    public final static int BUTTON_MUTE_4 = 24;
    public final static int BUTTON_MUTE_5 = 25;
    public final static int BUTTON_MUTE_6 = 26;
    public final static int BUTTON_MUTE_7 = 27;
    public final static int BUTTON_MUTE_8 = 28;

    public final static int BUTTON_RECORD_1 = 38;
    public final static int BUTTON_RECORD_2 = 39;
    public final static int BUTTON_RECORD_3 = 40;
    public final static int BUTTON_RECORD_4 = 41;
    public final static int BUTTON_RECORD_5 = 42;
    public final static int BUTTON_RECORD_6 = 43;
    public final static int BUTTON_RECORD_7 = 44;
    public final static int BUTTON_RECORD_8 = 45;

    public final static int BUTTON_SELECT_1 = 46;
    public final static int BUTTON_SELECT_2 = 47;
    public final static int BUTTON_SELECT_3 = 48;
    public final static int BUTTON_SELECT_4 = 49;
    public final static int BUTTON_SELECT_5 = 50;
    public final static int BUTTON_SELECT_6 = 51;
    public final static int BUTTON_SELECT_7 = 52;
    public final static int BUTTON_SELECT_8 = 53;

    // Cycle button used as shift. Press'n'Hold to use.
    public final static int BUTTON_SHIFT = 54;

    public final static int BUTTON_MARKER_SET = 55;
    public final static int BUTTON_MARKER_PREV = 56;
    public final static int BUTTON_MARKER_NEXT = 57;

    public final static int BUTTON_TRANSPORT_BACKWARD = 58;
    public final static int BUTTON_TRANSPORT_FAST_FORWARD = 59;

    public final static int BUTTON_TRACK_BANK_PREV = 60;
    public final static int BUTTON_TRACK_BANK_NEXT = 61;

    public final static int BUTTON_TRANSPORT_REWIND = 62;
    public final static int BUTTON_TRANSPORT_STOP = 63;
    public final static int BUTTON_TRANSPORT_PLAY = 80;
    public final static int BUTTON_TRANSPORT_RECORD = 81;

    public final static int BUTTON_WHEEL = 86;

    // Scene button values
    public final static Map<String, SceneView> SysexHandlers = new HashMap<String, SceneView>() {{
        put("f042400001370200004f00f7", new BaseView());
        put("f042400001370200004f01f7", new StubView());
        put("f042400001370200004f02f7", new StubView());
        put("f042400001370200004f03f7", new StubView());
        put("f042400001370200004f04f7", new StubView());
    }};

    public NanoKONTROLStudioExtensionDefinition() {
    }

    @Override
    public String getName() {
        return "NanoKONTROL Studio";
    }

    @Override
    public String getAuthor() {
        return "Vladimir Utoplov";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public UUID getId() {
        return DRIVER_ID;
    }

    @Override
    public String getHardwareVendor() {
        return "Korg";
    }

    @Override
    public String getHardwareModel() {
        return "NanoKONTROL Studio";
    }

    @Override
    public int getRequiredAPIVersion() {
        return 9;
    }

    @Override
    public int getNumMidiInPorts() {
        return 1;
    }

    @Override
    public int getNumMidiOutPorts() {
        return 1;
    }

    @Override
    public void listAutoDetectionMidiPortNames(final AutoDetectionMidiPortNamesList list, final PlatformType platformType) {
        if (platformType == PlatformType.WINDOWS) {
            // TODO: Set the correct names of the ports for auto detection on Windows platform here
            // and uncomment this when port names are correct.
            // list.add(new String[]{"Input Port 0"}, new String[]{"Output Port 0"});
            list.add(new String[]{"nanoKONTROL Studio"}, new String[]{"nanoKONTROL Studio"});
        } else if (platformType == PlatformType.MAC) {
            // TODO: Set the correct names of the ports for auto detection on Windows platform here
            // and uncomment this when port names are correct.
            // list.add(new String[]{"Input Port 0"}, new String[]{"Output Port 0"});
        } else if (platformType == PlatformType.LINUX) {
            // TODO: Set the correct names of the ports for auto detection on Windows platform here
            // and uncomment this when port names are correct.
            // list.add(new String[]{"Input Port 0"}, new String[]{"Output Port 0"});
        }
    }

    @Override
    public NanoKONTROLStudioExtension createInstance(final ControllerHost host) {
        return new NanoKONTROLStudioExtension(this, host);
    }
}
