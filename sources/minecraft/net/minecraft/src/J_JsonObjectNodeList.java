package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.*;

class J_JsonObjectNodeList extends HashMap
{

    J_JsonObjectNodeList(J_JsonObjectNodeBuilder j_jsonobjectnodebuilder)
    {
        field_27308_a = j_jsonobjectnodebuilder;
        //super();
        J_JsonFieldBuilder j_jsonfieldbuilder;
        for(Iterator iterator = J_JsonObjectNodeBuilder.func_27236_a(field_27308_a).iterator(); iterator.hasNext(); put(j_jsonfieldbuilder.func_27303_b(), j_jsonfieldbuilder.func_27302_c()))
        {
            j_jsonfieldbuilder = (J_JsonFieldBuilder)iterator.next();
        }

    }

    final J_JsonObjectNodeBuilder field_27308_a; /* synthetic field */
}
