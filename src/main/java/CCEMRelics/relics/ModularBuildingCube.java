package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class ModularBuildingCube extends CustomRelic {
    public static final String ID = makeID(ModularBuildingCube.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ModularBuildingCube.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ModularBuildingCube.png"));

    public ModularBuildingCube() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart()
    {
        AbstractPlayer p = AbstractDungeon.player;
        int blockGain = 0;
        for(AbstractCard c : p.masterDeck.group)
        {
            if(c.color == AbstractCard.CardColor.COLORLESS)
            {
                blockGain += 5;
            }
        }
        if(blockGain > 0)
        {
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, blockGain));

        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
