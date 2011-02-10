/**
 * Project: teclp: Live player updates for tectonicus. A Bukkit plugin.
 * File name: TECLPPlayer.java
 * Description:  Stores the players data during runtime.
 *   
 * @author Valentin CHuravy, v.churavy [at] gmail [dot] com, Copyright (C) 2011.
 * @version v1.0
 *   
 * @see The GNU Public License (GPLv3)
 */

/* 
 * This file is part of teclp.
 * teclp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * teclp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with teclp.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.bukkit.wallnuss.teclp;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class TECLPPlayer {

	private String name;
	private double worldX;
	private double worldY;
	private double worldZ;
	private int air;
	private int health;
	private Player p_ref;
	private Configuration config;
	private double distance;
	
	public TECLPPlayer(Player p, double d) {
		super();
		p_ref=p;
		distance=d;
		
	}
	
	private void update (){
		this.name = p_ref.getName();
		this.worldX = p_ref.getLocation().getX();
		this.worldY = p_ref.getLocation().getY();
		this.worldZ = p_ref.getLocation().getZ();
		this.air = p_ref.getRemainingAir();
		this.health = p_ref.getHealth();
	}

	public boolean moved (){
		if(diff_significant(worldX, p_ref.getLocation().getX()) || diff_significant(worldY, p_ref.getLocation().getY())){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean diff_significant(double x, double y){
		double z = Math.abs(x-y);
		if (z >= distance){ 
			return true;
		}else {
			return false;
		}
		
	}

	public HashMap<String, String> getData() {
		update();
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("name", "\"" + name + "\"");
		args.put("worldX", ""+ worldX );
		args.put("worldY", ""+ worldY);
		args.put("worldZ", ""+ worldZ);
		args.put("health", ""+ health);
		args.put("air", ""+ air);
		return args;
	}
	
}