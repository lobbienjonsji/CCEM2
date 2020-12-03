package theSynthesist.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

import static theSynthesist.SynthesistMain.*;
import static theextravagant.theextravagant.makeID;

public class TheSynthesist extends CustomPlayer {

    public static class Enums {
        @SpireEnum
        public static PlayerClass THE_SYNTHESIST;
        @SpireEnum(name = "SYNTHESIS_COLOR")
        public static AbstractCard.CardColor SYNTHESIST_COLOR;
        @SpireEnum(name = "SYNTHESIST_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 95;
    public static final int MAX_HP = 95;
    public static final int STARTING_GOLD = 121;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;

    private static final String ID = makeID("SynthesistCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    //TODO: Custom orb?
    public static final String[] orbTextures = {
            "theSynthesistResources/images/char/synthesistChar/orb/layer1.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer2.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer3.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer4.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer5.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer6.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer1d.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer2d.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer3d.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer4d.png",
            "theSynthesistResources/images/char/synthesistChar/orb/layer5d.png",};

    public TheSynthesist(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "SynthesistResources/images/char/synthesistChar/orb/vfx.png", null,
                new SpriterAnimation(
                        "SynthesistResources/images/char/synthesistChar/Spriter/SynthesistAnimation.scml"));
        
        
        initializeClass(null,

                THE_SYNTHESIST_SHOULDER_1,
                THE_SYNTHESIST_SHOULDER_2,
                THE_SYNTHESIST_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
                //TODO: fix hitbox or whatever
        
        /*loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        */
        
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);
        //TODO: fix where the dialogue box comes from?
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }
    
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        
        logger.info("Begin loading starter Deck Strings");


        //retVal.add(TestCard.ID);
        //TODO: add cards

        return retVal;
    }
    
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        //TODO: add relics

        //retVal.add(PlaceholderRelic.ID);
        //retVal.add(PlaceholderRelic2.ID);
        //retVal.add(DefaultClickableRelic.ID);
        
        return retVal;
    }
    
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("POTION_2", MathUtils.random(0.9f, 1.1f));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
        //TODO: selection noises?
    }
    
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "RELIC_DROP_MAGICAL";
    }
    
    @Override
    public int getAscensionMaxHPLoss() {
        //TODO: change this later
        return 5;
    }
    
    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.SYNTHESIST_COLOR;
    }
    
    @Override
    public Color getCardTrailColor() {
        return SYNTHESIST_COLOR;
    }
    
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }
    
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }
    
    @Override
    public AbstractCard getStartCardForEvent() {
        //TODO: change this
        return new Strike_Blue();
    }
    
    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }
    
    @Override
    public AbstractPlayer newInstance() {
        return new TheSynthesist(name, chosenClass);
    }
    
    @Override
    public Color getCardRenderColor() {
        return SYNTHESIST_COLOR;
    }
    
    @Override
    public Color getSlashAttackColor() {
        return SYNTHESIST_COLOR;
    }
    
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
        //TODO: change this
    }
    
    @Override
    public String getSpireHeartText() {
        //TODO: change this later
        return TEXT[1];
    }
    
    @Override
    public String getVampireText() {
        //TODO: change this later
        return TEXT[2];
    }

    /*public static CardGroup getParchmentCardGroup()
    {
        return parchmentPlayerSpireField.spireField.parchmentCardGroup.get(AbstractDungeon.player);
    }*/
}
