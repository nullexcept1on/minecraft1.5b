package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.*;

public abstract class Container
{

    public Container()
    {
        field_20123_d = new ArrayList();
        slots = new ArrayList();
        windowId = 0;
        field_20917_a = 0;
        field_20121_g = new ArrayList();
        field_20918_b = new HashSet();
    }

    protected void addSlot(Slot slot)
    {
        slot.slotNumber = slots.size();
        slots.add(slot);
        field_20123_d.add(null);
    }

    public void updateCraftingResults()
    {
        for(int i = 0; i < slots.size(); i++)
        {
            ItemStack itemstack = ((Slot)slots.get(i)).getStack();
            ItemStack itemstack1 = (ItemStack)field_20123_d.get(i);
            if(ItemStack.areItemStacksEqual(itemstack1, itemstack))
            {
                continue;
            }
            itemstack1 = itemstack != null ? itemstack.copy() : null;
            field_20123_d.set(i, itemstack1);
            for(int j = 0; j < field_20121_g.size(); j++)
            {
                ((ICrafting)field_20121_g.get(j)).func_20159_a(this, i, itemstack1);
            }

        }

    }

    public Slot getSlot(int i)
    {
        return (Slot)slots.get(i);
    }

    public ItemStack func_27279_a(int i)
    {
        Slot slot = (Slot)slots.get(i);
        if(slot != null)
        {
            return slot.getStack();
        } else
        {
            return null;
        }
    }

    public ItemStack func_27280_a(int i, int j, boolean flag, EntityPlayer entityplayer)
    {
        ItemStack itemstack = null;
        if(j == 0 || j == 1)
        {
            InventoryPlayer inventoryplayer = entityplayer.inventory;
            if(i == -999)
            {
                if(inventoryplayer.getItemStack() != null && i == -999)
                {
                    if(j == 0)
                    {
                        entityplayer.dropPlayerItem(inventoryplayer.getItemStack());
                        inventoryplayer.setItemStack(null);
                    }
                    if(j == 1)
                    {
                        entityplayer.dropPlayerItem(inventoryplayer.getItemStack().splitStack(1));
                        if(inventoryplayer.getItemStack().stackSize == 0)
                        {
                            inventoryplayer.setItemStack(null);
                        }
                    }
                }
            } else
            if(flag)
            {
                ItemStack itemstack1 = func_27279_a(i);
                if(itemstack1 != null)
                {
                    itemstack = itemstack1.copy();
                }
            } else
            {
                Slot slot = (Slot)slots.get(i);
                if(slot != null)
                {
                    slot.onSlotChanged();
                    ItemStack itemstack2 = slot.getStack();
                    ItemStack itemstack3 = inventoryplayer.getItemStack();
                    if(itemstack2 != null)
                    {
                        itemstack = itemstack2.copy();
                    }
                    if(itemstack2 == null)
                    {
                        if(itemstack3 != null && slot.isItemValid(itemstack3))
                        {
                            int k = j != 0 ? 1 : itemstack3.stackSize;
                            if(k > slot.getSlotStackLimit())
                            {
                                k = slot.getSlotStackLimit();
                            }
                            slot.putStack(itemstack3.splitStack(k));
                            if(itemstack3.stackSize == 0)
                            {
                                inventoryplayer.setItemStack(null);
                            }
                        }
                    } else
                    if(itemstack3 == null)
                    {
                        int l = j != 0 ? (itemstack2.stackSize + 1) / 2 : itemstack2.stackSize;
                        ItemStack itemstack5 = slot.decrStackSize(l);
                        if(itemstack5 != null && slot.func_25014_f())
                        {
                            entityplayer.addStat(StatList.field_25158_z[itemstack5.itemID], itemstack5.stackSize);
                        }
                        inventoryplayer.setItemStack(itemstack5);
                        if(itemstack2.stackSize == 0)
                        {
                            slot.putStack(null);
                        }
                        slot.onPickupFromSlot(inventoryplayer.getItemStack());
                    } else
                    if(slot.isItemValid(itemstack3))
                    {
                        if(itemstack2.itemID != itemstack3.itemID || itemstack2.getHasSubtypes() && itemstack2.getItemDamage() != itemstack3.getItemDamage())
                        {
                            if(itemstack3.stackSize <= slot.getSlotStackLimit())
                            {
                                ItemStack itemstack4 = itemstack2;
                                slot.putStack(itemstack3);
                                inventoryplayer.setItemStack(itemstack4);
                            }
                        } else
                        {
                            int i1 = j != 0 ? 1 : itemstack3.stackSize;
                            if(i1 > slot.getSlotStackLimit() - itemstack2.stackSize)
                            {
                                i1 = slot.getSlotStackLimit() - itemstack2.stackSize;
                            }
                            if(i1 > itemstack3.getMaxStackSize() - itemstack2.stackSize)
                            {
                                i1 = itemstack3.getMaxStackSize() - itemstack2.stackSize;
                            }
                            itemstack3.splitStack(i1);
                            if(itemstack3.stackSize == 0)
                            {
                                inventoryplayer.setItemStack(null);
                            }
                            itemstack2.stackSize += i1;
                        }
                    } else
                    if(itemstack2.itemID == itemstack3.itemID && itemstack3.getMaxStackSize() > 1 && (!itemstack2.getHasSubtypes() || itemstack2.getItemDamage() == itemstack3.getItemDamage()))
                    {
                        int j1 = itemstack2.stackSize;
                        if(j1 > 0 && j1 + itemstack3.stackSize <= itemstack3.getMaxStackSize())
                        {
                            itemstack3.stackSize += j1;
                            ItemStack itemstack6 = itemstack2.splitStack(j1);
                            if(itemstack6 != null && slot.func_25014_f())
                            {
                                entityplayer.addStat(StatList.field_25158_z[itemstack6.itemID], itemstack6.stackSize);
                            }
                            if(itemstack2.stackSize == 0)
                            {
                                slot.putStack(null);
                            }
                            slot.onPickupFromSlot(inventoryplayer.getItemStack());
                        }
                    }
                }
            }
        }
        return itemstack;
    }

    public void onCraftGuiClosed(EntityPlayer entityplayer)
    {
        InventoryPlayer inventoryplayer = entityplayer.inventory;
        if(inventoryplayer.getItemStack() != null)
        {
            entityplayer.dropPlayerItem(inventoryplayer.getItemStack());
            inventoryplayer.setItemStack(null);
        }
    }

    public void onCraftMatrixChanged(IInventory iinventory)
    {
        updateCraftingResults();
    }

    public void putStackInSlot(int i, ItemStack itemstack)
    {
        getSlot(i).putStack(itemstack);
    }

    public void putStacksInSlots(ItemStack aitemstack[])
    {
        for(int i = 0; i < aitemstack.length; i++)
        {
            getSlot(i).putStack(aitemstack[i]);
        }

    }

    public void func_20112_a(int i, int j)
    {
    }

    public short func_20111_a(InventoryPlayer inventoryplayer)
    {
        field_20917_a++;
        return field_20917_a;
    }

    public void func_20113_a(short word0)
    {
    }

    public void func_20110_b(short word0)
    {
    }

    public abstract boolean isUsableByPlayer(EntityPlayer entityplayer);

    public List field_20123_d;
    public List slots;
    public int windowId;
    private short field_20917_a;
    protected List field_20121_g;
    private Set field_20918_b;
}
