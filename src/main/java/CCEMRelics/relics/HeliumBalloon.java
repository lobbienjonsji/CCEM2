package CCEMRelics.relics;

import CCEMRelics.actions.HeliumSortAction;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class HeliumBalloon extends CustomRelic {
    public static final String ID = makeID(HeliumBalloon.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("HeliumBalloon.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("HeliumBalloon.png"));

    public HeliumBalloon() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public void atBattleStartPreDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new HeliumSortAction());
    }

    @Override
    public void onShuffle() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new HeliumSortAction());
    }



}
