package com.clovercard.clovergoshadow.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config {
    public static Config CONFIG = new Config(.01250f, 0.05f, 10f,0.25f, -0.5f, 1.2f, 0.5f, new ArrayList<>(), false, 1.2f, 0.8f, 1.2f, 0.8f, 1, new ArrayList<>(), 100, 10, 10, 5, 50, 25, 5, 15);
    private final float shadowSpawnPercent;
    private final float shadowTrainerPercent;
    private final float shadowRaidPiece;
    private final float shadowExpGainMultiplier;
    private final float purifiedExpGainMultiplier;
    private final float shadowEvGainMultiplier;
    private final float purifiedEvGainMultiplier;
    private final float shadowBoostAttack;
    private final float shadowBoostDefense;
    private final float shadowBoostSpecialAttack;
    private final float shadowBoostSpecialDefense;
    private final float shadowBoostSpeed;
    private final boolean useTranslatables;
    private final List<String> shadowBlackList;
    private final int baseExp;
    private final int expDifPerLevel;
    private final int expTrainerVictory;
    private final int expShadowPokemonCapture;
    private final int expPurifyShadowPokemon;
    private final int expShadowPokemonRaidCapture;
    private final int expShadowWildPokemonVictory;
    private final int expShadowRaidPokemonVictory;
    private final List<String> shadowFormWhiteList;
    public Config(float shadowSpawnPercent, float shadowTrainerPercent, float shadowRaidPiece, float shadowExpGainMultiplier, float shadowEvGainMultiplier, float purifiedExpGainMultiplier, float purifiedEvGainMultiplier, List<String> shadowBlackList, boolean useTranslatables, float shadowBoostAttack, float shadowBoostDefense, float shadowBoostSpecialAttack, float shadowBoostSpecialDefense, float shadowBoostSpeed, ArrayList<String> shadowFormWhiteList, int baseExp, int expDifPerLevel, int expTrainerVictory, int expShadowPokemonCapture, int expPurifyShadowPokemon, int expShadowPokemonRaidCapture, int expShadowWildPokemonVictory, int expShadowRaidPokemonVictory) {
        this.shadowSpawnPercent = shadowSpawnPercent;
        this.shadowTrainerPercent = shadowTrainerPercent;
        this.shadowRaidPiece = shadowRaidPiece;
        this.shadowExpGainMultiplier = shadowExpGainMultiplier;
        this.shadowEvGainMultiplier = shadowEvGainMultiplier;
        this.purifiedExpGainMultiplier = purifiedExpGainMultiplier;
        this.purifiedEvGainMultiplier = purifiedEvGainMultiplier;
        this.shadowBlackList = shadowBlackList;
        this.useTranslatables = useTranslatables;
        this.shadowBoostAttack = shadowBoostAttack;
        this.shadowBoostDefense = shadowBoostDefense;
        this.shadowBoostSpecialAttack = shadowBoostSpecialAttack;
        this.shadowBoostSpecialDefense = shadowBoostSpecialDefense;
        this.shadowBoostSpeed = shadowBoostSpeed;
        this.shadowFormWhiteList = shadowFormWhiteList;
        this.baseExp = baseExp;
        this.expDifPerLevel = expDifPerLevel;
        this.expTrainerVictory = expTrainerVictory;
        this.expShadowPokemonCapture = expShadowPokemonCapture;
        this.expPurifyShadowPokemon = expPurifyShadowPokemon;
        this.expShadowPokemonRaidCapture = expShadowPokemonRaidCapture;
        this.expShadowRaidPokemonVictory = expShadowRaidPokemonVictory;
        this.expShadowWildPokemonVictory = expShadowWildPokemonVictory;
        CONFIG = this;
    }
    public static void load() {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("config/clovergoshadow/config.json");
            CONFIG = gson.fromJson(reader, Config.class);
            reader.close();
        } catch (IOException err) {
            if (err instanceof FileNotFoundException) {
                try {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    File path = new File("config/clovergoshadow/");
                    if (!path.mkdirs()) {
                        System.err.println("Unable to make parent directories!");
                    }
                    File json = new File(path, "config.json");
                    json.createNewFile();
                    Writer writer = new FileWriter(json);
                    writer.write(gson.toJson(CONFIG));
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("An error occurred while trying to read the config file!");
            }
        }
    }

    public float getShadowSpawnPercent() {
        return shadowSpawnPercent;
    }

    public float getShadowTrainerPercent() {
        return shadowTrainerPercent;
    }

    public float getShadowRaidPiece() {
        return shadowRaidPiece;
    }

    public float getShadowExpGainMultiplier() {
        return shadowExpGainMultiplier;
    }

    public float getShadowEvGainMultiplier() {
        return shadowEvGainMultiplier;
    }

    public float getPurifiedExpGainMultiplier() {
        return purifiedExpGainMultiplier;
    }

    public float getPurifiedEvGainMultiplier() {
        return purifiedEvGainMultiplier;
    }

    public List<String> getShadowBlackList() {
        return shadowBlackList;
    }

    public boolean isUseTranslatables() {
        return useTranslatables;
    }

    public float getShadowBoostAttack() {
        return shadowBoostAttack;
    }

    public float getShadowBoostDefense() {
        return shadowBoostDefense;
    }

    public float getShadowBoostSpecialAttack() {
        return shadowBoostSpecialAttack;
    }

    public float getShadowBoostSpecialDefense() {
        return shadowBoostSpecialDefense;
    }

    public float getShadowBoostSpeed() {
        return shadowBoostSpeed;
    }

    public List<String> getShadowFormWhiteList() {
        return shadowFormWhiteList;
    }

    public int getBaseExp() {
        return baseExp;
    }

    public int getExpDifPerLevel() {
        return expDifPerLevel;
    }

    public int getExpTrainerVictory() {
        return expTrainerVictory;
    }

    public int getExpShadowPokemonCapture() {
        return expShadowPokemonCapture;
    }

    public int getExpPurifyShadowPokemon() {
        return expPurifyShadowPokemon;
    }
    public int getExpShadowPokemonRaidCapture() {
        return expShadowPokemonRaidCapture;
    }

    public int getExpShadowWildPokemonVictory() {
        return expShadowWildPokemonVictory;
    }

    public int getExpShadowRaidPokemonVictory() {
        return expShadowRaidPokemonVictory;
    }
}
