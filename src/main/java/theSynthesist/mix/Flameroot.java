package theSynthesist.mix;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSynthesist.SynthesistMain;

public class Flameroot extends AbstractMixIngredient {

    public static final String ID = SynthesistMain.makeID(Flameroot.class.getSimpleName());

    public Flameroot(int addAmount) {
        super(ID);
    }

    @Override
    public void use() {
        AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(null, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    }
}
