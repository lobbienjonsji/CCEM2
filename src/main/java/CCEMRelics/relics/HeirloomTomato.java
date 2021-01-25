package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class HeirloomTomato extends CustomRelic {
    public static final String ID = makeID(HeirloomTomato.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("HeirloomTomato.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("HeirloomTomato.png"));

    public HeirloomTomato() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void onEquip()
    {
        AbstractDungeon.player.increaseMaxHp(5, true);
    }

    @Override
    public void onVictory() {
        this.flash();
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(2);
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
