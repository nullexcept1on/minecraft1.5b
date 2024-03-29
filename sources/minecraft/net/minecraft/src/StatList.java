package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.*;

public class StatList
{

    public StatList()
    {
    }

    public static void func_27360_a()
    {
    }

    public static void func_25154_a()
    {
        field_25172_A = func_25155_a(field_25172_A, "stat.useItem", 0x1020000, 0, Block.blocksList.length);
        field_25170_B = func_25149_b(field_25170_B, "stat.breakItem", 0x1030000, 0, Block.blocksList.length);
        field_25166_D = true;
        func_25157_c();
    }

    public static void func_25151_b()
    {
        field_25172_A = func_25155_a(field_25172_A, "stat.useItem", 0x1020000, Block.blocksList.length, 32000);
        field_25170_B = func_25149_b(field_25170_B, "stat.breakItem", 0x1030000, Block.blocksList.length, 32000);
        field_25164_E = true;
        func_25157_c();
    }

    public static void func_25157_c()
    {
        if(!field_25166_D || !field_25164_E)
        {
            return;
        }
        HashSet hashset = new HashSet();
        IRecipe irecipe;
        for(Iterator iterator = CraftingManager.getInstance().func_25193_b().iterator(); iterator.hasNext(); hashset.add(Integer.valueOf(irecipe.func_25117_b().itemID)))
        {
            irecipe = (IRecipe)iterator.next();
        }

        ItemStack itemstack;
        for(Iterator iterator1 = FurnaceRecipes.smelting().func_25194_b().values().iterator(); iterator1.hasNext(); hashset.add(Integer.valueOf(itemstack.itemID)))
        {
            itemstack = (ItemStack)iterator1.next();
        }

        field_25158_z = new StatBase[32000];
        Iterator iterator2 = hashset.iterator();
        do
        {
            if(!iterator2.hasNext())
            {
                break;
            }
            Integer integer = (Integer)iterator2.next();
            if(Item.itemsList[integer.intValue()] != null)
            {
                String s = StatCollector.func_25199_a("stat.craftItem", new Object[] {
                    Item.itemsList[integer.intValue()].func_25009_k()
                });
                field_25158_z[integer.intValue()] = (new StatCrafting(0x1010000 + integer.intValue(), s, integer.intValue())).func_25068_c();
            }
        } while(true);
        replaceAllSimilarBlocks(field_25158_z);
    }

    private static StatBase[] func_25153_a(String s, int i)
    {
        StatBase astatbase[] = new StatBase[256];
        for(int j = 0; j < 256; j++)
        {
            if(Block.blocksList[j] != null && Block.blocksList[j].func_27033_k())
            {
                String s1 = StatCollector.func_25199_a(s, new Object[] {
                    Block.blocksList[j].func_25016_i()
                });
                astatbase[j] = (new StatCrafting(i + j, s1, j)).func_25068_c();
                field_25185_d.add((StatCrafting)astatbase[j]);
            }
        }

        replaceAllSimilarBlocks(astatbase);
        return astatbase;
    }

    private static StatBase[] func_25155_a(StatBase astatbase[], String s, int i, int j, int k)
    {
        if(astatbase == null)
        {
            astatbase = new StatBase[32000];
        }
        for(int l = j; l < k; l++)
        {
            if(Item.itemsList[l] == null)
            {
                continue;
            }
            String s1 = StatCollector.func_25199_a(s, new Object[] {
                Item.itemsList[l].func_25009_k()
            });
            astatbase[l] = (new StatCrafting(i + l, s1, l)).func_25068_c();
            if(l >= Block.blocksList.length)
            {
                field_25186_c.add((StatCrafting)astatbase[l]);
            }
        }

        replaceAllSimilarBlocks(astatbase);
        return astatbase;
    }

    private static StatBase[] func_25149_b(StatBase astatbase[], String s, int i, int j, int k)
    {
        if(astatbase == null)
        {
            astatbase = new StatBase[32000];
        }
        for(int l = j; l < k; l++)
        {
            if(Item.itemsList[l] != null && Item.itemsList[l].func_25007_g())
            {
                String s1 = StatCollector.func_25199_a(s, new Object[] {
                    Item.itemsList[l].func_25009_k()
                });
                astatbase[l] = (new StatCrafting(i + l, s1, l)).func_25068_c();
            }
        }

        replaceAllSimilarBlocks(astatbase);
        return astatbase;
    }

    private static void replaceAllSimilarBlocks(StatBase astatbase[])
    {
        replaceSimilarBlocks(astatbase, Block.waterStill.blockID, Block.waterMoving.blockID);
        replaceSimilarBlocks(astatbase, Block.lavaStill.blockID, Block.lavaStill.blockID);
        replaceSimilarBlocks(astatbase, Block.pumpkinLantern.blockID, Block.pumpkin.blockID);
        replaceSimilarBlocks(astatbase, Block.stoneOvenActive.blockID, Block.stoneOvenIdle.blockID);
        replaceSimilarBlocks(astatbase, Block.oreRedstoneGlowing.blockID, Block.oreRedstone.blockID);
        replaceSimilarBlocks(astatbase, Block.redstoneRepeaterActive.blockID, Block.redstoneRepeaterIdle.blockID);
        replaceSimilarBlocks(astatbase, Block.torchRedstoneActive.blockID, Block.torchRedstoneIdle.blockID);
        replaceSimilarBlocks(astatbase, Block.mushroomRed.blockID, Block.mushroomBrown.blockID);
        replaceSimilarBlocks(astatbase, Block.stairDouble.blockID, Block.stairSingle.blockID);
        replaceSimilarBlocks(astatbase, Block.grass.blockID, Block.dirt.blockID);
        replaceSimilarBlocks(astatbase, Block.tilledField.blockID, Block.dirt.blockID);
    }

    private static void replaceSimilarBlocks(StatBase astatbase[], int i, int j)
    {
        if(astatbase[i] != null && astatbase[j] == null)
        {
            astatbase[j] = astatbase[i];
            return;
        } else
        {
            field_25188_a.remove(astatbase[i]);
            field_25185_d.remove(astatbase[i]);
            field_25187_b.remove(astatbase[i]);
            astatbase[i] = astatbase[j];
            return;
        }
    }

    public static StatBase func_27361_a(int i)
    {
        return (StatBase)field_25169_C.get(Integer.valueOf(i));
    }

    protected static Map field_25169_C = new HashMap();
    public static List field_25188_a = new ArrayList();
    public static List field_25187_b = new ArrayList();
    public static List field_25186_c = new ArrayList();
    public static List field_25185_d = new ArrayList();
    public static StatBase field_25184_e = (new StatBasic(1000, StatCollector.func_25200_a("stat.startGame"))).func_27082_h().func_25068_c();
    public static StatBase field_25183_f = (new StatBasic(1001, StatCollector.func_25200_a("stat.createWorld"))).func_27082_h().func_25068_c();
    public static StatBase field_25182_g = (new StatBasic(1002, StatCollector.func_25200_a("stat.loadWorld"))).func_27082_h().func_25068_c();
    public static StatBase field_25181_h = (new StatBasic(1003, StatCollector.func_25200_a("stat.joinMultiplayer"))).func_27082_h().func_25068_c();
    public static StatBase field_25180_i = (new StatBasic(1004, StatCollector.func_25200_a("stat.leaveGame"))).func_27082_h().func_25068_c();
    public static StatBase field_25179_j;
    public static StatBase field_25178_k;
    public static StatBase field_25177_l;
    public static StatBase field_25176_m;
    public static StatBase field_25175_n;
    public static StatBase field_25174_o;
    public static StatBase field_25173_p;
    public static StatBase field_27364_r;
    public static StatBase field_27363_s;
    public static StatBase field_27362_t;
    public static StatBase field_25171_q = (new StatBasic(2010, StatCollector.func_25200_a("stat.jump"))).func_27082_h().func_25068_c();
    public static StatBase field_25168_r = (new StatBasic(2011, StatCollector.func_25200_a("stat.drop"))).func_27082_h().func_25068_c();
    public static StatBase field_25167_s = (new StatBasic(2020, StatCollector.func_25200_a("stat.damageDealt"))).func_25068_c();
    public static StatBase field_25165_t = (new StatBasic(2021, StatCollector.func_25200_a("stat.damageTaken"))).func_25068_c();
    public static StatBase field_25163_u = (new StatBasic(2022, StatCollector.func_25200_a("stat.deaths"))).func_25068_c();
    public static StatBase field_25162_v = (new StatBasic(2023, StatCollector.func_25200_a("stat.mobKills"))).func_25068_c();
    public static StatBase field_25161_w = (new StatBasic(2024, StatCollector.func_25200_a("stat.playerKills"))).func_25068_c();
    public static StatBase field_25160_x = (new StatBasic(2025, StatCollector.func_25200_a("stat.fishCaught"))).func_25068_c();
    public static StatBase field_25159_y[] = func_25153_a("stat.mineBlock", 0x1000000);
    public static StatBase field_25158_z[] = null;
    public static StatBase field_25172_A[] = null;
    public static StatBase field_25170_B[] = null;
    private static boolean field_25166_D = false;
    private static boolean field_25164_E = false;

    static 
    {
        field_25179_j = (new StatBasic(1100, StatCollector.func_25200_a("stat.playOneMinute"), StatBase.field_27086_j)).func_27082_h().func_25068_c();
        field_25178_k = (new StatBasic(2000, StatCollector.func_25200_a("stat.walkOneCm"), StatBase.field_27085_k)).func_27082_h().func_25068_c();
        field_25177_l = (new StatBasic(2001, StatCollector.func_25200_a("stat.swimOneCm"), StatBase.field_27085_k)).func_27082_h().func_25068_c();
        field_25176_m = (new StatBasic(2002, StatCollector.func_25200_a("stat.fallOneCm"), StatBase.field_27085_k)).func_27082_h().func_25068_c();
        field_25175_n = (new StatBasic(2003, StatCollector.func_25200_a("stat.climbOneCm"), StatBase.field_27085_k)).func_27082_h().func_25068_c();
        field_25174_o = (new StatBasic(2004, StatCollector.func_25200_a("stat.flyOneCm"), StatBase.field_27085_k)).func_27082_h().func_25068_c();
        field_25173_p = (new StatBasic(2005, StatCollector.func_25200_a("stat.diveOneCm"), StatBase.field_27085_k)).func_27082_h().func_25068_c();
        field_27364_r = (new StatBasic(2006, StatCollector.func_25200_a("stat.minecartOneCm"), StatBase.field_27085_k)).func_27082_h().func_25068_c();
        field_27363_s = (new StatBasic(2007, StatCollector.func_25200_a("stat.boatOneCm"), StatBase.field_27085_k)).func_27082_h().func_25068_c();
        field_27362_t = (new StatBasic(2008, StatCollector.func_25200_a("stat.pigOneCm"), StatBase.field_27085_k)).func_27082_h().func_25068_c();
        AchievementList.func_27374_a();
    }
}
