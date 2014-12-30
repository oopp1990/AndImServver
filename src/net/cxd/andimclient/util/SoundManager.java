package net.cxd.andimclient.util;

import java.util.HashMap;

import net.cxd.andimclient.R;
import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;

public class SoundManager {

	private static SoundManager soundManager;
	private Context context;
	private SoundPool soundPool;
	private HashMap<Integer, Integer> soundPoolMap;
	public static final int SHOOT = 1;

	private SoundManager(Context context) {
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();
		soundPoolMap.put(SHOOT, soundPool.load(context, R.raw.beep, 1));
		this.context = context;
	}

	public static SoundManager newInstance(Context context) {
		if (soundManager == null) {
			soundManager = new SoundManager(context);

		}
		return soundManager;
	}

	public void playSound(int type) {
		AudioManager mgr = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = streamVolumeCurrent / streamVolumeMax;

		/* 使用正确音量播放声音 */
		soundPool.play(soundPoolMap.get(type), volume, volume, 1, 0, 1f);
	}

	public void vibrate() {
		Vibrator vibrator = (Vibrator) context
				.getSystemService(Service.VIBRATOR_SERVICE);
		vibrator.vibrate(new long[] { 100, 10, 10, 100 }, -1); // -1短震动
	}

}
