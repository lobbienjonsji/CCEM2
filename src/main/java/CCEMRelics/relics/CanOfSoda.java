package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class CanOfSoda extends CustomRelic {
    public static final String ID = makeID(CanOfSoda.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("CanOfSoda.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("CanOfSoda.png"));

    public CanOfSoda() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip()
    {
        int HPIncrease = 0;
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group)
        {
            if(c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.BASIC)
            {
                HPIncrease++;
            }
        }
        AbstractDungeon.player.increaseMaxHp(HPIncrease, true);
    }
}
