package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

public class StatBasic extends StatBase
{

    public StatBasic(int i, String s, IStatType istattype)
    {
        super(i, s, istattype);
    }

    public StatBasic(int i, String s)
    {
        super(i, s);
    }

    public StatBase func_25068_c()
    {
        super.func_25068_c();
        StatList.field_25187_b.add(this);
        return this;
    }
}
