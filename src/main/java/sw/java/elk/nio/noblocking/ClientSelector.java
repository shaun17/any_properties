package sw.java.elk.nio.noblocking;

import org.apache.ibatis.annotations.SelectKey;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class ClientSelector {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",6666));
        socketChannel.configureBlocking(false);
        Selector open = Selector.open();
        socketChannel.register(open, SelectionKey.OP_READ);
        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\admin\\Pictures\\Mario.jpg"), StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(2014);
        while (fileChannel.read(byteBuffer)>0){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        while (open.select()>0){
            Iterator<SelectionKey> iterator = open.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if(next.isReadable()){
                    SocketChannel channel = (SocketChannel)next.channel();
                    ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
                    int read = channel.read(byteBuffer);
                    if(read>0){
                        byteBuffer1.flip();
                        System.out.println(new String(byteBuffer1.array(),0,read));
                    }
                }
                iterator.remove();
            }

        }

    }
}
