package minegame159.meteorclient.settings;

import minegame159.meteorclient.gui.widgets.WKeybind;
import net.minecraft.nbt.CompoundTag;

import java.util.function.Consumer;

public class KeybindSetting extends Setting<Integer> {
    public KeybindSetting(String name, String description, Integer defaultValue, Consumer<Integer> onChanged, Consumer<Setting<Integer>> onModuleActivated) {
        super(name, description, defaultValue, onChanged, onModuleActivated);

        widget = new WKeybind(get());
        ((WKeybind) widget).action = () -> set(((WKeybind) widget).get());
    }

    @Override
    protected Integer parseImpl(String str) {
        return -1;
    }

    @Override
    public void resetWidget() {
        ((WKeybind) widget).set(get());
    }

    @Override
    protected boolean isValueValid(Integer value) {
        return true;
    }

    @Override
    protected String generateUsage() {
        return "not implemented";
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = saveGeneral();
        tag.putInt("key", get());
        return tag;
    }

    @Override
    public Integer fromTag(CompoundTag tag) {
        set(tag.getInt("key"));

        return get();
    }

    public static class Builder {
        private String name = "undefined", description = "";
        private Integer defaultValue = -1;
        private Consumer<Integer> onChanged;
        private Consumer<Setting<Integer>> onModuleActivated;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder defaultValue(int defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder onChanged(Consumer<Integer> onChanged) {
            this.onChanged = onChanged;
            return this;
        }

        public Builder onModuleActivated(Consumer<Setting<Integer>> onModuleActivated) {
            this.onModuleActivated = onModuleActivated;
            return this;
        }

        public KeybindSetting build() {
            return new KeybindSetting(name, description, defaultValue, onChanged, onModuleActivated);
        }
    }
}