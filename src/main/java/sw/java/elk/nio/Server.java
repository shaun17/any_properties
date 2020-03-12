package sw.java.elk.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //获取文件通道
        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\admin\\Desktop\\demo.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //绑定
        serverSocketChannel.bind(new InetSocketAddress(6666));
        //创建缓冲
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //获取客户端
        SocketChannel accept = serverSocketChannel.accept();

        while (accept.read(byteBuffer) != -1) {
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        byteBuffer.put("this is callback".getBytes());
        byteBuffer.flip();
        accept.write(byteBuffer);
        byteBuffer.clear();

        fileChannel.close();
        serverSocketChannel.close();
        accept.close();
    }
}
