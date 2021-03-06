/*
 * Copyright (C) 2013-2014 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bukkitdev.arrows;

import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

/**
 *
 * @author daboross
 */
public class ArrowsPlugin extends JavaPlugin {

    private ItemArrowTracker arrow;
    private ArrowMetadata metadata;
    private ArrowAttackPerform attack;

    @Override
    public void onEnable() {
        arrow = new ItemArrowTracker();
        metadata = new ArrowMetadata( this );
        attack = new ArrowAttackPerform( this );
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents( new ArrowAttackListener( this ), this );
        new UltimateArrowCommand( this ).registerIfExists( getCommand( "ua" ) );
        MetricsLite metrics = null;
        try {
            metrics = new MetricsLite( this );
        } catch ( IOException ex ) {
            getLogger().log( Level.WARNING, "Unable to create Metrics", ex );
        }
        if ( metrics != null ) {
            metrics.start();
        }
    }

    @Override
    public void onDisable() {
    }

    public ItemArrowTracker getArrow() {
        return arrow;
    }

    public ArrowMetadata getMetadata() {
        return metadata;
    }

    public ArrowAttackPerform getAttack() {
        return attack;
    }
}