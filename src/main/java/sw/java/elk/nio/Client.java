package sw.java.elk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Client {
    public static void main(String[] args) throws IOException {
        //开启传输通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));
        //开启读取通道
        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\admin\\Pictures\\Mario.jpg"), StandardOpenOption.READ);
        //创建缓冲
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (fileChannel.read(byteBuffer) != -1) {
            //读模式
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            //清空，写模式
            byteBuffer.clear();
        }
        socketChannel.shutdownOutput();
        int len=0;
        while ((len = socketChannel.read(byteBuffer) )!= -1) {
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(),0,len));
            byteBuffer.clear();
        }
        socketChannel.close();
        fileChannel.close();

    }
}
