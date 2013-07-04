package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.gui.Layer;
import org.newdawn.slick.Sound;

public class BGSoundComponent extends Component {

	boolean loop, playing;
	float pitch, vol;
	Sound sound;
	
	public BGSoundComponent(Resources.SOUNDS sound, boolean loop, float pitch, float vol) {
		this.sound = Resources.getSound(sound);
		this.loop = loop;
		this.pitch = pitch;
		this.vol = vol;
	}
	
	public void play(boolean loop, float pitch, float vol) {
		if (loop)
			this.sound.loop(pitch, vol);
		else
			this.sound.play(pitch, vol);
	}

	@Override
	public void update(boolean canHandleInput) {
		
	}
	
	@Override
	public void render() {
		if (!playing) {
			playing = true;
			play(loop, pitch, vol);
		}
	}

}
