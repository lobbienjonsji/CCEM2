package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class SelfSharpeningBlade extends CustomRelic {
    public static final String ID = makeID("SelfSharpeningBlade");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SelfSharpeningBlade.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SelfSharpeningBlade.png"));

    public SelfSharpeningBlade() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public float atDamageModify(float damage, AbstractCard c)
    {
        if(c.color == AbstractCard.CardColor.COLORLESS)
        {
            return damage + 3.0F;
        }
        else {
            return damage;
        }
    }

}
