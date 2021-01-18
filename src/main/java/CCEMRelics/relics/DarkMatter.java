package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class DarkMatter extends CustomRelic {
    public static final String ID = makeID(DarkMatter.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DarkMatter.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DarkMatter.png"));

    public DarkMatter() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void onCardDraw(AbstractCard c)
    {
        AbstractPlayer p = AbstractDungeon.player;
        if(c instanceof VoidCard)
        {
            this.addToBot(new ExhaustSpecificCardAction(c, p.hand));
            this.addToBot(new GainEnergyAction(2));
            this.flash();
            this.addToBot(new DrawCardAction(1));
        }
    }

    @Override
    public void atTurnStart()
    {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
