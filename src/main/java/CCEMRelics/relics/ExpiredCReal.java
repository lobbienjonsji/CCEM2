package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class ExpiredCReal extends CustomRelic {
    public static final String ID = makeID(ExpiredCReal.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ExpiredCReal.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ExpiredCReal.png"));

    public ExpiredCReal() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.SOLID);
    }

    @Override
    public void atBattleStart()
    {
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters)
        {
            if(!m.isDeadOrEscaped())
            {
                this.addToTop(new RelicAboveCreatureAction(m, this));
                m.addPower(new IntangiblePower(m, 2));
            }
        }
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
