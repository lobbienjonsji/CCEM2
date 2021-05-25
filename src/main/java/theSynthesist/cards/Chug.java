package theSynthesist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSynthesist.actions.UseMixAction;
import theSynthesist.theSynthesist;

import static theSynthesist.theSynthesist.makeCardPath;

;

public class Chug extends AbstractSynthesistCard {

    //ONLY KEEP WHAT YOU NEED
    public static final String ID = theSynthesist.makeID(Chug.class.getSimpleName());
    public static final String IMG = makeCardPath("Chug.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 1;


    public Chug() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new UseMixAction());
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.exhaust = false;
            this.differentUpgradeDescription = true;
        }
        super.upgrade();
    }
}
