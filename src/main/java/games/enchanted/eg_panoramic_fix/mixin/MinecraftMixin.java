package games.enchanted.eg_panoramic_fix.mixin;

import games.enchanted.eg_panoramic_fix.PanoramicFix;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow @Nullable public LocalPlayer player;

    @Inject(
        at = @At("HEAD"),
        method = "grabPanoramixScreenshot"
    )
    private void eg_panoramic_fix$setFacingDirectionsBeforePano(File file, CallbackInfoReturnable<Component> cir) {
        if(this.player == null) return;
        PanoramicFix.playerXRotBeforePano.set(this.player.getXRot());
        PanoramicFix.playerYRotBeforePano.set(this.player.getYRot());
    }

    @Inject(
        at = @At("TAIL"),
        method = "grabPanoramixScreenshot"
    )
    private void eg_panoramic_fix$resetFacingDirectionsAfterPano(File file, CallbackInfoReturnable<Component> cir) {
        if(this.player == null) return;
        PanoramicFix.playerXRotBeforePano.remove();
        PanoramicFix.playerYRotBeforePano.remove();
    }
}
