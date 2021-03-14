package theSynthesist.mix;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Flameroot extends AbstractMixIngredient {

    public static final String ID = Flameroot.class.getSimpleName();

    public Flameroot(int addAmount) {
        super(ID, addAmount);
    }

    @Override
    public void use() {
        AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(null, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    }
}
