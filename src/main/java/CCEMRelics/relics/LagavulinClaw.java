package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class LagavulinClaw extends CustomRelic {
    public static final String ID = makeID(LagavulinClaw.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("LagavulinClaw.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LagavulinClaw.png"));

    public LagavulinClaw() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0 && this.counter == -2) {
            this.flash();
            this.counter = -1;
            this.stopPulse();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    this.isDone = true;
                    AbstractPlayer p = AbstractDungeon.player;
                    if(p.hasPower(MetallicizePower.POWER_ID))
                    {
                        int removeAmount = 8;
                        AbstractPower power = p.getPower(MetallicizePower.POWER_ID);
                        if(power.amount < 8)
                        {
                            removeAmount = power.amount;
                        }
                        AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p, p, power, removeAmount));
                    }
                }
            });
        }
        return damageAmount;
    }

    @Override
    public void onVictory()
    {
        this.stopPulse();
        this.counter = -1;
    }

    @Override
    public void atBattleStart()
    {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, 8), 8));
        this.beginLongPulse();
        this.counter = -2;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
