package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class ImbuedSpirit extends CustomRelic {
    public static final String ID = makeID(ImbuedSpirit.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ImbuedSpirit.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ImbuedSpirit.png"));

    public ImbuedSpirit() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart()
    {
        AbstractPlayer p = AbstractDungeon.player;
        int powerCount = 0;
        for(AbstractCard c : p.masterDeck.group)
        {
            if(c.type == AbstractCard.CardType.POWER)
            {
                powerCount++;
            }
        }
        if(powerCount > 0)
        {
            this.flash();
            for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters)
            {
                if(!m.isDeadOrEscaped())
                {
                    this.addToBot(new RelicAboveCreatureAction(m, this));
                    this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, powerCount, false), powerCount));
                    this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, powerCount, false), powerCount));
                }
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
