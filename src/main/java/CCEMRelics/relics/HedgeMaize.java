package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class HedgeMaize extends CustomRelic {
    public static final String ID = makeID(HedgeMaize.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("HedgeMaize.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("HedgeMaize.png"));

    public HedgeMaize() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEnterRoom(AbstractRoom room)
    {
        if(room instanceof EventRoom)
        {
            this.flash();
            AbstractDungeon.player.heal(5);
        }
    }

}
