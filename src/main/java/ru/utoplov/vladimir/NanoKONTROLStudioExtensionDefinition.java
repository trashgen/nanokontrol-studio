package ru.utoplov.vladimir;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

import java.util.UUID;

public class NanoKONTROLStudioExtensionDefinition extends ControllerExtensionDefinition {
    private static final UUID DRIVER_ID = UUID.fromString("dc010196-7101-4519-ad10-ab5634f76da5");

    public final static int BUTTON_FADER_1 = 2;
    public final static int BUTTON_FADER_2 = 3;
    public final static int BUTTON_FADER_3 = 4;
    public final static int BUTTON_FADER_4 = 5;
    public final static int BUTTON_FADER_5 = 6;
    public final static int BUTTON_FADER_6 = 7;
    public final static int BUTTON_FADER_7 = 8;
    public final static int BUTTON_FADER_8 = 9;

    public final static int BUTTON_KNOB_1 = 13;
    public final static int BUTTON_KNOB_2 = 14;
    public final static int BUTTON_KNOB_3 = 15;
    public final static int BUTTON_KNOB_4 = 16;
    public final static int BUTTON_KNOB_5 = 17;
    public final static int BUTTON_KNOB_6 = 18;
    public final static int BUTTON_KNOB_7 = 19;
    public final static int BUTTON_KNOB_8 = 20;

    // Cycle button used as shift. Press'n'Hold to use.
    public final static int BUTTON_SHIFT = 54;

    public final static int BUTTON_MARKER_SET = 55;
    public final static int BUTTON_MARKER_PREV = 56;
    public final static int BUTTON_SEND_BANK_NEXT = 57;

    public final static int BUTTON_TRANSPORT_BACKWARD = 58;
    public final static int BUTTON_TRANSPORT_FAST_FORWARD = 59;

    public final static int BUTTON_TRACK_BANK_PREV = 60;
    public final static int BUTTON_TRACK_BANK_NEXT = 61;

    public final static int BUTTON_WHEEL = 86;

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
//            list.add(new String[]{"nanoKONTROL Studio IN"}, new String[]{"nanoKONTROL Studio OUT"});
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
