package ru.utoplov.vladimir.core;

import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.PinnableCursorDevice;

import java.util.*;

public class RemoteControl {

    private static final String NOT_USED_PAGE = "NOT USED";

    private static final Map<String, List<String>> mappingNames = new HashMap<String, List<String>>() {{
        put("Polysynth", Arrays.asList("Oscillator", "Envelope", NOT_USED_PAGE, NOT_USED_PAGE, NOT_USED_PAGE));
        put("Phase-4", Arrays.asList("Red", "Yellow", "Blue", "Magenta", "Misc"));
    }};

    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_01 = "trashgen_1";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_02 = "trashgen_2";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_03 = "trashgen_3";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_04 = "trashgen_4";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_05 = "trashgen_5";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_06 = "trashgen_6";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_07 = "trashgen_7";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_08 = "trashgen_8";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_09 = "trashgen_9";
    private static final String NANO_KONTROL_STUDIO_REMOTE_TAG_10 = "trashgen_10";

    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE";
    private static final String NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO = "NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO";

    private class ActiveRemote {
        private CursorRemoteControlsPage first;
        private CursorRemoteControlsPage second;

        ActiveRemote(CursorRemoteControlsPage first, CursorRemoteControlsPage second) {
            this.first = first;
            this.second = second;
            for (int i = 0; i < this.first.getParameterCount(); i++) {
                com.bitwig.extension.controller.api.RemoteControl parameter = this.first.getParameter(i);
                parameter.markInterested();
                parameter.name().markInterested();
                parameter.value().markInterested();
            }
            for (int i = 0; i < this.second.getParameterCount(); i++) {
                com.bitwig.extension.controller.api.RemoteControl parameter = this.second.getParameter(i);
                parameter.markInterested();
                parameter.name().markInterested();
                parameter.value().markInterested();
            }
        }
    }

    private ActiveRemote control;
    private int currentRemoteSetID;
    private PinnableCursorDevice cursorDevice;
    private final List<ActiveRemote> remotes = new ArrayList<>();

    RemoteControl(PinnableCursorDevice cursorDevice) {
        this.cursorDevice = cursorDevice;
        addControlPage(NANO_KONTROL_STUDIO_REMOTE_TAG_01, NANO_KONTROL_STUDIO_REMOTE_TAG_02);
        addControlPage(NANO_KONTROL_STUDIO_REMOTE_TAG_03, NANO_KONTROL_STUDIO_REMOTE_TAG_04);
        addControlPage(NANO_KONTROL_STUDIO_REMOTE_TAG_05, NANO_KONTROL_STUDIO_REMOTE_TAG_06);
        addControlPage(NANO_KONTROL_STUDIO_REMOTE_TAG_07, NANO_KONTROL_STUDIO_REMOTE_TAG_08);
        addControlPage(NANO_KONTROL_STUDIO_REMOTE_TAG_09, NANO_KONTROL_STUDIO_REMOTE_TAG_10);

        control = remotes.get(currentRemoteSetID);
    }

    public CursorRemoteControlsPage getFirst() {
        return control.first;
    }

    public CursorRemoteControlsPage getSecond() {
        return control.second;
    }

    public void nextRemoteControls() {
        ++currentRemoteSetID;
        if (currentRemoteSetID == remotes.size() || isNotUsedPage()) {
            currentRemoteSetID = 0;
        }
        control = remotes.get(currentRemoteSetID);
    }

    public void prevRemoteControls() {
        --currentRemoteSetID;
        if (currentRemoteSetID < 0) {
            boolean wasChanged = false;
            for (int i = remotes.size() - 1; i >= 0; i--) {
                List<String> names = mappingNames.get(cursorDevice.name().get());
                if (names == null) {
                    currentRemoteSetID = 0;
                    return;
                }
                if (!names.get(i).equals(NOT_USED_PAGE)) {
                    wasChanged = true;
                    currentRemoteSetID = i;
                    break;
                }
            }
            if (!wasChanged) {
                currentRemoteSetID = 0;
            }
        }
        control = remotes.get(currentRemoteSetID);
    }

    String getName() {
        List<String> names = mappingNames.get(cursorDevice.name().get());
        if (names != null) {
            return names.get(currentRemoteSetID);
        }
        return null;
    }

    private boolean isNotUsedPage() {
        List<String> names = mappingNames.get(cursorDevice.name().get());
        if (names != null) {
            return names.get(currentRemoteSetID).equals(NOT_USED_PAGE);
        }
        return true;
    }

    private void addControlPage(String tag1, String tag2) {
        remotes.add(new ActiveRemote(
                cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_ONE, 8, tag1),
                cursorDevice.createCursorRemoteControlsPage(NANO_KONTROL_STUDIO_DEVICE_CONTROL_NAME_TWO, 8, tag2)));
    }

}
