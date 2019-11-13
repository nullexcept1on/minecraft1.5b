package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import org.lwjgl.opengl.GL11;

public class RenderGiantZombie extends RenderLiving
{

    public RenderGiantZombie(ModelBase modelbase, float f, float f1)
    {
        super(modelbase, f * f1);
        scale = f1;
    }

    protected void preRenderScale(EntityGiantZombie entitygiantzombie, float f)
    {
        GL11.glScalef(scale, scale, scale);
    }

    protected void preRenderCallback(EntityLiving entityliving, float f)
    {
        preRenderScale((EntityGiantZombie)entityliving, f);
    }

    private float scale;
}
