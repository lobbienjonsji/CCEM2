package CCEMRelics.relics;

import CCEMRelics.patches.ChainField;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class PowerPill extends CustomRelic {
    public static final String ID = makeID(PowerPill.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PowerPill.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PowerPill.png"));

    public PowerPill() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    public Texture getPowerPillTexture()
    {
        return IMG;
    }

    @Override
    public void onObtainCard(AbstractCard card)
    {
        if(card.type == AbstractCard.CardType.POWER)
        {
            ChainField.chain.set(card, true);
        }
    }

    @Override
    public void onEquip()
    {
        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractCard c : p.masterDeck.group)
        {
            if(c.type == AbstractCard.CardType.POWER)
            {
                ChainField.chain.set(c, true);
            }
        }
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractCard c : p.drawPile.group)
        {
            if(c.type == AbstractCard.CardType.POWER)
            {
                ChainField.chain.set(c, true);
            }
        }
        for(AbstractCard c : p.discardPile.group)
        {
            if(c.type == AbstractCard.CardType.POWER)
            {
                ChainField.chain.set(c, true);
            }
        }
        for(AbstractCard c : p.exhaustPile.group)
        {
            if(c.type == AbstractCard.CardType.POWER)
            {
                ChainField.chain.set(c, true);
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
