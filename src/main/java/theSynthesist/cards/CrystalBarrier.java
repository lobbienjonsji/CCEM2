package theSynthesist.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSynthesist.actions.AddIngredientAction;
import theSynthesist.mix.CrystalShard;
import theSynthesist.theSynthesist;

import static theSynthesist.theSynthesist.makeCardPath;

public class CrystalBarrier extends AbstractSynthesistCard {

    //ONLY KEEP WHAT YOU NEED
    public static final String ID = theSynthesist.makeID(CrystalBarrier.class.getSimpleName());
    public static final String IMG = makeCardPath("CrystalBarrier.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int BLOCK = 10;

    private static final int MAGIC_NUMBER = 5;

    public CrystalBarrier() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(new AddIngredientAction(new CrystalShard(this.magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeBaseCost(UPGRADED_COST);
        }
        super.upgrade();
    }
}
