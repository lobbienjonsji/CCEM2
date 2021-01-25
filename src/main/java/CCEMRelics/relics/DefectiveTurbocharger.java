package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class DefectiveTurbocharger extends CustomRelic {
    public static final String ID = makeID(DefectiveTurbocharger.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DefectiveTurbocharger.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DefectiveTurbocharger.png"));

    public DefectiveTurbocharger() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart()
    {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -15), -15));
        this.addToBot(new ApplyPowerAction(p, p, new RitualPower(p,5, true), 5));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
