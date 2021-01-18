package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class BundleOfDynamite extends CustomRelic {
    public static final String ID = makeID(BundleOfDynamite.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BundleOfDynamite.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BundleOfDynamite.png"));

    public BundleOfDynamite() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onCardDraw(AbstractCard c)
    {
        AbstractPlayer p = AbstractDungeon.player;

        if(p.drawPile.size() == 0)
        {
            this.flash();
            AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
            this.addToBot(new DamageAction(target, new DamageInfo(p, 15, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            this.addToBot(new RelicAboveCreatureAction(target, this));
        }
    }
}
