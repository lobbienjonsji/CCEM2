package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class TeddyBear extends CustomRelic {
    public static final String ID = makeID(TeddyBear.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TeddyBear.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TeddyBear.png"));

    public TeddyBear() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart()
    {
        AbstractPlayer p = AbstractDungeon.player;
        if(this.counter == -2)
        {
            this.counter = -1;
            this.pulse = false;
            this.flash();
            this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 2), 2, true));
            this.addToBot(new RelicAboveCreatureAction(p, this));
        }
    }

    @Override
    public void setCounter(int counter)
    {
        super.setCounter(counter);
        if(counter == -2)
        {
            this.pulse = true;
        }
    }

    @Override
    public void onEnterRestRoom()
    {
        this.flash();
        this.counter = -2;
        this.pulse = true;
    }
}
