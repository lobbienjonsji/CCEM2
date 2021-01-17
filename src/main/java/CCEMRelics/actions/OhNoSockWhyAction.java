package CCEMRelics.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class OhNoSockWhyAction extends AbstractGameAction {
    private AbstractCard moveToBottom;
    public OhNoSockWhyAction(AbstractCard card)
    {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.isDone = false;
        moveToBottom = null;
        for(AbstractCard c : AbstractDungeon.player.drawPile.group)
        {
            if(c.uuid.equals(card.uuid))
            {
                moveToBottom = c;
            }
        }
    }

    @Override
    public void update()
    {
        this.isDone = true;
        if(moveToBottom != null) {
            AbstractDungeon.player.drawPile.moveToBottomOfDeck(moveToBottom);
        }
    }
}
