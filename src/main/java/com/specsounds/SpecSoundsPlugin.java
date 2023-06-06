package com.specsounds;

import java.io.File;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.OverheadTextChanged;
import net.runelite.api.kit.KitType;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.StatChanged;
import net.runelite.api.ItemID;
import net.runelite.client.plugins.specialcounter.SpecialWeapon;


@Slf4j
@PluginDescriptor(
	name = "SpecSounds"
)
public class SpecSoundsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private SpecSoundsConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("SpecSounds started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("SpecSounds stopped!");
	}

	@Subscribe
	public void onSpec(StatChanged statChanged, KitType wep) {
		if(statChanged.getSkill().equals(Skill.WOODCUTTING) && statChanged.getBoostedLevel() == 3) {
			wep = KitType.WEAPON;
			if (wep.getIndex() == 6739) {
				client.getLocalPlayer().setOverheadText("YURR");
			}
		}
	}

	@Subscribe
	public void onOverheadTextChanged(OverheadTextChanged mesg) {
		if(mesg.getActor().equals(client.getLocalPlayer()) && mesg.getOverheadText().equals("Chop chop!")) { //Test for dragon axe
			client.getLocalPlayer().setOverheadText("Choppin ya ma");
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says ", null);
		}
	}

	@Provides
	SpecSoundsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SpecSoundsConfig.class);
	}
}
