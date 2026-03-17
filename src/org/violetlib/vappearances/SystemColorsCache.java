/*
 * Copyright (c) 2025 Alan Snyder.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the license agreement. For details see
 * accompanying license terms.
 */

package org.violetlib.vappearances;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/* package private */ final class SystemColorsCache
{
    private final @NotNull Map<String,Map<String,Color>> cache = new HashMap<>();

    public synchronized void clear()
    {
        cache.clear();
    }

    public synchronized void put(@NotNull String appearanceName, @NotNull Map<String,Color> colors)
    {
        cache.put(appearanceName, colors);
    }

    public synchronized @Nullable Map<String,Color> get(@NotNull String appearanceName)
    {
        return cache.get(appearanceName);
    }

    public synchronized @NotNull Map<String,Color> computeIfAbsent(
        @NotNull String appearanceName,
        @NotNull Function<String, Map<String,Color>> mappingFunction)
    {
        Map<String,Color> colors = cache.get(appearanceName);
        if (colors == null) {
            colors = mappingFunction.apply(appearanceName);
            cache.put(appearanceName, colors);
        }
        return colors;
    }
}
