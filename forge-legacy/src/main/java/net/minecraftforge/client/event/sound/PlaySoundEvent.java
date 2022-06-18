package net.minecraftforge.client.event.sound;

import net.minecraft.client.audio.ISound;
import net.minecraftforge.eventbus.api.Event;

public class PlaySoundEvent extends Event {
    private final ISound sound;

    public PlaySoundEvent(ISound sound) {
        this.sound = sound;
    }

    public ISound getSound() {
        return sound;
    }

    public void setResultSound(ISound result) {

    }
}
