package example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.Arrays;

/**
 * @author chenx
 * @create 2023-08-21 17:28
 */
public class ByteBufEx {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(5, 10);
        printByteBufInfo("ByteBufAllocator.buffer(5, 10)", buffer);
        buffer.writeBytes(new byte[]{1, 2});
        printByteBufInfo("write 2 bytes", buffer);
        buffer.writeInt(100);
        printByteBufInfo("write int 100", buffer);
        buffer.writeBytes(new byte[]{3, 4, 5});
        printByteBufInfo("write 3 bytes", buffer);
        byte[] read = new byte[buffer.readableBytes()];
        buffer.readBytes(read);
        printByteBufInfo("readBytes(" + buffer.readableBytes() + ")", buffer);
        printByteBufInfo("BeforeGetAndSet", buffer);
        System.out.println("getInt(2): " + buffer.getInt(2));   //参数表示从哪个字节开始读
        buffer.setByte(1, 0);
        System.out.println("getInt(1): " + buffer.getByte(1));
        printByteBufInfo("AfterGetAndSet", buffer);
        buffer.resetReaderIndex();
        printByteBufInfo("ResetReadIndex", buffer);
        buffer.resetWriterIndex();
        printByteBufInfo("ResetWriteIndex", buffer);
        System.out.println("getByte(1):" + buffer.getByte(0));      //虽然重置了读写指针，但是还是可以读到数据的。
    }

    private static void printByteBufInfo(String step, ByteBuf buffer) {
        System.out.println("------" + step + "-----");
        System.out.println("readerIndex(): " + buffer.readerIndex());
        System.out.println("writerIndex(): " + buffer.writerIndex());
        System.out.println("isReadable(): " + buffer.isReadable());
        System.out.println("isWritable(): " + buffer.isWritable());
        System.out.println("readableBytes(): " + buffer.readableBytes());
        System.out.println("writableBytes(): " + buffer.writableBytes());
        System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
        System.out.println("capacity(): " + buffer.capacity());
        System.out.println("maxCapacity(): " + buffer.maxCapacity());
    }
}
