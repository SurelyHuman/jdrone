package uab.se.drone.control.physical.tello;
import org.jcodec.codecs.h264.H264Decoder;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;

import uab.se.drone.connection.VideoServer;

import java.nio.ByteBuffer;

public class TelloCameraServer extends VideoServer{
	private H264Decoder decoder;

	public TelloCameraServer(int videoPort, int length) {
		super(videoPort, length);
		this.decoder = new H264Decoder();
	}
	
	@Override
    protected void handle(byte[] message) {
        Picture out = Picture.create(1920, 1088, ColorSpace.YUV420); // Allocate output frame of max size
        Picture real = decoder.decodeFrame(ByteBuffer.wrap(message), out.getData());
        System.out.println(real.getWidth() +  " : " + real.getHeight());
    }
}
