package theSynthesist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theSynthesist.theSynthesist;

import static theSynthesist.theSynthesist.makeCardPath;

public class SnapFreeze extends AbstractSynthesistCard {

    //ONLY KEEP WHAT YOU NEED
    public static final String ID = theSynthesist.makeID(SnapFreeze.class.getSimpleName());
    public static final String IMG = makeCardPath("PlaceholderAttack.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;

    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 4;

    private static final int MAGIC_NUMBER = 1;

    public SnapFreeze() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractCreature target = m;
        AbstractCreature source = p;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source,
                new VulnerablePower(target, this.magicNumber, false), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
        super.upgrade();
    }
}
