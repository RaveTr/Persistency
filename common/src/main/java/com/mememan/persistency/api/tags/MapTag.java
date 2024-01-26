package com.mememan.persistency.api.tags;

import net.minecraft.nbt.StreamTagVisitor;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagType;
import net.minecraft.nbt.TagVisitor;
import org.jetbrains.annotations.NotNull;

import java.io.DataOutput;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Set;

public class MapTag<K extends Tag, V extends Tag> extends AbstractMap<K, V> implements Tag {

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }

    @Override
    public byte getId() {
        return 0;
    }

    @Override
    public TagType<?> getType() {
        return null;
    }

    @Override
    public Tag copy() {
        return null;
    }

    @Override
    public int sizeInBytes() {
        return 0;
    }

    @Override
    public void accept(TagVisitor tagVisitor) {

    }

    @Override
    public StreamTagVisitor.ValueResult accept(StreamTagVisitor streamTagVisitor) {
        return null;
    }
}
