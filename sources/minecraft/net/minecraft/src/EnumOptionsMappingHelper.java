package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


class EnumOptionsMappingHelper
{

    static final int enumOptionsMappingHelperArray[]; /* synthetic field */

    static 
    {
        enumOptionsMappingHelperArray = new int[EnumOptions.values().length];
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.INVERT_MOUSE.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.VIEW_BOBBING.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.ANAGLYPH.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.ADVANCED_OPENGL.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.LIMIT_FRAMERATE.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.AMBIENT_OCCLUSION.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
    }
}
