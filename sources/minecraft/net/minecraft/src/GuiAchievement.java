package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiAchievement extends Gui
{

    public GuiAchievement(Minecraft minecraft)
    {
        field_25082_a = minecraft;
        field_27104_h = new RenderItem();
    }

    public void func_27102_a(Achievement achievement)
    {
        field_25085_d = StatCollector.func_25200_a("achievement.get");
        field_25084_e = achievement.field_25070_e;
        field_25083_f = System.currentTimeMillis();
        field_27105_f = achievement;
        field_27103_i = false;
    }

    public void func_27101_b(Achievement achievement)
    {
        field_25085_d = achievement.field_25070_e;
        field_25084_e = achievement.func_27090_e();
        field_25083_f = System.currentTimeMillis() - 2500L;
        field_27105_f = achievement;
        field_27103_i = true;
    }

    private void func_25079_b()
    {
        GL11.glViewport(0, 0, field_25082_a.displayWidth, field_25082_a.displayHeight);
        GL11.glMatrixMode(5889 /*GL_PROJECTION*/);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(5888 /*GL_MODELVIEW0_ARB*/);
        GL11.glLoadIdentity();
        field_25081_b = field_25082_a.displayWidth;
        field_25086_c = field_25082_a.displayHeight;
        ScaledResolution scaledresolution = new ScaledResolution(field_25082_a.gameSettings, field_25082_a.displayWidth, field_25082_a.displayHeight);
        field_25081_b = scaledresolution.getScaledWidth();
        field_25086_c = scaledresolution.getScaledHeight();
        GL11.glClear(256);
        GL11.glMatrixMode(5889 /*GL_PROJECTION*/);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, field_25081_b, field_25086_c, 0.0D, 1000D, 3000D);
        GL11.glMatrixMode(5888 /*GL_MODELVIEW0_ARB*/);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000F);
    }

    public void func_25080_a()
    {
        if(field_27105_f == null || field_25083_f == 0L)
        {
            return;
        }
        double d = (double)(System.currentTimeMillis() - field_25083_f) / 3000D;
        if(!field_27103_i && !field_27103_i && (d < 0.0D || d > 1.0D))
        {
            field_25083_f = 0L;
            return;
        }
        func_25079_b();
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        double d1 = d * 2D;
        if(d1 > 1.0D)
        {
            d1 = 2D - d1;
        }
        d1 *= 4D;
        d1 = 1.0D - d1;
        if(d1 < 0.0D)
        {
            d1 = 0.0D;
        }
        d1 *= d1;
        d1 *= d1;
        int i = field_25081_b - 160;
        int j = 0 - (int)(d1 * 36D);
        int k = field_25082_a.renderEngine.getTexture("/achievement/bg.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, k);
        GL11.glDisable(2896 /*GL_LIGHTING*/);
        drawTexturedModalRect(i, j, 96, 202, 160, 32);
        if(field_27103_i)
        {
            field_25082_a.fontRenderer.func_27278_a(field_25084_e, i + 30, j + 7, 120, -1);
        } else
        {
            field_25082_a.fontRenderer.drawString(field_25085_d, i + 30, j + 7, -256);
            field_25082_a.fontRenderer.drawString(field_25084_e, i + 30, j + 18, -1);
        }
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        GL11.glDisable(2896 /*GL_LIGHTING*/);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glEnable(2903 /*GL_COLOR_MATERIAL*/);
        GL11.glEnable(2896 /*GL_LIGHTING*/);
        field_27104_h.renderItemIntoGUI(field_25082_a.fontRenderer, field_25082_a.renderEngine, field_27105_f.field_27097_d, i + 8, j + 8);
        GL11.glDisable(2896 /*GL_LIGHTING*/);
        GL11.glDepthMask(true);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
    }

    private Minecraft field_25082_a;
    private int field_25081_b;
    private int field_25086_c;
    private String field_25085_d;
    private String field_25084_e;
    private Achievement field_27105_f;
    private long field_25083_f;
    private RenderItem field_27104_h;
    private boolean field_27103_i;
}
