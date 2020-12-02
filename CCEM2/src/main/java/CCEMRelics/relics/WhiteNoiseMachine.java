package CCEMRelics.relics;

import CCEMRelics.CCEMRelics;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.*;
import theextravagant.util.TextureLoader;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static CCEMRelics.CCEMRelics.*;

public class WhiteNoiseMachine extends CustomRelic {
    public static final String ID = makeID("WhiteNoiseMachine");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("WhiteNoiseMachine.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("WhiteNoiseMachine.png"));
    private boolean justenteredroom = false;

    public WhiteNoiseMachine() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
      justenteredroom = true;
    }

    private void reshuffleMap() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method m = AbstractDungeon.class.getDeclaredMethod("generateMap", null);
        m.setAccessible(true);
        m.invoke(AbstractDungeon.class, null);
        logger.info("Reshuffled Map");
    }
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void update() {
        super.update();
        if(justenteredroom && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMPLETE) {
            AbstractRoom CurrentRoom = AbstractDungeon.getCurrRoom();
            logger.info("Reshuffling Map");
            justenteredroom = false;
            int temp = AbstractDungeon.getCurrMapNode().y;
            try {
                this.reshuffleMap();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                logger.info("IllegalAccessException");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                logger.info("NoSuchMethodException");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                logger.info("InvocationTargetException");
            }
            ArrayList<MapRoomNode> List = new ArrayList();
            for (MapRoomNode node : AbstractDungeon.map.get(temp)) {
                if (node != null && node.room != null) {
                    List.add(node);
                }
            }
            AbstractDungeon.firstRoomChosen = true;
            AbstractDungeon.setCurrMapNode(List.get(AbstractDungeon.mapRng.random(0, List.size() - 1)));
            AbstractDungeon.getCurrMapNode().room = CurrentRoom;
            CurrentRoom.phase = AbstractRoom.RoomPhase.COMPLETE;
        }
    }
}
