package org.pincraft
;

import com.thoughtcomplex.hairpin.ClassPattern
import com.thoughtcomplex.hairpin.Mapper

/**
 * Created by Falkreon on 7/3/2014.
 */
class PincraftMapper implements Mapper {
	private Map<String, List<ClassPattern>> patternMap = [
			"net/minecraft/src/PlayerList"        : [ ClassPattern.usesStrings("banned-players.json", "banned-ips.json", "ops.json",
												"whitelist.json", "yyyy-MM-dd \'at\' HH:mm:ss z") ],
			//"ChunkCoordinates"  : [ ClassPattern.usesStrings("Pos{x=",", y=",", z="),
			//                        ClassPattern.doesImplementNormal("java.lang.Comparable")],

			"net/minecraft/world/WorldServer"       : [ ClassPattern.usesStrings("doDaylightCycle", "doMobSpawning", "tickBlocks","tickChunk",
												"TickNextTick list out of synch", "Exception while ticking a block")],

			"net/minecraft/item/Item"              : [ ClassPattern.usesStrings("CB3F55D3-645C-4F38-A497-9C13A33DB5CF")],
			"net/minecraft/item/ItemStack"         : [ ClassPattern.usesStrings("#.###", "id", "Count", "Damage", "tag", "Unbreakable","x","@" )],
			"net/minecraft/block/Block"             : [ ClassPattern.usesStrings("air", "stone", "grass", "dirt", "stonebrick", "cobblestone",
												"wood", "planks", "flowing_lava", "activator_rail", "doTileDrops" )],
			//"net/minecraft/world/biome/BiomeGenBase"         : [ ClassPattern.usesStrings("Ocean", "Plains", "Desert", "Extreme Hills", "River", "Hell",
			//									"Sky", "Please avoid temperatures in the range 0.1 - 0.2 because of snow",
			//									"Biome ID is out of bounds: " )],
			"net/minecraft/client/creativetab/CreativeTabs"   : [ ClassPattern.usesStrings("buildingBlocks", "decorations", "redstone", "transportation",
												"search", "misc", "food", "tools", "combat", "brewing", "materials",
												"inventory")],
			"net/minecraft/tileentity/TileEntityEnderChest"        : [ ClassPattern.usesStrings("container.enderchest", "Slot")],
			"net/minecraft/util/RegistrySimple"    : [ ClassPattern.usesStrings("Adding duplicate key \'",  "\' to registry")],
			"net/minecraft/util/ChatComponentTranslation"       : [ ClassPattern.usesStrings("%","%%","%(?:(\\d+)\\\$)?([A-Za-z%]|\$)")],
			//"RegistryMaterials"  : [ ClassPattern.usesStrings("minecraft:"), ClassPattern.subclassOf("RegistrySimple")],

			//Entities!
			"net/minecraft/entity/Entity"            : [ ClassPattern.usesStrings("entityBaseTick","Checking entity block collision",
												"Colliding entity with block","Pos","Motion", "Rotation", "FallDistance",
												"Fire", "Air", "OnGround", "Dimension", "Invulnerable", "PortalCooldown")],
			"net/minecraft/entity/EntityLiving"      : [ ClassPattern.usesStrings("662A6B8D-DA3E-4C1C-8813-96EA6097278D")],
			//"EntityInsentient"  : [ ClassPattern.usesStrings("CanPickUpLoot", "Equipment", "CustomNameVisible", "Leash", "mobGriefing",
			//									"checkDespawn","mob tick","goalSelector")], //FAILED MAPPING
			"net/minecraft/entity/EntityCreature"    : [ ClassPattern.usesStrings("E199AD21-BA8A-4C53-8D13-6182D5C69D3A")],
			"net/minecraft/entity/monster/EntityMob"     : [ ClassPattern.subclassOf ("net/minecraft/entity/EntityCreature"),
			                        ClassPattern.usesStrings("game.hostile.swim", "game.hostile.swim.splash", "game.hostile.hurt",
					                            "game.hostile.die", "game.hostile.hurt.fall.big",
					                            "game.hostile.hurt.fall.small")],
			"net/minecraft/entity/passive/EntityAnimal"      : [ ClassPattern.usesStrings("InLove")],
			"net/minecraft/entity/passive/EntityVillager"    : [ ClassPattern.usesStrings("mob.villager")],
			"net/minecraft/entity/passive/EntityPig"         : [ ClassPattern.usesStrings("mob.pig.say", "mob.pig.death", "Saddle")],
			"net/minecraft/entity/player/EntityPlayer"      : [ ClassPattern.usesStrings("Ticking player", "Player being ticked", "playerGameType")],

			"net/minecraft/entity/passive/EntitySheep"       : [ ClassPattern.usesStrings("mob.sheep.shear", "Sheared", "Color", "mob.sheep.say")],
			//"EntityPotion"      : [ ClassPattern.subclassOf ("EntityProjectile"),
			//                        ClassPattern.usesStrings("potionValue", "Potion")],
			//"net/minecraft/entity/boss/EntityEnderDragon" : [ ClassPattern.usesStrings("mob.enderdragon.wings","mob.enderdragon.growl","mob.enderdragon.hit","hugeexplosion")],
			//"EntityArrow"       : [ ClassPattern.usesStrings("random.bowhit", "crit", "bubble", "pickup", "random.pop")],
			"net/minecraft/entity/passive/EntityBat"         : [ ClassPattern.usesStrings("mob.bat.idle", "mob.bat.hurt", "mob.bat.death", "BatFlags")],
			"net/minecraft/entity/monster/EntitySpider"      : [ ClassPattern.usesStrings("mob.spider.say", "mob.spider.death")],
			"net/minecraft/entity/monster/EntitySkeleton"    : [ ClassPattern.usesStrings("mob.skeleton.say", "mob.skeleton.step")],
			"net/minecraft/entity/monster/EntityCreeper"     : [ ClassPattern.usesStrings("mob.creeper.say", "mob.creeper.death","creeper.primed")],
			"net/minecraft/entity/monster/EntityEnderman"    : [ ClassPattern.usesStrings("020E0DFB-87AE-4653-9556-831010E291A0")],
			"net/minecraft/entity/passive/EntityChicken"     : [ ClassPattern.usesStrings("mob.chicken.plop", "mob.chicken.say", "mob.chicken.hurt", "mob.chicken.step","IsChickenJockey")],
			//"net/minecraft/entity/monster/EntityWitch"       : [ ClassPattern.usesStrings("mob.witch.hurt", "mob.witch.idle")],
			"net/minecraft/entity/monster/EntityZombie"      : [ ClassPattern.usesStrings("mob.zombie.hurt", "mob.zombie.say", "mob.zombie.step")],
			"net/minecraft/entity/boss/EntityWither"      : [ ClassPattern.usesStrings("mob.wither.hurt", "mob.wither.idle", "mob.wither.death")],
			"net/minecraft/entity/monster/EntityBlaze"       : [ ClassPattern.usesStrings("mob.blaze.breathe", "mob.blaze.hit", "mob.blaze.death")],

			//ALL THE ENUMS!
			//"EnumAnimation"      : [ ClassPattern.isEnum(), ClassPattern.usesStrings("none", "eat", "drink", "block", "bow")],
			//"EnumArmorMaterial"  : [ ClassPattern.isEnum(), ClassPattern.usesStrings("CLOTH", "CHAIN", "IRON", "GOLD", "DIAMOND")],
			"net/minecraft/entity/item/EntityPainting\$EnumArt"            : [ ClassPattern.isEnum(), ClassPattern.usesStrings("SkullAndRoses", "Kebab", "Aztec")],
			//"EnumBedResult"      : [ ClassPattern.isEnum(), ClassPattern.usesStrings("OK", "NOT_POSSIBLE_HERE", "NOT_POSSIBLE_NOW")],
			"net/minecraft/util/EnumChatFormatting" : [ ClassPattern.isEnum(), ClassPattern.usesStrings("BLACK", "DARK_BLUE", "DARK_GREEN")],
			"net/minecraft/entity/player/EntityPlayer\$EnumChatVisibility" : [ ClassPattern.isEnum(), ClassPattern.usesStrings("options.chat.visibility.full", "options.chat.visibility.system",
														   "options.chat.visibility.hidden")],
			"net/minecraft/event/ClickEvent\$Action"    : [ ClassPattern.isEnum(), ClassPattern.usesStrings("OPEN_URL", "OPEN_FILE", "RUN_COMMAND", "SUGGEST_COMMAND")],
			//"net/minecraft/network/NetHandlerPlayServer\$SwitchEnumState"  : [ ClassPattern.isEnum(), ClassPattern.usesStrings("PERFORM_RESPAWN", "REQUEST_STATS", "OPEN_INVENTORY_ACHIEVEMENT")],
			//"EnumCreatureType"   : [ ClassPattern.isEnum(), ClassPattern.usesStrings("monster", "creature", "ambient", "waterCreature")],
			"net/minecraft/world/EnumDifficulty"     : [ ClassPattern.isEnum(), ClassPattern.usesStrings("PEACEFUL", "EASY", "NORMAL", "HARD")],
			//"net/minecraft/entity/Entity\$EnumEntitySize"     : [ ClassPattern.isEnum(), ClassPattern.usesStrings("SIZE_1", "SIZE_2", "SIZE_3", "SIZE_4", "SIZE_5", "SIZE_6")],
			"net/minecraft/network/play/C02PacketUseEntity\$Action": [ ClassPattern.isEnum(), ClassPattern.usesStrings("INTERACT", "ATTACK")],
			//MULTIPLE MATCHES \/
			//"net/minecraft/util/EnumFacing"         : [ ClassPattern.isEnum(), ClassPattern.usesStrings("DOWN", "UP", "NORTH", "SOUTH", "EAST", "WEST")],
			"net/minecraft/item/ItemFishFood\$FishType"           : [ ClassPattern.isEnum(), ClassPattern.usesStrings("COD", "SALMON", "CLOWNFISH", "PUFFERFISH")],
			"net/minecraft/world/WorldSettings\$GameType"       : [ ClassPattern.isEnum(), ClassPattern.usesStrings("NOT_SET", "SURVIVAL", "CREATIVE", "ADVENTURE")],
			"net/minecraft/world/gen/layer/GenLayerEdge\$SwitchMode": [ ClassPattern.isEnum(), ClassPattern.usesStrings("COOL_WARM", "HEAT_ICE", "SPECIAL")],
			"net/minecraft/event/HoverEvent\$Action"    : [ ClassPattern.isEnum(), ClassPattern.usesStrings("SHOW_TEXT", "SHOW_ACHIEVEMENT", "SHOW_ITEM")],
			//"EnumItemRarity"     : [ ClassPattern.isEnum(), ClassPattern.usesStrings("common", "uncommon", "rare", "epic")],
			"net/minecraft/entity/EnumCreatureAttribute"    : [ ClassPattern.isEnum(), ClassPattern.usesStrings("UNDEFINED", "ARTHROPOD", "UNDEAD")],
			"net/minecraft/client/Minecraft\$SwitchMovingObjectType":[ ClassPattern.isEnum(), ClassPattern.usesStrings("MISS", "BLOCK", "ENTITY")],
			"net/minecraft/network/EnumConnectionState"       : [ ClassPattern.isEnum(), ClassPattern.usesStrings("HANDSHAKING", "PLAY", "STATUS", "LOGIN")],
			//"EnumSkyBlock"       : [ ClassPattern.isEnum(), ClassPattern.usesStrings("Sky", "Block")],

			//Command stuff!
			"net/minecraft/command/CommandBase"    : [ ClassPattern.usesStrings("commands.generic.num.invalid", "commands.generic.num.tooSmall",
												 "commands.generic.num.tooBig", "commands.generic.num.invalid",
												 "commands.generic.double.tooSmall",
												 "You must specify which player you wish to perform this action on.")],
			"net/minecraft/command/CommandHandler"     : [ ClassPattern.usesStrings("commands.generic.permission", "commands.generic.usage",
												 "commands.generic.exception",
			                                     "Couldn\'t process command: \'") ],
			//"CommandDispatcher"  : [ ClassPattern.usesStrings("chat.type.admin", "commandBlockOutput"),
			//                         ClassPattern.subclassOf("CommandHandler")],

			"net/minecraft/client/Minecraft"          : [
					ClassPattern.usesStrings("sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch",
			                                 "icons/icon_16x16.png", "icons/icon_32x32.png")],

			"net/minecraft/nbt/CompressedStreamTools" : [
					ClassPattern.usesStrings("Failed to delete ", "_tmp", "Root tag must be a named compound tag",
							"Loading NBT data")],
			"net/minecraft/nbt/NBTBase"               : [
					ClassPattern.usesStrings("END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE",
					                         "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]")],
			"net/minecraft/nbt/NBTTagString"          : [
					ClassPattern.usesStrings("Empty string not allowed", "\"", "")],
			"net/minecraft/nbt/JsonToNBT\$Primitive"  : [
			        ClassPattern.usesStrings("[-+]?[0-9]*\\.?[0-9]+[d|D]",
					                         "[-+]?[0-9]*\\.?[0-9]+[f|F]",
					                         "[-+]?[0-9]+[b|B]",
					                         "[-+]?[0-9]+[l|L]")],
			"net/minecraft/nbt/JsonToNBT"             : [
			        ClassPattern.usesStrings("Encountered multiple top tags, only one expected",
											 "tag","{","Illegal use of \\\": ","Unbalanced curly brackets {}: ")],
			"net/minecraft/nbt/NBTSizeTracker"        : [
			        ClassPattern.usesStrings("Tried to read NBT tag that was too big; tried to allocate: ",
					                         "bytes where max allowed: ")],
			"net/minecraft/nbt/NBTByteArray"          : [
					ClassPattern.usesStrings("[", " bytes]")],
			"net/minecraft/nbt/NBTTagCompound"        : [
					ClassPattern.usesStrings("Tried to read NBT tag with too high complexity, depth > 512",
					"Reading NBT data","Tag type found","Corrupt NBT tag","Tag type expected")],
			"net/minecraft/nbt/NBTTagEnd"             : [
			        ClassPattern.usesStrings("END"),
					ClassPattern.subclassOf("net/minecraft/nbt/NBTBase")],
			//"EmptyChunk"         : [ Characteristic.subclassOf("Chunk"), /* references java.util.Random and java.util.List */],

			//Not Working Yet
			//"RecipeFireworks"    : [ new Characteristic.Strings("Explosions", "Flight", "Fireworks","Flicker","Trail","Colors","FadeColors")],
			//"EnumToolMaterial"   : [ Characteristic.isEnum(), new Characteristic.Strings("WOOD","STONE","IRON","GOLD")],
			//"EnumProtocolState"  : [ Characteristic.isEnum(), new Characteristic.Strings("HELLO", "KEY", "AUTHENTICATING", "READY_TO_ACCEPT", "ACCEPTED")],
			//"EnumMobType"        : [ Characteristic.isEnum(), new Characteristic.Strings("everything", "mobs", "players")],
//"EntityAgeable" : [ Characteristic.subclassOf("Entity"), new Characteristic.Strings("Age") ],
//"EntityAmbient" : [ Characteristic.subclassOf("EntityInsentient")], // Characteristic.doesImplement("IAnimal")],
			"net/minecraft/entity/projectile/EntityArrow" : [
			        ClassPattern.usesStrings("random.bowhit","inTile","inData")],
			"net/minecraft/entity/projectile/EntityThrowable" : [
					ClassPattern.usesStrings("ownerName", "inTile")],
			"net/minecraft/entity/projectile/EntityEgg" : [
					ClassPattern.usesStrings("doMobSpawning"),
					ClassPattern.subclassOf("net/minecraft/entity/projectile/EntityThrowable")],
			"net/minecraft/entity/projectile/EntityPotion" : [
					ClassPattern.usesStrings("Potion"),
					ClassPattern.subclassOf("net/minecraft/entity/projectile/EntityThrowable")

			],
	];

	private Map<String, List<ClassPattern>> indirectPatterns = [
			//Tagging Classes
			"net/minecraft/entity/passive/IAnimals"       : [ ClassPattern.implementedBy("net/minecraft/entity/passive/EntityAnimal")],
			"net/minecraft/entity/IProjectile" : [ ClassPattern.implementedBy("net/minecraft/entity/projectile/EntityArrow")],
			//"IProjectile"   : [ ClassPattern.implementedBy("EntityProjectile")],

	];

	@Override
	Map<String, List<ClassPattern>> getPatterns() {
		return patternMap.clone();
	}

	@Override
	Map<String, List<ClassPattern>> getIndirectPatterns() {
		return indirectPatterns.clone();
	}
}
