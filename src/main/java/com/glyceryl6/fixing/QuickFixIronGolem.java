package com.glyceryl6.fixing;

import com.glyceryl6.fixing.event.InteractWithIronGolem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(QuickFixIronGolem.MOD_ID)
public class QuickFixIronGolem {

    public static final String MOD_ID = "quick_fix_iron_golem";

    public QuickFixIronGolem() {
        MinecraftForge.EVENT_BUS.register(new InteractWithIronGolem());
        MinecraftForge.EVENT_BUS.register(this);
    }

}