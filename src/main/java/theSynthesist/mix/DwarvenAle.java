package theSynthesist.mix;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSynthesist.actions.UpgradeRandomCardInDrawPileAction;

public class DwarvenAle extends AbstractMixIngredient {

    public static final String ID = DwarvenAle.class.getSimpleName();

    public DwarvenAle(int addAmount) {
        super(ID, addAmount);
    }

    @Override
    public void updateDescription()
    {
        if(this.amount == 1)
        {
            this.description = descriptionStrings[0] + this.amount + descriptionStrings[1];
        }
        else
        {
            this.description = descriptionStrings[0] + this.amount + descriptionStrings[2];
        }
    }

    @Override
    public void use() {
        AbstractDungeon.actionManager.addToBottom(
                new UpgradeRandomCardInDrawPileAction(this.amount)
        );
    }
}
