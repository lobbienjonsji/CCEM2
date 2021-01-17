package ACCEMCore;

import CCEMRelics.CCEMRelics;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theSynthesist.SynthesistMain;
import theextravagant.theextravagant;

import java.util.Properties;

@SpireInitializer
public class ACCEMCore implements
        EditStringsSubscriber,
        PostInitializeSubscriber
    {
    public static final Logger logger = LogManager.getLogger(ACCEMCore.class.getName());

    public static final String ENABLE_EXTRAVAGANT = "enableEnableExtravagant";
    public static final String ENABLE_SYNTHESIST = "enableSynthesistChar";
    private static final String AUTHOR = "Lobbienjonsji, Left Click, EnderGrimm";
    //private static final String DESCRIPTION = "Im not your ordinary byrd. I am a peacock.";
    private static final String DESCRIPTION = "Core for CCEM";
    private static final String CARD_ENERGY_ORB = "theextravagantResources/images/512/card_small_orb.png";

    public static boolean isExtravagantEnabled = true;
    public static boolean isSynthesistEnabled = true;
    public static final String EXTRAVAGANT_DEFAULT = Boolean.toString(true);
    public static final String SYNTHESIST_DEFAULT = Boolean.toString(false); //TESTING

    private static SpireConfig CCEMConfig;

    public static Properties CCEMDefaultSettings = new Properties();

    public static ACCEMCore ACCEMCore;
    public static CCEMRelics CCEMRelics;
    public static theextravagant theextravagant;
    public static SynthesistMain SynthesistMain;

    public ACCEMCore() {
        BaseMod.subscribe(this);
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("============CCEM NOW INITIALIZING CORE============");
        ACCEMCore = new ACCEMCore();
        logger.info("============CCEM FINISHED INITIALIZING CORE============");

        //CONFIG
        CCEMConfig = makeConfig();
        setConfig(CCEMConfig);
        //CONFIG


        logger.info("============CCEM NOW INITIALIZING RELICS============");
        CCEMRelics = new CCEMRelics();
        logger.info("============CCEM FINISHED INITIALIZING RELICS============");

        if(isExtravagantEnabled)
        {
            logger.info("============CCEM NOW INITIALIZING THE EXTRAVAGANT============");
            theextravagant = new theextravagant();
            logger.info("============CCEM FINISHED INITIALIZING THE EXTRAVAGANT============");
        }
        if(isSynthesistEnabled)
        {
            logger.info("============CCEM NOW INITIALIZING THE SYNTHESIST============");
            SynthesistMain = new SynthesistMain();
            logger.info("============CCEM FINISHED INITIALIZING THE SYNTHESIST============");
        }

    }

    public static String getModID() {
        return "ACCEMCore";
    }

    @Override
    public void receivePostInitialize() {
        UIStrings EnableCharacters = CardCrawlGame.languagePack.getUIString("EnableCharacters");
        Texture Modtexture = new Texture(Gdx.files.internal(CARD_ENERGY_ORB));
        ModPanel settingsPanel = new ModPanel();

        //EXTRAVAGANT ENABLE BUTTON
        ModLabeledToggleButton enableExtravagantButton = new ModLabeledToggleButton(EnableCharacters.TEXT[0],
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                isExtravagantEnabled,
                settingsPanel,
                (label) -> {
                },
                (button) -> {
                    isExtravagantEnabled = button.enabled;
                    try {
                        CCEMConfig.setBool(ENABLE_EXTRAVAGANT, isExtravagantEnabled);
                        CCEMConfig.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        //EXTRAVAGANT ENABLE BUTTON

        //SYNTHESIST ENABLE BUTTON
        ModLabeledToggleButton enableSynthesistButton = new ModLabeledToggleButton(EnableCharacters.TEXT[1], 350f,
                500f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                isSynthesistEnabled,
                settingsPanel,
                (label) -> {},
                (button) -> {
                    isSynthesistEnabled = button.enabled;
                    try {
                        CCEMConfig.setBool(ENABLE_SYNTHESIST, isSynthesistEnabled);
                        CCEMConfig.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        //SYNTHESIST ENABLE BUTTON

        settingsPanel.addUIElement(enableExtravagantButton);
        settingsPanel.addUIElement(enableSynthesistButton);
        BaseMod.registerModBadge(Modtexture, "CCEM", AUTHOR, DESCRIPTION, settingsPanel);
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.UIStrings.class,
                getModID() + "Resources/localization/eng/ACCEMCore-UI-Strings.json");
    }

    private static SpireConfig makeConfig()
    {
        CCEMDefaultSettings.setProperty(ENABLE_EXTRAVAGANT, EXTRAVAGANT_DEFAULT);
        CCEMDefaultSettings.setProperty(ENABLE_SYNTHESIST, SYNTHESIST_DEFAULT);

        try
        {
            SpireConfig config = new SpireConfig("CCEM", "CCEM", CCEMDefaultSettings);
            return config;
        }
        catch
        (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static void setConfig(SpireConfig config)
    {
        if(config != null)
        {
            isExtravagantEnabled = config.getBool(ENABLE_EXTRAVAGANT);
            isSynthesistEnabled = config.getBool(ENABLE_SYNTHESIST);
        }
    }
}
