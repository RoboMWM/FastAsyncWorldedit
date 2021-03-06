package com.boydti.fawe.command;

import com.boydti.fawe.config.BBC;
import com.boydti.fawe.object.FaweCommand;
import com.boydti.fawe.object.FawePlayer;
import com.boydti.fawe.object.RegionWrapper;

public class WorldEditRegion extends FaweCommand {
    
    public WorldEditRegion() {
        super("fawe.worldeditregion");
    }
    
    @Override
    public boolean execute(final FawePlayer player, final String... args) {
        if (player == null) {
            return false;
        }
        RegionWrapper region = player.getLargestRegion();
        if (region == null) {
            BBC.NO_REGION.send(player);
            return false;
        }
        player.setSelection(region);
        BBC.SET_REGION.send(player);
        return true;
    }
    
    private boolean toggle(FawePlayer player) {
        if (player.hasPermission("fawe.bypass")) {
            player.setPermission("fawe.bypass", false);
            return false;
        } else {
            player.setPermission("fawe.bypass", true);
            return true;
        }
    }
}
