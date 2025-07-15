package de.jonas.engine.utils;
import org.lwjgl.*;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class IOUtil {

    private IOUtil() {
    }

    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    public static ByteBuffer ioResourceToByteBuffer(String resourcePath, int bufferSize) throws IOException {
        try (InputStream source = IOUtil.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (source == null) {
                throw new FileNotFoundException("Resource not found in classpath: " + resourcePath);
            }

            try (ReadableByteChannel rbc = Channels.newChannel(source)) {
                ByteBuffer buffer = createByteBuffer(bufferSize);

                while (true) {
                    int bytes = rbc.read(buffer);
                    if (bytes == -1) break;

                    if (buffer.remaining() == 0) {
                        buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2); // grow by 50%
                    }
                }

                buffer.flip();
                return memSlice(buffer);
            }
        }
    }

}

