package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class GlassOfMilk extends CustomRelic {
    public static final String ID = makeID(GlassOfMilk.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("GlassOfMilk.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("GlassOfMilk.png"));

    public GlassOfMilk() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if(info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0)
        {
            this.counter = -2; // -2 is spilt, -1 is default.
            this.beginLongPulse();
            this.flash();
        }
        return damageAmount;
    }

    @Override
    public void atBattleStart()
    {
        this.counter = -1;
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
        AbstractPlayer p = AbstractDungeon.player;
        this.flash();
        if(this.counter == -2) { // if spilt
            this.addToBot(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng),
                    new DamageInfo(p, 5, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        else
        {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 5));
        }
    }
}
