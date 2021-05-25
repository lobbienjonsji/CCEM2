package theSynthesist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSynthesist.actions.BoilingBoltAction;
import theSynthesist.actions.ThawMixAction;
import theSynthesist.theSynthesist;

import static theSynthesist.theSynthesist.makeCardPath;

public class BoilingBolt extends AbstractSynthesistCard {

    //ONLY KEEP WHAT YOU NEED
    public static final String ID = theSynthesist.makeID(BoilingBolt.class.getSimpleName());
    public static final String IMG = makeCardPath("BoilingBolt.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 0;

    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER = 1;

    public BoilingBolt() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ThawMixAction());
        AbstractDungeon.actionManager.addToBottom(new BoilingBoltAction(this.magicNumber, m));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.differentUpgradeDescription = true;
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }
        super.upgrade();
    }
}
