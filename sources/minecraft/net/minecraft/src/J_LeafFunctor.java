package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


abstract class J_LeafFunctor
    implements J_Functor
{

    J_LeafFunctor()
    {
    }

    public final Object func_27059_b(Object obj)
    {
        if(!func_27058_a(obj))
        {
            throw J_JsonNodeDoesNotMatchChainedJsonNodeSelectorException.func_27322_a(this);
        } else
        {
            return func_27063_c(obj);
        }
    }

    protected abstract Object func_27063_c(Object obj);
}
