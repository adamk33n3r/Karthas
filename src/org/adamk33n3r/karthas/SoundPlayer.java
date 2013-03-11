package org.adamk33n3r.karthas;

import org.newdawn.slick.Sound;

public class SoundPlayer {
	
	private static Sound sound;
	
	public static void play(Resources.SOUNDS sound, float pitch, float vol) {
		SoundPlayer.sound = Resources.getSound(sound);
		SoundPlayer.sound.play(pitch, vol);
	}
}
