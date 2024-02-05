package com.mememan.persistency;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class PersistencyConstants {
	public static final String MOD_ID = "persistency";
	public static final String MOD_NAME = "Persistency";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static ResourceLocation prefix(String path) {
		return new ResourceLocation(MOD_ID, path.toLowerCase(Locale.ROOT));
	}
}