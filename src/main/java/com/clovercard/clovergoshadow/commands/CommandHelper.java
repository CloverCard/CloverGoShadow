package com.clovercard.clovergoshadow.commands;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.server.permission.PermissionAPI;

public class CommandHelper {
    public static boolean hasPermission(CommandSource src, int level, String node) {
        if(src.hasPermission(level)) return true;
        if(src.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) src.getEntity();
            return PermissionAPI.hasPermission(player, node);
        }
        if(src.getEntity() instanceof ClientPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) src.getEntity();
            return PermissionAPI.hasPermission(player, node);
        }
        return false;
    }
}
