package CCEMRelics.relics;

import CCEMRelics.patches.ChainField;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class FiftyFTOfRope extends CustomRelic implements CustomSavable<String> {
    public static final String ID = makeID(FiftyFTOfRope.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("FiftyFTOfRope.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("FiftyFTOfRope.png"));

    private boolean cardSelected = true;
    private String cardID;

    public FiftyFTOfRope() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    public Texture getFiftyFTOfRopeTexture()
    {
        return IMG;
    }

    public String getFiftyFTOfRopeCardID()
    {
        return cardID;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStartPreDraw() {
        super.atBattleStartPreDraw();
        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractCard c : p.drawPile.group)
        {
            if(c.cardID.equals(cardID))
            {
                ChainField.chain.set(c, true);
            }
        }
        for(AbstractCard c : p.discardPile.group)
        {
            if(c.cardID.equals(cardID))
            {
                ChainField.chain.set(c, true);
            }
        }
        for(AbstractCard c : p.exhaustPile.group)
        {
            if(c.cardID.equals(cardID))
            {
                ChainField.chain.set(c, true);
            }
        }
    }

    @Override
    public void onEquip()
    {
        cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        CardGroup masterDeckSkills = AbstractDungeon.player.masterDeck.getSkills();
        AbstractDungeon.gridSelectScreen.open(masterDeckSkills, 1, DESCRIPTIONS[1] + name + DESCRIPTIONS[2], false, false, false, false);
    }

    @Override
    public void onUnequip()
    {
        if(cardID != null)
        {
            for(AbstractCard c : AbstractDungeon.player.masterDeck.group)
            {
                if(c.cardID.equals(cardID))
                {
                    if(ChainField.chain.get(c)) {
                        ChainField.chain.set(c, false);
                    }
                }
            }
        }
    }

    @Override
    public void update()
    {
        super.update();
        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            cardSelected = true;
            AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            cardID = card.cardID;
            for(AbstractCard c : AbstractDungeon.player.masterDeck.group)
            {
                if(cardID.equals(c.cardID))
                {
                    ChainField.chain.set(c, true);
                }
            }
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.INCOMPLETE) {
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            setDescriptionAfterLoading();
        }
    }

    @Override
    public void onObtainCard(AbstractCard c)
    {
        if(c.cardID.equals(cardID))
        {
            ChainField.chain.set(c, true);
        }
    }

    @Override
    public String onSave()
    {
        if(cardID != null)
        {
            return cardID;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void onLoad(String ID)
    {
        cardID = ID;
        if(ID != null) {
            for(AbstractCard c : AbstractDungeon.player.masterDeck.group)
            {
                if(cardID.equals(c.cardID))
                {
                    ChainField.chain.set(c, true);
                }
            }
            setDescriptionAfterLoading();
        }
    }


    public void setDescriptionAfterLoading() {
        String cardName = CardCrawlGame.languagePack.getCardStrings(cardID).NAME;
        this.description = DESCRIPTIONS[3] + FontHelper.colorString(cardName, "y") + DESCRIPTIONS[4];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

}
