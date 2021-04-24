package theSynthesist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSynthesist.theSynthesist;

public class BoilingBoltAction extends AbstractGameAction {

    private static final int DRAW_CARD_AMOUNT = 1;

    private int multiplier;
    private AbstractPlayer p;
    private AbstractMonster m;
    public BoilingBoltAction(final int multiplier, final AbstractMonster target)
    {
        this.multiplier = multiplier;
        this.p = AbstractDungeon.player;
        this.m = target;
    }

    @Override
    public void update() {
        this.isDone = true;
        BoilingBoltCostTracker cost = new BoilingBoltCostTracker();
        //Stop Recording
        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                theSynthesist.boilingBoltTracker = false;
                cost.setCardCost(theSynthesist.boilingBoltCost);
                AbstractDungeon.actionManager.addToTop(
                        new DamageAction(m, new DamageInfo(p, cost.getCardCost() * multiplier, DamageInfo.DamageType.THORNS),
                        AttackEffect.BLUNT_LIGHT));
            }
        });
        //Draw Card
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(DRAW_CARD_AMOUNT));
        //Start Recording
        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                theSynthesist.boilingBoltTracker = true;
            }
        });
    }

    private class BoilingBoltCostTracker
    {
        public int cardCost;
        public BoilingBoltCostTracker()
        {
            cardCost = 0;
        }
        public void setCardCost(int cost)
        {
            this.cardCost = cost;
        }
        public int getCardCost()
        {
            return this.cardCost;
        }
    }
}
