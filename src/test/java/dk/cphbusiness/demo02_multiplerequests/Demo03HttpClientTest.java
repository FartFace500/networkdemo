package dk.cphbusiness.demo02_multiplerequests;

import dk.cphbusiness.demo03_httprequest.HttpClient;
import dk.cphbusiness.demo03_httprequest.HttpServer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Demo03HttpClientTest {

    private static final int PORT = 9090;
    private static final String IP = "127.0.0.1";

    private static HttpServer httpServer = new HttpServer();

    @BeforeAll
    public static void setup() {
        System.out.println("setup");
        new Thread(()->httpServer.startConnection(PORT)).start();

    }
    @BeforeEach
    public void setupEach() {
        System.out.println("setupEach");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("tearDown");
        httpServer.stopConnection();
    }

    @Test
    @DisplayName("Test http server and client")
    public void testHttpServerAndClient() {
        HttpClient client = new HttpClient();
        client.startConnection("localhost", PORT);
        client.sendMessage("GET /pages/index.html HTTP/1.1\n" +
                "Host: localhost");

        String expected = "HTTP/1.1 200 OK\n" +
                "Date: Mon, 23 May 2022 22:38:34 GMT\n" +
                "Server: Apache/2.4.1 (Unix)\n" +
                "Content-Type: text/html; charset=UTF-8\n" +
                "Content-Length: 107\n" +
                "Connection: close\n" +
                "\n" +
                "<html><head><title>monkey world</title></head><body><h1>Funny Monkey :3</h1><p>mmm banana</p></body></html>";
        String actual = client.getResponse();
        assertEquals(expected, actual);
        client.stopConnection();
    }
}