package net.cold.coldsmod.stat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;

public class ArmorRarityModifier extends LootModifier {

    public static final Codec<ArmorRarityModifier> CODEC =
            RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, ArmorRarityModifier::new));

    protected ArmorRarityModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (ItemStack stack : generatedLoot) {
            if (stack.getItem() instanceof ArmorItem && !ItemRarityUtils.hasRarity(stack)) {
                ItemRarity rarity = ItemRarity.setCustomRarity();
                ItemRarityUtils.writeRarityToNBT(stack, rarity);
            }
        }
        return generatedLoot;
    }


    @Override
    public Codec<? extends LootModifier> codec() {
        return CODEC;
    }
}
