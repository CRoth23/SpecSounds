package com.specsounds;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface SpecSoundsConfig extends Config
{
	@ConfigItem(
			keyName = "dragonAxeSpecSound",
			name = "Dragon axe spec",
			description = "New sound when player uses dragon axe special attack",
			position = 0
	)
	default boolean playDragonAxeSpecSound()
	{
		return true;
	}
}
