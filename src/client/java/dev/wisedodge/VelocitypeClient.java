package dev.wisedodge;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.render.RenderTickCounter;

public class VelocitypeClient implements ClientModInitializer {
	public static final MinecraftClient mc = MinecraftClient.getInstance();

	// Variables to track typing state
	public static long startTime = 0;
	public static int charCount = 0;

	@Override
	public void onInitializeClient() {
		HudRenderCallback.EVENT.register(this::renderTypingHud);
	}

	private void renderTypingHud(DrawContext drawContext, RenderTickCounter tickCounter) {
		if (mc.currentScreen instanceof ChatScreen) {
			String wpmText = "WPM: 0";

			if (startTime > 0 && charCount > 0) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				if (elapsedTime > 1000) { // Only calculate after 1 second
					// WPM = (characters / 5) / (seconds / 60)
					double wpm = (charCount / 5.0) / (elapsedTime / 60000.0);
					wpmText = "WPM: " + Math.round(wpm);
				}
			}

			int width = mc.getWindow().getScaledWidth();
			int height = mc.getWindow().getScaledHeight();

			drawContext.drawTextWithShadow(
							mc.textRenderer,
							wpmText,
							width - mc.textRenderer.getWidth(wpmText) - 10,
							height - 20,
							0xFFFFFF
			);
		} else {
			// Reset stats when the chat screen is closed
			startTime = 0;
			charCount = 0;
		}
	}
}