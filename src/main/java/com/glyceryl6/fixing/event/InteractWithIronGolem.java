package com.glyceryl6.fixing.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class InteractWithIronGolem {

    @SubscribeEvent
    public void onFixIronGolem(PlayerInteractEvent.EntityInteract event) {
        int ironConsumption;
        Random random = new Random();
        Player player = event.getPlayer();
        Entity entity = event.getTarget();
        ItemStack itemStack = player.getItemInHand(player.getUsedItemHand());
        if (entity instanceof IronGolem ironGolem) {
            float surplusHealth = ironGolem.getMaxHealth() - ironGolem.getHealth();
            float fixCount = (float) Math.ceil(surplusHealth / 25.0F);
            if (ironGolem.isAlive() && surplusHealth > 0 && itemStack.is(Items.IRON_INGOT)) {
                int itemCount = itemStack.getCount();
                float f1 = 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F;
                ironGolem.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
                ironGolem.gameEvent(GameEvent.MOB_INTERACT, ironGolem.eyeBlockPosition());
                if (itemCount >= fixCount) {
                    ironGolem.heal(fixCount * 25.0F);
                    ironConsumption = (int) fixCount;
                } else {
                    ironGolem.heal(itemCount * 25.0F);
                    ironConsumption = itemCount;
                }
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(ironConsumption);
                }
                player.swing(player.getUsedItemHand());
            }
        }
    }

}