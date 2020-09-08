package net.dohaw.play.divisions.archetypes;

import net.dohaw.play.divisions.Stat;
import net.dohaw.play.divisions.archetypes.specializations.SpecialityKey;
import net.dohaw.play.divisions.archetypes.specializations.SpecialityWrapper;
import net.dohaw.play.divisions.archetypes.spells.SpellKey;
import net.dohaw.play.divisions.archetypes.spells.SpellWrapper;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public abstract class ArchetypeWrapper extends Wrapper{

    public ArchetypeWrapper(ArchetypeKey KEY) {
        super(KEY);
    }

    public abstract List<ItemStack> getDefaultItems();

    public abstract Map<Stat, Double> getDefaultStats();

    public abstract Map<SpecialityKey, SpecialityWrapper> getSpecialities();

    public abstract List<ItemStack> getProficientItems();

    public abstract RegenType getRegenType();

    public abstract Map<SpellKey, SpellWrapper> getSpells();

}