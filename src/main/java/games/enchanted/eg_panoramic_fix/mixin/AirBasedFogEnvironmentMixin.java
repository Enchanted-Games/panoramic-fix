package games.enchanted.eg_panoramic_fix.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import games.enchanted.eg_panoramic_fix.PanoramicFix;
import games.enchanted.eg_panoramic_fix.mixin.accessor.CameraAccessor;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.environment.AirBasedFogEnvironment;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AirBasedFogEnvironment.class)
public class AirBasedFogEnvironmentMixin {
    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getLookVector()Lorg/joml/Vector3f;"),
        method = "getBaseColor"
    )
    private Vector3f eg_panoramic_fix$forceCameraLookVectorInPanoramicMode(Camera instance, Operation<Vector3f> original, ClientLevel level) {
        if(!Minecraft.getInstance().gameRenderer.isPanoramicMode()) return original.call(instance);

        ((CameraAccessor) instance).eg_panoramic_fix$invokeSetRotation(PanoramicFix.playerYRotBeforePano.get(), PanoramicFix.playerXRotBeforePano.get());

        return original.call(instance);
    }
}
