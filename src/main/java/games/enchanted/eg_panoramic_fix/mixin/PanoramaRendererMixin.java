package games.enchanted.eg_panoramic_fix.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.renderer.PanoramaRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PanoramaRenderer.class)
public class PanoramaRendererMixin {
	@WrapOperation(
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"),
		method = "render"
	)
	private Object init(OptionInstance instance, Operation<Double> original) {
		return original.call(instance) * 2;
	}
}