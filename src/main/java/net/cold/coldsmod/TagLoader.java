package net.cold.coldsmod;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class TagLoader {
    private static final Gson GSON = new Gson();

    public static Set<Item> loadItemsFromConfig(String fileName) {
        Set<Item> items = new HashSet<>();
        File file = FMLPaths.CONFIGDIR.get().resolve("coldsmod/" + fileName).toFile();

        if (!file.exists()) return items;

        try (FileReader reader = new FileReader(file)) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);
            JsonArray values = json.getAsJsonArray("values");

            for (int i = 0; i < values.size(); i++) {
                String entry = values.get(i).getAsString().trim();
                try {
                    ResourceLocation rl = new ResourceLocation(entry);
                    Item item = ForgeRegistries.ITEMS.getValue(rl);
                    if (item != null && item != Items.AIR) items.add(item);
                } catch (Exception ignored) {}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // System.out.println("[TagLoader] Loaded " + items.size() + " items from " + fileName);
        return items;
    }
}
