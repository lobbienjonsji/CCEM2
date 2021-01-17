package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class LatexGloves extends CustomRelic {
    public static final String ID = makeID(LatexGloves.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("LatexGloves.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LatexGloves.png"));

    public LatexGloves() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart()
    {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
    }

    @Override
    public void atTurnStartPostDraw()
    {
        if(this.counter == -2)
        {
            this.counter = -1;
            this.stopPulse();
            this.flash();
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    @Override
    public void onCardDraw(AbstractCard c)
    {
        AbstractPlayer p = AbstractDungeon.player;

        if(p.drawPile.size() == 0)
        {
            this.beginLongPulse();
            this.counter = -2;
        }
    }
}
