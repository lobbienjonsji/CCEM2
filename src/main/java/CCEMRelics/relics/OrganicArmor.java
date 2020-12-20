package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class OrganicArmor extends CustomRelic {
    public static final String ID = makeID("OrganicArmor");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("OrganicArmor.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("OrganicArmor.png"));

    public OrganicArmor() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onPlayerEndTurn() {
        if(!this.grayscale)
        {
            this.grayscale = true;
            AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.currentBlock));
            AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(AbstractDungeon.player, AbstractDungeon.player));
        }
    }

    @Override
    public void onVictory() {
        grayscale = false;
    }
}
