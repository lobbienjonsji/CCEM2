package theSynthesist;

//TODO: Unify all the mod initializations and whatever into one central file?

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theSynthesist.characters.TheSynthesist;
import theextravagant.util.IDCheckDontTouchPls;
import theextravagant.util.TextureLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class SynthesistMain implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {

    public static final Logger logger = LogManager.getLogger(TheSynthesist.class.getName());
    private static String modID;

    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true;

    private static final String MODNAME = "The Synthesist";
    private static final String AUTHOR = "EnderGrimm, Lobbienjonsji, Left Click";
    private static final String DESCRIPTION = "Synthesist Description text";
    //TODO: fix description text

    public static final Color SYNTHESIST_COLOR = CardHelper.getColor(255.0f, 255.0f, 255.0f);
    //TODO: proper color

    private static final String ATTACK_SYNTHESIST_COLOR = "theSynthesistResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_SYNTHESIST_COLOR = "theSynthesistResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_SYNTHESIST_COLOR = "theSynthesistResources/images/512/bg_power_default_gray.png";

    private static final String ENERGY_ORB_SYNTHESIST_COLOR = "theSynthesistResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "theSynthesistResources/images/512/card_small_orb.png";

    private static final String ATTACK_SYNTHESIST_COLOR_PORTRAIT = "theSynthesistResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_SYNTHESIST_COLOR_PORTRAIT = "theSynthesistResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_SYNTHESIST_COLOR_PORTRAIT = "theSynthesistResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_SYNTHESIST_COLOR_PORTRAIT = "theSynthesistResources/images/1024/card_default_gray_orb.png";

    private static final String THE_SYNTHESIST_BUTTON = "theSynthesistResources/images/charSelect/synthesistCharButton.png";
    private static final String THE_SYNTHESIST_PORTRAIT = "theSynthesistResources/images/charSelect/synthesistCharPortraitBG.png";
    public static final String THE_SYNTHESIST_SHOULDER_1 = "theSynthesistResources/images/char/synthesistChar/shoulder.png";
    public static final String THE_SYNTHESIST_SHOULDER_2 = "theSynthesistResources/images/char/synthesistChar/shoulder2.png";
    public static final String THE_SYNTHESIST_CORPSE = "theSynthesistResources/images/char/synthesistChar/corpse.png";

    public static final String BADGE_IMAGE = "theSynthesistResources/images/Badge.png";

    //public static final String THE_DEFAULT_SKELETON_ATLAS = "theSynthesistResources/images/char/SynthesistCharacter/skeleton.atlas";
    //public static final String THE_DEFAULT_SKELETON_JSON = "theSynthesistResources/images/char/SynthesistCharacter/skeleton.json";

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }

    public SynthesistMain() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);
        
        
        setModID("theSynthesist");
        
        
        logger.info("Done subscribing");
        
        logger.info("Creating the color " + TheSynthesist.Enums.SYNTHESIST_COLOR.toString());
        
        BaseMod.addColor(TheSynthesist.Enums.SYNTHESIST_COLOR, SYNTHESIST_COLOR, SYNTHESIST_COLOR, SYNTHESIST_COLOR,
                SYNTHESIST_COLOR, SYNTHESIST_COLOR, SYNTHESIST_COLOR, SYNTHESIST_COLOR,
                ATTACK_SYNTHESIST_COLOR, SKILL_SYNTHESIST_COLOR, POWER_SYNTHESIST_COLOR, ENERGY_ORB_SYNTHESIST_COLOR,
                ATTACK_SYNTHESIST_COLOR_PORTRAIT, SKILL_SYNTHESIST_COLOR_PORTRAIT, POWER_SYNTHESIST_COLOR_PORTRAIT,
                ENERGY_ORB_SYNTHESIST_COLOR_PORTRAIT, CARD_ENERGY_ORB);
        
        logger.info("Done creating the color");
        
        
        logger.info("Adding mod settings");
        
        
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("theSynthesist", "theSynthesistConfig", theDefaultDefaultSettings);
            
            config.load();
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
    }
    
    public static void setModID(String ID) {
        Gson coolG = new Gson();
        
        InputStream in = SynthesistMain.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }
    
    public static String getModID() {
        return modID;
    }
    
    private static void pathCheck() {
        Gson coolG = new Gson();
        
        InputStream in = TheSynthesist.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = TheSynthesist.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }
            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }
    }
    
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("Initializing The Synthesist. Feed Cookies to continue. Left Click was here");
        System.out.println(
                        "         _____\n" +
                        "      __/   _/   \n" +
                        "   __/    _/   \n" +
                        "  /      /         \n" +
                        " /     ()\\___\n" +
                        "|            \\__            _\n" +
                        "|               \\___     __/ |\n" +
                        "|   ()        ()    \\___/    |\n" +
                        "|                            |\n" +
                        "|                    ()      |\n" +
                        " \\         ()                /\n" +
                        "  \\__                     __/\n" +
                        "     \\__         ()    __/\n" +
                        "        \\_____________/");
        SynthesistMain synthesistMod = new SynthesistMain();
        logger.info("The Synthesist Initialized - Cookies have been fed");
    }
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + TheSynthesist.Enums.THE_SYNTHESIST.toString());
        
        BaseMod.addCharacter(new TheSynthesist("The Synthesist", TheSynthesist.Enums.THE_SYNTHESIST),
                THE_SYNTHESIST_BUTTON, THE_SYNTHESIST_PORTRAIT, TheSynthesist.Enums.THE_SYNTHESIST);
        
        receiveEditPotions();
        logger.info("Added " + TheSynthesist.Enums.THE_SYNTHESIST.toString());
    }
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        
        ModPanel settingsPanel = new ModPanel();
        
        
        /*ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                enablePlaceholder,
                settingsPanel,
                (label) -> {
                },
                (button) -> {
                    
                    enablePlaceholder = button.enabled;
                    try {
                        
                        SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
                        config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        */
        //settingsPanel.addUIElement(enableNormalsButton);
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        
        //BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);
        
        
        logger.info("Done loading badge Image and mod options");
    }
    
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        
        
        //BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, TheSynthesist.Enums.THE_SPELL_SCRIBE);
        
        logger.info("Done editing potions");
    }
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        //addAndUnlockCharRelic(new PlaceholderRelic());

        /*
        BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);
        UnlockTracker.markRelicAsSeen(BottledPlaceholderRelic.ID);
        */
        logger.info("Done adding relics!");
    }
    
    @Override
    public void receiveEditCards() {

        pathCheck();

        logger.info("Adding variables");
        
        //BaseMod.addDynamicVariable(new DefaultCustomVariable());
        //BaseMod.addDynamicVariable(new SecondMagicNumber());
        logger.info("Done adding variables");
        
        logger.info("Adding cards");

        //addAndUnlockCard(new Strike());
        //addAndUnlockCard(new Defend());


        /*addAndUnlockCard(new OrbSkill());
        addAndUnlockCard(new DefaultSecondMagicNumberSkill());
        addAndUnlockCard(new DefaultCommonAttack());
        addAndUnlockCard(new DefaultAttackWithVariable());
        addAndUnlockCard(new DefaultCommonSkill());
        addAndUnlockCard(new DefaultCommonPower());
        addAndUnlockCard(new DefaultUncommonSkill());
        addAndUnlockCard(new DefaultUncommonAttack());
        addAndUnlockCard(new DefaultUncommonPower());
        addAndUnlockCard(new DefaultRareAttack());
        addAndUnlockCard(new DefaultRareSkill());
        addAndUnlockCard(new DefaultRarePower());*/
        
        logger.info("Done adding cards");
    }

    private void addAndUnlockCharRelic(AbstractRelic relic)
    {
        //BaseMod.addRelicToCustomPool(relic, TheSynthesist.Enums.SYNTHESIST_COLOR);
        UnlockTracker.markRelicAsSeen(relic.relicId);
    }

    private void addAndUnlockCard(AbstractCard card)
    {
        BaseMod.addCard(card);
        UnlockTracker.unlockCard(card.cardID);
    }

    @Override
    public void receiveEditStrings() {
        logger.info("Feed Cookies to begin string editing");
        logger.info("*Chucks a Chocolate Chip Cookie at the computer*");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        
        
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/Synthesist-Card-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/Synthesist-Power-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/Synthesist-Relic-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/Synthesist-Event-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/Synthesist-Potion-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/Synthesist-Character-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/Synthesist-Orb-Strings.json");
        
        logger.info("NOM NOM NOM (Strings have been edited)");
    }
    
    @Override
    public void receiveEditKeywords() {
        
        
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Synthesist-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
