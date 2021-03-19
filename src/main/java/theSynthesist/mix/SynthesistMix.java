package theSynthesist.mix;

import ACCEMCore.util.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import theSynthesist.theSynthesist;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class SynthesistMix {
    private static final String MIX_TEXTURE = "theSynthesistResources/images/orbs/Mix.png";

    public int turnCounter;
    private static final int TURN_COUNTER_MIN = 0;
    public boolean frozen;
    private static final int TURNS_TO_DRINK = 3;

    private float angleOffset = 360.0F;
    private static final float ANGLE_MIN = 0.0F;
    private static final float ANGLE_MAX = 360.0F;
    private static final float ANGLE_CHANGE_RATE = 6.0F;

    public ArrayList<AbstractMixIngredient> ingredients;
    public Hitbox hb;
    private static final float NUM_X_OFFSET;
    private static final float NUM_Y_OFFSET;
    private static final float WIDTH = 96.0F;
    private static final float HEIGHT = 96.0F;
    private static final float FLOAT_X_OFFSET = 250.0F;
    private static final float INGREDIENT_OFFSET = 100.0F;
    public float cX;
    public float cY;
    public float tX;
    public float tY;
    public Color color;
    private Texture img;
    private float scale;

    private static final String NAME = languagePack.getOrbString(theSynthesist.makeID("Mix")).NAME;
    private static final String[] DESCRIPTION = languagePack.getOrbString(theSynthesist.makeID("Mix")).DESCRIPTION;
    private static final String NAME_FROZEN = languagePack.getOrbString(theSynthesist.makeID("MixFrozen")).NAME;
    private static final String DESCRIPTION_FROZEN = languagePack.getOrbString(theSynthesist.makeID("MixFrozen")).DESCRIPTION[0];


    public SynthesistMix()
    {
        ingredients = new ArrayList<>();

        this.scale = Settings.scale;
        this.color = Color.WHITE.cpy();
        this.hb = new Hitbox(WIDTH * Settings.scale, HEIGHT * Settings.scale);
        this.img = TextureLoader.getTexture(MIX_TEXTURE);

        this.cX = this.tX = FLOAT_X_OFFSET * Settings.scale + AbstractDungeon.player.drawX + AbstractDungeon.player.hb_w / 2.0F;
        this.cY = this.tY = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0F;
        this.hb.move(tX, tY);
    }

    public void mixEndTurn()
    {
        if(!frozen) {
            turnCounter++;
            if (turnCounter >= TURNS_TO_DRINK) {
                turnCounter = TURN_COUNTER_MIN;
                drinkMix();
            }
        }

    }

    public void addIngredient(AbstractMixIngredient ingredientToAdd)
    {
        if(!frozen) {
            boolean alreadyAdded = false;

            for (AbstractMixIngredient i : ingredients) {
                if (ingredientToAdd.id.equals(i.id)) {
                    i.addAmount(ingredientToAdd.amount);
                    alreadyAdded = true;
                }
            }
            if (!alreadyAdded) {
                ingredients.add(ingredientToAdd);
            }
            setIngredientPositions();
        }
    }

    public void drinkMix()
    {
        for(AbstractMixIngredient i : ingredients)
        {
            i.use();
        }
        ingredients.clear();
        turnCounter = TURN_COUNTER_MIN;
    }

    public void update()
    {
        boolean isIngredientBeingHovered = false;
        for(AbstractMixIngredient i : ingredients)
        {
            i.update();
            if(i.hb.hovered)
            {
                isIngredientBeingHovered = true;
            }
        }


        if(!isIngredientBeingHovered) {
            this.angleOffset -= ANGLE_CHANGE_RATE * Gdx.graphics.getDeltaTime();
        }
        while(this.angleOffset <= ANGLE_MIN)
        {
            this.angleOffset += ANGLE_MAX;
        }
        this.setIngredientPositions();


        this.hb.update();
        if(this.hb.hovered)
        {
            String descriptionToShow;
            ArrayList<PowerTip> tips = new ArrayList<>();
            if(this.turnCounter < TURNS_TO_DRINK - 1) {
                descriptionToShow = DESCRIPTION[0] + DESCRIPTION[1] + (TURNS_TO_DRINK - this.turnCounter) + DESCRIPTION[2];
            }
            else {
                descriptionToShow = DESCRIPTION[0] + DESCRIPTION[3];
            }
            PowerTip tip = new PowerTip(NAME, descriptionToShow);
            tips.add(tip);
            if(this.frozen)
            {
                PowerTip tipFrozen = new PowerTip(NAME_FROZEN, DESCRIPTION_FROZEN);
                tips.add(tipFrozen);
            }
            TipHelper.queuePowerTips(this.tX + WIDTH * Settings.scale, this.tY + 64.0F * Settings.scale, tips);
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(this.img, this.cX - (WIDTH / 2.0F), this.cY - (HEIGHT / 2.0F), WIDTH / 2.0F, HEIGHT / 2.0F, WIDTH, HEIGHT, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
        renderText(sb);
        this.hb.render(sb);
        for(AbstractMixIngredient i : ingredients)
        {
            i.render(sb);
        }
    }

    protected void renderText(SpriteBatch sb)
    {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(TURNS_TO_DRINK - this.turnCounter), this.cX + NUM_X_OFFSET, this.cY + NUM_Y_OFFSET, Color.WHITE.cpy(), 0.7F);
    }

    public void setIngredientPositions()
    {
        for(int i = 0; i < ingredients.size(); i++)
        {
            positionIngredient(ingredients.get(i), i, ingredients.size());
        }
    }

    private void positionIngredient(AbstractMixIngredient ingredient, int index, int maxNum) {
        float dist = (INGREDIENT_OFFSET) * Settings.scale;
        //float angle = 100.0F + (float)maxNum * 12.0F;
        float angle = (ANGLE_MAX / (float)maxNum) * (float)index;

        float ingredientX = dist * MathUtils.cosDeg(angle + angleOffset) + this.cX;
        float ingredientY = dist * MathUtils.sinDeg(angle + angleOffset) + this.cY;

        ingredient.moveIngredient(ingredientX, ingredientY);
    }


    static {
        NUM_X_OFFSET = 20.0F * Settings.scale;
        NUM_Y_OFFSET = -12.0F * Settings.scale;
    }
}
