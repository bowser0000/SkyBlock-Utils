package com.golem.skyblockutils;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.*;
import logger.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Config extends Vigilant
{
	public static String stonksFolder;
	public File CONFIG_FILE;


	@Property(
			type = PropertyType.SLIDER,
			name = "Time between AH Checks",
			description = "Time to wait between updating AH data. (0 turns it off)",
			category = "General",
			subcategory = "General",
			min = 1,
			max = 30
	)
	public int time_between_checks = 1;
	@Property(
			type = PropertyType.SLIDER,
			name = "Minimum Tier",
			description = "Minimum tier to consider for finding cheapest price per tier in /ap and /ep when tier is not specified.",
			category = "General",
			subcategory = "Kuudra Pricing",
			min = 1,
			max = 5
	)
	public int min_tier = 1;

	@Property(type = PropertyType.TEXT, name = "Attributes to Prioritize", description = "If the item is not a godroll and one attribute is from this list, it will value it over the other attribute no matter what. Separate multiple by _", category = "General", subcategory = "Kuudra Pricing")
	public String priorityAttributes = "";

	@Property(type = PropertyType.TEXT, name = "Excluded Attributes", description = "Attributes here will not be valued in any circumstance.", category = "General", subcategory = "Kuudra Pricing")
	public String attributesToExclude = "experience, arachno_resistance, blazing_resistance, undead_resistance, life_regeneration, ender_resistance";

	@Property(type = PropertyType.SWITCH, name = "Show Player Info", description = "Gives a summary on players joining kuudra parties.", category = "General", subcategory = "Party Finder")
	public boolean showKuudraPlayerInfo = false;
	@Property(type = PropertyType.SWITCH, name = "Show Own Player Info", description = "Shows your own player info when you join a party finder party.", category = "General", subcategory = "Party Finder")
	public boolean showOwnPlayerInfo = true;

	@Property(
			type = PropertyType.SLIDER,
			name = "Min. Godroll Price",
			description = "Minimum Price for a combo to be considered a godroll (in millions).",
			category = "General",
			subcategory = "Attribute Overlay",
			min = 1,
			max = 300
	)
	public int min_godroll_price = 50;
	@Property(
			type = PropertyType.SWITCH,
			name = "Display Attribute Overlay",
			description = "Show the best attribute on any attribute item (Will also show if it's a godroll).",
			category = "General",
			subcategory = "Attribute Overlay"
	)
	public boolean attribute_overlay = true;
	@Property(
			type = PropertyType.SWITCH,
			name = "Display Kuudra Overlay",
			description = "Accurate Kuudra Profit Overlay.",
			category = "General",
			subcategory = "Kuudra Profit Overlay"
	)
	public boolean kuudra_overlay = true;
	@Property(
			type = PropertyType.SWITCH,
			name = "Container Value",
			description = "Turn this on to be able to use the keybind (in controls) to show value of all items in any chest (backpack, ender chest, etc.).",
			category = "General",
			subcategory = "Kuudra Profit Overlay"
	)
	public boolean container_value = true;

	@Property(
			type = PropertyType.SELECTOR,
			name = "Kuudra Pet Rarity",
			description = "Enter Kuudra Pet Rarity to include the extra essence in calculation",
			category = "General",
			subcategory = "Kuudra Profit Overlay",
			options = {"Common", "Uncommon", "Rare", "Epic", "Legendary"}
	)
	public int kuudraPetRarity = 0;
	@Property(
			type = PropertyType.SLIDER,
			name = "Kuudra Pet Level",
			description = "Enter Kuudra Pet Level to include the extra essence in calculation",
			category = "General",
			subcategory = "Kuudra Profit Overlay",
			max = 100
	)
	public int kuudraPetLevel = 0;
	@Property(
			type = PropertyType.SELECTOR,
			name = "Book Valuation",
			description = "Choose whether books are insta-sold/sell offer.\n(Hardened Mana is always insta-sold)",
			category = "General",
			subcategory = "Kuudra Profit Overlay",
			options = {"Instant Sell", "Sell Offer"}
	)
	public int book_sell_method = 0;
	@Property(
			type = PropertyType.SWITCH,
			name = "Sell Essence",
			description = "Choose whether essence is considered in the value and profit of a chest.",
			category = "General",
			subcategory = "Kuudra Profit Overlay"
	)
	public boolean considerEssenceValue = true;

	@Property(
			type = PropertyType.SELECTOR,
			name = "Faction",
			description = "Needed to calculate key cost for kuudra.",
			category = "General",
			options = {"Mage", "Barbarian"},
			subcategory = "Kuudra Profit Overlay"
	)
	public int faction = 0;


	private void checkFolderExists() {
		Path directory = Paths.get(stonksFolder);
		if (!Files.exists(directory)) {
			try {
				Logger.warn("Created directory!", "\n", "Potentially config issue being initialized twice or first time using mod.");
				Files.createDirectory(directory);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Config() {
		super(new File(Config.stonksFolder + "config.toml"), "golemmod", new JVMAnnotationPropertyCollector(),
				new ConfigSorting());

		this.checkFolderExists();
		this.CONFIG_FILE = new File(Config.stonksFolder + "config.toml");
		this.initialize();
	}

	static {
		Config.stonksFolder = "config/golemmod/";
	}

	public static class ConfigSorting extends SortingBehavior {
		@NotNull
		@Override
		public Comparator<Category> getCategoryComparator() {
			return Comparator.comparingInt(o -> this.categories.indexOf(o.getName()));
		}

		private final List<String> categories = Arrays.asList("General"); //
	}
}