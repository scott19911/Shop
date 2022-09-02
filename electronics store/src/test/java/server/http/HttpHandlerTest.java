package server.http;

import com.epam.verizhenko_andrii.electronicsStore.server.http.GetCountCommand;
import com.epam.verizhenko_andrii.electronicsStore.server.http.HttpHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HttpHandlerTest {

    @Test
    public void shouldSocketWrite_whenGetRequestCount() throws IOException {
        Socket socket = mock(Socket.class);
        String request = "GET /shop/count HTTP/1.1\r\n" +
                "Host: reqbin.com\r\n" +
                "Connection: keep-alive\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Accept-Language: en-US,en;q=0.9\r\n" +
                "Accept-Encoding: gzip, deflate";
        ByteArrayInputStream in = new ByteArrayInputStream(request.getBytes());
        OutputStream out = mock(OutputStream.class);
        GetCountCommand getCountCommand = mock(GetCountCommand.class);
        when(getCountCommand.execute()).thenReturn("{count:5}");
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        HttpHandler httpHandler = new HttpHandler(socket);
        verify(socket, atLeast(1)).getInputStream();
        verify(socket, atLeast(1)).getOutputStream();
        verify(out, atLeast(1)).write(any());

    }
}
