package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public interface ICamera
{

    public abstract boolean isBoundingBoxInFrustum(AxisAlignedBB axisalignedbb);

    public abstract void setPosition(double d, double d1, double d2);
}
