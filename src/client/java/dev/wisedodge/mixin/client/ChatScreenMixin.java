package dev.wisedodge.mixin.client;

import dev.wisedodge.VelocitypeClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {

  @Inject(method = "keyPressed", at = @At("HEAD"))
  private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
    // Start the timer on the first key press
    if (VelocitypeClient.startTime == 0 && keyCode != GLFW.GLFW_KEY_ESCAPE) {
      VelocitypeClient.startTime = System.currentTimeMillis();
    }

    // Handle backspace
    if (keyCode == GLFW.GLFW_KEY_BACKSPACE) {
      if (VelocitypeClient.charCount > 0) {
        VelocitypeClient.charCount--;
      }
    }
    // Use a simple check for printable characters. It's not perfect but it will compile.
    else if (keyCode >= 32 && keyCode <= 255) {
      VelocitypeClient.charCount++;
    }
  }
}