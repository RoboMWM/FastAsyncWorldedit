package com.boydti.fawe.bukkit.regions;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.boydti.fawe.bukkit.FaweBukkit;
import com.boydti.fawe.object.FawePlayer;
import com.worldcretornica.plotme_core.Plot;
import com.worldcretornica.plotme_core.PlotMe_Core;
import com.worldcretornica.plotme_core.bukkit.PlotMe_CorePlugin;
import com.worldcretornica.plotme_core.bukkit.api.BukkitPlayer;
import com.worldcretornica.plotme_core.bukkit.api.BukkitWorld;

public class PlotMeFeature extends BukkitMaskManager implements Listener {
    FaweBukkit plugin;
    PlotMe_Core plotme;
    
    public PlotMeFeature(final Plugin plotmePlugin, final FaweBukkit p3) {
        super(plotmePlugin.getName());
        plotme = ((PlotMe_CorePlugin) plotmePlugin).getAPI();
        plugin = p3;
        
    }
    
    @Override
    public FaweMask getMask(final FawePlayer<Player> fp) {
        final Player player = fp.parent;
        final Location location = player.getLocation();
        final Plot plot = plotme.getPlotMeCoreManager().getPlotById(new BukkitPlayer(player));
        if (plot == null) {
            return null;
        }
        final boolean isallowed = plot.isAllowed(player.getUniqueId());
        if (isallowed) {
            final Location pos1 = new Location(location.getWorld(), plotme.getGenManager(player.getWorld().getName()).bottomX(plot.getId(), new BukkitWorld(player.getWorld())), 0, plotme
            .getGenManager(player.getWorld().getName()).bottomZ(plot.getId(), new BukkitWorld(player.getWorld())));
            final Location pos2 = new Location(location.getWorld(), plotme.getGenManager(player.getWorld().getName()).topX(plot.getId(), new BukkitWorld(player.getWorld())), 256, plotme
            .getGenManager(player.getWorld().getName()).topZ(plot.getId(), new BukkitWorld(player.getWorld())));
            return new FaweMask(pos1, pos2) {
                @Override
                public String getName() {
                    return plot.getId();
                }
            };
        }
        return null;
    }
}
