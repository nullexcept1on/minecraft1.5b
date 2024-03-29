package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public final class ItemStack
{

    public ItemStack(Block block)
    {
        this(block, 1);
    }

    public ItemStack(Block block, int i)
    {
        this(block.blockID, i, 0);
    }

    public ItemStack(Block block, int i, int j)
    {
        this(block.blockID, i, j);
    }

    public ItemStack(Item item)
    {
        this(item.shiftedIndex, 1, 0);
    }

    public ItemStack(Item item, int i)
    {
        this(item.shiftedIndex, i, 0);
    }

    public ItemStack(Item item, int i, int j)
    {
        this(item.shiftedIndex, i, j);
    }

    public ItemStack(int i, int j, int k)
    {
        stackSize = 0;
        itemID = i;
        stackSize = j;
        itemDamage = k;
    }

    public ItemStack(NBTTagCompound nbttagcompound)
    {
        stackSize = 0;
        readFromNBT(nbttagcompound);
    }

    public ItemStack splitStack(int i)
    {
        stackSize -= i;
        return new ItemStack(itemID, i, itemDamage);
    }

    public Item getItem()
    {
        return Item.itemsList[itemID];
    }

    public int getIconIndex()
    {
        return getItem().getIconIndex(this);
    }

    public boolean useItem(EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        boolean flag = getItem().onItemUse(this, entityplayer, world, i, j, k, l);
        if(flag)
        {
            entityplayer.addStat(StatList.field_25172_A[itemID], 1);
        }
        return flag;
    }

    public float getStrVsBlock(Block block)
    {
        return getItem().getStrVsBlock(this, block);
    }

    public ItemStack useItemRightClick(World world, EntityPlayer entityplayer)
    {
        return getItem().onItemRightClick(this, world, entityplayer);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("id", (short)itemID);
        nbttagcompound.setByte("Count", (byte)stackSize);
        nbttagcompound.setShort("Damage", (short)itemDamage);
        return nbttagcompound;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        itemID = nbttagcompound.getShort("id");
        stackSize = nbttagcompound.getByte("Count");
        itemDamage = nbttagcompound.getShort("Damage");
    }

    public int getMaxStackSize()
    {
        return getItem().getItemStackLimit();
    }

    public boolean func_21180_d()
    {
        return getMaxStackSize() > 1 && (!isItemStackDamageable() || !isItemDamaged());
    }

    public boolean isItemStackDamageable()
    {
        return Item.itemsList[itemID].getMaxDamage() > 0;
    }

    public boolean getHasSubtypes()
    {
        return Item.itemsList[itemID].getHasSubtypes();
    }

    public boolean isItemDamaged()
    {
        return isItemStackDamageable() && itemDamage > 0;
    }

    public int getItemDamageForDisplay()
    {
        return itemDamage;
    }

    public int getItemDamage()
    {
        return itemDamage;
    }

    public int getMaxDamage()
    {
        return Item.itemsList[itemID].getMaxDamage();
    }

    public void func_25190_a(int i, Entity entity)
    {
        if(!isItemStackDamageable())
        {
            return;
        }
        itemDamage += i;
        if(itemDamage > getMaxDamage())
        {
            if(entity instanceof EntityPlayer)
            {
                ((EntityPlayer)entity).addStat(StatList.field_25170_B[itemID], 1);
            }
            stackSize--;
            if(stackSize < 0)
            {
                stackSize = 0;
            }
            itemDamage = 0;
        }
    }

    public void hitEntity(EntityLiving entityliving, EntityPlayer entityplayer)
    {
        boolean flag = Item.itemsList[itemID].hitEntity(this, entityliving, entityplayer);
        if(flag)
        {
            entityplayer.addStat(StatList.field_25172_A[itemID], 1);
        }
    }

    public void func_25191_a(int i, int j, int k, int l, EntityPlayer entityplayer)
    {
        boolean flag = Item.itemsList[itemID].func_25008_a(this, i, j, k, l, entityplayer);
        if(flag)
        {
            entityplayer.addStat(StatList.field_25172_A[itemID], 1);
        }
    }

    public int getDamageVsEntity(Entity entity)
    {
        return Item.itemsList[itemID].getDamageVsEntity(entity);
    }

    public boolean canHarvestBlock(Block block)
    {
        return Item.itemsList[itemID].canHarvestBlock(block);
    }

    public void func_1097_a(EntityPlayer entityplayer)
    {
    }

    public void useItemOnEntity(EntityLiving entityliving)
    {
        Item.itemsList[itemID].saddleEntity(this, entityliving);
    }

    public ItemStack copy()
    {
        return new ItemStack(itemID, stackSize, itemDamage);
    }

    public static boolean areItemStacksEqual(ItemStack itemstack, ItemStack itemstack1)
    {
        if(itemstack == null && itemstack1 == null)
        {
            return true;
        }
        if(itemstack == null || itemstack1 == null)
        {
            return false;
        } else
        {
            return itemstack.isItemStackEqual(itemstack1);
        }
    }

    private boolean isItemStackEqual(ItemStack itemstack)
    {
        if(stackSize != itemstack.stackSize)
        {
            return false;
        }
        if(itemID != itemstack.itemID)
        {
            return false;
        }
        return itemDamage == itemstack.itemDamage;
    }

    public boolean isItemEqual(ItemStack itemstack)
    {
        return itemID == itemstack.itemID && itemDamage == itemstack.itemDamage;
    }

    public String func_20109_f()
    {
        return Item.itemsList[itemID].getItemNameIS(this);
    }

    public String toString()
    {
        return (new StringBuilder()).append(stackSize).append("x").append(Item.itemsList[itemID].getItemName()).append("@").append(itemDamage).toString();
    }

    public int stackSize;
    public int animationsToGo;
    public int itemID;
    private int itemDamage;
}
