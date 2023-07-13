package com.clovercard.clovergoshadow.listeners;

import com.pixelmonmod.api.registry.RegistryValue;
import com.pixelmonmod.pixelmon.api.events.raids.DenEvent;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;
import com.pixelmonmod.pixelmon.api.pokemon.species.Stats;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.battles.raids.RaidData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Optional;

public class WishPieceInteraction {
    @SubscribeEvent
    public void onInteract(DenEvent.Interact event) {
        if(event.getDen().getData().isPresent()) return;
        if(event.wasLeftClick()) return;
        if(event.getPlayer().getMainHandItem() == null) return;
        ItemStack held = event.getPlayer().getMainHandItem();
        CompoundNBT data = held.getOrCreateTag();
        if(!held.getItem().equals(PixelmonItems.poke_flute.getItem())) return;
        if(!data.contains("clovergoshadowwishingpiece")) {
            event.getPlayer().getMainHandItem().shrink(1);
            return;
        }
        else if(!data.contains("clovergoshadowspecies")) {
            event.getPlayer().getMainHandItem().shrink(1);
            return;
        }
        else if(!data.contains("clovergoshadowform")) {
            event.getPlayer().getMainHandItem().shrink(1);
            return;
        }
        else {
            String specName = data.getString("clovergoshadowspecies");
            String formName = data.getString("clovergoshadowform");
            Optional<RegistryValue<Species>> optReg = PixelmonSpecies.get(specName);
            if(!optReg.isPresent()) event.getPlayer().getMainHandItem().shrink(1);
            RegistryValue<Species> reg = optReg.get();
            Optional<Species> optSpec = reg.getValue();
            if(!optSpec.isPresent()) event.getPlayer().getMainHandItem().shrink(1);
            Species spec = optSpec.get();
            Stats form = spec.getForm(formName);
            RaidData raid;
            if(spec.isLegendary() || spec.isMythical()) {
                raid = new RaidData(event.getDen().getId(), 5, spec, form);
            }
            else {
                int stars = (int) (Math.random()*4) + 1;
                raid = new RaidData(event.getDen().getId(), stars, spec, form);
            }
            event.getDen().setData(raid);
            event.getDen().getPersistentData().putString("clovergoshadowden", spec.getName());
            event.getPlayer().getMainHandItem().shrink(1);
        }
    }
}
