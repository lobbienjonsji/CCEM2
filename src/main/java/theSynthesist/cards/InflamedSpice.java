package theSynthesist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSynthesist.actions.AddIngredientAction;
import theSynthesist.mix.Flameroot;
import theSynthesist.theSynthesist;

import static theSynthesist.theSynthesist.makeCardPath;

public class InflamedSpice extends AbstractSynthesistCard {

    //ONLY KEEP WHAT YOU NEED
    public static final String ID = theSynthesist.makeID(InflamedSpice.class.getSimpleName());
    public static final String IMG = makeCardPath("InflamedSpice.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 0;

    private static final int DAMAGE = 3;

    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER = 2;

    public InflamedSpice() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.FIRE));
       AbstractDungeon.actionManager.addToBottom(new AddIngredientAction(new Flameroot(this.magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }
        super.upgrade();
    }
}
