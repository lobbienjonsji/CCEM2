package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theextravagant.util.TextureLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static CCEMRelics.CCEMRelics.*;

public class IronFruit extends CustomRelic {
    public static final String ID = makeID(IronFruit.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("IronFruit.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("IronFruit.png"));

    public IronFruit() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip()
    {
        AbstractDungeon.player.increaseMaxHp(5, true);
        ArrayList<AbstractCard> upgradable = new ArrayList<>();
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group)
        {
            if(c.canUpgrade())
            {
                upgradable.add(c);
            }
        }
        if(!upgradable.isEmpty())
        {
            Collections.shuffle(upgradable, new Random(AbstractDungeon.miscRng.randomLong()));
            AbstractCard target = upgradable.get(0);
            target.upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(target);
            AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(target.makeStatEquivalentCopy()));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        }
    }
}
