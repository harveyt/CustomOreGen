
package com.xcompwiz.mystcraft.api.symbol.words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.util.ResourceLocation;

import com.xcompwiz.mystcraft.api.symbol.ISymbolAPI;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * This is the "word" class for the Narayan Poems
 * You can use this class to do very advanced words, but generally it should be
 * sufficient to use {@code registerWord(String name, Integer[] components)} in
 * {@link ISymbolAPI} See {@link WordData}
 * 
 * @author xcompwiz
 */
public final class DrawableWord {

    private ArrayList<Integer> components = new ArrayList<Integer>();
    private ArrayList<Integer> colors = new ArrayList<Integer>();
    private ResourceLocation imageSource = null;
    //Symbols
    public static final ResourceLocation word_components = new ResourceLocation("mystcraft:textures/symbolcomponents.png");

    public DrawableWord() {}

    public DrawableWord(Integer[] components) {
        List<Integer> list = Arrays.asList(components);
        this.components.addAll(list);
    }

    public ArrayList<Integer> components() {
        return this.components;
    }

    public ArrayList<Integer> colors() {
        return this.colors;
    }

    public DrawableWord addDrawComponent(int slot, int color) {
        this.components.add(slot);
        this.colors.add(color);
        return this;
    }

    public DrawableWord addDrawComponent(int x, int y, int color) {
        return this.addDrawComponent(x + y * 8, color);
    }

    public DrawableWord addDrawComponents(int[] components, int color) {
        for (int i = 0; i < components.length; ++i) {
            this.addDrawComponent(components[i], color);
        }
        return this;
    }

    public DrawableWord addDrawComponents(int[] components, int[] colors) {
        int def = (colors.length > 0 ? colors[0] : 0);
        for (int i = 0; i < components.length; ++i) {
            this.addDrawComponent(components[i], (colors.length > i ? colors[i] : def));
        }
        return this;
    }

    public DrawableWord addDrawWord(int[][] word) {
        if (word.length == 2) {
            this.addDrawComponents(word[0], word[1]);
        }
        return this;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation imageSource() {
        if (this.imageSource != null) {
            return this.imageSource;
        }
        return word_components;
    }

    @SideOnly(Side.CLIENT)
    public DrawableWord setImageSource(ResourceLocation source) {
        this.imageSource = source;
        return this;
    }
}
