package com.prinjsystems.asct.structures;

import java.awt.Color;
import java.awt.Graphics;

public abstract class ActionTile extends Tile {
    private static final long serialVersionUID = -40261091317178060L;

    protected int unpoweredDelay = 4;
    protected boolean canReceivePower = true;
    protected boolean powered;
    private int unpoweredFor = 0;

    protected ActionTile(int posX, int posY, Color color, boolean powered) {
        super(posX, posY, color);
        this.powered = powered;
    }

    protected boolean isPowered() {
        return powered;
    }

    protected void setPowered(boolean powered) {
        this.powered = powered;
    }

    public void trySetPowered(boolean powered, Tile source) {
        if (canReceivePower) {
            this.powered = powered;
            canReceivePower = false;
            temp += 0.1f;
        }
    }

    @Override
    public void render(Graphics g) {
        // #getColor() is based on the implementation, it may change depending on the state of the tile
        g.setColor(getColor());
        g.fillRect(getPosX() * TILE_SIZE, getPosY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public abstract void tick();

    @Override
    public void update() {
        super.update();
        if (!canReceivePower) {
            if (++unpoweredFor == unpoweredDelay) {
                canReceivePower = true;
                unpoweredFor = 0;
            }
        }
    }
}
