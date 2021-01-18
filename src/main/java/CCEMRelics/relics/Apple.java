package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class Apple extends CustomRelic {
    public static final String ID = makeID(Apple.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Apple.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Apple.png"));

    public Apple() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public void onVictory()
    {
        AbstractPlayer p = AbstractDungeon.player;
        if(((float)p.currentHealth / (float)p.maxHealth) < 0.5F) {
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));// 24
            p.increaseMaxHp(3, true);
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
