import io.TransitoryChannel;
import io.FileIO;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class StreamsExp {

    public static void main(String[] args) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(500000);
        TransitoryChannel channel = new TransitoryChannel();
        FileIO.iotest(new File("sample.txt"), buf, channel);
    }

}
