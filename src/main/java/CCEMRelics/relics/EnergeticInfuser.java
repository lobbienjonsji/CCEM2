package CCEMRelics.relics;

import CCEMRelics.cards.EnergeticInfuser.EnergeticInfuserAttackChoice;
import CCEMRelics.cards.EnergeticInfuser.EnergeticInfuserPowerChoice;
import CCEMRelics.cards.EnergeticInfuser.EnergeticInfuserSkillChoice;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class EnergeticInfuser extends CustomRelic implements CustomSavable<Integer> {
    public static final String ID = makeID(EnergeticInfuser.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("EnergeticInfuser.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("EnergeticInfuser.png"));

    public EnergeticInfuser() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    private boolean cardSelected = true;
    private int typeSelect = -1;
    /*
    typeSelect = 0 means ATTACK
    typeSelect = 1 means SKILL
    typeSelect = 2 means POWER
    typeSelect = -1 means NULL
     */

    @Override
    public void atBattleStart()
    {
        applyDiscount();
        this.flash();
    }

    @Override
    public void update()
    {
        super.update();
        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            cardSelected = true;
            AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);

            if(card instanceof EnergeticInfuserAttackChoice)
            {
                typeSelect = 0;
            }
            else if(card instanceof EnergeticInfuserSkillChoice)
            {
                typeSelect = 1;
            }
            else if(card instanceof EnergeticInfuserPowerChoice)
            {
                typeSelect = 2;
            }
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.INCOMPLETE) {
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            setDescriptionAfterLoading();
        }
    }

    private void applyDiscount()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard.CardType chosenType;
        switch(typeSelect)
        {
            case 0:
                chosenType = AbstractCard.CardType.ATTACK;
                break;
            case 1:
                chosenType = AbstractCard.CardType.SKILL;
                break;
            case 2:
                chosenType = AbstractCard.CardType.POWER;
                break;
            default:
                chosenType = null;
        }
        if(chosenType != null) {
            for (AbstractCard c : p.drawPile.group) {
                if (c.type == chosenType) {
                    c.modifyCostForCombat(-1);
                }
            }
            for (AbstractCard c : p.discardPile.group) {
                if (c.type == chosenType) {
                    c.modifyCostForCombat(-1);
                }
            }
            for (AbstractCard c : p.exhaustPile.group) {
                if (c.type == chosenType) {
                    c.modifyCostForCombat(-1);
                }
            }
        }
    }

    @Override
    public void onEquip()
    {
        cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        CardGroup choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        choices.addToTop(new EnergeticInfuserAttackChoice());
        choices.addToTop(new EnergeticInfuserSkillChoice());
        choices.addToTop(new EnergeticInfuserPowerChoice());
        AbstractDungeon.gridSelectScreen.open(choices, 1, DESCRIPTIONS[1] + name + DESCRIPTIONS[2], false, false, false, false);
    }

    @Override
    public Integer onSave()
    {
        return typeSelect;
    }

    @Override
    public void onLoad(Integer type)
    {
        typeSelect = type;
        setDescriptionAfterLoading();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void setDescriptionAfterLoading() {
        this.description = this.name + DESCRIPTIONS[3] + DESCRIPTIONS[typeSelect + 5] + DESCRIPTIONS[8];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

}
