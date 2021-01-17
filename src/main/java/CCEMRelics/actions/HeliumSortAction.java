package CCEMRelics.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Comparator;


public class HeliumSortAction extends AbstractGameAction {
    public HeliumSortAction() {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.isDone = false;
    }

    @Override
    public void update() {
        sortWithComparator(new CardCostComparator(), AbstractDungeon.player.drawPile);
        this.isDone = true;
    }

    private void sortWithComparator(Comparator<AbstractCard> comp, CardGroup c) {
        c.group.sort(comp);
    }

    private class CardCostComparator implements Comparator<AbstractCard> {
        private CardCostComparator() {
        }

        public int compare(AbstractCard c1, AbstractCard c2) {
            if(c1.costForTurn > c2.costForTurn)
            {
                return -1;
            }
            else if(c2.costForTurn > c1.costForTurn)
            {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

}
