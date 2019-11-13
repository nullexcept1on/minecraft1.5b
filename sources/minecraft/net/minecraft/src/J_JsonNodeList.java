package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.ArrayList;
import java.util.Iterator;

final class J_JsonNodeList extends ArrayList
{

    J_JsonNodeList(Iterable iterable)
    {
        field_27405_a = iterable;
        //super();
        J_JsonNode j_jsonnode;
        for(Iterator iterator = field_27405_a.iterator(); iterator.hasNext(); add(j_jsonnode))
        {
            j_jsonnode = (J_JsonNode)iterator.next();
        }

    }

    final Iterable field_27405_a; /* synthetic field */
}
