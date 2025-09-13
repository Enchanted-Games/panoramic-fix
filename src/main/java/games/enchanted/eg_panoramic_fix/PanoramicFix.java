package games.enchanted.eg_panoramic_fix;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PanoramicFix implements ModInitializer {
	public static final String MOD_ID = "eg_panoramic_fix";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ThreadLocal<Float> playerXRotBeforePano = new ThreadLocal<>();
	public static final ThreadLocal<Float> playerYRotBeforePano = new ThreadLocal<>();

	@Override
	public void onInitialize() {
	}
}