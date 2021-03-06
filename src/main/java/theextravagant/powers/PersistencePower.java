package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class PersistencePower extends TwoAmountPower implements NonStackablePower {
    public static final String POWER_ID = makeID("PersistencePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/persist_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/persist_power32.png");
    public boolean upgraded;

    public PersistencePower(boolean upgraded) {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        type = PowerType.BUFF;
        isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.upgraded = upgraded;
        amount2 = 4;
        amount = 1;
        if (upgraded) {
            amount = 2;
        }
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount2 > 1) {
            description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onInitialApplication() {
        amount2 = 4;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            amount2--;
            if (amount2 == 0) {
                if (!upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
                }
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
            }
        }
    }
}
