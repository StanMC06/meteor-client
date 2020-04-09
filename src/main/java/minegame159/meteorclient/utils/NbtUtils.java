package minegame159.meteorclient.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NbtUtils {
    public static <T extends ISerializable<?>> ListTag listToTag(List<T> list) {
        ListTag tag = new ListTag();
        for (T item : list) tag.add(item.toTag());
        return tag;
    }

    public static <T> List<T> listFromTag(ListTag tag, ToValue<T> toItem) {
        List<T> list = new ArrayList<>(tag.size());
        for (Tag itemTag : tag) list.add(toItem.toValue(itemTag));
        return list;
    }

    public static <K, V extends ISerializable<?>> CompoundTag mapToTag(Map<K, V> map) {
        CompoundTag tag = new CompoundTag();
        for (K key : map.keySet()) tag.put(key.toString(), map.get(key).toTag());
        return tag;
    }

    public static <K, V> Map<K, V> mapFromTag(CompoundTag tag, ToKey<K> toKey, ToValue<V> toValue) {
        Map<K, V> map = new HashMap<>(tag.getSize());
        for (String key : tag.getKeys()) map.put(toKey.toKey(key), toValue.toValue(tag.get(key)));
        return map;
    }

    public interface ToKey<T> {
        public T toKey(String string);
    }

    public interface ToValue<T> {
        public T toValue(Tag tag);
    }
}