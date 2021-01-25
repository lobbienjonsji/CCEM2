package CCEMRelics.relics;

import CCEMRelics.patches.ChainField;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theextravagant.util.TextureLoader;

import java.util.ArrayList;

import static CCEMRelics.CCEMRelics.*;

public class ChainBlade extends CustomRelic {
    public static final String ID = makeID(ChainBlade.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ChainBlade.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ChainBlade.png"));

    public ChainBlade() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public void onEquip()
    {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> toDupe = new ArrayList<>();
        for(AbstractCard c : CardGroup.getGroupWithoutBottledCards(p.masterDeck).group)
        //ignores any cards in bottles as I don't really know how to take modded cards out of other mod bottles.
        {
            if(ChainField.chain.get(c))
            {
                toDupe.add(c.makeStatEquivalentCopy());
            }
        }
        if(!toDupe.isEmpty())
        {
            for(AbstractCard c : toDupe)
            {
                AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
