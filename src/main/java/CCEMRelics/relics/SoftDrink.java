package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class SoftDrink extends CustomRelic {
    public static final String ID = makeID(SoftDrink.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SoftDrink.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SoftDrink.png"));

    public SoftDrink() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new EnergizedBluePower(p, 1), 1, true)
        );
    }

}
