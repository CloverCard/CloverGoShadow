package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.pixelmon.api.events.PixelmonUpdateEvent;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class DetectShadowPokemon {
    @SubscribeEvent
    public void onUpdate(PixelmonUpdateEvent event) {
        //Check for valid ribbon
        if(RibbonHelper.isWearingShadowRibbon(event.pokemon.getPokemon())) {

            //Create Shadowy Cloud Effect On Pokemon
            PixelmonEntity pokemon = event.pokemon;
            Random rand = RandomHelper.getRandom();
            World modWorld = pokemon.getCommandSenderWorld();

            //Multiplayer
            if(modWorld instanceof ServerWorld) {
                ServerWorld world = (ServerWorld) modWorld;
                //Borrow Gastly particle physics to create Shadowy Aura
                float var2 = 0.6F;
                float var4 = rand.nextFloat() * 3.1415927F * 2.0F;
                float var42 = rand.nextFloat() * 3.1415927F * 2.0F;
                float var5 = rand.nextFloat() + 0.5F;
                float var52 = rand.nextFloat() + 0.5F;
                float var6 = MathHelper.sin(var4) * var2 * 0.7F * var5;
                float var62 = MathHelper.sin(var42) * var2 * 0.7F * var52;
                float var7 = MathHelper.cos(var4) * var2 * 0.5F * var5;
                float var72 = MathHelper.cos(var42) * var2 * 0.5F * var52;
                float var8 = rand.nextFloat() * var2 * 1.4F - 0.2F;
                float var82 = rand.nextFloat() * var2 * 1.2F;
                double gravity = -0.5;
                RedstoneParticleData data = new RedstoneParticleData(0.4f, 0.28f, 1f, 1.7F);
                int i;
                if (pokemon.getPokemon().getGrowth() == EnumGrowth.Enormous) {
                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(data, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 1, 0, 0, 0, gravity);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(data, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 1, 0, 0, 0, gravity);
                    }
                } else if (pokemon.getPokemon().getGrowth() == EnumGrowth.Huge) {
                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(data, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 1, 0, 0, 0, gravity);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(data, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 1, 0, 0, 0, gravity);
                    }
                } else if (pokemon.getPokemon().getGrowth() == EnumGrowth.Pygmy) {
                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(data, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 1, 0, 0, 0, gravity);
                    }
                } else {
                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(data, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 1, 0, 0, 0, gravity);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(data, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 1, 0, 0, 0, gravity);
                    }
                }
                //Singleplayer
            } else if (modWorld instanceof ClientWorld) {
                ClientWorld world = (ClientWorld) modWorld;
                //Borrow Gastly particle physics to create Shadowy Aura
                float var2 = 0.6F;
                float var4 = rand.nextFloat() * 3.1415927F * 2.0F;
                float var42 = rand.nextFloat() * 3.1415927F * 2.0F;
                float var5 = rand.nextFloat() + 0.5F;
                float var52 = rand.nextFloat() + 0.5F;
                float var6 = MathHelper.sin(var4) * var2 * 0.7F * var5;
                float var62 = MathHelper.sin(var42) * var2 * 0.7F * var52;
                float var7 = MathHelper.cos(var4) * var2 * 0.5F * var5;
                float var72 = MathHelper.cos(var42) * var2 * 0.5F * var52;
                float var8 = rand.nextFloat() * var2 * 1.4F - 0.2F;
                float var82 = rand.nextFloat() * var2 * 1.2F;
                RedstoneParticleData data = new RedstoneParticleData(0.4f, 0.28f, 1f, 1.7F);
                int i;
                if (pokemon.getPokemon().getGrowth() == EnumGrowth.Enormous) {
                    for (i = 0; i < 1; ++i) {
                        world.addParticle(data, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 0, 0, 0);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.addParticle(data, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 0, 0, 0);
                    }
                } else if (pokemon.getPokemon().getGrowth() == EnumGrowth.Huge) {
                    for (i = 0; i < 1; ++i) {
                        world.addParticle(data, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 0, 0, 0);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.addParticle(data, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 0, 0, 0);
                    }
                } else if (pokemon.getPokemon().getGrowth() == EnumGrowth.Pygmy) {
                    for (i = 0; i < 1; ++i) {
                        world.addParticle(data, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645,0, 0, 0);
                    }
                } else {
                    for (i = 0; i < 1; ++i) {
                        world.addParticle(data, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645,0, 0, 0);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.addParticle(data, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 0, 0, 0);
                    }
                }
            }
        }
        else if(RibbonHelper.isWearingPurifiedRibbon(event.pokemon.getPokemon())) {
            //Create Purified Cloud Effect On Pokemon
            PixelmonEntity pokemon = event.pokemon;
            Random rand = RandomHelper.getRandom();
            World modWorld = pokemon.getCommandSenderWorld();
            if (modWorld instanceof ServerWorld) {
                ServerWorld world = (ServerWorld) pokemon.getCommandSenderWorld();

                //Borrow Gastly particle physics to create Sparkly Aura
                float var2 = 0.6F;
                float var4 = rand.nextFloat() * 3.1415927F * 2.0F;
                float var42 = rand.nextFloat() * 3.1415927F * 2.0F;
                float var5 = rand.nextFloat() + 0.5F;
                float var52 = rand.nextFloat() + 0.5F;
                float var6 = MathHelper.sin(var4) * var2 * 0.7F * var5;
                float var62 = MathHelper.sin(var42) * var2 * 0.7F * var52;
                float var7 = MathHelper.cos(var4) * var2 * 0.5F * var5;
                float var72 = MathHelper.cos(var42) * var2 * 0.5F * var52;
                float var8 = rand.nextFloat() * var2 * 1.4F - 0.2F;
                float var82 = rand.nextFloat() * var2 * 1.2F;
                double gravity = -0.5;
                int i;
                if (pokemon.getPokemon().getGrowth() == EnumGrowth.Enormous) {
                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 1, 0, 0, 0, gravity);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 1, 0, 0, 0, gravity);
                    }
                } else if (pokemon.getPokemon().getGrowth() == EnumGrowth.Huge) {
                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 1, 0, 0, 0, gravity);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 1, 0, 0, 0, gravity);
                    }
                } else if (pokemon.getPokemon().getGrowth() == EnumGrowth.Pygmy) {
                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 1, 0, 0, 0, gravity);
                    }
                } else {
                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 1, 0, 0, 0, gravity);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.sendParticles(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 1, 0, 0, 0, gravity);
                    }
                }
            }
            else if (modWorld instanceof ClientWorld) {
                ClientWorld world = (ClientWorld) modWorld;
                //Borrow Gastly particle physics to create Shadowy Aura
                float var2 = 0.6F;
                float var4 = rand.nextFloat() * 3.1415927F * 2.0F;
                float var42 = rand.nextFloat() * 3.1415927F * 2.0F;
                float var5 = rand.nextFloat() + 0.5F;
                float var52 = rand.nextFloat() + 0.5F;
                float var6 = MathHelper.sin(var4) * var2 * 0.7F * var5;
                float var62 = MathHelper.sin(var42) * var2 * 0.7F * var52;
                float var7 = MathHelper.cos(var4) * var2 * 0.5F * var5;
                float var72 = MathHelper.cos(var42) * var2 * 0.5F * var52;
                float var8 = rand.nextFloat() * var2 * 1.4F - 0.2F;
                float var82 = rand.nextFloat() * var2 * 1.2F;
                int i;
                if (pokemon.getPokemon().getGrowth() == EnumGrowth.Enormous) {
                    for (i = 0; i < 1; ++i) {
                        world.addParticle(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 0, 0, 0);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.addParticle(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 0, 0, 0);
                    }
                } else if (pokemon.getPokemon().getGrowth() == EnumGrowth.Huge) {
                    for (i = 0; i < 1; ++i) {
                        world.addParticle(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645, 0, 0, 0);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.addParticle(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 0, 0, 0);
                    }
                } else if (pokemon.getPokemon().getGrowth() == EnumGrowth.Pygmy) {
                    for (i = 0; i < 1; ++i) {
                        world.addParticle(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645,0, 0, 0);
                    }
                } else {
                    for (i = 0; i < 1; ++i) {
                        world.addParticle(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var6 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var8, pokemon.getZ() + (double) var7 + 0.4000000059604645,0, 0, 0);
                    }

                    for (i = 0; i < 1; ++i) {
                        world.addParticle(ParticleTypes.COMPOSTER, pokemon.getX() + (double) var62 + 0.20000000298023224, pokemon.getY() + 0.5 + (double) var82, pokemon.getZ() + (double) var72 - 0.6000000238418579, 0, 0, 0);
                    }
                }
            }
        }
    }
}
