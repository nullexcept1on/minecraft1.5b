package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class StatStringFormatKeyInv
    implements IStatStringFormat
{

    public StatStringFormatKeyInv(Minecraft minecraft)
    {
        field_27344_a = minecraft;
        //super();
    }

    public String func_27343_a(String s)
    {
        return String.format(s, new Object[] {
            Keyboard.getKeyName(field_27344_a.gameSettings.keyBindInventory.keyCode)
        });
    }

    final Minecraft field_27344_a; /* synthetic field */
}
