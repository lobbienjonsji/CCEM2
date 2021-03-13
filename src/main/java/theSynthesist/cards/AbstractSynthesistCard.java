package theSynthesist.cards;

import basemod.abstracts.CustomCard;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theSynthesist.characters.TheSynthesist.Enums.SYNTHESIST_COLOR;

public abstract class AbstractSynthesistCard extends CustomCard {

    public boolean differentUpgradeDescription;

    public AbstractSynthesistCard(
            final String id,
            final String name,
            final String img,
            final int cost,
            final String rawDescription,
            final CardType type,
            final CardColor color,
            final CardRarity rarity,
            final CardTarget target
    )
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;

        differentUpgradeDescription = false;
    }

    public AbstractSynthesistCard(
            final String id,
            final String img,
            final int cost,
            final CardType type,
            final CardRarity rarity,
            final CardTarget target
    )
    {
        this(id, languagePack.getCardStrings(id).NAME, img, cost,
                languagePack.getCardStrings(id).DESCRIPTION, type, SYNTHESIST_COLOR, rarity, target);

    }

    @Override public void upgrade()
    {
        if(!this.upgraded) {
            if(this.differentUpgradeDescription)
            {
                this.rawDescription = languagePack.getCardStrings(this.cardID).UPGRADE_DESCRIPTION;
            }
            this.upgradeName();
            this.initializeDescription();
        }
    }

}
