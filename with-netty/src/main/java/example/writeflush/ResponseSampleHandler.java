package example.writeflush;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author chenx
 * @create 2023-08-21 16:48
 */
public class ResponseSampleHandler extends MessageToByteEncoder<ResponseSample> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseSample msg, ByteBuf out) throws Exception {
        if(msg != null) {
            out.writeBytes(msg.getCode().getBytes());
            out.writeBytes(msg.getData().getBytes());
            out.writeLong(msg.getTime());
        }
    }
}
