package com.golem.skyblockutils.init;

import com.golem.skyblockutils.ChatListener;
import com.golem.skyblockutils.Main;
import com.golem.skyblockutils.events.ChannelHandlerInput;
import com.golem.skyblockutils.features.*;
import com.golem.skyblockutils.features.General.CustomEmotes;
import com.golem.skyblockutils.features.General.Perspective;
import com.golem.skyblockutils.features.KuudraFight.EnderPearl;
import com.golem.skyblockutils.features.KuudraFight.Kuudra;
import com.golem.skyblockutils.features.KuudraFight.Waypoints;
import com.golem.skyblockutils.models.Overlay.TextOverlay.*;
import com.golem.skyblockutils.utils.LocationUtils;
import com.golem.skyblockutils.utils.ToolTipListener;
import net.minecraftforge.common.MinecraftForge;

public class EventInit {
	public static void registerEvents() {
		Object[] listeners = {
				new Main(),
				new ToolTipListener(),
				new KuudraOverlay(),
				new KeybindsInit(),
				new ContainerValue(),
				new ChatListener(),
				new KuudraHealth(),
				new Kuudra(),
				new Waypoints(),
				new GuiEvent(),
				new BrokenHyp(),
				new CombineHelper(),
				new EnderPearl(),
				new LocationUtils(),
				new DescriptionHandler(),
				new ChatWaypoints(),
				new AutoUpdater(),
				new Perspective(),
				new CustomEmotes(),
				new ChannelHandlerInput()
		};

		for (Object listener : listeners) {
			MinecraftForge.EVENT_BUS.register(listener);
		}

	}

	public static void registerOverlays() {
		Object[] listeners = {
				new AlignOverlay(),
				new RagnarokOverlay(),
				new CratesOverlay(),
				new ChampionOverlay(),
				new FishingOverlay(),
				new AlertOverlay(),
				new SplitsOverlay(),
				new ReaperOverlay(),
				new DamageOverlay(),
				new FatalTempoOverlay(),
				new ProfitOverlay(),
				new ContainerOverlay(),
				new TPSOverlay(),
				new FPSOverlay(),
				new PingOverlay()
		};

		for (Object listener : listeners) {
			MinecraftForge.EVENT_BUS.register(listener);
		}
	}
}