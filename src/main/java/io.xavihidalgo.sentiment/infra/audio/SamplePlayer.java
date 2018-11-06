package io.xavihidalgo.sentiment.infra.audio;

import javax.sound.sampled.*;
import java.io.File;

public class SamplePlayer {

	public void play(File file) {
		try {
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;
			final Clip clip;

			stream = AudioSystem.getAudioInputStream(file);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);

			clip.addLineListener(new LineListener() {
				public void update(LineEvent event) {
					if (event.getType() == LineEvent.Type.STOP)
						clip.close();
				}
			});
			clip.open(stream);
			clip.start();
		} catch (Exception e) {
			throw new RuntimeException(e.getClass().getSimpleName() + " " + e.getMessage());
		}
	}
}
