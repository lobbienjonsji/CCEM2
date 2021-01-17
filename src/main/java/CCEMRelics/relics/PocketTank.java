package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.util.TextureLoader;

import java.util.ArrayList;

import static CCEMRelics.CCEMRelics.*;

public class PocketTank extends CustomRelic {
    public static final String ID = makeID(PocketTank.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PocketTank.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PocketTank.png"));

    public PocketTank() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public int onPlayerGainedBlock(float block)
    {
        if(AbstractDungeon.player.currentBlock >= 30 && !this.pulse)
        {
            this.beginLongPulse();
        }
        return super.onPlayerGainedBlock(block);
    }

    @Override
    public void onPlayerEndTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractMonster strongestMonster = null;
        if(p.currentBlock >= 30)
        {
            this.flash();
            this.stopPulse();
            ArrayList<AbstractMonster> monsters = AbstractDungeon.getMonsters().monsters;
            for(AbstractMonster m : monsters)
            {
                if(!m.isDeadOrEscaped())
                {
                    if(strongestMonster == null)
                    {
                        strongestMonster = m;
                    }
                    else if(m.currentHealth > strongestMonster.currentHealth)
                    {
                        strongestMonster = m;
                    }
                }
            }
            this.addToBot(new RelicAboveCreatureAction(strongestMonster, this));
            this.addToBot(new DamageAction(strongestMonster, new DamageInfo(p, 12, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }
}
