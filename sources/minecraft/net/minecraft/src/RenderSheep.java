package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import org.lwjgl.opengl.GL11;

public class RenderSheep extends RenderLiving
{

    public RenderSheep(ModelBase modelbase, ModelBase modelbase1, float f)
    {
        super(modelbase, f);
        setRenderPassModel(modelbase1);
    }

    protected boolean func_176_a(EntitySheep entitysheep, int i, float f)
    {
        if(i == 0 && !entitysheep.getSheared())
        {
            loadTexture("/mob/sheep_fur.png");
            float f1 = entitysheep.getEntityBrightness(f);
            int j = entitysheep.getFleeceColor();
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean shouldRenderPass(EntityLiving entityliving, int i, float f)
    {
        return func_176_a((EntitySheep)entityliving, i, f);
    }
}
