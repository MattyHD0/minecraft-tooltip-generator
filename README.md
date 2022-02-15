# Mineraft Tooltip Generator

![](minecraft_tooltip_generator.png)

## Examples:

```java
public class Examples {

    public static void diamondSwordExample() {

        TooltipBuilder tooltipBuilder = new TooltipBuilder()
                .setColorCode("&")
                .setName("&fDiamond Sword")
                .addLore("&fWhen in main hand:")
                .addLore("  &21.6 Attack Speed")
                .addLore("  &27 Attack Damage")
                .addLore("&8minecraft:diamond_sword")
                .addLore("&8NBT: 1 tag(s)");
A
        tooltipBuilder.save("diamond_sword.png");

    }

}

```

![](diamond_sword.png)

```java

public class Examples {

    public static void hypixelHyperionExample() {

        new TooltipBuilder()
                .setBetterMagic(true)
                .setName(" §5Suspicious Scylla §6✪✪✪✪✪")
                .setLore("§7Gear Score: §d991 §8(3094)\\n§7Damage: §c+332 §e(+30) §8(+1,211.8)\\n§7Strength: §c+201 §e(+30) §6[+5] §d(+16) §8(+733.65)\\n§7Crit Chance: §c+22% §9(+10%) §8(+33%)\\n§7Crit Damage: §c+277% §9(+110%) §8(+1,011.05%)\\n§7Intelligence: §a+50 §8(+182.5)\\n§7Ferocity: §a+30 §8(+45)\\n §5[§d⚔§5] §5[§d⚔§5]\\n\\n§d§l§d§lUltimate Wise V§9, §9Critical VII§9, §9Cubism V\\n§9Dragon Hunter I§9, §9Ender Slayer VI§9, §9Execute V\\n§9Experience IV§9, §9Fire Aspect II§9, §9First Strike IV\\n§9Giant Killer VII§9, §9Impaling III§9, §9Lethality VI\\n§9Life Steal IV§9, §9Looting IV§9, §9Luck VI\\n§9Scavenger IV§9, §9Smite VII§9, §9Telekinesis I\\n§9Thunderlord VI§9, §9Vampirism VI§9, §9Venomous V\\n\\n§b◆ Music Rune I\\n\\n§7Deals +§c50% §7damage to\\n§7Withers. Grants §c+1 §c❁ Damage\\n§c§7and §a+1 §9:skull_crossbones: Crit Damage §7per\\n§7§cCatacombs §7level.\\n\\n§aScroll Abilities:\\n§6Ability: Wither Impact §e§lRIGHT CLICK\\n§7Teleport §a10 blocks§7 ahead of\\n§7you. Then implode dealing\\n§7§c57,666.2 §7damage to nearby\\n§7enemies. Also applies the wither\\n§7shield scroll ability reducing\\n§7damage taken and granting an\\n§7absorption shield for §e5\\n§e§7seconds.\\n§8Mana Cost: §3150\\n\\n§9Suspicious Bonus\\n§7Increases weapon damage by\\n§7§c+15§7.\\n\\n§d§l§ka§r §d§l§d§lMYTHIC DUNGEON SWORD §d§l§ka")
                .save("hypixel_hyperion.png");

    }

}
```

![](hypixel_hyperion.png)