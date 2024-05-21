package simplehttpserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;

/**
 * @author chenx
 * @description 自定义业务逻辑处理器
 * @create 2023-04-28 16:46
 */
public class HttpClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();        //获取到具体的内容
            System.out.println(buf.toString(CharsetUtil.UTF_8));        //打印
            buf.release();      // 手动释放ByteBuf
        }
    }
}
