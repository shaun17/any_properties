package sw.java.elk.nio.noblocking;

import org.apache.ibatis.annotations.SelectKey;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class Server {
    public static void main(String[] args) throws IOException {
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定
        serverSocketChannel.bind(new InetSocketAddress(6666));
        //获取选择器
        Selector open = Selector.open();
        //将通道注册到选择器上
        serverSocketChannel.register(open, SelectionKey.OP_ACCEPT);
        //5. 轮训地获取选择器上已“就绪”的事件--->只要select()>0，说明已就绪
        while (open.select() > 0) {
            //当前选择器所以的事件
            Iterator<SelectionKey> iterator = open.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                //接受事件就绪
                if (next.isAcceptable()) {
                    // 8. 获取客户端的链接
                    SocketChannel accept = serverSocketChannel.accept();
                    //设置非阻塞
                    accept.configureBlocking(false);
                    //拿到客户端获取读事件数据
                    accept.register(open, SelectionKey.OP_READ);
                } else if (next.isReadable()) {//读事件就绪
                    // 9. 获取当前选择器读就绪状态的通道
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    FileChannel fileChannel = FileChannel.open(Paths.get("/Users/shaun/Desktop/demo.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                    while (channel.read(byteBuffer) > 0) {
                        byteBuffer.flip();
                        fileChannel.write(byteBuffer);
                        byteBuffer.clear();
                    }
                }
            }
            iterator.remove();
        }
    }
}
