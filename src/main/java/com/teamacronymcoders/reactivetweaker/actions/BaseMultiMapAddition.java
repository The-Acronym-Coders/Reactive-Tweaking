package com.teamacronymcoders.reactivetweaker.actions;

import com.blamejared.mtlib.helpers.LogHelper;
import com.blamejared.mtlib.utils.BaseUndoable;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseMultiMapAddition<K, V> extends BaseUndoable {
    protected Map<K, V> map1;
    protected Map<K, V> map2;

    protected final HashMap<K, V> recipes;
    protected final HashMap<K, V> successful;

    protected final HashMap<K, V> overwritten;

    protected BaseMultiMapAddition(String name, Map<K, V> map1, Map<K, V> map2) {
        super(name);
        this.map1 = map1;
        this.map2 = map2;
        this.recipes = new HashMap<K, V>();
        this.successful = new HashMap<K, V>();
        this.overwritten = new HashMap<K, V>();
    }

    protected BaseMultiMapAddition(String name, Map<K, V> map1, Map<K, V> map2, Map<K, V> recipes) {
        this(name, map1, map2);
        this.recipes.putAll(recipes);
    }

    @Override
    public void apply() {
        if(recipes.isEmpty())
            return;

        for(Entry<K, V> entry : recipes.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            V oldValue1 = map1.put(key, value);
            V oldValue2 = map2.put(key, value);


            if(oldValue1 != null || oldValue2 != null) {
                LogHelper.logWarning(String.format("Overwritten %s Recipe for %s", name, getRecipeInfo(new AbstractMap.SimpleEntry<K, V>(entry.getKey(), value))));
                if(oldValue1 != null)
                    overwritten.put(key, oldValue1);
                if(oldValue2 != null)
                    overwritten.put(key, oldValue2);

            }


            successful.put(key, value);
        }
    }

    @Override
    public void undo() {
        if(successful.isEmpty() && overwritten.isEmpty())
            return;

        for(Entry<K, V> entry : successful.entrySet()) {
            K key = entry.getKey();
            V value1 = map1.remove(key);
            V value2 = map2.remove(key);

            if(value1 == null) {
                LogHelper.logError(String.format("Error removing %s Recipe: null object from map1", name));
            }
            if(value2 == null) {
                LogHelper.logError(String.format("Error removing %s Recipe: null object from map2", name));
            }
        }

        for(Entry<K, V> entry : overwritten.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            V oldValue1 = map1.put(key, value);
            V oldValue2 = map2.put(key, value);


            if(oldValue1 != null || oldValue2 != null) {
                LogHelper.logWarning(String.format("Overwritten %s Recipe which should not exist for %s", name, getRecipeInfo(new AbstractMap.SimpleEntry<K, V>(entry.getKey(), value))));
            }
        }
    }

    @Override
    public String describe() {
        return String.format("Adding %d %s Recipe(s) for %s", recipes.size(), name, getRecipeInfo());
    }

    @Override
    public String describeUndo() {
        return String.format("Removing %d %s Recipe(s) for %s", recipes.size(), name, getRecipeInfo());
    }

    @Override
    public boolean canUndo() {
        return !recipes.isEmpty();
    }

    @Override
    protected String getRecipeInfo() {
        if(!recipes.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for(Entry<K, V> recipe : recipes.entrySet()) {
                if(recipe != null) {
                    sb.append(getRecipeInfo(recipe)).append(", ");
                }
            }

            if(sb.length() > 0) {
                sb.setLength(sb.length() - 2);
            }

            return sb.toString();
        }

        return super.getRecipeInfo();
    }

    /**
     * This method must be overwritten by the extending classes. It should return
     * the name of the key item, for which the recipe is for. For example for machines
     * which produce new items, it should return the name of the ouput. For machines
     * which are processing items (like a pulverizer) it should return the name of the
     * the input. Another example would be the name of the enchantmant for a thaumcraft
     * infusion recipe.
     */
    protected abstract String getRecipeInfo(Entry<K, V> recipe);
}
