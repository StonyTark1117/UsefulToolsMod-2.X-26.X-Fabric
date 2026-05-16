package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;

/**
 * Fabric replacement for the NeoForge sibling's split
 * {@code ModBlockStateProvider} + {@code ModItemModelProvider}. The Fabric
 * {@link FabricModelProvider} exposes both halves on a single class, and the
 * Fabric mixins on {@code ModelProvider} already scope strict validation to
 * the {@link FabricPackOutput}'s mod-id — no manual {@code getKnownBlocks} /
 * {@code getKnownItems} overrides are needed.
 *
 * <ul>
 *   <li>Every block we register gets a trivial cube model except
 *       {@link ModBlocks#SPECTRAL_INFUSER}, whose blockstate + facing/lit model
 *       set live as hand-authored JSON under
 *       {@code src/main/resources/assets/usefultoolsmod/}. We {@code declareCustomModelItem}
 *       for its {@link BlockItem} so the items/ dispatcher still gets emitted.</li>
 *   <li>Standalone items are split: handheld tools (sword/pickaxe/axe/shovel/hoe)
 *       use {@link ModelTemplates#FLAT_HANDHELD_ITEM}, everything else uses
 *       {@link ModelTemplates#FLAT_ITEM}. Items with hand-authored geometry
 *       ({@code grenade}, {@code dynamite}) only get the dispatcher.</li>
 *   <li>{@link BlockItem}s without a generated block model
 *       ({@link ModBlocks#SPECTRAL_INFUSER}) are handled above; the rest are
 *       auto-bridged to their block model by {@code ItemInfoCollector}.</li>
 * </ul>
 */
public class ModModelProvider extends FabricModelProvider {

    private static final Set<String> CUSTOM_GEOMETRY_ITEMS = Set.of("grenade", "dynamite");

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModels) {
        blockModels.createTrivialCube(ModBlocks.RGOLDBLOCK);
        blockModels.createTrivialCube(ModBlocks.HRBLOCK);
        blockModels.createTrivialCube(ModBlocks.RGOLDORE);
        blockModels.createTrivialCube(ModBlocks.RGOLD_NETHER_ORE);
        blockModels.createTrivialCube(ModBlocks.RGOLD_END_ORE);
        blockModels.createTrivialCube(ModBlocks.RGOLD_DEEPSLATE_ORE);
        blockModels.createTrivialCube(ModBlocks.SEMBLOCK);
        blockModels.createTrivialCube(ModBlocks.SOBLOCK);
        blockModels.createTrivialCube(ModBlocks.LBLOCK);
        blockModels.createTrivialCube(ModBlocks.HGLOW_BLOCK);
        blockModels.createTrivialCube(ModBlocks.RAW_RGOLD_BLOCK);
        blockModels.createTrivialCube(ModBlocks.ECTOPLASM_BLOCK);
        blockModels.createTrivialCube(ModBlocks.REFINED_ECTOPLASM_BLOCK);
        blockModels.createTrivialCube(ModBlocks.HARDENED_COAL_BLOCK);
        blockModels.createTrivialCube(ModBlocks.COAL_DUST_BLOCK);
        blockModels.createTrivialCube(ModBlocks.OBSHARD_BLOCK);
        blockModels.createTrivialCube(ModBlocks.CALCIFIED_AMETHYST_BLOCK);
        blockModels.createTrivialCube(ModBlocks.GLACIAL_SHARD_BLOCK);
        blockModels.createTrivialCube(ModBlocks.POLISHED_QUARTZ_BLOCK);
        blockModels.createTrivialCube(ModBlocks.POLISHED_PRISMARINE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModels) {
        // SPECTRAL_INFUSER ships hand-written blockstate + models. We can't use
        // declareCustomModelItem because that emits items/spectral_infuser.json
        // pointing at item/spectral_infuser (the default getModelLocation path).
        // The baseline (from the NeoForge sibling port) points at
        // block/spectral_infuser instead so the in-world block model is reused
        // as the inventory icon. Drive the underlying ItemModelOutput sink
        // directly with ItemModelUtils.plainModel to produce the right JSON.
        Identifier spectralBlockModel = ModelLocationUtils.getModelLocation(ModBlocks.SPECTRAL_INFUSER);
        itemModels.itemModelOutput.accept(
                ModBlocks.SPECTRAL_INFUSER.asItem(),
                ItemModelUtils.plainModel(spectralBlockModel));

        for (Item item : BuiltInRegistries.ITEM) {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);
            if (!UsefultoolsMod.MOD_ID.equals(id.getNamespace())) {
                continue;
            }
            if (item instanceof BlockItem blockItem) {
                // The other 20 BlockItems are auto-bridged to their cube model by
                // ItemInfoCollector. SPECTRAL_INFUSER was handled above.
                if (blockItem.getBlock() == ModBlocks.SPECTRAL_INFUSER) continue;
                continue;
            }
            String path = id.getPath();
            if (CUSTOM_GEOMETRY_ITEMS.contains(path)) {
                itemModels.declareCustomModelItem(item);
                continue;
            }
            ModelTemplate template = isHandheldTool(path)
                    ? ModelTemplates.FLAT_HANDHELD_ITEM
                    : ModelTemplates.FLAT_ITEM;
            itemModels.generateFlatItem(item, template);
        }
    }

    private static boolean isHandheldTool(String path) {
        return path.endsWith("_sword")
                || path.endsWith("_pickaxe")
                || path.endsWith("_axe")
                || path.endsWith("_shovel")
                || path.endsWith("_hoe");
    }

    /** Compile-time safety: ensure {@link Block} symbol is exercised when we
     *  inevitably add per-block model registration. Suppressing the unused
     *  warning by referencing the type. */
    @SuppressWarnings("unused")
    private static final Class<?> BLOCK_REF = Block.class;
}
