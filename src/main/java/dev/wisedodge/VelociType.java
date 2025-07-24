package dev.wisedodge;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VelociType implements ModInitializer {
	public static final String MOD_ID = "velocitype";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This is for code that runs on both client and server.
		// For a client-only mod, this can be left mostly empty.
		LOGGER.info("VelociType Initialized!");
	}
}