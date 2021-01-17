package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class FakeTeeth extends CustomRelic {
    public static final String ID = makeID(FakeTeeth.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("FakeTeeth.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("FakeTeeth.png"));

    public FakeTeeth() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m)
    {
        if(c.type == AbstractCard.CardType.POWER)
        {
            this.flash();
            for(AbstractMonster mo: AbstractDungeon.getMonsters().monsters) {
                if(!mo.isDeadOrEscaped()) {
                    this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new WeakPower(mo ,1, false), 1, true));
                }
            }
        }
    }

}
