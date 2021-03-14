package theSynthesist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

public class UpgradeRandomCardInDrawPileAction extends AbstractGameAction {

    public UpgradeRandomCardInDrawPileAction(int amount)
    {
        this.amount = amount;
    }

    @Override
    public void update() {
        this.isDone = true;
        CardGroup upgradeableCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for(AbstractCard c : AbstractDungeon.player.drawPile.group)
        {
            if(c.canUpgrade())
            {
                upgradeableCards.addToBottom(c);
            }
        }
        upgradeableCards.shuffle();
        if(this.amount > upgradeableCards.size())
        {
            this.amount = upgradeableCards.size();
        }
        for(int i = 0; i < this.amount; i++)
        {
            AbstractCard card = upgradeableCards.group.get(0);
            card.upgrade();
            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(card));
        }
    }
}
