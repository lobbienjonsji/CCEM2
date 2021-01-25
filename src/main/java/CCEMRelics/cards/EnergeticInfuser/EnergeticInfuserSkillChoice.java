package CCEMRelics.cards.EnergeticInfuser;

import CCEMRelics.CCEMRelics;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class EnergeticInfuserSkillChoice extends AbstractCard {


    public static final String ID = CCEMRelics.makeID("EnergeticInfuserSkillChoice");
    public static final String IMG = "green/skill/defend";
    public static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = -2;

    public EnergeticInfuserSkillChoice() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnergeticInfuserSkillChoice();
    }

    @Override
    public void upgrade() {

    }
}