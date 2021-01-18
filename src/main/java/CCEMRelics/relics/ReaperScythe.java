package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class ReaperScythe extends CustomRelic {
    public static final String ID = makeID(ReaperScythe.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ReaperScythe.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ReaperScythe.png"));

    public ReaperScythe() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    private boolean firstTurn = true;
    private static final float KILL_THRESHOLD = 0.33F;

    @Override
    public void atBattleStart()
    {
        firstTurn = true;
    }

    @Override
    public void atTurnStart()
    {
        if(firstTurn)
        {
            firstTurn = false;
        }
        else
        {
            reapCheck();
        }
    }

    @Override
    public void onPlayerEndTurn()
    {
        reapCheck();
    }

    private void reapCheck()
    {
        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters)
        {
            if(!m.isDeadOrEscaped())
            {
                if(((float)m.currentHealth / (float)m.maxHealth) < KILL_THRESHOLD)
                {
                    this.flash();
                    this.addToBot(new RelicAboveCreatureAction(m, this));
                    this.addToBot(new InstantKillAction(m));
                }
            }
        }
        if(((float)p.currentHealth / (float)p.maxHealth) < KILL_THRESHOLD)
        {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(p, this));
            this.addToBot(new InstantKillAction(p));
            this.addToBot(new DamageAction(p, new DamageInfo(p, 1, DamageInfo.DamageType.HP_LOSS)));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
