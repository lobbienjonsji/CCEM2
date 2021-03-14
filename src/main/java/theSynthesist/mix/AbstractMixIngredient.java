package theSynthesist.mix;

import ACCEMCore.util.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractMixIngredient {
    protected int amount;

    protected String[] descriptionStrings;

    public String name;
    public String description;
    public String id;

    private static final float WIDTH = 96.0F;
    private static final float HEIGHT = 96.0F;

    private static final float NUM_X_OFFSET;
    private static final float NUM_Y_OFFSET;

    public Hitbox hb;
    public float cX;
    public float cY;
    public float tX;
    public float tY;
    public Color color;
    private Texture img;
    public float scale;

    public AbstractMixIngredient(final String ID, final int startingAmount)
    {
        this.scale = Settings.scale;
        this.color = Color.WHITE.cpy();

        this.hb = new Hitbox(WIDTH * Settings.scale, HEIGHT * Settings.scale);
        this.id = theSynthesist.theSynthesist.makeID(ID);
        this.amount = startingAmount;
        this.name = languagePack.getOrbString(id).NAME;
        this.descriptionStrings = languagePack.getOrbString(id).DESCRIPTION;
        this.img = TextureLoader.getTexture("theSynthesistResources/images/orbs/" + ID + ".png");
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = descriptionStrings[0] + this.amount + descriptionStrings[1];
    }

    public void addAmount(int amountAdd)
    {
        this.amount += amountAdd;
        this.updateDescription();
        //TODO: Update visuals
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(this.img, this.cX - (WIDTH / 2.0F), this.cY - (HEIGHT / 2.0F), WIDTH / 2.0F, HEIGHT / 2.0F, WIDTH, HEIGHT, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    protected void renderText(SpriteBatch sb)
    {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.amount), this.cX + NUM_X_OFFSET, this.cY + NUM_Y_OFFSET, Color.WHITE.cpy(), 0.7F);
    }

    public void update()
    {
        this.hb.update();
        if(this.hb.hovered)
        {
            TipHelper.renderGenericTip(this.tX + WIDTH * Settings.scale, this.tY + 64.0F * Settings.scale, this.name, this.description);

        }
    }

    public void moveIngredient(float x, float y)
    {
        this.cX = this.tX = x;
        this.cY = this.tY = y;
        this.hb.move(tX, tY);
    }

    public abstract void use();

    static {
        NUM_X_OFFSET = 20.0F * Settings.scale;
        NUM_Y_OFFSET = -12.0F * Settings.scale;
    }
}
