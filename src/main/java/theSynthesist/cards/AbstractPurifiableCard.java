package theSynthesist.cards;

import theSynthesist.SynthesistMain;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractPurifiableCard extends AbstractSynthesistCard{

    public int purityNumber;
    public int basePurityNumber;
    public boolean upgradedPurityNumber;
    public boolean isPurityNumberModified;

    private final String PURITY_NAMES = "PurityNames";
    private final String[] PurityNamesArray = languagePack.getCardStrings(SynthesistMain.makeID(PURITY_NAMES)).EXTENDED_DESCRIPTION;

    public AbstractPurifiableCard(
            final String id,
            final String img,
            final int cost,
            final CardType type,
            final CardRarity rarity,
            final CardTarget target
    )
    {
        super(id, img, cost, type, rarity, target);
        purityNumber = 0;
        basePurityNumber = 0;
        upgradedPurityNumber = false;
        isPurityNumberModified = false;
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
        this.setNamePurity();
    }

    protected void setNamePurity()
    {
        String oldName = this.name;
        this.name =  PurityNamesArray[purityNumber] + " " + this.name;
        this.initializeTitle();
        this.name = oldName;
    }

    public void displayPurity()
    {
        if(upgradedPurityNumber)
        {
            purityNumber = basePurityNumber;
            isPurityNumberModified = true;
        }
        this.setNamePurity();
    }

    public void incrementPurity()
    {
        if(purityNumber < 3)
        {
            purityNumber++;
        }
        else
        {
            purityNumber = 2;
        }
        this.setNamePurity();
    }

    public void decrementPurity()
    {
        if(purityNumber > 0)
        {
            purityNumber--;
        }
        else
        {
            purityNumber = 0;
        }
        this.setNamePurity();
    }

}
