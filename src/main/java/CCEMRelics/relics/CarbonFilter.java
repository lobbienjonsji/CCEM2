package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class CarbonFilter extends CustomRelic {
    public static final String ID = makeID(CarbonFilter.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("CarbonFilter.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("CarbonFilter.png"));

    public CarbonFilter() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart()
    {
        this.counter = 0;
        this.stopPulse();
    }

    @Override
    public void onVictory()
    {
        this.counter = -1;
        this.stopPulse();
    }

    @Override
    public void atTurnStart()
    {
        if(this.counter > 0)
        {
            this.addToBot(new DrawCardAction(AbstractDungeon.player, this.counter));
            this.counter = 0;
            this.flash();
            this.stopPulse();
        }
    }

    @Override
    public void onExhaust(AbstractCard card)
    {
        this.counter++;
        this.beginLongPulse();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
